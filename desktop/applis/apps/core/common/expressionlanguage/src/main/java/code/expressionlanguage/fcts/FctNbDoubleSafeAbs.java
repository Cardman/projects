package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.DoubleInfo;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public abstract class FctNbDoubleSafeAbs implements AnaStdCaller {

    private final double minValue;
    private final double maxValue;

    protected FctNbDoubleSafeAbs(double _minValue, double _maxValue) {
        this.minValue = _minValue;
        this.maxValue = _maxValue;
    }

    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return parse(_args[0]);
    }
    private Struct parse(Struct _arg) {
        DoubleInfo lg_ = FctNbDoubleAbs.parseAsInfo(minValue, maxValue, _arg);
        if (lg_ == null) {
            return null;
        }
        return build(lg_);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct parse_ = parse(argumentWrappers_.get(0).getValue().getStruct());
        return new ArgumentWrapper(parse_);
    }

    public abstract NumberStruct build(DoubleInfo _info);
}
