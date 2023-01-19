package code.expressionlanguage.utilcompo.stds;


import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctNbRateSafeAbs extends FctNbRateComAbs {

    public FctNbRateSafeAbs(AbsFctRate _m) {
        super(_m);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct parse_ = parseAsInfo(argumentWrappers_.get(0).getValue().getStruct());
        return new ArgumentWrapper(parse_);
    }
}