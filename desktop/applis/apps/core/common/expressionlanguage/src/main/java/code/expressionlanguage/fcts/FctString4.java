package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AliasCharSequenceType;
import code.expressionlanguage.stds.AliasReflection;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctString4 implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct offset_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct to_ = argumentWrappers_.get(1).getValue().getStruct();
        Struct argArr_ = argumentWrappers_.get(2).getValue().getStruct();
        if (!(argArr_ instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_cont, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        ArrayStruct chArr_ = (ArrayStruct) argArr_;
        int len_ = chArr_.getLength();
        int one_ = NumParsers.convertToNumber(offset_).intStruct();
        int two_ = NumParsers.convertToNumber(to_).intStruct();
        if (one_ < 0 || two_ < 0 || one_ > len_ - two_) {
            if (one_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_cont, AliasCharSequenceType.getBeginMessage(one_), _stackCall)));
            } else if (two_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_cont, AliasCharSequenceType.getBeginMessage(two_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_cont, AliasCharSequenceType.getBeginEndMessage(len_, one_, two_), _stackCall)));
            }
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        char[] arr_ = new char[two_];
        int until_ = one_ + two_;
        for (int i = one_; i < until_; i++) {
            arr_[i-one_] = NumParsers.convertToChar(chArr_.get(i)).getChar();
        }
        return new ArgumentWrapper(new StringStruct(String.valueOf(arr_)));
    }
}
