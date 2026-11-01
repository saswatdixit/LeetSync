class Solution {
    public int minMoves(int[] nums) {
        int minimum = nums[0];

        for (int num : nums) {
            minimum = Math.min(minimum, num);
        }

        int moves = 0;

        for (int num : nums) {
            moves += num - minimum;
        }

        return moves;
    }
}