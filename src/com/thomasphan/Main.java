package com.thomasphan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/***
 * This class provides a command-line entry point into the program.
 * Usage: java com.thomasphan.Main textfile topN
 *     where: 
 *     textfile = path to the text from which you want to find frequent NGrams.
 *     topN = the number of top-most occurring NGrams
 */
public class Main {

    public static void main(String argv[]) throws IOException {
        
        if (argv.length != 2) {
            System.out.printf("Usage: java com.thomasphan.Main textfile topN\n");
            return;
        }
        
        String pathToFile = argv[0];
        int topN = Integer.parseInt(argv[1]);

        BufferedReader br = new BufferedReader(new FileReader(pathToFile));

        
        TokenQueue tokenQueue = new TokenQueue(3);
        NGramCollection nGramCollection = new NGramCollection();

        String buffer;
        while ((buffer = br.readLine()) != null) {
            
            // Add start tokens for the line.
            tokenQueue.addToken("<s>");
            tokenQueue.addToken("<s>");
            
            String[] tokens = buffer.split(" ");
            for (String token : tokens) {
                tokenQueue.addToken(token);
                String[] tokenArray = tokenQueue.getTokenArray();
                nGramCollection.addTrigram(tokenArray[0], tokenArray[1], tokenArray[2]);
            }
        }
        br.close();
        
        nGramCollection.updateProbabilities();
        boolean avoidSentenceBoundaries = true;
        boolean avoidPunctuation = true;
        List<NGram> nGrams = 
                nGramCollection.sortAndGetTopN(topN, avoidSentenceBoundaries, avoidPunctuation);
        
        for (NGram ngram : nGrams) {
            System.out.printf("%s\n", ngram.toString());
        }

    }

}
