class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int[] result = new int[num1.length() + num2.length()];

        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int a = num1.charAt(i) - '0';
                int b = num2.charAt(j) - '0';

                int product = a * b;
                int pos = i + j + 1;

                result[pos] += product;
                result[pos - 1] += result[pos] / 10;
                result[pos] %= 10;
            }
        }

        StringBuilder answer = new StringBuilder();

        int start = 0;
        while (start < result.length && result[start] == 0) {
            start++;
        }

        for (int i = start; i < result.length; i++) {
            answer.append(result[i]);
        }

        return answer.toString();
    }
}