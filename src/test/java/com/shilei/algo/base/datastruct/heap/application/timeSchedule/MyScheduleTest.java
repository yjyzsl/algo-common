package com.shilei.algo.base.datastruct.heap.application.timeSchedule;

import com.shilei.algo.base.datastruct.heap.application.timeSchedule.MySchedule;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:
 * @Author: shilei
 * @Date: 2019/7/2 19:43
 **/
public class MyScheduleTest {

    private Object lock = new Object();

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Test
    public void test(){
        try{
            MySchedule mySchedule = new MySchedule();
            mySchedule.start();

            MyTask task1 = new MyTask("task 1111");
            mySchedule.delayTask(task1,5*1000);

            MyTask task2 = new MyTask("task 2222");
            mySchedule.delayTask(task2,8*1000);

            countDownLatch.await();
        }catch (Exception e){
            e.printStackTrace();
            countDownLatch.countDown();
        }
    }

    static class MyTask implements Runnable{

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String taskName;

        public MyTask(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            String date = dateFormat.format(System.currentTimeMillis());
            System.out.println(String.format("%s 正在执行定时任务[%s]",date,taskName));
        }
    }

}
