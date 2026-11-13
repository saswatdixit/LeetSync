from collections import deque

class Solution(object):
    def countNonDecreasingSubarrays(self, nums, k):
        n = len(nums)
        dq = deque()

        right = n - 1
        sum_nums = 0
        sum_max = 0
        ans = 0

        for i in range(n - 1, -1, -1):
            x = nums[i]
            count = 1

            # Merge groups whose maximum is <= x
            while dq and dq[0][0] <= x:
                val, cnt = dq.popleft()
                sum_max -= val * cnt
                count += cnt

            dq.appendleft([x, count])

            sum_max += x * count
            sum_nums += x

            # Shrink the window from the right
            while sum_max - sum_nums > k:
                val, cnt = dq[-1]

                sum_max -= val
                sum_nums -= nums[right]

                if cnt == 1:
                    dq.pop()
                else:
                    dq[-1][1] -= 1

                right -= 1

            ans += right - i + 1

        return ans