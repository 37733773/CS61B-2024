package main;

import browser.NgordnetQueryHandler;

import static main.Main.SMALL_HYPONYM_FILE;
import static main.Main.SMALL_SYNSET_FILE;


public class AutograderBuddy {
    /**
     * Returns a HyponymHandler
     */
    public static NgordnetQueryHandler getHyponymsHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {
        return new HyponymsHandler(synsetFile, hyponymFile);
    }
//        throw new RuntimeException("Please fill out AutograderBuddy.java!");

}
