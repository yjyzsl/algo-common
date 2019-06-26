package com.shilei.algo.base.datastruct.hash;

import org.junit.Test;

/**
 * @Description TODO
 * @Author shil20
 * @Date 2019/6/26 15:34
 **/
public class LindedListHashTest {

    @Test
    public void testPut(){
        LindedListHash lindedListHash = new LindedListHash();

        lindedListHash.put("asdas","1234");
        lindedListHash.put("bb","1235");
        lindedListHash.put("123","1236");
        lindedListHash.put("dd","1237");

        lindedListHash.put("bb","123b");

        lindedListHash.printAll();
    }


    @Test
    public void testGet(){
        LindedListHash lindedListHash = new LindedListHash();

        lindedListHash.put("asdas","1234");
        lindedListHash.put("bb","1235");
        lindedListHash.put("123","1236");
        lindedListHash.put("dd","1237");
        lindedListHash.put("bb","123b");
        lindedListHash.printAll();

        System.out.println(lindedListHash.get("bb"));
        System.out.println(lindedListHash.get("123"));
        System.out.println(lindedListHash.get("aaaa"));

    }

    @Test
    public void testRemove(){
        LindedListHash lindedListHash = new LindedListHash();

        lindedListHash.put("asdas","1234");
        lindedListHash.put("bb","1235");
        lindedListHash.put("123","1236");
        lindedListHash.put("dd","1237");
        lindedListHash.put("bb","123b");
        lindedListHash.printAll();
        System.out.println("size:"+lindedListHash.getSize());
        System.out.println(lindedListHash.remove("bb"));
        lindedListHash.printAll();
        System.out.println("size:"+lindedListHash.getSize());
        System.out.println(lindedListHash.remove("123"));
        lindedListHash.printAll();
        System.out.println(lindedListHash.remove("aaaa"));
        System.out.println(lindedListHash.remove("bb"));
        lindedListHash.printAll();
        System.out.println("size:"+lindedListHash.getSize());
        System.out.println(lindedListHash.remove("asdas"));
        System.out.println(lindedListHash.remove("dd"));
        lindedListHash.printAll();
        System.out.println("size:"+lindedListHash.getSize());

    }

}
