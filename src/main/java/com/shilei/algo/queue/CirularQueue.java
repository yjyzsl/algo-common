package com.shilei.algo.queue;

/**
 * @Description: 基于数组实现循环队列
 * @Author: shilei
 * @Date: 2019/5/20 16:42
 **/
public class CirularQueue<E> {

    private Object[] elementData;

    // 默认大小
    private final static int DEFAULT_CAPACITY = 8;
    private int capacity;
    // 队头指针
    private int head = 0;
    // 队尾指针
    private int tail = 0;

    public CirularQueue(int capacity){
        this.elementData = new Object[capacity];
        this.capacity = capacity;
    }

    /**
     * 向队尾添加一个节点
     * @param e
     * @return
     */
    public boolean add(E e){
        if((tail + 1)%capacity == head){
            System.out.println("elementData is full ---- head:"+head+",tail:"+tail);
            return false;
        }
        elementData[tail] = e;
        tail = (tail+1) % capacity;
        return true;
    }

    /**
     * 弹出队头元素
     */
    public E poll(){
        if(head == tail){// 当队头与队尾相等时说明队列为空了
            System.out.println("elementData is null ---- head:"+head+",tail:"+tail);
            return null;
        }
        E value = (E)elementData[head];
        elementData[head] = null;
        head = (head + 1) % capacity;
        return value;
    }

    public void print(){
        if(tail == head){
            System.out.println("elementData is empty. ----head:"+head+",tail:"+tail);
        }
        int size = tail - head;
        if(size < 0){// 计算队列节点数量
            size = tail + capacity - head;
        }
        int index;
        for(int i=0; i<size; i++){
            index = (head + i)%capacity;
            System.out.print(elementData[index]+",");
        }
        System.out.println("----head:"+head+",tail:"+tail);
    }

    public static void main(String[] args) {

        CirularQueue<Integer> cirularQueue = new CirularQueue<>(4);
        cirularQueue.print();

        cirularQueue.add(1);
        cirularQueue.add(2);
        cirularQueue.add(3);
        cirularQueue.add(4);
        cirularQueue.add(5);
        cirularQueue.print();

        cirularQueue.poll();
        cirularQueue.poll();
        cirularQueue.print();
        cirularQueue.add(6);
        cirularQueue.add(7);
        cirularQueue.add(8);
        cirularQueue.print();

    }

}
