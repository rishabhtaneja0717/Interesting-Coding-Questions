/*Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).
*/

class WildcardMatchingSolution {
    public boolean isMatch(String s, String p) {
        if(s == null && p ==null) {
            return true;
        }
        
        if(s == null || p == null) {
            return false;
        }
        
        boolean [] [] T = new boolean [s.length() +1] [p.length() +1];
        
        T[0][0] = true;
        
        // first row
        for(int j =1; j<= p.length(); j++) {
            if(p.charAt(j -1) == '*') {
                T[0][j] = true;
            } else {
                break;
            }
        }
        
        for(int i =1; i<= s.length(); i++) {
            for(int j=1; j<= p.length(); j++) {
                if(s.charAt(i-1) == p.charAt(j - 1)  || p.charAt(j-1) == '?') {
                    T[i][j] = T[i-1][j-1];
                } else if(p.charAt(j-1) == '*') {
                    T[i][j] = T[i][j-1] || T[i-1] [j];
                } else {
                    T[i][j] = false;
                }
                
            }
        }
        
        return T[s.length()] [p.length()];        
    }
}
