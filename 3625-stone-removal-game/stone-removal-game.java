class Solution {
    public boolean canAliceWin(int n) {
        if (n < 10) {
            return false;
        }

        int stones = n - 10;
        int remove = 9;
        boolean aliceTurn = false;

        while (stones >= remove) {
            stones -= remove;
            remove--;
            aliceTurn = !aliceTurn;
        }

        return !aliceTurn;
    }
}