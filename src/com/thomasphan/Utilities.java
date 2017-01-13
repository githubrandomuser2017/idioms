package com.thomasphan;

import java.util.List;

public class Utilities {
    
    /**
     * Utility function to join Strings concatenated together and 
     * separated by a delimiter.
     */
    public static String join(List<String> tokens, String delim) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tokens.size(); i++) {
            sb.append(tokens.get(i));
            if (i < tokens.size()-1) {
                sb.append(delim);
            }
        }
        
        return sb.toString();
    }

}
