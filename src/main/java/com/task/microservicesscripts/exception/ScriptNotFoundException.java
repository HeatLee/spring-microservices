package com.task.microservicesscripts.exception;

public class ScriptNotFoundException extends Exception {
    public ScriptNotFoundException(int id) {
        super("Could not find script " + id);
    }
}
