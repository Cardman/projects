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
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringBuilderStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctStringBuilderAppend3 implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringBuilderStruct inst_ = (StringBuilderStruct) _instance;
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct arr_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct start_ = argumentWrappers_.get(1).getValue().getStruct();
        Struct end_ = argumentWrappers_.get(2).getValue().getStruct();
        appendChars(inst_, arr_,NumParsers.convertToNumber(start_),NumParsers.convertToNumber(end_),_cont,_stackCall);
        return new ArgumentWrapper(inst_);
    }

    private static void appendChars(StringBuilderStruct _instance, Struct _str, NumberStruct _offset, NumberStruct _len, ContextEl _an, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        if (!(_str instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_an, _stackCall)));
            return;
        }
        int offset_ = _offset.intStruct();
        int len_ = _len.intStruct();
        int lenChar_ = ((ArrayStruct)_str).getLength();
        if (offset_ < 0 || len_ < 0 || offset_ + len_ > lenChar_) {
            if (offset_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_an, AliasCharSequenceType.getBeginMessage(offset_), _stackCall)));
            } else if (len_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_an, AliasCharSequenceType.getBeginMessage(len_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_an, AliasCharSequenceType.getBeginEndMessage(lenChar_, offset_, len_), _stackCall)));
            }
            return;
        }
        char[] chars_ = new char[lenChar_];
        for (int i = 0; i < lenChar_; i++) {
            chars_[i] = NumParsers.convertToChar(((ArrayStruct)_str).get(i)).getChar();
        }
        _instance.getInstance().append(chars_, offset_, len_);
    }
}
