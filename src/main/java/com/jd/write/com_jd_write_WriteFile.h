/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_jd_write_WriteFile */

#ifndef _Included_com_jd_write_WriteFile
#define _Included_com_jd_write_WriteFile
#ifdef __cplusplus
extern "C" {
#endif
#undef com_jd_write_WriteFile_PARENT_ID
#define com_jd_write_WriteFile_PARENT_ID 1LL
#undef com_jd_write_WriteFile_STATUS_SUCCESS
#define com_jd_write_WriteFile_STATUS_SUCCESS 0L
/*
 * Class:     com_jd_write_WriteFile
 * Method:    NewClient
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_jd_write_WriteFile_NewClient
  (JNIEnv *, jobject);

/*
 * Class:     com_jd_write_WriteFile
 * Method:    create
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_com_jd_write_WriteFile_create
  (JNIEnv *, jobject, jlong, jint);

/*
 * Class:     com_jd_write_WriteFile
 * Method:    open
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_jd_write_WriteFile_open
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_jd_write_WriteFile
 * Method:    write
 * Signature: (JJLjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_jd_write_WriteFile_write
  (JNIEnv *, jobject, jlong, jlong, jstring);

/*
 * Class:     com_jd_write_WriteFile
 * Method:    close
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_jd_write_WriteFile_close
  (JNIEnv *, jobject, jlong);

#ifdef __cplusplus
}
#endif
#endif
