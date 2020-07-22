package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;

public interface LambdaStruct extends Struct {
    Argument getInstanceCall();
    Struct getMetaInfo();
}
