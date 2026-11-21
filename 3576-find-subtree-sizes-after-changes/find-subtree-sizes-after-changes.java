import java.util.*;

class Solution {
    List<Integer>[] children;
    List<Integer>[] newChildren;
    int[] last;
    int[] ans;
    String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;

        children = new ArrayList[n];
        newChildren = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
            newChildren[i] = new ArrayList<>();
        }

        // Build original tree
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        last = new int[26];
        Arrays.fill(last, -1);

        // Build modified tree
        dfs(0, parent);

        // Calculate subtree sizes
        ans = new int[n];
        Arrays.fill(ans, 1);

        calculate(0);

        return ans;
    }

    private void dfs(int node, int[] parent) {
        int ch = s.charAt(node) - 'a';

        int old = last[ch];
        last[ch] = node;

        int newParent = (old == -1) ? parent[node] : old;

        if (node != 0) {
            newChildren[newParent].add(node);
        }

        for (int child : children[node]) {
            dfs(child, parent);
        }

        // Restore previous ancestor
        last[ch] = old;
    }

    private void calculate(int node) {
        for (int child : newChildren[node]) {
            calculate(child);
            ans[node] += ans[child];
        }
    }
}