import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        int wordLen = words[0].length();
        int wordCount = words.length;

        HashMap<String, Integer> required = new HashMap<>();

        for (String word : words) {
            required.put(word, required.getOrDefault(word, 0) + 1);
        }

        // Try every possible starting offset
        for (int offset = 0; offset < wordLen; offset++) {

            int left = offset;
            int right = offset;
            int count = 0;

            HashMap<String, Integer> current = new HashMap<>();

            while (right + wordLen <= s.length()) {

                String word = s.substring(right, right + wordLen);
                right += wordLen;

                // Word is not required
                if (!required.containsKey(word)) {
                    current.clear();
                    count = 0;
                    left = right;
                    continue;
                }

                current.put(word, current.getOrDefault(word, 0) + 1);
                count++;

                // Too many copies of this word
                while (current.get(word) > required.get(word)) {
                    String leftWord = s.substring(left, left + wordLen);

                    current.put(leftWord, current.get(leftWord) - 1);

                    left += wordLen;
                    count--;
                }

                // All words found
                if (count == wordCount) {
                    result.add(left);

                    // Move window forward
                    String leftWord = s.substring(left, left + wordLen);
                    current.put(leftWord, current.get(leftWord) - 1);

                    left += wordLen;
                    count--;
                }
            }
        }

        return result;
    }
}