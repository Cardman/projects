package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractRefectLambdaMethodPageEl;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.LambdaFieldStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class LambdaParentRetriever implements IntParentRetriever {
    private final ArgumentListCall array;
    private final LambdaFieldStruct lambdaFieldStruct;
    private Struct parent;

    public LambdaParentRetriever(ArgumentListCall _values,LambdaFieldStruct _l) {
        this.array = _values;
        this.lambdaFieldStruct = _l;
    }

    @Override
    public boolean retrieve(ContextEl _conf, StackCall _stackCall) {
        if (parent != null) {
            return true;
        }
        boolean static_ = lambdaFieldStruct.isStaticField();
        int nbAncestors_ = lambdaFieldStruct.getAncestor();
        Struct value_ = retrInstance(array, lambdaFieldStruct);
        Struct par_ = AbstractRefectLambdaMethodPageEl.parentRet(static_, nbAncestors_, value_, _stackCall);
        if (par_ == null) {
            String npe_ = _conf.getStandards().getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
            return false;
        }
        parent = par_;
        return true;
    }

    public Struct getParent() {
        return parent;
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
}
