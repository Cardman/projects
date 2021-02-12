package code.util;
import code.util.core.IndexConstants;
import code.util.ints.Listable;

public abstract class AbEqList<T> extends CustList<T> {

    protected AbEqList() {
    }

    protected AbEqList(Listable<T> _c) {
        super(_c);
    }

    protected AbEqList(T... _elements) {
        super(_elements);
    }

    protected AbEqList(CollCapacity _capacity) {
        super(_capacity);
    }

    public final void removeAllElements(Listable<T> _c) {
        for (T s: _c) {
            removeAllObj(s);
        }
    }

    public final void removeAllObj(T _obj) {
        int index_ = size() - 1;
        while (index_ >= IndexConstants.FIRST_INDEX) {
            T current_ = get(index_);
            if (match(_obj,current_)) {
                remove(index_);
            }
            index_--;
        }
    }

    public final void removeObj(T _obj) {
        int index_ = indexOfObj(_obj);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        remove(index_);
    }

    public final boolean containsAllObj(Listable<T> _list) {
        for (T e: _list) {
            if (!containsObj(e)) {
                return false;
            }
        }
        return true;
    }
    public final boolean containsObj(T _obj) {
        return indexOfObj(_obj) != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public final int indexOfObj(T _obj) {
        return indexOfObj(_obj, IndexConstants.FIRST_INDEX);
    }

    public void removeDuplicates()  {
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < size()) {
            T e_ = get(i_);
            boolean rem_ = false;
            int next_ = indexOfObj(e_, i_ + 1);
            while (next_ != IndexConstants.INDEX_NOT_FOUND_ELT) {
                remove(next_);
                rem_ = true;
                next_ = indexOfObj(e_, i_ + 1);
            }
            if (!rem_) {
                i_++;
            }
        }
    }

    public boolean hasDuplicates() {
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < size()) {
            T e_ = get(i_);
            int next_ = indexOfObj(e_, i_ + 1);
            if (next_ != IndexConstants.INDEX_NOT_FOUND_ELT) {
                return true;
            }
            i_++;
        }
        return false;
    }
    public Ints indexesOfObj(T _element) {
        Ints indexes_;
        indexes_ = new Ints();
        int i_ = IndexConstants.FIRST_INDEX;
        while (true) {
            int found_ = indexOfObj(_element, i_);
            if (found_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
                break;
            }
            indexes_.add(found_);
            i_ = found_ + 1;
        }
        return indexes_;
    }

    public int indexOfObj(T _element, int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            T e_ = get(i);
            if (match(_element,e_)) {
                return i;
            }
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }
    public boolean eq(AbEqList<T> _b) {
        int len_ = size();
        if (_b.size() != len_) {
            return false;
        }
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            T e_ = get(i);
            T f_ = _b.get(i);
            if (!match(e_,f_)) {
                return false;
            }
        }
        return true;
    }
    public abstract boolean match(T _one, T _two);

}
