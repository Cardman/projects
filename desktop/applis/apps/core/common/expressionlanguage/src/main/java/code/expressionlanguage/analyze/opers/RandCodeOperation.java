package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.fwd.opers.AnaOperatorContent;
import code.expressionlanguage.stds.PrimitiveTypes;

public final class RandCodeOperation extends AbstractUnaryOperation implements SymbolOperation {
    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private final AnaOperatorContent operatorContent;
    private boolean okNum;
    public RandCodeOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        operatorContent = new AnaOperatorContent();
        operatorContent.setOper("*");
        operatorContent.setOpOffset(getOperators().firstKey());
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        okNum = true;
        setResultClass(new AnaClassArgumentMatching(_page.getAliasPrimLong(),PrimitiveTypes.LONG_WRAP));
    }

    public ClassMethodIdMemberIdTypeFct getFct() {
        return fct;
    }

    @Override
    public AnaOperatorContent getOperatorContent() {
        return operatorContent;
    }

    public boolean isOkNum() {
        return okNum;
    }

}
