package com.controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.parser.YahooParserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/")
public class ScheduledTasks {

    private static final String API_KEY = "17e92629a4mshace0dc356abaa16p1d4abejsn90dd108ad1bb";
    private static final String URL = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/auto-complete?lang=en&region=US&query=yahoo";

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private YahooParserImpl yahooParser;

    private String jsonString;

    {
        try {
            jsonString = takeInfo(URL, API_KEY);
            System.out.println(jsonString);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/add")
    public void add() {
        String editedString = jsonString.substring(yahooParser.findStart(jsonString), yahooParser.findFinish(jsonString));
        yahooParser.addToDB(yahooParser.convertToJson(editedString));
    }


    @Scheduled(cron = "${time.to.update}", zone = "${timezone}")
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    private String takeInfo(String URL, String API_KEY) throws UnirestException {
        HttpResponse<String> response = Unirest.get(URL)
                .header("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
                .header("x-rapidapi-key", API_KEY)
                .asString();
        return response.getBody();
    }
}