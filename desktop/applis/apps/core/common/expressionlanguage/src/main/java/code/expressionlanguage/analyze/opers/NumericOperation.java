package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.SymbolFactoryUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.opers.util.ParamReturn;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.fwd.opers.AnaOperatorContent;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class NumericOperation extends MethodOperation implements MiddleSymbolOperation {
    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private final AnaOperatorContent operatorContent;
    private boolean okNum;
    private CommonOperSymbol symbol;

    private boolean catString;
    public NumericOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        operatorContent = new AnaOperatorContent();
        operatorContent.setOper(_op.getOperators().firstValue());
        operatorContent.setOpOffset(_op.getOperators().firstKey());
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode l_ = chidren_.first();
        StrTypes ops_ = getOperators();
        OperationNode r_ = chidren_.last();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
        okNum = true;
        String oper_ = ops_.firstValue();
        OperatorConverter res_ = null;
        ResultOperand resOp_ = SymbolFactoryUtil.generateOperand(oper_, l_.getResultClass(), r_.getResultClass(), _page);
        AnaClassArgumentMatching rOp_ = resOp_.getResult();
        catString = resOp_.isDefConcat();
        if (rOp_.getSingleNameOrEmpty().isEmpty()) {
            CustList<CustList<ParamReturn>> bins_ = SymbolFactoryUtil.binaries(oper_, _page);
            res_ = CompoundAffectationOperation.tryGetStd(_page, oper_, this, bins_);
        }
        symbol = resOp_.getSymbol();
        if (res_ != null) {
            fct.infos(res_,_page);
            return;
        }
        setResultClass(AnaClassArgumentMatching.copy(rOp_, _page.getPrimitiveTypes()));
        if (catString) {
            l_.getResultClass().setConvertToString(true);
            r_.getResultClass().setConvertToString(true);
        } else if (rOp_.getSingleNameOrEmpty().isEmpty()) {
            errNum(l_.getResultClass(), r_.getResultClass(),_page);
            String exp_ = _page.getAliasNumber();
            setResultClass(new AnaClassArgumentMatching(exp_));
        }
        okNum = _page.isOkNumOp();
    }

    void errNum(AnaClassArgumentMatching _a, AnaClassArgumentMatching _b, AnalyzedPageEl _page) {
        _page.setOkNumOp(false);
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
    }

    public CommonOperSymbol getSymbol() {
        return symbol;
    }

    public boolean isCatString() {
        return catString;
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
