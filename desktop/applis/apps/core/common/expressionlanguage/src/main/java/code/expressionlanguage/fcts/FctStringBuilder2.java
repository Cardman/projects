package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.CharSequenceStruct;
import code.expressionlanguage.structs.StringBuilderStruct;
import code.expressionlanguage.structs.Struct;

public final class FctStringBuilder2 implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return newStringBuilderStructByString(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct(),_cont,_stackCall);
    }

    private static ArgumentWrapper newStringBuilderStructByString(Struct _arg, ContextEl _context, StackCall _stackCall) {
        if (!(_arg instanceof CharSequenceStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNpe(_context, _stackCall)));
            return new ArgumentWrapper(null);
        }
        CharSequenceStruct arg_ = NumParsers.getCharSeq(_arg);
        return new ArgumentWrapper(new StringBuilderStruct(new StringBuilder(arg_.toStringInstance())));
    }
}
