package code.expressionlanguage.assign.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.opers.AssAffectationOperation;
import code.expressionlanguage.analyze.blocks.ForLoopPart;
import code.util.CustList;
import code.util.IdList;
import code.util.StringMap;

public final class AssignedBooleanLoopVariables extends AssignedBooleanVariables {

    private IdList<AssAffectationOperation> affectations = new IdList<AssAffectationOperation>();

    private CustList<StringMap<Assignment>> variablesRootAfterInit = new CustList<StringMap<Assignment>>();
    private CustList<StringMap<Assignment>> mutableLoopRootAfterInit = new CustList<StringMap<Assignment>>();
    private StringMap<Assignment> fieldsRootAfterInit = new StringMap<Assignment>();
    public void add(AssAffectationOperation _aff, ContextEl _an) {
        if (_an.getAnalyzing().getForLoopPartState() == ForLoopPart.INIT) {
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
    @Override
    public IdList<AssAffectationOperation> getMutableLoopBefore(AssBlock _filter,
            boolean _all) {
        if (_all) {
            return super.getMutableLoopBefore(_filter, true);
        }
        return affectations;
    }
    public CustList<StringMap<Assignment>> getVariablesRootAfterInit() {
        return variablesRootAfterInit;
    }
    public StringMap<Assignment> getFieldsRootAfterInit() {
        return fieldsRootAfterInit;
    }
    public CustList<StringMap<Assignment>> getMutableLoopRootAfterInit() {
        return mutableLoopRootAfterInit;
    }
}
