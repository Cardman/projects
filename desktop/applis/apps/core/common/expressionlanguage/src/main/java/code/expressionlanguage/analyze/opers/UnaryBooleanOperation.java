package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.fwd.opers.AnaOperatorContent;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.CustList;

public final class UnaryBooleanOperation extends AbstractUnaryOperation implements SymbolOperation {
    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private final AnaOperatorContent operatorContent;
    private boolean okNum;

    public UnaryBooleanOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        operatorContent = new AnaOperatorContent();
        operatorContent.setOper(getOperators().firstValue());
        operatorContent.setOpOffset(getOperators().firstKey());
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        okNum = true;
        OperationNode child_ = getFirstChild();
        String oper_ = getOperators().firstValue();
        if (child_.getResultClass().isBoolType(_page)) {
            unaryBool(_page);
            return;
        }
        AnaClassArgumentMatching operand_ = child_.getResultClass();
        CustList<OperationNode> single_ = new CustList<OperationNode>(child_);
        OperatorConverter clId_ = operUse(_page, oper_, operand_, single_, groupBool(_page));
        if (clId_ != null) {
            fct.infos(clId_,_page);
            return;
        }
        unaryBool(_page);
    }

    private void unaryBool(AnalyzedPageEl _page) {
        String booleanPrimType_ = _page.getAliasPrimBoolean();
        OperationNode child_ = getFirstChild();
        AnaClassArgumentMatching clMatch_ = child_.getResultClass();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+operatorContent.getOpOffset(), _page);
        if (!clMatch_.isBoolType(_page)) {
            errSymbol(_page);
        }
        clMatch_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        setResultClass(new AnaClassArgumentMatching(booleanPrimType_,PrimitiveTypes.BOOL_WRAP));
    }

    public ClassMethodIdMemberIdTypeFct getFct() {
        return fct;
    }

    @Override
    public AnaOperatorContent getOperatorContent() {
        return operatorContent;
    }

    @Override
    public boolean isOkNum() {
        return okNum;
    }

}
