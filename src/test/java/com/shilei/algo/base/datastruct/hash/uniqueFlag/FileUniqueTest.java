package com.shilei.algo.base.datastruct.hash.uniqueFlag;

import org.junit.Test;

/**
 * @Description TODO
 * @Author shil20
 * @Date 2019/7/2 15:48
 **/
public class FileUniqueTest {

    @Test
    public void testFileUnique(){

        long startTime = System.currentTimeMillis();
        String filePath = "E:\\1.jpg";
        String fileUnique = FileUnique.fileUnique(filePath);
        long endTime = System.currentTimeMillis();

        System.out.println(String.format("fileUnique:%s,use time:%s",fileUnique,(endTime-startTime)));

        startTime = System.currentTimeMillis();
        filePath = "E:\\2.jpg";
        fileUnique = FileUnique.fileUnique(filePath);
        endTime = System.currentTimeMillis();

        System.out.println(String.format("fileUnique:%s,use time:%s",fileUnique,(endTime-startTime)));

        startTime = System.currentTimeMillis();
        filePath = "E:\\1.4-SNAPSHOT.zip";
        fileUnique = FileUnique.fileUnique(filePath);
        endTime = System.currentTimeMillis();

        System.out.println(String.format("fileUnique:%s,use time:%s",fileUnique,(endTime-startTime)));

    }


}
