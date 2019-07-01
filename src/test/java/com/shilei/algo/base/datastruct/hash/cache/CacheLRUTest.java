package com.shilei.algo.base.datastruct.hash.cache;

import org.junit.Test;

/**
 * @Description
 * @Author shil20
 * @Date 2019/7/1 16:17
 **/
public class CacheLRUTest {

    @Test
    public void putTest(){

        CacheLRU cacheLRU = new CacheLRU(4,8);
        cacheLRU.put("1",11);
        cacheLRU.put("2",22);
        cacheLRU.put("3",33);
        cacheLRU.put("4",44);
        cacheLRU.printLinkList();
        cacheLRU.printHashTable();

        cacheLRU.put("5",55);
        cacheLRU.put("6",66);
        cacheLRU.put("7",77);
        cacheLRU.put("8",88);
        cacheLRU.printLinkList();
        cacheLRU.printHashTable();

        cacheLRU.put("9",12);
        cacheLRU.printLinkList();
        cacheLRU.printHashTable();

        cacheLRU.put("2",23);
        cacheLRU.printLinkList();
        cacheLRU.printHashTable();

        cacheLRU.put("7",78);
        cacheLRU.printLinkList();
        cacheLRU.printHashTable();

    }

    @Test
    public void getTest(){

        CacheLRU cacheLRU = new CacheLRU(4,8);
        cacheLRU.put("1",11);
        cacheLRU.put("2",22);
        cacheLRU.put("3",33);
        cacheLRU.put("4",44);
        cacheLRU.put("5",55);
        cacheLRU.put("6",66);
        cacheLRU.put("7",77);
        cacheLRU.put("8",88);
        cacheLRU.printLinkList();
        cacheLRU.printHashTable();

        System.out.println(cacheLRU.get("4"));

        cacheLRU.put("1",23);
        cacheLRU.printLinkList();
        cacheLRU.printHashTable();
        System.out.println(cacheLRU.get("1"));

        System.out.println(cacheLRU.get("10"));

        cacheLRU.put("7",78);
        cacheLRU.printLinkList();
        cacheLRU.printHashTable();
        System.out.println(cacheLRU.get("7"));

        System.out.println(cacheLRU.get("8"));
        cacheLRU.printLinkList();
        cacheLRU.printHashTable();

    }

    @Test
    public void removeTest(){

        CacheLRU cacheLRU = new CacheLRU(4,8);
        cacheLRU.put("1",11);
        cacheLRU.put("2",22);
        cacheLRU.put("3",33);
        cacheLRU.put("4",44);
        cacheLRU.put("5",55);
        cacheLRU.put("6",66);
        cacheLRU.put("7",77);
        cacheLRU.put("8",88);
        cacheLRU.printLinkList();
        cacheLRU.printHashTable();

        System.out.println(cacheLRU.get("4"));

        System.out.println(cacheLRU.remove("1"));
        cacheLRU.printLinkList();
        cacheLRU.printHashTable();

        System.out.println(cacheLRU.remove("6"));
        cacheLRU.printLinkList();
        cacheLRU.printHashTable();

        cacheLRU.put("6",67);
        cacheLRU.printLinkList();
        cacheLRU.printHashTable();
        System.out.println(cacheLRU.get("6"));
        cacheLRU.printLinkList();
        cacheLRU.printHashTable();
    }



}
