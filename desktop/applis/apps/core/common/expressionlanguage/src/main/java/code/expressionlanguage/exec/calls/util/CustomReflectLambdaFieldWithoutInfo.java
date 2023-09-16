package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.LambdaFieldStruct;

public final class CustomReflectLambdaFieldWithoutInfo extends AbstractReflectElement {

    private final LambdaFieldStruct lambdaFieldStruct;
    private final ArgumentListCall array;
    public CustomReflectLambdaFieldWithoutInfo(LambdaFieldStruct _l, ArgumentListCall _array) {
        super(true);
        lambdaFieldStruct = _l;
        array = _array;
    }

    public LambdaFieldStruct getLambdaFieldStruct() {
        return lambdaFieldStruct;
    }

    public ArgumentListCall getArray() {
        return array;
    }

}
