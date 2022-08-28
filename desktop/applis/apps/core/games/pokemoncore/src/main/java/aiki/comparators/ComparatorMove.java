package aiki.comparators;
import aiki.facade.EnumFieldComparator;
import aiki.facade.LongFieldComparator;
import aiki.facade.Pagination;
import aiki.facade.StringFieldComparator;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.SortingMove;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class ComparatorMove implements Comparing<SortingMove> {

    private StringFieldComparator cmpName = new StringFieldComparator();

    private LongFieldComparator cmpPrice = new LongFieldComparator();

    private LongFieldComparator cmpDescription = new LongFieldComparator();

    private LongFieldComparator cmpPpp = new LongFieldComparator();

    private LongFieldComparator cmpPrio = new LongFieldComparator();

    private EnumFieldComparator<TargetChoice> cmpTargetChoice = new EnumFieldComparator<TargetChoice>();

    private final int nbComparators;

    public ComparatorMove() {
        nbComparators = 0;
    }

    public ComparatorMove(StringFieldComparator _cmpName,
            LongFieldComparator _cmpPrice,
            LongFieldComparator _cmpDescription,
            LongFieldComparator _cmpPpp, LongFieldComparator _cmpPrio,
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
        } else if (cmpName.getPriority() == _i) {
            return cmpName.compare(_o1.getName(), _o2.getName());
        } else if (cmpDescription.getPriority() == _i) {
            return cmpDescription.compare(_o1.getMoveClass(), _o2.getMoveClass());
        }
        return SortConstants.EQ_CMP;
    }
}
