package code.images;
import code.util.CustList;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class ComparatorIntPoint implements Comparing<IntPoint> {

    @Override
    public int compare(IntPoint _o1, IntPoint _o2) {
        int res_ = Numbers.compare(_o1.getXcoords(), _o2.getXcoords());
        if (res_ != CustList.EQ_CMP) {
            return res_;
        }
        return Numbers.compare(_o1.getYcoords(), _o2.getYcoords());
    }

}
