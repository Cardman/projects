package code.util.comparators;
import java.util.Comparator;

import code.util.AbEqList;
import code.util.CustList;
import code.util.exceptions.NullComparatorException;

public final class ComparatorList<K> implements Comparator<AbEqList<K>> {

    private static final String NULL_CMP = "The comparator arg is null";

    private Comparator<K> cmp;
    public ComparatorList(Comparator<K> _cmp) {
        if (_cmp == null) {
            throw new NullComparatorException(NULL_CMP);
        }
        cmp = _cmp;
    }
    @Override
    public int compare(AbEqList<K> _o1, AbEqList<K> _o2) {
        int minimumSize_ = Math.min(_o1.size(), _o2.size());
        for (int i=CustList.FIRST_INDEX;i<minimumSize_;i++) {
            int res_ = cmp.compare(_o1.get(i), _o2.get(i));
            if (res_ != 0) {
                return res_;
            }
        }
        if (_o1.size() > _o2.size()) {
            return 1;
        }
        if (_o1.size() < _o2.size()) {
            return -1;
        }
        return 0;
    }
}
