package code.expressionlanguage.assign.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.opers.AssAffectationOperation;
import code.expressionlanguage.analyze.blocks.ForLoopPart;
import code.util.IdList;
import code.util.StringMap;

public final class AssignedBooleanLoopVariables extends AssignedBooleanVariables {

    private IdList<AssAffectationOperation> affectations = new IdList<AssAffectationOperation>();

    private StringMap<Assignment> variablesRootAfterInit = new StringMap<Assignment>();
    private StringMap<Assignment> fieldsRootAfterInit = new StringMap<Assignment>();
    public void add(AssAffectationOperation _aff, AnalyzedPageEl _page) {
        if (_page.getForLoopPartState() == ForLoopPart.INIT) {
            return;
        }
        affectations.add(_aff);
    }
    @Override
    public IdList<AssAffectationOperation> getVariablesBefore(AssBlock _filter, boolean _all) {
        if (_all) {
            return super.getVariablesBefore(_filter, true);
        }
        return affectations;
    }
    @Override
    public IdList<AssAffectationOperation> getFieldsBefore(AssBlock _filter, boolean _all) {
        if (_all) {
            return super.getFieldsBefore(_filter, true);
        }
        return affectations;
    }

    public StringMap<Assignment> getVariablesRootAfterInit() {
        return variablesRootAfterInit;
    }
    public StringMap<Assignment> getFieldsRootAfterInit() {
        return fieldsRootAfterInit;
    }
}
