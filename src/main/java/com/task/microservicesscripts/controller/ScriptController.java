package com.task.microservicesscripts.controller;

import com.task.microservicesscripts.entity.Script;
import com.task.microservicesscripts.exception.ScriptNotFoundException;
import com.task.microservicesscripts.exception.WrongScriptDataException;
import com.task.microservicesscripts.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

@RestController
public class ScriptController {

    private final ScriptService service;

    @Autowired
    ScriptController(ScriptService service) {
        this.service = service;
    }

    @GetMapping("/scripts")
    public List<Script> getAll() {
        return service.getAll();
    }

    @GetMapping("/scripts/{id}")
    public Script getById(@PathVariable int id) throws ScriptNotFoundException {
        return service.getById(id);
    }

    @PostMapping(value = "/scripts",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public Script addScript(@RequestParam String name,
                            @RequestParam String description,
                            @RequestPart("command") MultipartFile file) throws WrongScriptDataException {

        return service.addScript(name, description, file);
    }

    @PutMapping(value = "/scripts/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public Script updateScript(@PathVariable int id,
                               @RequestParam String newName,
                               @RequestParam String newDescription,
                               @RequestPart("file") MultipartFile newCommand)
            throws ScriptNotFoundException, IOException, WrongScriptDataException {
        return service.updateScript(id, newName, newDescription, newCommand);
    }

    @DeleteMapping("/scripts/{id}")
    public void deleteScript(@PathVariable int id) {
        service.deleteScript(id);
    }

    @PostMapping("/scripts/{id}/run")
    public int runScript(@PathVariable int id,
                         @RequestBody SortedMap<String, List<String>> args) {
        return service.run(id, args);
    }

    @GetMapping("/scripts/{id}/error")
    public String getError(@PathVariable int id) {
        return service.getError(id);
    }

    @ResponseBody
    @ExceptionHandler(ScriptNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleNotFoundException(Exception exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler({WrongScriptDataException.class, IOException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleBadRequestException(Exception exception) {
        return exception.getMessage();
    }
}
