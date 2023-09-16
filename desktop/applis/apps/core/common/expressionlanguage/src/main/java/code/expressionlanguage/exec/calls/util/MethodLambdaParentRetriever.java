package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractRefectLambdaMethodPageEl;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.Struct;

public final class MethodLambdaParentRetriever extends AbsLambdaParentRetriever {
    private final ArgumentListCall array = new ArgumentListCall();
    private final ArgumentListCall original;
    private final LambdaMethodStruct lambdaMethodStruct;
    private final Struct originalInstance;

    public MethodLambdaParentRetriever(ArgumentListCall _values, LambdaMethodStruct _l) {
        this.original = _values;
        this.lambdaMethodStruct = _l;
        originalInstance = AbstractRefectLambdaMethodPageEl.originalInstance(lambdaMethodStruct, original.getArgumentWrappers());
    }

    @Override
    protected Struct getValue(StackCall _stackCall) {
        return AbstractRefectLambdaMethodPageEl.adjustedInstance(_stackCall, lambdaMethodStruct, original.getArgumentWrappers());
    }

    @Override
    public int getAncestor() {
        return lambdaMethodStruct.getAncestor();
    }

    @Override
    protected boolean afterSetParent() {
        AbstractRefectLambdaMethodPageEl.trySetArgs(lambdaMethodStruct, original, array);
        return true;
    }

    public ArgumentListCall getArray() {
        return array;
    }

    @Override
    public Struct getOriginalInstance() {
        return originalInstance;
    }
}
