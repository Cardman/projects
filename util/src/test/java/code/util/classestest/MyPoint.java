package code.util.classestest;
import code.util.AbEqList;
import code.util.EqList;
import code.util.ints.Equallable;
import code.util.ints.Parent;
import code.util.ints.TreeFilter;

public final class MyPoint implements Parent<MyPoint>, TreeFilter<MyPoint>, Equallable<MyPoint> {

    private int xCoords;

    private int yCoords;

    public MyPoint() {
    }

    public MyPoint(int _x, int _y) {
        xCoords = _x;
        yCoords = _y;
    }

    @Override
    public EqList<MyPoint> getChildren(AbEqList<MyPoint> _visited, AbEqList<MyPoint> _all) {
        EqList<MyPoint> list_ = new EqList<MyPoint>();
        list_.add(new MyPoint(xCoords, yCoords+1));
        list_.add(new MyPoint(xCoords+1, yCoords));
        list_.add(new MyPoint(xCoords, yCoords-1));
        list_.add(new MyPoint(xCoords-1, yCoords));
        EqList<MyPoint> nextPoints_ = new EqList<MyPoint>();
        for (MyPoint p: list_) {
            if (_visited.containsObj(p)) {
                continue;
            }
            if (!_all.containsObj(p)) {
                continue;
            }
            nextPoints_.add(p);
        }
        return nextPoints_;
    }

    @Override
    public boolean accept(AbEqList<MyPoint> _list) {
        return xCoords >= 0 && yCoords >= 0 && xCoords <= 10 && yCoords <= 10;
    }

    @Override
    public boolean eq(MyPoint _obj) {
        if (xCoords != _obj.xCoords) {
            return false;
        }
        if (yCoords != _obj.yCoords) {
            return false;
        }
        return true;
    }

    public int getXcoords() {
        return xCoords;
    }

    public void setXcoords(int _x) {
        xCoords = _x;
    }

    public int getYcoords() {
        return yCoords;
    }

    public void setYcoords(int _y) {
        yCoords = _y;
    }
}
