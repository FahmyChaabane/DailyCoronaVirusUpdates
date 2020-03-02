package com.chaabane.virus.corona;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaService {

    private final String lien = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
    private static final Logger log = LoggerFactory.getLogger(CoronaApplication.class);

    @Autowired
    private RestTemplate restTemplate;

    //@PostConstruct execute this after the app is built
    public List<LocationStats> fetchCoronaVirusData() throws IOException {
        ArrayList<LocationStats> tempList = new ArrayList<LocationStats>();
        log.info("started here ----------");
        String response = restTemplate.getForObject(
                lien, String.class);
        /*
        List<String> items = Arrays.asList(response.split("\\r?\\n"));
        for (String str : items) {
            log.info(str);
        }*/

        StringReader read = new StringReader(response);
        for (CSVRecord record : CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(read)) {
            LocationStats locationStats = new LocationStats();
            locationStats.setState(record.get("Province/State"));
            locationStats.setLatitude(Double.parseDouble(record.get("Lat")));
            locationStats.setLongtitude(Double.parseDouble(record.get("Long")));
            locationStats.setCountry(record.get("Country/Region"));
            locationStats.setLastTotalCases(Integer.parseInt(record.get(record.size()-1)));
            tempList.add(locationStats);
            log.info(locationStats.toString());
        }
        return tempList;
    }

}
