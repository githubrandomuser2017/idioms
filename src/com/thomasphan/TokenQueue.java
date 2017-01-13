package com.thomasphan;

import java.util.LinkedList;
import java.util.Queue;

/***
 * This class implements a queue that allows the caller to add tokens 
 * one after another (presumably from some text) and pull out sequences of 
 * tokens to be converted into NGram objects. The length of the sequence is 
 * configurable through the argument of the constructor. Passing in 3 will 
 * produce trigrams.
 */
public class TokenQueue {
    
    Queue<String> _queue = new LinkedList<String>();
    int _size = 1;
    
    /**
     * Principal constructor. Pass in 3 to generate lists for trigrams.
     * @param size The size of the token lists to accumulate.
     */
    public TokenQueue(int size) {
        _size = size;
    }
    
    /**
     * Add a token to the queue.
     * @param token The token to be added.
     */
    public void addToken(String token) {
        
        _queue.add(token);
        if (_queue.size() > _size) {
            _queue.poll();
        }
    }
    
    /**
     * Retrieve the most recent tokens added to the queue. The
     * returned array will have the size specified in the argument
     * of the constructor.
     * @return A newly-allocated array of most recent tokens, where
     * the 0th item in the array is the oldest from the queue.
     */
    public String[] getTokenArray() {
        
        String[] result = new String[_size];
        int i = 0;
        for (String token : _queue) {
            result[i++] = token;
        }
        
        return result;
    }
}
