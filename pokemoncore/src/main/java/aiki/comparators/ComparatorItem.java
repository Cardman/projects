package aiki.comparators;
import java.util.Comparator;

import code.maths.LgInt;
import code.util.CustList;
import code.util.Numbers;
import code.util.pagination.FieldComparator;
import code.util.pagination.FieldCustComparator;
import code.util.pagination.Pagination;
import aiki.util.SortingItem;

public final class ComparatorItem implements Comparator<SortingItem> {

    private FieldComparator<String> cmpName = new FieldComparator<String>();

    private FieldComparator<Integer> cmpPrice = new FieldComparator<Integer>();

    private FieldComparator<String> cmpDescription = new FieldComparator<String>();

    private FieldCustComparator<LgInt> cmpNumber = new FieldCustComparator<LgInt>();

    private final int nbComparators;

    public ComparatorItem() {
        nbComparators = 0;
    }

    public ComparatorItem(FieldComparator<String> _cmpName,
            FieldComparator<Integer> _cmpPrice,
            FieldComparator<String> _cmpDescription,
            FieldCustComparator<LgInt> _cmpNumber, int _nbComparators) {
        cmpName = _cmpName;
        cmpPrice = _cmpPrice;
        cmpDescription = _cmpDescription;
        cmpNumber = _cmpNumber;
        nbComparators = _nbComparators;
    }

    @Override
    public int compare(SortingItem _o1, SortingItem _o2) {
        for (int i = nbComparators; i >= Pagination.MIN_PRIORITY; i--) {
            if (cmpPrice.getPriority() == i) {
                int res_ = cmpPrice.compare(_o1.getPrice(), _o2.getPrice());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpName.getPriority() == i) {
                int res_ = cmpName.compare(_o1.getName(), _o2.getName());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpDescription.getPriority() == i) {
                int res_ = cmpDescription.compare(_o1.getItemClass(), _o2.getItemClass());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpNumber.getPriority() == i) {
                int res_ = cmpNumber.compare(_o1.getNumber(), _o2.getNumber());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            }
        }
        return Numbers.compare(_o1.getIndex(), _o2.getIndex());
    }

}
