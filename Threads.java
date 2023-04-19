import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;

public class Threads {
    private static final int ARRAY_SIZE = 200000000;
    private static final int NUM_THREADS = 4;
    private static final int RANGE = 10;


    private static int parallelSum(int[] arr) throws InterruptedException {
        int sum = 0;
        int[][] subArrays = splitArray(arr, NUM_THREADS);

        SumThread[] threads = new SumThread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new SumThread(subArrays[i]);
            threads[i].start();
        }

        return sum;
    }
    private static int singleThreadSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }
    private static int[][] splitArray(int[] arr, int numParts) {
        int[][] subArrays = new int[numParts][];
        int chunkSize = arr.length / numParts;
        int leftover = arr.length % numParts;
        int index = 0;

        for (int i = 0; i < numParts; i++) {
            int size = chunkSize;
            if (i < leftover) {
                size++;
            }
            subArrays[i] = new int[size];
            System.arraycopy(arr, index, subArrays[i], 0, size);
            index += size;
        }

        return subArrays;
    }

    private static class SumThread extends Thread {
        private int[] arr;
        private int sum = 0;

        public SumThread(int[] arr) {
            this.arr = arr;
        }

        public void run() {
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
            }
        }
    }
}




