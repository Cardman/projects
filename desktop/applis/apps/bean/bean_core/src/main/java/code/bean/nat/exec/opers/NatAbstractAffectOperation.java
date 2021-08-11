package code.bean.nat.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendCalculableOperation;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.opers.RendMethodOperation;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;

public abstract class NatAbstractAffectOperation extends RendMethodOperation implements RendCalculableOperation {

    private RendDynOperationNode settable;

    protected NatAbstractAffectOperation(ExecOperationContent _content) {
        super(_content);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        calculateAffect(_nodes, _advStandards, _context, _rendStack);
    }

    protected abstract void calculateAffect(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack);
    public void setup() {
        settable = tryGetSettable(this);
    }
    public static RendDynOperationNode tryGetSettable(RendMethodOperation _operation) {
        RendDynOperationNode root_ = getIdOp(_operation);
        return castDottedTo(root_);
    }

    public static RendDynOperationNode getIdOp(RendMethodOperation _operation) {
        RendDynOperationNode root_ = _operation.getFirstChild();
        while (root_ instanceof NatIdOperation) {
            root_ = root_.getFirstChild();
        }
        return root_;
    }

    public static RendDynOperationNode castDottedTo(RendDynOperationNode _root) {
        RendDynOperationNode elt_;
        if (!(_root instanceof NatAbstractDotOperation)) {
            elt_ = _root;
        } else {
            elt_ = getLastNode((RendMethodOperation) _root);
        }
        return elt_;
    }

    public RendDynOperationNode getSettable() {
        return settable;
    }

}
