package com.shilei.algo.search;

/**
 * @Description: 二分查找
 * 二分查找时间复杂度为O(logn)
 * 二分查找局限性
 * 1.只能用在数据是使用的顺序表存储的数据结构上，如果数据通过其他类型的数据机构就不适合了
 * 2.数据集合必须要有序，所以针对数据集频繁进行新增或删除时不适合，适合一次排序多次查找的场景
 * 3.数据量太小时不适合
 * 4.数据量太大时不适合，二分查找底层是依赖数组，当数据很大时，没有连续的内存空间存放则会失败
 * @Author: shilei
 * @Date: 2019/5/23 21:40
 **/
public class BinarySearch {

    public static int binarySearch(int[] a,int value){
        if(a == null || a.length == 0){
            return -1;
        }
        int n = a.length-1;
        return binarySearchInternally(a,0,n,value);
    }

    public static int binarySearchInternally(int[] a,int low,int hight,int value){
        if(low > hight){
            return -1;
        }
        // 计算中间位置数据，位运算优先级不高于算数运算符,所以需要括号
        int mid = low + ((hight-low)>>1);
        System.out.println("low:"+low+"--value:"+a[low]+",hight:"+hight+"--value:"+a[hight]+",mid:"+mid+"--value:"+a[mid]);
        if(a[mid] == value){
            return mid;
        }else if(a[mid] > value){
            hight = mid - 1;
        }else{
            low = mid + 1;
        }
        return binarySearchInternally(a,low,hight,value);
    }

    public static void main(String[] args) {
        int[] a = {1,12,22,44,55,64,67,77,90,99};

        System.out.println(binarySearch(a,22));
        System.out.println(binarySearch(a,100));
        System.out.println(binarySearch(a,50));
        System.out.println(binarySearch(a,-1));
        System.out.println(binarySearch(a,90));

    }

}
