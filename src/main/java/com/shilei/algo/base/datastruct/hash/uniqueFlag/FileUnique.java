package com.shilei.algo.base.datastruct.hash.uniqueFlag;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description 通过Hash实现文件唯一标识
 * @Author shil20
 * @Date 2019/7/2 14:46
 **/
public class FileUnique {

    /** 文件大小在这个范围内则全量 */
    private final static int CHECK_SIZE = 1024;

    /** 每次读取数据的大小 */
    private final static int READ_SIZE = 128;

    private static MessageDigest MD;

    static {
        try {
            MD = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算文件的唯一标识
     * 当文件大小达到一定大小时,取文件开头、中间、结尾三部分固定长度的字节合并起来然后进行MD5作为此文件的标识
     * @param filePath
     * @return
     */
    public static String fileUnique(String filePath){
        FileInputStream fis = null;
        FileChannel fileChannel = null;
        try {
            fis = new FileInputStream(filePath);
            int fileSize = fis.available();
            if(fileSize > CHECK_SIZE){
                fileChannel = fis.getChannel();
                //long maxSize = fileChannel.size();
                System.out.println("fileSize:"+fileSize);
                ByteBuffer buffer = ByteBuffer.allocate(3*READ_SIZE + 8);
                // 取文件开头128个字节写入到buffer中
                buffer.limit(READ_SIZE);
                fileChannel.read(buffer);

                long minPosition = fileSize/2;
                // 取文件中间位置128个字节写入到buffer中
                fileChannel.position(minPosition);
                buffer.limit(2*READ_SIZE);
                fileChannel.read(buffer);

                long lastPosition = fileSize - READ_SIZE;
                // 取文件末尾位置128个字节写入到buffer中
                fileChannel.position(lastPosition);
                buffer.limit(3*READ_SIZE);
                fileChannel.read(buffer);

                buffer.limit(3*READ_SIZE + 8);
                buffer.putLong(fileSize);
                return getMD5(buffer.array());
            }else{// 需要对文件内容全部进行MD5加密
                byte[] bytes = new byte[fileSize];
                fis.read(bytes);
                return getMD5(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != fileChannel){
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != fis){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static String getMD5(byte[] bytes){
        System.out.println("bytes size:"+bytes.length+" , "+bytes);

        MD.update(bytes);
        byte[] mdBytes = MD.digest();
        System.out.println("mdBytes size:"+mdBytes.length+" , "+mdBytes);

        StringBuffer hexBuffer = new StringBuffer();
        for(int i=0; i<mdBytes.length; i++){
            // 除以16的值
            int c = mdBytes[i] >>> 4 & 0xf;
            hexBuffer.append(Integer.toHexString(c));
            // 16取余
            c = mdBytes[i] & 0xf;
            hexBuffer.append(Integer.toHexString(c));
        }
        return hexBuffer.toString();
    }

}
