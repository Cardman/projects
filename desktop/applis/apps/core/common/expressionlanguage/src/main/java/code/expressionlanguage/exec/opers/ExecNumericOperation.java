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
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.*;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class ExecNumericOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
    private static final String INCR = "++";
    private final int opOffset;

    protected ExecNumericOperation(ExecOperationContent _opCont, int _opOffset) {
        super(_opCont);
        opOffset = _opOffset;
    }

    static Argument calculateAffect(Argument _left, ContextEl _conf, Argument _right, String _op, byte _cast, StackCall _stackCall) {
        ResultErrorStd res_= new ResultErrorStd();
        calculateOperator(_conf, res_, _op, _left.getStruct(), _right.getStruct(), _cast, _stackCall);
        return new Argument(res_.getResult());
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

    public static Argument calculateIncrDecr(Argument _left, String _op, byte _cast) {
        Argument o_;
        if (StringUtil.quickEq(_op, INCR)) {
            o_ = new Argument(ExecAddOperation.addOne(NumParsers.convertToNumber(_left.getStruct()), _cast));
        } else {
            o_ = new Argument(ExecAddOperation.removeOne(NumParsers.convertToNumber(_left.getStruct()), _cast));
        }
        return o_;
    }

    public static void calculateOperator(ContextEl _cont, ResultErrorStd _res,
                                         String _op,
                                         Struct _first, Struct _second, byte _cast, StackCall _stackCall) {
        String op_ = _op.substring(0, _op.length() - 1);
        if (StringUtil.quickEq(op_, "+")) {
            _res.setResult(NumParsers.calculateSum(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (StringUtil.quickEq(op_, "-")) {
            _res.setResult(NumParsers.calculateDiff(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (StringUtil.quickEq(op_, "*")) {
            _res.setResult(NumParsers.calculateMult(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (StringUtil.quickEq(op_, "/")) {
            _res.setResult(calculateDivEx(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cont, _cast, _stackCall));
            return;
        }
        if (StringUtil.quickEq(op_, "%")) {
            _res.setResult(calculateModEx(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cont, _cast, _stackCall));
            return;
        }
        if (StringUtil.quickEq(op_, "&")) {
            _res.setResult(NumParsers.calculateAnd(_first, _second, _cast));
            return;
        }
        if (StringUtil.quickEq(op_, "|")) {
            _res.setResult(NumParsers.calculateOr(_first, _second, _cast));
            return;
        }
        if (StringUtil.quickEq(op_, "^")) {
            _res.setResult(NumParsers.calculateXor(_first, _second, _cast));
            return;
        }
        if (StringUtil.quickEq(op_, "<<")) {
            _res.setResult(NumParsers.calculateShiftLeft(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (StringUtil.quickEq(op_, ">>")) {
            _res.setResult(NumParsers.calculateShiftRight(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (StringUtil.quickEq(op_, "<<<")) {
            _res.setResult(NumParsers.calculateBitShiftLeft(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (StringUtil.quickEq(op_, ">>>")) {
            _res.setResult(NumParsers.calculateBitShiftRight(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (StringUtil.quickEq(op_, "<<<<")) {
            _res.setResult(NumParsers.calculateRotateLeft(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (StringUtil.quickEq(op_, ">>>>")) {
            _res.setResult(NumParsers.calculateRotateRight(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (isAndLogic(op_)) {
            andLogic(_res, _first, _second);
            return;
        }
        orLogic(_res, _first, _second);
    }

    private static boolean isAndLogic(String _op) {
        return StringUtil.quickEq(_op, "&&") || StringUtil.quickEq(_op, "&&&");
    }

    private static void orLogic(ResultErrorStd _res, Struct _first, Struct _second) {
        if (BooleanStruct.isTrue(_first)) {
            _res.setResult(NumParsers.convertToBoolean(_first));
            return;
        }
        _res.setResult(NumParsers.convertToBoolean(_second));
    }

    private static void andLogic(ResultErrorStd _res, Struct _first, Struct _second) {
        if (BooleanStruct.isFalse(_first)) {
            _res.setResult(NumParsers.convertToBoolean(_first));
            return;
        }
        _res.setResult(NumParsers.convertToBoolean(_second));
    }

    public static Struct calculateDivEx(NumberStruct _a, NumberStruct _b, ContextEl _an, byte _cast, StackCall _stackCall) {
        LgNames stds_ = _an.getStandards();
        String div_;
        div_ = stds_.getContent().getCoreNames().getAliasDivisionZero();
        Struct res_ = NumParsers.calculateDiv(_a,_b, _cast);
        if (res_ == NullStruct.NULL_VALUE) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_an, div_, _stackCall)));
        }
        return res_;
    }

    public static Struct calculateModEx(NumberStruct _a, NumberStruct _b, ContextEl _an, byte _cast, StackCall _stackCall) {
        LgNames stds_ = _an.getStandards();
        String div_;
        div_ = stds_.getContent().getCoreNames().getAliasDivisionZero();
        Struct res_ = NumParsers.calculateMod(_a,_b, _cast);
        if (res_ == NullStruct.NULL_VALUE) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_an, div_, _stackCall)));
        }
        return res_;
    }

    public int getOpOffset() {
        return opOffset;
    }
}
