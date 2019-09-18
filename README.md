# top10Numbers

The goal:
Find the top 10 (i.e. largest 10) integer values from an input source and returns them as a list. The values should be returned in descending order, e.g. if the input is [1, 3, 9, 4] the method should return [9, 4, 3, 1].

It was compared 3 ways for solving this problem:
- using PriorityQueue with capacity = 10;
- using Stream.sorted();
- using implementation of heap alghoritm with capacity of array = 10.

I have tested on small amount of numbers (N=50) and on huge amount of numbers(N=100_000_000). And results were similar.

It is used JMH library for perfomance testing .

For N = 50 / 10_000_000
          
Heap          - 1 ms / 2052 ms

PriorityQueue - 2 ms / 3488 ms

Stream.sorted - 7 ms / N/A (with JMH (process didn't stop), but 92102 ms with time log in code)


So, the best solution is using Heap for getting top 10 integer values from stream.
