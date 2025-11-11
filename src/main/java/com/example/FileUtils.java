package com.example;

import java.io.IOException;
import java.nio.file.*;

public class FileUtils {

    public static void saveBytes(String path, byte[] data) throws IOException {
        Path p = Paths.get(path);
        Files.createDirectories(p.getParent());
        Files.write(p, data);
        System.out.println("Saved file: " + p.toAbsolutePath());
    }
}



