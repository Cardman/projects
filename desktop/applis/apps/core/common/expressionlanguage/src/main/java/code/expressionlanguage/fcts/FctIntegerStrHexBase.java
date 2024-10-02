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

public final class FctIntegerStrHexBase implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return FctIntegerStrHex.convert(_args[0], NumParsers.baseHex(_args[1],_page.getDisplayedStrings().getAlphaHex()));
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(FctIntegerStrHex.convert(_firstArgs.getArgumentWrappers().get(0).getValue(), NumParsers.baseHex(_firstArgs.getArgumentWrappers().get(1).getValue(), _cont.getStandards().getDisplayedStrings().getAlphaHex())));
    }

}
