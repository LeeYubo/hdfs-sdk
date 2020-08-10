#include "jni.h"
#include "com_jd_jni_TestJni.h"
#include <stdio.h>
JNIEXPORT void JNICALL Java_com_jd_jni_TestJni_hello(JNIEnv *env,jobject obj){
		    printf("Hello World!\n");
		    	        return;
}
