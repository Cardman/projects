package code.expressionlanguage.opers.util;

import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringMap;


public final class AssignedBooleanVariables extends AssignedVariables {

    private CustList<StringMap<BooleanAssignment>> variablesRootAfter = new CustList<StringMap<BooleanAssignment>>();
    private ObjectMap<ClassField,BooleanAssignment> fieldsRootAfter = new ObjectMap<ClassField,BooleanAssignment>();
    public CustList<StringMap<BooleanAssignment>> getVariablesRootAfter() {
        return variablesRootAfter;
    }
    public ObjectMap<ClassField, BooleanAssignment> getFieldsRootAfter() {
        return fieldsRootAfter;
    }

}
