package com.task.microservicesscripts.service.impl;

import com.task.microservicesscripts.entity.Script;
import com.task.microservicesscripts.exception.ScriptNotFoundException;
import com.task.microservicesscripts.exception.WrongScriptDataException;
import com.task.microservicesscripts.repository.ScriptRepository;
import com.task.microservicesscripts.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class ScriptServiceImpl implements ScriptService {

    private final ScriptRepository scriptRepository;

    @Autowired
    ScriptServiceImpl(ScriptRepository scriptRepository) {
        this.scriptRepository = scriptRepository;
    }

    @Override
    public List<Script> getAll() {
        return scriptRepository.findAll();
    }

    @Override
    public Script getById(int id) throws ScriptNotFoundException{
        return scriptRepository.findById(id)
                .orElseThrow(() -> new ScriptNotFoundException(id));
    }

    @Override
    public Script addScript(Script script) throws WrongScriptDataException{
        if (script.getCommand().length == 0) {
            throw new WrongScriptDataException();
        }
        script.setCreatedAt(Calendar.getInstance());
        script.setModifiedAt(Calendar.getInstance());
        return scriptRepository.save(script);
    }

    @Override
    public Script updateScript(int id, Script newScript) throws ScriptNotFoundException{
        return scriptRepository.findById(id)
                .map(script -> {
                    script.setName(newScript.getName() != null ? newScript.getName():script.getName());
                    script.setDescription(newScript.getDescription());
                    script.setCommand(newScript.getCommand());
                    script.setCreatedAt(newScript.getCreatedAt());
                    script.setModifiedAt(newScript.getModifiedAt());
                    script.setFilenameExtension(newScript.getFilenameExtension());
                    return scriptRepository.save(script);
                })
                .orElseThrow(() -> new ScriptNotFoundException(id));
    }

    @Override
    public void deleteScript(int id) {

    }
}
