class Solution(object):
    def fullJustify(self, words, maxWidth):
        result = []
        i = 0
        n = len(words)

        while i < n:
            j = i
            letters = 0

            while j < n and letters + len(words[j]) + (j - i) <= maxWidth:
                letters += len(words[j])
                j += 1

            gaps = j - i - 1
            line = ""

            if j == n or gaps == 0:
                line = " ".join(words[i:j])
                line += " " * (maxWidth - len(line))

            else:
                totalSpaces = maxWidth - letters
                spaces = totalSpaces // gaps
                extra = totalSpaces % gaps

                for k in range(i, j - 1):
                    line += words[k]
                    line += " " * spaces

                    if extra > 0:
                        line += " "
                        extra -= 1

                line += words[j - 1]

            result.append(line)
            i = j

        return result