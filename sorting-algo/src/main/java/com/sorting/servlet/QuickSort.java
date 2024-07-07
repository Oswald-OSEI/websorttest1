package com.sorting.servlet;

import java.util.List;
import java.util.ArrayList;

public class QuickSort {

    // Method to sort the given array using Quick Sort and return the result as a List
    public List sort(int[] array){
        
        // Create a List to store the result
        List result = new ArrayList<>();
        
        // Clone the input array to avoid modifying the original array
        int[] input = array.clone(); 
        result.add(input); // Add the input array to the result list
        
        // Start measuring the time
        double startTime = System.nanoTime();
        
        // Perform Quick Sort on the array
        quickSort(array, 0, array.length - 1);
        
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
        result.add("Quick Sort");
        
        // Return the result list
        return result;
    }
    
    // Method to perform Quick Sort on the array
    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // Partition the array and get the pivot index
            int pi = partition(array, low, high);
            
            // Recursively sort the elements before and after partition
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    // Method to partition the array around the pivot
    private int partition(int[] array, int low, int high) {
        int pivot = array[high]; // Select the last element as pivot
        int i = (low - 1); // Index of smaller element
        
        // Traverse through all elements and rearrange them based on the pivot
        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            if (array[j] < pivot) {
                i++;
                // Swap array[i] and array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        
        // Swap array[i + 1] and array[high] (or pivot)
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        
        // Return the partitioning index
        return i + 1;
    }
}
