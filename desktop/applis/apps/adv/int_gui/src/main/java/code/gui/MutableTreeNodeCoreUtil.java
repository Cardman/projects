package code.gui;

import code.util.IdList;
import code.util.Ints;

public final class MutableTreeNodeCoreUtil {
    private MutableTreeNodeCoreUtil() {
    }

    public static AbstractMutableTreeNodeCore getElt(AbstractMutableTreeNodeCore _root, Ints _indexes) {
        int nb_ = _indexes.size();
        AbstractMutableTreeNodeCore cur_ = _root;
        for (int i = 0; i < nb_; i++) {
            if (cur_ == null) {
                return null;
            }
            cur_ = getChildAt(cur_,_indexes.get(i));
        }
        return cur_;
    }
    public static int getIndex(AbstractMutableTreeNodeCore _c) {
        AbstractMutableTreeNodeCore par_ = _c.getParent();
        if (par_ == null) {
            return -1;
        }
        return children(par_).indexOfObj(_c);
    }

    public static int getAntiIndex(AbstractMutableTreeNodeCore _c,AbstractMutableTreeNodeCore _m) {
        return children(_c).indexOfObj(_m);
    }

    public static boolean add(AbstractMutableTreeNodeCore _c,AbstractMutableTreeNodeCore _m) {
        append(_c,_m);
        return false;
    }

    private static void append(AbstractMutableTreeNodeCore _c,AbstractMutableTreeNodeCore _m) {
        removeFromParent(_m);
        IdList<AbstractMutableTreeNodeCore> ch_ = children(_c);
        _m.setParent(_c);
        if (ch_.isEmpty()) {
            _c.setFirstChild(_m);
        } else {
            ch_.last().setNextSibling(_m);
        }
    }

    public static int getChildCount(AbstractMutableTreeNodeCore _c) {
        return children(_c).size();
    }

    public static int insert(AbstractMutableTreeNodeCore _c,AbstractMutableTreeNodeCore _m, int _i) {
        removeFromParent(_m);
        int index_ = 0;
        AbstractMutableTreeNodeCore prev_ = null;
        AbstractMutableTreeNodeCore curr_ = _c.getFirstChild();
        while (curr_ != null) {
            if (index_ == _i) {
                _m.setParent(_c);
                insert(_c, _m, prev_, curr_);
                return _i;
            }
            prev_ = curr_;
            curr_ = curr_.getNextSibling();
            index_++;
        }
        if (index_ == _i) {
            _m.setParent(_c);
            insert(_c, _m, prev_, null);
            return _i;
        }
        return -1;
    }

    private static void insert(AbstractMutableTreeNodeCore _c,AbstractMutableTreeNodeCore _m, AbstractMutableTreeNodeCore _prev, AbstractMutableTreeNodeCore _curr) {
        if (_prev == null) {
            _m.setNextSibling(_c.getFirstChild());
            _c.setFirstChild(_m);
        } else {
            _m.setNextSibling(_curr);
            _prev.setNextSibling(_m);
        }
    }

    public static int removeAllChildren(AbstractMutableTreeNodeCore _c) {
        int ch_ = getChildCount(_c);
        for (AbstractMutableTreeNodeCore m: children(_c)) {
            m.setParent(null);
            m.setNextSibling(null);
        }
        _c.setFirstChild(null);
        return ch_;
    }

    public static AbstractMutableTreeNodeCore getParent(AbstractMutableTreeNodeCore _c) {
        return _c.getParent();
    }

    public static int remove(AbstractMutableTreeNodeCore _c,AbstractMutableTreeNodeCore _m) {
        int index_ = 0;
        AbstractMutableTreeNodeCore prev_ = null;
        AbstractMutableTreeNodeCore curr_ = _c.getFirstChild();
        while (curr_ != null) {
            if (curr_ == _m) {
                clear(_c,prev_, curr_);
                return index_;
            }
            prev_ = curr_;
            curr_ = curr_.getNextSibling();
            index_++;
        }
        return -1;
    }

    public static AbstractMutableTreeNodeCore remove(AbstractMutableTreeNodeCore _c,int _index) {
        int index_ = 0;
        AbstractMutableTreeNodeCore prev_ = null;
        AbstractMutableTreeNodeCore curr_ = _c.getFirstChild();
        while (curr_ != null) {
            if (index_ == _index) {
                clear(_c,prev_, curr_);
                return curr_;
            }
            prev_ = curr_;
            curr_ = curr_.getNextSibling();
            index_++;
        }
        return null;
    }

    private static void clear(AbstractMutableTreeNodeCore _c,AbstractMutableTreeNodeCore _prev, AbstractMutableTreeNodeCore _curr) {
        if (_prev == null) {
            _c.setFirstChild(_curr.getNextSibling());
        } else {
            _prev.setNextSibling(_curr.getNextSibling());
        }
        _curr.setNextSibling(null);
        _curr.setParent(null);
    }

    public static AbstractMutableTreeNodeCore getChildAt(AbstractMutableTreeNodeCore _c,int _i) {
        IdList<AbstractMutableTreeNodeCore> ch_ = children(_c);
        if (ch_.isValidIndex(_i)) {
            return ch_.get(_i);
        }
        return null;
    }

    public static AbstractMutableTreeNodeCore getPreviousSibling(AbstractMutableTreeNodeCore _c) {
        int index_ = getIndex(_c);
        if (index_ < 1) {
            return null;
        }
        IdList<AbstractMutableTreeNodeCore> ch_ = children(_c.getParent());
        return ch_.get(index_ - 1);
    }

    public static AbstractMutableTreeNodeCore getNextSibling(AbstractMutableTreeNodeCore _c) {
        return _c.getNextSibling();
    }

    public static AbstractMutableTreeNodeCore removeFromParent(AbstractMutableTreeNodeCore _c) {
        if (_c.getParent() != null) {
            AbstractMutableTreeNodeCore old_ = getParent(_c);
            AbstractMutableTreeNodeCore prev_ = getPreviousSibling(_c);
            if (prev_ == null) {
                _c.getParent().setFirstChild(_c.getNextSibling());
                _c.setParent(null);
                _c.setNextSibling(null);
                return old_;
            }
            prev_.setNextSibling(_c.getNextSibling());
            _c.setParent(null);
            _c.setNextSibling(null);
            return old_;
        }
        return null;
    }

    public static IdList<AbstractMutableTreeNodeCore> children(AbstractMutableTreeNodeCore _c) {
        IdList<AbstractMutableTreeNodeCore> ch_ = new IdList<AbstractMutableTreeNodeCore>();
        AbstractMutableTreeNodeCore curr_ = _c.getFirstChild();
        while (curr_ != null) {
            ch_.add(curr_);
            curr_ = curr_.getNextSibling();
        }
        return ch_;
    }
}
