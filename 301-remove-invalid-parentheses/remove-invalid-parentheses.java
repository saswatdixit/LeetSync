class Solution {
    public List<String> removeInvalidParentheses(String s) {
        Set<String> result = new HashSet<>();

        int left = 0;
        int right = 0;

        // Find the minimum number of '(' and ')' to remove
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                left++;
            } else if (ch == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        backtrack(s, 0, left, right, 0, new StringBuilder(), result);

        return new ArrayList<>(result);
    }

    private void backtrack(String s, int index, int leftRemove,
                            int rightRemove, int balance,
                            StringBuilder current,
                            Set<String> result) {

        if (index == s.length()) {
            if (leftRemove == 0 && rightRemove == 0 && balance == 0) {
                result.add(current.toString());
            }
            return;
        }

        char ch = s.charAt(index);

        // Remove current parenthesis
        if (ch == '(' && leftRemove > 0) {
            backtrack(s, index + 1, leftRemove - 1, rightRemove,
                      balance, current, result);
        }

        if (ch == ')' && rightRemove > 0) {
            backtrack(s, index + 1, leftRemove, rightRemove - 1,
                      balance, current, result);
        }

        // Keep current character
        current.append(ch);

        if (ch == '(') {
            backtrack(s, index + 1, leftRemove, rightRemove,
                      balance + 1, current, result);
        } else if (ch == ')') {
            if (balance > 0) {
                backtrack(s, index + 1, leftRemove, rightRemove,
                          balance - 1, current, result);
            }
        } else {
            backtrack(s, index + 1, leftRemove, rightRemove,
                      balance, current, result);
        }

        current.deleteCharAt(current.length() - 1);
    }
}