package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.StrTypes;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

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
        opOffset= getOperations().getOperators().firstKey();
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
