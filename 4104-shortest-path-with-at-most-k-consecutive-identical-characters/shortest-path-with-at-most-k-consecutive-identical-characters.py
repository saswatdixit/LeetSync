import heapq

class Solution:
    def shortestPath(self, n, edges, labels, k):
        graph = [[] for _ in range(n)]

        for u, v, w in edges:
            graph[u].append((v, w))

        INF = float('inf')
        dist = [[INF] * (k + 1) for _ in range(n)]

        dist[0][1] = 0
        pq = [(0, 0, 1)]  # cost, node, consecutive count

        while pq:
            cost, node, count = heapq.heappop(pq)

            if cost != dist[node][count]:
                continue

            if node == n - 1:
                return cost

            for nxt, weight in graph[node]:
                if labels[node] == labels[nxt]:
                    next_count = count + 1
                else:
                    next_count = 1

                if next_count > k:
                    continue

                new_cost = cost + weight

                if new_cost < dist[nxt][next_count]:
                    dist[nxt][next_count] = new_cost
                    heapq.heappush(
                        pq, (new_cost, nxt, next_count)
                    )

        return -1