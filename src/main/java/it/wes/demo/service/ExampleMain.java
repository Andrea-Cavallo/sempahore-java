package it.wes.demo.service;

import it.wes.demo.model.RisorsaCondivisa;
import it.wes.demo.model.Semaphore;

public class ExampleMain {

    public static final String THREAD_1 = "THREAD-1";
    public static final String THREAD_2 = "THREAD-2";
    public static final String THREAD_3 = "THREAD-3";
    public static final String THREAD_4 = "THREAD-4";
    public static final String THREAD_5 = "THREAD-5";

    public static void main(String[] args) {


        Semaphore s1 = new Semaphore(1);

        new RisorsaCondivisa(THREAD_1, s1).start();
        new RisorsaCondivisa(THREAD_2, s1).start();
        new RisorsaCondivisa(THREAD_3, s1).start();
        new RisorsaCondivisa(THREAD_4, s1).start();
        new RisorsaCondivisa(THREAD_5, s1).start();
    }
}
