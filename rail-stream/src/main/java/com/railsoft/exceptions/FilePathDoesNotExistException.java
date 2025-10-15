package com.railsoft.exceptions;

import java.io.IOException;


public class FilePathDoesNotExistException extends IOException {

    public FilePathDoesNotExistException(String message){
        super(message);
    }
    
}
