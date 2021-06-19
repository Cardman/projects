package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.gui.AbstractMutableTreeNode;
import code.util.CustList;
import code.util.core.StringUtil;

public final class TreeNodeStruct extends WithoutParentStruct implements Struct {

    private final AbstractMutableTreeNode treeNode;
    private StringStruct userObject = new StringStruct("");

    TreeNodeStruct(AbstractMutableTreeNode _str) {
        treeNode = _str;
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
        return BooleanStruct.of(isAncestor(_node));
    }
    BooleanStruct isDescendantMethod(Struct _node) {
        return BooleanStruct.of(isDescendant(_node));
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
        AbstractMutableTreeNode par_ = treeNode.getParent();
        return nodeOrNull(par_);
    }

    Struct getPreviousSibling() {
        AbstractMutableTreeNode prev_ = treeNode.getPreviousSibling();
        return nodeOrNull(prev_);
    }

    Struct getNextSibling() {
        AbstractMutableTreeNode next_ = treeNode.getNextSibling();
        return nodeOrNull(next_);
    }

    static Struct nodeOrNull(AbstractMutableTreeNode _value) {
        if (_value == null) {
            return NullStruct.NULL_VALUE;
        }
        return new TreeNodeStruct(_value);
    }

    IntStruct getChildCount() {
        return new IntStruct(treeNode.getChildCount());
    }
    Struct getFirstChild() {
        AbstractMutableTreeNode ch_ = treeNode.getChildAt(0);
        return nodeOrNull(ch_);
    }

    Struct getLastChild() {
        int count_ = treeNode.getChildCount();
        AbstractMutableTreeNode ch_ = treeNode.getChildAt(count_ - 1);
        return nodeOrNull(ch_);

    }

    static boolean eqPath(CustList<TreeNodeStruct> _one, CustList<TreeNodeStruct> _two) {
        int len_ = _one.size();
        if (len_ != _two.size()) {
            return false;
        }
        for (int i = 0; i < len_; i++) {
            if (!StringUtil.quickEq(_one.get(i).userObject.getInstance(),_two.get(i).userObject.getInstance())) {
                return false;
            }
        }
        return true;
    }
    static boolean eqIndexes(CustList<IntStruct> _one, CustList<IntStruct> _two) {
        int len_ = _one.size();
        if (len_ != _two.size()) {
            return false;
        }
        for (int i = 0; i < len_; i++) {
            if (_one.get(i).intStruct() != _two.get(i).intStruct()) {
                return false;
            }
        }
        return true;
    }
    ArrayStruct getPath(LgNames _stds) {
        CustList<TreeNodeStruct> pars_ = getPath();
        int len_ = pars_.size();
        ArrayStruct arr_ = new ArrayStruct(len_,StringExpUtil.getPrettyArrayType(_stds.getCharSeq().getAliasString()));
        for (int i = 0; i < len_; i++) {
            arr_.set(i,pars_.get(i).userObject);
        }
        return arr_;
    }
    CustList<TreeNodeStruct> getPath() {
        CustList<TreeNodeStruct> pars_ = new CustList<TreeNodeStruct>();
        Struct par_ = getParentNode();
        while (par_ instanceof TreeNodeStruct) {
            pars_.add(0, (TreeNodeStruct) par_);
            par_ = ((TreeNodeStruct)par_).getParentNode();
        }
        return pars_;
    }
    IntStruct getIndex() {
        return new IntStruct(treeNode.getIndex());
    }
    CustList<IntStruct> getIndexes() {
        CustList<IntStruct> pars_ = new CustList<IntStruct>();
        Struct par_ = getParentNode();
        while (par_ instanceof TreeNodeStruct) {
            pars_.add(0, ((TreeNodeStruct) par_).getIndex());
            par_ = ((TreeNodeStruct)par_).getParentNode();
        }
        return pars_;
    }
    StringStruct getUserObject() {
        return userObject;
    }

    void setUserObject(Struct _struct) {
        userObject= NumParsers.getString(_struct);
    }

    AbstractMutableTreeNode getTreeNode() {
        return treeNode;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui)_contextEl.getStandards()).getGuiAliases().getAliasTreeNode();
    }

    static BooleanStruct eq(Struct _current, Struct _other) {
        if (!(_current instanceof TreeNodeStruct)||!(_other instanceof TreeNodeStruct)) {
            return BooleanStruct.of(_current.sameReference(_other));
        }
        return BooleanStruct.of(eqIndexes(((TreeNodeStruct)_current).getIndexes(),((TreeNodeStruct)_other).getIndexes()));
    }
    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof TreeNodeStruct)) {
            return false;
        }
        return treeNode == ((TreeNodeStruct)_other).treeNode;
    }

    @Override
    public long randCode() {
        return 1;
    }
}
