package code.util.comparators;
import code.util.ints.Comparing;

import code.util.AbEqList;
import code.util.CustList;

public final class ComparatorList<K> implements Comparing<AbEqList<K>> {

    private Comparing<K> cmp;
    public ComparatorList(Comparing<K> _cmp) {
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
