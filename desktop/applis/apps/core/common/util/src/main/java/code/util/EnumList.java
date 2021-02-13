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
        while (i_ < copy_.size()) {
            if (i_ > IndexConstants.FIRST_INDEX) {
                int res_ = _cmp.compare(copy_.get(i_), copy_.get(i_-1));
                if (res_ != SortConstants.EQ_CMP) {
                    groups_.add(group_);
                    group_ = new EnumList<T>();
                }
            }
            group_.add(copy_.get(i_));
            i_++;
        }
        groups_.add(group_);
        return groups_;
    }
    @Override
    public boolean match(T _one, T _two) {
        return _one == _two;
    }

}
