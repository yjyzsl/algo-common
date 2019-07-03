package com.shilei.algo.base.datastruct.heap.application.midNumber;

import org.junit.Test;

/**
 * @Description TODO
 * @Author shil20
 * @Date 2019/7/3 16:27
 **/
public class MidNumCountTest {

    @Test
    public void test(){

        MidNumCount midNumCount = new MidNumCount();

        midNumCount.put(2);
        midNumCount.put(4);
        midNumCount.put(6);
        midNumCount.put(1);
        midNumCount.put(5);
        midNumCount.put(3);
        midNumCount.put(10);
        midNumCount.put(8);
        midNumCount.put(9);
        midNumCount.put(10);
        midNumCount.put(10);
        midNumCount.put(7);

        System.out.println(midNumCount.getMidNum());
        System.out.println("SmallHeap:");
        midNumCount.getSmallHeap().print();

        System.out.println("BigHeap:");
        midNumCount.getBigHeap().print();

    }



}
