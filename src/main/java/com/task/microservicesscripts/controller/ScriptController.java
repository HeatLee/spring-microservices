package com.task.microservicesscripts.controller;

import com.task.microservicesscripts.entity.Script;
import com.task.microservicesscripts.exception.ScriptNotFoundException;
import com.task.microservicesscripts.exception.WrongScriptDataException;
import com.task.microservicesscripts.repository.ScriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScriptController {

    @GetMapping("/scripts")
    public List<Script> getAll() {

    }

    @GetMapping("/scripts/{id}")
    public Script getById(@PathVariable int id) {

    }

    @PostMapping("/scripts")
    public Script newScript(@Validated @RequestBody Script script) {

    }

    @PutMapping("/scripts/{id}")
    public Script updateScript(@PathVariable int id, @Validated @RequestBody Script newScript) {

    }

    @ResponseBody
    @ExceptionHandler(ScriptNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleNotFoundException(Exception exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(WrongScriptDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleBadRequestException(Exception exception) {
        return exception.getMessage();
    }
}
