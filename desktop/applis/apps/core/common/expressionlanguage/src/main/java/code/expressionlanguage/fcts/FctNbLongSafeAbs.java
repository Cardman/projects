package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public abstract class FctNbLongSafeAbs implements AnaStdCaller {

    private final long minValue;
    private final long maxValue;
    private final AbsRadix radix;
    private final AbsEncodeArg encodeArg;

    protected FctNbLongSafeAbs(long _minValue, long _maxValue, AbsRadix _radix, AbsEncodeArg _e) {
        this.minValue = _minValue;
        this.maxValue = _maxValue;
        this.radix = _radix;
        this.encodeArg = _e;
    }

    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        if (_args.length < 2) {
            return parse(_args[0],_args[_args.length-1], encodeArg.baseEncode(_args[_args.length-1], _page.getDisplayedStrings().getAlpha()));
        }
        return parse(_args[0],_args[1], encodeArg.baseEncode(_args[_args.length-1], _page.getDisplayedStrings().getAlpha()));
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        if (argumentWrappers_.size() < 2) {
            Struct parse_ = parse(argumentWrappers_.get(0).getValue(), argumentWrappers_.last().getValue(), encodeArg.baseEncode(argumentWrappers_.last().getValue(),_cont.getStandards().getDisplayedStrings().getAlpha()));
            return new ArgumentWrapper(parse_);
        }
        Struct parse_ = parse(argumentWrappers_.get(0).getValue(), argumentWrappers_.get(1).getValue(), encodeArg.baseEncode(argumentWrappers_.last().getValue(),_cont.getStandards().getDisplayedStrings().getAlpha()));
        return new ArgumentWrapper(parse_);
    }
    private Struct parse(Struct _arg, Struct _radix, String _alpha) {
        LongInfo lg_ = FctNbLongAbs.parseAsInfo(minValue, maxValue, radix, _arg, _radix, _alpha);
        if (lg_ == null) {
            return null;
        }
        return build(lg_);
    }
    public abstract NumberStruct build(LongInfo _info);
}
