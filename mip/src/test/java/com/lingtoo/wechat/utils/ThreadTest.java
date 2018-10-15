package com.lingtoo.wechat.utils;

/**
 * Created by shenzh on 2016/8/8.
 */
public class ThreadTest {
    public static void main(String[] args){
        System.out.println("初始化");
        send();
        System.out.println("已经签单");
    }

    private static void send(){
        System.out.println("send start");
        new Thread(){
            @Override
            public void run() {
                System.out.println("线程init");
                try {
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("线程end");
            }
        }.start();
    }
}
