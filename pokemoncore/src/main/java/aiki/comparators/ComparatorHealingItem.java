package aiki.comparators;
import java.util.Comparator;

import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.Numbers;
import code.util.pagination.FieldComparator;
import code.util.pagination.FieldCustComparator;
import code.util.pagination.Pagination;
import aiki.util.SortingHealingItem;

public class ComparatorHealingItem implements Comparator<SortingHealingItem> {

    private FieldComparator<String> cmpName = new FieldComparator<String>();

    private FieldComparator<String> cmpDescription = new FieldComparator<String>();

    private FieldComparator<Integer> cmpPrice = new FieldComparator<Integer>();

    private FieldComparator<Integer> cmpNbHealedStatus = new FieldComparator<Integer>();

    private FieldComparator<Boolean> cmpRelativeRateHp = new FieldComparator<Boolean>();

    private FieldCustComparator<Rate> cmpHp = new FieldCustComparator<Rate>();

    private FieldCustComparator<Rate> cmpRateHp = new FieldCustComparator<Rate>();

    private FieldComparator<Boolean> cmpRelativeRatePp = new FieldComparator<Boolean>();

    private FieldCustComparator<Rate> cmpPp = new FieldCustComparator<Rate>();

    private FieldComparator<Boolean> cmpHealOneMove = new FieldComparator<Boolean>();

    private FieldComparator<Integer> cmpStatistics = new FieldComparator<Integer>();

    private FieldComparator<Boolean> cmpKo = new FieldComparator<Boolean>();

    private FieldCustComparator<LgInt> cmpNumber = new FieldCustComparator<LgInt>();

    private final int nbComparators;

    public ComparatorHealingItem() {
        nbComparators = 0;
    }

    public ComparatorHealingItem(FieldComparator<String> _cmpName,
            FieldComparator<String> _cmpDescription,
            FieldComparator<Integer> _cmpPrice,
            FieldComparator<Integer> _cmpNbHealedStatus,
            FieldComparator<Boolean> _cmpRelativeRateHp,
            FieldCustComparator<Rate> _cmpHp, FieldCustComparator<Rate> _cmpRateHp,
            FieldComparator<Boolean> _cmpRelativeRatePp,
            FieldCustComparator<Rate> _cmpPp,
            FieldComparator<Boolean> _cmpHealOneMove,
            FieldComparator<Integer> _cmpStatistics,
            FieldComparator<Boolean> _cmpKo, FieldCustComparator<LgInt> _cmpNumber,
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
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpName.getPriority() == i) {
                int res_ = cmpName.compare(_o1.getName(), _o2.getName());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpHealOneMove.getPriority() == i) {
                int res_ = cmpHealOneMove.compare(_o1.isHealOneMove(), _o2.isHealOneMove());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpHp.getPriority() == i) {
                int res_ = cmpHp.compare(_o1.getHp(), _o2.getHp());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpRateHp.getPriority() == i) {
                int res_ = cmpRateHp.compare(_o1.getHpRate(), _o2.getHpRate());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpKo.getPriority() == i) {
                int res_ = cmpKo.compare(_o1.isKo(), _o2.isKo());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpStatistics.getPriority() == i) {
                int res_ = cmpStatistics.compare(_o1.getNbStatistics(), _o2.getNbStatistics());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpNumber.getPriority() == i) {
                int res_ = cmpNumber.compare(_o1.getNumber(), _o2.getNumber());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpNbHealedStatus.getPriority() == i) {
                int res_ = cmpNbHealedStatus.compare(_o1.getNbHealedStatus(), _o2.getNbHealedStatus());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpPp.getPriority() == i) {
                int res_ = cmpPp.compare(_o1.getPp(), _o2.getPp());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpRelativeRateHp.getPriority() == i) {
                int res_ = cmpRelativeRateHp.compare(_o1.isRelativeRateHp(), _o2.isRelativeRateHp());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpRelativeRatePp.getPriority() == i) {
                int res_ = cmpRelativeRatePp.compare(_o1.isRelativeRatePp(), _o2.isRelativeRatePp());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpDescription.getPriority() == i) {
                int res_ = cmpDescription.compare(_o1.getItemClass(), _o2.getItemClass());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            }
        }
        return Numbers.compare(_o1.getIndex(), _o2.getIndex());
    }

}
