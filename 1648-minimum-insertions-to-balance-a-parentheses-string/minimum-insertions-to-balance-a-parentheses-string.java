class Solution {
    public int minInsertions(String s) {
        int openCount = 0;
        int insertions = 0;

        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                openCount++;
            } else {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    insertions++;
                }

                if (openCount > 0) {
                    openCount--;
                } else {
                    insertions++;
                }
            }

            i++;
        }

        return insertions + openCount * 2;
    }
}