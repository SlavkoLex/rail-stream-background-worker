package com.railsoft.exceptions;

public class SQLQueryIsEmptyException extends RuntimeException{

    public SQLQueryIsEmptyException(String message){
        super(message);
    }
    
}
