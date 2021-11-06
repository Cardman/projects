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
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.RangeStruct;
import code.expressionlanguage.structs.Struct;

public final class FctRange0 implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return FctRangeUnlimitedStep.rangeUnlimit(_args[0]);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return rangeUnlimit(_cont, _stackCall, _firstArgs.getArgumentWrappers().get(0).getValue().getStruct());
    }

    public static ArgumentWrapper rangeUnlimit(ContextEl _cont, StackCall _stackCall, Struct _min) {
        int lower_ = NumParsers.convertToNumber(_min).intStruct();
        if (lower_ < 0) {
            _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_cont, FctUtil.getBeginMessage(lower_), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(new RangeStruct(lower_));
    }
}
