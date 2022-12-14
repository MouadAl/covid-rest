package com.dosi.covid.DAO;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dosi.covid.Model.CovidState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


public class CovidDAO {
    private static String path = "src/main/resources/covid-data.csv";

    public void saveCsvFile() throws IOException {
        File file = new File(path);
        InputStream in = new URL("https://coronavirus.politologue.com/data/coronavirus/coronacsv.aspx?format=csv&t=pays").openStream();
        Files.copy(in, Paths.get(file.getPath()), StandardCopyOption.REPLACE_EXISTING);
    }

    public List<CovidState> loadCovidStateList() throws IOException {
        saveCsvFile();
        boolean headerFlag = true;
        List<CovidState> covidStateList = new ArrayList<CovidState>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.charAt(0) == '#' || line.charAt(0) == '-') continue;
                if (headerFlag) {
                    headerFlag = false;
                    continue;
                }
                String[] attributes = line.split(";");
                CovidState covidState = new CovidState();
                covidState.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(attributes[0]));
                covidState.setPays(attributes[1]);
                covidState.setInfections(Integer.parseInt(attributes[2]));
                covidState.setDeces(Integer.parseInt(attributes[3]));
                covidState.setGuerisons(Integer.parseInt(attributes[4]));
                covidState.setTauxDeces(Float.parseFloat(attributes[5]));
                covidState.setTauxGuerison(Float.parseFloat(attributes[6]));
                covidState.setTauxInfection(Float.parseFloat(attributes[7]));
                covidStateList.add(covidState);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return covidStateList;
    }


}
