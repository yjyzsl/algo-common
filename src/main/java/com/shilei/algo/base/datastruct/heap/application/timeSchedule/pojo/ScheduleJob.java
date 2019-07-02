package com.shilei.algo.base.datastruct.heap.application.timeSchedule.pojo;

/**
 * @Description 定时任务调度实体
 * @Author shil20
 * @Date 2019/7/2 17:54
 **/
public class ScheduleJob {

    /** 任务执行时间间隔 */
    private long delay;

    /** 指定时间点运行 */
    private long runTime;

    /** 定时调度执行的任务 */
    private Runnable runTask;

    public ScheduleJob(long runTime, Runnable runTask) {
        this.runTime = runTime;
        this.runTask = runTask;
    }

    public long getRunTime() {
        return runTime;
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }

    public Runnable getRunTask() {
        return runTask;
    }

    public void setRunTask(Runnable runTask) {
        this.runTask = runTask;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    @Override
    public String toString() {
        return "ScheduleJob{" +
                "delay=" + delay +
                ", runTime=" + runTime +
                ", runTask=" + runTask +
                '}';
    }
}
