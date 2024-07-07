package com.sorting.servlet;

import java.util.List;
import java.util.ArrayList;

public class RadixSort {
    
    // Method to sort the given array using Radix Sort and return the result as a List
    public List sort(int[] array){
        
        // Create a List to store the result
        List result = new ArrayList<>();
        
        // Clone the input array to avoid modifying the original array
        int[] input = array.clone(); 
        result.add(input); // Add the input array to the result list
        
        // Start measuring the time
        double startTime = System.nanoTime();
        
        // Get the maximum element in the array to determine the number of digits
        int max = getMax(array);
        
        // Perform counting sort for every digit (1, 10, 100, ...)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(array, exp);
        }
        
        // End measuring the time
        double endTime = System.nanoTime();
        
        // Calculate the time taken in milliseconds
        double timeTaken = (endTime - startTime) / 1_000_000;
        
        // Clone the sorted array
        int[] sortedArray = array.clone();
        
        // Add the sorted array to the result list
        result.add(sortedArray);
        
        // Add the time taken to the result list
        result.add(timeTaken);
        
        // Add the sorting algorithm name to the result list
        result.add("Radix Sort");
        
        // Return the result list
        return result;
    }

    // Method to get the maximum value in the array
    private int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    // Method to perform counting sort on the array based on the digit represented by exp
    private void countSort(int[] array, int exp) {
        int n = array.length; // Get the size of the array
        int[] output = new int[n]; // Output array to store sorted elements
        int[] count = new int[10]; // Count array to store count of occurrences of digits

        // Store count of occurrences of each digit
        for (int i = 0; i < n; i++) {
            count[(array[i] / exp) % 10]++;
        }

        // Change count[i] so that it contains the actual position of this digit in the output array
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        // Copy the output array to the original array so that array now contains sorted numbers according to the current digit
        for (int i = 0; i < n; i++) {
            array[i] = output[i];
        }
    }
}
