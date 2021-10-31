package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.DoubleInfo;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AliasNumberType;
import code.expressionlanguage.stds.AliasReflection;
import code.expressionlanguage.structs.CharSequenceStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public abstract class FctNbDoubleAbs implements AnaStdCaller {

    private final double minValue;
    private final double maxValue;

    protected FctNbDoubleAbs(double _minValue, double _maxValue) {
        this.minValue = _minValue;
        this.maxValue = _maxValue;
    }

    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return parse(_args[0]);
    }
    private Struct parse(Struct _arg) {
        DoubleInfo lg_ = parseAsInfo(minValue, maxValue, _arg);
        if (lg_ == null) {
            return null;
        }
        return build(lg_);
    }

    public static DoubleInfo parseAsInfo(double _minValue,double _maxValue,Struct _arg) {
        if (!(_arg instanceof CharSequenceStruct)) {
            return null;
        }
        String one_ = NumParsers.getCharSeq(_arg).toStringInstance();
        boolean valid_ = true;
        DoubleInfo v_ = NumParsers.splitDouble(one_);
        if (v_.outOfRange(_minValue,_maxValue)) {
            valid_ = false;
        }
        if (!valid_) {
            return null;
        }
        return v_;
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        return parse(argumentWrappers_.get(0).getValue().getStruct(), _cont, _stackCall);
    }

    private ArgumentWrapper parse(Struct _arg, ContextEl _context, StackCall _stackCall) {
        if (!(_arg instanceof CharSequenceStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_context, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        String one_ = NumParsers.getCharSeq(_arg).toStringInstance();
        boolean valid_ = true;
        DoubleInfo v_ = NumParsers.splitDouble(one_);
        if (v_.outOfRange(minValue,maxValue)) {
            valid_ = false;
        }
        if (!valid_) {
            _stackCall.setCallingState(new CustomFoundExc(AliasNumberType.getFormatError(_context, one_, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(build(v_));
    }

    public abstract NumberStruct build(DoubleInfo _info);
}
