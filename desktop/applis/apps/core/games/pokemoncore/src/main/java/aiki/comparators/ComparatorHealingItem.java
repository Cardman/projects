package aiki.comparators;
import aiki.facade.BooleanFieldComparator;
import aiki.facade.FieldCustComparator;
import aiki.facade.LongFieldComparator;
import aiki.facade.Pagination;
import aiki.facade.StringFieldComparator;
import aiki.util.SortingHealingItem;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class ComparatorHealingItem implements Comparing<SortingHealingItem> {

    private StringFieldComparator cmpName = new StringFieldComparator();

    private StringFieldComparator cmpDescription = new StringFieldComparator();

    private LongFieldComparator cmpPrice = new LongFieldComparator();

    private LongFieldComparator cmpNbHealedStatus = new LongFieldComparator();

    private BooleanFieldComparator cmpRelativeRateHp = new BooleanFieldComparator();

    private FieldCustComparator<Rate> cmpHp = new FieldCustComparator<Rate>();

    private FieldCustComparator<Rate> cmpRateHp = new FieldCustComparator<Rate>();

    private BooleanFieldComparator cmpRelativeRatePp = new BooleanFieldComparator();

    private FieldCustComparator<Rate> cmpPp = new FieldCustComparator<Rate>();

    private BooleanFieldComparator cmpHealOneMove = new BooleanFieldComparator();

    private LongFieldComparator cmpStatistics = new LongFieldComparator();

    private BooleanFieldComparator cmpKo = new BooleanFieldComparator();

    private FieldCustComparator<LgInt> cmpNumber = new FieldCustComparator<LgInt>();

    private final int nbComparators;

    public ComparatorHealingItem() {
        nbComparators = 0;
    }

    public ComparatorHealingItem(StringFieldComparator _cmpName,
            StringFieldComparator _cmpDescription,
            LongFieldComparator _cmpPrice,
            LongFieldComparator _cmpNbHealedStatus,
            BooleanFieldComparator _cmpRelativeRateHp,
            FieldCustComparator<Rate> _cmpHp, FieldCustComparator<Rate> _cmpRateHp,
            BooleanFieldComparator _cmpRelativeRatePp,
            FieldCustComparator<Rate> _cmpPp,
            BooleanFieldComparator _cmpHealOneMove,
            LongFieldComparator _cmpStatistics,
            BooleanFieldComparator _cmpKo, FieldCustComparator<LgInt> _cmpNumber,
            int _nbComparators) {
        cmpName = _cmpName;
        cmpDescription = _cmpDescription;
        cmpPrice = _cmpPrice;
        cmpNbHealedStatus = _cmpNbHealedStatus;
        cmpRelativeRateHp = _cmpRelativeRateHp;
        cmpHp = _cmpHp;
        cmpRateHp = _cmpRateHp;
        cmpRelativeRatePp = _cmpRelativeRatePp;
        cmpPp = _cmpPp;
        cmpHealOneMove = _cmpHealOneMove;
        cmpStatistics = _cmpStatistics;
        cmpKo = _cmpKo;
        cmpNumber = _cmpNumber;
        nbComparators = _nbComparators;
    }

    @Override
    public int compare(SortingHealingItem _o1, SortingHealingItem _o2) {
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
            } else if (cmpHealOneMove.getPriority() == i) {
                int res_ = cmpHealOneMove.compare(_o1.isHealOneMove(), _o2.isHealOneMove());
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            } else if (cmpHp.getPriority() == i) {
                int res_ = cmpHp.compare(_o1.getHp(), _o2.getHp());
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            } else if (cmpRateHp.getPriority() == i) {
                int res_ = cmpRateHp.compare(_o1.getHpRate(), _o2.getHpRate());
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            } else if (cmpKo.getPriority() == i) {
                int res_ = cmpKo.compare(_o1.isKo(), _o2.isKo());
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            } else if (cmpStatistics.getPriority() == i) {
                int res_ = cmpStatistics.compare(_o1.getNbStatistics(), _o2.getNbStatistics());
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            } else if (cmpNumber.getPriority() == i) {
                int res_ = cmpNumber.compare(_o1.getNumber(), _o2.getNumber());
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            } else if (cmpNbHealedStatus.getPriority() == i) {
                int res_ = cmpNbHealedStatus.compare(_o1.getNbHealedStatus(), _o2.getNbHealedStatus());
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            } else if (cmpPp.getPriority() == i) {
                int res_ = cmpPp.compare(_o1.getPp(), _o2.getPp());
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            } else if (cmpRelativeRateHp.getPriority() == i) {
                int res_ = cmpRelativeRateHp.compare(_o1.isRelativeRateHp(), _o2.isRelativeRateHp());
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            } else if (cmpRelativeRatePp.getPriority() == i) {
                int res_ = cmpRelativeRatePp.compare(_o1.isRelativeRatePp(), _o2.isRelativeRatePp());
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            } else if (cmpDescription.getPriority() == i) {
                int res_ = cmpDescription.compare(_o1.getItemClass(), _o2.getItemClass());
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            }
        }
        return NumberUtil.compareLg(_o1.getIndex(), _o2.getIndex());
    }

}
