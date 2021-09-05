package com.luxoft.springdb.lab1.dao;

import com.luxoft.springdb.lab1.model.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country,Integer> {

    List<Country> findAll();

    List<Country> findByNameStartingWith(String name);

    Country findByCodeName(String codeName);

    List<Country> findByName(String name);
}
