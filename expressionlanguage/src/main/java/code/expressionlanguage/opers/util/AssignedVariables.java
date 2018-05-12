package code.expressionlanguage.opers.util;

import code.expressionlanguage.opers.OperationNode;
import code.util.CustList;
import code.util.IdMap;
import code.util.ObjectMap;
import code.util.StringMap;

public class AssignedVariables {
    
    private IdMap<OperationNode,CustList<StringMap<AssignmentBefore>>> variablesBefore = new IdMap<OperationNode,CustList<StringMap<AssignmentBefore>>>();
    private IdMap<OperationNode,ObjectMap<ClassField,AssignmentBefore>> fieldsBefore = new IdMap<OperationNode,ObjectMap<ClassField,AssignmentBefore>>();

    private IdMap<OperationNode,CustList<StringMap<Assignment>>> variables = new IdMap<OperationNode,CustList<StringMap<Assignment>>>();
    private IdMap<OperationNode,ObjectMap<ClassField,Assignment>> fields = new IdMap<OperationNode,ObjectMap<ClassField,Assignment>>();

    private CustList<StringMap<AssignmentBefore>> variablesRootBefore = new CustList<StringMap<AssignmentBefore>>();
    private ObjectMap<ClassField,AssignmentBefore> fieldsRootBefore = new ObjectMap<ClassField,AssignmentBefore>();

    private CustList<StringMap<SimpleAssignment>> variablesRoot = new CustList<StringMap<SimpleAssignment>>();
    private ObjectMap<ClassField,SimpleAssignment> fieldsRoot = new ObjectMap<ClassField,SimpleAssignment>();
    public void initVars() {
        variablesRootBefore.add(new StringMap<AssignmentBefore>());
        variablesRoot.add(new StringMap<SimpleAssignment>());
    }
    public void removeVars() {
        variablesRootBefore.removeLast();
        variablesRoot.removeLast();
    }
    public IdMap<OperationNode, CustList<StringMap<Assignment>>> getVariables() {
        return variables;
    }
    public IdMap<OperationNode, ObjectMap<ClassField,Assignment>> getFields() {
        return fields;
    }
    public IdMap<OperationNode, CustList<StringMap<AssignmentBefore>>> getVariablesBefore() {
        return variablesBefore;
    }
    public IdMap<OperationNode, ObjectMap<ClassField,AssignmentBefore>> getFieldsBefore() {
        return fieldsBefore;
    }
    public CustList<StringMap<AssignmentBefore>> getVariablesRootBefore() {
        return variablesRootBefore;
    }
    public ObjectMap<ClassField,AssignmentBefore> getFieldsRootBefore() {
        return fieldsRootBefore;
    }
    public CustList<StringMap<SimpleAssignment>> getVariablesRoot() {
        return variablesRoot;
    }
    public ObjectMap<ClassField,SimpleAssignment> getFieldsRoot() {
        return fieldsRoot;
    }
}
