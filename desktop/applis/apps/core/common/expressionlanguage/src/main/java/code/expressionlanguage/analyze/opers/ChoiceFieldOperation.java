package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;

public final class ChoiceFieldOperation extends AbsChoiceFieldOperation {

    public ChoiceFieldOperation(OperationsSequence _op) {
        super(_op);
    }

    @Override
    public AnaClassArgumentMatching getFrom(AnalyzedPageEl _page,SettableAbstractFieldOperation _settable) {
        return new AnaClassArgumentMatching(className(_page));
    }

    @Override
    public boolean isSuperAccess() {
        return false;
    }

}
