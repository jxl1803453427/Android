//
// Created by Administrator on 2017/3/1.
//

#include "com_sanxiang_project_JniUtils.h"

 jint  Java_com_sanxiang_project_JniUtils_add
        (JNIEnv * env, jobject jobj, jint j1, jint j2){
     int result = j1 + j2;

     return result;
 };

