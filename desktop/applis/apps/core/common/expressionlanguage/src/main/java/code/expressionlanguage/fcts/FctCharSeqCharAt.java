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
import code.expressionlanguage.stds.AliasCharSequenceType;
import code.expressionlanguage.structs.*;

public final class FctCharSeqCharAt implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return charAt((CharSequenceStruct) _instance,_args[0]);
    }

    private static Struct charAt(CharSequenceStruct _charSequence, Struct _index) {
        NumberStruct nb_ = NumParsers.convertToNumber(_index);
        int ind_ = nb_.intStruct();
        if (NumParsers.isInvalidIndex(ind_, _charSequence)) {
            return null;
        }
        return new CharStruct(_charSequence.charAt(ind_));
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return charAt((CharSequenceStruct) _instance,_firstArgs.getArgumentWrappers().get(0).getValue().getStruct(),_cont,_stackCall);
    }

    private static ArgumentWrapper charAt(CharSequenceStruct _charSequence, Struct _index, ContextEl _context, StackCall _stackCall) {
        NumberStruct nb_ = NumParsers.convertToNumber(_index);
        int ind_ = nb_.intStruct();
        if (NumParsers.isInvalidIndex(ind_, _charSequence)) {
            if (ind_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_context, AliasCharSequenceType.getBeginMessage(ind_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_context, AliasCharSequenceType.getEndMessage(ind_, ">=", _charSequence.length()), _stackCall)));
            }
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(new CharStruct(_charSequence.charAt(ind_)));
    }

}
