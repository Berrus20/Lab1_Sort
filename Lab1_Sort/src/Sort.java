import java.util.Arrays;
import java.time.Duration;
import java.time.Instant;

public class Sort {
    public static void main(String[] args) {

        //Сортировка обменом
        Instant startEx = Instant.now();
        System.out.println("Сортировка обменом");
        int[][] arrayEx = new int[1000][1000];
        rand(arrayEx);
        sortMatrixEx(arrayEx);
        Instant finishEx = Instant.now();
        long elapsed = Duration.between(startEx, finishEx).toMillis();
        System.out.println("Время сортировки, мс: " + elapsed);
        System.out.println("--------------------");

        //Быстрая сортировка
        Instant startQ = Instant.now();
        System.out.println("Быстрая сортировка");
        int[][] arrayQ = new int[1000][1000];
        rand(arrayQ);
        sortMatrixQ(arrayQ);
        Instant finishQ = Instant.now();
        long elapsed2 = Duration.between(startQ, finishQ).toMillis();
        System.out.println("Время сортировки, мс: " + elapsed2);
        System.out.println("-------------------");

        //Стандартная функция сортировки Java
        Instant start = Instant.now();
        System.out.println("Стандартная функция сортировки Java");
        int[][] arraySt = new int[1000][1000];
        rand(arraySt);
        sortMatrixSt(arraySt);
        Instant finish = Instant.now();
        long elapsed3 = Duration.between(start, finish).toMillis();
        System.out.println("Время сортировки, мс: " + elapsed3);
    }

    public static int[][] sortMatrixEx(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            exchangeSort(matrix[i]);
        }
        return matrix;
    }

    public static int[][] sortMatrixQ(int[][] matrix) {
        int low = 0;
        int high = matrix[0].length - 1;
        for (int i = 0; i < matrix.length; i++) {
            quickSort(matrix[i], low, high);
        }
        return matrix;
    }

    public static int[][] sortMatrixSt(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            Arrays.sort(matrix[i]);
        }
        return matrix;
    }

    //Сортировка обменом
    private static void swap(int[] array, int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }

    public static int[] exchangeSort(int[] arr) {
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < arr[i - 1]) {
                    swap(arr, i, i-1);
                    needIteration = true;
                }
            }
        }
        return arr;
    }

    //Быстрая сортировка
    public static void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;                 //завершить выполнение если длина массива равна 0

        if (low >= high)
            return;                 //завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int prop = array[middle];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < prop) {
                i++;
            }

            while (array[j] > prop) {
                j--;
            }

            //меняем местами
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }

    //заполнение матрицы рандомными значениями
    public static void rand (int[][] matrix) {
        for (int i=0; i<1000; i++) {
            for (int j=0; j<1000; j++) {
                matrix[i][j] = ((int) (Math.random()*30)-15);
            }
        }
    }
}