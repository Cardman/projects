package code.expressionlanguage.opers.util;

import code.util.CustList;
import code.util.StringMap;


public final class AssignedBooleanVariables extends AssignedVariables {

    private CustList<StringMap<BooleanAssignment>> variablesRootAfter = new CustList<StringMap<BooleanAssignment>>();
    private StringMap<BooleanAssignment> fieldsRootAfter = new StringMap<BooleanAssignment>();
    public CustList<StringMap<BooleanAssignment>> getVariablesRootAfter() {
        return variablesRootAfter;
    }
    public StringMap<BooleanAssignment> getFieldsRootAfter() {
        return fieldsRootAfter;
    }

}
