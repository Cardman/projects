package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.maths.litteral.StrTypes;
import code.util.CustList;
import code.util.IntTreeMap;

public abstract class AbstractDotOperation extends MethodOperation {
    public AbstractDotOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setResultClass(AnaClassArgumentMatching.copy(chidren_.last().getResultClass(), _page.getPrimitiveTypes()));
    }
    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        getChildren().addAllEntries(vs_);
    }
}
