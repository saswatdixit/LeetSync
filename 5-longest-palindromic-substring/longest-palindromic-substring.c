char* longestPalindrome(char* s) {
    int n = strlen(s);

    int start = 0;
    int maxLen = 1;

    for (int i = 0; i < n; i++) {

        // Odd length
        int l = i, r = i;

        while (l >= 0 && r < n && s[l] == s[r]) {
            if (r - l + 1 > maxLen) {
                start = l;
                maxLen = r - l + 1;
            }
            l--;
            r++;
        }

        // Even length
        l = i;
        r = i + 1;

        while (l >= 0 && r < n && s[l] == s[r]) {
            if (r - l + 1 > maxLen) {
                start = l;
                maxLen = r - l + 1;
            }
            l--;
            r++;
        }
    }

    char* result = malloc((maxLen + 1) * sizeof(char));
    strncpy(result, s + start, maxLen);
    result[maxLen] = '\0';

    return result;
}