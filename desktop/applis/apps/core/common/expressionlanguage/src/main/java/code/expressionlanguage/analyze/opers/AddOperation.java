package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.OperationsSequence;

import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.AliasNumber;
import code.util.CustList;
import code.util.StringList;



public final class AddOperation extends NumericOperation {

    private boolean catString;

    public AddOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    Argument calculateOperAna(Argument _a, String _op, Argument _b, ContextEl _cont) {
        return localSumDiff(_a, _op, _b, _cont);
    }

    private Argument localSumDiff(Argument _a, String _op, Argument _b,
                                  ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        if (StringList.quickEq(_op.trim(), PLUS)) {
            if (catString) {
                return AnaApplyCoreMethodUtil.localSumDiff(_a,_b,_cont);
            }
            return new Argument(AliasNumber.calculateSum(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                    ClassArgumentMatching.convertToNumber(_b.getStruct()), getResultClass(), page_.getStandards()));
        }
        return new Argument(AliasNumber.calculateDiff(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                ClassArgumentMatching.convertToNumber(_b.getStruct()), getResultClass(), page_.getStandards()));
    }
    @Override
    ResultOperand analyzeOper(ClassArgumentMatching _a, String _op, ClassArgumentMatching _b, ContextEl _cont) {
        ResultOperand res_ = new ResultOperand();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        String stringType_ = page_.getStandards().getAliasString();
        if (StringList.quickEq(_op.trim(), PLUS)) {
            if (AnaTypeUtil.isIntOrderClass(_a,_b,_cont)) {
                int oa_ = AnaTypeUtil.getIntOrderClass(_a, _cont);
                int ob_ = AnaTypeUtil.getIntOrderClass(_b, _cont);
                ClassArgumentMatching out_ = getQuickResultClass(_a, oa_, _cont, _b, ob_);
                _a.setUnwrapObject(out_);
                _b.setUnwrapObject(out_);
                res_.setResult(out_);
                return res_;
            }
            if (AnaTypeUtil.isFloatOrderClass(_a,_b,_cont)) {
                int oa_ = AnaTypeUtil.getFloatOrderClass(_a, _cont);
                int ob_ = AnaTypeUtil.getFloatOrderClass(_b, _cont);
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
            page_.setOkNumOp(false);
            String exp_ = page_.getStandards().getAliasNumber();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            int index_ = page_.getLocalizer().getCurrentLocationIndex();
            un_.setIndexFile(index_);
            un_.setFileName(page_.getLocalizer().getCurrentFileName());
            //oper
            un_.buildError(_cont.getAnalysisMessages().getUnexpectedOperandTypes(),
                    StringList.join(new StringList(
                            StringList.join(_a.getNames(),"&"),
                            StringList.join(_b.getNames(),"&")
                    ),";"),
                    getOp());
            page_.getLocalizer().addError(un_);
            CustList<PartOffset> err_ = new CustList<PartOffset>();
            err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",index_));
            err_.add(new PartOffset("</a>",index_+1));
            getPartOffsetsChildren().add(err_);
            ClassArgumentMatching arg_ = new ClassArgumentMatching(exp_);
            res_.setResult(arg_);
            return res_;
        }
        if (AnaTypeUtil.isIntOrderClass(_a,_b,_cont)) {
            ClassArgumentMatching out_ = getIntResultClass(_a, _cont, _b);
            _a.setUnwrapObject(out_);
            _b.setUnwrapObject(out_);
            res_.setResult(out_);
            return res_;
        }
        if (AnaTypeUtil.isFloatOrderClass(_a,_b,_cont)) {
            ClassArgumentMatching out_ = getFloatResultClass(_a, _cont, _b);
            _a.setUnwrapObject(out_);
            _b.setUnwrapObject(out_);
            res_.setResult(out_);
            return res_;
        }
        page_.setOkNumOp(false);
        String exp_ = page_.getStandards().getAliasNumber();
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        int index_ = page_.getLocalizer().getCurrentLocationIndex();
        un_.setIndexFile(index_);
        un_.setFileName(page_.getLocalizer().getCurrentFileName());
        //oper
        un_.buildError(_cont.getAnalysisMessages().getUnexpectedOperandTypes(),
                StringList.join(new StringList(
                        StringList.join(_a.getNames(),"&"),
                        StringList.join(_b.getNames(),"&")
                ),";"),
                getOp());
        page_.getLocalizer().addError(un_);
        CustList<PartOffset> err_ = new CustList<PartOffset>();
        err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",index_));
        err_.add(new PartOffset("</a>",index_+1));
        getPartOffsetsChildren().add(err_);
        ClassArgumentMatching arg_ = new ClassArgumentMatching(exp_);
        res_.setResult(arg_);
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
