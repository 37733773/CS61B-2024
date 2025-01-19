package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.*;


public class HyponymsHandler extends NgordnetQueryHandler {

    hfGraph hfgraph;
    ssGraph ssgraph;
    public HyponymsHandler(String synSetsFile, String hyponymsFile) {
        hfgraph = new hfGraph(hyponymsFile);
        ssgraph = new ssGraph(synSetsFile);

    }

    @Override
    public String handle(NgordnetQuery q) {
        ArrayList<String> returnString = new ArrayList<>();
        HashMap newMap = new HashMap();
        for (String word : q.words()) {
           for (int num : ssgraph.adj(word)) {
               if (newMap.containsKey(num)) {
                   newMap.put(num, (Integer)newMap.get(num)+1);
               }
               else {
                   newMap.put(num, 1);
               }
               for (int num2 : hfgraph.getPathSet(num)) {
                   if (newMap.containsKey(num2)) {
                       newMap.put(num2, (Integer)newMap.get(num2)+1);
                   }
                   else {
                       newMap.put(num2, 1);
                   }
               }
           }
        }
        for (Object o : newMap.keySet()) {
            if (q.words().size() >= 2 && (Integer) newMap.get(o) > 1 || q.words().size() == 1) {
                for (String rs : ssgraph.GetString((Integer) o)) {
                    returnString.add(rs);
                }
            }
        }
        Collections.sort(returnString);
        return returnString.toString();
    }




}
