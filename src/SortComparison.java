import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public class SortComparison {

    // Generate an array and shuffle it
    public static Integer[] genArray(int arrayLength) {
        Integer[] array = new Integer[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = i;
        }
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(array));
        Collections.shuffle(arrayList);
        return arrayList.toArray(new Integer[0]);
    }

    // Bubble Sort
    public static void bubbleSort(Integer[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap elements
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Merge Sort
    public static void mergeSort(Integer[] array) {
        if (array.length > 1) {
            int mid = array.length / 2;
            Integer[] left = Arrays.copyOfRange(array, 0, mid);
            Integer[] right = Arrays.copyOfRange(array, mid, array.length);

            mergeSort(left);
            mergeSort(right);

            merge(array, left, right);
        }
    }

    // Merge helper function for merge sort
    public static void merge(Integer[] array, Integer[] left, Integer[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    // Quick Sort (GPT version)
    public static Integer[] quickSortGPT(Integer[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int pivot = arr[arr.length / 2];
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> middle = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        for (int x : arr) {
            if (x < pivot) {
                left.add(x);
            } else if (x == pivot) {
                middle.add(x);
            } else {
                right.add(x);
            }
        }
        Integer[] sortedLeft = quickSortGPT(left.toArray(new Integer[0]));
        Integer[] sortedRight = quickSortGPT(right.toArray(new Integer[0]));
        return concatenate(sortedLeft, middle.toArray(new Integer[0]), sortedRight);
    }

    // Quick Sort (StackOverflow version)
    public static Integer[] quickSortStack1(Integer[] array) {
        if (array.length <= 1) {
            return array;
        }
        int pivot = array[0];
        ArrayList<Integer> less = new ArrayList<>();
        ArrayList<Integer> equal = new ArrayList<>();
        ArrayList<Integer> greater = new ArrayList<>();

        for (int x : array) {
            if (x < pivot) {
                less.add(x);
            } else if (x == pivot) {
                equal.add(x);
            } else {
                greater.add(x);
            }
        }
        Integer[] sortedLess = quickSortStack1(less.toArray(new Integer[0]));
        Integer[] sortedGreater = quickSortStack1(greater.toArray(new Integer[0]));
        return concatenate(sortedLess, equal.toArray(new Integer[0]), sortedGreater);
    }

    // Concatenate arrays for quicksort
    public static Integer[] concatenate(Integer[]... arrays) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Integer[] array : arrays) {
            Collections.addAll(result, array);
        }
        return result.toArray(new Integer[0]);
    }

    // Measure time taken for a sorting function
    public static long measureSortTime(Runnable sortFunction) {
        long startTime = System.nanoTime();
        sortFunction.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Main program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for number of iterations
        System.out.print("Enter number of iterations: ");
        int iterations = scanner.nextInt();

        // Ask if user wants to use bubble sort
        System.out.print("Would you like to use bubble sort? (Y/N): ");
        String B_sort_yes = scanner.next().toUpperCase();

        for (int i = 0; i < iterations; i++) {
            System.out.print("Enter the length of the array (n^5): ");
            int arrayLength = scanner.nextInt();
            arrayLength = (int) Math.pow(arrayLength, 5);  // Calculate array length

            // Generate the unsorted array
            Integer[] unsortedArray = genArray(arrayLength);
            System.out.println(arrayLength);

            // Measure and display Bubble Sort time
            if (B_sort_yes.equals("Y")) {
                Integer[] bubbleSortArray = unsortedArray.clone();
                long bubbleSortTime = measureSortTime(() -> bubbleSort(bubbleSortArray));
                System.out.printf("Bubble Sort Time: %.6f seconds\n", bubbleSortTime / 1e9);
            }

            // Measure and display Merge Sort time
            Integer[] mergeSortArray = unsortedArray.clone();
            long mergeSortTime = measureSortTime(() -> mergeSort(mergeSortArray));
            System.out.printf("Merge Sort Time: %.6f seconds\n", mergeSortTime / 1e9);

            // Measure and display Quick Sort GPT time
            Integer[] quickSortGPTArray = unsortedArray.clone();
            long quickSortGPTTime = measureSortTime(() -> quickSortGPT(quickSortGPTArray));
            System.out.printf("Quick Sort GPT Time: %.6f seconds\n", quickSortGPTTime / 1e9);

            // Measure and display Quick Sort StackOverflow time
            Integer[] quickSortStackArray = unsortedArray.clone();
            long quickSortStackTime = measureSortTime(() -> quickSortStack1(quickSortStackArray));
            System.out.printf("Quick Sort StackOverflow Time: %.6f seconds\n", quickSortStackTime / 1e9);

            System.out.println();
        }

        scanner.close();
    }
}
