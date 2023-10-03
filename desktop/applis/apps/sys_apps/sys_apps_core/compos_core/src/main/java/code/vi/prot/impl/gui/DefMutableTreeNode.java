package code.vi.prot.impl.gui;

import code.gui.*;
import code.util.core.StringUtil;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public final class DefMutableTreeNode extends MutableTreeNodeNav<String> implements AbstractMutableTreeNodeCore<String> {
    private final DefaultMutableTreeNode node;

    public DefMutableTreeNode(String _name) {
        String value_ = StringUtil.nullToEmpty(_name);
        node = new DefaultMutableTreeNode(value_);
        info(_name);
    }

    public DefMutableTreeNode(MutableTreeNode _name) {
        node = (DefaultMutableTreeNode) _name;
        info(info(node));
    }

    public MutableTreeNode node() {
        return node;
    }

    @Override
    public int getIndex() {
        try {
            return node.getParent().getIndex(node);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public boolean add(AbstractMutableTreeNodeCore<String> _treeNode) {
        boolean v_ = super.add(_treeNode);
        node.add(((DefMutableTreeNode) _treeNode).node());
        return v_;
    }

    @Override
    public int insert(AbstractMutableTreeNodeCore<String> _treeNode, int _index) {
        node.insert(((DefMutableTreeNode) _treeNode).node(), _index);
        return super.insert(_treeNode,_index);
    }

    @Override
    public int removeAllChildren() {
        int nb_ = super.removeAllChildren();
        node.removeAllChildren();
        return nb_;
    }

    @Override
    public AbstractMutableTreeNodeCore<String> getFirstChildReal() {
        try {
            return build((DefaultMutableTreeNode) node.getFirstChild());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AbstractMutableTreeNodeCore<String> getNextSiblingReal() {
        try {
            return build(node.getNextSibling());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AbstractMutableTreeNodeCore<String> getParentReal() {
        try {
            return build((DefaultMutableTreeNode) node.getParent());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int remove(AbstractMutableTreeNodeCore<String> _treeNode) {
        int v_ = super.remove(_treeNode);
        node.remove(((DefMutableTreeNode) _treeNode).node());
        return v_;
    }

    @Override
    public AbstractMutableTreeNodeCore<String> remove(int _index) {
        AbstractMutableTreeNodeCore<String> v_ = super.remove( _index);
        node.remove(_index);
        return v_;
    }

    @Override
    public AbstractMutableTreeNodeCore<String> removeFromParent() {
        AbstractMutableTreeNodeCore<String> v_ = super.removeFromParent();
        node.removeFromParent();
        return v_;
    }

    static DefMutableTreeNode build(DefaultMutableTreeNode _node) {
        return new DefMutableTreeNode(_node);
    }

    @Override
    public String info() {
        return info(node);
    }

    private static String info(DefaultMutableTreeNode _n) {
        try {
            return (String) _n.getUserObject();
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public void info(String _t) {
        super.info(_t);
        node.setUserObject(StringUtil.nullToEmpty(_t));
    }

}
