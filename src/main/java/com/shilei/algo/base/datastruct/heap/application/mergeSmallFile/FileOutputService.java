package com.shilei.algo.base.datastruct.heap.application.mergeSmallFile;

import com.shilei.algo.base.datastruct.heap.application.mergeSmallFile.pojo.FileOutputPojo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Description 合并后输出文件操作
 * @Author shil20
 * @Date 2019/7/3 11:38
 **/
public class FileOutputService {

    public final static FileOutputService INSTANCE = new FileOutputService();

    // 写入总数据
    private int totalWirteSize = 0;

    private StringBuilder buffer = new StringBuilder();

    // 批量写入字符串最大长度
    private final int WIRTE_BATCH_LENGTH = 4096;

    public FileOutputPojo openOutputFile(String filePath){
        FileOutputPojo fileOutputPojo = null;
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            fileOutputPojo = new FileOutputPojo();
            fileOutputPojo.setBufferedWriter(bufferedWriter);
            fileOutputPojo.setFilePath(filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileOutputPojo;
    }


    public void wirte(FileOutputPojo fileOutputPojo,String word){
        this.wirte(fileOutputPojo,word,false);
    }

    /**
     * 将字符串写入文件
     * @param fileOutputPojo
     * @param word
     * @param forceFlush
     */
    public void wirte(FileOutputPojo fileOutputPojo,String word,boolean forceFlush){
        try {
            if(null != word && word.trim().length() > 0){
                buffer.append(word).append(" ");
                totalWirteSize++;
            }
            System.out.println(String.format("wirte [%s]",buffer.toString()));
            int buffLen = buffer.length();
            BufferedWriter bufferedWriter = fileOutputPojo.getBufferedWriter();
            boolean isWirte = forceFlush;
            if(buffLen >= WIRTE_BATCH_LENGTH){
                isWirte = true;
            }
            if(isWirte){
                bufferedWriter.write(buffer.toString());
                //bufferedWriter.newLine();
                bufferedWriter.flush();
                buffer = new StringBuilder();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getTotalWirteSize() {
        return totalWirteSize;
    }

    /**
     * 关闭IO流
     * @param fileOutputPojo
     */
    public void close(FileOutputPojo fileOutputPojo){
        BufferedWriter bufferedWriter = fileOutputPojo.getBufferedWriter();
        if(null != bufferedWriter){
            try {
                System.out.println(String.format("关闭输出流[%s]",fileOutputPojo.getFilePath()));
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
