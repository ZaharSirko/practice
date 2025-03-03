package com.epam.rd.autotasks.suffixing;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.logging.Logger;

public class SuffixingApp {

    private static final Logger LOGGER = Logger.getLogger(SuffixingApp.class.getName());

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            LOGGER.severe("Usage: java -jar suffixing.jar <config-file-path>");
            return;
        }

        String configFilePath = args[0];
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream(configFilePath)) {
            properties.load(input);
        } catch (IOException e) {
            LOGGER.severe("Failed to load config file: " + e.getMessage());
            return;
        }

        String mode = properties.getProperty("mode", "").toUpperCase();
        String suffix = properties.getProperty("suffix");
        String files = properties.getProperty("files");


        if (!mode.equals("copy".toUpperCase()) && !mode.equals("move".toUpperCase())) {
            LOGGER.severe("Mode is not recognized: " + mode);
            return;
        }

        if (suffix == null || suffix.isEmpty()) {
            LOGGER.severe("No suffix is configured");
            return;
        }
        if (files == null || files.isEmpty()) {
            LOGGER.warning("No files are configured to be copied/moved");
            return;
        }

        String[] filePaths = files.split(":");
        for (String filePath : filePaths) {
            processFile(filePath, suffix, mode);
        }

    }

    private static void processFile(String filePath, String suffix, String mode) {
        File sourceFile = new File(filePath);
        if (!sourceFile.exists()) {
            LOGGER.severe("No such file: " + filePath);
            return;
        }

        String parentDir = sourceFile.getParent();
        String fileName = sourceFile.getName();
        int dotIndex = fileName.lastIndexOf('.');
        String baseName = dotIndex != -1 ? fileName.substring(0, dotIndex) : fileName;
        String extension = dotIndex != -1 ? fileName.substring(dotIndex) : "";

        File targetFile = new File(parentDir, baseName + suffix + extension);

        try {
            if (mode.equals("COPY")) {
                Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                LOGGER.info(sourceFile.getPath().replace("\\", "/") + " -> " + targetFile.getPath().replace("\\", "/"));
            } else if (mode.equals("MOVE")) {
                Files.move(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                LOGGER.info(sourceFile.getPath().replace("\\", "/") + " => " + targetFile.getPath().replace("\\", "/"));
            }
        }
        catch (Exception e) {
            LOGGER.severe("Failed to process file: " + filePath.replace("\\", "/") + " - " + e.getMessage());
        }
    }
}
