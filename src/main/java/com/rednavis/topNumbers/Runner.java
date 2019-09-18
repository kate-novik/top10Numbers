package com.rednavis.topNumbers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Launcher for getting top 10 numbers from stream by using different ways.
 *
 * @author Kate Novik
 */
public class Runner {
    public static void main(String[] args) {

        //Get stream with random values
        Integer[] numbers =  new Random().ints(50,1,50).boxed().toArray(Integer[]::new);

        System.out.println("Check time of executing getTopNByPriorityQueue function:");
        long startTime1 = System.currentTimeMillis();
        List<Integer> top10Numbers_1 = getTopNByPriorityQueue(Stream.of(numbers));
        long diffTime1 = System.currentTimeMillis() - startTime1;
        System.out.printf("Score of getting top 10 numbers by PriorityQueue (ms): %d\n", diffTime1 );
        printList(top10Numbers_1);

        System.out.println("Check time of executing getTopNByMinHeap function:");
        long startTime2 = System.currentTimeMillis();
        List<Integer> top10Numbers_2 = getTopNByMinHeap(Stream.of(numbers));
        long diffTime2 = System.currentTimeMillis() - startTime2;
        System.out.printf("Score of getting top 10 numbers by Max Heap (ms): %d\n", diffTime2);
        printList(top10Numbers_2);

        System.out.println("Check time of executing getTopNBySortedStream function:");
        long startTime3 = System.currentTimeMillis();
        List<Integer> top10Numbers_3 = getTopNBySortedStream(Stream.of(numbers));
        long diffTime3= System.currentTimeMillis() - startTime3;
        System.out.printf("Score of getting top 10 numbers by sorted Stream (ms): %d\n", diffTime3);
        printList(top10Numbers_3);
    }

    /**
     * Get top 10 numbers from stream by using PriorityQueue.
     *
     * @param input input stream of numbers
     * @return list of Integer values
     */
    public static List<Integer> getTopNByPriorityQueue (Stream<Integer> input) {

        //Create queueInt with ascending order of elements, ec. min element is in the top of Priority Queue
        PriorityQueue<Integer> queueInt = new PriorityQueue<>(10);

        input.forEach(num -> {
                        if (queueInt.size() < 10) {
                            queueInt.add(num);
                        } else {
                            if (queueInt.peek() < num) {
                                queueInt.poll();
                                queueInt.add(num);
                            }
                      }});
        Integer[] arr = new Integer[10];
        for (int i = 9; i >= 0; i--) {
            if (queueInt.size() > 0) {
                arr[i] = queueInt.poll();
            } else {
                break;
            }
        }
        return Arrays.asList(arr);
    }

    /**
     * Get top 10 numbers from stream by using binary max heap.
     *
     * @param input input stream of numbers
     * @return list of Integer values
     */
    public static List<Integer> getTopNByMinHeap(Stream<Integer> input) {

        int[] array = new int[10];
        MinHeap heap = new MinHeap(array, 10);
        input.forEach(num -> {
            if (heap.getHeapSize() < 10) {
                heap.add(num);
            } else {
                if (heap.getMinNumber() < num) {
                    heap.replaceMinNumber(num);
                }
            }});
        Integer[] arr = new Integer[10];
        for (int i = 9; i >= 0; i--) {
            if (heap.getHeapSize() > 0) {
                arr[i] = heap.removeMinNumber();
            } else {
                break;
            }
        }
        return Arrays.asList(arr);
    }

    /**
     * Get top 10 numbers from stream by using sorting from stream.
     *
     * @param input input stream of numbers
     * @return list of Integer values
     */
    public static List<Integer> getTopNBySortedStream (Stream<Integer> input) {
        return input.sorted(Comparator.reverseOrder()).limit(10).collect(Collectors.toList());

    }

    private static void printList (List<Integer> list) {
        for (int i = 0; i < 10; i++) {
            System.out.println(list.get(i));
        }
    }
}
