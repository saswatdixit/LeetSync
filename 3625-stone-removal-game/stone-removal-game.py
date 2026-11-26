class Solution(object):
    def canAliceWin(self, n):
        if n < 10:
            return False

        stones = n - 10
        remove = 9
        alice_turn = False  # Bob plays next

        while stones >= remove:
            stones -= remove
            remove -= 1
            alice_turn = not alice_turn

        return not alice_turn