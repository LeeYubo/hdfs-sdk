package com.jd.jni;

/**
 * @author liyubo
 * @create 2020-08-06 11:51 上午
 **/
public class TestJni {

    public native void hello();

    static {
        //设置查找路径为当前项目路径
        System.setProperty("java.library.path", "/Users/liyubo/gitpath/work/hdfs-sdk/src/main/java");
        //加载动态库的名称
        System.loadLibrary("hello");
    }

    public static void main(String[] args) {
        System.out.println("Start to run hello");
        new TestJni().hello();
    }
}
