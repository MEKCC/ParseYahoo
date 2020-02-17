package com.parser;

import com.model.Yahoo;

import java.util.List;

public interface IParse {

    int findStart(String s);

    int findFinish(String s);

    List<Yahoo> convertToJson(String clearString);

    void addToDB(List<Yahoo> list);
}
