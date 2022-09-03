package aiki.comparators;
import aiki.facade.*;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.SortingMove;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class ComparatorMove extends ComparatorCommon implements Comparing<SortingMove> {

    private final LongFieldComparator cmpPrice = new LongFieldComparator();

    private final LongFieldComparator cmpDescription = new LongFieldComparator();

    private final LongFieldComparator cmpPpp = new LongFieldComparator();

    private final LongFieldComparator cmpPrio = new LongFieldComparator();

    private final EnumFieldComparator<TargetChoice> cmpTargetChoice = new EnumFieldComparator<TargetChoice>();

    @Override
    public int compare(SortingMove _o1, SortingMove _o2) {
        for (int i = PaginationMove.NB_CMPARATORS; i >= Pagination.MIN_PRIORITY; i--) {
            int res_ = res(_o1,_o2,i);
            if (res_ != SortConstants.EQ_CMP) {
                return res_;
            }
        }
        return NumberUtil.compareLg(_o1.getIndex(), _o2.getIndex());
    }

    private int res(SortingMove _o1, SortingMove _o2,int _i){
        if (cmpPrice.getPriority() == _i) {
            return cmpPrice.compare(_o1.getPrice(), _o2.getPrice());
        } else if (cmpPpp.getPriority() == _i) {
            return cmpPpp.compare(_o1.getPp(), _o2.getPp());
        } else if (cmpPrio.getPriority() == _i) {
            return cmpPrio.compare(_o1.getPriority(), _o2.getPriority());
        } else if (cmpTargetChoice.getPriority() == _i) {
            return cmpTargetChoice.compare(_o1.getTargetChoice(), _o2.getTargetChoice());
        } else if (getCmpName().getPriority() == _i) {
            return getCmpName().compare(_o1.getName(), _o2.getName());
        } else if (cmpDescription.getPriority() == _i) {
            return cmpDescription.compare(_o1.getMoveClass(), _o2.getMoveClass());
        }
        return SortConstants.EQ_CMP;
    }

    public LongFieldComparator getCmpPrice() {
        return cmpPrice;
    }

    public EnumFieldComparator<TargetChoice> getCmpTargetChoice() {
        return cmpTargetChoice;
    }

    public LongFieldComparator getCmpDescription() {
        return cmpDescription;
    }

    public LongFieldComparator getCmpPpp() {
        return cmpPpp;
    }

    public LongFieldComparator getCmpPrio() {
        return cmpPrio;
    }
}
