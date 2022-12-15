package com.dosi.covid.Service;

import com.dosi.covid.DAO.CovidDAO;
import com.dosi.covid.Model.CovidState;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CovidService implements CovidServiceInter {

    @Autowired
    private CovidDAO covidDAO;
    private List<CovidState> covidStateList;
    private List<CovidState> covidStateFiltered;

    @Override
    public List<CovidState> oneContryData(String countryName) {
        covidStateList=covidDAO.loadCovidStateList();
        covidStateFiltered = new ArrayList<CovidState>();
        for (CovidState covidState : covidStateList )
            if (covidState.getPays().equalsIgnoreCase(countryName))
                covidStateFiltered.add(covidState);

        return covidStateFiltered;
    }

    @Override
    public CovidState oneCountryDataWithDate(String countryName, Date date) {
        covidStateList=covidDAO.loadCovidStateList();
        covidStateFiltered = new ArrayList<CovidState>();
        for (CovidState covidState : covidStateList )
            if (covidState.getPays().equalsIgnoreCase(countryName) && covidState.getDate().equals(date))
                return covidState;
        return new CovidState();


    }

    @Override
    public CovidState todayCountryData(String countryName) {
        covidStateList=covidDAO.loadCovidStateList();
        covidStateFiltered = new ArrayList<CovidState>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date(System.currentTimeMillis());

        for (CovidState covidState : covidStateList )
            if (covidState.getPays().equalsIgnoreCase(countryName) && simpleDateFormat.format(date).equals(simpleDateFormat.format(covidState.getDate())))
                return covidState;
        return new CovidState();
    }
}
