package com.shilei.algo.base.datastruct.queue;


/**
 * @Description: 基于数组实现队列,实现自动扩容
 * @Author: shilei
 * @Date: 2019/5/18 9:21
 **/
public class ArrayQueue<E> {

    private Object[] elementData;

    private final static Object[] DEFAULT_EMPTY_ELEMENTDATA = {};
    // 默认大小
    private final static int DEFAULT_CAPACITY = 2;
    // 对头指针
    private int head = 0;
    // 对尾指针
    private int tail = 0;
    // 元素大小
    private int size = 0;

    public ArrayQueue(){
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayQueue(int initialCapacity){
        if(initialCapacity > 0){
            elementData = new Object[initialCapacity];
        }else if(initialCapacity == 0){
            elementData = DEFAULT_EMPTY_ELEMENTDATA;
        }else{
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    /**
     * 添加节点到队尾
     * @param e
     * @return
     */
    public boolean add(E e){
        if(tail == elementData.length){// 尾指针已到达队尾
            if(head == 0){// 头指针指向0,说明队列满了,则扩容
                grow();
            }else{// 队列还没满,可以向前移动
                moveElementData();
            }
        }
        // 未指针指向新添加的元素
        elementData[tail] = e;
        // 尾指针向后移动
        ++tail;
        size++;
        return true;
    }

    /**
     * 当数组没有满时向前移动数组中的节点
     */
    public void moveElementData(){
        for(int i=head,j=0; i< tail; i++){
            elementData[j++] = elementData[i];
            elementData[i] = null;
        }
        tail = tail-head;
        head = 0;
    }


    /**
     * 从队头弹出一个节点并删除
     * @return
     */
    public E poll(){
        if(head == tail){
            return null;
        }
        // 取出头指针指向的元素
        E element = (E)elementData[head];
        // 释放头指针指向的元素
        elementData[head] = null;
        // 头指针向后移动
        head++;
        size--;
        return element;
    }

    public void print(){
        if(tail == head){
            System.out.println("elementData is empty. ----head:"+head+",tail:"+tail);
        }
        for(int i=head; i<tail; i++){
            System.out.print(elementData[i]+",");
        }
        System.out.println("----head:"+head+",tail:"+tail);
    }

    /**
     * 扩容,没有考虑并发问题
     */
    public void grow(){
        int size = elementData.length;
        // 扩容1.5倍
        int newCapacity = size==0 ? DEFAULT_CAPACITY : size + (size >> 1);
        Object[] newElementData = new Object[newCapacity];
        Object[] oldElementData = elementData;
        for(int i=0; i<size; i++){
            newElementData[i] = oldElementData[i];
            // 释放旧数组节点的指针
            oldElementData[i] = null;
        }
        elementData = newElementData;
    }


    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.add(1);
        System.out.println("poll:"+arrayQueue.poll());
        arrayQueue.add(2);
        System.out.println("length:"+arrayQueue.elementData.length+"-- size:"+arrayQueue.size);
        arrayQueue.add(3);
        System.out.println("length:"+arrayQueue.elementData.length+"-- size:"+arrayQueue.size);
        arrayQueue.add(4);
        arrayQueue.add(5);
        System.out.println("length:"+arrayQueue.elementData.length+"-- size:"+arrayQueue.size);
        arrayQueue.print();
        System.out.println("poll:"+arrayQueue.poll());
        arrayQueue.print();
        System.out.println("poll:"+arrayQueue.poll());
        arrayQueue.print();
        System.out.println("poll:"+arrayQueue.poll());
        arrayQueue.print();
        arrayQueue.add(6);
        arrayQueue.print();
        System.out.println("poll:"+arrayQueue.poll());
        arrayQueue.print();
        System.out.println("poll:"+arrayQueue.poll());
        arrayQueue.print();
        System.out.println("poll:"+arrayQueue.poll());
        arrayQueue.print();
        System.out.println("poll:"+arrayQueue.poll());
        arrayQueue.print();
        System.out.println("poll:"+arrayQueue.poll());
        arrayQueue.print();
    }

}
