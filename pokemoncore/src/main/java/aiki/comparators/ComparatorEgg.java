package aiki.comparators;
import aiki.facade.LongFieldComparator;
import aiki.facade.Pagination;
import aiki.facade.StringFieldComparator;
import aiki.util.SortingEgg;
import code.util.CustList;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class ComparatorEgg implements Comparing<SortingEgg> {

    private LongFieldComparator cmpSteps = new LongFieldComparator();

    private StringFieldComparator cmpName = new StringFieldComparator();

    private final int nbComparators;

    public ComparatorEgg() {
        nbComparators = 0;
    }

    public ComparatorEgg(LongFieldComparator _cmpSteps,
            StringFieldComparator _cmpName,
            int _nbComparators) {
        cmpSteps = _cmpSteps;
        cmpName = _cmpName;
        nbComparators = _nbComparators;
    }

    @Override
    public int compare(SortingEgg _o1, SortingEgg _o2) {
        for (int i = nbComparators; i >= Pagination.MIN_PRIORITY; i--) {
            if (cmpSteps.getPriority() == i) {
                int res_ = cmpSteps.compare(_o1.getSteps(), _o2.getSteps());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpName.getPriority() == i) {
                int res_ = cmpName.compare(_o1.getName(), _o2.getName());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            }
        }
        return Numbers.compareLg(_o1.getIndex(), _o2.getIndex());
    }

}
