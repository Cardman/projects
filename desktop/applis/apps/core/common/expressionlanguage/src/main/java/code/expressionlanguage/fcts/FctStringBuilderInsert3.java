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

public final class FctStringBuilderInsert3 implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringBuilderStruct inst_ = (StringBuilderStruct) _instance;
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct index_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct arr_ = argumentWrappers_.get(1).getValue().getStruct();
        Struct start_ = argumentWrappers_.get(2).getValue().getStruct();
        Struct end_ = argumentWrappers_.get(3).getValue().getStruct();
        insertChars(inst_,NumParsers.convertToNumber(index_), arr_,NumParsers.convertToNumber(start_),NumParsers.convertToNumber(end_),_cont,_stackCall);
        return new ArgumentWrapper(inst_);
    }

    private static void insertChars(StringBuilderStruct _instance, NumberStruct _index, Struct _str, NumberStruct _offset, NumberStruct _len, ContextEl _an, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        int index_ = _index.intStruct();
        if (index_ < 0 || index_ > _instance.getInstance().length()) {
            if (index_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_an, AliasCharSequenceType.getBeginMessage(index_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_an, AliasCharSequenceType.getEndMessage(index_, ">", _instance.getInstance().length()), _stackCall)));
            }
            return;
        }
        if (!(_str instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_an, _stackCall)));
            return;
        }
        int lenArr_ = ((ArrayStruct)_str).getLength();
        char[] chars_ = new char[lenArr_];
        for (int i = 0; i < lenArr_; i++) {
            chars_[i] = NumParsers.convertToChar(((ArrayStruct)_str).get(i)).getChar();
        }
        int offset_ = _offset.intStruct();
        int len_ = _len.intStruct();
        if (offset_ < 0 || len_ < 0 || offset_ + len_ > chars_.length) {
            if (offset_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_an, AliasCharSequenceType.getBeginMessage(offset_), _stackCall)));
            } else if (len_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_an, AliasCharSequenceType.getBeginMessage(len_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_an, AliasCharSequenceType.getBeginEndMessage(chars_.length, offset_, len_), _stackCall)));
            }
            return;
        }
        _instance.getInstance().insert(index_, chars_, offset_, len_);
    }
}
