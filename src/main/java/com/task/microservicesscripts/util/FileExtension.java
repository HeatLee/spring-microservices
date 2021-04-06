package com.task.microservicesscripts.util;

import java.util.Arrays;

public enum FileExtension {
    PY("py", "python3"),
    SH("sh", "sh"),
    EXE("exe", ""),
    BAT("bat", ""),
    UNSUPPORTED("Unsupported extension", "")
    ;
    private final String  extension;
    private final String runCommand;

    FileExtension(String extension, String run) {
        this.extension = extension;
        this.runCommand = run;
    }

    public String getExtension() {
        return extension;
    }

    public String getRun() {
        return runCommand;
    }

    public static FileExtension getByExtension(String extension) {
        return Arrays.stream(FileExtension.values())
                .filter(fileExtension -> fileExtension.getExtension().equals(extension))
                .findFirst()
                .orElse(UNSUPPORTED);
    }
}
