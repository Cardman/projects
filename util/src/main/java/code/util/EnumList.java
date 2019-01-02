package code.util;
import code.util.comparators.ComparatorEnum;
import code.util.ints.Comparing;
import code.util.ints.Equallable;
import code.util.ints.Listable;

/** Not compared in tests */
public final class EnumList<T extends Enum<T>> extends AbEqList<T> implements Equallable<EnumList<T>> {

    public EnumList() {
    }

    public EnumList(Listable<T> _c) {
        super(_c);
    }

    public EnumList(T... _elements) {
        super(_elements);
    }


    public EnumList(CollCapacity _capacity) {
        super(_capacity);
    }

    public void sort() {
        sortElts(new ComparatorEnum<T>());
    }

    public CustList<EnumList<T>> getGroupsSameCompare(Comparing<T> _cmp) {
        CustList<T> copy_ = new CustList<T>(this);
        copy_.sortElts(_cmp);
        CustList<EnumList<T>> groups_;
        groups_ = new CustList<EnumList<T>>();
        EnumList<T> group_;
        group_ = new EnumList<T>();
        int i_ = CustList.FIRST_INDEX;
        int j_ = i_;
        j_--;
        while (i_ < copy_.size()) {
            if (i_ > CustList.FIRST_INDEX) {
                int res_ = _cmp.compare(copy_.get(i_), copy_.get(j_));
                if (res_ != EQ_CMP) {
                    groups_.add(group_);
                    group_ = new EnumList<T>();
                }
            }
            group_.add(copy_.get(i_));
            i_++;
            j_++;
        }
        groups_.add(group_);
        return groups_;
    }
    public Numbers<Integer> indexesOfObj(T _element) {
        Numbers<Integer> indexes_;
        indexes_ = new Numbers<Integer>();
        int i_ = FIRST_INDEX;
        while (true) {
            int found_ = indexOfObj(_element, i_);
            if (found_ == INDEX_NOT_FOUND_ELT) {
                break;
            }
            indexes_.add(found_);
            i_ = found_ + 1;
        }
        return indexes_;
    }
    @Override
    public int indexOfObj(T _element, int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            T e_ = get(i);
            if (e_ == _element) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }

    @Override
    public EnumList<T> subAbEq(int _from, int _to) {
        return sub(_from, _to);
    }


    public void removeDuplicates()  {
        int i_ = FIRST_INDEX;
        while (true) {
            if(i_ >= size()) {
                break;
            }
            T e_ = get(i_);
            boolean rem_ = false;
            int next_ = indexOfObj(e_, i_ + 1);
            while (next_ != INDEX_NOT_FOUND_ELT) {
                removeAt(next_);
                rem_ = true;
                next_ = indexOfObj(e_, next_ + 1);
            }
            if (!rem_) {
                i_++;
            }
        }
    }
    @Override
    public EnumList<T> sub(int _from, int _to) {
        if (_from > _to) {
            return new EnumList<T>();
        }
        return new EnumList<T>(super.sub(_from, _to));
    }

    @Override
    public boolean eq(EnumList<T> _g) {
        if (_g == null) {
            return false;
        }
        int len_ = size();
        if (_g.size() != len_) {
            return false;
        }
        for (int i = FIRST_INDEX; i < len_; i++) {
            T e_ = get(i);
            T f_ = _g.get(i);
            if (f_ != e_) {
                return false;
            }
        }
        return true;
    }

}
