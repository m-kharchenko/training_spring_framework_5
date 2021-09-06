package com.luxoft.springdb.lab1.dao;

import com.luxoft.springdb.lab1.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country,Integer> {

    List<Country> findAll();

    List<Country> findByNameStartingWith(String name);

    Country findByCodeName(String codeName);

    List<Country> findByName(String name);
}
