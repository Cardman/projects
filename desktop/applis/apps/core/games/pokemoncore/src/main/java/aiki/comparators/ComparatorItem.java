package aiki.comparators;
import aiki.facade.*;
import aiki.util.SortingItem;
import code.maths.LgInt;
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
            if (cmpPrice.getPriority() == i) {
                int res_ = cmpPrice.compare(_o1.getPrice(), _o2.getPrice());
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            } else if (cmpName.getPriority() == i) {
                int res_ = cmpName.compare(_o1.getName(), _o2.getName());
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            } else if (cmpDescription.getPriority() == i) {
                int res_ = cmpDescription.compare(_o1.getItemClass(), _o2.getItemClass());
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            } else if (cmpNumber.getPriority() == i) {
                int res_ = cmpNumber.compare(_o1.getNumber(), _o2.getNumber());
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            }
        }
        return NumberUtil.compareLg(_o1.getIndex(), _o2.getIndex());
    }

}
