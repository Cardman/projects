package code.util;
import code.util.core.IndexConstants;
import code.util.core.SortConstants;
import code.util.ints.Comparing;
import code.util.ints.Listable;

/** Not compared in tests */
public final class EnumList<T> extends AbEqList<T> {

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

    public CustList<EnumList<T>> getGroupsSameCompare(Comparing<T> _cmp) {
        CustList<T> copy_ = new CustList<T>(this);
        copy_.sortElts(_cmp);
        CustList<EnumList<T>> groups_;
        groups_ = new CustList<EnumList<T>>();
        EnumList<T> group_;
        group_ = new EnumList<T>();
        int i_ = IndexConstants.FIRST_INDEX;
        int j_ = i_;
        j_--;
        while (i_ < copy_.size()) {
            if (i_ > IndexConstants.FIRST_INDEX) {
                int res_ = _cmp.compare(copy_.get(i_), copy_.get(j_));
                if (res_ != SortConstants.EQ_CMP) {
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
    @Override
    public boolean match(T _one, T _two) {
        return _one == _two;
    }

}
