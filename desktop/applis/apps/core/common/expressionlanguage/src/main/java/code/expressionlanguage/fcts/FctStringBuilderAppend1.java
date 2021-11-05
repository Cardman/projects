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
import code.expressionlanguage.structs.StringBuilderStruct;
import code.expressionlanguage.structs.Struct;

public final class FctStringBuilderAppend1 implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringBuilderStruct inst_ = (StringBuilderStruct) _instance;
        Struct arr_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        appendChars(inst_,arr_,_cont,_stackCall);
        return new ArgumentWrapper(inst_);
    }

    private static void appendChars(StringBuilderStruct _instance, Struct _str, ContextEl _an, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        if (!(_str instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNpe(_an, _stackCall)));
            return;
        }
        int len_ =  ((ArrayStruct)_str).getLength();
        char[] chars_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            chars_[i] = NumParsers.convertToChar(((ArrayStruct)_str).get(i)).getChar();
        }
        _instance.getInstance().append(chars_);
    }
}
