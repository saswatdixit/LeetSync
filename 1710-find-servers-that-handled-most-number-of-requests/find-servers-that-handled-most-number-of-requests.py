import heapq

class Solution(object):
    def busiestServers(self, k, arrival, load):
        busy = []
        available = [0] * (k + 1)
        count = [0] * k

        # Fenwick Tree: initially all servers are available
        def update(i, val):
            i += 1
            while i <= k:
                available[i] += val
                i += i & -i

        def query(i):
            # Number of available servers with index < i
            s = 0
            while i > 0:
                s += available[i]
                i -= i & -i
            return s

        def find_kth(target):
            # Find the 0-indexed server with this available rank
            idx = 0
            bit = 1 << (k.bit_length() - 1)

            while bit:
                nxt = idx + bit
                if nxt <= k and available[nxt] < target:
                    idx = nxt
                    target -= available[nxt]
                bit >>= 1

            return idx

        for i in range(k):
            update(i, 1)

        for i in range(len(arrival)):
            time = arrival[i]

            # Release servers that have completed their requests
            while busy and busy[0][0] <= time:
                end, server = heapq.heappop(busy)
                update(server, 1)

            if query(k) == 0:
                continue

            start = i % k

            # Find the first available server >= start
            before = query(start)
            total = query(k)

            if before < total:
                server = find_kth(before + 1)
            else:
                server = find_kth(1)

            update(server, -1)
            count[server] += 1

            heapq.heappush(busy, (time + load[i], server))

        maximum = max(count)

        return [i for i in range(k) if count[i] == maximum]