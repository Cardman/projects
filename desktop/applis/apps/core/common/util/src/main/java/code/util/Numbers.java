package code.util;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Listable;

public abstract class Numbers<T> extends CustList<T> {

    protected Numbers() {
    }

    protected Numbers(Listable<T> _list) {
        super(_list);
    }

    protected Numbers(T... _array) {
        super(_array);
    }


    protected Numbers(CollCapacity _capacity) {
        super(_capacity);
    }

    public final void sort() {
        //setModified();
        int len_ = size();
        for (int i = IndexConstants.FIRST_INDEX; i <len_; i++) {
            for (int j = i + 1; j <len_; j++) {
                long one_ = getLong(i);
                long two_ = getLong(j);
                int res_ = NumberUtil.compareLg(one_, two_);
                if (res_ > SortConstants.EQ_CMP) {
                    swapIndexes(i,j);
                }
            }
        }
    }

    public final long getMinimum(long _def) {
        if (isEmpty()) {
            return _def;
        }
        long min_ = getLong(IndexConstants.FIRST_INDEX);
        int size_ = size();
        for (int i = IndexConstants.SECOND_INDEX; i<size_; i++) {
            long cur_ = getLong(i);
            if (min_> cur_) {
                min_ = cur_;
            }
        }
        return min_;
    }

    public final long getMaximum(long _def) {
        if (isEmpty()) {
            return _def;
        }
        long max_ = getLong(IndexConstants.FIRST_INDEX);
        int size_ = size();
        for (int i = IndexConstants.SECOND_INDEX; i<size_; i++) {
            long cur_ = getLong(i);
            if (max_< cur_) {
                max_ = cur_;
            }
        }
        return max_;
    }
    public final void removeOneNumber(long _n) {
        int s_ = size();
        for (int i = 0; i < s_; i++) {
            long lg_ = getLong(i);
            if (NumberUtil.eq(lg_,_n)) {
                remove(i);
                break;
            }
        }
    }
    public final boolean contains(long _element) {
        return indexOf(_element) != IndexConstants.INDEX_NOT_FOUND_ELT;
    }
    public final int indexOf(long _element) {
        int s_ = size();
        for (int i = 0; i < s_; i++) {
            if (_element == getLong(i)) {
                return i;
            }
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }


    public final void removeObj(long _obj) {
        int index_ = indexOfNb(_obj);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        remove(index_);
    }

    public final boolean containsObj(long _obj) {
        return indexOfNb(_obj) != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public final void removeDuplicates() {
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < size()) {
            long e_ = getLong(i_);
            boolean rem_ = false;
            int next_ = indexOfNb(e_, i_ + 1);
            while (next_ != IndexConstants.INDEX_NOT_FOUND_ELT) {
                remove(next_);
                rem_ = true;
                next_ = indexOfNb(e_, i_ + 1);
            }
            if (!rem_) {
                i_++;
            }
        }
    }
    public final boolean hasDuplicates() {
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < size()) {
            long e_ = getLong(i_);
            int next_ = indexOfNb(e_, i_ + 1);
            if (next_ > IndexConstants.INDEX_NOT_FOUND_ELT) {
                return true;
            }
            i_++;
        }
        return false;
    }
    public final int indexOfNb(long _element) {
        return indexOfNb(_element, IndexConstants.FIRST_INDEX);
    }
    public final int indexOfNb(long _element, int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            long e_ = getLong(i);
            if (NumberUtil.eq(_element, e_)) {
                return i;
            }
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }
    public final Ints indexesOfObj(long _element) {
        Ints indexes_;
        indexes_ = new Ints();
        int i_ = IndexConstants.FIRST_INDEX;
        while (true) {
            int found_ = indexOfNb(_element, i_);
            if (found_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
                break;
            }
            indexes_.add(found_);
            i_ = found_ + 1;
        }
        return indexes_;
    }

    public final void removeAllLong(long _obj) {
        int index_ = size() - 1;
        while (index_ >= IndexConstants.FIRST_INDEX) {
            long current_ = getLong(index_);
            if (_obj == current_) {
                remove(index_);
            }
            index_--;
        }
    }

    abstract long getLong(int _index);
}
