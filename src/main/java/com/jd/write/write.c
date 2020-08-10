#include "jni.h"
#include "com_jd_write_WriteFile.h"
#include "libclient.h"
#include <stdio.h>

/*
 * Class:     com_jd_write_WriteFile
 * Method:    NewClient
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_jd_write_WriteFile_NewClient(JNIEnv *env, jobject jo) {
	printf("[c -> Java_com_jd_write_WriteFile_NewClient] init client.\n");
	int status = NewClient();
	printf("[c -> Java_com_jd_write_WriteFile_NewClient] Got init client status : %d\n", status);
	return 0;
}
 
/*
 * Class:     com_jd_write_WriteFile
 * Method:    create
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_com_jd_write_WriteFile_create(JNIEnv *env, jobject jo, jlong pid, jint index) {
	printf("[c -> Java_com_jd_write_WriteFile_create] got param pid : %ld, index : %d\n", pid, index);
	long pid_c = pid;
	long index_c = index;
	return Create(pid_c, index_c);
}
 
/*
 * Class:     com_jd_write_WriteFile
 * Method:    open
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_jd_write_WriteFile_open(JNIEnv *env, jobject jo, jlong inode_id) {
	printf("[c -> Java_com_jd_write_WriteFile_open] open file in c...\n");
	long inode_id_c = inode_id;
	return Open(inode_id_c);
}
 
/*
 * Class:     com_jd_write_WriteFile
 * Method:    write
 * Signature: (JJLjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_jd_write_WriteFile_write(JNIEnv *env, jobject jo, jlong inode_id, jlong offset, jstring data) {
    char *input = (*env)->GetStringUTFChars(env, data, NULL);
	// printf("[c -> Java_com_jd_write_WriteFile_write] write file in c...\n");
	long inode_id_c = inode_id;
	long offset_c = offset;
	char* data_c = (char *) (*env)->GetStringUTFChars(env, data, 0);  
    int size = strlen(data_c);
	GoString st = {data_c, size} ;
	// printf("[c -> Java_com_jd_write_WriteFile_write] size : %d\n", size);
	return Write(inode_id_c, offset_c, &st);
}

/*
 * Class:     com_jd_write_WriteFile
 * Method:    close
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_jd_write_WriteFile_close(JNIEnv *env, jobject jo, jlong inode_id) {
	printf("[c -> Java_com_jd_write_WriteFile_close] close file in c...\n");
	long inode_id_c = inode_id;
	return Close(inode_id_c);
}
