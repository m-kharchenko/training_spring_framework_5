package com.luxoft.springdb.lab1.controller;

import com.luxoft.springdb.lab1.dao.CountryDao;
import com.luxoft.springdb.lab1.dao.CountryNotFoundException;
import com.luxoft.springdb.lab1.model.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class CountryController {

    private final CountryDao countryDao;

    public CountryController(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @GetMapping("/countries")
    List<Country> getAllCountries() {
        return countryDao.getCountryList();
    }

    @GetMapping("/countries/{id}")
    Country getCountryById(@PathVariable Integer id) throws CountryNotFoundException {
        return countryDao.getCountryById(id);
    }

    @PostMapping("/countries")
    ResponseEntity<Country> createCountry(@RequestBody Country country) {
        Country countrySave = countryDao.saveCountry(country);
        return ResponseEntity.ok(countrySave);
    }

    @PutMapping("/countries/countryCode={countryCode}/newCountryName={newCountryName}")
    ResponseEntity<String> updateCountryNameByCode(@PathVariable String countryCode,
                                                   @PathVariable String newCountryName)
            throws CountryNotFoundException {
        countryDao.updateCountryName(countryCode, newCountryName);
        return ResponseEntity.ok("Country with countryCode [" + countryCode +
                "] successfully changed name to " + newCountryName);
    }

    @PutMapping("/countries")
    ResponseEntity<Country> updateCountry(@RequestBody Country country) throws CountryNotFoundException {
        Country countryInDb = countryDao.loadCountryFromDbByAnyPojoField(country);
        countryInDb.setName(country.getName());
        countryInDb.setCodeName(country.getCodeName());
        Country countrySave = countryDao.saveCountry(countryInDb);
        return ResponseEntity.ok(countrySave);
    }

    @DeleteMapping("/countries/{id}")
    ResponseEntity<String> deleteCountry(@PathVariable Integer id) throws CountryNotFoundException {
        Country country = countryDao.getCountryById(id);
        countryDao.deleteCountry(country);
        return ResponseEntity.ok("Country with name [" + country.getName() + "] successfully deleted");
    }

    @DeleteMapping("/countries")
    ResponseEntity<String> deleteCountryByAnyPojoField(@RequestBody Country country) throws CountryNotFoundException {
        Country countryInDb = countryDao.loadCountryFromDbByAnyPojoField(country);
        countryDao.deleteCountry(countryInDb);
        return ResponseEntity.ok("Country with name [" + countryInDb.getName() + "] successfully deleted");
    }

    @ExceptionHandler(value = {CountryNotFoundException.class})
    public ResponseEntity handleCountryNotFoundException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Country Not Found");
    }


}
