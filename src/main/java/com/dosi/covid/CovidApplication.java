package com.dosi.covid;

import com.dosi.covid.Model.CovidState;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dosi.covid.DAO.CovidDAO;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@EnableSwagger2
public class CovidApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(CovidApplication.class, args);
        CovidDAO.saveCsvFile();
/*        CovidDAO dao = new CovidDAO();
        List<CovidState> covidStateList = dao.loadCovidStateList();
        for (CovidState covidState : covidStateList) {
            System.out.println(covidState.toString());
        }*/
    }

}
