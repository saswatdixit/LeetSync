class Solution(object):
    def removeInvalidParentheses(self, s):
        def is_valid(s):
            balance = 0

            for ch in s:
                if ch == '(':
                    balance += 1
                elif ch == ')':
                    balance -= 1

                    if balance < 0:
                        return False

            return balance == 0

        result = []
        queue = {s}
        found = False

        while queue and not found:
            next_level = set()

            for current in queue:
                if is_valid(current):
                    result.append(current)
                    found = True

            if found:
                break

            for current in queue:
                for i in range(len(current)):
                    if current[i] not in "()":
                        continue

                    next_string = current[:i] + current[i + 1:]
                    next_level.add(next_string)

            queue = next_level

        return result