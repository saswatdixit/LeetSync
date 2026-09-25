#include <stdlib.h>

int compare(const void* a, const void* b) {
    return (*(int*)a - *(int*)b);
}

int** threeSum(int* nums, int numsSize, int* returnSize,
               int** returnColumnSizes) {

    *returnSize = 0;

    int maxResult = numsSize * numsSize;
    int** result = malloc(sizeof(int*) * maxResult);
    *returnColumnSizes = malloc(sizeof(int) * maxResult);

    qsort(nums, numsSize, sizeof(int), compare);

    for (int i = 0; i < numsSize - 2; i++) {

        if (i > 0 && nums[i] == nums[i - 1])
            continue;

        int left = i + 1;
        int right = numsSize - 1;

        while (left < right) {

            long sum = (long)nums[i] + nums[left] + nums[right];

            if (sum == 0) {

                int* triplet = malloc(sizeof(int) * 3);

                triplet[0] = nums[i];
                triplet[1] = nums[left];
                triplet[2] = nums[right];

                result[*returnSize] = triplet;
                (*returnColumnSizes)[*returnSize] = 3;
                (*returnSize)++;

                while (left < right && nums[left] == nums[left + 1])
                    left++;

                while (left < right && nums[right] == nums[right - 1])
                    right--;

                left++;
                right--;
            }
            else if (sum < 0) {
                left++;
            }
            else {
                right--;
            }
        }
    }

    return result;
}