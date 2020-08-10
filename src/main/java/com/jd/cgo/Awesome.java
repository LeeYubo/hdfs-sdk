package com.jd.cgo;

import com.sun.jna.Library;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * @author liyubo
 * @create 2020-08-05 11:26 下午
 **/
public interface Awesome extends Library {
    // GoString class maps to:
    // C type struct { const char *p; GoInt n; }
    public class GoString extends Structure {
        public static class ByValue extends GoString implements Structure.ByValue {}
        public String p;
        public long n;
        protected List getFieldOrder(){
            return Arrays.asList(new String[]{"p","n"});
        }
    }

    int Add(int a, int b);
    double Cosine(double x);
    String Hello1(GoString.ByValue world, GoString.ByValue test);
    String Hello2(byte[] raw, int len);
    String Hello3(GoString.ByValue world, byte[] raw, int len);
}
