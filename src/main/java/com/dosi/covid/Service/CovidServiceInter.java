package com.dosi.covid.Service;

import com.dosi.covid.Model.CovidState;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface CovidServiceInter {
    public List<CovidState> oneContryData(String countryName);
    public CovidState oneCountryDataWithDate(String countryName, Date date);
    public CovidState todayCountryData(String countryName);

}
