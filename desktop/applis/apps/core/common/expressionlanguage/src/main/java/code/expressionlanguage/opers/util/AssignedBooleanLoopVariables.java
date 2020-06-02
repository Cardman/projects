package code.expressionlanguage.opers.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.ForLoopPart;
import code.expressionlanguage.opers.AffectationOperation;
import code.util.CustList;
import code.util.IdList;
import code.util.StringMap;

public class AssignedBooleanLoopVariables extends AssignedBooleanVariables {

    private IdList<AffectationOperation> affectations = new IdList<AffectationOperation>();

    private CustList<StringMap<Assignment>> variablesRootAfterInit = new CustList<StringMap<Assignment>>();
    private CustList<StringMap<Assignment>> mutableLoopRootAfterInit = new CustList<StringMap<Assignment>>();
    private StringMap<Assignment> fieldsRootAfterInit = new StringMap<Assignment>();
    public void add(AffectationOperation _aff, ContextEl _an) {
        if (_an.getAnalyzing().getForLoopPartState() == ForLoopPart.INIT) {
            return;
        }
        affectations.add(_aff);
    }
    @Override
    public IdList<AffectationOperation> getVariablesBefore(Block _filter, boolean _all) {
        if (_all) {
            return super.getVariablesBefore(_filter, _all);
        }
        return affectations;
    }
    @Override
    public IdList<AffectationOperation> getFieldsBefore(Block _filter, boolean _all) {
        if (_all) {
            return super.getFieldsBefore(_filter, _all);
        }
        return affectations;
    }
    @Override
    public IdList<AffectationOperation> getMutableLoopBefore(Block _filter,
            boolean _all) {
        if (_all) {
            return super.getMutableLoopBefore(_filter, _all);
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
