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
        int count = minHeap.getCount();
        if(k > count){
            // 继续插入
            minHeap.insert(v);
        }else{// 堆已经达到k个元素
            // 1.判断顶元素是否小于新插入的元素
            int rootNode = minHeap.getRootNode();
            // 2.如果是将堆顶元素替换成新元素
            if(rootNode < v){
                minHeap.replaceRoot(v);
            }
            // 3.从上至下进行堆化
            minHeap.heapify(k,1);
        }
    }

    public void printTopK(){
        minHeap.print();
    }



}
