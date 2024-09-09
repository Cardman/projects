package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.CharSequenceStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctCharSeqLastIndexOf2 implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return comLast((CharSequenceStruct) _instance, _args[0], _args[1]);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct one_ = argumentWrappers_.get(0).getValue();
        Struct two_ = argumentWrappers_.get(1).getValue();
        return new ArgumentWrapper(comLast((CharSequenceStruct) _instance, one_, two_));
    }

    public static IntStruct comLast(CharSequenceStruct _charSequence, Struct _ch) {
        return comLast(_charSequence,_ch,new IntStruct(_charSequence.length()));
    }
    public static IntStruct comLast(CharSequenceStruct _charSequence, Struct _ch, Struct _fromIndex) {
        NumberStruct ch_ = NumParsers.convertToNumber(_ch);
        int int_ = ch_.intStruct();
        NumberStruct index_ = NumParsers.convertToNumber(_fromIndex);
        int from_ = index_.intStruct();
        return new IntStruct(_charSequence.toStringInstance().lastIndexOf(int_, from_));
    }
}
