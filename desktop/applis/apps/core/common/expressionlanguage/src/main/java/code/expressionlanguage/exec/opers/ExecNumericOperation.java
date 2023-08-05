package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;

public abstract class ExecNumericOperation {

    private ExecNumericOperation() {
    }

    public static Struct exc(Struct _res, StackCall _stackCall, ContextEl _an) {
        if (_res == null) {
            CustomFoundExc exc_ = exc(_stackCall, _an);
            _stackCall.setCallingState(exc_);
            return NullStruct.NULL_VALUE;
        }
        return _res;
    }

    public static CustomFoundExc exc(StackCall _stackCall, ContextEl _an) {
        LgNames stds_ = _an.getStandards();
        String div_ = stds_.getContent().getCoreNames().getAliasDivisionZero();
        return new CustomFoundExc(new ErrorStruct(_an, div_, _stackCall));
    }

    public static NumberStruct addOne(NumberStruct _arg, byte _cast) {
        return NumParsers.calculateIncr(_arg, 1, _cast);
    }

    public static NumberStruct removeOne(NumberStruct _arg, byte _cast) {
        return NumParsers.calculateIncr(_arg, -1, _cast);
    }

}
