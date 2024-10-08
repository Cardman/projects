package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ReplacementStruct;
import code.expressionlanguage.structs.Struct;

public final class FctReplNew implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return replNew(_instance);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(replNew(_instance));
    }

    private static Struct replNew(Struct _instance) {
        ReplacementStruct rp_ = NumParsers.getReplacement(_instance);
        String old_ = rp_.getInstance().getNewString();
        return ArgumentListCall.wrapStr(old_);
    }
}
