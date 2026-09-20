class Solution(object):
    def longestPalindrome(self, s):
        if len(s) < 2:
            return s

        start = 0
        end = 0

        for i in range(len(s)):
            # Odd length palindrome
            l = i
            r = i

            while l >= 0 and r < len(s) and s[l] == s[r]:
                if r - l > end - start:
                    start = l
                    end = r
                l -= 1
                r += 1

            # Even length palindrome
            l = i
            r = i + 1

            while l >= 0 and r < len(s) and s[l] == s[r]:
                if r - l > end - start:
                    start = l
                    end = r
                l -= 1
                r += 1

        return s[start:end + 1]