package com.railsoft.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Use when reading the "application-coap.properties" file  with a full server configuration
public class CoAPServerConfFileSearcher {
    
    private File serverConfFile;

    public File getCoAPServerConfFile(){
        return serverConfFile;
    }

    public void searchCoAPServerConfFile(String pathToCoAPServerConfFile, String nameCoAPServerConfFile) throws IOException{

        Path startDir = Paths.get(pathToCoAPServerConfFile);

        try (Stream<Path> stream = Files.walk(startDir)) {
            List<Path> filesPath = stream
                .filter(Files::isRegularFile)
                .filter(path -> path.getFileName().toString().equals(nameCoAPServerConfFile))
                .collect(Collectors.toList());

            for(Path filePath: filesPath){
                serverConfFile = filePath.toFile();
                break;
            }

        }
    }


}
