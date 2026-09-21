#include <string.h>
#include <stdlib.h>

char* convert(char* s, int numRows) {
    int n = strlen(s);

    if (numRows == 1 || numRows >= n)
        return s;

    char* result = malloc((n + 1) * sizeof(char));
    int index = 0;

    int cycle = 2 * (numRows - 1);

    for (int row = 0; row < numRows; row++) {

        for (int i = row; i < n; i += cycle) {
            result[index++] = s[i];

            int diagonal = i + cycle - 2 * row;

            if (row != 0 && row != numRows - 1 && diagonal < n) {
                result[index++] = s[diagonal];
            }
        }
    }

    result[index] = '\0';

    return result;
}