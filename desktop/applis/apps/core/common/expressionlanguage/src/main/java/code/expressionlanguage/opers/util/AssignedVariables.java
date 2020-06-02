package code.expressionlanguage.opers.util;

import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.ForEachLoop;
import code.expressionlanguage.methods.ForEachTable;
import code.expressionlanguage.methods.ForIterativeLoop;
import code.expressionlanguage.opers.AffectationOperation;
import code.expressionlanguage.opers.OperationNode;
import code.util.CustList;
import code.util.IdList;
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

    public IdList<AffectationOperation> getVariablesBefore(Block _filter, boolean _all) {
        return getList(_filter, _all, variablesBefore.getKeys());
    }

    public IdList<AffectationOperation> getFieldsBefore(Block _filter, boolean _all) {
        return getList(_filter,_all,fieldsBefore.getKeys());
    }

    public IdList<AffectationOperation> getMutableLoopBefore(Block _filter, boolean _all) {
        return getList(_filter, _all, mutableLoopBefore.getKeys());
    }

    private IdList<AffectationOperation> getList(Block _filter, boolean _all, CustList<OperationNode> _list) {
        if (_all) {
            return filter(_list);
        }
        if (_filter instanceof ForIterativeLoop) {
            return new IdList<AffectationOperation>();
        }
        if (_filter instanceof ForEachLoop) {
            return new IdList<AffectationOperation>();
        }
        if (_filter instanceof ForEachTable) {
            return new IdList<AffectationOperation>();
        }
        return filter(_list);
    }
    private IdList<AffectationOperation> filter(CustList<OperationNode> _ops) {
        IdList<AffectationOperation> out_ = new IdList<AffectationOperation>();
        for (OperationNode o: _ops) {
            if (o instanceof AffectationOperation) {
                out_.add((AffectationOperation) o);
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
    public IdMap<OperationNode, CustList<StringMap<Assignment>>> getVariables() {
        return variables;
    }
    public StringMap<Assignment> getLastFieldsOrEmpty() {
        if (fields.isEmpty()) {
            return new StringMap<Assignment>();
        }
        return fields.lastValue();
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
    public CustList<StringMap<Assignment>> getLastMutableLoopOrEmpty() {
        if (mutableLoop.isEmpty()) {
            return new CustList<StringMap<Assignment>>();
        }
        return mutableLoop.lastValue();
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
