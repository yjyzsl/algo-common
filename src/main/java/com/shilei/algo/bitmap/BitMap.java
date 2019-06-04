package com.shilei.algo.bitmap;

/**
 * @Description 位图
 * @Author shil20
 * @Date 2019/6/4 11:08
 **/
public class BitMap {

    private long[] bitWords;
    private int capacity;
    private final int addressBitsPre = 6;

    public BitMap(int capacity){
        bitWords = new long[(capacity >> addressBitsPre) +1];
        this.capacity = capacity;
    }

    public void set(int bitValue){
        if(bitValue < 0 || bitValue > capacity){
            System.out.println("set bitValue:"+bitValue);
            return ;
        }
        int bitIndex = bitValue >> addressBitsPre; // 计算属于第几个槽位
        bitWords[bitIndex] |= (1 << bitValue);     // 按位或
    }

    public boolean get(int bitValue){
        if(bitValue < 0 || bitValue > capacity){
            System.out.println("get bitValue:"+bitValue);
            return false;
        }
        int bitIndex = bitValue >> addressBitsPre; // 计算属于第几个槽位

        return (bitWords[bitIndex] & (1 << bitValue)) != 0; // 按位与
    }

    public static void main(String[] args) {

        int capacity = 1 << 16;
        System.out.println(capacity);

        BitMap bitMap = new BitMap(capacity);

        bitMap.set(66);
        bitMap.set(65);
        bitMap.set(67);
        bitMap.set(1029);
        bitMap.set(65534);

        System.out.println(bitMap.get(66));
        System.out.println(bitMap.get(65));
        System.out.println(bitMap.get(67));
        System.out.println(bitMap.get(1029));
        System.out.println(bitMap.get(1028));
        System.out.println(bitMap.get(65534));

    }

}
