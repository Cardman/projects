package aiki.comparators;
import aiki.facade.*;
import aiki.util.SortingHealingItem;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class ComparatorHealingItem extends ComparatorCommonItem implements Comparing<SortingHealingItem> {

    private final LongFieldComparator cmpNbHealedStatus = new LongFieldComparator();

    private final BooleanFieldComparator cmpRelativeRateHp = new BooleanFieldComparator();

    private final RateFieldComparator cmpHp = new RateFieldComparator();

    private final RateFieldComparator cmpRateHp = new RateFieldComparator();

    private final BooleanFieldComparator cmpRelativeRatePp = new BooleanFieldComparator();

    private final RateFieldComparator cmpPp = new RateFieldComparator();

    private final BooleanFieldComparator cmpHealOneMove = new BooleanFieldComparator();

    private final LongFieldComparator cmpStatistics = new LongFieldComparator();

    private final BooleanFieldComparator cmpKo = new BooleanFieldComparator();

    @Override
    public int compare(SortingHealingItem _o1, SortingHealingItem _o2) {
        for (int i = PaginationHealingItem.NB_COMPARATORS; i >= Pagination.MIN_PRIORITY; i--) {
            int res_ = res(_o1,_o2,i);
            if (res_ != SortConstants.EQ_CMP) {
                return res_;
            }
        }
        return NumberUtil.compareLg(_o1.getIndex(), _o2.getIndex());
    }
    private int res(SortingHealingItem _o1, SortingHealingItem _o2, int _i) {
        if (getCmpPrice().getPriority() == _i) {
            return getCmpPrice().compare(_o1.getPrice(), _o2.getPrice());
        } else if (getCmpName().getPriority() == _i) {
            return getCmpName().compare(_o1.getName(), _o2.getName());
        } else if (cmpHealOneMove.getPriority() == _i) {
            return cmpHealOneMove.compare(_o1.isHealOneMove(), _o2.isHealOneMove());
        } else if (cmpHp.getPriority() == _i) {
            return cmpHp.compare(_o1.getHp(), _o2.getHp());
        } else if (cmpRateHp.getPriority() == _i) {
            return cmpRateHp.compare(_o1.getHpRate(), _o2.getHpRate());
        } else if (cmpKo.getPriority() == _i) {
            return cmpKo.compare(_o1.isKo(), _o2.isKo());
        } else if (cmpStatistics.getPriority() == _i) {
            return cmpStatistics.compare(_o1.getNbStatistics(), _o2.getNbStatistics());
        } else if (getCmpNumber().getPriority() == _i) {
            return getCmpNumber().compare(_o1.getNumber(), _o2.getNumber());
        } else if (cmpNbHealedStatus.getPriority() == _i) {
            return cmpNbHealedStatus.compare(_o1.getNbHealedStatus(), _o2.getNbHealedStatus());
        } else if (cmpPp.getPriority() == _i) {
            return cmpPp.compare(_o1.getPp(), _o2.getPp());
        } else if (cmpRelativeRateHp.getPriority() == _i) {
            return cmpRelativeRateHp.compare(_o1.isRelativeRateHp(), _o2.isRelativeRateHp());
        } else if (cmpRelativeRatePp.getPriority() == _i) {
            return cmpRelativeRatePp.compare(_o1.isRelativeRatePp(), _o2.isRelativeRatePp());
        } else if (getCmpDescription().getPriority() == _i) {
            return getCmpDescription().compare(_o1.getItemClass(), _o2.getItemClass());
        }
        return SortConstants.EQ_CMP;
    }

    public BooleanFieldComparator getCmpHealOneMove() {
        return cmpHealOneMove;
    }

    public BooleanFieldComparator getCmpKo() {
        return cmpKo;
    }

    public BooleanFieldComparator getCmpRelativeRateHp() {
        return cmpRelativeRateHp;
    }

    public BooleanFieldComparator getCmpRelativeRatePp() {
        return cmpRelativeRatePp;
    }

    public LongFieldComparator getCmpNbHealedStatus() {
        return cmpNbHealedStatus;
    }

    public LongFieldComparator getCmpStatistics() {
        return cmpStatistics;
    }

    public RateFieldComparator getCmpHp() {
        return cmpHp;
    }

    public RateFieldComparator getCmpPp() {
        return cmpPp;
    }

    public RateFieldComparator getCmpRateHp() {
        return cmpRateHp;
    }

}
