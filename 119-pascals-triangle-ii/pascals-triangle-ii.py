
class Solution(object):
    def getRow(self, rowIndex):
        row = [1]

        for i in range(rowIndex):
            row.append(1)

            for j in range(i, 0, -1):
                row[j] = row[j] + row[j - 1]

        return row
