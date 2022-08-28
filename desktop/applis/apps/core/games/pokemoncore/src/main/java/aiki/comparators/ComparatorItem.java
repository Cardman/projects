package aiki.comparators;
import aiki.facade.*;
import aiki.util.SortingItem;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class ComparatorItem implements Comparing<SortingItem> {

    private StringFieldComparator cmpName = new StringFieldComparator();

    private LongFieldComparator cmpPrice = new LongFieldComparator();

    private StringFieldComparator cmpDescription = new StringFieldComparator();

    private LgIntFieldComparator cmpNumber = new LgIntFieldComparator();

    private final int nbComparators;

    public ComparatorItem() {
        nbComparators = 0;
    }

    public ComparatorItem(StringFieldComparator _cmpName,
                          LongFieldComparator _cmpPrice,
                          StringFieldComparator _cmpDescription,
                          LgIntFieldComparator _cmpNumber, int _nbComparators) {
        cmpName = _cmpName;
        cmpPrice = _cmpPrice;
        cmpDescription = _cmpDescription;
        cmpNumber = _cmpNumber;
        nbComparators = _nbComparators;
    }

    @Override
    public int compare(SortingItem _o1, SortingItem _o2) {
        for (int i = nbComparators; i >= Pagination.MIN_PRIORITY; i--) {
            int res_ = res(_o1,_o2,i);
            if (res_ != SortConstants.EQ_CMP) {
                return res_;
            }
        }
        return NumberUtil.compareLg(_o1.getIndex(), _o2.getIndex());
    }
    private int res(SortingItem _o1, SortingItem _o2, int _i){
        if (cmpPrice.getPriority() == _i) {
            return cmpPrice.compare(_o1.getPrice(), _o2.getPrice());
        } else if (cmpName.getPriority() == _i) {
            return cmpName.compare(_o1.getName(), _o2.getName());
        } else if (cmpDescription.getPriority() == _i) {
            return cmpDescription.compare(_o1.getItemClass(), _o2.getItemClass());
        } else if (cmpNumber.getPriority() == _i) {
            return cmpNumber.compare(_o1.getNumber(), _o2.getNumber());
        }
        return SortConstants.EQ_CMP;
    }

}
