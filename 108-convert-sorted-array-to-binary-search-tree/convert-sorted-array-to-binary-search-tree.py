class Solution(object):
    def sortedArrayToBST(self, nums):

        def build(left, right):
            if left > right:
                return None

            mid = (left + right) // 2

            node = TreeNode(nums[mid])

            node.left = build(left, mid - 1)
            node.right = build(mid + 1, right)

            return node

        return build(0, len(nums) - 1)