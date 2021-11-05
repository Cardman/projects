package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithParentStruct;
import code.util.CustList;
import code.util.core.StringUtil;

public final class FctObjSetParent implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct arg_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct par_ = argumentWrappers_.get(1).getValue().getStruct();
        setParent(arg_,par_,_cont,_stackCall);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
    private static void setParent(Struct _arg, Struct _par,ContextEl _cont,StackCall _stackCall){
        if (!(_arg instanceof WithParentStruct)) {
            return;
        }
        WithParentStruct i_ = (WithParentStruct) _arg;
        if (!StringUtil.quickEq(i_.getParentClassName(), _par.getClassName(_cont))) {
            return;
        }
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(i_)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        i_.setParent(_par);
    }
}
