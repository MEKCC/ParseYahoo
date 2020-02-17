package com.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.model.Yahoo;
import com.repo.YahooRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class YahooParserImpl implements IParse {

    @Autowired
    private YahooRepo yahooRepo;

    @Override
    public int findStart(String s) {
        return s.indexOf("[");
    }

    @Override
    public int findFinish(String s) {
        return s.indexOf("]") + 1;
    }

    @Override
    public List<Yahoo> convertToJson(String clearString) {
        Type foundYahooType = new TypeToken<ArrayList<Yahoo>>() {
        }.getType();
        return new Gson().fromJson(clearString, foundYahooType);
    }

    @Override
    public void addToDB(List<Yahoo> list) {
        list.forEach(yahoo -> {
            Yahoo emp = new Yahoo();
            emp.setName(yahoo.getName());
            emp.setExch(yahoo.getExch());
            emp.setExchDisp(yahoo.getExchDisp());
            emp.setSymbol(yahoo.getSymbol());
            emp.setType(yahoo.getType());
            emp.setTypeDisp(yahoo.getTypeDisp());
            yahooRepo.save(emp);
        });
    }
}
