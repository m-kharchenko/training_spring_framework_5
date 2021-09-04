package com.luxoft.springdb.lab1;

import com.luxoft.springdb.lab1.dao.CountryDao;
import com.luxoft.springdb.lab1.dao.CountryNotFoundException;
import com.luxoft.springdb.lab1.model.Country;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        CountryDao countryDao = (CountryDao) run.getBean("countryDao");
        countryDao.loadCountries();
        System.out.println("list_size = " + countryDao.getCountryList().size());
        System.out.println("country = " + getCountryByName("Russian Federation",countryDao));
        System.out.println("country = " + getCountryByName("Russian",countryDao));
    }

    private static Country getCountryByName(String countryName, CountryDao countryDao) {
        try {
            return countryDao.getCountryByName(countryName);
        } catch (CountryNotFoundException e) {
            e.printStackTrace();
            System.out.println("Country with name = '"+countryName+"' not found");
            return null;
        }
    }


}
