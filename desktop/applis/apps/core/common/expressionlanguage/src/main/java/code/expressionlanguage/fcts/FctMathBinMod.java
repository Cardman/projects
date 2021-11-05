package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctMathBinMod implements AnaStdCaller {
    private final byte wrap;

    public FctMathBinMod(byte _wrap) {
        this.wrap = _wrap;
    }

    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return binMod(_args[0], _args[1]);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct one_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct two_ = argumentWrappers_.get(1).getValue().getStruct();
        Struct res_ = binMod(one_, two_);
        if (res_ == null) {
            _stackCall.setCallingState(new CustomFoundExc(FctMath.getDivideZero(_cont, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(res_);
    }

    private Struct binMod(Struct _one, Struct _two) {
        Struct arg_ = NumParsers.calculateMod(NumParsers.convertToNumber(_one), NumParsers.convertToNumber(_two), wrap);
        if (arg_ == NullStruct.NULL_VALUE) {
            return null;
        }
        return arg_;
    }
}
