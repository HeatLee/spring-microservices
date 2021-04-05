package com.task.microservicesscripts.service;

import com.task.microservicesscripts.entity.Script;
import com.task.microservicesscripts.exception.ScriptNotFoundException;
import com.task.microservicesscripts.exception.WrongScriptDataException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ScriptService {
    List<Script> getAll();

    Script getById(int id) throws ScriptNotFoundException;

    Script addScript(String name,
                     String description,
                     MultipartFile command) throws WrongScriptDataException;

    Script updateScript(int id,
                        String newName,
                        String newDescription,
                        MultipartFile newCommand) throws ScriptNotFoundException, IOException, WrongScriptDataException;

    void deleteScript(int id);

    int run(int scriptId, Map<String, String> args);

    String getError(int scriptId);
}
