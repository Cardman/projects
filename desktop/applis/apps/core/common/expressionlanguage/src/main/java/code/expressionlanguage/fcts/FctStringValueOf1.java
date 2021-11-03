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
import code.expressionlanguage.stds.AliasReflection;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctStringValueOf1 implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        Struct arr_ = _args[2];
        if (!(arr_ instanceof ArrayStruct)) {
            return null;
        }
        char[] chars_ = toCharArr((ArrayStruct) arr_);
        int one_ = NumParsers.convertToNumber(_args[0]).intStruct();
        int two_ = NumParsers.convertToNumber(_args[1]).intStruct();
        if (NumParsers.koArray(chars_, one_, two_)) {
            return null;
        }
        return new StringStruct(String.valueOf(chars_,one_,two_));
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct arr_ = argumentWrappers_.get(2).getValue().getStruct();
        if (!(arr_ instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_cont, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        char[] chars_ = toCharArr((ArrayStruct) arr_);
        int one_ = NumParsers.convertToNumber(argumentWrappers_.get(0).getValue().getStruct()).intStruct();
        int two_ = NumParsers.convertToNumber(argumentWrappers_.get(1).getValue().getStruct()).intStruct();
        if (NumParsers.koArray(chars_, one_, two_)) {
            if (one_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_cont, AliasCharSequenceType.getBeginMessage(one_), _stackCall)));
            } else if (two_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_cont, AliasCharSequenceType.getBeginMessage(two_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_cont, AliasCharSequenceType.getBeginEndMessage(chars_.length, one_, two_), _stackCall)));
            }
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(new StringStruct(String.valueOf(chars_,one_,two_)));
    }
    public static char[] toCharArr(ArrayStruct _arg) {
        int len_ = _arg.getLength();
        char[] arr_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            arr_[i] = NumParsers.convertToChar(_arg.get(i)).getChar();
        }
        return arr_;
    }
}
