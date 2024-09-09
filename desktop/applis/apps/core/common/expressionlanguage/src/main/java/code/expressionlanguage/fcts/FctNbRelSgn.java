package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.Struct;
import code.util.core.NumberUtil;

public final class FctNbRelSgn implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return str(_args[0]);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(str(_firstArgs.getArgumentWrappers().get(0).getValue()));
    }

    private static Struct str(Struct _arg) {
        long nb_ = NumParsers.convertToNumber(_arg).longStruct();
        return new ByteStruct(NumberUtil.signum(nb_));
    }
}
