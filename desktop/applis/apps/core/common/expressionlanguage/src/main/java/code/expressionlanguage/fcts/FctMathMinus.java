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
import code.util.CustList;

public final class FctMathMinus implements AnaStdCaller {
    private final byte wrap;

    public FctMathMinus(byte _wrap) {
        this.wrap = _wrap;
    }

    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return diff(_args[0], _args[1]);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct one_ = argumentWrappers_.get(0).getValue();
        Struct two_ = argumentWrappers_.get(1).getValue();
        return new ArgumentWrapper(diff(one_,two_));
    }

    private NumberStruct diff(Struct _one, Struct _two) {
        return NumParsers.calculateDiff(NumParsers.convertToNumber(_one), NumParsers.convertToNumber(_two), wrap);
    }
}
