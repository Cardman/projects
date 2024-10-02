package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.CharSequenceStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public abstract class FctNbLongAbs implements AnaStdCaller {

    private final long minValue;
    private final long maxValue;
    private final AbsRadix radix;
    private final AbsEncodeArg encodeArg;

    protected FctNbLongAbs(long _minValue, long _maxValue, AbsRadix _radix, AbsEncodeArg _e) {
        this.minValue = _minValue;
        this.maxValue = _maxValue;
        this.radix = _radix;
        this.encodeArg = _e;
    }

    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        if (_args.length < 2) {
            return parse(_args[0],_args[_args.length-1], encodeArg.baseEncode(_args[_args.length-1],_page.getDisplayedStrings().getAlpha()));
        }
        return parse(_args[0],_args[1], encodeArg.baseEncode(_args[_args.length-1],_page.getDisplayedStrings().getAlpha()));
    }
    private Struct parse(Struct _arg, Struct _radix, String _alpha) {
        LongInfo lg_ = parseAsInfo(minValue, maxValue, radix, _arg, _radix, _alpha);
        if (lg_ == null) {
            return null;
        }
        return build(lg_);
    }
    public static LongInfo parseAsInfo(long _minValue, long _maxValue, AbsRadix _rad, Struct _arg, Struct _radix, String _alpha) {
        if (!(_arg instanceof CharSequenceStruct)) {
            return null;
        }
        String one_ = NumParsers.getCharSeq(_arg).toStringInstance();
        int radix_ = _rad.radix(_radix);
        LongInfo lg_ = NumParsers.parseLong(one_, radix_, _alpha);
        if (lg_.outOfRange(_minValue, _maxValue)) {
            return null;
        }
        return lg_;
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        if (argumentWrappers_.size() < 2) {
            return parse(argumentWrappers_.get(0).getValue(),argumentWrappers_.last().getValue(), _cont, _stackCall, encodeArg.baseEncode(argumentWrappers_.last().getValue(),_cont.getStandards().getDisplayedStrings().getAlpha()));
        }
        return parse(argumentWrappers_.get(0).getValue(),argumentWrappers_.get(1).getValue(), _cont, _stackCall, encodeArg.baseEncode(argumentWrappers_.last().getValue(),_cont.getStandards().getDisplayedStrings().getAlpha()));
    }

    private ArgumentWrapper parse(Struct _arg, Struct _radix, ContextEl _context, StackCall _stackCall, String _alpha) {
        if (!(_arg instanceof CharSequenceStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNpe(_context, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        String one_ = NumParsers.getCharSeq(_arg).toStringInstance();
        int radix_ = radix.radix(_radix);
        LongInfo lg_ = NumParsers.parseLong(one_, radix_, _alpha);
        if (lg_.outOfRange(minValue, maxValue)) {
            _stackCall.setCallingState(new CustomFoundExc(FctUtil.getFormatError(_context, FctUtil.getNumberRadixMessage(one_, radix_), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(build(lg_));
    }
    public abstract NumberStruct build(LongInfo _info);
}
