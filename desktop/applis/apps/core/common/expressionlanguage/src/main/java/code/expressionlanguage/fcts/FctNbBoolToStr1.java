package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class FctNbBoolToStr1 implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return str(_args[0],_page);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(str(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct(),_cont));
    }

    public static Struct str(Struct _arg,AnalyzedPageEl _page) {
        BooleanStruct instance_ = NumParsers.convertToBoolean(_arg);
        return instance_.getDisplayedString(_page);
    }

    public static Struct str(Struct _arg, ContextEl _cont) {
        BooleanStruct instance_ = NumParsers.convertToBoolean(_arg);
        return instance_.getDisplayedString(_cont);
    }
}
