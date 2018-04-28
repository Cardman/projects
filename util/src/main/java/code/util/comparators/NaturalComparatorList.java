package code.util.comparators;
import code.util.ints.Comparing;

import code.util.CustList;
import code.util.SortableList;

public final class NaturalComparatorList<K extends Comparable<K>> implements Comparing<SortableList<K>> {

    @Override
    public int compare(SortableList<K> _o1, SortableList<K> _o2) {
        int minimumSize_ = Math.min(_o1.size(), _o2.size());
        for (int i = CustList.FIRST_INDEX;i<minimumSize_;i++) {
            int res_ = _o1.get(i).compareTo(_o2.get(i));
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
