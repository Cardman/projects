package aiki.comparators;
import aiki.facade.*;
import aiki.util.SortingItem;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class ComparatorItem extends ComparatorCommonItem implements Comparing<SortingItem> {

    @Override
    public int compare(SortingItem _o1, SortingItem _o2) {
        for (int i = PaginationItem.NB_CMPARATORS; i >= Pagination.MIN_PRIORITY; i--) {
            int res_ = res(_o1,_o2,i);
            if (res_ != SortConstants.EQ_CMP) {
                return res_;
            }
        }
        return NumberUtil.compareLg(_o1.getIndex(), _o2.getIndex());
    }
    private int res(SortingItem _o1, SortingItem _o2, int _i){
        if (getCmpPrice().getPriority() == _i) {
            return getCmpPrice().compare(_o1.getPrice(), _o2.getPrice());
        } else if (getCmpName().getPriority() == _i) {
            return getCmpName().compare(_o1.getName(), _o2.getName());
        } else if (getCmpDescription().getPriority() == _i) {
            return getCmpDescription().compare(_o1.getItemClass(), _o2.getItemClass());
        } else if (getCmpNumber().getPriority() == _i) {
            return getCmpNumber().compare(_o1.getNumber(), _o2.getNumber());
        }
        return SortConstants.EQ_CMP;
    }

}
