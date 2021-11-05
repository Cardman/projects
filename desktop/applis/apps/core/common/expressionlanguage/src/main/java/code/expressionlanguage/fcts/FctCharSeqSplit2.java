package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class FctCharSeqSplit2 implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct one_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct two_ = argumentWrappers_.get(1).getValue().getStruct();
        return splitSingleChar((CharSequenceStruct) _instance, (CharStruct) one_,  NumParsers.convertToNumber(two_), _cont.getStandards());
    }

    public static ArgumentWrapper splitSingleChar(CharSequenceStruct _charSequence, CharStruct _sep, LgNames _stds) {
        return splitSingleChar(_charSequence, _sep, new IntStruct(-1), _stds);
    }

    public static ArgumentWrapper splitSingleChar(CharSequenceStruct _charSequence, CharStruct _sep, NumberStruct _lim, LgNames _stds) {
        int lim_ = _lim.intStruct();
        if (lim_ < -1) {
            lim_ = -1;
        }
        char ch_ = _sep.getChar();
        StringList parts_ = StringUtil.splitChars(_charSequence.toStringInstance(), ch_);
        int lenArr_ = parts_.size();
        if (lim_ >= 0) {
            lenArr_ = Math.min(lim_,lenArr_);
        }
        String aliasString_ = _stds.getContent().getCharSeq().getAliasString();
        aliasString_ = StringExpUtil.getPrettyArrayType(aliasString_);
        ArrayStruct result_ = new ArrayStruct(lenArr_, aliasString_);
        for (int i = 0; i < lenArr_; i++) {
            result_.set(i, new StringStruct(parts_.get(i)));
        }
        return new ArgumentWrapper(result_);
    }
}
