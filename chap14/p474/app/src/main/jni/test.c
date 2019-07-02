//
// Created by hzk on 2019/7/1.
//
#include "com_nwu_hzk_myapplication_JniTest.h"
#include "stdio.h"
JNIEXPORT jstring JNICALL Java_com_nwu_hzk_myapplication_JniTest_get
  (JNIEnv *env, jobject thiz){
      printf("invoke get from C\n");
      return (*env)->NewStringUTF(env,"Hello from jni!");
  }

  JNIEXPORT void JNICALL Java_com_nwu_hzk_myapplication_JniTest_set
    (JNIEnv *env, jobject thiz, jstring string){
    printf("invoke set from C\n");
     char *str = (char*)(*env)->GetStringUTFChars(env,string,NULL);
    printf("%s\n",str);
    (*env)->ReleaseStringUTFChars(env,string,str);
    }
