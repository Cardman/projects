package code.expressionlanguage.exec.opers;

import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.fwd.opers.ExecOperationContent;

public abstract class ExecAbstractAffectOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private ExecOperationNode settable;
    private ExecMethodOperation settableParent;
    protected ExecAbstractAffectOperation(ExecOperationContent _m) {
        super(_m);
    }

    public void setup() {
        settable = tryGetSettable(this);
        settableParent = tryGetSettableParent(this);
    }
    static ExecOperationNode tryGetSettable(ExecMethodOperation _operation) {
        ExecOperationNode root_ = getFirstToBeAnalyzed(_operation);
        ExecOperationNode elt_;
        if (!(root_ instanceof ExecAbstractDotOperation)) {
            elt_ = root_;
        } else {
            elt_ = ExecHelper.getLastNode((ExecMethodOperation)root_);
        }
        return elt_;
    }
    static ExecMethodOperation tryGetSettableParent(ExecMethodOperation _operation) {
        ExecOperationNode root_ = getFirstToBeAnalyzed(_operation);
        ExecMethodOperation elt_;
        if (!(root_ instanceof ExecAbstractDotOperation)) {
            elt_ = ExecHelper.getParentOrNull(root_);
        } else {
            elt_ = (ExecMethodOperation)root_;
        }
        return elt_;
    }

    static ExecOperationNode getFirstToBeAnalyzed(ExecMethodOperation _operation) {
        ExecOperationNode root_ = _operation.getFirstChild();
        while (root_ instanceof ExecIdOperation) {
            root_ = root_.getFirstChild();
        }
        return root_;
    }

    public ExecOperationNode getSettable() {
        return settable;
    }

    public ExecMethodOperation getSettableParent() {
        return settableParent;
    }
}
