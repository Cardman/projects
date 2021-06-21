package code.gui;

public final class UsObj {
    private final AbstractMutableTreeNode node;
    private final String userObject;

    public UsObj(AbstractMutableTreeNode _node,String _userObject) {
        this.node = _node;
        this.userObject = _userObject;
    }

    @Override
    public String toString() {
        return getUserObject();
    }

    public AbstractMutableTreeNode getNode() {
        return node;
    }

    public String getUserObject() {
        return userObject;
    }

}
