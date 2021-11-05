package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AliasMathType;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class FctMathQuot0 implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return quotInt(_args[0], _args[1]);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct one_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct two_ = argumentWrappers_.get(1).getValue().getStruct();
        Struct res_ = quotInt(one_, two_);
        if (res_ == null) {
            _stackCall.setCallingState(new CustomFoundExc(AliasMathType.getDivideZero(_cont, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(res_);
    }

    private static Struct quotInt(Struct _one, Struct _two) {
        int num_ = NumParsers.convertToNumber(_one).intStruct();
        int den_ = NumParsers.convertToNumber(_two).intStruct();
        if (den_ == 0) {
            return null;
        }
        return new IntStruct(NumberUtil.quot(num_, den_));
    }
}
