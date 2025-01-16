package ngrams;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        for (int year = startYear; year <= endYear; year++){
            this.put(year, ts.get(year));
        }
    }

    /**
     *  Returns all years for this time series in ascending order.
     */
    public List<Integer> years() {
       return new ArrayList<Integer>(this.keySet());
    }

    /**
     *  Returns all data for this time series. Must correspond to the
     *  order of years().
     */
    public List<Double> data() {
        ArrayList<Double> data = new ArrayList<>();
        for (int year : years()) {
            data.add(get(year));
        }
        return data;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries newTS = new TimeSeries();
        if (years().isEmpty() && ts.years().isEmpty()) {
            return newTS;
        }else {
            int count1 = 0;
            int count2 = 0;
            while (count1 < years().size() || count2 < ts.years().size()) {

                if (count1 == years().size()) {
                    int year2 = ts.years().get(count2);
                    while (count2 < ts.years().size()) {
                        newTS.put(year2, ts.get(year2));
                        count2++;
                    }
                } else if (count2 == ts.years().size()) {
                    int year1 = years().get(count1);
                    while (count1 < years().size()) {
                        newTS.put(year1, ts.get(year1));
                        count1++;
                    }

                } else {
                    int year1 = years().get(count1);
                    int year2 = ts.years().get(count2);

                    if (year1 > year2) {
                        newTS.put(year2, ts.get(year2));
                        count2++;
                    } else if (year1 < year2) {
                        newTS.put(year1, get(year1));
                        count1++;
                    } else {
                        newTS.put(year1, get(year1)+ts.get(year2));
                        count1++;
                        count2++;
                    }
                }
            }
            return newTS;
        }
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries newTS = new TimeSeries();
        for (int year : years()) {
            if (!ts.years().contains(year)) {
                throw new IllegalArgumentException();
            } else {
                Double Data = get(year) / ts.get(year);
                newTS.put(year, Data);
            }
        }
        return newTS;
    }



}
