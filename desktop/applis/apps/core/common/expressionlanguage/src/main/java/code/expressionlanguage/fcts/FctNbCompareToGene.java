package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AliasReflection;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;

public final class FctNbCompareToGene implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return cmpNull(_instance, _args[0]);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(cmpNull(_instance, _firstArgs.getArgumentWrappers().get(0).getValue().getStruct(),_cont,_stackCall));
    }
    public static Struct cmpNull(Struct _instance, Struct _other, ContextEl _cont, StackCall _stackCall) {
        IntStruct res_ = cmpNull(_instance, _other);
        if (res_ == null) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_cont, _stackCall)));
            return NullStruct.NULL_VALUE;
        }
        return res_;
    }
    public static IntStruct cmpNull(Struct _instance, Struct _other) {
        if (!(_instance instanceof NumberStruct)) {
            return null;
        }
        if (!(_other instanceof NumberStruct)) {
            return null;
        }
        return FctNbCompareToSpecReal.cmpGene(_instance, _other);
    }
}
