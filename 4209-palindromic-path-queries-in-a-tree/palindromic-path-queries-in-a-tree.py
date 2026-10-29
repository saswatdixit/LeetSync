class Solution(object):
    def palindromePath(self, n, edges, s, queries):
        from collections import deque

        adj = [[] for _ in range(n)]
        for u, v in edges:
            adj[u].append(v)
            adj[v].append(u)

        LOG = n.bit_length()
        parent = [0] * n
        depth = [0] * n
        tin = [0] * n
        tout = [0] * n
        pref = [0] * n
        timer = 0

        # Iterative DFS
        stack = [(0, -1, 0)]
        while stack:
            u, p, state = stack.pop()

            if state == 0:
                tin[u] = timer
                timer += 1
                parent[u] = u if p == -1 else p
                pref[u] = (pref[p] if p != -1 else 0) ^ (1 << (ord(s[u]) - 97))

                stack.append((u, p, 1))
                for v in reversed(adj[u]):
                    if v != p:
                        depth[v] = depth[u] + 1
                        stack.append((v, u, 0))
            else:
                tout[u] = timer - 1

        up = [parent[:]]
        for j in range(1, LOG):
            prev = up[j - 1]
            up.append([prev[prev[i]] for i in range(n)])

        def lca(u, v):
            if depth[u] < depth[v]:
                u, v = v, u

            diff = depth[u] - depth[v]
            for j in range(LOG):
                if diff & (1 << j):
                    u = up[j][u]

            if u == v:
                return u

            for j in range(LOG - 1, -1, -1):
                if up[j][u] != up[j][v]:
                    u, v = up[j][u], up[j][v]

            return parent[u]

        bit = [0] * (n + 2)

        def update(i, delta):
            i += 1
            while i < len(bit):
                bit[i] ^= delta
                i += i & -i

        def point_query(i):
            i += 1
            ans = 0
            while i > 0:
                ans ^= bit[i]
                i -= i & -i
            return ans

        def range_xor(left, right, delta):
            update(left, delta)
            update(right + 1, delta)

        chars = list(s)
        ans = []

        for q in queries:
            parts = q.split()
            u = int(parts[1])

            if parts[0] == "update":
                c = parts[2]
                delta = (1 << (ord(chars[u]) - 97)) ^ (1 << (ord(c) - 97))

                if delta:
                    range_xor(tin[u], tout[u], delta)
                    chars[u] = c
            else:
                v = int(parts[2])
                w = lca(u, v)

                pu = pref[u] ^ point_query(tin[u])
                pv = pref[v] ^ point_query(tin[v])
                mask = pu ^ pv ^ (1 << (ord(chars[w]) - 97))

                ans.append((mask & (mask - 1)) == 0)

        return ans