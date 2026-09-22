class Solution(object):
    def myAtoi(self, s):
        i = 0
        sign = 1
        result = 0

        # Skip spaces
        while i < len(s) and s[i] == ' ':
            i += 1

        # Check sign
        if i < len(s) and s[i] == '-':
            sign = -1
            i += 1
        elif i < len(s) and s[i] == '+':
            i += 1

        # Read digits
        while i < len(s) and s[i].isdigit():
            result = result * 10 + int(s[i])
            i += 1

        result *= sign

        # 32-bit range
        if result > 2147483647:
            return 2147483647

        if result < -2147483648:
            return -2147483648

        return result