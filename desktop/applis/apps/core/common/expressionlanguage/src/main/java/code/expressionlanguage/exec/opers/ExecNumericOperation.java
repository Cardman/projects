package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.AbstractStackCall;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.StringList;

public abstract class ExecNumericOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
    private final int opOffset;

    protected ExecNumericOperation(ExecOperationContent _opCont, int _opOffset) {
        super(_opCont);
        opOffset = _opOffset;
    }

    public static Argument calculateAffect(Argument _left, ContextEl _conf, Argument _right, StringList _cls, AbstractStackCall _stackCall) {
        Struct first_ = _left.getStruct();
        Argument res_;
        if (first_ != NullStruct.NULL_VALUE) {
            res_=new Argument(ExecClassArgumentMatching.convertFormatted(first_, _conf, _cls, _stackCall));
        } else {
            res_=new Argument(ExecClassArgumentMatching.convertFormatted(_right.getStruct(), _conf, _cls, _stackCall));
        }
        return res_;
    }

    public static Struct exc(Struct _res, StackCall _stackCall, ContextEl _an) {
        if (_res == NullStruct.NULL_VALUE) {
            LgNames stds_ = _an.getStandards();
            String div_;
            div_ = stds_.getContent().getCoreNames().getAliasDivisionZero();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_an, div_, _stackCall)));
        }
        return _res;
    }

    public static NumberStruct addOne(NumberStruct _arg, byte _cast) {
        return NumParsers.calculateIncr(_arg, 1, _cast);
    }

    public static NumberStruct removeOne(NumberStruct _arg, byte _cast) {
        return NumParsers.calculateIncr(_arg, -1, _cast);
    }

    public int getOpOffset() {
        return opOffset;
    }
}
