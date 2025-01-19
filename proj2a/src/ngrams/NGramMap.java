package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {


    String wF;
    String cF;
    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        wF = wordsFilename;
        cF = countsFilename;
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        In in = new In(wF);
        TimeSeries newTS = new TimeSeries();

        while (in.hasNextLine()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split("\t");
            if (splitLine[0].equals(word) && Integer.parseInt(splitLine[1]) >= startYear && Integer.parseInt(splitLine[1]) <= endYear) {
                newTS.put(Integer.parseInt(splitLine[1]), Double.parseDouble(splitLine[2]));
            }
        }
        return newTS;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        In in = new In(wF);
        TimeSeries newTS = new TimeSeries();

        while (in.hasNextLine()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split("\t");
            if (splitLine[0].equals(word)) {
                newTS.put(Integer.parseInt(splitLine[1]), Double.parseDouble(splitLine[2]));
            }
        }
        return newTS;
    }


    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        In in = new In(cF);
        TimeSeries newTS = new TimeSeries();

        while (in.hasNextLine()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");
            newTS.put(Integer.parseInt(splitLine[0]), Double.parseDouble(splitLine[1]));
        }
        return newTS;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        In in = new In(wF);
        TimeSeries newTS = new TimeSeries();

        while (in.hasNextLine()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split("\t");
            if (splitLine[0].equals(word) && Integer.parseInt(splitLine[1]) >= startYear && Integer.parseInt(splitLine[1]) <= endYear) {
                newTS.put(Integer.parseInt(splitLine[1]), Double.parseDouble(splitLine[2]) / totalCountHistory().get(Integer.parseInt(splitLine[1])));
            }
        }
        return newTS;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        In in = new In(wF);
        TimeSeries newTS = new TimeSeries();

        while (in.hasNextLine()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split("\t");
            if (splitLine[0].equals(word)) {
                newTS.put(Integer.parseInt(splitLine[1]), Double.parseDouble(splitLine[2]) / totalCountHistory().get(Integer.parseInt(splitLine[1])));
            }
        }
        return newTS;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        In in = new In(wF);
        TimeSeries newTS = new TimeSeries();
        for (String word : words) {
            newTS = newTS.plus(weightHistory(word, startYear, endYear));
        }
        return newTS;
    }
    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        In in = new In(wF);
        TimeSeries newTS = new TimeSeries();
        for (String word : words) {
           newTS = newTS.plus(weightHistory(word));
        }
        return newTS;
    }

}
