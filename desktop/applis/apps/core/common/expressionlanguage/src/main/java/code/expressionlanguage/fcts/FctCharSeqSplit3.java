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

public final class FctCharSeqSplit3 implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct one_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct two_ = argumentWrappers_.get(1).getValue().getStruct();
        return splitSingleString((CharSequenceStruct) _instance,one_,NumParsers.convertToNumber(two_), _cont,_stackCall);
    }

    public static ArgumentWrapper splitSingleString(CharSequenceStruct _charSequence, Struct _sep, ContextEl _context, StackCall _stackCall) {
        return splitSingleString(_charSequence, _sep, new IntStruct(-1), _context, _stackCall);
    }

    public static ArgumentWrapper splitSingleString(CharSequenceStruct _charSequence, Struct _sep, NumberStruct _lim, ContextEl _context, StackCall _stackCall) {
        if (!(_sep instanceof CharSequenceStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNpe(_context, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        int lim_ = _lim.intStruct();
        if (lim_ < -1) {
            lim_ = -1;
        }
        CharSequenceStruct str_ = NumParsers.getCharSeq(_sep);
        StringList parts_ = StringUtil.splitStrings(_charSequence.toStringInstance(), str_.toStringInstance());
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
