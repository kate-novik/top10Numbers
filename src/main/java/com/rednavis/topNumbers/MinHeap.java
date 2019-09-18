package com.rednavis.topNumbers;

/**
 * Implementation of minimum binary heap for int values.
 *
 * @author Kate Novik
 */
public class MinHeap {

    private int[] heap;
    //current size of heap
    private int size;
    //max size of heap
    private int capacity;

    public MinHeap(int[] array, int capacity) {
        this.capacity = ++capacity;
        this.heap = new int[this.capacity];
        heap[0] = Integer.MIN_VALUE;
        for (int value : array) {
            add(value);
        }
    }

    /**
     * Get min element in the heap.
     *
     * @return min element in the heap
     */
    public int getMinNumber() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty!");
        }
        return heap[0];
    }

    /**
     * Remove min element from the heap.
     *
     * @return min element in the heap
     */
    public int removeMinNumber() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty!");
        }
        int minNumber = heap[0];
        heap[0] = heap[--size];
        heap[size] = 0;
        sinkDown(0);
        return minNumber;
    }

    /**
     * Replace min element from the heap on input element.
     *
     * @param  num element for adding in the hip
     * @return min element in the heap
     */
    public void replaceMinNumber(int num) {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty!");
        }
        heap[0] = num;
        sinkDown(0);
    }

    /**
     * Add a new element in the heap.
     *
     * @param value element for adding
     */
    public void add(int value) {
        if (size < capacity) {
            size++;
            int pos = size;
            heap[pos] = value;
            sinkUp(pos);
        } else {
            throw new IllegalStateException("Heap exceeded!");
        }
    }

    /**
     * Balancing heap for moving min element up.
     *
     * @param pos index of element in the heap
     */
    private void sinkUp(int pos) {

        int currentChildPos = pos;
        int parentNodePos = getParentNode(pos);

        while (currentChildPos > 0 && heap[parentNodePos] > heap[currentChildPos]) {

            swap(currentChildPos,parentNodePos);
            currentChildPos = parentNodePos;
            parentNodePos = getParentNode(parentNodePos)/2;
        }

    }

    /**
     * Balancing heap for moving max element down.
     *
     * @param pos index of element in heap
     */
    private void sinkDown(int pos) {
        int minNum = pos;
        int leftLeafNode = getLeftChildLeaf(pos);
        int rightLeafNode = getRightChildLeaf(pos);
        if (leftLeafNode < getHeapSize() && heap[minNum] > heap[leftLeafNode]) {
            minNum = leftLeafNode;
        }
        if (rightLeafNode < getHeapSize() && heap[minNum] > heap[rightLeafNode]) {
            minNum = rightLeafNode;
        }
        if (minNum != pos) {
            swap(pos, minNum);
            sinkDown(minNum);
        }
    }

    private void swap(int a, int b) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    private int getParentNode(int pos) {
        return pos/2;
    }

    private int getLeftChildLeaf(int pos) {
        return 2 * pos;
    }

    private int getRightChildLeaf(int pos) {
        return 2 * pos + 1;
    }

    public int getHeapSize() {
        return this.size;
    }

}
