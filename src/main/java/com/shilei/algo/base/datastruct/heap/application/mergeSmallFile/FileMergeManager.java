package com.shilei.algo.base.datastruct.heap.application.mergeSmallFile;

import com.shilei.algo.base.datastruct.heap.MyHeap;
import com.shilei.algo.base.datastruct.heap.application.mergeSmallFile.pojo.FileInputPojo;
import com.shilei.algo.base.datastruct.heap.application.mergeSmallFile.pojo.FileOutputPojo;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description 合并有序小文件操作类
 * @Author shil20
 * @Date 2019/7/3 13:34
 **/
public class FileMergeManager {

    private FileInputPojo[] fileInputPojos;

    private FileOutputPojo fileOutputPojo;

    private MyHeap<String> minHeap;

    // 已完成文件合并的集合
    private Set<FileInputPojo> finishSet;

    /**
     * 初始化有序小文件合并输入文件和输出文件
     * @param inPathDir 输入文件目录
     * @param outPath 输出文件
     * @return
     */
    public void initFileInputPojos(String inPathDir,String outPath){
        File fileDir = new File(inPathDir);
        File[] children = fileDir.listFiles();
        int length = children.length;
        fileInputPojos = new FileInputPojo[length];
        System.out.println(String.format("input merge file size[%s]",length));
        for(int i=0; i<length; i++){
            File childFile = children[i];
            fileInputPojos[i] = FileMergeService.INSTANCE.openMergeFile(childFile);
            System.out.println(String.format("i[%s] file [%s]",i,childFile));
        }
        fileOutputPojo = FileOutputService.INSTANCE.openOutputFile(outPath);
        minHeap = new MyHeap<>(length);
        finishSet = new HashSet<>();
        System.out.println("init finish.");
    }

    public void wordMerge(){
        FileInputPojo fileInputPojo = null;
        long startTime = System.currentTimeMillis();
        int fileLength = fileInputPojos.length;
        // 堆是否装满了队列
        boolean isHeapFull = false;
        while (true){
            if(finishSet.size() >= fileLength){// 所有文件都处理完了
                // 将小顶堆中的数据写入到文件
                wirte4Heap();
                // 关闭输出流
                FileOutputService.INSTANCE.wirte(fileOutputPojo,null,true);

                FileOutputService.INSTANCE.close(fileOutputPojo);
                System.out.println(String.format("单词写入总数量为[%s]",FileOutputService.INSTANCE.getTotalWirteSize()));
                break;
            }
            for(int i=0; i<fileLength; i++){
                fileInputPojo = fileInputPojos[i];

                if(finishSet.contains(fileInputPojo)){
                    continue;
                }

                if(fileInputPojo.isFinish()){
                    // 写入没有写完的数据
                    FileOutputService.INSTANCE.wirte(fileOutputPojo,null,true);
                    // 关闭流
                    FileMergeService.INSTANCE.close(fileInputPojo);
                    // 已完成则将其添加到已完成集合中
                    finishSet.add(fileInputPojo);
                    System.out.println(String.format("%s-->[%s]已完成合并,单词数量为[%s]",i,fileInputPojo.getFile(),fileInputPojo.getTotalSize()));
                    continue;
                }

                boolean isReaded = FileMergeService.INSTANCE.isReaded(fileInputPojo);
                if(isReaded){// 重新从文件中读一行
                    boolean readFail = FileMergeService.INSTANCE.read(fileInputPojo);
                    if(!readFail){
                        continue;
                    }
                }

                System.out.println(String.format("i:%s,%s",i,fileInputPojo));
                int position = fileInputPojo.getPosition();
                String word = fileInputPojo.getCurMergeList().get(position);
                // 返回当前堆中最小的单词
                word = minHeap.offerOrReplace(word);
                // 当小顶堆中数量不小于文件个数时，就开始往输出文件中写
                if(minHeap.getSize() >= fileLength){
                    if(isHeapFull){
                        FileOutputService.INSTANCE.wirte(fileOutputPojo,word);
                    }else{
                        isHeapFull = true;
                    }
                }
                // 下标加一
                ++position;
                fileInputPojo.setPosition(position);
            }
        }
        System.out.println(String.format("所有文件都处理完毕,耗时:%s",(System.currentTimeMillis()-startTime)));
    }

    /**
     * 将小顶堆中的数据写入到输出文件中
     */
    private void wirte4Heap() {
        String word = null;
        while ((word = minHeap.pollFirst()) != null){
            FileOutputService.INSTANCE.wirte(fileOutputPojo,word);
        }
    }

    public static void main(String[] args) {
        String dataPath = FileMergeManager.class.getResource("").getPath();
        dataPath = dataPath.replace("target/classes","src/main/java");
        System.out.println("dataPath:"+dataPath);
        String inPathDir = dataPath + "data\\input";
        String outPath = dataPath + "data\\output\\outFile.txt";
        FileMergeManager fileMergeManager = new FileMergeManager();
        fileMergeManager.initFileInputPojos(inPathDir,outPath);

        fileMergeManager.wordMerge();
    }

}
