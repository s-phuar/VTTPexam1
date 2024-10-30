package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;


import vttp.batch5.sdf.task01.models.BikeEntry;


// Use this class as the entry point of your program
//NUS\coding\exam1\task01> javac -d classes --source-path src src/vttp/batch5/sdf/task01/*.java
//NUS\coding\exam1\task01> java -cp classes vttp/batch5/sdf/task01.Main

public class Main {

	public static void main(String[] args) throws IOException {
		Path bikePath = Paths.get("day.csv");
    	File bikeFile = bikePath.toFile();
		FileReader fr = new FileReader(bikeFile);
        BufferedReader br = new BufferedReader(fr);

		//arraylist to hold all 700+ bike entry objects
		ArrayList<BikeEntry> allBikes = new ArrayList<>();

		String line;
		String headerLine = br.readLine();				//capture the header only
		while ((line = br.readLine()) != null){			//read each line of the csv
			String[] lineData = line.split(","); 	//parse all csv line to a string []
			allBikes.add(BikeEntry.toBikeEntry(lineData));	//takes in string array, create bike objects for each line, add bikeentry objects to arraylist
		}
		br.close();


        Comparator<BikeEntry> compare = Comparator.comparingInt(b -> b.getCasual() + b.getRegistered());
        allBikes.sort(compare.reversed());
        // allBikes.forEach(b -> {
        //     System.out.println(b.getCasual() + b.getRegistered());
        // });

		//printing out the top 5 days with most cyclists
		for(int i = 0 ; i < 5; i++){
			// System.out.println(allBikes.get(i).getCasual() + allBikes.get(i).getRegistered());
			String position;
			if(i == 0){
				position = "highest";
			}else if( i == 1){
				position = "second highest";
			}else if( i == 2){
				position = "third highest";
			}else if( i == 3){
				position = "fourth highest";
			}else{
				position = "fifth highest";
			}
			String weather = null;
			if(allBikes.get(i).getWeather() == 1){
				weather = "Clear, Few clouds, Partly cloudy, Partly cloudy";
			}else if(allBikes.get(i).getWeather() == 2){
				weather = "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist";
			}else if(allBikes.get(i).getWeather() == 3){
				weather = "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds";
			}else if(allBikes.get(i).getWeather() == 4){
				weather = "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog";
			};

			String holiday = null;
			if(allBikes.get(i).isHoliday()){
				holiday = "is a holiday";
			}else{
				holiday = "not a holiday";
			}

			System.out.printf("The %s recorded number of cyclists was in %s, on a %s in the month of %s. \n", position, Utilities.toSeason(allBikes.get(i).getSeason()), Utilities.toWeekday(allBikes.get(i).getWeekday()), Utilities.toMonth(allBikes.get(i).getMonth()) );
			System.out.printf("There were a total of %d cyclist. The weather was %s.\n", allBikes.get(i).getCasual()+allBikes.get(i).getRegistered(), weather);
			System.out.printf("%s was %s.\n", Utilities.toWeekday(allBikes.get(i).getWeekday()), holiday);
			System.out.println("");

		}




		
	}



}
