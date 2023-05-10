package com.solvd.app;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



public class App {
    private final static Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        wordCounter2("src/main/resources/sample.txt", "Word count:");
    }

    // using for each loop to remove punctuation
    public static void wordCounter1(String path, String myWord) {
        try {
            App.LOGGER.info("Reading file");
            File file = new File(path);
            String text = StringUtils.lowerCase(FileUtils.readFileToString(file, "UTF-8"));
            String[] toRemove = {",", "?", "!", ".", "-", ":", ";", "/"};
            for (String el : toRemove) {
                text = StringUtils.remove(text, el);
            }
            Set<String> uniqueSet = new HashSet<>(Arrays.asList(StringUtils.split(text, " ")));
            App.LOGGER.info("Creating new file at src/main/resources");
            File newFile = new File ("src/main/resources/output.txt");
            FileUtils.touch(newFile);
            App.LOGGER.info("Writing to new file");
            FileUtils.write(newFile, myWord+" "+uniqueSet.size(), "UTF-8", false);
        } catch (IOException e) {
            App.LOGGER.error(e.getMessage(), e);
        }
    }

    // using for regex to remove punctuation
    public static void wordCounter2(String path, String myWord) {
        try {
            App.LOGGER.info("Reading file");
            File file = new File(path);
            String text = StringUtils.lowerCase(FileUtils.readFileToString(file, "UTF-8"));
            String[] toRemove = {",", "?", "!", ".", "-", ":", ";", "/"};
            text = text.replaceAll("[\\p{Punct}&&[^']]+", "");
            Set<String> uniqueSet = new HashSet<>(Arrays.asList(StringUtils.split(text, " ")));
            App.LOGGER.info("Creating new file at src/main/resources");
            File newFile = new File ("src/main/resources/output.txt");
            FileUtils.touch(newFile);
            App.LOGGER.info("Writing to new file");
            FileUtils.write(newFile, myWord+" "+uniqueSet.size(), "UTF-8", false);
        } catch (IOException e) {
            App.LOGGER.error(e.getMessage(), e);
        }
    }
}


