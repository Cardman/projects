package code.expressionlanguage.analyze.assign.util;

import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.blocks.AssForEach;
import code.expressionlanguage.analyze.assign.blocks.AssForIterativeLoop;
import code.expressionlanguage.analyze.assign.opers.AssAffectationOperation;
import code.expressionlanguage.analyze.assign.opers.AssOperationNode;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringMap;

public class AssignedVariables {

    private IdMap<AssOperationNode,StringMap<AssignmentBefore>> variablesBefore = new IdMap<AssOperationNode,StringMap<AssignmentBefore>>();
    private IdMap<AssOperationNode,StringMap<AssignmentBefore>> fieldsBefore = new IdMap<AssOperationNode,StringMap<AssignmentBefore>>();

    private IdMap<AssOperationNode,StringMap<Assignment>> variables = new IdMap<AssOperationNode,StringMap<Assignment>>();
    private IdMap<AssOperationNode,StringMap<Assignment>> fields = new IdMap<AssOperationNode,StringMap<Assignment>>();

    private StringMap<AssignmentBefore> variablesRootBefore = new StringMap<AssignmentBefore>();
    private StringMap<AssignmentBefore> fieldsRootBefore = new StringMap<AssignmentBefore>();

    private StringMap<SimpleAssignment> variablesRoot = new StringMap<SimpleAssignment>();
    private StringMap<SimpleAssignment> fieldsRoot = new StringMap<SimpleAssignment>();

    public IdList<AssAffectationOperation> getVariablesBefore(AssBlock _filter, boolean _all) {
        return getList(_filter, _all, variablesBefore.getKeys());
    }

    public IdList<AssAffectationOperation> getFieldsBefore(AssBlock _filter, boolean _all) {
        return getList(_filter,_all,fieldsBefore.getKeys());
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
    public StringMap<Assignment> getLastVariablesOrEmpty() {
        if (variables.isEmpty()) {
            return new StringMap<Assignment>();
        }
        return variables.lastValue();
    }
    public IdMap<AssOperationNode, StringMap<Assignment>> getVariables() {
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
    public IdMap<AssOperationNode, StringMap<AssignmentBefore>> getVariablesBefore() {
        return variablesBefore;
    }
    public IdMap<AssOperationNode, StringMap<AssignmentBefore>> getFieldsBefore() {
        return fieldsBefore;
    }
    public StringMap<AssignmentBefore> getVariablesRootBefore() {
        return variablesRootBefore;
    }
    public StringMap<AssignmentBefore> getFieldsRootBefore() {
        return fieldsRootBefore;
    }
    public StringMap<SimpleAssignment> getVariablesRoot() {
        return variablesRoot;
    }
    public StringMap<SimpleAssignment> getFieldsRoot() {
        return fieldsRoot;
    }
}
