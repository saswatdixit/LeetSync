class Solution(object):
    def insert(self, intervals, newInterval):
        result = []
        i = 0
        n = len(intervals)

        start, end = newInterval

        # Add intervals before newInterval
        while i < n and intervals[i][1] < start:
            result.append(intervals[i])
            i += 1

        # Merge overlapping intervals
        while i < n and intervals[i][0] <= end:
            start = min(start, intervals[i][0])
            end = max(end, intervals[i][1])
            i += 1

        result.append([start, end])

        # Add remaining intervals
        while i < n:
            result.append(intervals[i])
            i += 1

        return result