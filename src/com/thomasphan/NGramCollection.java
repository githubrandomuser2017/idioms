package com.thomasphan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/***
 * This class manages a collection of NGram objects, particularly trigrams.
 * Trigrams can be added, and their probabilities can be returned.
 */
public class NGramCollection {
    
    
    Map<String, NGram> _keyToNGram; // Map of key to NGram objects.
    List<NGram> _data;              // Auxilary list for sorting.
    int _trigramCount = 0;          // Total count of trigrams.
    


    /**
     * Principal constructor.
     */
    public NGramCollection() {

        _keyToNGram = new HashMap<String, NGram>();
        _data = new ArrayList<NGram>();

    }
    
    /**
     * Add token sequence by converting it into an NGram and then adding
     * it to the collection.
     * @param tokens The sequence of tokens.
     */
    private void addNGram(List<String> tokens) {
        
        String key = Utilities.join(tokens, "-&-");
        NGram nGram = _keyToNGram.get(key);
        if (nGram == null) {
            nGram = new NGram(new ArrayList<String>(tokens));
            _keyToNGram.put(key, nGram);
            _data.add(nGram);
        }
        else {
            nGram.incrementCount(1);
        }
    }

    /**
     * Add a trigram (three tokens) to the collection.
     * @param token1 The first token
     * @param token2 The second token
     * @param token3 The third token
     */
    public void addTrigram(String token1, String token2, String token3) {
        List<String> tokens = new ArrayList<String>();
        tokens.add(token1);
        tokens.add(token2);
        tokens.add(token3);
        addNGram(tokens);
        
        _trigramCount++;
    }
    
    /**
     * Update the probabilities of all the trigrams in the collection.
     */
    public void updateProbabilities() {
        
        // Loop over each NGram and divide by the total count of trigrams.
        for (Map.Entry<String, NGram> kv : _keyToNGram.entrySet()) {
            NGram nGram = kv.getValue();
            nGram.updateProbability(_trigramCount);
        }
    }
    
    /**
     * Sort the NGrams ordered by descending count and return the top N.
     * @param N The number of top NGrams to return.
     * @param avoidSentenceBoundaries Do not include NGrams with the sentence boundary markers.
     * @param avoidPunctuation Do not include NGrams with punctuation tokens.
     * @return A list of top-N NGrams.
     */
    public List<NGram> sortAndGetTopN(int N, boolean avoidSentenceBoundaries, boolean avoidPunctuation) {
        
        List<NGram> result = new ArrayList<NGram>();
        
        Collections.sort(_data, new Comparator<NGram>() {
            @Override
            public int compare(NGram a, NGram b) {
                return (int)(b._count - a._count);
            }
        });
        
        int count = 0;
        for (NGram nGram : _data) {
            if (avoidSentenceBoundaries && nGram.contains("<s>")) continue;
            if (avoidPunctuation && nGram.containsPunctuation()) continue;
            result.add(nGram);
            if (++count == N) break;
        }
        
        return result;
        
    }

    /**
     * Print all the NGrams in the collection.
     */
    public void printAllNGrams() {
        
        for (NGram ngram : _data) {
            System.out.printf("%s\n", ngram.toString());
        }
    }
}

