package com.jd.cfs;

/**
 * @author liyubo
 * @create 2020-08-05 6:18 下午
 **/
public class CallCTest {

    private native void print();

    public static void main(String[] args) {
        new CallCTest().print();
    }

    static {
        System.loadLibrary("HelloWorld");
    }
}
