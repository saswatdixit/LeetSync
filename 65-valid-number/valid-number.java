class Solution {
    public boolean isNumber(String s) {
        int i = 0;
        int n = s.length();

        // Sign
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            i++;
        }

        int digits = 0;

        // Digits before decimal
        while (i < n && Character.isDigit(s.charAt(i))) {
            i++;
            digits++;
        }

        // Decimal point
        if (i < n && s.charAt(i) == '.') {
            i++;
        }

        // Digits after decimal
        while (i < n && Character.isDigit(s.charAt(i))) {
            i++;
            digits++;
        }

        // Must have at least one digit
        if (digits == 0)
            return false;

        // Exponent
        if (i < n && (s.charAt(i) == 'e' || s.charAt(i) == 'E')) {
            i++;

            if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
                i++;
            }

            int expDigits = 0;

            while (i < n && Character.isDigit(s.charAt(i))) {
                i++;
                expDigits++;
            }

            if (expDigits == 0)
                return false;
        }

        return i == n;
    }
}