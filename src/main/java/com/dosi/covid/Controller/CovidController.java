package com.dosi.covid.Controller;
import com.dosi.covid.Model.CovidState;
import com.dosi.covid.Service.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CovidController {
	@Autowired
	CovidService covidService;

	@GetMapping("/test")
	public String hello()
	{
		return "Hello mouad";
	}

	@GetMapping("/oneContryData")
	public List<CovidState> oneContryData(@RequestParam String countryName) {
		return covidService.oneContryData(countryName);
	}

	@GetMapping("/oneCountryDataWithDate")
	public CovidState  oneCountryDataWithDate(@RequestParam String countryName, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
		return covidService.oneCountryDataWithDate(countryName,date);

	}

	@GetMapping("/todayCountryData")
	public CovidState todayCountryData(@RequestParam String countryName) {
		System.out.println(countryName);
		return covidService.todayCountryData(countryName);
	}


}