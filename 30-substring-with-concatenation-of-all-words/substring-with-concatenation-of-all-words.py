class Solution(object):
    def findSubstring(self, s, words):
        if not s or not words:
            return []

        wordLen = len(words[0])
        wordCount = len(words)
        totalLen = wordLen * wordCount

        count = {}
        for word in words:
            count[word] = count.get(word, 0) + 1

        result = []

        for start in range(wordLen):
            left = start
            right = start
            current = {}
            used = 0

            while right + wordLen <= len(s):
                word = s[right:right + wordLen]
                right += wordLen

                if word in count:
                    current[word] = current.get(word, 0) + 1
                    used += 1

                    while current[word] > count[word]:
                        leftWord = s[left:left + wordLen]
                        current[leftWord] -= 1
                        left += wordLen
                        used -= 1

                    if used == wordCount:
                        result.append(left)

                        leftWord = s[left:left + wordLen]
                        current[leftWord] -= 1
                        left += wordLen
                        used -= 1

                else:
                    current = {}
                    used = 0
                    left = right

        return result