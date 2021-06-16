package code.gui;

import code.util.Ints;

public final class MutableTreeNodeUtil {
    private MutableTreeNodeUtil() {
    }
    public static Ints getIndexes(AbstractMutableTreeNode _current) {
        AbstractMutableTreeNode parent_ = _current;
        Ints pars_ = new Ints();
        while (parent_ != null) {
            int index_ = parent_.getIndex();
            if (index_ > -1) {
                pars_.add(0, index_);
            }
            parent_ = parent_.getParent();
        }
        return pars_;
    }
}
