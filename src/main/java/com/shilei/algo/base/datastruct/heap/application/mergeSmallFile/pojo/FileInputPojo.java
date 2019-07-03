package com.shilei.algo.base.datastruct.heap.application.mergeSmallFile.pojo;

import java.io.BufferedReader;
import java.io.File;
import java.util.List;

/**
 * @Description 小文件输入操作
 * @Author shil20
 * @Date 2019/7/3 10:56
 **/
public class FileInputPojo {

    private File file;

    private BufferedReader bufferedReader;

    // 字符串分隔符
    private String separator;

    // 当前等待合并的字符串集合
    private List<String> curMergeList;

    // 当前等待合并的字符串集合索引下标
    private int position;

    // 当前集合大小
    private int curSize;

    // 文件单词总数量
    private int totalSize;

    // 是否完成
    private boolean isFinish;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public List<String> getCurMergeList() {
        return curMergeList;
    }

    public void setCurMergeList(List<String> curMergeList) {
        this.curMergeList = curMergeList;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getCurSize() {
        return curSize;
    }

    public void setCurSize(int curSize) {
        this.curSize = curSize;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    @Override
    public String toString() {
        return "FileInputPojo{" +
                "curMergeList=" + curMergeList +
                ", position=" + position +
                ", curSize=" + curSize +
                ", totalSize=" + totalSize +
                '}';
    }
}
