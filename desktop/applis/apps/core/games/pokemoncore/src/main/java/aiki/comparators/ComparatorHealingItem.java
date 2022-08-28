package aiki.comparators;
import aiki.facade.*;
import aiki.util.SortingHealingItem;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class ComparatorHealingItem implements Comparing<SortingHealingItem> {

    public static final int NB_COMPARATORS = 13;

    private final StringFieldComparator cmpName;

    private final StringFieldComparator cmpDescription;

    private final LongFieldComparator cmpPrice;

    private final LongFieldComparator cmpNbHealedStatus;

    private final BooleanFieldComparator cmpRelativeRateHp;

    private final RateFieldComparator cmpHp;

    private final RateFieldComparator cmpRateHp;

    private final BooleanFieldComparator cmpRelativeRatePp;

    private final RateFieldComparator cmpPp;

    private final BooleanFieldComparator cmpHealOneMove;

    private final LongFieldComparator cmpStatistics;

    private final BooleanFieldComparator cmpKo;

    private final LgIntFieldComparator cmpNumber;

    private final int nbComparators;

    public ComparatorHealingItem(int _nb) {
        nbComparators = _nb;
        cmpName = new StringFieldComparator();
        cmpDescription = new StringFieldComparator();
        cmpPrice = new LongFieldComparator();
        cmpNbHealedStatus = new LongFieldComparator();
        cmpRelativeRateHp = new BooleanFieldComparator();
        cmpHp = new RateFieldComparator();
        cmpRateHp = new RateFieldComparator();
        cmpRelativeRatePp = new BooleanFieldComparator();
        cmpPp = new RateFieldComparator();
        cmpHealOneMove = new BooleanFieldComparator();
        cmpStatistics = new LongFieldComparator();
        cmpKo = new BooleanFieldComparator();
        cmpNumber = new LgIntFieldComparator();
    }

    public ComparatorHealingItem(int _nb, ComparatorHealingItem _other) {
        nbComparators = _nb;
        cmpName = _other.cmpName;
        cmpDescription = _other.cmpDescription;
        cmpPrice = _other.cmpPrice;
        cmpNbHealedStatus = _other.cmpNbHealedStatus;
        cmpRelativeRateHp = _other.cmpRelativeRateHp;
        cmpHp = _other.cmpHp;
        cmpRateHp = _other.cmpRateHp;
        cmpRelativeRatePp = _other.cmpRelativeRatePp;
        cmpPp = _other.cmpPp;
        cmpHealOneMove = _other.cmpHealOneMove;
        cmpStatistics = _other.cmpStatistics;
        cmpKo = _other.cmpKo;
        cmpNumber = _other.cmpNumber;
    }

    @Override
    public int compare(SortingHealingItem _o1, SortingHealingItem _o2) {
        for (int i = nbComparators; i >= Pagination.MIN_PRIORITY; i--) {
            int res_ = res(_o1,_o2,i);
            if (res_ != SortConstants.EQ_CMP) {
                return res_;
            }
        }
        return NumberUtil.compareLg(_o1.getIndex(), _o2.getIndex());
    }
    private int res(SortingHealingItem _o1, SortingHealingItem _o2, int _i) {
        if (cmpPrice.getPriority() == _i) {
            return cmpPrice.compare(_o1.getPrice(), _o2.getPrice());
        } else if (cmpName.getPriority() == _i) {
            return cmpName.compare(_o1.getName(), _o2.getName());
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
        } else if (cmpNumber.getPriority() == _i) {
            return cmpNumber.compare(_o1.getNumber(), _o2.getNumber());
        } else if (cmpNbHealedStatus.getPriority() == _i) {
            return cmpNbHealedStatus.compare(_o1.getNbHealedStatus(), _o2.getNbHealedStatus());
        } else if (cmpPp.getPriority() == _i) {
            return cmpPp.compare(_o1.getPp(), _o2.getPp());
        } else if (cmpRelativeRateHp.getPriority() == _i) {
            return cmpRelativeRateHp.compare(_o1.isRelativeRateHp(), _o2.isRelativeRateHp());
        } else if (cmpRelativeRatePp.getPriority() == _i) {
            return cmpRelativeRatePp.compare(_o1.isRelativeRatePp(), _o2.isRelativeRatePp());
        } else if (cmpDescription.getPriority() == _i) {
            return cmpDescription.compare(_o1.getItemClass(), _o2.getItemClass());
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

    public LgIntFieldComparator getCmpNumber() {
        return cmpNumber;
    }

    public LongFieldComparator getCmpNbHealedStatus() {
        return cmpNbHealedStatus;
    }

    public LongFieldComparator getCmpPrice() {
        return cmpPrice;
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

    public StringFieldComparator getCmpDescription() {
        return cmpDescription;
    }

    public StringFieldComparator getCmpName() {
        return cmpName;
    }
}
