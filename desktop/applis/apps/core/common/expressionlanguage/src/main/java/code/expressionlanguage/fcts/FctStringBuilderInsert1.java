package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringBuilderStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctStringBuilderInsert1 implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringBuilderStruct inst_ = (StringBuilderStruct) _instance;
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct index_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct arr_ = argumentWrappers_.get(1).getValue().getStruct();
        insertChars(inst_,NumParsers.convertToNumber(index_),arr_,_cont,_stackCall);
        return new ArgumentWrapper(inst_);
    }

    private static void insertChars(StringBuilderStruct _instance, NumberStruct _offset, Struct _str, ContextEl _an, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        int index_ = _offset.intStruct();
        if (index_ < 0 || index_ > _instance.getInstance().length()) {
            if (index_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_an, FctUtil.getBeginMessage(index_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_an, FctUtil.getEndMessage(index_, ">", _instance.getInstance().length()), _stackCall)));
            }
            return;
        }
        if (!(_str instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNpe(_an, _stackCall)));
            return;
        }
        int len_ = ((ArrayStruct) _str).getLength();
        char[] chars_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            chars_[i] = NumParsers.convertToChar(((ArrayStruct) _str).get(i)).getChar();
        }
        _instance.getInstance().insert(index_, chars_);
    }
}
