package code.expressionlanguage.opers.util;

import code.expressionlanguage.opers.OperationNode;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public class AssignedVariables {
    
    private IdMap<OperationNode,CustList<StringMap<AssignmentBefore>>> variablesBefore = new IdMap<OperationNode,CustList<StringMap<AssignmentBefore>>>();
    private IdMap<OperationNode,CustList<StringMap<AssignmentBefore>>> mutableLoopBefore = new IdMap<OperationNode,CustList<StringMap<AssignmentBefore>>>();
    private IdMap<OperationNode,StringMap<AssignmentBefore>> fieldsBefore = new IdMap<OperationNode,StringMap<AssignmentBefore>>();

    private IdMap<OperationNode,CustList<StringMap<Assignment>>> variables = new IdMap<OperationNode,CustList<StringMap<Assignment>>>();
    private IdMap<OperationNode,CustList<StringMap<Assignment>>> mutableLoop = new IdMap<OperationNode,CustList<StringMap<Assignment>>>();
    private IdMap<OperationNode,StringMap<Assignment>> fields = new IdMap<OperationNode,StringMap<Assignment>>();

    private CustList<StringMap<AssignmentBefore>> variablesRootBefore = new CustList<StringMap<AssignmentBefore>>();
    private CustList<StringMap<AssignmentBefore>> mutableLoopRootBefore = new CustList<StringMap<AssignmentBefore>>();
    private StringMap<AssignmentBefore> fieldsRootBefore = new StringMap<AssignmentBefore>();

    private CustList<StringMap<SimpleAssignment>> variablesRoot = new CustList<StringMap<SimpleAssignment>>();
    private CustList<StringMap<SimpleAssignment>> mutableLoopRoot = new CustList<StringMap<SimpleAssignment>>();
    private StringMap<SimpleAssignment> fieldsRoot = new StringMap<SimpleAssignment>();
    public void initVars() {
        variablesRootBefore.add(new StringMap<AssignmentBefore>());
        variablesRoot.add(new StringMap<SimpleAssignment>());
        mutableLoopRootBefore.add(new StringMap<AssignmentBefore>());
        mutableLoopRoot.add(new StringMap<SimpleAssignment>());
    }
    public IdMap<OperationNode, CustList<StringMap<Assignment>>> getVariables() {
        return variables;
    }
    public IdMap<OperationNode, StringMap<Assignment>> getFields() {
        return fields;
    }
    public IdMap<OperationNode, CustList<StringMap<AssignmentBefore>>> getVariablesBefore() {
        return variablesBefore;
    }
    public IdMap<OperationNode, StringMap<AssignmentBefore>> getFieldsBefore() {
        return fieldsBefore;
    }
    public CustList<StringMap<AssignmentBefore>> getVariablesRootBefore() {
        return variablesRootBefore;
    }
    public StringMap<AssignmentBefore> getFieldsRootBefore() {
        return fieldsRootBefore;
    }
    public CustList<StringMap<SimpleAssignment>> getVariablesRoot() {
        return variablesRoot;
    }
    public StringMap<SimpleAssignment> getFieldsRoot() {
        return fieldsRoot;
    }
    public IdMap<OperationNode, CustList<StringMap<AssignmentBefore>>> getMutableLoopBefore() {
        return mutableLoopBefore;
    }
    public IdMap<OperationNode, CustList<StringMap<Assignment>>> getMutableLoop() {
        return mutableLoop;
    }
    public CustList<StringMap<AssignmentBefore>> getMutableLoopRootBefore() {
        return mutableLoopRootBefore;
    }
    public CustList<StringMap<SimpleAssignment>> getMutableLoopRoot() {
        return mutableLoopRoot;
    }
}
