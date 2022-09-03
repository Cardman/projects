package aiki.comparators;

import aiki.facade.LongFieldComparator;
import aiki.facade.Pagination;
import aiki.facade.PaginationEgg;
import aiki.util.SortingEgg;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class ComparatorEgg extends ComparatorCommon implements Comparing<SortingEgg> {

    private final LongFieldComparator cmpSteps = new LongFieldComparator();

    @Override
    public int compare(SortingEgg _o1, SortingEgg _o2) {
        for (int i = PaginationEgg.NB_COMPARATORS; i >= Pagination.MIN_PRIORITY; i--) {
            if (cmpSteps.getPriority() == i) {
                int res_ = cmpSteps.compare(_o1.getSteps(), _o2.getSteps());
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            } else if (getCmpName().getPriority() == i) {
                int res_ = getCmpName().compare(_o1.getName(), _o2.getName());
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            }
        }
        return NumberUtil.compareLg(_o1.getIndex(), _o2.getIndex());
    }

    public LongFieldComparator getCmpSteps() {
        return cmpSteps;
    }
}
