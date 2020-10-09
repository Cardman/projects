package code.images;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class ComparatorIntPoint implements Comparing<IntPoint> {

    @Override
    public int compare(IntPoint _o1, IntPoint _o2) {
        int res_ = NumberUtil.compareLg(_o1.getXcoords(), _o2.getXcoords());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        return NumberUtil.compareLg(_o1.getYcoords(), _o2.getYcoords());
    }

}
