package ru.otus.java.basic.homeworks;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Console {

    static boolean continueAction = true;

    static String currentDirectory = "src/main/java";

    static List<String> lastFolders = new ArrayList<>();

    static String currentFile = null;


    public static List<String> getCurrentFiles(String currentDirectory) {
        System.out.println();
        System.out.println("В текущей папке отображаются следующие файлы: ");
        String[] listFiles = new File(currentDirectory).list();
        List<String> stringList = List.of(listFiles);
        for (String element : stringList){
            System.out.println(element);
        }
        if (stringList.size()==0){
            System.out.println("Файлы или папки отсутствуют");
        }
        return stringList;
    }

    public static void openFileOrDirectory(String fileName) {
        File file = new File(currentDirectory + "/" + fileName);
        if (file.isFile()) {
            currentFile = file.getName();
            try (InputStreamReader in = new InputStreamReader(new BufferedInputStream(new FileInputStream(file)))) {
                int n = in.read();
                while (n != -1) {
                    System.out.print((char) n);
                    n = in.read();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        } else if (file.isDirectory()) {
            lastFolders.add(fileName);
            currentDirectory = currentDirectory + "/" + fileName;
            getCurrentFiles(currentDirectory);
        }
    }

    public static void goToBackDirectory(){
        currentDirectory = currentDirectory.substring(0, currentDirectory.length() - lastFolders.get(lastFolders.size()-1).length() - 1);
        lastFolders.remove(lastFolders.size()-1);
        getCurrentFiles(currentDirectory);
    }

    public static void goToBackFromFile(){
        currentFile = null;
        getCurrentFiles(currentDirectory);
    }


    public static void writeStringToFile(String text, boolean isAdded) {
        File file = new File(currentDirectory + "/" + currentFile);
        try(OutputStreamWriter out = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(file, isAdded)))){
            byte[] buffer = text.getBytes(StandardCharsets.UTF_8);
            for(int i=0; i<buffer.length; i++){
                out.write(buffer[i]);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("В текущем файле отобразилось следующее: ");
        openFileOrDirectory(currentFile);
    }
}
