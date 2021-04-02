package com.task.microservicesscripts.service;

import com.task.microservicesscripts.entity.Script;
import com.task.microservicesscripts.exception.ScriptNotFoundException;
import com.task.microservicesscripts.exception.WrongScriptDataException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ScriptService {
    List<Script> getAll();

    Script getById(int id) throws ScriptNotFoundException;

    Script addScript(String name,
                     String description,
                     MultipartFile command) throws WrongScriptDataException;

    Script updateScript(int id,
                        String newName,
                        String newDescription,
                        MultipartFile newCommand) throws ScriptNotFoundException;

    void deleteScript(int id);

    int run(int scriptId);
}
