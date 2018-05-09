package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.SortedClassField;
import code.util.CustList;
import code.util.EqList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringMap;

public abstract class MethodOperation extends OperationNode {

    private OperationNode firstChild;

    private NatTreeMap<Integer,String> children;

    public MethodOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        children = new NatTreeMap<Integer,String>();
        calculateChildren();
    }
    @Override
    public final boolean isCalculated(IdMap<OperationNode, ArgumentsPair> _nodes) {
        OperationNode op_ = this;
        while (op_ != null) {
            if (_nodes.getVal(op_).getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public final boolean isCalculated() {
        OperationNode op_ = this;
        while (op_ != null) {
            if (op_.getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    public final void tryAnalyzeAssignmentBefore(Analyzable _conf, OperationNode _firstChild) {
        Block currentBlock_ = _conf.getCurrentBlock();
        if (currentBlock_  == null) {
            return;
        }
        analyzeAssignmentBefore(_conf, _firstChild);
    }
    public void analyzeAssignmentBefore(Analyzable _conf, OperationNode _firstChild) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        ObjectMap<ClassField,AssignmentBefore> fields_;
        CustList<StringMap<AssignmentBefore>> variables_;
        fields_ = vars_.getFieldsBefore().getVal(this);
        variables_ = vars_.getVariablesBefore().getVal(this);
        vars_.getFieldsBefore().put(_firstChild, fields_);
        vars_.getVariablesBefore().put(_firstChild, variables_);
    }
    public final void tryAnalyzeAssignmentBeforeNextSibling(Analyzable _conf, OperationNode _firstChild, OperationNode _previous) {
        Block currentBlock_ = _conf.getCurrentBlock();
        if (currentBlock_  == null) {
            return;
        }
        analyzeAssignmentBeforeNextSibling(_conf, _firstChild, _previous);
    }
    public abstract void analyzeAssignmentBeforeNextSibling(Analyzable _conf, OperationNode _firstChild, OperationNode _previous);
    @Override
    public void tryCalculateNode(ContextEl _conf, EqList<SortedClassField> _list, SortedClassField _current) {
        CustList<OperationNode> children_ = getChildrenNodes();
        for (OperationNode o: children_) {
            if (o.getArgument() == null) {
                return;
            }
        }
        quickCalculate(_conf);
    }
    @Override
    public void tryCalculateNode(Analyzable _conf) {
        CustList<OperationNode> children_ = getChildrenNodes();
        for (OperationNode o: children_) {
            if (o.getArgument() == null) {
                return;
            }
        }
        quickCalculate(_conf);
    }
    public void quickCalculate(Analyzable _conf) {
    }

    public final void appendChild(OperationNode _child) {
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        OperationNode child_ = firstChild;
        while (true) {
            OperationNode sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }
    final CustList<OperationNode> getChildrenNodes() {
        CustList<OperationNode> chidren_ = new CustList<OperationNode>();
        for (OperationNode o: ElUtil.getDirectChildren(this)) {
            chidren_.add(o);
        }
        return chidren_;
    }

    abstract void calculateChildren();

    @Override
    public final OperationNode getFirstChild() {
        return firstChild;
    }

    public final NatTreeMap<Integer, String> getChildren() {
        return children;
    }
}
