class Solution {
    public int lengthOfLongestSubstring(String s) {
        boolean[] seen = new boolean[256];

        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {

            while (seen[s.charAt(right)]) {
                seen[s.charAt(left)] = false;
                left++;
            }

            seen[s.charAt(right)] = true;

            int len = right - left + 1;

            if (len > maxLen)
                maxLen = len;
        }

        return maxLen;
    }
}