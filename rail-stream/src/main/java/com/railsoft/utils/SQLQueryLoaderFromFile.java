package com.railsoft.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.railsoft.exceptions.FileIsEmptyException;
import com.railsoft.exceptions.FilePathDoesNotExistException;


public class SQLQueryLoaderFromFile implements SQLQueryLoader{

    
    private Map<String, Optional<String>> sqlQueries = new HashMap<>();


    private Path searchSQLFile(String directory, String fileName) throws FileNotFoundException, FilePathDoesNotExistException{

        if(fileName == null || fileName.trim().isEmpty()){
            throw new FilePathDoesNotExistException("Incorrect file name is specified!");
        }

        if(directory == null || directory.trim().isEmpty()){
            throw new FilePathDoesNotExistException("Incorrect directory is specified!");
        }

        Path filePath = Paths.get(directory, fileName);
        
        if (!Files.exists(filePath)) {
            System.out.println("--------Path to SQL: \n" + filePath + "-----------\n");
            throw new FileNotFoundException("File " + filePath + " was not found");
        }

        return filePath;
    }

    private String parseSQLStatmentFromFile(Path pathToSQLFile) throws FileIsEmptyException, FilePathDoesNotExistException, IOException{

        if(pathToSQLFile == null || pathToSQLFile.toString().isEmpty() || pathToSQLFile.getNameCount() == 0){
             throw new FilePathDoesNotExistException("The specified path to file does not exist!");
        }

        String rowDataFromSQLFile = Files.readString(pathToSQLFile)
            .replaceAll("--.*$", "")  
            .replaceAll("/\\*.*?\\*/", "") 
            .trim();

        if (rowDataFromSQLFile.isEmpty()) {
            throw new FileIsEmptyException("The specified SQL file file is empty or conteins or contains only ignored characters!");
        }

        return rowDataFromSQLFile;
    }

    @Override
    public void loadingSQLQueries(Map<String, Optional<String>> sqlQueryProperties){

        for(String key : sqlQueryProperties.keySet()){

            Optional<String> value = sqlQueryProperties.get(key);

            if(value.isPresent()){

                String[] temp = value.get().split("/");

                String fileName = temp[temp.length - 1].trim();
                String pathToFile = String.join("/", Arrays.copyOf(temp, temp.length - 1)).trim();

                try{
                    sqlQueries.put(key, Optional.of(parseSQLStatmentFromFile(searchSQLFile(pathToFile, fileName))));
                }catch(Exception e){
                    System.err.println(e);
                    sqlQueries.put(key, Optional.empty());
                }
            }
        }

    }

    public Optional<String> fetchSQLQuery(String quryKey){
        return sqlQueries.get(quryKey);
    }

    public Set<String> getSQLQueryKeys(){
        return sqlQueries.keySet();
    }


    @Override
    public String toString(){
        return "";
    }

}
