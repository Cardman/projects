package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.StringBuilderStruct;
import code.expressionlanguage.structs.Struct;

public final class FctStringBuilder1 implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return newStringBuilderStructByNumber(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct(),_cont,_stackCall);
    }

    private static ArgumentWrapper newStringBuilderStructByNumber(Struct _arg, ContextEl _context, StackCall _stackCall) {
        int one_ = NumParsers.convertToNumber(_arg).intStruct();
        if (one_ < 0) {
            _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_context, Long.toString(one_), _stackCall)));
            return new ArgumentWrapper(null);
        }
        return new ArgumentWrapper(new StringBuilderStruct(new StringBuilder(one_)));
    }
}
