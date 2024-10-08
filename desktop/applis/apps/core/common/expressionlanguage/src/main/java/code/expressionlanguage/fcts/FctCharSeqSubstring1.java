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
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctCharSeqSubstring1 implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return FctCharSeqSubstring0.substring((CharSequenceStruct) _instance, NumParsers.convertToNumber(_args[0]), NumParsers.convertToNumber(_args[1]));
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct one_ = argumentWrappers_.get(0).getValue();
        Struct two_ = argumentWrappers_.get(1).getValue();
        return FctCharSeqSubstring0.substring((CharSequenceStruct) _instance, NumParsers.convertToNumber(one_), NumParsers.convertToNumber(two_), _cont, _stackCall);
    }
}
