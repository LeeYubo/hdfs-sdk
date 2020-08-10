
#include "jni.h"
#include "com_jd_write_WriteFile.h"
#include <stdio.h>

/*
 *
 *
*/
JNIEXPORT void JNICALL Java_TestJni_hello(JNIEnv *env,jobject obj){
		    printf("Hello World!\n");
		    	        return;
}

/*
 * Class:     com_jd_write_WriteFile
 * Method:    NewClient
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_jd_write_WriteFile_NewClient(JNIEnv *env, jobject jo) {
	printf("init cfs client in c...\n");
	return 0;
}
 
/*
 * Class:     com_jd_write_WriteFile
 * Method:    create
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_com_jd_write_WriteFile_create(JNIEnv *env, jobject jo, jlong jl, jint ji) {
	printf("create file in c...\n");
	return 9808;
}
 
/*
 * Class:     com_jd_write_WriteFile
 * Method:    open
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_jd_write_WriteFile_open(JNIEnv *env, jobject jo, jlong jl) {
	printf("open file in c...\n");
	return 0;
}
 
/*
 * Class:     com_jd_write_WriteFile
 * Method:    write
 * Signature: (JJLjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_jd_write_WriteFile_write(JNIEnv *env, jobject jo, jlong jl1, jlong jl2, jstring js) {
	printf("create file in c...\n");
	return 1024;
}

/*
 * Class:     com_jd_write_WriteFile
 * Method:    close
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_jd_write_WriteFile_close(JNIEnv *env, jobject jo, jlong jl) {
	printf("close file in c...\n");
	return 0;
}
