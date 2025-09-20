package com.railsoft.utils;

import java.math.BigInteger;

import org.springframework.stereotype.Component;

@Component
public class HexParser {

    public byte[] hexToBytes(String hex){

        hex = hex.replaceAll("^0x", ""); 
        byte[] bytes = new BigInteger(hex, 16).toByteArray();
    
        if (bytes[0] == 0) {
            byte[] tmp = new byte[bytes.length - 1];
            System.arraycopy(bytes, 1, tmp, 0, tmp.length);
            return tmp;
        }
        return bytes;
        

    }
    
}
