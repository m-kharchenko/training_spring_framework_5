package com.luxoft.springdb.lab1.dao;


import java.util.List;

import com.luxoft.springdb.lab1.model.Country;
import org.springframework.stereotype.Component;

@Component("countryDao")
public class CountryDao {

    public static final String[][] COUNTRY_INIT_DATA = {{"Australia", "AU"},
            {"Canada", "CA"}, {"France", "FR"}, {"Hong Kong", "HK"},
            {"Iceland", "IC"}, {"Japan", "JP"}, {"Nepal", "NP"},
            {"Russian Federation", "RU"}, {"Sweden", "SE"},
            {"Switzerland", "CH"}, {"United Kingdom", "GB"},
            {"United States", "US"}};

    private final CountryRepository countryRepository;


    public CountryDao(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getCountryList() {
        return countryRepository.findAll();
    }

    public List<Country> getCountryListStartWith(String name) {
        return countryRepository.findByNameStartingWith(name);
    }

    public void updateCountryName(String codeName, String newCountryName) {
        Country country = countryRepository.findByCodeName(codeName);
        if (country != null) {
            country.setName(newCountryName);
            countryRepository.save(country);
        }
    }

    public void loadCountries() {
        for (String[] countryData : COUNTRY_INIT_DATA) {
            Country country = new Country(countryData[0], countryData[1]);
            countryRepository.save(country);
        }
    }

    public Country getCountryByCodeName(String codeName) {
        return countryRepository.findByCodeName(codeName);
    }

    public Country getCountryByName(String name)
            throws CountryNotFoundException {
        List<Country> countryList = countryRepository.findByName(name);
        if (countryList.isEmpty()) {
            throw new CountryNotFoundException();
        }
        return countryList.get(0);
    }
}
