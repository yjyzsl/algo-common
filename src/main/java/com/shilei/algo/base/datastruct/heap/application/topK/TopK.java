package com.shilei.algo.base.datastruct.heap.application.topK;

import com.shilei.algo.base.datastruct.heap.MinHeap;

/**
 * @Description:
 * @Author: shilei
 * @Date: 2019/7/2 21:33
 **/
public class TopK {

    private MinHeap minHeap;

    private int k;

    public TopK(int k){
        this.minHeap = new MinHeap(k);
        this.k = k;
    }

    public void insert(int v){
        int size = minHeap.getSize();
        if(k > size){
            // 继续插入
            minHeap.offer(v);
        }else{// 堆已经达到k个元素
            // 1.判断顶元素是否小于新插入的元素
            Comparable rootNode = (Comparable)minHeap.peek();
            // 2.如果是将堆顶元素替换成新元素
            if(rootNode.compareTo(v) < 0){
                rootNode = v;
                // 3.移除头节点
                Object oldFirst = minHeap.replaceFirst(rootNode);
                System.out.println(String.format("oldFirst:%s , newFirst:%s",oldFirst,v));
            }
        }
    }

    public void printTopK(){
        minHeap.print();
    }



}
