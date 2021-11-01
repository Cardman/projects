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

public final class FctCharSeqIndexOf3 implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return indexOfString((CharSequenceStruct) _instance, _args[0], NumParsers.convertToNumber(_args[1]));
    }

    public static Struct indexOfString(CharSequenceStruct _charSequence, Struct _str) {
        if (!(_str instanceof CharSequenceStruct)) {
            return null;
        }
        CharSequenceStruct str_ = NumParsers.getCharSeq(_str);
        return new IntStruct(_charSequence.toStringInstance().indexOf(str_.toStringInstance()));
    }

    public static Struct indexOfString(CharSequenceStruct _charSequence, Struct _str, NumberStruct _fromIndex) {
        int from_ = _fromIndex.intStruct();
        if (!(_str instanceof CharSequenceStruct)) {
            return null;
        }
        CharSequenceStruct str_ = NumParsers.getCharSeq(_str);
        return new IntStruct(_charSequence.toStringInstance().indexOf(str_.toStringInstance(), from_));
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct one_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct two_ = argumentWrappers_.get(1).getValue().getStruct();
        return indexOfString((CharSequenceStruct) _instance, one_, NumParsers.convertToNumber(two_), _cont, _stackCall);
    }

    public static ArgumentWrapper indexOfString(CharSequenceStruct _charSequence, Struct _str, ContextEl _context, StackCall _stackCall) {
        if (!(_str instanceof CharSequenceStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_context, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        CharSequenceStruct str_ = NumParsers.getCharSeq(_str);
        return new ArgumentWrapper(new IntStruct(_charSequence.toStringInstance().indexOf(str_.toStringInstance())));
    }

    public static ArgumentWrapper indexOfString(CharSequenceStruct _charSequence, Struct _str, NumberStruct _fromIndex, ContextEl _context, StackCall _stackCall) {
        int from_ = _fromIndex.intStruct();
        if (!(_str instanceof CharSequenceStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_context, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        CharSequenceStruct str_ = NumParsers.getCharSeq(_str);
        return new ArgumentWrapper(new IntStruct(_charSequence.toStringInstance().indexOf(str_.toStringInstance(), from_)));
    }
}
