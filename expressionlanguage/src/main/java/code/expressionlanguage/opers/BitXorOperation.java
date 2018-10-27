package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ResultOperand;

public final class BitXorOperation extends NumericOperation {

    public BitXorOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    ResultOperand analyzeOper(ClassArgumentMatching _a, String _op,
            ClassArgumentMatching _b, Analyzable _cont) {
        ResultOperand res_ = new ResultOperand();
        if (_a.isBoolType(_cont) && _b.isBoolType(_cont)) {
            String bool_ = _cont.getStandards().getAliasPrimBoolean();
            _a.setUnwrapObject(bool_);
            _b.setUnwrapObject(bool_);
            res_.setResult(new ClassArgumentMatching(bool_));
            return res_;
        }
        int oa_ = PrimitiveTypeUtil.getIntOrderClass(_a, _cont);
        int ob_ = PrimitiveTypeUtil.getIntOrderClass(_b, _cont);
        if (oa_ > 0 && ob_ > 0) {
            ClassArgumentMatching out_ = getQuickResultClass(_a, oa_, _cont, _b, ob_);
            _a.setUnwrapObject(out_);
            _b.setUnwrapObject(out_);
            res_.setResult(out_);
            return res_;
        }
        String exp_ = _cont.getStandards().getAliasNumber();
        UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
        un_.setRc(_cont.getCurrentLocation());
        un_.setFileName(_cont.getCurrentFileName());
        un_.setExpectedResult(exp_);
        un_.setOperands(_a,_b);
        _cont.getClasses().addError(un_);
        ClassArgumentMatching arg_ = new ClassArgumentMatching(exp_);
        res_.setResult(arg_);
        return res_;
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ExecutableCode _cont) {
        return calculateXor(_a, _b, _cont, getResultClass());
    }

    @Override
    Argument calculateOperAna(Argument _a, String _op, Argument _b,
            Analyzable _an) {
        if (_a.isNull() || _b.isNull()) {
            return Argument.createVoid();
        }
        return calculateXor(_a, _b, _an, getResultClass());
    }

    @Override
    void setCatenize(ResultOperand _res) {
    }

}
