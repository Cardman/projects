package code.expressionlanguage.structs;

public interface AbstractFunctionalInstance extends Struct {
    LambdaStruct getFunctional();
    void setFunctional(LambdaStruct _functional);
}
