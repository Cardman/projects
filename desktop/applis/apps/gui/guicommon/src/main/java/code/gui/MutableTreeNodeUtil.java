package code.gui;

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

    public static AbstractMutableTreeNode original(AbstractMutableTreeNode _node) {
        if (_node == null) {
            return null;
        }
        return _node.original();
    }
}
