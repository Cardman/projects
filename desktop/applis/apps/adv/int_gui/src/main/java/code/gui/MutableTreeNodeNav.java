package code.gui;

import code.util.IdList;
import code.util.Ints;

public class MutableTreeNodeNav<T> implements AbstractMutableTreeNodeCore<T>{
    private AbstractMutableTreeNodeCore<T> parent;
    private AbstractMutableTreeNodeCore<T> firstChild;
    private AbstractMutableTreeNodeCore<T> nextSibling;
    private T current;

    @Override
    public T info() {
        return current;
    }

    @Override
    public void info(T _t) {
        current = _t;
    }

    @Override
    public AbstractMutableTreeNodeCore<T> getFirstChildReal() {
        return getFirstChild();
    }

    @Override
    public AbstractMutableTreeNodeCore<T> getNextSiblingReal() {
        return getNextSibling();
    }

    @Override
    public AbstractMutableTreeNodeCore<T> getParentReal() {
        return getParent();
    }

    @Override
    public AbstractMutableTreeNodeCore<T> getParent() {
        return parent;
    }

    @Override
    public AbstractMutableTreeNodeCore<T> getFirstChild() {
        return firstChild;
    }

    @Override
    public AbstractMutableTreeNodeCore<T> getNextSibling() {
        return nextSibling;
    }

    @Override
    public void setParent(AbstractMutableTreeNodeCore<T> _v) {
        parent = _v;
    }

    @Override
    public void setFirstChild(AbstractMutableTreeNodeCore<T> _v) {
        firstChild = _v;
    }

    @Override
    public void setNextSibling(AbstractMutableTreeNodeCore<T> _v) {
        nextSibling = _v;
    }

    public boolean add(AbstractMutableTreeNodeCore<T> _m) {
        append(_m);
        return false;
    }

    private void append(AbstractMutableTreeNodeCore<T> _m) {
        _m.removeFromParent();
        IdList<AbstractMutableTreeNodeCore<T>> ch_ = children();
        _m.setParent(this);
        if (ch_.isEmpty()) {
            setFirstChild(_m);
        } else {
            ch_.last().setNextSibling(_m);
        }
    }


    public AbstractMutableTreeNodeCore<T> remove(int _index) {
        int index_ = 0;
        AbstractMutableTreeNodeCore<T> prev_ = null;
        AbstractMutableTreeNodeCore<T> curr_ = getFirstChild();
        while (curr_ != null) {
            if (index_ == _index) {
                clear(prev_, curr_);
                return curr_;
            }
            prev_ = curr_;
            curr_ = curr_.getNextSibling();
            index_++;
        }
        return null;
    }
    public int remove(AbstractMutableTreeNodeCore<T> _m) {
        int index_ = 0;
        AbstractMutableTreeNodeCore<T> prev_ = null;
        AbstractMutableTreeNodeCore<T> curr_ = getFirstChild();
        while (curr_ != null) {
            if (curr_ == _m) {
                clear(prev_, curr_);
                return index_;
            }
            prev_ = curr_;
            curr_ = curr_.getNextSibling();
            index_++;
        }
        return -1;
    }

    private void clear(AbstractMutableTreeNodeCore<T> _prev, AbstractMutableTreeNodeCore<T> _curr) {
        if (_prev == null) {
            setFirstChild(_curr.getNextSibling());
        } else {
            _prev.setNextSibling(_curr.getNextSibling());
        }
        _curr.setNextSibling(null);
        _curr.setParent(null);
    }

    @Override
    public int getIndex() {
        AbstractMutableTreeNodeCore<T> par_ = getParent();
        if (par_ == null) {
            return -1;
        }
        return par_.children().indexOfObj(this);
    }

    @Override
    public int getAntiIndex(AbstractMutableTreeNodeCore<T> _treeNode) {
        return children().indexOfObj(_treeNode);
    }

    @Override
    public int getChildCount() {
        return children().size();
    }

    @Override
    public AbstractMutableTreeNodeCore<T> getPreviousSibling() {
        int index_ = getIndex();
        if (index_ < 1) {
            return null;
        }
        IdList<AbstractMutableTreeNodeCore<T>> ch_ = getParent().children();
        return ch_.get(index_ - 1);
    }

    @Override
    public AbstractMutableTreeNodeCore<T> getChildAt(int _i) {
        IdList<AbstractMutableTreeNodeCore<T>> ch_ = children();
        if (ch_.isValidIndex(_i)) {
            return ch_.get(_i);
        }
        return null;
    }

    public int removeAllChildren() {
        int ch_ = getChildCount();
        for (AbstractMutableTreeNodeCore<T> m: children()) {
            m.setParent(null);
            m.setNextSibling(null);
        }
        setFirstChild(null);
        return ch_;
    }
    public AbstractMutableTreeNodeCore<T> simular(AbstractMutableTreeNodeCore<String> _e) {
        if (_e == null) {
            return null;
        }
        return getElt(_e.getIndexes());
    }
    public AbstractMutableTreeNodeCore<T> getElt(Ints _indexes) {
        int nb_ = _indexes.size();
        AbstractMutableTreeNodeCore<T> cur_ = this;
        for (int i = 0; i < nb_; i++) {
            if (cur_ == null) {
                return null;
            }
            cur_ = cur_.getChildAt(_indexes.get(i));
        }
        return cur_;
    }

    public IdList<AbstractMutableTreeNodeCore<T>> children() {
        IdList<AbstractMutableTreeNodeCore<T>> ch_ = new IdList<AbstractMutableTreeNodeCore<T>>();
        AbstractMutableTreeNodeCore<T> curr_ = getFirstChild();
        while (curr_ != null) {
            ch_.add(curr_);
            curr_ = curr_.getNextSibling();
        }
        return ch_;
    }

    public Ints getIndexes() {
        AbstractMutableTreeNodeCore<T> parent_ = this;
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

    public int insert(AbstractMutableTreeNodeCore<T> _m, int _i) {
        _m.removeFromParent();
        int index_ = 0;
        AbstractMutableTreeNodeCore<T> prev_ = null;
        AbstractMutableTreeNodeCore<T> curr_ = getFirstChild();
        while (curr_ != null) {
            if (index_ == _i) {
                _m.setParent(this);
                insert(_m, prev_, curr_);
                return _i;
            }
            prev_ = curr_;
            curr_ = curr_.getNextSibling();
            index_++;
        }
        if (index_ == _i) {
            _m.setParent(this);
            insert(_m, prev_, null);
            return _i;
        }
        return -1;
    }

    private void insert(AbstractMutableTreeNodeCore<T> _m, AbstractMutableTreeNodeCore<T> _prev, AbstractMutableTreeNodeCore<T> _curr) {
        if (_prev == null) {
            _m.setNextSibling(getFirstChild());
            setFirstChild(_m);
        } else {
            _m.setNextSibling(_curr);
            _prev.setNextSibling(_m);
        }
    }
    @Override
    public AbstractMutableTreeNodeCore<T> removeFromParent() {
        if (getParent() != null) {
            AbstractMutableTreeNodeCore<T> old_ = getParent();
            AbstractMutableTreeNodeCore<T> prev_ = getPreviousSibling();
            if (prev_ == null) {
                getParent().setFirstChild(getNextSibling());
                setParent(null);
                setNextSibling(null);
                return old_;
            }
            prev_.setNextSibling(getNextSibling());
            setParent(null);
            setNextSibling(null);
            return old_;
        }
        return null;
    }
}
