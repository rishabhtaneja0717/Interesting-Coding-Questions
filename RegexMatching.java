/*Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

*/

class RegexMatching {
    public boolean isMatch(String s, String p) {
        
        if(s == null && p== null) {
            return true;
        }
        
        if(s == null || p == null) {
            return false;
        }
        
        if(p.length()== 0 && s.length() ==0) {
            return true;
        }
        
        if(s.length() == 0 || p.length() == 0 || p.charAt(0) == '*') {
            return false;
        }
        
        boolean T[][] = new boolean[s.length() + 1] [p.length() +1];
        
        T[0][0] = true;
        
        // first row, for pattern a*b*c* which should match empty array
        
        for(int j=2; j<=p.length(); j++) {
            if(p.charAt(j-1) == '*') {
                T[0][j] =T[0][j-2];
            }
        }
        
        for(int i=1; i<=s.length(); i++) {
            for (int j =1; j<= p.length(); j++) {
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {
                    T[i][j] = T[i-1][j-1];
                } else if(p.charAt(j-1) == '*'){
                    T[i][j] = T[i][j-2] || T[i-1][j];
                } else {
                    T[i][j] = false;
                }
            }
        }
        
        return T[s.length()][p.length()];
        
    }
}
