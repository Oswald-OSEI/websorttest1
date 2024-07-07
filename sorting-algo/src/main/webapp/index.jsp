<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sort Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            text-align: center;
        }
        .upper, .lower {
            margin: 20px;
        }
        h1 {
            color: #0073e6;
        }
        form {
            display: inline-block;
            text-align: left;
        }
        label {
            display: block;
            margin: 10px 0 5px;
            color: #555;
        }
        input[type="text"], select {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        button {
            padding: 10px 20px;
            background-color: #0073e6;
            border: none;
            border-radius: 5px;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #005bb5;
        }
        .results {
            margin-top: 40px;
            padding: 20px;
            background-color: #e6f7ff;
            border-radius: 10px;
            text-align: left;
        }
        .results p {
            margin: 10px 0;
            font-size: 18px;
        }
        .results p strong {
            color: #0073e6;
        }
        .buttons {
            margin-top: 20px;
        }
        .buttons button {
            margin: 0 10px;
        }
        .error {
            color: red;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="upper">
            <h1>Enter an Array to Sort</h1>
            <form action="${pageContext.request.contextPath}/sort" method="post">
                <label for="array">Array (comma-separated values):</label>
                <input type="text" id="array" name="array" required>
                <label for="sortType">Choose a sort type:</label>
                <select id="sortType" name="sortType" required>
                    <option value="InsertionSort">Insertion Sort</option>
                    <option value="ShellSort">Shell Sort</option>
                    <option value="MergeSort">Merge Sort</option>
                    <option value="QuickSort">Quick Sort</option>
                    <option value="HeapSort">Heap Sort</option>
                    <option value="RadixSort">Radix Sort</option>
                    <option value="BucketSort">Bucket Sort</option>
                </select>
                <button type="submit">Sort</button>
            </form>
            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>
        </div>
        <div class="lower">
            <h1>Sort Result</h1>
            <c:if test="${not empty sortType}">
                <div class="results">
                    <p><strong>Sort Type:</strong> ${sortType}</p>
                    <p><strong>Time Taken (milliseconds):</strong> ${timeTaken}</p>
                    <p><strong>Input Array:</strong> ${inputArray}</p>
                    <p><strong>Sorted Array:</strong> ${sortedArray}</p>
                </div>
                <div class="buttons">
                    <button onclick="location.href='${pageContext.request.contextPath}/sort?action=previous'">Previous</button>
                    <button onclick="location.href='${pageContext.request.contextPath}/sort?action=next'">Next</button>
                </div>
            </c:if>
        </div>
    </div>
</body>
</html>
