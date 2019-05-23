package com.shilei.algo.sorts;

/**
 * @Description: 归并排序
 * 归并排序时间复杂度为：O(nlogn),最坏时间复杂度是O(nlogn)，属于稳定排序
 * 归并排序使用了临时数组占用了更多的内存，不是原地排序，所以快速排序使用更广泛
 * @Author: shilei
 * @Date: 2019/5/23 11:18
 **/
public class MergeSort {


    public static void mergeSort(int[] a){
        mergeSortInternally(a,0,a.length-1);
    }

    public static void mergeSortInternally(int[] a,int l,int r){
        if(l >= r){// 当左下标大于等于右下标时递归结束
            return ;
        }
        // 取数组中间位置
        int mid = (l+r)/2;
        // 分治递归
        mergeSortInternally(a,l,mid);
        mergeSortInternally(a,mid+1,r);
        // 合并
        merge(a,l,mid,r);
    }

    /**
     * 将以mid为中点，l-mid和mid+1-r两边的元素合并到临时数据
     * 然后将临时数组的节点拷贝到原数组
     * @param a 原数组
     * @param l 左下标
     * @param mid 中间下标
     * @param r 右下标
     */
    public static void merge(int[] a,int l,int mid,int r){
        System.out.println("l:"+l+",mid:"+mid+",r:"+r);
        int i=l,j=mid+1,k=0;
        int[] tmp = new int[r-l+1];

        while(i<=mid && j<=r){
            // 将i或j下标的节点拷贝到临时节点
            if(a[i] < a[j]){
                tmp[k++] = a[i++];
            }else{
                tmp[k++] = a[j++];
            }
        }
        // 将原左边未比较完的数组拷贝到临时数组
        while(i<=mid){
            tmp[k++] = a[i++];
        }
        // 将原右边未比较完的数组拷贝到临时数组
        while(j<=r){
            tmp[k++] = a[j++];
        }

        // 将临时数组拷贝到原数组
        for(i=0; i<tmp.length; i++){
            a[l+i] = tmp[i];
        }
    }

    public static void main(String[] args) {
        int[] a = {4,1,9,5,3,7,6,3,4};
        mergeSort(a);
        for(int i=0; i<a.length; i++){
            System.out.print(a[i]+",");
        }
        System.out.println();
    }



}
