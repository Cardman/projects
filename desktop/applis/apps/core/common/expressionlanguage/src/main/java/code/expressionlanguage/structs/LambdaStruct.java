package code.expressionlanguage.structs;


public interface LambdaStruct extends Struct {
    Struct getInstanceCall();
    Struct getMetaInfo();
}
