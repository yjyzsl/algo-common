package com.shilei.algo.base.datastruct.linkedlist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

    private LinkedList<LinkedList.User> linkedList;
    private LinkedList.User user2;
    private LinkedList.User user6;
    private LinkedList.User user5;

    @Before
    public void init(){
        linkedList = new LinkedList<>();

        LinkedList.User user1 = new LinkedList.User("zhangsan1");
        user2 = new LinkedList.User("zhangsan2");
        LinkedList.User user3 = new LinkedList.User("zhangsan3");
        LinkedList.User user4 = new LinkedList.User("zhangsan4");
        user5 = new LinkedList.User("zhangsan2");
        LinkedList.User user7 = new LinkedList.User("zhangsan5");
        user6 = new LinkedList.User("zhangsan2");
        LinkedList.User user8 = new LinkedList.User("zhangsan6");

        linkedList.add(user1);
        linkedList.add(user2);
        linkedList.add(user3);
        linkedList.add(user4);
        linkedList.add(user5);
        linkedList.add(user7);
        linkedList.add(user8);

        System.out.println("size:"+linkedList.getSize());
        linkedList.print();

    }

    /**
     * 查找节点
     */
    @Test
    public void testFind(){
        Assert.assertNotNull(linkedList.find(user2));
        Assert.assertNull(linkedList.find(user6));
    }

    /**
     * 查看链表中是否存在这个节点
     */
    @Test
    public void testContains(){
        Assert.assertTrue(linkedList.contains(user2));
        Assert.assertFalse(linkedList.contains(user6));
    }

    /**
     * 移除节点
     */
    @Test
    public void testRemove(){
        Assert.assertTrue(linkedList.remove(user5));
        Assert.assertFalse(linkedList.remove(user6));
        System.out.println("size:"+linkedList.getSize());
        linkedList.print();
        linkedList.reversePrint();
    }


    /**
     * 单链表反转
     */
    @Test
    public void testReverse(){
        linkedList.reverse();
        linkedList.print();
    }

    /**
     * 移除倒数第index个位置的数组
     */
    @Test
    public void testRemoveIndex(){
        linkedList.removeIndex(1);
        linkedList.print();
        linkedList.removeIndex(2);
        linkedList.print();
        linkedList.removeIndex(4);
        linkedList.print();
    }

    /**
     * 查找链表的中间节点
     */
    @Test
    public void testFindMidNode(){
        System.out.println(linkedList.findMidNode());
    }


    /**
     * 通过快慢节点方式查找链表的中间节点
     */
    @Test
    public void testFindMidNodeFL(){
        System.out.println(linkedList.findMidNodeByFL());
    }



    @Test
    public void testMergeLinkedList(){

        LinkedList<Integer> aLinkedList = new LinkedList<>();
        aLinkedList.add(1);
        aLinkedList.add(5);
        aLinkedList.add(8);
        aLinkedList.add(10);
        aLinkedList.add(12);

        LinkedList<Integer> bLinkedList = new LinkedList<>();
        bLinkedList.add(2);
        bLinkedList.add(4);
        bLinkedList.add(10);
        bLinkedList.add(12);
        bLinkedList.add(12);
        bLinkedList.add(13);

        LinkedList.Node<Integer> megerHead = LinkedList.mergeLinkedList(aLinkedList.getHead(),bLinkedList.getHead());
        megerHead.printAll();

    }


    @Test
    public void testEntryNodeOfLoop(){
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);

        System.out.println("===========================================");

        LinkedList.Node<Integer> entryNode = linkedList.entryNodeOfLoop();
        System.out.println(entryNode);


    }

    @Test
    public void testFindKthToTail(){
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);

        System.out.println("===========================================");

        LinkedList.Node<Integer> entryNode = linkedList.findKthToTail(5);
        System.out.println(entryNode);


    }




}
