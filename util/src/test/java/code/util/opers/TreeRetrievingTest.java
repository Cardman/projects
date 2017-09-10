package code.util.opers;
import static code.util.EquallableExUtil.assertEq;

import org.junit.Test;

import code.util.AbEqList;
import code.util.EqList;
import code.util.classestest.MyPoint;
import code.util.opers.TreeRetrieving;

@SuppressWarnings("static-method")
public class TreeRetrievingTest {

    @Test
    public void getRankedList_retrievePoint1Test() {
        MyPoint root_ = new MyPoint(5,5);
        EqList<MyPoint> all_ = getList(-10, -10, 19, 19);
        AbEqList<MyPoint> rankedList_ = TreeRetrieving.<MyPoint>getRankedList(root_, all_);
        assertEq(121, rankedList_.size());
    }

    @Test
    public void getRankedList_retrievePoint2Test() {
        MyPoint root_ = new MyPoint(5,5);
        EqList<MyPoint> all_ = getList(2, 2, 6, 6);
        AbEqList<MyPoint> rankedList_ = TreeRetrieving.<MyPoint>getRankedList(root_, all_);
        assertEq(25, rankedList_.size());
    }

    private static EqList<MyPoint> getList(int _minx, int _miny, int _maxx, int _maxy) {
        EqList<MyPoint> all_ = new EqList<MyPoint>();
        for (int i = _minx; i <= _maxx; i++) {
            for (int j = _miny; j <= _maxy; j++) {
                all_.add(new MyPoint(i, j));
            }
        }
        return all_;
    }
}
