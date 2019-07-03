package com.shilei.algo.base.datastruct.heap.application.midNumber;

import com.shilei.algo.base.datastruct.heap.MyHeap;

/**
 * @Description 中位数统计
 * 1.创建两个堆，一个大顶堆，一个小顶堆
 * 2.新增一个数据时跟小顶堆比较，如果比小顶堆小，则将其插入到大顶堆，反之则插入小顶堆
 * 3.查看两个堆数据量是否分布为50%，如果不是则将堆多的堆顶数据移动到堆少的堆中
 * @Author shil20
 * @Date 2019/7/3 16:06
 **/
public class MidNumCount {

    private int count = 0;

    // 假设数据总量为100
    private MyHeap<Integer> smallHeap = new MyHeap<>(10);

    private MyHeap<Integer> bigHeap = new MyHeap<Integer>(10,(o1,o2) -> {
        if(o1 > o2){
            return -1;
        }else if(o1 < o2){
            return 1;
        }else {
            return 0;
        }
    });

    public void put(Integer value){
        ++count;
        Integer top = smallHeap.peek();

        if(top == null){
            smallHeap.offer(value);
            return;
        }

        if(value < top){
            bigHeap.offer(value);
        }else{
            smallHeap.offer(value);
        }

        int midIndex = count/2;
        int smallSize = smallHeap.getSize();
        int bigSize = bigHeap.getSize();

        if(smallSize > midIndex){//小顶堆元素多余
            move(smallHeap,bigHeap);
        }else if(bigSize > midIndex){
            move(bigHeap,smallHeap);
        }

    }

    /**
     * 将srcHeap的堆顶移动到destHeap堆中
     * @param srcHeap
     * @param destHeap
     */
    public void move(MyHeap<Integer> srcHeap,MyHeap<Integer> destHeap){
        Integer value = srcHeap.pollFirst();
        if(null == value){
            System.out.println("==================");
            srcHeap.print();
        }
        destHeap.offer(value);
    }

    public Integer getMidNum(){
        return smallHeap.peek();
    }

    public MyHeap getSmallHeap(){
        return smallHeap;
    }

    public MyHeap getBigHeap(){
        return bigHeap;
    }


}
