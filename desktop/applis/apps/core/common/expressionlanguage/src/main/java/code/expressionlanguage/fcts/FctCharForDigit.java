package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringDataUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.CharStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctCharForDigit implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return forDig(_args[0],_args[1], _page.getDisplayedStrings().getAlpha());
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct one_ = argumentWrappers_.get(0).getValue();
        Struct two_ = argumentWrappers_.get(1).getValue();
        return new ArgumentWrapper(forDig(one_,two_, _cont.getStandards().getDisplayedStrings().getAlpha()));
    }

    private Struct forDig(Struct _arg, Struct _sec, String _alpha) {
        int one_ = (NumParsers.convertToNumber(_arg)).intStruct();
        int two_ = (NumParsers.convertToNumber(_sec)).intStruct();
        return(new CharStruct(StringDataUtil.forDigit(one_, two_, _alpha)));
    }
}
