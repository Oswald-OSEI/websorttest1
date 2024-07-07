package com.sorting.servlet;

import java.util.List;
import java.util.ArrayList;

public class MergeSort {
    
    // Method to sort the given array using Merge Sort and return the result as a List
    public List sort(int[] array){
        
        // Create a List to store the result
        List result = new ArrayList<>();
        
        // Clone the input array to avoid modifying the original array
        int[] input = array.clone();
        result.add(input); // Add the input array to the result list
        
        // Start measuring the time
        double startTime = System.nanoTime();
        
        // Perform Merge Sort on the array
        mergeSort(array, 0, array.length - 1);
        
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
        result.add("Merge Sort");

        // Return the result list
        return result;
    }

    // Method to recursively divide the array and merge the sorted parts
    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2; // Find the middle point
            
            // Recursively sort the first half
            mergeSort(array, left, mid);
            
            // Recursively sort the second half
            mergeSort(array, mid + 1, right);
            
            // Merge the sorted halves
            merge(array, left, mid, right);
        }
    }

    // Method to merge two subarrays of array[]
    private void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1; // Size of the first subarray
        int n2 = right - mid;    // Size of the second subarray
        
        // Temporary arrays to hold the subarrays
        int[] L = new int[n1];
        int[] R = new int[n2];
        
        // Copy data to temporary arrays L[] and R[]
        System.arraycopy(array, left, L, 0, n1);
        System.arraycopy(array, mid + 1, R, 0, n2);
        
        int i = 0, j = 0;
        int k = left;
        
        // Merge the temporary arrays back into the original array
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }
        
        // Copy any remaining elements of L[]
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }
        
        // Copy any remaining elements of R[]
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }
}
