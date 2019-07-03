package com.shilei.algo.base.datastruct.heap;

/**
 * @Description: 小顶堆实现
 * 堆中任意节点都比它的所有子节点要都小
 * @Author: shilei
 * @Date: 2019/7/2 20:40
 **/
public class MinHeap<E extends Comparable> {

    private Object[] queue;

    // 堆容量
    private int capacity;

    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    public MinHeap(){
        this(DEFAULT_CAPACITY);
    }

    public MinHeap(int capacity){
        this.capacity = capacity;
        queue = new Object[capacity+1];
        size = 0;
    }

    public MinHeap(Object[] queue){
        this.capacity = queue.length-1;
        this.queue = queue;
        this.size = capacity;
    }

    public void buildHeap(){
        this.buildHeap(queue);
    }

    // 建堆
    public void buildHeap(Object[] queue){
        int n = queue.length-1;
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
            if(2*i <= n && queue[2*i] != null && ((E)queue[2*i]).compareTo(queue[i]) < 0){// 左子节点小于父节点,将小节点记录为k
                k = 2*i;
            }
            if(2*i+1 <= n && queue[2*i+1] != null && ((E)queue[2*i+1]).compareTo(queue[k]) < 0){// 将最小的节点与右节点比较,记录最小的节点
                k = 2*i+1;
            }
            if(i == k){// 当i=k时说明该节点比子节点都小，不需要在进行堆化了
                break;
            }
            swap(queue,k,i);
            i = k;
        }
    }

    public boolean offer(Object v){
        if(size > capacity){
            System.out.println(String.format("insert [%s] array is full."));
            return false;
        }
        // 插入到数据末尾
        queue[++size] = v;
        // 从下自上堆化
        int i = size;
        while(true){
            if(i > 1 && ((E)queue[i]).compareTo(queue[i/2]) < 0){// 比父节点小,则交换
                swap(queue,i,i/2);
            }else{
                break;
            }
            i = i/2;
        }
        return true;
    }

    /**
     * 添加数据，当比堆顶大时则插入堆中，并返回对顶元素
     * 当比堆顶小时直接返回原数据
     * @return
     */
    public E offerOrReplace(E v){
        if(size < capacity){
            offer(v);
            return queue[1] == null ? null : (E)queue[1];
        }else{// 对顶已经满了
            E top = (E)queue[1];
            if(top.compareTo(v) < 0){// 堆顶元素比新增元素小,将新元素插入，并且堆化
                queue[1] = v;
                heapify(size,1);
                v = top;
            }
            // 返回较小的元素
            return v;
        }
    }

    /**
     * 弹出头节点
     * @return
     */
    public E pollFirst(){
        if(size == 0){
            return null;
        }
        E result = queue[1] == null ? null : (E)queue[1];
        // 将尾节点放到头节点
        queue[1] = queue[size];
        queue[size] = 0;
        --size;
        // 然后节点堆化
        heapify(size,1);
        return result;
    }

    /**
     * 替换头节点,并返回原头节点
     * @param v
     */
    public Object replaceFirst(Object v){
        if(size == 0){
            return null;
        }
        Object oldFirst = queue[1];
        // 替换头节点
        queue[1] = v;
        // 然后节点堆化
        heapify(size,1);
        return oldFirst;
    }

    /**
     * 查看头节点
     * @return
     */
    public Object peek(){
        if(size == 0){
            return null;
        }
        Object result = queue[1];
        return result;
    }

    public int getSize(){
        return size;
    }

    public void swap(Object[] queue,int i,int j){
        Object temp = queue[i];
        queue[i] = queue[j];
        queue[j] = temp;
    }

    public void print(){
        for(int i=0; i<queue.length; i++){
            System.out.print(queue[i]+",");
        }
        System.out.println();
    }

    public static void print(Object[] queue,int n){
        for(int i=0; i<=n; i++){
            System.out.print(queue[i]+",");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Object[] queue = new Object[]{0,3,5,1,4,12,8,6,10,2,11};
        MinHeap minHeap = new MinHeap(10);

        minHeap.buildHeap(queue);
        MinHeap.print(queue,queue.length-1);

        minHeap.offer(3);
        minHeap.offer(5);
        minHeap.offer(1);
        minHeap.offer(4);
        minHeap.offer(12);
        minHeap.offer(8);
        minHeap.offer(6);
        minHeap.offer(10);
        minHeap.offer(2);
        minHeap.offer(11);
        minHeap.print();

        minHeap.pollFirst();
        minHeap.print();

        minHeap.pollFirst();
        minHeap.print();

    }

}
