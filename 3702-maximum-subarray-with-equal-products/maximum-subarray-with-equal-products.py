class Solution(object):
    def maxLength(self, nums):
        n = len(nums)
        ans = 0

        for i in range(n):
            product = 1
            gcd_val = 0
            lcm_val = 1

            for j in range(i, n):
                x = nums[j]

                product *= x
                gcd_val = self.gcd(gcd_val, x)
                lcm_val = (lcm_val // self.gcd(lcm_val, x)) * x

                if product == gcd_val * lcm_val:
                    ans = max(ans, j - i + 1)

        return ans

    def gcd(self, a, b):
        while b:
            a, b = b, a % b
        return a