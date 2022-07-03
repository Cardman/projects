package code.mock;

import code.util.CustList;

public final class MockTreePath {
    private final CustList<MockMutableTreeNode> path = new CustList<MockMutableTreeNode>();

    public CustList<MockMutableTreeNode> getPath() {
        return path;
    }
}
