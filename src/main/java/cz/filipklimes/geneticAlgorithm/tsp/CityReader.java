package cz.filipklimes.geneticAlgorithm.tsp;

import java.io.*;
import java.util.*;

public class CityReader {

    public static final String CSV_SPLITTER = ",";

    public List<City> readCities(String fileName) throws IOException {
        List<City> cities = new ArrayList<>();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Header
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] cityData = line.split(CSV_SPLITTER);
                cities.add(new City(i++, cityData[0], Double.parseDouble(cityData[2]), Double.parseDouble(cityData[3])));
            }
        }

        return cities;
    }

}
