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
import code.util.StringList;
import code.util.core.StringUtil;

public final class FctCharSeqSplitChars implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct one_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        return splitChars((CharSequenceStruct) _instance, one_, _cont, _stackCall);
    }

    private static ArgumentWrapper splitChars(CharSequenceStruct _charSequence, Struct _seps, ContextEl _context, StackCall _stackCall) {
        if (!(_seps instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNpe(_context, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        int lenSeps_ = arrSep_.getLength();
        char[] seps_ = new char[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrSep_.get(i);
            seps_[i] = NumParsers.convertToChar(curSep_).getChar();
        }
        StringList parts_ = StringUtil.splitChars(_charSequence.toStringInstance(), seps_);
        int lenArr_ = parts_.size();
        String aliasString_ = _context.getStandards().getContent().getCharSeq().getAliasString();
        aliasString_ = StringExpUtil.getPrettyArrayType(aliasString_);
        ArrayStruct result_ = new ArrayStruct(lenArr_, aliasString_);
        for (int i = 0; i < lenArr_; i++) {
            result_.set(i, new StringStruct(parts_.get(i)));
        }
        return new ArgumentWrapper(result_);
    }
}
