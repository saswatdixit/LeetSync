class Solution(object):
    def maximumSaleItems(self, items, budget):
        n = len(items)

        bonus = [0] * n

        for i in range(n):
            for j in range(n):
                if i != j and items[j][0] % items[i][0] == 0:
                    bonus[i] += 1

        dp = [0] * (budget + 1)

        for i in range(n):
            price = items[i][1]
            d = bonus[i]

            prev = dp[:]
            active = [-1] * (budget + 1)

            for b in range(price, budget + 1):
                first = prev[b - price] + 1 + d
                more = active[b - price] + 1

                active[b] = max(first, more)
                dp[b] = max(dp[b], active[b])

        return dp[budget]