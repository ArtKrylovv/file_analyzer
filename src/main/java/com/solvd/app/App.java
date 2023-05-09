package com.solvd.app;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) throws IOException {
        wordCounter1("src/main/resources/sample.txt");
    }
    // using for each loop to remove punctuation
    public static void wordCounter1(String path) throws  IOException{
        File file = new File(path);
        String text = StringUtils.lowerCase(FileUtils.readFileToString(file, "UTF-8"));
        String[] toRemove = {",", "?", "!", ".", "-", ":", ";", "/"};
        for (String el : toRemove) {
            text = StringUtils.remove(text, el);
        }
        Set<String> uniqueSet = new HashSet<>(Arrays.asList(StringUtils.split(text, " ")));
        FileUtils.write(file, "\n" + Integer.toString(uniqueSet.size()), "UTF-8", true);
    }
    // using for regex to remove punctuation
    public static void wordCounter2(String path) throws IOException{
        File file = new File(path);
        String text = StringUtils.lowerCase(FileUtils.readFileToString(file, "UTF-8"));
        text = text.replaceAll("[\\p{Punct}&&[^']]+", "");
        Set<String> uniqueSet = new HashSet<>(Arrays.asList(StringUtils.split(text, " ")));
        FileUtils.write(file, "\n" + Integer.toString(uniqueSet.size()), "UTF-8", true);
    }
}
