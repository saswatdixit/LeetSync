class Solution(object):
    def findTheDistanceValue(self, arr1, arr2, d):
        ans = 0

        for a in arr1:
            valid = True

            for b in arr2:
                if abs(a - b) <= d:
                    valid = False
                    break

            if valid:
                ans += 1

        return ans