#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_hzk_myapplication_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    jclass clazz = env->FindClass("com/example/hzk/myapplication/MainActivity");
    if(clazz == NULL){
        printf("find the mainActivity error!");
        return NULL;
    }
    jmethodID id = env->GetStaticMethodID(clazz,"methodCalledByJNI","(Ljava/lang/String;)V");
    if(id == NULL){
        printf("find the methodCalledByJni error!");
    }
    jstring msg = env->NewStringUTF("msg from native stringFromJni.cpp");
    env->CallStaticVoidMethod(clazz,id,msg);
    return env->NewStringUTF(hello.c_str());
}
