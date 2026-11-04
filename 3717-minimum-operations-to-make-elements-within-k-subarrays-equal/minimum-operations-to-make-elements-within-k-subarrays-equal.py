class Fenwick:
    def __init__(self, n):
        self.n = n
        self.tree = [0] * (n + 1)

    def add(self, i, val):
        i += 1
        while i <= self.n:
            self.tree[i] += val
            i += i & -i

    def query(self, i):
        # Sum of first i elements
        ans = 0
        while i > 0:
            ans += self.tree[i]
            i -= i & -i
        return ans

    def kth(self, k):
        # Returns 0-based index of kth smallest element
        idx = 0
        bit = 1 << (self.n.bit_length() - 1)

        while bit:
            nxt = idx + bit
            if nxt <= self.n and self.tree[nxt] < k:
                idx = nxt
                k -= self.tree[nxt]
            bit >>= 1

        return idx


class Solution(object):
    def minOperations(self, nums, x, k):
        n = len(nums)

        vals = sorted(set(nums))
        size = len(vals)

        pos = {v: i for i, v in enumerate(vals)}

        count = Fenwick(size)
        sums = Fenwick(size)

        def update(num, delta):
            i = pos[num]
            count.add(i, delta)
            sums.add(i, num * delta)

        # Insert the first window
        for i in range(x):
            update(nums[i], 1)

        costs = []

        # Calculate the cost of every window
        for start in range(n - x + 1):
            median_idx = count.kth((x + 1) // 2)
            median = vals[median_idx]

            left_count = count.query(median_idx + 1)
            left_sum = sums.query(median_idx + 1)

            total_sum = sums.query(size)
            right_count = x - left_count
            right_sum = total_sum - left_sum

            cost = (
                median * left_count - left_sum
                + right_sum - median * right_count
            )

            costs.append(cost)

            # Slide the window
            if start + x < n:
                update(nums[start], -1)
                update(nums[start + x], 1)

        # DP: minimum cost to select j windows
        INF = float('inf')
        prev = [0] * (n + 1)

        for j in range(1, k + 1):
            curr = [INF] * (n + 1)

            for i in range(1, n + 1):
                # Option 1: Skip the current element
                curr[i] = curr[i - 1]

                # Option 2: Select the window ending at i - 1
                if i >= x:
                    curr[i] = min(
                        curr[i],
                        prev[i - x] + costs[i - x]
                    )

            prev = curr

        return prev[n]