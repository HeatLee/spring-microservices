package com.task.microservicesscripts.util;

import com.task.microservicesscripts.exception.FileWorkerException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.task.microservicesscripts.util.FileExtension.PY;
import static com.task.microservicesscripts.util.FileExtension.SH;

public class FileWorker {
    private static final String FILE_PATH_WITHOUT_EXTENSION = "temp.";
    private static final String OUTPUT_FILE_PATH = "out.txt";
    private static final String ERR_FILE_PATH = "err.txt";

    private static final ProcessBuilder processBuilder = new ProcessBuilder();
    private static File out = new File(OUTPUT_FILE_PATH);
    private static File err = new File(ERR_FILE_PATH);

    private static boolean isWindows = false;
    private static boolean isLinux = false;


    static {
        processBuilder.redirectError(err);
        processBuilder.redirectOutput(out);
        detectOS();
    }

    public static File getOut() {
        return out;
    }

    public static File getErr() {
        return err;
    }

    public static File makeFileFromByteArray(byte[] array, String fileExtension) throws FileWorkerException {
        try {
            File file = new File(FILE_PATH_WITHOUT_EXTENSION + fileExtension);
            FileUtils.writeByteArrayToFile(file, array);
            return file;
        } catch (IOException e) {
            throw new FileWorkerException(e.getMessage());
        }
    }

    public static int run(File file,
                          String extension,
                          Map<String, List<String>> args)
            throws FileWorkerException {
        int exitCode = 1;
        List<String> command = buildCommand(file.getAbsolutePath(), args);
        try {
            if (isWindows) {
                processBuilder.command(command);
            } else if (isLinux) {
                switch (FileExtension.getByExtension(extension)) {
                    case PY:
                        command.set(0, PY.getRun());
                        processBuilder.command(command);
                        break;
                    default:
                        command.set(0, SH.getRun());
                        processBuilder.command(command);
                        break;
                }
            } else throw new FileWorkerException("Unsupported OS");

            Process process = processBuilder.start();
            exitCode = process.waitFor();
            return exitCode;
        } catch (IOException | InterruptedException e) {
            throw new FileWorkerException("Failed with exit code " + exitCode);
        }
    }

    public static void cleanOutputAndError() {
        out = new File(OUTPUT_FILE_PATH);
        err = new File(ERR_FILE_PATH);
    }

    private static void detectOS() {
        String os = System.getProperty("os.name")
                .toLowerCase(Locale.ROOT);
        if (os.startsWith("windows")) {
            isWindows = true;
        } else if (os.startsWith("linux")) {
            isLinux = true;
        }
    }

    private static List<String> buildCommand(String filePath,
                                             Map<String, List<String>> args) {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add(filePath);
        addArgs(list, args);
        return list;
    }

    private static void addArgs(List<String> out,
                                Map<String, List<String>> args) {
        args.keySet().forEach(s -> {
                    if (s.length() > 0) {
                        out.add(s);
                    }
                    out.addAll(args.get(s));
                });
    }
}
