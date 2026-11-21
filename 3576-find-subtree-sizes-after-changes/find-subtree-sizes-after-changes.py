class Solution(object):
    def findSubtreeSizes(self, parent, s):
        n = len(parent)

        # Build original tree
        children = [[] for _ in range(n)]

        for i in range(1, n):
            children[parent[i]].append(i)

        # Store the closest ancestor with each character
        last = [-1] * 26

        # Final tree
        new_children = [[] for _ in range(n)]

        def dfs(node):
            ch = ord(s[node]) - ord('a')

            # Find closest matching ancestor
            old = last[ch]

            # Update closest matching ancestor
            last[ch] = node

            if old == -1:
                new_parent = parent[node]
            else:
                new_parent = old

            # Connect node to its new parent
            if node != 0:
                new_children[new_parent].append(node)

            # Visit original children
            for child in children[node]:
                dfs(child)

            # Restore previous ancestor
            last[ch] = old

        dfs(0)

        # Calculate subtree sizes in final tree
        ans = [1] * n

        def calculate(node):
            for child in new_children[node]:
                calculate(child)
                ans[node] += ans[child]

        calculate(0)

        return ans