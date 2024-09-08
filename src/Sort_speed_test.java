//This program is the java version of my python script that takes differing sorting algorithms and
//determines their speed with 5**n array length. This version does not have the graphing capability due to me
// being unfamiliar with java libraries regarding graphing.


// importing necessary libraries
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;






public class Sort_speed_test {

    //Generate an array and then shuffle it

    public static Integer[] genArray(int arrayLength) {
        //initializes a new array
        Integer[] array = new Integer[arrayLength];
        //iterates over entire length of array
        for (int i = 0; i < arrayLength; i++) {
            array[i] = i;
        }
        //puts the array into a list hence 'new ArrayList'
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(array));
        //shuffles the array
        Collections.shuffle(arrayList);
        //puts it back into the original array
        return arrayList.toArray(new Integer[0]);
    }
    //Bubble sort (bigger value moves right basically)
    public static void bubbleSort(Integer[] array){
        int n = array.length;
        // for every int i that is smaller than array length, increment the counter
        for (int i = 0; i < n - 1; i++){
            // for every int j that is smaller than int n, increment j
            for (int j = 0; j < n - 1; j++){
                // if left element is bigger than right, swap em
                if (array[j] > array[j+1]){
                    //swap elements in the array
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
    //Merge sort
    public static void mergeSort(Integer[] array){
        //implement base case that array is greater than 1!
        if (array.length > 1){
            int mid = array.length / 2;
            Integer[] left = Arrays.copyOfRange(array, 0, mid);
            Integer[] right = Arrays.copyOfRange(array, mid, array.length);

            //recursive call itself
            mergeSort(left);
            mergeSort(right);

            //call function merge (merge actually does the sorting)
            merge(array, left, right);
        }
    }

    //merge function that does the sorting and merging of the array
    //takes parameters: array, left parts of array, right parts of array that are all integers
    public static void merge(Integer[] array, Integer[] left, Integer[] right){
        //initialize these variables
        int i = 0, j = 0, k = 0;
        // while i is less than left length **AND** j is less than right length
        // note: k is for the array that contains the new sorted elements from left and right arrays
        while (i < left.length && j < right.length){
            // if index position i in left array is less than or equal to index j for right array then
            if (left[i] <= right[j]){
                //increment array index k and set it equal to left index i incremented
                array[k++] = left[i++];
            }
            else {
                //do same but set it equal to right
                array[k++] = right[j++];
            }
        }
        while (i < left.length){
            array[k++] = left[i++];
        }
        while (j < right.length){
            array[k++] = right[j++];
        }
    }

    //Quick Sort
    public static Integer[] quicksort(Integer[] array) {
        //basecase
        if (array.length < 1) {
            return array;
        }
        //quick sort works by having a 'pivot value', where numbers are sorted around it
        int pivot = array[0];
        // these are temp arrays that are defined as either having values, greater, smaller, or equal to the pivot
        ArrayList<Integer> less = new ArrayList<>();
        ArrayList<Integer> equal = new ArrayList<>();
        ArrayList<Integer> greater = new ArrayList<>();

        //iterates over indexes and determines if it is greater, smaller, or equal to pivot
        //then put it into their respective array.
        for (int x : array) {
            if (x > pivot) {
                less.add(x);
            } else if (x == pivot) {
                equal.add(x);
            } else
                greater.add(x);
        }
        //Recursively call the itself to sort
        Integer[] sortedLess = quicksort(less.toArray(new Integer[0]));
        Integer[] sortedGreater = quicksort(greater.toArray(new Integer[0]));
        return concatenate(sortedLess, equal.toArray(new Integer[0]), sortedGreater);
    }

//function that concatenates arrays for quicksort
public static Integer[] concatenate(Integer[]... arrays) {
    //create an empry list called 'result'
    ArrayList<Integer> result = new ArrayList<>();
    // for every int in the array containing arrays
    for (Integer[] array : arrays){
        // add all elements from the current array into array result
        Collections.addAll(result, array);
    }
    //return the result array
    return result.toArray(new Integer[0]);
    }
    // Measure time taken for a sorting function
    public static long measureSortTime(Runnable sortFunction) {
        long startTime = System.nanoTime();
        sortFunction.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

//Main program that actually gets executed

public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Ask for number of iterations
        System.out.print("Enter the number of iterations: ");
        int iterations = scanner.nextInt();

        // Ask if the user would like to use bubble sort since it is slow and may cause issues
        System.out.print("Would you like to use bubble sort? (Y/N) ");

        //to make sure case doesn't matter
        String B_sort_yes = scanner.next().toLowerCase();

        //main logic
        for (int i = 0; i < iterations; i++){
            //n^5 chosen due to having to stop the user for having to input a large number
            System.out.print("Enter the length of the array (n^5): ");
            int arrayLength = scanner.nextInt();

            //this gets the array's length
            arrayLength = (int) Math.pow(arrayLength, 5);

            // Generate the unsorted array
            Integer[] unsortedArray = genArray(arrayLength);
            System.out.print("The array is of length: ");
            System.out.print(arrayLength + "\n" );

            //Check if user wanted to use bubble sort and do it if the user wanted bubble sort.
            if (B_sort_yes.equals("Y")){
                Integer[] bubbleSortArray = unsortedArray.clone();
                long bubbleSortTime = measureSortTime(() -> bubbleSort(bubbleSortArray));
                System.out.printf("Bubble Sort Time: %.6f seconds\n", bubbleSortTime / 1e9);
            }
            // Measure and display Merge Sort time
            Integer[] mergeSortArray = unsortedArray.clone();
            long mergeSortTime = measureSortTime(() -> mergeSort(mergeSortArray));
            System.out.printf("Merge Sort Time: %.6f seconds\n", mergeSortTime / 1e9);


            // Measure and display Quick Sort time
            Integer[] quickSortStackArray = unsortedArray.clone();
            long quicksortTime = measureSortTime(() -> quicksort(quickSortStackArray));
            System.out.printf("Quick Sort Time: %.6f seconds\n", quicksortTime / 1e9);

            //Just to add an extra space
            System.out.println("\n");
        }
    }

}


