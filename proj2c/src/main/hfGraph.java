package main;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class hfGraph {

    In in;
    HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<Integer, ArrayList<Integer>>();

    public hfGraph(String hyponymsFile) {
        in = new In(hyponymsFile);
        while (in.hasNextLine()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");
            for (int i = 1; i < splitLine.length; i++) {
                addEdge(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[i]));
            }
        }

    }

    public void addEdge(int v, int m) {
        if (!adjList.containsKey(v)) {
            adjList.put(v, new ArrayList<>());
        }
         adjList.get(v).add(m);

}


    Iterable<Integer> adj(int v) {
        return adjList.get(v);
    }


    public HashSet<Integer> getPathSet(int v) {
        HashSet<Integer> Path = new HashSet<>();
        if (adjList.containsKey(v)) {
            for (int num : adj(v)) {
                HashSet<Integer> Path2 = getPathSet(num);
                Path.add(num);
                Path.addAll(Path2);
            }
        }
        return Path;
    }
}
