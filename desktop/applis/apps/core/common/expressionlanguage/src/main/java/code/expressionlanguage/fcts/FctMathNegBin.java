package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;

public final class FctMathNegBin implements AnaStdCaller {
    private final byte wrap;

    public FctMathNegBin(byte _wrap) {
        this.wrap = _wrap;
    }

    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return negBin(_args[0]);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct one_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        return new ArgumentWrapper(negBin(one_));
    }

    private NumberStruct negBin(Struct _one) {
        return NumParsers.negBinNumber(NumParsers.convertToNumber(_one),wrap);
    }
}
