package code.expressionlanguage.analyze.assign.util;

import code.util.StringMap;


public class AssignedBooleanVariables extends AssignedVariables {

    private StringMap<BooleanAssignment> variablesRootAfter = new StringMap<BooleanAssignment>();
    private StringMap<BooleanAssignment> fieldsRootAfter = new StringMap<BooleanAssignment>();
    public StringMap<BooleanAssignment> getVariablesRootAfter() {
        return variablesRootAfter;
    }
    public StringMap<BooleanAssignment> getFieldsRootAfter() {
        return fieldsRootAfter;
    }

}
