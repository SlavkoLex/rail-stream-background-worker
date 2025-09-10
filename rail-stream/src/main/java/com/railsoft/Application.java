package com.railsoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.railsoft.repository.DeviceRepository;
import com.railsoft.repository.RowDataDeviceRepository;


@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    private ApplicationContext applicationContext; 

    @Override
    public void run(String... args){

        // Test run

        DeviceRepository tdsObj = applicationContext.getBean(DeviceRepository.class);
        RowDataDeviceRepository tdsObj2 = applicationContext.getBean(RowDataDeviceRepository.class);
        
        System.out.println(tdsObj.findDeviceByID(1));
        System.out.println(tdsObj2.findRowDeviceDataByDeviceId(1));

    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);  
    }


}
