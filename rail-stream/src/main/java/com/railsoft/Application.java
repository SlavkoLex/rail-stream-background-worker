package com.railsoft;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    private ApplicationContext applicationContext; // Ввод объекта контекста для доступа к объектам контролируемым Spring


    @Override
    public void run(String... args){


        TestDataScope tdsObj = applicationContext.getBean(TestDataScope.class);

        try{
            List<String> queryList = tdsObj.createSqlQueryListFromFile("src/test/resources/sql/test-data.sql");
            tdsObj.createMultipleables(queryList);
        }catch(Exception e){
            System.err.println("Exception during creating tables" + e);
        }

    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);  
    }


}
