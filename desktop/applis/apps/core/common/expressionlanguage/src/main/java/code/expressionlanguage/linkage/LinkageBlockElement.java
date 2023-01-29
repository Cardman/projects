package code.expressionlanguage.linkage;

import code.expressionlanguage.analyze.opers.OperationNode;

public final class LinkageBlockElement {
    private final OperationNode root;
    private final int begin;
    private final int length;

    public LinkageBlockElement(OperationNode _r, int _b, int _l) {
        this.root = _r;
        this.begin = _b;
        this.length = _l;
    }

    public int getLength() {
        return length;
    }

    public int getBegin() {
        return begin;
    }

    public OperationNode getRoot() {
        return root;
    }
}
