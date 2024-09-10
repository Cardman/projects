package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.common.ComMessageOperationNode;

public final class AnaMessageOperationNode extends ComMessageOperationNode {
    private final OperationNode root;

    public AnaMessageOperationNode(OperationNode _r, boolean _q, boolean _e, String _a) {
        super(_q, _e, _a);
        this.root = _r;
    }

    public OperationNode getRoot() {
        return root;
    }
}
