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
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public final class FctStringCompareToIgnoreCase implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        Struct two_ = _args[0];
        if (!(two_ instanceof StringStruct)) {
            return null;
        }
        String one_ = ((StringStruct)_instance).getInstance();
        StringStruct t_ = (StringStruct) two_;
        return new IntStruct(NumParsers.compareToIgnoreCase(one_,t_.getInstance()));
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct two_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        if (!(two_ instanceof StringStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNpe(_cont, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        String one_ = ((StringStruct)_instance).getInstance();
        StringStruct t_ = (StringStruct) two_;
        return new ArgumentWrapper(new IntStruct(NumParsers.compareToIgnoreCase(one_,t_.getInstance())));
    }
}
