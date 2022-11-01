package code.gui;

public class MutableTreeNodeNav implements AbstractMutableTreeNodeNav{

    private final MutableTreeNodeCore nav = new MutableTreeNodeCore();
    @Override
    public AbstractMutableTreeNodeCore getParent() {
        return nav.getParent();
    }

    @Override
    public AbstractMutableTreeNodeCore getFirstChild() {
        return nav.getFirstChild();
    }

    @Override
    public AbstractMutableTreeNodeCore getNextSibling() {
        return nav.getNextSibling();
    }

    @Override
    public void setParent(AbstractMutableTreeNodeCore _v) {
        nav.setParent(_v);
    }

    @Override
    public void setFirstChild(AbstractMutableTreeNodeCore _v) {
        nav.setFirstChild(_v);
    }

    @Override
    public void setNextSibling(AbstractMutableTreeNodeCore _v) {
        nav.setNextSibling(_v);
    }

    @Override
    public int getAntiIndex(AbstractMutableTreeNodeCore _treeNode) {
        return MutableTreeNodeCoreUtil.getAntiIndex(this,_treeNode);
    }

    @Override
    public int getChildCount() {
        return MutableTreeNodeCoreUtil.getChildCount(this);
    }

    @Override
    public AbstractMutableTreeNodeCore getPreviousSibling() {
        return MutableTreeNodeCoreUtil.getPreviousSibling(this);
    }

    @Override
    public AbstractMutableTreeNodeCore getChildAt(int _i) {
        return MutableTreeNodeCoreUtil.getChildAt(this,_i);
    }
}
