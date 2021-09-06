package com.luxoft.springdb.lab1.dao;



import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(CountryDao countryDao) {

        return args -> {
            countryDao.loadCountries();
        };
    }

}
