package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.LambdaConstructorStruct;

public final class CustomReflectLambdaConstructorWithoutInfo extends AbstractReflectElement {

    private final LambdaConstructorStruct lambdaConstructorStruct;
    private final ArgumentListCall array;
    public CustomReflectLambdaConstructorWithoutInfo(LambdaConstructorStruct _l, ArgumentListCall _array) {
        super(true);
        lambdaConstructorStruct = _l;
        array = _array;
    }

    public LambdaConstructorStruct getLambdaConstructorStruct() {
        return lambdaConstructorStruct;
    }

    public ArgumentListCall getArray() {
        return array;
    }

}
