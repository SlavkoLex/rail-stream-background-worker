package com.railsoft;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// TEST
@Component
public class TestDataScope {


    private final JdbcTemplate jdbcTemplate;

    public TestDataScope(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<String> createSqlQueryListFromFile(String pathToFile)throws IOException{

        List<String> queries = new ArrayList<>();
    
        String str = new String(Files.readAllBytes(Paths.get(pathToFile)));
        for(String partStr : str.split(";")){
            queries.add(partStr);
        }

        System.out.println(queries);

        return queries;
    }

    public void sendSqlQuery(String sqlQuery){

    }

    @Transactional
    public void createMultipleables(List<String> queryList) throws Exception{
        for(String query : queryList){
            jdbcTemplate.execute(query);
        }
    }

}
