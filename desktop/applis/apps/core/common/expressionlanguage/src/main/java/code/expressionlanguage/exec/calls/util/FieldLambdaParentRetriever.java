package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractRefectLambdaMethodPageEl;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.LambdaFieldStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FieldLambdaParentRetriever extends AbsLambdaParentRetriever {
    private final ArgumentListCall array;
    private final LambdaFieldStruct lambdaFieldStruct;
    private final Struct originalInstance;

    public FieldLambdaParentRetriever(ArgumentListCall _values, LambdaFieldStruct _l) {
        this.array = _values;
        this.lambdaFieldStruct = _l;
        originalInstance = retrInstance(array, lambdaFieldStruct);
    }

    @Override
    protected Struct getValue(StackCall _stackCall) {
        boolean static_ = lambdaFieldStruct.isStaticField();
        int nbAncestors_ = getAncestor();
        return AbstractRefectLambdaMethodPageEl.parentRet(static_, nbAncestors_, retrInstance(array, lambdaFieldStruct), _stackCall);
    }

    @Override
    public int getAncestor() {
        return lambdaFieldStruct.getAncestor();
    }

    @Override
    protected boolean afterSetParent() {
        return true;
    }

    public static Struct retrInstance(ArgumentListCall _values, LambdaFieldStruct _ldaField) {
        Argument instance_ = _ldaField.getInstanceCall();
        Struct realInstance_;
        if (!_ldaField.isShiftInstance()) {
            realInstance_ = instance_.getStruct();
        } else {
            CustList<ArgumentWrapper> argumentWrappers_ = _values.getArgumentWrappers();
            realInstance_ = ArgumentWrapper.helpArg(ExecHelper.getFirstArgumentWrapper(argumentWrappers_)).getStruct();
        }
        return realInstance_;
    }

    public Struct getOriginalInstance() {
        return originalInstance;
    }
}
