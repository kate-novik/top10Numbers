package com.rednavis.draft;

/**
 * @author Kate Novik
 */

public class MaxHeap {
    private int[] heap;
    //current size of heap
    private int size;
    //max size of heap
    private int capacity;

    public MaxHeap(int[] array) {
        if (array.length != 0) {
            this.capacity = array.length + 1;
            this.heap = new int[capacity];
            heap[0] = Integer.MIN_VALUE;
            for (int i = 0; i < array.length ; i++) {
                add(array[i]);
            }
        }
    }

    /**
     * Get max element in the heap with removing it from the heap.
     *
     * @return max element in the heap
     */
    public int getMaxNumber() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty!");
        }
        int maxNumber = heap[0];
        heap[0] = heap[size];
        heap[size] = 0;
        sinkDown(0);
        size--;
        return maxNumber;
    }

    /**
     * Add a new element in the heap.
     *
     * @param value element for adding
     */
    private void add(int value) {
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
     * Balancing heap for moving max element up.
     *
     * @param pos index of element in heap
     */
    private void sinkUp(int pos) {

        int currentChildPos = pos;
        int parentNodePos = getParentNode(pos);

        while (currentChildPos > 0 && heap[parentNodePos] < heap[currentChildPos]) {

            swap(currentChildPos,parentNodePos);
            currentChildPos = parentNodePos;
            parentNodePos = getParentNode(parentNodePos);
        }

    }

    /**
     * Balancing heap for moving min element down.
     *
     * @param pos index of element in heap
     */
    private void sinkDown(int pos) {
        int maxNum = pos;
        int leftLeafNode = getLeftChildLeaf(pos);
        int rightLeafNode = getRightChildLeaf(pos);
        if (leftLeafNode < getHeapSize() && heap[maxNum] < heap[leftLeafNode]) {
            maxNum = leftLeafNode;
        }
        if (rightLeafNode < getHeapSize() && heap[maxNum] < heap[rightLeafNode]) {
            maxNum = rightLeafNode;
        }
        if (maxNum != pos) {
            swap(pos, maxNum);
            sinkDown(maxNum);
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

    private int getHeapSize() {
        return this.size;
    }
}
