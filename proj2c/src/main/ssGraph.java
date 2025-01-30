package main;

import edu.princeton.cs.algs4.In;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;

public class ssGraph {
    In in;
    HashMap<String, ArrayList<Integer>> adjList = new HashMap<String, ArrayList<Integer>>();
    HashMap<Integer, ArrayList<String>> adjList2 = new HashMap<>();
    public ssGraph(String syssetsFile) {
        in = new In(syssetsFile);

        while (in.hasNextLine()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");
            for (int i = 1; i < splitLine.length; i++) {
                String[] words = splitLine[i].split(" ");
                if (!splitLine[i].equals("dummy")) {
                    for (String word : words) {
                        addEdge(word, Integer.parseInt(splitLine[0]));
                        if (!adjList2.containsKey(Integer.parseInt(splitLine[0]))){
                            adjList2.put(Integer.parseInt(splitLine[0]), new ArrayList<>());
                        }
                            adjList2.get(Integer.parseInt(splitLine[0])).add(word);

                    }
                }
            }
        }
    }

    public void addEdge(String v, int m) {
        if (!adjList.containsKey(v)) {
            adjList.put(v, new ArrayList<>());
        }
            adjList.get(v).add(m);
    }

    Iterable<Integer> adj(String v) {
        return adjList.get(v);
    }

    public  ArrayList<String> GetString(int m) {
        return adjList2.get(m);
    }
}
