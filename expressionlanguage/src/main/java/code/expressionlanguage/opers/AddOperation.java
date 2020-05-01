package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.exec.ExecCatOperation;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.structs.NumberStruct;
import code.util.StringList;



public final class AddOperation extends NumericOperation {

    private boolean catString;

    public AddOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    Argument calculateOperAna(Argument _a, String _op, Argument _b, Analyzable _cont) {
        return localSumDiff(_a, _op, _b, _cont.getContextEl());
    }

    private Argument localSumDiff(Argument _a, String _op, Argument _b,
            ExecutableCode _cont) {
        if (StringList.quickEq(_op.trim(), PLUS)) {
            if (catString) {
                return ExecCatOperation.localSumDiff(_a,_b,_cont);
            }
            return new Argument(NumberStruct.calculateSum(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                    ClassArgumentMatching.convertToNumber(_b.getStruct()), _cont, getResultClass()));
        }
        return new Argument(NumberStruct.calculateDiff(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                ClassArgumentMatching.convertToNumber(_b.getStruct()), _cont, getResultClass()));
    }
    @Override
    ResultOperand analyzeOper(ClassArgumentMatching _a, String _op, ClassArgumentMatching _b, Analyzable _cont) {
        ResultOperand res_ = new ResultOperand();
        String stringType_ = _cont.getStandards().getAliasString();
        if (StringList.quickEq(_op.trim(), PLUS)) {
            int oa_ = PrimitiveTypeUtil.getOrderClass(_a, _cont);
            int ob_ = PrimitiveTypeUtil.getOrderClass(_b, _cont);
            if (oa_ > 0 && ob_ > 0) {
                ClassArgumentMatching out_ = getQuickResultClass(_a, oa_, _cont, _b, ob_);
                _a.setUnwrapObject(out_);
                _b.setUnwrapObject(out_);
                res_.setResult(out_);
                return res_;
            }
            boolean str_ = false;
            if (_a.matchClass(stringType_) || _a.isVariable()) {
                str_ = true;
            }
            if (_b.matchClass(stringType_) || _b.isVariable()) {
                str_ = true;
            }
            if (str_) {
                _a.setConvertToString(true);
                _b.setConvertToString(true);
                res_.setResult(new ClassArgumentMatching(stringType_));
                res_.setCatString(true);
                return res_;
            }
            _cont.setOkNumOp(false);
            String exp_ = _cont.getStandards().getAliasNumber();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_cont.getCurrentLocationIndex());
            un_.setFileName(_cont.getCurrentFileName());
            //oper
            un_.buildError(_cont.getContextEl().getAnalysisMessages().getUnexpectedOperandTypes(),
                    StringList.join(new StringList(
                            StringList.join(_a.getNames(),"&"),
                            StringList.join(_b.getNames(),"&")
                    ),";"),
                    getOp());
            _cont.addError(un_);
            ClassArgumentMatching arg_ = new ClassArgumentMatching(exp_);
            res_.setResult(arg_);
            return res_;
        }
        ClassArgumentMatching out_ = getResultClass(_a, _cont, _b);
        _a.setUnwrapObject(out_);
        _b.setUnwrapObject(out_);
        res_.setResult(out_);
        return res_;
    }

    @Override
    void setCatenize(ResultOperand _res) {
        catString = _res.isCatString();
    }

    public boolean isCatString() {
        return catString;
    }
}
