package com.thomasphan;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * This class encapsulates an n-gram (a consecutive sequence of tokens)
 * as well as the frequency and probability of the n-gram in some text.
 */
public class NGram {

    List<String> _tokens; // The sequence of tokens.
    long _count;           // The frequency count of this n-gram.
    double _probability;  // The probability of this n-gram.

    private String _tokensAsOneString; // Used for printing.

    private static Set<String> _punctuation = new HashSet<String>();
    static {
        String [] punctuationArray = {"." , "," , ";" , ":", "?" , "!" , "\"", "''", "``", ")" , "(", "$", "%"};
        for (String punctuation : punctuationArray) {
            _punctuation.add(punctuation);
        }
    }

    /**
     * Principal constructor.
     * @param tokens The token sequence.
     */
    public NGram(List<String> tokens) {
        _count = 1;
        _tokens = tokens;
        _tokensAsOneString = Utilities.join(tokens, " ");
    }
    
    /**
     * Increment the frequency count of this n-gram.
     * @param increment The amount by which to increment.
     */
    public void incrementCount(int increment) {
        _count += increment;
    }
    
    /**
     * Get the frequency count of this n-gram.
     * @return The frequency count.
     */
    public long getCount() {
        return _count;
    }
    
    /**
     * Compute the occurrence probability of this n-gram. The denominator
     * of the probability must be passed in; it is most likely the total
     * number of n-grams.
     * @param denominator The denominator for the probability.
     */
    public void updateProbability(int denominator) {
        _probability = (double)_count / denominator;
    }
    
    /**
     * Get the probability of this n-gram.
     * @return The probability of this n-gram.
     */
    public double getProbability() {
        return _probability;
    }
    
    /**
     * Checks whether or not any of the tokens contains a specific string.
     * @param string The String to check.
     * @return True if any of the tokens is the input string, false otherwise.
     */
    public boolean contains(String string) {
        for (String s : _tokens) {
            if (s.equals(string)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks whether or not any of the tokens contains punctuation.
     * @return True if any of the tokens is punctuation, false otherwise.
     */
    public boolean containsPunctuation() {
        for (String s : _tokens) {
            if (_punctuation.contains(s)) return true;

        }
        return false;
    }
    
    @Override
    public String toString() {
        return String.format("%-30s, count=%3d, prob=%.10f", 
                _tokensAsOneString, _count, _probability);
        
    }
}
