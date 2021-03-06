package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.maths.litteralcom.StrTypes;

public final class DeclaringOperation extends MethodOperation {

    public DeclaringOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }


    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        getChildren().addAllEntries(vs_);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        setResultClass(new AnaClassArgumentMatching(EMPTY_STRING));
    }

}
