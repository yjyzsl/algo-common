package com.shilei.algo.base.datastruct.heap.application.mergeSmallFile;

import com.shilei.algo.base.datastruct.heap.application.mergeSmallFile.pojo.FileInputPojo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 文件合并操作类
 * @Author shil20
 * @Date 2019/7/3 11:00
 **/
public class FileMergeService {

    public final static FileMergeService INSTANCE = new FileMergeService();

    /**
     * 打开需要合并的小文件
     * @param mergeFile
     * @return
     */
    public FileInputPojo openMergeFile(File mergeFile){
        FileInputPojo fileInputPojo = null;
        try {
            //File mergeFile = new File(filePath);
            FileReader fileReader = new FileReader(mergeFile);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            fileInputPojo = new FileInputPojo();
            //fileInputPojo.setFileReader(fileReader);
            fileInputPojo.setFile(mergeFile);
            fileInputPojo.setBufferedReader(bufferedReader);
            fileInputPojo.setSeparator(" ");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileInputPojo;
    }


    /**
     * 打开需要合并的小文件
     * @param fileInputPojo
     * @return
     */
    public boolean read(FileInputPojo fileInputPojo){
        try {
            BufferedReader bufferedReader = fileInputPojo.getBufferedReader();
            String line = bufferedReader.readLine();
            if(null == line){// 已经读完
                fileInputPojo.setFinish(true);
                return false;
            }
            String[] words = line.split(fileInputPojo.getSeparator());
            List<String> curMergeList = new ArrayList<>();
            for(int i=0; i<words.length; i++){
                String word = words[i];
                if(word != null && word.trim().length() > 0){
                    curMergeList.add(word);
                }
            }
            int curSize = curMergeList.size();
            int position = 0;
            fileInputPojo.setCurSize(curSize);
            fileInputPojo.setPosition(position);
            fileInputPojo.setCurMergeList(curMergeList);
            fileInputPojo.setTotalSize(fileInputPojo.getTotalSize()+curSize);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 是否需要重新读
     * @param fileInputPojo
     * @return
     */
    public boolean isReaded(FileInputPojo fileInputPojo){
        int position = fileInputPojo.getPosition();
        int curSize = fileInputPojo.getCurSize();
        return position >= curSize;
    }

    /**
     * 关闭IO流
     * @param fileInputPojo
     */
    public void close(FileInputPojo fileInputPojo){
        BufferedReader bufferedReader = fileInputPojo.getBufferedReader();
        if(null != bufferedReader){
            try {
                System.out.println(String.format("关闭输入流[%s]",fileInputPojo.getFile()));
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
