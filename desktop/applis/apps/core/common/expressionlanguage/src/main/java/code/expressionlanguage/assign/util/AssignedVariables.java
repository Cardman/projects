package code.expressionlanguage.assign.util;

import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.blocks.AssForEach;
import code.expressionlanguage.assign.blocks.AssForIterativeLoop;
import code.expressionlanguage.assign.opers.AssAffectationOperation;
import code.expressionlanguage.assign.opers.AssOperationNode;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringMap;

public class AssignedVariables {

    private IdMap<AssOperationNode,CustList<StringMap<AssignmentBefore>>> variablesBefore = new IdMap<AssOperationNode,CustList<StringMap<AssignmentBefore>>>();
    private IdMap<AssOperationNode,CustList<StringMap<AssignmentBefore>>> mutableLoopBefore = new IdMap<AssOperationNode,CustList<StringMap<AssignmentBefore>>>();
    private IdMap<AssOperationNode,StringMap<AssignmentBefore>> fieldsBefore = new IdMap<AssOperationNode,StringMap<AssignmentBefore>>();

    private IdMap<AssOperationNode,CustList<StringMap<Assignment>>> variables = new IdMap<AssOperationNode,CustList<StringMap<Assignment>>>();
    private IdMap<AssOperationNode,CustList<StringMap<Assignment>>> mutableLoop = new IdMap<AssOperationNode,CustList<StringMap<Assignment>>>();
    private IdMap<AssOperationNode,StringMap<Assignment>> fields = new IdMap<AssOperationNode,StringMap<Assignment>>();

    private CustList<StringMap<AssignmentBefore>> variablesRootBefore = new CustList<StringMap<AssignmentBefore>>();
    private CustList<StringMap<AssignmentBefore>> mutableLoopRootBefore = new CustList<StringMap<AssignmentBefore>>();
    private StringMap<AssignmentBefore> fieldsRootBefore = new StringMap<AssignmentBefore>();

    private CustList<StringMap<SimpleAssignment>> variablesRoot = new CustList<StringMap<SimpleAssignment>>();
    private CustList<StringMap<SimpleAssignment>> mutableLoopRoot = new CustList<StringMap<SimpleAssignment>>();
    private StringMap<SimpleAssignment> fieldsRoot = new StringMap<SimpleAssignment>();

    public IdList<AssAffectationOperation> getVariablesBefore(AssBlock _filter, boolean _all) {
        return getList(_filter, _all, variablesBefore.getKeys());
    }

    public IdList<AssAffectationOperation> getFieldsBefore(AssBlock _filter, boolean _all) {
        return getList(_filter,_all,fieldsBefore.getKeys());
    }

    public IdList<AssAffectationOperation> getMutableLoopBefore(AssBlock _filter, boolean _all) {
        return getList(_filter, _all, mutableLoopBefore.getKeys());
    }

    private IdList<AssAffectationOperation> getList(AssBlock _filter, boolean _all, CustList<AssOperationNode> _list) {
        if (_all) {
            return filter(_list);
        }
        if (_filter instanceof AssForIterativeLoop) {
            return new IdList<AssAffectationOperation>();
        }
        if (_filter instanceof AssForEach) {
            return new IdList<AssAffectationOperation>();
        }
        return filter(_list);
    }
    private IdList<AssAffectationOperation> filter(CustList<AssOperationNode> _ops) {
        IdList<AssAffectationOperation> out_ = new IdList<AssAffectationOperation>();
        for (AssOperationNode o: _ops) {
            if (o instanceof AssAffectationOperation) {
                out_.add((AssAffectationOperation) o);
            }
        }
        return out_;
    }
    public CustList<StringMap<Assignment>> getLastVariablesOrEmpty() {
        if (variables.isEmpty()) {
            return new CustList<StringMap<Assignment>>();
        }
        return variables.lastValue();
    }
    public IdMap<AssOperationNode, CustList<StringMap<Assignment>>> getVariables() {
        return variables;
    }
    public StringMap<Assignment> getLastFieldsOrEmpty() {
        if (fields.isEmpty()) {
            return new StringMap<Assignment>();
        }
        return fields.lastValue();
    }
    public IdMap<AssOperationNode, StringMap<Assignment>> getFields() {
        return fields;
    }
    public IdMap<AssOperationNode, CustList<StringMap<AssignmentBefore>>> getVariablesBefore() {
        return variablesBefore;
    }
    public IdMap<AssOperationNode, StringMap<AssignmentBefore>> getFieldsBefore() {
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
    public IdMap<AssOperationNode, CustList<StringMap<AssignmentBefore>>> getMutableLoopBefore() {
        return mutableLoopBefore;
    }
    public CustList<StringMap<Assignment>> getLastMutableLoopOrEmpty() {
        if (mutableLoop.isEmpty()) {
            return new CustList<StringMap<Assignment>>();
        }
        return mutableLoop.lastValue();
    }
    public IdMap<AssOperationNode, CustList<StringMap<Assignment>>> getMutableLoop() {
        return mutableLoop;
    }
    public CustList<StringMap<AssignmentBefore>> getMutableLoopRootBefore() {
        return mutableLoopRootBefore;
    }
    public CustList<StringMap<SimpleAssignment>> getMutableLoopRoot() {
        return mutableLoopRoot;
    }
}
