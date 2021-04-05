package com.task.microservicesscripts.exception;

public class FileWorkerException extends Exception {
    public FileWorkerException() {
        super();
    }

    public FileWorkerException(String message) {
        super(message);
    }

    public FileWorkerException(String message, Throwable cause) {
        super(message, cause);
    }
}
