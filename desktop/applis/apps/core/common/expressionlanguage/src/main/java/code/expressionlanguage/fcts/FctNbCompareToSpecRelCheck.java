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
import code.expressionlanguage.structs.*;

public final class FctNbCompareToSpecRelCheck implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return cmp(_instance, _args[0]);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(cmp(_instance, _firstArgs.getArgumentWrappers().get(0).getValue().getStruct(),_cont,_stackCall));
    }

    public static Struct cmp(Struct _instance, Struct _other, ContextEl _cont, StackCall _stackCall) {
        IntStruct cmp_ = cmp(_instance, _other);
        if (cmp_ == null) {
            _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNpe(_cont, _stackCall)));
            return NullStruct.NULL_VALUE;
        }
        return cmp_;
    }
    public static IntStruct cmp(Struct _instance, Struct _other) {
        if (!(_other instanceof CharStruct)) {
            return null;
        }
        NumberStruct instance_ = NumParsers.convertToNumber(_instance);
        return new IntStruct(NumParsers.compare(instance_, NumParsers.convertToNumber(_other)));
    }
}
