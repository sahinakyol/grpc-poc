package com.countryservice.dto;


public class CountryDto {

    private final Integer id;
    private final String name;
    private final Integer population;

    public CountryDto(Integer id, String name, Integer population) {
        this.id = id;
        this.name = name;
        this.population = population;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPopulation() {
        return population;
    }
}
