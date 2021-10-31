package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AliasReflection;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public final class FctString2 implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct argArr_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        if (!(argArr_ instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_cont, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        ArrayStruct chArr_ = (ArrayStruct) argArr_;
        int len_ = chArr_.getLength();
        char[] arr_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            arr_[i] = NumParsers.convertToChar(chArr_.get(i)).getChar();
        }
        return new ArgumentWrapper(new StringStruct(String.valueOf(arr_)));
    }
}
