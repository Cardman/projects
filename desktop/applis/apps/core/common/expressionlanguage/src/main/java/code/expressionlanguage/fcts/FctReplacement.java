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
import code.util.CustList;
import code.util.Replacement;

public final class FctReplacement implements AnaStdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Replacement rep_ = NumParsers.getReplacement(argumentWrappers_.get(0).getValue().getStruct(),argumentWrappers_.get(1).getValue().getStruct());
        return new ArgumentWrapper(new ReplacementStruct(rep_));
    }

    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        Replacement rep_ = NumParsers.getReplacement(_args[0],_args[1]);
        return new ReplacementStruct(rep_);
    }
}
