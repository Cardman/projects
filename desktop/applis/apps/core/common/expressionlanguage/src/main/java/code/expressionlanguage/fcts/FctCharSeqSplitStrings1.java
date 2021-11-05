package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class FctCharSeqSplitStrings1 implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct one_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct two_ = argumentWrappers_.get(1).getValue().getStruct();
        return splitStrings((CharSequenceStruct) _instance, NumParsers.convertToNumber(one_), two_, _cont, _stackCall);
    }

    public static ArgumentWrapper splitStrings(CharSequenceStruct _charSequence, Struct _seps, ContextEl _context, StackCall _stackCall) {
        return splitStrings(_charSequence, new IntStruct(-1), _seps, _context, _stackCall);
    }

    public static ArgumentWrapper splitStrings(CharSequenceStruct _charSequence, NumberStruct _lim, Struct _seps, ContextEl _context, StackCall _stackCall) {
        if (!(_seps instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNpe(_context, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        int lenSeps_ = arrSep_.getLength();
        String[] seps_ = new String[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrSep_.get(i);
            if (!(curSep_ instanceof CharSequenceStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNpe(_context, _stackCall)));
                return new ArgumentWrapper(NullStruct.NULL_VALUE);
            }
            seps_[i] = NumParsers.getCharSeq(curSep_).toStringInstance();
        }
        int lim_ = _lim.intStruct();
        if (lim_ < -1) {
            lim_ = -1;
        }
        StringList parts_ = StringUtil.splitStrings(_charSequence.toStringInstance(), seps_);
        int lenArr_ = parts_.size();
        if (lim_ >= 0) {
            lenArr_ = Math.min(lim_,lenArr_);
        }
        String aliasString_ = _context.getStandards().getContent().getCharSeq().getAliasString();
        aliasString_ = StringExpUtil.getPrettyArrayType(aliasString_);
        ArrayStruct result_ = new ArrayStruct(lenArr_, aliasString_);
        for (int i = 0; i < lenArr_; i++) {
            result_.set(i, new StringStruct(parts_.get(i)));
        }
        return new ArgumentWrapper(result_);
    }
}
