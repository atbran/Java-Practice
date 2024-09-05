//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

public static Interger[] genArray(int arrayLength){
    Integer[] array = new Integer[arrrayLength];
    for (int i = 0; i < arrayLength; i++) {
        array[i] = i;
    }
    ArrayList<integer> arrayList = new ArrayList<>(Arrays.asList(array));
    Collections.shuffle(arrayList);
    return arrayList.toArray(new Integer[0]);
}

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






public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        system.out.print("Enter number of iterations: ");
        int iterations = scanner.nextInt();
        }
    }
