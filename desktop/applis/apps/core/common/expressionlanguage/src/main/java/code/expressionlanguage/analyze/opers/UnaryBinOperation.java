package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.util.CustList;

public final class UnaryBinOperation extends AbstractUnaryOperation implements SymbolOperation {

    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private int opOffset;
    private boolean okNum;

    public UnaryBinOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        okNum = true;
        OperationNode child_ = getFirstChild();
        opOffset = getOperators().firstKey();
        String oper_ = getOperators().firstValue();
        if (AnaTypeUtil.getIntOrderClass(child_.getResultClass(), _page) != 0) {
            unaryBinNum(_page);
            return;
        }
        AnaClassArgumentMatching operand_ = child_.getResultClass();
        CustList<OperationNode> single_ = new CustList<OperationNode>(child_);
        OperatorConverter clId_ = operUse(_page, oper_, operand_, single_, groupUnBin(_page));
        if (clId_ != null) {
            fct.infos(clId_,_page);
            return;
        }
        unaryBinNum(_page);
    }

    private void unaryBinNum(AnalyzedPageEl _page) {
        OperationNode child_ = getFirstChild();
        AnaClassArgumentMatching clMatch_ = child_.getResultClass();
        int order_ = AnaTypeUtil.getIntOrderClass(clMatch_, _page);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+opOffset, _page);
        if (order_ == 0) {
            errSymbol(_page);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasNumber()));
            return;
        }
        AnaClassArgumentMatching cl_ = AnaTypeUtil.toPrimitive(clMatch_, _page);
        cl_ = NumericOperation.goToAtLeastInt(_page,cl_,order_);
        clMatch_.setUnwrapObject(cl_, _page.getPrimitiveTypes());
        setResultClass(AnaClassArgumentMatching.copy(cl_, _page.getPrimitiveTypes()));
    }

    public ClassMethodIdMemberIdTypeFct getFct() {
        return fct;
    }

    @Override
    public int getOpOffset() {
        return opOffset;
    }

    @Override
    public boolean isOkNum() {
        return okNum;
    }

}
