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
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;

public final class FctNbFloatToStr implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return str(_args[0], _page.getDisplayedStrings());
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(str(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct(),_cont.getStandards().getDisplayedStrings()));
    }

    private static Struct str(Struct _arg, DisplayedStrings _dis) {
        NumberStruct nb_ = NumParsers.convertToNumber(_arg);
        return NumParsers.getFloatString(nb_,_dis.getInfinity(),
                _dis.getNan(),
                _dis.getExponent());
    }
}
