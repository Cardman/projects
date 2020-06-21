package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.stds.AliasNumber;
import code.util.StringList;


public final class MultOperation extends NumericOperation {

    public MultOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    Argument calculateOperAna(Argument _a, String _op, Argument _b,
            ContextEl _an) {
        if (StringList.quickEq(_op.trim(), MULT)) {
            return new Argument(AliasNumber.calculateMult(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                    ClassArgumentMatching.convertToNumber(_b.getStruct()), _an, getResultClass()));
        }
        if (StringList.quickEq(_op.trim(), DIV)) {
            return new Argument(AliasNumber.calculateDiv(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                    ClassArgumentMatching.convertToNumber(_b.getStruct()), _an, getResultClass()));
        }
        return new Argument(AliasNumber.calculateMod(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                ClassArgumentMatching.convertToNumber(_b.getStruct()), _an, getResultClass()));
    }

    @Override
    ResultOperand analyzeOper(ClassArgumentMatching _a, String _op, ClassArgumentMatching _b, ContextEl _cont) {
        ResultOperand res_ = new ResultOperand();
        if (PrimitiveTypeUtil.isIntOrderClass(_a,_b,_cont)) {
            ClassArgumentMatching out_ = getIntResultClass(_a, _cont, _b);
            _a.setUnwrapObject(out_);
            _b.setUnwrapObject(out_);
            res_.setResult(out_);
            return res_;
        }
        if (PrimitiveTypeUtil.isFloatOrderClass(_a,_b,_cont)) {
            ClassArgumentMatching out_ = getFloatResultClass(_a, _cont, _b);
            _a.setUnwrapObject(out_);
            _b.setUnwrapObject(out_);
            res_.setResult(out_);
            return res_;
        }
        String exp_ = _cont.getStandards().getAliasNumber();
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setIndexFile(_cont.getAnalyzing().getLocalizer().getCurrentLocationIndex());
        un_.setFileName(_cont.getAnalyzing().getLocalizer().getCurrentFileName());
        //oper
        un_.buildError(_cont.getAnalysisMessages().getUnexpectedOperandTypes(),
                StringList.join(new StringList(
                        StringList.join(_a.getNames(),"&"),
                        StringList.join(_b.getNames(),"&")
                ),";"),
                getOp());
        _cont.getAnalyzing().getLocalizer().addError(un_);
        _cont.getAnalyzing().setOkNumOp(false);
        ClassArgumentMatching arg_ = new ClassArgumentMatching(exp_);
        res_.setResult(arg_);
        return res_;
//        int oa_ = PrimitiveTypeUtil.getOrderClass(_a, _cont);
//        int ob_ = PrimitiveTypeUtil.getOrderClass(_b, _cont);
//        if (oa_ <= 0 || ob_ <= 0) {
//
//        }
//        ClassArgumentMatching out_ = getResultClass(_a, _cont, _b);
//        _a.setUnwrapObject(out_);
//        _b.setUnwrapObject(out_);
//        res_.setResult(out_);
//        return res_;
    }

    @Override
    void setCatenize(ResultOperand _res) {
    }

}
