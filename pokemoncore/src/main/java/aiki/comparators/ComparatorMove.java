package aiki.comparators;
import code.util.ints.Comparing;

import aiki.fight.moves.enums.TargetChoice;
import aiki.util.SortingMove;
import code.util.CustList;
import code.util.Numbers;
import code.util.pagination.EnumFieldComparator;
import code.util.pagination.FieldComparator;
import code.util.pagination.Pagination;

public final class ComparatorMove implements Comparing<SortingMove> {

    private FieldComparator<String> cmpName = new FieldComparator<String>();

    private FieldComparator<Integer> cmpPrice = new FieldComparator<Integer>();

    private FieldComparator<Integer> cmpDescription = new FieldComparator<Integer>();

    private FieldComparator<Short> cmpPpp = new FieldComparator<Short>();

    private FieldComparator<Byte> cmpPrio = new FieldComparator<Byte>();

    private EnumFieldComparator<TargetChoice> cmpTargetChoice = new EnumFieldComparator<TargetChoice>();

    private final int nbComparators;

    public ComparatorMove() {
        nbComparators = 0;
    }

    public ComparatorMove(FieldComparator<String> _cmpName,
            FieldComparator<Integer> _cmpPrice,
            FieldComparator<Integer> _cmpDescription,
            FieldComparator<Short> _cmpPpp, FieldComparator<Byte> _cmpPrio,
            EnumFieldComparator<TargetChoice> _cmpTargetChoice,
            int _nbComparators) {
        cmpName = _cmpName;
        cmpPrice = _cmpPrice;
        cmpDescription = _cmpDescription;
        cmpPpp = _cmpPpp;
        cmpPrio = _cmpPrio;
        cmpTargetChoice = _cmpTargetChoice;
        nbComparators = _nbComparators;
    }

    @Override
    public int compare(SortingMove _o1, SortingMove _o2) {
        for (int i = nbComparators; i >= Pagination.MIN_PRIORITY; i--) {
            if (cmpPrice.getPriority() == i) {
                int res_ = cmpPrice.compare(_o1.getPrice(), _o2.getPrice());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpPpp.getPriority() == i) {
                int res_ = cmpPpp.compare(_o1.getPp(), _o2.getPp());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpPrio.getPriority() == i) {
                int res_ = cmpPrio.compare(_o1.getPriority(), _o2.getPriority());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpTargetChoice.getPriority() == i) {
                int res_ = cmpTargetChoice.compare(_o1.getTargetChoice(), _o2.getTargetChoice());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpName.getPriority() == i) {
                int res_ = cmpName.compare(_o1.getName(), _o2.getName());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpDescription.getPriority() == i) {
                int res_ = cmpDescription.compare(_o1.getMoveClass(), _o2.getMoveClass());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            }
        }
        return Numbers.compare(_o1.getIndex(), _o2.getIndex());
    }

}
