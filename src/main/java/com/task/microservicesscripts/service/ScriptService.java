package com.task.microservicesscripts.service;

import com.task.microservicesscripts.entity.Script;
import com.task.microservicesscripts.exception.ScriptNotFoundException;
import com.task.microservicesscripts.exception.WrongScriptDataException;

import java.util.List;

public interface ScriptService {
    List<Script> getAll();

    Script getById(int id) throws ScriptNotFoundException;

    Script addScript(Script script) throws WrongScriptDataException;

    Script updateScript(int id, Script script) throws ScriptNotFoundException;

    void deleteScript(int id);
}
