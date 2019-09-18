package code.expressionlanguage;

import code.expressionlanguage.structs.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public final class TreeNodeStruct implements Struct {

    private final DefaultMutableTreeNode treeNode;

    TreeNodeStruct() {
        treeNode = new DefaultMutableTreeNode("");
    }

    TreeNodeStruct(Struct _str) {
        treeNode = new DefaultMutableTreeNode(getString(_str));
    }

    TreeNodeStruct(DefaultMutableTreeNode _cp) {
        treeNode = _cp;
    }

    void add(Struct _node) {
        if (!(_node instanceof TreeNodeStruct)) {
            return;
        }
        if (isAncestor(_node)) {
            return;
        }
        TreeNodeStruct treeNode_ = (TreeNodeStruct) _node;
        treeNode.add(treeNode_.treeNode);
    }

    void insert(Struct _index,Struct _node) {
        int index_ = ((NumberStruct)_index).intStruct();
        if (!(_node instanceof TreeNodeStruct)) {
            return;
        }
        if (isAncestor(_node)) {
            return;
        }
        TreeNodeStruct treeNode_ = (TreeNodeStruct) _node;
        int count_ = treeNode.getChildCount();
        if (index_ < 0 || index_ > count_) {
            return;
        }
        treeNode.insert(treeNode_.treeNode,index_);
    }
    BooleanStruct isAncestorMethod(Struct _node) {
        return new BooleanStruct(isAncestor(_node));
    }
    BooleanStruct isDescendantMethod(Struct _node) {
        return new BooleanStruct(isDescendant(_node));
    }
    private boolean isAncestor(Struct _node) {
        Struct cur_ = this;
        while (cur_ instanceof TreeNodeStruct) {
            if (cur_.sameReference(_node)) {
                return true;
            }
            cur_ = ((TreeNodeStruct)cur_).getParentNode();
        }
        return false;
    }
    private boolean isDescendant(Struct _node) {
        Struct cur_ = _node;
        while (cur_ instanceof TreeNodeStruct) {
            if (cur_.sameReference(this)) {
                return true;
            }
            cur_ = ((TreeNodeStruct)cur_).getParentNode();
        }
        return false;
    }
    void removeAllChildren() {
        treeNode.removeAllChildren();
    }
    void removeNode(Struct _node) {
        if (!(_node instanceof TreeNodeStruct)) {
            return;
        }
        TreeNodeStruct n_ = (TreeNodeStruct) _node;
        if (n_.treeNode.getParent() != treeNode) {
            return;
        }
        treeNode.remove(n_.treeNode);
    }
    void remove(Struct _index) {
        int index_ = ((NumberStruct)_index).intStruct();
        int count_ = treeNode.getChildCount();
        if (index_ < 0 || index_ >= count_) {
            return;
        }
        treeNode.remove(index_);
    }


    void removeFromParent() {
        Struct par_ = getParentNode();
        if (!(par_ instanceof TreeNodeStruct)) {
            return;
        }
        ((TreeNodeStruct) par_).removeNode(this);
    }

    Struct getParentNode() {
        TreeNode par_ = treeNode.getParent();
        if (!(par_ instanceof DefaultMutableTreeNode)) {
            return NullStruct.NULL_VALUE;
        }
        return new TreeNodeStruct((DefaultMutableTreeNode) par_);
    }

    Struct getPreviousSibling() {
        DefaultMutableTreeNode prev_ = treeNode.getPreviousSibling();
        if (prev_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return new TreeNodeStruct(prev_);
    }

    Struct getNextSibling() {
        DefaultMutableTreeNode next_ = treeNode.getNextSibling();
        if (next_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return new TreeNodeStruct(next_);
    }

    IntStruct getChildCount() {
        return new IntStruct(treeNode.getChildCount());
    }
    Struct getFirstChild() {
        if (treeNode.getChildCount() == 0) {
            return NullStruct.NULL_VALUE;
        }
        return new TreeNodeStruct((DefaultMutableTreeNode) treeNode.getChildAt(0));
    }

    Struct getLastChild() {
        int count_ = treeNode.getChildCount();
        if (count_ == 0) {
            return NullStruct.NULL_VALUE;
        }
        return new TreeNodeStruct((DefaultMutableTreeNode) treeNode.getChildAt(count_-1));

    }

    StringStruct getUserObject() {
        Object user_ = treeNode.getUserObject();
        if (!(user_ instanceof String)) {
            return new StringStruct("");
        }
        return new StringStruct((String) user_);
    }

    void setUserObject(Struct _struct) {
        if (_struct instanceof StringStruct) {
            treeNode.setUserObject(((StringStruct)_struct).getInstance());
        } else {
            treeNode.setUserObject("");
        }
    }

    DefaultMutableTreeNode getTreeNode() {
        return treeNode;
    }
    private static String getString(Struct _str) {
        if (_str instanceof StringStruct) {
            return ((StringStruct)_str).getInstance();
        }
        return "";
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return ((LgNamesGui)_contextEl.getStandards()).getAliasTreeNode();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof TreeNodeStruct)) {
            return false;
        }
        return treeNode == ((TreeNodeStruct)_other).treeNode;
    }
}
