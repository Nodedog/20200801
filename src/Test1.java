/*
*
*
*                               排序
*
*           都以升序为例
*
* */


public class Test1 {


    //插入排序
    public static void insertSort(int[] array){
        //有序区间:[0,bound)
        //无序区间:[bound,size)
        //每次选择无序区间的第一个元素，在有序区间中选择合理的位置插入
        //bound也可以从0开始 但是更方便的就从1下标开始 和零号下标进行比较
        for (int bound = 1; bound < array.length; bound++) {
            //保存无序区间的第一个元素，防止已排序区间的元素小于无序区间的第一个元素，
            // 覆盖掉无序区间的第一个元素
            int tmp = array[bound];
            int cur = bound - 1;//已排序区间最后一个元素
            for (; cur >=0 ; cur--) {
                //已排序区间的最后一个元素如果大于无序区间的第一个元素bound的值就将
                //已排序区间的最后一个元素挪到无序区间的第一个元素位置上（往后挪一位）
                //每次跟有序区间的元素相比，只要小于有序区间的值，那么有序区间的元素就往后挪一位
                //array[cur] > tmp此时的> 如果改成>= 那么就变成不稳点排序了
                if (tmp < array[cur]){
                    array[cur + 1] = array[cur];
                }else {
                    break;
                }
            }
            //因为如果发现tmp < array[cur]已排序区间的值大于无序区间的第一个值（bound）就往后挪一位
            //会覆盖掉无序区间的第一个值，所以之前用tmp保存的现在就赋值给经过循环之后的cur+1位置
            array[cur + 1] = tmp;
        }
    }










    //希尔排序(进阶版的插排)
    //将整个数组分为若干组，分成的组数为gap，针对每个组进行插排
    public static void ShellSort(int[] array){
        //首先将array分为长度为2的小组， 一共5组
        int gap = array.length/2;
        //只要组数大于1，就进行插排，没插排一次有序性提高一点，组数减少一半（除以2）
        while (gap > 1){
            insertSortGap(array, gap);
            gap = gap/2;
        }
        //当排序到组数只剩下一组的时候，再插排一次，此时就跟插排一模一样
        insertSortGap(array, 1);
    }

    //例如array = {1,6,8,3,7,4,10,5,2} 分成三组
    //第一组：{1,3,10} 第二组：{6,7,5} 第三组：{8,4,2}
    private static void insertSortGap(int[] array, int gap) {
        //此时bound要从gap组数也就是三号下标（元素为3）开始
        for (int bound = gap; bound < array.length; bound++) {
            int tmp = array[bound];
            //cur是保存同组里面的上一个元素（比如第一组的1,3元素在array里面就是差了gap个下标）
            //所以cur = bound - gap
            int cur = bound - gap;
            //cur -= gap 在有序区间里面第一个跟bound位置比较完大小之后，
            // 再跟同组里面的第二个元素相比较，所以每次 cur -=gap
            for (; cur >= 0; cur -= gap) {
                if (array[cur] > tmp){
                    //如果同组里面有序区间的最后一个元素cur对应的元素是大于同一组无序区间的第一个元素
                    //那么就把cur挪到同一组无序区间第一个元素的位置也就是cur+gap
                    array[cur + gap] = array[cur];
                }else {
                    break;
                }
            }
            array[cur + gap] = tmp;
        }
    }










    //选择排序
    public static void selectSort(int[] array){
        for (int bound = 0; bound < array.length; bound++) {
            //此时借助bound把数组分为两个区间
            //[0，bound）为已排序区间
            //[bound，size）为待排序区间
            //接下来在待排序区间找最小值，放到bound位置上
            for (int cur = bound + 1; cur < array.length; cur++) {
                if (array[cur] < array[bound]){
                    //以bound位置元素座位擂台
                    //拿当前元素和擂台元素进行比较大小
                    //满足if条件句 当前已升序为例所以cur<bound则交换位置
                    swap(array,cur,bound);
                }
            }
        }
    }


    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }






    //冒泡排序
    public static void bubbleSort(int[] array){
        //[0,bound)已排序区间
        //[bound,size)待排序区间
        for (int bound = 0; bound < array.length; bound++) {
            //从后往前遍历，每次找最小的元素放前面
            //cur>bound而不是cur>=bound 因为当bound=0时 ，cur =0，cur-1就越界了
            for (int cur = array.length - 1; cur > bound; cur--) {
                //比较相邻两个元素如果后一个比前一个小，就互相交换
                if (array[cur - 1] > array[cur]){
                    swap(array,cur-1,cur);
                }
            }
        }
    }








    //快速排序
    public static void quickSort(int[] array){
        quickSortHelp(array,0,array.length - 1);
    }

    private static void quickSortHelp(int[] array, int left, int right) {
        //区间中有0个元素或者1个元素
        if (left >= right){
            return;
        }
        //返回值表示整理之后，基准值所处在的位置
        int index = partition(array,left,right);
        //递归访问左右区间
        //[left,index-1]
        //[index+1,right]
        quickSortHelp(array,left,index - 1);
        quickSortHelp(array,index + 1,right);
    }

    private static int partition(int[] array, int left, int right) {
        //把基准值设置为right位置
        int baseValue = array[right];
        int i = left;
        int j = right;
        while (i < j){
            //从左往右找到一个大于基准值baseValue的元素
            while (i < j && array[i] <= baseValue){
                i++;
            }
            //此时i指向的位置要么和j重合，要么就是一个比基准值大的元素

            //从右往左找到一个小于基准值baseValue的元素
            while (i < j && array[j] >= baseValue){
                j--;
            }
            //此时j指向的位置要么和i重合，要么就是一个比基准值小的元素

            //交换i j的位置
            swap(array,i,j);
        }
        //循环结束交换重合位置元素和基准值元素
        swap(array,i,right);
        //返回的基准值所在位置
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
        //下面方法执行完left，mid已经排序好了
        mergeSortHelp(array,left,mid);
        //下面方法执行mid，right已经排序好了
        mergeSortHelp(array,mid,right);
        //通过上面的递归，认为这两个区间都被排好序了，接下来进行合并
        merge(array,left,mid,right);
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int cur1 = left;
        int cur2 = mid;
        int[] output = new int[right - left];
        int outputIndex = 0;//记录当前output数组当中放入多少元素
        while (cur1 < mid && cur2 < right){
            //这里条件写成<=才能保证稳定性
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
        //当上面的循环结束时，肯定是cur1或者cur2有一个先到达末尾，另一个还剩一些元素
        //吧剩下的元素都直接拷贝到output中
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
        //吧output中的元素再搬运回原来的数组
        for (int i = 0; i < right - left; i++) {
            array[left + i] = output[i];
        }
    }


}
