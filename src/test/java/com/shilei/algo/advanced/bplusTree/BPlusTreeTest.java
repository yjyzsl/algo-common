package com.shilei.algo.advanced.bplusTree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description TODO
 * @Author shil20
 * @Date 2019/7/4 11:23
 **/
public class BPlusTreeTest {

    @Test
    public void testBinarySearch(){
        Integer[] arr = new Integer[]{1,4,6,7,8,10,15};
        List<Integer> list = new ArrayList<>(Arrays.asList(arr));
        System.out.println(String.format("2 binarySearch %s , position:%s",6,Collections.binarySearch(list,6)));
        System.out.println(String.format("5 binarySearch %s , position:%s",10,Collections.binarySearch(list,10)));
        System.out.println(String.format("7 binarySearch %s , position:%s",12,Collections.binarySearch(list,12)));
        System.out.println(String.format("3 binarySearch %s , position:%s",5,Collections.binarySearch(list,5)));
        System.out.println(String.format("8 binarySearch %s , position:%s",16,Collections.binarySearch(list,16)));

    }



}
