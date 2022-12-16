package com.dosi.covid;

import com.dosi.covid.DAO.CovidDAO;
import com.dosi.covid.Model.CovidState;
import com.dosi.covid.Service.CovidService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class CovidApplicationTests {


	@Autowired
	private CovidDAO dao;

	@Test
	public void loadData() throws ParseException, IOException {
		List<CovidState> covidList = new ArrayList<>();
		CovidState covidState = new CovidState(new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH).parse("Tue Dec 13 00:00:00 CET 2022"),"Albanie",333567,3594,0,(float)1.08,(float)0.0,(float)98.92);
		covidList.add(covidState);

		//assertEquals(dao.loadCovidStateList().get(0),covidList.get(0));
		assertTrue(dao.loadCovidStateList().contains(covidState));
		//CovidState(date=Tue Dec 13 00:00:00 CET 2022, Pays=Albanie, Infections=333567, Deces=3594, Guerisons=0, TauxDeces=1.08, TauxGuerison=0.0, TauxInfection=98.92)
	}
	/*String date="Sat Jun 01 12:53:10 IST 2013";
	SimpleDateFormat sdf=new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");*/



	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CovidService covidService;

	@Test
	public void shouldPassIfStringMatches() throws Exception {
		assertThat(restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("Hello MOuad");
	}
	@Test
	public void oneCountryData() throws ParseException {
		List<CovidState> covidCountryData =covidService.oneCountryData("France");
		CovidState otherCounrtyData = new CovidState(new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH).parse("Tue Dec 13 00:00:00 CET 2022"),"Albanie",333567,3594,0,(float)1.08,(float)0.0,(float)98.92);
		assertThat(covidCountryData)
				.isNotNull()
				.doesNotContain(otherCounrtyData);

	}
	@Test
	public void getCountryDataWithDate() throws ParseException {
		CovidState  covidCountryDataWithDate =covidService.oneCountryDataWithDate("France",new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-13") );
		CovidState CounrtyDataWithDate = new CovidState(new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH).parse("Tue Dec 13 00:00:00 CET 2022"),"Albanie",333567,3594,0,(float)1.05,(float)0.0,(float)98.92);
		assertThat(covidCountryDataWithDate)
				.isNotNull()
				.isEqualTo(CounrtyDataWithDate);

	}
	@Test
	public void getTodayCountryData() throws ParseException {
		CovidState todayCountryData = covidService.todayCountryData("France");
			CovidState todayCountryDataTest = new CovidState(new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH).parse("Tue Dec 13 00:00:00 CET 2022"),"Albanie",333567,3594,0,(float)1.05,(float)0.0,(float)98.92);

		assertThat(todayCountryData)
				.isNotNull()
				.isEqualTo(todayCountryDataTest);

	}




	@Test
	void contextLoads() {





	}

}
