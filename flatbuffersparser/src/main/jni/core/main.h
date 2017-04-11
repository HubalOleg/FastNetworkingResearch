#include <jni.h>
#include "flatbuffers/flatbuffers.h"

extern "C" {

JNIEXPORT jbyteArray JNICALL
        Java_com_oleg_hubal_flatbuffersparser_FlatBuffersParser_parseJsonNative(JNIEnv *env,
                                                                              jobject instance,
                                                                              jstring json_,
                                                                              jstring schema_);
}