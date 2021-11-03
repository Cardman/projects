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
import code.expressionlanguage.stds.AliasCharSequenceType;
import code.expressionlanguage.stds.AliasMathType;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.RangeStruct;
import code.expressionlanguage.structs.Struct;

public final class FctRange2 implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return FctRangeUnlimitedStep.rangeBoundsStep(_args[0], _args[1], _args[2]);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return rangeBoundsStep(_cont, _stackCall, _firstArgs.getArgumentWrappers().get(0).getValue().getStruct(), _firstArgs.getArgumentWrappers().get(1).getValue().getStruct(), _firstArgs.getArgumentWrappers().get(2).getValue().getStruct());
    }

    public static ArgumentWrapper rangeBoundsStep(ContextEl _cont, StackCall _stackCall, Struct _min, Struct _max, Struct _step) {
        int lower_ = NumParsers.convertToNumber(_min).intStruct();
        if (lower_ < 0) {
            _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_cont, AliasCharSequenceType.getBeginMessage(lower_), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        int upper_ = NumParsers.convertToNumber(_max).intStruct();
        if (upper_ < lower_) {
            _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_cont, AliasCharSequenceType.getEndMessage(lower_, ">", upper_), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        int step_ = NumParsers.convertToNumber(_step).intStruct();
        if (step_ == 0) {
            _stackCall.setCallingState(new CustomFoundExc(AliasMathType.getDivideZero(_cont, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(new RangeStruct(lower_, upper_,step_));
    }
}
