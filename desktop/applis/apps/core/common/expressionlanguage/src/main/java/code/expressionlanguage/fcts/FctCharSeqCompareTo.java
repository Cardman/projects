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
import code.expressionlanguage.stds.AliasReflection;
import code.expressionlanguage.structs.CharSequenceStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.core.StringUtil;

public final class FctCharSeqCompareTo implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return compareTo((CharSequenceStruct) _instance, _args[0]);
    }

    private static Struct compareTo(CharSequenceStruct _charSequence, Struct _anotherString) {
        if (!(_anotherString instanceof CharSequenceStruct)) {
            return null;
        }
        CharSequenceStruct st_ = NumParsers.getCharSeq(_anotherString);
        return new IntStruct(StringUtil.compareStrings(_charSequence.toStringInstance(),st_.toStringInstance()));
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return compareTo((CharSequenceStruct) _instance, _firstArgs.getArgumentWrappers().get(0).getValue().getStruct(), _cont, _stackCall);
    }

    private static ArgumentWrapper compareTo(CharSequenceStruct _charSequence, Struct _anotherString, ContextEl _context, StackCall _stackCall) {
        if (!(_anotherString instanceof CharSequenceStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_context, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        CharSequenceStruct st_ = NumParsers.getCharSeq(_anotherString);
        return new ArgumentWrapper(new IntStruct(StringUtil.compareStrings(_charSequence.toStringInstance(),st_.toStringInstance())));
    }

}
