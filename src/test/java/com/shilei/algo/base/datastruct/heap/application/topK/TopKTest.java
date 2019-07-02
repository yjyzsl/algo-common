package com.shilei.algo.base.datastruct.heap.application.topK;

import org.junit.Test;

/**
 * @Description:
 * @Author: shilei
 * @Date: 2019/7/2 21:49
 **/
public class TopKTest {

    @Test
    public void test(){

        TopK topK = new TopK(10);
        topK.insert(100);
        topK.insert(10);
        topK.insert(50);
        topK.insert(20);
        topK.insert(13);
        topK.insert(40);
        topK.insert(22);
        topK.insert(30);
        topK.insert(99);
        topK.insert(80);

        topK.printTopK();

        topK.insert(90);
        topK.printTopK();
        topK.insert(2);
        topK.printTopK();
        topK.insert(200);
        topK.printTopK();
        topK.insert(60);
        topK.printTopK();
        topK.insert(12);
        topK.printTopK();
        topK.insert(99);
        topK.printTopK();
    }


}
