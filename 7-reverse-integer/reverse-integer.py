class Solution(object):
    def reverse(self, x):
        sign = -1 if x < 0 else 1
        x = abs(x)

        result = 0

        while x > 0:
            digit = x % 10
            result = result * 10 + digit
            x //= 10

        result *= sign

        if result < -2147483648 or result > 2147483647:
            return 0

        return result