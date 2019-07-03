package com.shilei.algo.base.datastruct.heap.application.mergeSmallFile.pojo;

import java.io.BufferedWriter;

/**
 * @Description 合并文件输出操作
 * @Author shil20
 * @Date 2019/7/3 11:05
 **/
public class FileOutputPojo {

    private String filePath;

    private BufferedWriter bufferedWriter;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }
}
