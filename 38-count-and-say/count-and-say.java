class Solution {
    public String countAndSay(int n) {
        String result = "1";

        for (int k = 1; k < n; k++) {
            StringBuilder newResult = new StringBuilder();
            int i = 0;

            while (i < result.length()) {
                int count = 1;

                while (i + 1 < result.length() &&
                       result.charAt(i) == result.charAt(i + 1)) {
                    count++;
                    i++;
                }

                newResult.append(count);
                newResult.append(result.charAt(i));

                i++;
            }

            result = newResult.toString();
        }

        return result;
    }
}