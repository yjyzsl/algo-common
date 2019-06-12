package com.shilei.algo.base.sort;

/**
 * @Description: 冒泡排序、插入排序、选择排序
 * @Author: shilei
 * @Date: 2019/5/22 19:33
 **/
public class Sorts {


    /**
     * 冒泡排序
     * @param a
     */
    public static void bubbleSort(int[] a){
        if(a == null || a.length == 0){
            return ;
        }
        boolean flag = false;
        int n = a.length,tmp;
        for(int i=0; i<n; i++){
            flag = false;
            for(int j=0; j<(n-i-1); j++){
                if(a[j] > a[j+1]){
                    tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;
                }
            }
            if(!flag){
                System.out.println(i);
                break;
            }
        }
    }

    /**
     * 冒泡排序优化：冒泡一次记录最后一次交换位置lastExchage，lastExchage位置之后的元素为有序
     * 下一次冒泡则只遍历到lastExchage位置
     * @param a
     */
    public static void bubbleSort2(int[] a){
        if(a == null || a.length == 0){
            return ;
        }
        boolean flag = false;
        int n = a.length,tmp,lastExchage=0,sortBorder=n-1;
        for(int i=0; i<n; i++){
            flag = false;
            for(int j=0; j<sortBorder; j++){
                if(a[j] > a[j+1]){
                    tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;
                    lastExchage = j;
                }
            }
            // lastExchage后面的元素都是有序的了
            sortBorder = lastExchage;
            if(!flag){
                System.out.println(i);
                return ;
            }
        }
    }

    /**
     * 插入排序
     * @param a
     */
    public static void insertionSort(int[] a){
        if(a == null || a.length == 0){
            return ;
        }
        int n = a.length;
        for(int i=1; i<n; i++){
            int value = a[i];
            int j = i-1;
            for(; j>=0; j--){// 从后往前遍历i下标前面的元素,如果比i下标的值大则进行交换
                if(a[j] > value){// 插入位置后的元素后移
                    a[j+1] = a[j];
                }else{// 因为i下标前的元素都是有序的,当遍历到j不比value大时,此时的j就是最小的j,所以需要结束此循环
                    break;
                }
            }
            // 将i下标的元素插入进来
            a[j+1] = value;
        }
    }



    public static void main(String[] args) {
        int[] a = {6,3,2,4,1,5,2};
        //bubbleSort2(a);
        insertionSort(a);
        for(int i=0; i<a.length; i++){
            System.out.print(a[i]+",");
        }
        System.out.println();
    }

}
