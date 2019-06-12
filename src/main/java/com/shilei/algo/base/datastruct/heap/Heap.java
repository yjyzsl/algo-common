package com.shilei.algo.base.datastruct.heap;

/**
 * @Description 堆
 * 1.堆是一个完全二叉树
 * 2.堆中每一个节点都大于等于(或小于等于)其子树上的所有节点
 * 时间复杂度为O(nlogn),属于原地排序,不稳定
 * 为什么堆排序没有快速排序快?
 * 1.堆排序是跳着访问，不像快速排序，数据是顺序访问，这样对CPU是不友好的
 * 2.整个排序过程就是有两个基本操作组成，比较和交换，快速排序的数据交换次数不会比逆序度多
 *   但是对于堆排序，首先需要建堆，则打乱了原先的有序度，数据反而更加无序了
 * @Author shil20
 * @Date 2019/6/5 19:20
 **/
public class Heap {

    // 存储数据的数组,从下标1开始存储数据
    int[] a;
    // 数组最大容量
    private int capacity;
    // 当前数据数量
    private int count;

    public Heap(int capacity){
        this.a = new int[capacity+1];
        this.capacity = capacity;
        count = 0;
    }

    /**
     * 建堆
     * @param a
     * @param n
     */
    public static void buildHeap(int[] a,int n){
        for(int i=n/2; i>0; --i){
            heapify(a,n,i);
        }
    }

    /**
     * 堆化，从上至下与左右子节点比较，如果比子节点小则互换位置，直至不小于左右子节点
     * @param a
     * @param n
     * @param i
     */
    public static void heapify(int[] a,int n,int i){
//        if(i>n && i<1){
//            return;
//        }
        int k;
        while(true){
            k = i;
            if(2*i<=n && a[i]<a[2*i]){
                k = 2*i;
            }
            if((2*i+1)<=n && a[k]<a[2*i+1]){
                k = 2*i+1;
            }
            if(k == i){
                break;
            }
            //System.out.println("before i:"+i+"--value:"+a[i]+",k:"+k+"--value:"+a[k]);
            swap(a,i,k);
            //System.out.println("after i:"+i+"--value:"+a[i]+",k:"+k+"--value:"+a[k]);
            i = k;
        }


    }

    /**
     * 插入一个元素，插入的元素放在堆最后，然后从下至上与父节点比较，如果比父节点大则互换，只至不大于父节点
     * @param v
     */
    public void insert(int v){
        if(count >= capacity){
            return ;
        }
        count++;
        a[count] = v;
        int k,i=count;
        while (true){
            k = i;
            if(k>1 && a[k] > a[k/2]){
                swap(a,k/2,k);
                k = k/2;
            }else{
                break;
            }
            i = k;
        }
    }

    /**
     * 移除对顶元素
     */
    public void removeMax(){
        if(count == 0){
            return ;
        }
        a[1] = a[count];
        --count;
        heapify(a,count,1);
    }

    /**
     * 堆排序
     * 大顶堆堆顶元素是最大的，将堆顶元素与最后一个未被交换的位置互换，n减一，最后一个元素在堆顶，则需要进行从上制下的堆化
     * 循环至堆节点个数为1
     * @param a
     * @param n
     */
    public static void heapSort(int[] a,int n){
        int i = n;
        while (i>0){
            swap(a,1,i);
            --i;
            heapify(a,i,1);
        }
    }

    public static void print(int[] a){
        for(int i=0; i<a.length; i++){
            System.out.print(a[i]+",");
        }
        System.out.println();
    }

    public static void print(int[] a,int n){
        for(int i=0; i<=n; i++){
            System.out.print(a[i]+",");
        }
        System.out.println();
    }

    public static void swap(int[] a, int i, int k) {
        int tmp = a[i];
        a[i] = a[k];
        a[k] = tmp;
    }

    public static void main(String[] args) {
        int[] a = {0,6,5,1,2,8,7,9,3,6};
        //int[] a = {0,1,2,3};
        Heap.buildHeap(a,a.length-1);
        Heap.print(a);
        Heap.heapSort(a,a.length-1);
        Heap.print(a);
//
        Heap heap = new Heap(9);
        heap.insert(6);
        heap.insert(5);
        heap.insert(1);
        heap.insert(2);
        heap.insert(8);
        heap.insert(7);
        heap.insert(9);
        heap.insert(3);
        heap.insert(6);

        Heap.print(heap.a);
        heap.removeMax();
        Heap.print(heap.a,heap.count);
        Heap.heapSort(heap.a,heap.count);
        Heap.print(heap.a,heap.count);
    }

}
