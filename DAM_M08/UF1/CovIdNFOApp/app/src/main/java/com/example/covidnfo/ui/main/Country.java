package com.example.covidnfo.ui.main;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/4-carregar-dades-internet/1-crear-el-pojo/
 * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/7-detall/1-preparar-pojo/
 * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/9-base-de-dades/2-preparar-les-entitats/
 */
/*
@Entity*/
//public class Country implements Serializable {

/*    @PrimaryKey(autoGenerate = true)
    private int id;*/
@Entity
public class Country implements Serializable {
    private String flag,country,cases,todayCases,deaths,todayDeaths,recovered,active,critical, todayRecovered, tests, population;
    @PrimaryKey(autoGenerate = true)
    private int id;
    public Country(String flag, String country, String cases, String todayCases, String deaths, String todayDeaths, String recovered, String active, String critical, String todayRecovered, String tests, String population) {
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
        this.todayRecovered = todayRecovered;
        this.tests = tests;
        this.population = population;
    }

    public Country() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Country{" +
                "flag='" + flag + '\'' +
                ", country='" + country + '\'' +
                ", cases='" + cases + '\'' +
                ", todayCases='" + todayCases + '\'' +
                ", deaths='" + deaths + '\'' +
                ", todayDeaths='" + todayDeaths + '\'' +
                ", recovered='" + recovered + '\'' +
                ", active='" + active + '\'' +
                ", critical='" + critical + '\'' +
                ", todayRecovered='" + todayRecovered + '\'' +
                ", tests='" + tests + '\'' +
                ", population='" + population + '\'' +
                '}';
    }
}
