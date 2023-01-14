package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.SymbolFactoryUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.fwd.opers.AnaOperatorContent;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.CustList;
import code.util.core.StringUtil;

public final class EqOperation extends MethodOperation implements MiddleSymbolOperation {

    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private final AnaOperatorContent operatorContent;

    public EqOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        operatorContent = new AnaOperatorContent();
        operatorContent.setOper(_op.getOperators().firstValue());
        operatorContent.setOpOffset(_op.getOperators().firstKey());
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+operatorContent.getOpOffset(), _page);
        if (StringUtil.quickEq(operatorContent.getOper().trim(), NEG_BOOL)) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFile(_page.getCurrentFile());
            badEl_.setIndexFile(_page);
            //oper len
            badEl_.buildError(_page.getAnalysisMessages().getBadOperatorRef(),
                    operatorContent.getOper().trim());
            _page.getLocalizer().addError(badEl_);
            getPartOffsetsChildren().add(new InfoErrorDto(badEl_,_page,1));
        }
        String custOp_ = getOp().trim();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode l_ = chidren_.first();
        OperationNode r_ = chidren_.last();
        OperatorConverter cl_ = null;
        ResultOperand resOp_ = SymbolFactoryUtil.generateOperand(custOp_, l_.getResultClass(), r_.getResultClass(), _page);
        AnaClassArgumentMatching rOp_ = resOp_.getResult();
        if (rOp_.getSingleNameOrEmpty().isEmpty()) {
            cl_ = CompoundAffectationOperation.tryGetStd(_page, custOp_, this, SymbolFactoryUtil.binaries(custOp_, _page));
        }
        fct.setSymbol(resOp_.getSymbol());
        if (cl_ != null) {
            fct.infos(cl_);
            return;
        }
        setResultClass(new AnaClassArgumentMatching(_page.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
    }

    public CommonOperSymbol getSymbol() {
        return fct.getSymbol();
    }

    public ClassMethodIdMemberIdTypeFct getFct() {
        return fct;
    }

    @Override
    public AnaOperatorContent getOperatorContent() {
        return operatorContent;
    }

    @Override
    public String getOp() {
        return operatorContent.getOper();
    }

    /**The execution cannot occur if there is only one character as symbol
    Sample: 1!2 leads to error even if there is two operands*/
    @Override
    public boolean isOkNum() {
        return true;
    }

}
