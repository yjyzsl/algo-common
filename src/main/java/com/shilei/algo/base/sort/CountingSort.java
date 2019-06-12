package com.shilei.algo.base.sort;

/**
 * @Description: 计数排序
 * 计数排序适用于数据范围不大的场景，如果数据范围K比排序数据数量n大很多就不适合用计数排序了
 * 时间复杂度为O(n)
 * @Author: shilei
 * @Date: 2019/5/23 19:47
 **/
public class CountingSort {


    /**
     * 计算每个人的分数，在所以人中的排序，分数范围为0-5
     * @param a 排序数据
     * @param scope 数据范围
     */
    public static void countingSort(int[] a,int scope){
        if(a == null || a.length == 0){
            return ;
        }
        int score,rank;
        int n = a.length;
        // 数组初始化是默认全部为0
        int[] c = new int[scope];
        // 1.统计等于某分数的所以人数
        for(int i=0; i<n; i++){
            score = a[i];
            c[score] = c[score]+1;
        }
        printArray(c);
        // 2.统计小于等于某分数的所以人数
        for(int i=1; i<c.length; i++){
            c[i] = c[i] + c[i-1];
        }
        printArray(c);
        // 3.创建一个和排序数组大小一致的数组存放分数排名
        int[] r = new int[n];

        // 4.倒序遍历排序数据数组
        for(int i=n-1; i>=0; i--){
            // 取出分数
            score = a[i];
            // 查看分数在统计排名c数组上的排名
            rank = c[score];
            // 将分数设置到下标为排名-1的索引下
            r[rank-1] = score;
            // 统计排名c数组对应分数下的排名-1
            c[score] = rank-1;
        }
        printArray(r);

    }

    public static void printArray(int[] a){
        if(a == null || a.length == 0){
            return ;
        }
        for(int i=0; i<a.length; i++){
            System.out.print(a[i]+",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] a = {2,1,5,3,4,4,3,0,5};
        printArray(a);

        countingSort(a,6);
    }

}
