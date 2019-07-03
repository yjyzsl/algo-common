package com.shilei.algo.base.datastruct.heap.application.mergeSmallFile.pojo;

/**
 * @Description 小顶堆元素信息
 * @Author shil20
 * @Date 2019/7/3 11:08
 **/
public class HeapPojo {

    // 需要插入的值
    private String value;

    // 值来至那个索引文件
    private int fileIndex;

    // 集合索引下标
    private int listIndex;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getFileIndex() {
        return fileIndex;
    }

    public void setFileIndex(int fileIndex) {
        this.fileIndex = fileIndex;
    }

    public int getListIndex() {
        return listIndex;
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }
}
