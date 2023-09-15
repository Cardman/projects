package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.LambdaMethodStruct;

public final class CustomReflectLambdaMethodWithoutInfo extends AbstractReflectElement {

    private final LambdaMethodStruct lambdaMethodStruct;
    private final ArgumentListCall array;
    public CustomReflectLambdaMethodWithoutInfo(LambdaMethodStruct _l, ArgumentListCall _array) {
        super(true);
        lambdaMethodStruct = _l;
        array = _array;
    }

    public LambdaMethodStruct getLambdaMethodStruct() {
        return lambdaMethodStruct;
    }

    public ArgumentListCall getArray() {
        return array;
    }

}
