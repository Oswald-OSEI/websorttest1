package com.sorting.servlet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class BucketSort {
    public List<Object> sort(int[] array){
        List<Object> result = new ArrayList<>();
        int[] input = array.clone(); 
        result.add(input);
        
        double startTime = System.nanoTime();
        
        // Find the minimum and maximum values
        int max = array[0], min = array[0];
        for (int i : array) {
            if (i > max) max = i;
            if (i < min) min = i;
        }
        
        // Calculate the number of buckets
        int bucketCount = (max - min) / array.length + 1;
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
        
        // Distribute input array values into buckets
        for (int i : array) {
            buckets.get((i - min) / array.length).add(i);
        }
        
        // Sort each bucket and collect the sorted values
        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int i : bucket) {
                array[index++] = i;
            }
        }
        
        double endTime = System.nanoTime();
        double timeTaken = (double) (endTime - startTime) / 1_000_000.0;
        
        // Save the sorted array
        int[] sortedArray = array.clone();
        
        result.add(sortedArray);
        result.add(timeTaken);
        result.add("Bucket Sort");
        
        return result;
    }
}
