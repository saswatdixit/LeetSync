class Solution(object):
    def multiply(self, num1, num2):
        if num1 == "0" or num2 == "0":
            return "0"

        result = [0] * (len(num1) + len(num2))

        for i in range(len(num1) - 1, -1, -1):
            for j in range(len(num2) - 1, -1, -1):
                a = ord(num1[i]) - ord('0')
                b = ord(num2[j]) - ord('0')

                product = a * b
                pos = i + j + 1

                result[pos] += product
                result[pos - 1] += result[pos] // 10
                result[pos] %= 10

        start = 0

        while start < len(result) and result[start] == 0:
            start += 1

        return ''.join(str(x) for x in result[start:])