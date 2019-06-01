package com.shilei.algo.sorts;


/**
 * @Description: 快速排序
 * 快排时间复杂度为：O(nlogn),最坏时间复杂度是O(n^2)，属于不稳定排序
 * @Author: shilei
 * @Date: 2019/5/23 9:18
 **/
public class QuickSort {

    static  int counting = 0;


    public static void quickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        // 先找分区点
        int p = partition(a, l, r);
        // 对分区左边的元素排序
        quickSort(a, l, p - 1);
        // 对分区右边的元素排序
        quickSort(a, p + 1, r);
    }

    /**
     * 分区
     *
     * @param a 数组
     * @param l 左边索引
     * @param r 右边索引
     * @return 返回分区点的下标
     */
    public static int partition(int[] a, int l, int r) {
        if (l >= r) {
            return l;
        }
        int pivot = a[r];
        int i = l, tmp;
//        while(i <= r && a[i] < pivot){//有序度和逆序度之和等于总的比较次数，一个组数据的总比较次数是固定的
//            counting++;
//            i++;
//        }
//        System.out.println("i:"+i);
        for (int j = i; j < r; j++) {
            counting++;
            if (a[j] < pivot) {// i下标左边的节点值都小于pivot，当找到一个a[j]<pivot时则交换i,j位置的元素，然后i位置后移，则i左边的元素都小于pivot
                tmp = a[j];
                a[j] = a[i];
                a[i] = tmp;
                System.out.println("l:" + l + ",r:" + r + ",i:" + i + ",j:" + j);
                i++;
            }
        }
        a[r] = a[i];
        a[i] = pivot;
        return i;
    }

    public static void main(String[] args) {
        int[] a = {4, 1, 9, 5, 3, 7, 6, 3, 4};
        quickSort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println();
        System.out.println("counting:"+counting);
    }



}
