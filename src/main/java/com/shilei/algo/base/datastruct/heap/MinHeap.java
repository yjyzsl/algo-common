package com.shilei.algo.base.datastruct.heap;

/**
 * @Description: 小顶堆实现
 * 堆中任意节点都比它的所有子节点要都小
 * @Author: shilei
 * @Date: 2019/7/2 20:40
 **/
public class MinHeap {

    int[] a;

    // 堆容量
    private int capacity;

    private int count;

    private static final int DEFAULT_CAPACITY = 10;

    public MinHeap(){
        this(DEFAULT_CAPACITY);
    }

    public MinHeap(int capacity){
        this.capacity = capacity;
        a = new int[capacity+1];
        count = 0;
    }

    public MinHeap(int[] a){
        this.capacity = a.length-1;
        this.a = a;
        this.count = capacity;
    }

    public void buildHeap(){
        this.buildHeap(a);
    }

    // 建堆
    public void buildHeap(int a[]){
        int n = a.length-1;
        for(int i=n/2; i>0; i--){
            heapify(n,i);
        }
    }


    /**
     * 从上往下进行堆化
     * @param n 堆化到第n个下标是结束
     * @param i 从第i个下标开始进行堆化
     */
    public void heapify(int n,int i){
        int k;
        while(true){
            k = i;
            if(2*i <= n && a[2*i] < a[i]){// 左子节点小于父节点,将小节点记录为k
                k = 2*i;
            }
            if(2*i+1 <= n && a[2*i+1] < a[k]){// 将最小的节点与右节点比较,记录最小的节点
                k = 2*i+1;
            }
            if(i == k){// 当i=k时说明该节点比子节点都小，不需要在进行堆化了
                break;
            }
            swap(a,k,i);
            i = k;
        }
    }

    public boolean insert(int v){
        if(count > capacity){
            System.out.println(String.format("insert [%s] array is full."));
            return false;
        }
        // 插入到数据末尾
        a[++count] = v;
        // 从下自上堆化
        int i = count;
        while(true){
            if(i > 1 && a[i] < a[i/2]){// 比父节点小,则交换
                swap(a,i,i/2);
            }else{
                break;
            }
            i = i/2;
        }
        return true;
    }

    public int getRootNode(){
        return a[1];
    }

    public void replaceRoot(int v){
        a[1] = v;
    }

    public int getCount(){
        return count;
    }

    public void swap(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void print(){
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


    public static void main(String[] args) {
        int[] a = new int[]{0,3,5,1,4,12,8,6,10,2,11};
        MinHeap minHeap = new MinHeap(10);

        minHeap.buildHeap(a);
        MinHeap.print(a,a.length-1);

        minHeap.insert(3);
        minHeap.insert(5);
        minHeap.insert(1);
        minHeap.insert(4);
        minHeap.insert(12);
        minHeap.insert(8);
        minHeap.insert(6);
        minHeap.insert(10);
        minHeap.insert(2);
        minHeap.insert(11);
        minHeap.print();

    }

}
