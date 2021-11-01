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
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.CharSequenceStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class FctCharSeqEndsWith implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return endsWith((CharSequenceStruct) _instance,_args[0]);
    }

    private static Struct endsWith(CharSequenceStruct _charSequence, Struct _suffix) {
        if (!(_suffix instanceof CharSequenceStruct)) {
            return null;
        }
        CharSequenceStruct suffix_ = NumParsers.getCharSeq(_suffix);
        return BooleanStruct.of(_charSequence.toStringInstance().endsWith(suffix_.toStringInstance()));
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return endsWith((CharSequenceStruct) _instance,_firstArgs.getArgumentWrappers().get(0).getValue().getStruct(),_cont,_stackCall);
    }

    private static ArgumentWrapper endsWith(CharSequenceStruct _charSequence, Struct _suffix, ContextEl _context, StackCall _stackCall) {
        if (!(_suffix instanceof CharSequenceStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_context, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        CharSequenceStruct suffix_ = NumParsers.getCharSeq(_suffix);
        return new ArgumentWrapper(BooleanStruct.of(_charSequence.toStringInstance().endsWith(suffix_.toStringInstance())));
    }
}
