package com.sorting.servlet;

import java.util.List;
import java.util.ArrayList;

public class ShellSort {
    
    // Method to sort the given array using Shell Sort and return the result as a List
    public List sort(int[] array){
        
        // Create a List to store the result
        List result = new ArrayList<>();
        
        // Clone the input array to avoid modifying the original array
        int[] input = array.clone(); 
        result.add(input); // Add the input array to the result list
        
        // Start measuring the time
        double startTime = System.nanoTime();
        
        int n = array.length;
        
        // Start with a large gap, then reduce the gap
        for (int gap = n / 2; gap > 0; gap /= 2) {
            
            // Perform a gapped insertion sort for this gap size
            for (int i = gap; i < n; i++) {
                
                // Save array[i] in temp and make a hole at position i
                int temp = array[i];
                int j;
                
                // Shift earlier gap-sorted elements up until the correct location for array[i] is found
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }
                
                // Put temp (the original array[i]) in its correct location
                array[j] = temp;
            }
        }
        
        // End measuring the time
        double endTime = System.nanoTime();
        
        // Calculate the time taken in milliseconds
       double timeTaken = (double) (endTime - startTime) / 1_000_000.0;
        
        // Clone the sorted array
        int[] sortedArray = array.clone();
        
        // Add the sorted array to the result list
        result.add(sortedArray);
        
        // Add the time taken to the result list
        result.add(timeTaken);
        
        // Add the sorting algorithm name to the result list
        result.add("Shell Sort");
        
        // Return the result list
        return result;
    }
}
