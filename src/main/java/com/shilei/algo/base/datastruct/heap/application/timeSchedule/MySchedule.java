package com.shilei.algo.base.datastruct.heap.application.timeSchedule;

import com.shilei.algo.base.datastruct.heap.application.timeSchedule.pojo.ScheduleJob;

import java.util.PriorityQueue;

/**
 * @Description 通过优先级队列实现定时器
 *
 * 优先级队列是通过堆实现
 *
 * @Author shil20
 * @Date 2019/7/2 17:50
 **/
public class MySchedule {

    private PriorityQueue<ScheduleJob> priorityQueue;

    private Thread mainThread;

    public MySchedule(){
        priorityQueue = new PriorityQueue<>(8,(o1,o2) ->{
            if(o1.getRunTime() > o2.getRunTime()){
                return 1;
            }else if(o1.getRunTime() < o2.getRunTime()){
                return -1;
            }else {
                return 0;
            }
        });
    }

    /**
     * 启动定时任务
     */
    public void start(){
        mainThread = new Thread(() -> {
            while (true){
                try {
                    ScheduleJob scheduleJob = priorityQueue.poll();
                    if(scheduleJob == null){
                        System.out.println("priority queue is empty...");
                        Thread.sleep(100);
                        continue;
                    }
                    long runTime = scheduleJob.getRunTime();
                    long curTime = System.currentTimeMillis();
                    long awitTime = runTime - curTime;
                    if(awitTime > 0){// 还有到指定时间,等待awitTime后在运行
                        Thread.sleep(awitTime);
                    }
                    Runnable task = scheduleJob.getRunTask();
                    System.out.println("执行任务:"+scheduleJob);
                    task.run();
                    // 执行完后有将任务放入队列
                    curTime = System.currentTimeMillis();
                    scheduleJob.setRunTime(scheduleJob.getDelay()+curTime);
                    // 执行完后重新放入队列
                    priorityQueue.offer(scheduleJob);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        mainThread.start();
    }

    /**
     * 延时执行定时任务
     * @param task 具体任务
     * @param delay 延时时间
     */
    public void delayTask(Runnable task,long delay){
        // 下一次运行的时间
        long runTime = System.currentTimeMillis()+delay;
        ScheduleJob scheduleJob = new ScheduleJob(runTime,task);
        scheduleJob.setDelay(delay);
        System.out.println("添加任务:"+scheduleJob);
        // 将任务放到队列中
        priorityQueue.offer(scheduleJob);
    }



}
