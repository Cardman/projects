package code.gui;

import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.Ints;

public final class MutableTreeNodeUtil {
    private MutableTreeNodeUtil() {
    }
    public static void reload(AbsTreeGui _abs) {
        AbstractMutableTreeNode sel_ = _abs.selectEvt();
        if (sel_ != null) {
            _abs.reload(sel_);
        } else {
            _abs.reloadRoot();
        }
    }
    public static CustList<AbstractMutableTreeNode> list(AbstractMutableTreeNode _root,AbsTreePaths _paths) {
        int len_ = _paths.getLength();
        CustList<AbstractMutableTreeNode> ls_ = new CustList<AbstractMutableTreeNode>();
        for (int i = 0; i < len_; i++) {
            ls_.add(_paths.elt(_root,i).data());
        }
        return ls_;
    }
    public static AbsTreePaths list(AbsCompoFactory _compo, AbsTreeGui _root, CustList<AbstractMutableTreeNode> _paths) {
        int len_ = _paths.size();
        AbsTreePaths ls_ = _compo.emptyList();
        for (int i = 0; i < len_; i++) {
            ls_.add(_root.translate(_paths.get(i)));
        }
        return ls_;
    }
    public static Ints getIndexes(AbstractMutableTreeNode _current) {
        AbstractMutableTreeNode parent_ = _current;
        Ints pars_ = new Ints();
        while (parent_ != null) {
            int index_ = parent_.getIndex();
            if (index_ > -1) {
                pars_.add(0, index_);
            }
            parent_ = parent_.getParentReal();
        }
        return pars_;
    }

}
