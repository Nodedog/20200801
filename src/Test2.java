import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;

import java.util.Arrays;

public class Test2 {
    public static void insertSort(int[] array){
        for (int bound = 1; bound < array.length; bound++) {
            int tmp = array[bound];
            int cur = bound - 1;
            for (; cur >= 0; cur--) {
                if (array[cur] > tmp){
                    array[cur + 1] = array[cur];
                }else {
                    break;
                }
            }
            array[cur + 1] = tmp;
        }
    }




    public static void ShellSort(int[] array){
        int gap = array.length/2;
        while (gap > 1){
            insertSortGap(array, gap);
            gap = gap/2;
        }
        insertSortGap(array, 1);
    }

    private static void insertSortGap(int[] array, int gap) {
        for (int bound = gap; bound < array.length; bound++) {
            int tmp = array[bound];
            int cur = bound - gap;
            for (; cur >= 0; cur -= gap) {
                if (array[cur] > tmp){
                    array[cur + gap] = array[cur];
                }else {
                    break;
                }
            }
            array[cur + gap] = tmp;
        }

    }




    public static void selectSort(int[] array){
        for (int bound = 0; bound < array.length; bound++) {
            for (int cur = bound + 1; cur < array.length; cur++) {
                if (array[cur] < array[bound]){
                    int tmp = array[cur];
                    array[cur] = array[bound];
                    array[bound] = tmp;
                }
            }
        }
    }


    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


    public static void bubbleSort(int[] array){
        for (int bound = 0; bound < array.length; bound++) {
            for (int cur = array.length - 1; cur > bound ; cur--) {
                if (array[cur - 1] > array[cur]){
                    swap(array,cur-1,cur);
                }
            }
        }
    }






    //快速排序
    public static void quickSort(int[] array) {
        quickSortHelp(array,0,array.length - 1);
    }

    private static void quickSortHelp(int[] array, int left, int right) {
        if (left >= right){
            return;
        }
        int index = partition(array,left,right);
        quickSortHelp(array,left,index-1);
        quickSortHelp(array,index+1,right);
    }

    private static int partition(int[] array, int left, int right) {
        int baseValue = array[right];
        int i = left;
        int j = right;
        while (i < j){
            while (i < j && array[i] <= baseValue){
                i++;
            }
            while (i < j && array[i] >= baseValue){
                j--;
            }
            swap(array,i,j);
        }
        swap(array,i,right);
        return i;
    }




    //归并排序
    public static void mergeSort(int[] array) {
        //[0,length)
        mergeSortHelp(array,0,array.length);
    }

    private static void mergeSortHelp(int[] array, int left, int right) {
        if (right - left <= 1){
            return;
        }
        int mid = (left + right) / 2;
        mergeSortHelp(array,left,mid);
        mergeSortHelp(array,mid,right);
        merge(array,left,mid,right);
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int cur1 = left;
        int cur2 = mid;
        int[] output = new int[right - left];
        int outputIndex = 0;
        while (cur1 < mid && cur2 < right){
            if (array[cur1] <= array[cur2]){
                output[outputIndex] = array[cur1];
                cur1++;
                outputIndex++;
            }else {
                output[outputIndex] = array[cur2];
                cur2++;
                outputIndex++;
            }
        }

        while (cur1 < mid) {
            output[outputIndex] = array[cur1];
            cur1++;
            outputIndex++;
        }
        while (cur2 < right){
            output[outputIndex] = array[cur2];
            cur2++;
            outputIndex++;
        }
        for (int i = 0; i < right - left; i++) {
            array[left + i] = output[i];
        }
    }


    public static void main(String[] args) {
        int[] array = {5,7,3,9,44,2};
        mergeSort(array);
        System.out.println(Arrays.toString(array));
    }
}
