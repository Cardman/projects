package code.expressionlanguage.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.*;
import code.util.StringList;

public abstract class ExecNumericOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
    private static final String INCR = "++";
    private int opOffset;

    protected ExecNumericOperation(ExecOperationContent _opCont, int _opOffset) {
        super(_opCont);
        opOffset = _opOffset;
    }

    static Argument calculateAffect(Argument _left, ContextEl _conf, Argument _right, String _op, boolean _catString, StringList _cls, byte _cast) {
        ResultErrorStd res_= new ResultErrorStd();
        calculateOperator(_conf.getLastPage(),_conf, res_, _op, _catString, _left.getStruct(), _right.getStruct(), _cls, _cast);
        return new Argument(res_.getResult());
    }
    public static Argument calculateIncrDecr(Argument _left, String _op, byte _cast) {
        Argument o_;
        if (StringList.quickEq(_op, INCR)) {
            o_ = new Argument(ExecAddOperation.addOne(NumParsers.convertToNumber(_left.getStruct()), _cast));
        } else {
            o_ = new Argument(ExecAddOperation.removeOne(NumParsers.convertToNumber(_left.getStruct()), _cast));
        }
        return o_;
    }

    public static Argument calculateDivEx(Argument _a, ContextEl _cont, Argument _b, byte _cast) {
        Struct res_ = calculateDivEx(NumParsers.convertToNumber(_a.getStruct()), NumParsers.convertToNumber(_b.getStruct()), _cont, _cast);
        return new Argument(res_);
    }
    public static Argument calculateModEx(Argument _a, ContextEl _cont, Argument _b, byte _cast) {
        Struct res_ = calculateModEx(NumParsers.convertToNumber(_a.getStruct()), NumParsers.convertToNumber(_b.getStruct()), _cont, _cast);
        return new Argument(res_);
    }

    public static void calculateOperator(PageEl _page, ContextEl _cont, ResultErrorStd _res,
                                         String _op, boolean _catString,
                                         Struct _first, Struct _second, StringList _cls, byte _cast) {
        if (_catString) {
            catenize(_cont, _res, _first, _second);
            return;
        }
        String op_ = _op.substring(0, _op.length() - 1);
        if (StringList.quickEq(op_, "+")) {
            _res.setResult(NumParsers.calculateSum(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (StringList.quickEq(op_, "-")) {
            _res.setResult(NumParsers.calculateDiff(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (StringList.quickEq(op_, "*")) {
            _res.setResult(NumParsers.calculateMult(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (StringList.quickEq(op_, "/")) {
            _res.setResult(calculateDivEx(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cont, _cast));
            return;
        }
        if (StringList.quickEq(op_, "%")) {
            _res.setResult(calculateModEx(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cont, _cast));
            return;
        }
        if (StringList.quickEq(op_, "&")) {
            _res.setResult(NumParsers.calculateAnd(_first, _second, _cast));
            return;
        }
        if (StringList.quickEq(op_, "|")) {
            _res.setResult(NumParsers.calculateOr(_first, _second, _cast));
            return;
        }
        if (StringList.quickEq(op_, "^")) {
            _res.setResult(NumParsers.calculateXor(_first, _second, _cast));
            return;
        }
        if (StringList.quickEq(op_, "<<")) {
            _res.setResult(NumParsers.calculateShiftLeft(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (StringList.quickEq(op_, ">>")) {
            _res.setResult(NumParsers.calculateShiftRight(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (StringList.quickEq(op_, "<<<")) {
            _res.setResult(NumParsers.calculateBitShiftLeft(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (StringList.quickEq(op_, ">>>")) {
            _res.setResult(NumParsers.calculateBitShiftRight(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (StringList.quickEq(op_, "<<<<")) {
            _res.setResult(NumParsers.calculateRotateLeft(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (StringList.quickEq(op_, ">>>>")) {
            _res.setResult(NumParsers.calculateRotateRight(NumParsers.convertToNumber(_first), NumParsers.convertToNumber(_second), _cast));
            return;
        }
        if (StringList.quickEq(op_, "??")) {
            if (_first != NullStruct.NULL_VALUE) {
                _res.setResult(ExecClassArgumentMatching.convert(_page, _first,_cont, _cls));
                return;
            }
            _res.setResult(ExecClassArgumentMatching.convert(_page, _second,_cont, _cls));
            return;
        }
        if (StringList.quickEq(op_, "&&")) {
            BooleanStruct b_ = NumParsers.convertToBoolean(_first);
            if (BooleanStruct.isFalse(b_)) {
                _res.setResult(b_);
                return;
            }
            _res.setResult(NumParsers.convertToBoolean(_second));
            return;
        }
        BooleanStruct b_ = NumParsers.convertToBoolean(_first);
        if (BooleanStruct.isTrue(b_)) {
            _res.setResult(b_);
            return;
        }
        _res.setResult(NumParsers.convertToBoolean(_second));
    }

    private static void catenize(ContextEl _cont, ResultErrorStd _res, Struct _first, Struct _second) {
      Argument arg_ = ExecCatOperation.localSumDiff(new Argument(_first), new Argument(_second), _cont);
      _res.setResult(arg_.getStruct());
  }

    private static Struct calculateDivEx(NumberStruct _a, NumberStruct _b, ContextEl _an, byte _cast) {
        LgNames stds_ = _an.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        Struct res_ = NumParsers.calculateDiv(_a,_b, _cast);
        if (res_ == NullStruct.NULL_VALUE) {
            _an.setException(new ErrorStruct(_an,div_));
        }
        return res_;
    }

    private static Struct calculateModEx(NumberStruct _a, NumberStruct _b, ContextEl _an, byte _cast) {
        LgNames stds_ = _an.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        Struct res_ = NumParsers.calculateMod(_a,_b, _cast);
        if (res_ == NullStruct.NULL_VALUE) {
            _an.setException(new ErrorStruct(_an,div_));
        }
        return res_;
    }

    public int getOpOffset() {
        return opOffset;
    }
}
