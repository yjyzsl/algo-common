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

    /**
     * 二分精确查找
     * @param a
     * @param value
     * @return
     */
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

    /**
     * 查找第一个等于给定值的元素
     * @param a
     * @param value
     * @return
     */
    public static int findFirstEqElement(int[] a,int value){
        if(a == null || a.length == 0){
            return -1;
        }
        int n = a.length - 1 ;
        return findFirstEqElementInternally(a,0,n,value);
    }

    public static int findFirstEqElementInternally(int[] a,int low,int hight,int value){
        if(low > hight){
            return -1;
        }
        int mid = low + ((hight-low) >> 1);
        if(a[mid] > value){
            hight = mid - 1;
        }else if(a[mid] < value){
            low = mid + 1;
        }else {
            if(mid == 0 || a[mid-1] != value){// mid=0则mid的左边没有元素了，或者mid左边的第一个元素不等于给定值
                return mid;
            }else{
                hight = mid - 1;
            }
        }
        return findFirstEqElementInternally(a,low,hight,value);
    }


    /**
     * 查找最后一个等于给定值的元素
     * @param a
     * @param value
     * @return
     */
    public static int findLastEqElement(int[] a,int value){
        if(a==null || a.length == 0){
            return -1;
        }
        int n = a.length - 1;
        return findLastEqElemetInternally(a,0,n,value);
    }

    public static int findLastEqElemetInternally(int[] a,int low,int hight,int value){
        if(low > hight){
            return -1;
        }
        int mid = low + ((hight-low)>>1);
        if(a[mid] > value){
            hight = mid - 1;
        }else if(a[mid] < value){
            low = mid + 1;
        }else{
            if(mid==(a.length - 1) || a[mid+1] != value){// 当mid=length-1时又边已经没有元素了 或则 mid+1不等于查找值 则表明右边的第一个元素大于给定值
                return mid;
            }else {
                low = mid + 1;
            }
        }
        return findLastEqElemetInternally(a,low,hight,value);
    }

    /**
     * 查找第一个大于等于给定值的元素
     * @param a
     * @param value
     * @return
     */
    public static int findFirstLteElement(int[] a,int value){
        if(a == null || a.length == 0){
            return -1;
        }
        int n = a.length - 1;
        return findFirstLteElementInternally(a,0,n,value);
    }

    public static int findFirstLteElementInternally(int[] a,int low,int hight,int value){
        if(low > hight){
            return -1;
        }
        int mid = low + ((hight-low)>>1);
        if(a[mid] >= value){
            if(mid == 0 || a[mid-1] < value){// mid=0 在列表的最左端 或者 mid左边的第一个元素小于给定值，则mid就是第一个大于等于给定值的位置
                return mid;
            }else{
                hight = mid - 1;
            }
        }else{
            low = mid + 1;
        }
        return findFirstLteElementInternally(a,low,hight,value);
    }


    /**
     * 查找最后一个小于等于给定值的元素
     * @param a
     * @param value
     * @return
     */
    public static int findLastgteElement(int[] a,int value){
        if(a == null || a.length == 0){
            return -1;
        }
        int n = a.length - 1;
        return findLastgteElementInternally(a,0,n,value);
    }

    public static int findLastgteElementInternally(int[] a,int low,int hight,int value){
        if(low > hight){
            return -1;
        }
        int mid = low + ((hight-low)>>1);
        if(a[mid] <= value){
            if(mid == (a.length-1) || a[mid+1] > value){
                return mid;
            }else{
                low = mid + 1;
            }
        }else{
            hight = mid - 1;
        }
        return findLastgteElementInternally(a,low,hight,value);
    }



    public static void main(String[] args) {
        int[] a = {1,12,22,44,44,44,55,64,67,77,78,88,90,99};

        System.out.println(binarySearch(a,22));
        System.out.println(binarySearch(a,100));
        System.out.println(binarySearch(a,50));
        System.out.println(binarySearch(a,-1));
        System.out.println(binarySearch(a,90));

        System.out.println("=================findFirstEqElement==================");
        System.out.println(findFirstEqElement(a,44));

        System.out.println("=================findLastEqElement==================");
        System.out.println(findLastEqElement(a,44));

        //查找第一个大于等于给定值的元素
        System.out.println("=================findFirstLteElement==================");
        System.out.println(findFirstLteElement(a,44)); // 3
        System.out.println(findFirstLteElement(a,33)); // 3
        System.out.println(findFirstLteElement(a,0));  // 0
        System.out.println(findFirstLteElement(a,99)); // 13
        System.out.println(findFirstLteElement(a,200));// -1
        System.out.println(findFirstLteElement(a,1));  // 0

        //查找最后一个小于等于给定值的元素
        System.out.println("=================findLastgteElement==================");
        System.out.println(findLastgteElement(a,44)); // 5
        System.out.println(findLastgteElement(a,45)); // 5
        System.out.println(findLastgteElement(a,1));  // 0
        System.out.println(findLastgteElement(a,99)); // 13
        System.out.println(findLastgteElement(a,0));  // -1
        System.out.println(findLastgteElement(a,200));// 13
    }

}
