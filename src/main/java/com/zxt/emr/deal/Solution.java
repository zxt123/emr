package com.zxt.emr.deal;

import javax.sound.midi.Soundbank;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        T t1 = new T();
        T t2 = new T();
        t1.start();
        System.out.println("当前活跃线程数量:"+Thread.activeCount());
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        //System.out.println(group.activeCount());
        System.out.println("当前线程组的名称:"+group.getName());
        Thread[] list1 = new Thread[group.activeCount()];
        group.enumerate(list1);
        //System.out.println("number:"+number);
        for (Thread thread:list1){
            System.out.println(thread.getName());
        }
        ThreadGroup groupParent = group.getParent();

        System.out.println("当前线程组的名称:"+groupParent.getName());
        Thread[] list2 = new Thread[groupParent.activeCount()];
        groupParent.enumerate(list2);
        //System.out.println("number:"+number);
        for (Thread thread:list2){
            System.out.println(thread.getName());
        }
//        ThreadGroup topGroup = group;
//// 遍历线程组树，获取根线程组
//        while (group != null) {
//            topGroup = group;
//            group = group.getParent();
//        }
//// 激活的线程数加倍
//        int estimatedSize = topGroup.activeCount() * 2;
//        Thread[] slackList = new Thread[estimatedSize];
//// 获取根线程组的所有线程
//        int actualSize = topGroup.enumerate(slackList);
//// copy into a list that is the exact size
//        Thread[] list = new Thread[actualSize];
//        System.arraycopy(slackList, 0, list, 0, actualSize);
//        System.out.println("Thread list size == " + list.length);
//        for (Thread thread : list) {
//            System.out.println(thread.getName()+"---"+thread.isAlive());
//        }
//        t2.start();
//        System.out.println(Thread.activeCount());
//        Map<Thread,StackTraceElement[]> maps = Thread.getAllStackTraces();
//        for ( Thread thread:maps.keySet()){
//            System.out.println(thread.getName());
//        }
//        t2.start();
//        System.out.println(Thread.activeCount());
    }
    static class T extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
