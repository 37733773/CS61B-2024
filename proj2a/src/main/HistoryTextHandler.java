package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    NGramMap MP;
    public HistoryTextHandler(NGramMap map) {
         MP = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        String response = "";

    for (String word : words) {
        response += word + ":" + " "  + MP.weightHistory(word, startYear, endYear)  + "\n";
    }
    return response;
}
}
