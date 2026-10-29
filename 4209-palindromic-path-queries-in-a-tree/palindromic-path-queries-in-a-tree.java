import java.util.*;

class Solution {
    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] pref = new int[n];
        int timer = 0;

        int[][] stack = new int[2 * n + 5][3];
        int top = 0;
        stack[top++] = new int[]{0, -1, 0};

        while (top > 0) {
            int[] cur = stack[--top];
            int u = cur[0], p = cur[1], state = cur[2];

            if (state == 0) {
                tin[u] = timer++;
                parent[u] = (p == -1) ? u : p;
                pref[u] = (p == -1 ? 0 : pref[p])
                        ^ (1 << (s.charAt(u) - 'a'));

                stack[top++] = new int[]{u, p, 1};

                for (int v : adj[u]) {
                    if (v != p) {
                        depth[v] = depth[u] + 1;
                        stack[top++] = new int[]{v, u, 0};
                    }
                }
            } else {
                tout[u] = timer - 1;
            }
        }

        int[][] up = new int[LOG][n];
        up[0] = parent.clone();

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        Fenwick fw = new Fenwick(n + 1);
        char[] chars = s.toCharArray();
        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            int u = Integer.parseInt(parts[1]);

            if (parts[0].equals("update")) {
                char c = parts[2].charAt(0);
                int delta = (1 << (chars[u] - 'a'))
                        ^ (1 << (c - 'a'));

                if (delta != 0) {
                    fw.rangeXor(tin[u], tout[u], delta);
                    chars[u] = c;
                }
            } else {
                int v = Integer.parseInt(parts[2]);
                int w = getLca(u, v, depth, up, LOG);

                int pu = pref[u] ^ fw.query(tin[u]);
                int pv = pref[v] ^ fw.query(tin[v]);
                int mask = pu ^ pv ^ (1 << (chars[w] - 'a'));

                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    private int getLca(int u, int v, int[] depth,
                       int[][] up, int LOG) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }

    static class Fenwick {
        int[] bit;
        int n;

        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 2];
        }

        void update(int i, int delta) {
            i++;
            while (i < bit.length) {
                bit[i] ^= delta;
                i += i & -i;
            }
        }

        void rangeXor(int left, int right, int delta) {
            update(left, delta);
            update(right + 1, delta);
        }

        int query(int i) {
            i++;
            int result = 0;

            while (i > 0) {
                result ^= bit[i];
                i -= i & -i;
            }

            return result;
        }
    }
}