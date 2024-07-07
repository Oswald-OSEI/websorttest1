package com.sorting.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/") // Annotation to define the URL pattern for this servlet
public class SortsController extends HttpServlet {
    // LinkedList to store the history of sorting operations
    public LinkedList<List> sortHistory = new LinkedList<>();
    // Variable to track the position in the sort history
    public int accessHistory = -1;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve the input array and sort type from the request
            String inputArray = request.getParameter("array");
            String sortType = request.getParameter("sortType");

            // Check if inputArray and sortType are not null or empty
            if (inputArray != null && !inputArray.isEmpty() && sortType != null) {
                // Split the input array string into individual numbers and parse them to integers
                String[] stringArray = inputArray.split(",");
                int[] intArray = Arrays.stream(stringArray).mapToInt(Integer::parseInt).toArray();

                // Variable to store the sorted result
                List sortedArray = null;

                // Perform the sorting based on the sort type selected
                switch (sortType) {
                    case "InsertionSort":
                        sortedArray = new InsertionSort().sort(intArray);
                        sortHistory.addFirst(sortedArray); // Add the result to the history
                        break;
                    case "ShellSort":
                        sortedArray = new ShellSort().sort(intArray);
                        sortHistory.addFirst(sortedArray);
                        break;
                    case "MergeSort":
                        sortedArray = new MergeSort().sort(intArray);
                        sortHistory.addFirst(sortedArray);
                        break;
                    case "QuickSort":
                        sortedArray = new QuickSort().sort(intArray);
                        sortHistory.addFirst(sortedArray);
                        break;
                    case "HeapSort":
                        sortedArray = new HeapSort().sort(intArray);
                        sortHistory.addFirst(sortedArray);
                        break;
                    case "RadixSort":
                        sortedArray = new RadixSort().sort(intArray);
                        sortHistory.addFirst(sortedArray);
                        break;
                    case "BucketSort":
                        sortedArray = new BucketSort().sort(intArray);
                        sortHistory.addFirst(sortedArray);
                        break;
                }

                // If sorting was successful, set attributes and forward to the JSP page
                if (sortedArray != null) {
                    accessHistory = 0;

                    request.setAttribute("sortType", sortedArray.get(3));
                    request.setAttribute("timeTaken", sortedArray.get(2));
                    request.setAttribute("sortedArray", Arrays.toString((int[]) sortedArray.get(1)));
                    request.setAttribute("inputArray", Arrays.toString((int[]) sortedArray.get(0)));
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } else {
                // If input array or sort type is invalid, set error message and forward to the JSP page
                request.setAttribute("error", "Please enter a valid array and select a sort type.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            // Handle number format exception (e.g., invalid integer input)
            request.setAttribute("error", "Invalid input array format. Please enter integers separated by commas.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle other unexpected exceptions
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve the action parameter from the request
            String action = request.getParameter("action");

            if (action != null) {
                // Navigate through the sort history based on the action parameter
                if (action.equals("previous") && accessHistory < sortHistory.size() - 1) {
                    accessHistory++;
                } else if (action.equals("next") && accessHistory > 0) {
                    accessHistory--;
                }

                // If the accessHistory index is valid, set attributes and forward to the JSP page
                if (accessHistory >= 0 && accessHistory < sortHistory.size()) {
                    List result = sortHistory.get(accessHistory);
                    request.setAttribute("sortType", result.get(3));
                    request.setAttribute("timeTaken", result.get(2));
                    request.setAttribute("inputArray", Arrays.toString((int[]) result.get(0)));
                    request.setAttribute("sortedArray", Arrays.toString((int[]) result.get(1)));
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            // Handle unexpected exceptions in doGet
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
