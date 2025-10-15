package com.railsoft.exceptions;

import java.io.IOException;

public class FileIsEmptyException extends IOException{
    
    public FileIsEmptyException(String message){
        super(message);
    }
}
