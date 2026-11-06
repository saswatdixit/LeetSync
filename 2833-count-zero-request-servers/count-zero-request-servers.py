class Solution(object):
    def countServers(self, n, logs, x, queries):
        logs.sort(key=lambda a: a[1])

        q = sorted((time, i) for i, time in enumerate(queries))

        freq = [0] * (n + 1)
        ans = [0] * len(queries)

        left = 0
        right = 0
        active = 0

        for time, idx in q:
            # Add logs up to the query time
            while right < len(logs) and logs[right][1] <= time:
                server = logs[right][0]

                if freq[server] == 0:
                    active += 1

                freq[server] += 1
                right += 1

            # Remove logs outside the interval
            while left < right and logs[left][1] < time - x:
                server = logs[left][0]

                freq[server] -= 1

                if freq[server] == 0:
                    active -= 1

                left += 1

            ans[idx] = n - active

        return ans