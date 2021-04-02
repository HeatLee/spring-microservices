package com.task.microservicesscripts.service.impl;

import com.task.microservicesscripts.entity.Script;
import com.task.microservicesscripts.exception.ScriptNotFoundException;
import com.task.microservicesscripts.exception.WrongScriptDataException;
import com.task.microservicesscripts.repository.ScriptRepository;
import com.task.microservicesscripts.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

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
    public Script addScript(String name,
                            String description,
                            MultipartFile command) throws WrongScriptDataException{
        try {
            return scriptRepository.save(buildScript(name, description, command));
        } catch (IOException e) {
            throw new WrongScriptDataException();
        }
    }

    @Override
    public Script updateScript(int id,
                               String newName,
                               String newDescription,
                               MultipartFile file)
            throws ScriptNotFoundException, IOException, WrongScriptDataException {
        Script oldScript = scriptRepository.getOne(id);
        oldScript.setName(newName != null && newName.length() > 0 ?
                newName : oldScript.getName());
        oldScript.setDescription(newDescription != null && newDescription.length() > 0 ?
                newDescription : oldScript.getDescription());
        oldScript.setFilenameExtension(getFilenameExtension(file));
        oldScript.setCommand(file.getBytes());
        oldScript.setModifiedAt(Calendar.getInstance());
        return scriptRepository.save(oldScript);
    }

    @Override
    public void deleteScript(int id) {
        scriptRepository.deleteById(id);
    }

    @Override
    public int run(int scriptId) {
        //todo  runService
        return 0;
    }

    private Script buildScript(String name,
                               String description,
                               MultipartFile file) throws IOException, WrongScriptDataException {
        Script script = new Script();
        script.setName(name);
        script.setDescription(description);
        script.setCommand(file.getBytes());


        script.setFilenameExtension(getFilenameExtension(file));
        script.setCreatedAt(Calendar.getInstance());
        script.setModifiedAt(Calendar.getInstance());
        return script;
    }

    private String getFilenameExtension(MultipartFile file) throws WrongScriptDataException {
        String fileName = file.getOriginalFilename();
        String fileExtension = Objects.requireNonNull(fileName).substring(
                fileName.indexOf(".")+1, fileName.length());
        if (fileExtension.length() == 0) {
            throw new WrongScriptDataException();
        }
        return fileExtension;
    }
}
