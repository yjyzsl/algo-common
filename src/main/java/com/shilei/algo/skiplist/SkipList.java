package com.shilei.algo.skiplist;

import java.util.Random;

/**
 * @Description: 跳表
 * 跳表长度为n,假设每两个节点抽出一个节点作为索引，那么第一层索引数量为n/2,第二层为n/4,第k层为n/2^k
 * 所需要的索引节点为 n/2 + n/4 + ... + n/2^k + ... + 4 + 2 = n - 2 , 空间复杂度为O(n)
 * 假设索引高度为h,顶层索引为2个节点，则 n/2^h=2 , h = logn
 * 假设每层遍历m个节点，则时间负责度为O(m * logn),m为一个小的常量级数，则时间复杂度为O(logn)
 *
 * 特性：
 * 查找、插入、删除都很高效时间复杂度都是log(n)
 * 跳表相比红黑树代码更容易读懂（可读性好），实现起来就更不容易出错
 * 跳表可以快速查找出区间内的数据
 *
 * 实现的非重复数据
 * @Author: shilei
 * @Date: 2019/5/26 9:02
 **/
public class SkipList<E extends Comparable<? super E>> {

    /**
     * 索引最大层
     */
    private final int MAX_LEVEL = 16;

    /**
     * 当前索引层
     */
    private int level = 0;

    /**
     * 跳表节点数
     */
    private int size;

    /**
     * 头节点，头结点不存储值，指向每层第一个有数据的节点
     */
    private final SkipNode head = new SkipNode(null,MAX_LEVEL);

    /**
     * 尾节点
     */
    private SkipNode tail;

    /**
     * 随机数生成器
     */
    private final Random random = new Random();


    /**
     * 插入节点
     * @param value
     * @return
     */
    public boolean insert(E value){
        if(value == null){
            return false;
        }

        // 当前节点指向头结点
        SkipNode currNode = head;
        // 生成新节点对象
        SkipNode newNode = new SkipNode(value,MAX_LEVEL);

        // 找出需要插入节点每层的前驱节点的位置
        // 创建一个大小为MAX_LEVEL的数组存储每层的前驱节点
        SkipNode[] prevNodes = new SkipNode[MAX_LEVEL];
        // 初始化，将前驱节点数组都指向head
        for(int i=0; i<level; i++){
            prevNodes[i] = head;
        }

        for(int i=level - 1 ; i >= 0; i--){//遍历每一层，寻找前驱节点
            while (currNode.fowward[i] != null && currNode.fowward[i].value.compareTo(value) < 0){// 找到后继节点为空 或者 最后一个小于等于插入值的节点
                currNode = currNode.fowward[i];
            }
            // 将每层找到的节点存储到前驱节点数组中
            prevNodes[i] = currNode;
        }
        currNode = currNode.fowward[0];
        if(currNode != null && value.equals(currNode.value)){// 存在相同的值的节点
            System.out.println("nodes that have the same value.");
            return false;
        }
        // 随机生成当前节点索引层
        int currLevel = randomLevel();
        System.out.println("currLevel:"+currLevel);
        if(currLevel > level){// 当索引随机层数大于当前索引层数时，将高出的所有层数指向head节点
            for(int i = level; i<currLevel; i++){
                prevNodes[i] = head;
            }
            level = currLevel;
        }
        newNode.maxLevel = level;
        // 将新增节点插入到前驱节点数组中
        for(int i = currLevel - 1; i >= 0; i--){
            newNode.fowward[i] = prevNodes[i].fowward[i];
            prevNodes[i].fowward[i] = newNode;
        }
        return true;
    }

    /**
     * 在跳表中查找指定元素
     * @param value
     * @return
     */
    public SkipNode<E> find(E value){
        if(value == null){
            return null;
        }
        SkipNode<E> skipNode = head;
        for(int i = level-1; i >= 0; i--){
            while (skipNode.fowward[i] != null && skipNode.fowward[i].value.compareTo(value) <= 0){
                skipNode = skipNode.fowward[i];
            }
            if(skipNode != null && value.equals(skipNode.value)){
                return skipNode;
            }else{
                return null;
            }
        }
        return null;
    }


    /**
     * 移除跳表中给定值的一个节点
     * @param value
     * @return
     */
    public SkipNode<E> remove(E value){
        if(value == null){
            return null;
        }
        SkipNode<E> currSkipNode = head;
        SkipNode[] prevNodes = new SkipNode[level];
        for(int i=level-1; i >= 0; i--){
            while (currSkipNode.fowward[i] != null && currSkipNode.fowward[i].value.compareTo(value) < 0 ){//
                currSkipNode = currSkipNode.fowward[i];
            }
            // 前驱节点数组
            prevNodes[i] = currSkipNode;
        }
        // currSkipNode当前节点， 前驱节点的下一个节点就是寻找的给定值的节点，当前节点是第一个大于给定值的节点
        currSkipNode = currSkipNode.fowward[0];//
        if(currSkipNode == null || !value.equals(currSkipNode.value)){// 当前节点值不等于给定值
            return null;
        }
        for(int i = level-1; i >= 0; i--){
            /**
             * 1.当前索引层i驱节点的后继指针指向null,则删除节点不需要维护改层的指针
             * 2.当前索引层i驱节点的后继指针指向的节点的值与给定值不相等时，则删除节点不需要维护改层的指针
             */
            if(prevNodes[i].fowward[i] == null || !prevNodes[i].fowward[i].value.equals(value)){
                continue;
            }
            // 当前索引层i驱节点的后继指针指向删除节点的后继指针
            prevNodes[i].fowward[i] = currSkipNode.fowward[i];
            currSkipNode.fowward[i] = null;
        }
        return currSkipNode;
    }



    /**
     * 随机生成level次数，如果是奇数则层数+1，防止伪随机
     * @return
     */
    private int randomLevel(){
        int nextLevel = 1;
        for(int i=1; i<MAX_LEVEL; i++){
            if(random.nextInt() % 2 == 1){
                nextLevel++;
            }
        }
        return nextLevel;
    }

    public void printAll(){
        SkipNode skipNode = head.fowward[0];
        while (skipNode != null){
            System.out.print(skipNode+"  ,  ");
            skipNode = skipNode.fowward[0];
        }
        System.out.println();
    }


    /**
     * 跳表中的节点
     */
    class SkipNode<E extends Comparable<? super E>>{
        // 节点值
        E value;
        // 节点每层指向的后继节点指针
        SkipNode[] fowward;

        int maxLevel;

        public SkipNode(E value,int maxLevel) {
            this.value = value;
            fowward = new SkipNode[maxLevel];
        }

        @Override
        public String toString() {
            return "SkipNode{" +
                    "value=" + value +
                    ", maxLevel=" + maxLevel +
                    '}';
        }
    }

    public static void main(String[] args) {

        SkipList skipList = new SkipList();
        skipList.insert(5);
        skipList.insert(2);
        skipList.insert(3);
        skipList.insert(6);
        skipList.insert(4);

        skipList.printAll();

        skipList.insert(3);
        skipList.insert(1);
        skipList.printAll();

        System.out.println("find(3):"+skipList.find(3));
        System.out.println("find(6):"+skipList.find(6));
        System.out.println("find(7):"+skipList.find(7));

        System.out.println("remove(3):"+skipList.remove(3));
        skipList.printAll();
        System.out.println("find(3):"+skipList.find(3));

        System.out.println("remove(1):"+skipList.remove(1));
        skipList.printAll();
        System.out.println("find(1):"+skipList.find(1));

        System.out.println("remove(2):"+skipList.remove(2));
        skipList.printAll();
        System.out.println("find(2):"+skipList.find(2));

        System.out.println("remove(3):"+skipList.remove(3));
        skipList.printAll();
        System.out.println("find(3):"+skipList.find(3));

        System.out.println("remove(4):"+skipList.remove(4));
        skipList.printAll();
        System.out.println("find(4):"+skipList.find(4));

        System.out.println("remove(5):"+skipList.remove(5));
        skipList.printAll();
        System.out.println("find(5):"+skipList.find(5));

        System.out.println("remove(6):"+skipList.remove(6));
        skipList.printAll();
        System.out.println("find(6):"+skipList.find(6));

        System.out.println("remove(6):"+skipList.remove(6));
        skipList.printAll();
        System.out.println("find(6):"+skipList.find(6));

    }

}
