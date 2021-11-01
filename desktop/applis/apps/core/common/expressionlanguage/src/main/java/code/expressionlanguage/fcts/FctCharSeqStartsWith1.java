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
import code.expressionlanguage.structs.*;
import code.util.CustList;

public final class FctCharSeqStartsWith1 implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return startsWith((CharSequenceStruct) _instance, _args[0], NumParsers.convertToNumber(_args[1]));
    }

    public static Struct startsWith(CharSequenceStruct _charSequence, Struct _prefix) {
        return startsWith(_charSequence, _prefix,new IntStruct(0));
    }

    public static Struct startsWith(CharSequenceStruct _charSequence, Struct _prefix, NumberStruct _toffset) {
        if (!(_prefix instanceof CharSequenceStruct)) {
            return null;
        }
        CharSequenceStruct pref_ = NumParsers.getCharSeq(_prefix);
        int to_ = _toffset.intStruct();
        return BooleanStruct.of(_charSequence.toStringInstance().startsWith(pref_.toStringInstance(), to_));
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct one_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct two_ = argumentWrappers_.get(1).getValue().getStruct();
        return startsWith((CharSequenceStruct) _instance, one_, NumParsers.convertToNumber(two_), _cont, _stackCall);
    }


    public static ArgumentWrapper startsWith(CharSequenceStruct _charSequence, Struct _prefix, ContextEl _context, StackCall _stackCall) {
        return startsWith(_charSequence, _prefix,new IntStruct(0), _context, _stackCall);
    }

    public static ArgumentWrapper startsWith(CharSequenceStruct _charSequence, Struct _prefix, NumberStruct _toffset, ContextEl _context, StackCall _stackCall) {
        if (!(_prefix instanceof CharSequenceStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_context, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        CharSequenceStruct pref_ = NumParsers.getCharSeq(_prefix);
        int to_ = _toffset.intStruct();
        return new ArgumentWrapper(BooleanStruct.of(_charSequence.toStringInstance().startsWith(pref_.toStringInstance(), to_)));
    }
}
