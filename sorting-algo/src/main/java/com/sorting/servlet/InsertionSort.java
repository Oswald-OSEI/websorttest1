package com.sorting.servlet;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort {
    
    // Method to sort the given array using Insertion Sort and return the result as a List
    public List sort(int[] array){
        
        // Start measuring the time
        double
 startTime = System.nanoTime();
        
        // Create a List to store the result
        List result = new ArrayList<>();
        
        // Clone the input array to avoid modifying the original array
        int[] input = array.clone(); 
        result.add(input); // Add the input array to the result list
        
        // Perform Insertion Sort
        for (int i = 1; i < array.length; i++) {
            int key = array[i]; // Select the element to be inserted
            int j = i - 1;
            
            // Move elements of array[0..i-1] that are greater than key to one position ahead of their current position
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key; // Place the key in its correct position
        }
        
        // Add the sorted array to the result list
        result.add(array);
        
        // End measuring the time
        double endTime = System.nanoTime();
        
        // Calculate the time taken in milliseconds
        double timeTaken = (double) (endTime - startTime) / 1_000_000.0;
        
        // Add the time taken to the result list
        result.add(timeTaken);
        
        // Add the sorting algorithm name to the result list
        result.add("Insertion Sort");

        // Return the result list
        return result;
    }
}
