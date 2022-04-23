package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.stds.PrimitiveTypes;

public final class RandCodeOperation extends AbstractUnaryOperation implements SymbolOperation {
    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private int opOffset;
    private boolean okNum;
    public RandCodeOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        okNum = true;
        opOffset= getOperators().firstKey();
        setResultClass(new AnaClassArgumentMatching(_page.getAliasPrimLong(),PrimitiveTypes.LONG_WRAP));
    }

    public ClassMethodIdMemberIdTypeFct getFct() {
        return fct;
    }
    public int getOpOffset() {
        return opOffset;
    }

    public boolean isOkNum() {
        return okNum;
    }

}
