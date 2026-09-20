int lengthOfLongestSubstring(char* s) {
    int seen[256] = {0};
    int left = 0;
    int maxLen = 0;

    for (int right = 0; s[right] != '\0'; right++) {

        while (seen[(unsigned char)s[right]]) {
            seen[(unsigned char)s[left]] = 0;
            left++;
        }

        seen[(unsigned char)s[right]] = 1;

        int len = right - left + 1;

        if (len > maxLen)
            maxLen = len;
    }

    return maxLen;
}