package code.expressionlanguage.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.SymbolOperation;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.AliasNumber;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.*;
import code.util.StringList;

public abstract class ExecNumericOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
    private int opOffset;

    public ExecNumericOperation(SymbolOperation _n, OperationNode _op) {
        super(_op);
        opOffset = _n.getOpOffset();
    }

    static Argument calculateAffect(Argument _left, ContextEl _conf, Argument _right, String _op, boolean _catString, ClassArgumentMatching _arg) {
        ResultErrorStd res_= new ResultErrorStd();
        calculateOperator(_conf.getLastPage(),_conf, res_, _arg, _op, _catString, _left.getStruct(), _right.getStruct());
        return new Argument(res_.getResult());
    }
    public static Argument calculateIncrDecr(Argument _left,ContextEl _conf, String _op, ClassArgumentMatching _arg) {
        Argument o_;
        if (StringList.quickEq(_op, Block.INCR)) {
            o_ = new Argument(ExecAddOperation.addOne(ClassArgumentMatching.convertToNumber(_left.getStruct()), _conf, _arg));
        } else {
            o_ = new Argument(ExecAddOperation.removeOne(ClassArgumentMatching.convertToNumber(_left.getStruct()), _conf, _arg));
        }
        return o_;
    }

    public static Argument calculateDivEx(Argument _a, ContextEl _cont, Argument _b,ClassArgumentMatching _order) {
        LgNames stds_ = _cont.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        Struct res_ = AliasNumber.calculateDiv(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                ClassArgumentMatching.convertToNumber(_b.getStruct()), _order, _cont.getStandards());
        if (res_ == NullStruct.NULL_VALUE) {
            _cont.setException(new ErrorStruct(_cont,div_));
        }
        return new Argument(res_);
    }
    public static Argument calculateModEx(Argument _a, ContextEl _cont, Argument _b,ClassArgumentMatching _order) {
        LgNames stds_ = _cont.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        Struct res_ = AliasNumber.calculateMod(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                ClassArgumentMatching.convertToNumber(_b.getStruct()), _order, _cont.getStandards());
        if (res_ == NullStruct.NULL_VALUE) {
            _cont.setException(new ErrorStruct(_cont,div_));
        }
        return new Argument(res_);
    }

    public static void calculateOperator(PageEl _page, ContextEl _cont, ResultErrorStd _res, ClassArgumentMatching _order,
                                         String _op, boolean _catString,
                                         Struct _first, Struct _second) {
        if (_catString) {
            catenize(_cont, _res, _first, _second);
            return;
        }
        String op_ = _op.substring(0, _op.length() - 1);
        if (StringList.quickEq(op_, "+")) {
            _res.setResult(AliasNumber.calculateSum(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _order, _cont.getStandards()));
            return;
        }
        if (StringList.quickEq(op_, "-")) {
            _res.setResult(AliasNumber.calculateDiff(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _order, _cont.getStandards()));
            return;
        }
        if (StringList.quickEq(op_, "*")) {
            _res.setResult(AliasNumber.calculateMult(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _order, _cont.getStandards()));
            return;
        }
        if (StringList.quickEq(op_, "/")) {
            _res.setResult(calculateDivEx(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "%")) {
            _res.setResult(calculateModEx(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _cont, _order));
            return;
        }
        if (StringList.quickEq(op_, "&")) {
            _res.setResult(AliasNumber.calculateAnd(_first, _second, _order, _cont.getStandards()));
            return;
        }
        if (StringList.quickEq(op_, "|")) {
            _res.setResult(AliasNumber.calculateOr(_first, _second, _order, _cont.getStandards()));
            return;
        }
        if (StringList.quickEq(op_, "^")) {
            _res.setResult(AliasNumber.calculateXor(_first, _second, _order, _cont.getStandards()));
            return;
        }
        if (StringList.quickEq(op_, "<<")) {
            _res.setResult(AliasNumber.calculateShiftLeft(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _order, _cont.getStandards()));
            return;
        }
        if (StringList.quickEq(op_, ">>")) {
            _res.setResult(AliasNumber.calculateShiftRight(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _order, _cont.getStandards()));
            return;
        }
        if (StringList.quickEq(op_, "<<<")) {
            _res.setResult(AliasNumber.calculateBitShiftLeft(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _order, _cont.getStandards()));
            return;
        }
        if (StringList.quickEq(op_, ">>>")) {
            _res.setResult(AliasNumber.calculateBitShiftRight(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _order, _cont.getStandards()));
            return;
        }
        if (StringList.quickEq(op_, "<<<<")) {
            _res.setResult(AliasNumber.calculateRotateLeft(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _order, _cont.getStandards()));
            return;
        }
        if (StringList.quickEq(op_, ">>>>")) {
            _res.setResult(AliasNumber.calculateRotateRight(ClassArgumentMatching.convertToNumber(_first), ClassArgumentMatching.convertToNumber(_second), _order, _cont.getStandards()));
            return;
        }
        if (StringList.quickEq(op_, "??")) {
            if (_first != NullStruct.NULL_VALUE) {
                _res.setResult(ClassArgumentMatching.convert(_page,_order, _first,_cont));
                return;
            }
            _res.setResult(ClassArgumentMatching.convert(_page,_order, _second,_cont));
            return;
        }
        if (StringList.quickEq(op_, "&&")) {
            BooleanStruct b_ = ClassArgumentMatching.convertToBoolean(_first);
            if (BooleanStruct.isFalse(b_)) {
                _res.setResult(b_);
                return;
            }
            _res.setResult(ClassArgumentMatching.convertToBoolean(_second));
            return;
        }
        BooleanStruct b_ = ClassArgumentMatching.convertToBoolean(_first);
        if (BooleanStruct.isTrue(b_)) {
            _res.setResult(b_);
            return;
        }
        _res.setResult(ClassArgumentMatching.convertToBoolean(_second));
    }

    private static void catenize(ContextEl _cont, ResultErrorStd _res, Struct _first, Struct _second) {
      Argument arg_ = ExecCatOperation.localSumDiff(new Argument(_first), new Argument(_second), _cont);
      _res.setResult(arg_.getStruct());
  }

    private static Struct calculateDivEx(NumberStruct _a, NumberStruct _b, ContextEl _an, ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        Struct res_ = AliasNumber.calculateDiv(_a,_b, _order, _an.getStandards());
        if (res_ == NullStruct.NULL_VALUE) {
            _an.setException(new ErrorStruct(_an,div_));
        }
        return res_;
    }

    private static Struct calculateModEx(NumberStruct _a, NumberStruct _b, ContextEl _an, ClassArgumentMatching _order) {
        LgNames stds_ = _an.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        Struct res_ = AliasNumber.calculateMod(_a,_b, _order, _an.getStandards());
        if (res_ == NullStruct.NULL_VALUE) {
            _an.setException(new ErrorStruct(_an,div_));
        }
        return res_;
    }

    public int getOpOffset() {
        return opOffset;
    }
}
