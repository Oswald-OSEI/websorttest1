package com.sorting.servlet;
import java.util.List;
import java.util.ArrayList;

public class HeapSort {

    // Method to sort the given array using Heap Sort and return the result as a List
    public List sort(int[] array){
        
        // Create a List to store the result
        List result = new ArrayList<>();
        
        // Clone the input array to avoid modifying the original array
        int[] input = array.clone(); 
        result.add(input); // Add the input array to the result list
        
        // Start measuring the time
        double startTime = System.nanoTime();
        
        int n = array.length; // Get the length of the array
        
        // Build the heap (rearrange the array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
        
        // Extract elements from the heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to the end
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            
            // Call max heapify on the reduced heap
            heapify(array, i, 0);
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
        result.add("Heap Sort");
        
        // Return the result list
        return result;
    }

    // Method to heapify a subtree rooted with node i which is an index in array[]
    // n is the size of the heap
    private void heapify(int[] array, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2
        
        // If left child is larger than root
        if (left < n && array[left] > array[largest]) {
            largest = left;
        }
        
        // If right child is larger than the largest so far
        if (right < n && array[right] > array[largest]) {
            largest = right;
        }
        
        // If largest is not root
        if (largest != i) {
            // Swap array[i] with array[largest]
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            
            // Recursively heapify the affected sub-tree
            heapify(array, n, largest);
        }
    }
}
