class Solution(object):
    def isNumber(self, s):
        i = 0
        n = len(s)

        # Sign
        if s[i] == '+' or s[i] == '-':
            i += 1

        digits = 0

        # Digits before decimal
        while i < n and s[i].isdigit():
            i += 1
            digits += 1

        # Decimal point
        if i < n and s[i] == '.':
            i += 1

        # Digits after decimal
        while i < n and s[i].isdigit():
            i += 1
            digits += 1

        # Must have at least one digit
        if digits == 0:
            return False

        # Exponent
        if i < n and (s[i] == 'e' or s[i] == 'E'):
            i += 1

            if i < n and (s[i] == '+' or s[i] == '-'):
                i += 1

            expDigits = 0

            while i < n and s[i].isdigit():
                i += 1
                expDigits += 1

            if expDigits == 0:
                return False

        return i == n