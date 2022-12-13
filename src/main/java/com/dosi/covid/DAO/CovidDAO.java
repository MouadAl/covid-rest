package com.dosi.covid.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


public class CovidDAO {
	
	@Autowired
	ResourceLoader resourceLoader;

	
	public void loadCovidData() {
		//Resource resource = resourceLoader.getResource("classpath:coronavirus.politologue.com-pays-2022-12-13.csv");
		File file = new File("src/main/resources/coronavirus.politologue.com-pays-2022-12-13.csv");
		try(FileInputStream fis = new FileInputStream(file))
		{
			System.out.println(new String(fis.readAllBytes()));
			
		}
		catch(IOException e)
		{
			
		}
			
		
	}
	
	/*public static void main(String args[]) {
		CovidDAO dao = new CovidDAO();
		dao.loadCovidData();
		
		
		
		
	}*/
	
	
	
	
	
	
	

}
