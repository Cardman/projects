package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fwd.opers.AnaOperatorContent;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public abstract class NumericOperation extends MethodOperation implements MiddleSymbolOperation {
    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private final AnaOperatorContent operatorContent;
    private boolean okNum;

    protected NumericOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        operatorContent = new AnaOperatorContent();
        operatorContent.setOper(_op.getOperators().firstValue());
        operatorContent.setOpOffset(_op.getOperators().firstKey());
    }

    static ResultOperand unwrappBinNumResultClass(AnaClassArgumentMatching _a, AnaClassArgumentMatching _b, AnalyzedPageEl _page) {
        AnaClassArgumentMatching out_;
        if (AnaTypeUtil.isIntOrderClass(_a, _b, _page)) {
            out_ = getIntResultClass(_a, _b, _page);
        } else if (AnaTypeUtil.isFloatOrderClass(_a, _b, _page)) {
            out_ = getFloatResultClass(_a, _b, _page);
        } else {
            out_ = new AnaClassArgumentMatching("");
        }
        if (!out_.getSingleNameOrEmpty().isEmpty()) {
            _a.setUnwrapObject(out_, _page.getPrimitiveTypes());
            _b.setUnwrapObject(out_, _page.getPrimitiveTypes());
        }
        ResultOperand res_ = new ResultOperand();
        res_.setResult(out_);
        return res_;
    }
    ResultOperand analyzeShift(AnaClassArgumentMatching _a,
                                 AnaClassArgumentMatching _b, AnalyzedPageEl _page) {
        ResultOperand res_ = new ResultOperand();
        if (AnaTypeUtil.isIntOrderClass(_a,_b, _page)) {
            AnaClassArgumentMatching out_ = getIntResultClass(_a, _b, _page);
            _a.setUnwrapObject(out_, _page.getPrimitiveTypes());
            _b.setUnwrapObject(out_, _page.getPrimitiveTypes());
            res_.setResult(out_);
            return res_;
        }
        return errNum(_a, _b, _page);
    }
    ResultOperand analyzeBitwise(AnaClassArgumentMatching _a,
                                 AnaClassArgumentMatching _b, AnalyzedPageEl _page) {
        ResultOperand res_ = new ResultOperand();
        if (_a.isBoolType(_page) && _b.isBoolType(_page)) {
            String bool_ = _page.getAliasPrimBoolean();
            _a.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
            _b.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
            res_.setResult(new AnaClassArgumentMatching(bool_,PrimitiveTypes.BOOL_WRAP));
            return res_;
        }
        if (AnaTypeUtil.isIntOrderClass(_a,_b, _page)) {
            AnaClassArgumentMatching out_ = getIntResultClass(_a, _b, _page);
            _a.setUnwrapObject(out_, _page.getPrimitiveTypes());
            _b.setUnwrapObject(out_, _page.getPrimitiveTypes());
            res_.setResult(out_);
            return res_;
        }
        return errNum(_a, _b, _page);
    }
    static AnaClassArgumentMatching getIntResultClass(AnaClassArgumentMatching _a, AnaClassArgumentMatching _b, AnalyzedPageEl _page) {
        int oa_ = AnaTypeUtil.getIntOrderClass(_a, _page);
        int ob_ = AnaTypeUtil.getIntOrderClass(_b, _page);
        int max_ = NumberUtil.max(oa_, ob_);
        AnaClassArgumentMatching arg_ = getMaxWrap(_a, oa_, _b, ob_);
        return AnaTypeUtil.toPrimitive(goToAtLeastInt(_page,arg_,max_), _page);
    }
    static AnaClassArgumentMatching goToAtLeastInt(AnalyzedPageEl _page, AnaClassArgumentMatching _before, int _value) {
        AnaClassArgumentMatching after_ = _before;
        int intOrder_ = AnaTypeUtil.getIntOrderClass(_page.getAliasPrimInteger(), _page);
        if (_value < intOrder_) {
            after_ = new AnaClassArgumentMatching(_page.getAliasPrimInteger(),PrimitiveTypes.INT_WRAP);
        }
        return after_;
    }
    static AnaClassArgumentMatching getFloatResultClass(AnaClassArgumentMatching _a, AnaClassArgumentMatching _b, AnalyzedPageEl _page) {
        int oa_ = AnaTypeUtil.getFloatOrderClass(_a, _page);
        int ob_ = AnaTypeUtil.getFloatOrderClass(_b, _page);
        AnaClassArgumentMatching arg_ = getMaxWrap(_a, oa_, _b, ob_);
        return AnaTypeUtil.toPrimitive(arg_, _page);
    }

    private static AnaClassArgumentMatching getMaxWrap(AnaClassArgumentMatching _a, int _oa, AnaClassArgumentMatching _b, int _ob) {
        AnaClassArgumentMatching arg_;
        if (_oa > _ob) {
            arg_ = _a;
        } else {
            arg_ = _b;
        }
        return arg_;
    }

    @Override
    public final void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode l_ = chidren_.first();
        StrTypes ops_ = getOperators();
        OperationNode r_ = chidren_.last();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
        okNum = true;
        String oper_ = ops_.firstValue();
        OperatorConverter res_ = null;
        if ( StringExpUtil.isBinNum(oper_) && !binNum(oper_,l_.getResultClass(), r_.getResultClass(), _page)){
            res_ = CompoundAffectationOperation.tryGetStd(_page, oper_, this, groupBinNum(_page));
        } else if (StringExpUtil.isBitwise(oper_)&&!bitwise(l_.getResultClass(), r_.getResultClass(), _page)) {
            res_ = CompoundAffectationOperation.tryGetStd(_page, oper_, this, groupBinBitwise(_page));
        } else if (StringExpUtil.isShiftOper(oper_)&&!AnaTypeUtil.isIntOrderClass(l_.getResultClass(), r_.getResultClass(), _page)) {
            res_ = CompoundAffectationOperation.tryGetStd(_page, oper_, this, groupBinShift(_page));
        }
        if (res_ != null) {
            fct.infos(res_,_page);
            return;
        }
        natOp(_page);
    }

    private void natOp(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode l_ = chidren_.first();
        AnaClassArgumentMatching a_ = l_.getResultClass();
        StrTypes ops_ = getOperators();
        OperationNode r_ = chidren_.last();
        AnaClassArgumentMatching c_ = r_.getResultClass();
        ResultOperand res_ = analyzeOper(a_, ops_.firstValue(), c_, _page);
        okNum = _page.isOkNumOp();
        setResultClass(AnaClassArgumentMatching.copy(res_.getResult(), _page.getPrimitiveTypes()));
    }

    abstract ResultOperand analyzeOper(AnaClassArgumentMatching _a, String _op, AnaClassArgumentMatching _b, AnalyzedPageEl _page);

    ResultOperand errNum(AnaClassArgumentMatching _a, AnaClassArgumentMatching _b, AnalyzedPageEl _page) {
        ResultOperand res_ = new ResultOperand();
        _page.setOkNumOp(false);
        String exp_ = _page.getAliasNumber();
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setIndexFile(_page);
        un_.setFile(_page.getCurrentFile());
        //oper
        un_.buildError(_page.getAnalysisMessages().getUnexpectedOperandTypes(),
                StringUtil.join(new StringList(
                        StringUtil.join(_a.getNames(), ExportCst.JOIN_TYPES),
                        StringUtil.join(_b.getNames(),ExportCst.JOIN_TYPES)
                ),ExportCst.JOIN_OPERANDS),
                getOp());
        _page.getLocalizer().addError(un_);
        getPartOffsetsChildren().add(new InfoErrorDto(un_, _page,getOp().length()));
        AnaClassArgumentMatching arg_ = new AnaClassArgumentMatching(exp_);
        res_.setResult(arg_);
        return res_;
    }

    public ClassMethodIdMemberIdTypeFct getFct() {
        return fct;
    }

    public AnaOperatorContent getOperatorContent() {
        return operatorContent;
    }

    @Override
    public String getOp() {
        return operatorContent.getOper();
    }

    @Override
    public boolean isOkNum() {
        return okNum;
    }

}
