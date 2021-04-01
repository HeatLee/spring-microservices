package com.task.microservicesscripts.exception;

public class WrongScriptDataException extends Exception {

    public WrongScriptDataException() {
        super("Bad data");
    }
}
