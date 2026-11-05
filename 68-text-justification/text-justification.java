import java.util.*;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();

        int i = 0;
        int n = words.length;

        while (i < n) {
            int j = i;
            int letters = 0;

            // Find maximum words that fit in this line
            while (j < n && letters + words[j].length() + (j - i) <= maxWidth) {
                letters += words[j].length();
                j++;
            }

            int gaps = j - i - 1;
            StringBuilder line = new StringBuilder();

            // Last line or only one word: left justified
            if (j == n || gaps == 0) {
                for (int k = i; k < j; k++) {
                    if (k > i) {
                        line.append(" ");
                    }
                    line.append(words[k]);
                }

                while (line.length() < maxWidth) {
                    line.append(" ");
                }
            } 
            else {
                int totalSpaces = maxWidth - letters;
                int spaces = totalSpaces / gaps;
                int extra = totalSpaces % gaps;

                for (int k = i; k < j - 1; k++) {
                    line.append(words[k]);

                    for (int s = 0; s < spaces; s++) {
                        line.append(" ");
                    }

                    if (extra > 0) {
                        line.append(" ");
                        extra--;
                    }
                }

                line.append(words[j - 1]);
            }

            result.add(line.toString());
            i = j;
        }

        return result;
    }
}