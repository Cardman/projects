package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.core.StringUtil;

public final class FctBool implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return common(_args[0], _page.getDisplayedStrings());
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(common(_firstArgs.getArgumentWrappers().get(0).getValue(),_cont.getStandards().getDisplayedStrings()));
    }

    public static Struct common(Struct _arg, DisplayedStrings _dis) {
        String one_ = NumParsers.getCharSeq(_arg).toStringInstance();
        return BooleanStruct.of(StringUtil.quickEq(one_, _dis.getTrueString()));
    }
}
