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

public final class FctCharSeqSubstring0 implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return substring((CharSequenceStruct) _instance, NumParsers.convertToNumber(_args[0]));
    }

    public static Struct substring(CharSequenceStruct _charSequence, NumberStruct _beginIndex) {
        return substring(_charSequence, _beginIndex, new IntStruct(_charSequence.length()));
    }

    public static Struct substring(CharSequenceStruct _charSequence, NumberStruct _beginIndex, NumberStruct _endIndex) {
        int begin_ = _beginIndex.intStruct();
        int end_ = _endIndex.intStruct();
        if (NumParsers.isIncorrectSub(begin_, end_, _charSequence)) {
            return null;
        }
        return new StringStruct(_charSequence.substring(begin_, end_));
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return substring((CharSequenceStruct) _instance, NumParsers.convertToNumber(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct()), _cont, _stackCall);
    }

    public static ArgumentWrapper substring(CharSequenceStruct _charSequence, NumberStruct _beginIndex, ContextEl _context, StackCall _stackCall) {
        return substring(_charSequence, _beginIndex, new IntStruct(_charSequence.length()), _context, _stackCall);
    }

    public static ArgumentWrapper substring(CharSequenceStruct _charSequence, NumberStruct _beginIndex, NumberStruct _endIndex, ContextEl _context, StackCall _stackCall) {
        int begin_ = _beginIndex.intStruct();
        int end_ = _endIndex.intStruct();
        if (NumParsers.isIncorrectSub(begin_, end_, _charSequence)) {
            if (begin_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_context, AliasCharSequenceType.getBeginMessage(begin_), _stackCall)));
            } else if (end_ > _charSequence.length()) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_context, AliasCharSequenceType.getEndMessage(end_, ">", _charSequence.length()), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_context, AliasCharSequenceType.getEndMessage(begin_, ">", end_), _stackCall)));
            }
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(new StringStruct(_charSequence.substring(begin_, end_)));
    }
}
