package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctNbRelToStr2 implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return FctNbRelToStr1.str(_args[0],_args[1], NumParsers.base(_args[2], _page.getDisplayedStrings().getAlpha()));
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        return new ArgumentWrapper(FctNbRelToStr1.str(argumentWrappers_.get(0).getValue(),argumentWrappers_.get(1).getValue(), NumParsers.base(argumentWrappers_.get(2).getValue(),_cont.getStandards().getDisplayedStrings().getAlpha())));
    }

}
