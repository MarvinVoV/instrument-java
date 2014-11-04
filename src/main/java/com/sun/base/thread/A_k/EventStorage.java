package com.sun.base.thread.A_k;

import java.util.Date;
import java.util.LinkedList;

/**
 * Created by louis on 2014/11/4.
 */
public class EventStorage {
    private int maxSize;
    private LinkedList<Date> storage;

    public EventStorage(){
        maxSize=10;
        storage = new LinkedList<>();
    }

    public synchronized void set() {
        while (storage.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.offer(new Date());
        System.out.printf("Set:%d\n",storage.size());
        notifyAll();
    }

    public synchronized void get() {
        while (storage.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Get:%d: %s\n", storage.size(), storage.poll());
        notifyAll();
    }
}
