package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.CustList;
import code.util.StringList;

public final class BitOrOperation extends NumericOperation {

    public BitOrOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    ResultOperand analyzeOper(AnaClassArgumentMatching _a, String _op,
                              AnaClassArgumentMatching _b, AnalyzedPageEl _page) {
        ResultOperand res_ = new ResultOperand();
        if (_a.isBoolType(_page) && _b.isBoolType(_page)) {
            String bool_ = _page.getStandards().getAliasPrimBoolean();
            _a.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
            _b.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
            res_.setResult(new AnaClassArgumentMatching(bool_,PrimitiveTypes.BOOL_WRAP));
            return res_;
        }
        if (AnaTypeUtil.isIntOrderClass(_a,_b, _page)) {
            AnaClassArgumentMatching out_ = getIntResultClass(_a, _b, _page);
            _a.setUnwrapObject(out_, _page.getStandards());
            _b.setUnwrapObject(out_, _page.getStandards());
            res_.setResult(out_);
            return res_;
        }
        _page.setOkNumOp(false);
        String exp_ = _page.getStandards().getAliasNumber();
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        int index_ = _page.getLocalizer().getCurrentLocationIndex();
        un_.setIndexFile(index_);
        un_.setFileName(_page.getLocalizer().getCurrentFileName());
        //oper
        un_.buildError(_page.getAnalysisMessages().getUnexpectedOperandTypes(),
                StringList.join(new StringList(
                        StringList.join(_a.getNames(),"&"),
                        StringList.join(_b.getNames(),"&")
                ),";"),
                getOp());
        _page.getLocalizer().addError(un_);
        CustList<PartOffset> err_ = new CustList<PartOffset>();
        err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",index_));
        err_.add(new PartOffset("</a>",index_+getOp().length()));
        getPartOffsetsChildren().add(err_);
        AnaClassArgumentMatching arg_ = new AnaClassArgumentMatching(exp_);
        res_.setResult(arg_);
        return res_;
    }

    @Override
    void setCatenize(ResultOperand _res) {
    }

}
