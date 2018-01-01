package code.maths.geo;
import code.util.CustList;
import code.util.EqList;
import code.util.SortableCustList;
import code.util.ints.Displayable;

public final class Edge implements Displayable {

    private static final String SEPARATOR = " ";

    private CustPoint first;

    private CustPoint second;

    public Edge(CustPoint _first, CustPoint _second) {
        first = _first;
        second = _second;
    }

    public boolean isSame(Edge _other) {
        if (first == _other.first) {
            if (second == _other.second) {
                return true;
            }
        }
        if (first == _other.second) {
            if (second == _other.first) {
                return true;
            }
        }
        return false;
    }

    public boolean isEqual(Edge _other) {
        if (first.eq(_other.first)) {
            if (second.eq(_other.second)) {
                return true;
            }
        }
        if (first.eq(_other.second)) {
            if (second.eq(_other.first)) {
                return true;
            }
        }
        return false;
    }

    public boolean intersectNotContains(Edge _other) {
        EqList<CustPoint> points_ = new EqList<CustPoint>();
        points_.add(first);
        points_.add(second);
        points_.add(_other.first);
        points_.add(_other.second);
        if (containsPoint(_other.second)) {
            return false;
        }
        if (_other.containsPoint(first)) {
            return false;
        }
        if (_other.containsPoint(second)) {
            return false;
        }
        if (containsPoint(_other.first)) {
            return false;
        }
        int index_ = CustList.FIRST_INDEX;
        for (CustPoint p: points_) {
            EqList<CustPoint> others_ = new EqList<CustPoint>();
            int next_;
            int nextOthOne_;
            int nextOthTwo_;
            if (index_ <= CustList.SECOND_INDEX) {
                if (index_ == CustList.FIRST_INDEX) {
                    next_ = CustList.SECOND_INDEX;
                } else {
                    next_ = CustList.FIRST_INDEX;
                }
                nextOthOne_ = CustList.SECOND_INDEX + CustList.ONE_ELEMENT;
                nextOthTwo_ = nextOthOne_ + CustList.ONE_ELEMENT;
            } else {
                if (index_ == CustList.SECOND_INDEX + CustList.ONE_ELEMENT) {
                    next_ = index_ + CustList.ONE_ELEMENT;
                } else {
                    next_ = index_ - CustList.ONE_ELEMENT;
                }
                nextOthOne_ = CustList.FIRST_INDEX;
                nextOthTwo_ = CustList.SECOND_INDEX;
            }
            CustPoint o_ = points_.get(next_);
            others_.add(points_.get(nextOthOne_));
            others_.add(points_.get(nextOthTwo_));
            SortableCustList<SitePoint> sites_ = new SortableCustList<SitePoint>();
            VectTwoDims v_ = new VectTwoDims(p, o_);
            for (CustPoint n: others_) {
                sites_.add(new SitePoint(n, p, v_));
            }
            sites_.sort();
            if (sites_.first().getNumber() >= SitePoint.QUAD_THREE) {
                return false;
            }
            if (sites_.last().getNumber() < SitePoint.QUAD_THREE) {
                return false;
            }
            index_ ++;
        }
        return true;
    }

    public boolean intersectNotContainsBound(Edge _other) {
        EqList<CustPoint> points_ = new EqList<CustPoint>();
        points_.add(first);
        points_.add(second);
        points_.add(_other.first);
        points_.add(_other.second);
        if (first.eq(_other.first) && !second.eq(_other.second)) {
            return false;
        }
        if (first.eq(_other.second) && !second.eq(_other.first)) {
            return false;
        }
        if (second.eq(_other.first) && !first.eq(_other.second)) {
            return false;
        }
        if (second.eq(_other.second) && !first.eq(_other.first)) {
            return false;
        }
        if (containsPoint(_other.second)) {
            return true;
        }
        if (_other.containsPoint(first)) {
            return true;
        }
        if (_other.containsPoint(second)) {
            return true;
        }
        if (containsPoint(_other.first)) {
            return true;
        }
        int index_ = CustList.FIRST_INDEX;
        for (CustPoint p: points_) {
            EqList<CustPoint> others_ = new EqList<CustPoint>();
            int next_;
            int nextOthOne_;
            int nextOthTwo_;
            if (index_ <= CustList.SECOND_INDEX) {
                if (index_ == CustList.FIRST_INDEX) {
                    next_ = CustList.SECOND_INDEX;
                } else {
                    next_ = CustList.FIRST_INDEX;
                }
                nextOthOne_ = CustList.SECOND_INDEX + CustList.ONE_ELEMENT;
                nextOthTwo_ = nextOthOne_ + CustList.ONE_ELEMENT;
            } else {
                if (index_ == CustList.SECOND_INDEX + CustList.ONE_ELEMENT) {
                    next_ = index_ + CustList.ONE_ELEMENT;
                } else {
                    next_ = index_ - CustList.ONE_ELEMENT;
                }
                nextOthOne_ = CustList.FIRST_INDEX;
                nextOthTwo_ = CustList.SECOND_INDEX;
            }
            CustPoint o_ = points_.get(next_);
            others_.add(points_.get(nextOthOne_));
            others_.add(points_.get(nextOthTwo_));
            SortableCustList<SitePoint> sites_ = new SortableCustList<SitePoint>();
            VectTwoDims v_ = new VectTwoDims(p, o_);
            for (CustPoint n: others_) {
                sites_.add(new SitePoint(n, p, v_));
            }
            sites_.sort();
            if (sites_.first().getNumber() >= SitePoint.QUAD_THREE) {
                return false;
            }
            if (sites_.last().getNumber() < SitePoint.QUAD_THREE) {
                return false;
            }
            index_ ++;
        }
        return true;
    }

    public boolean intersect(Edge _other) {
        EqList<CustPoint> points_ = new EqList<CustPoint>();
        points_.add(first);
        points_.add(second);
        points_.add(_other.first);
        points_.add(_other.second);
        if (containsPoint(_other.second)) {
            return true;
        }
        if (_other.containsPoint(first)) {
            return true;
        }
        if (_other.containsPoint(second)) {
            return true;
        }
        if (containsPoint(_other.first)) {
            return true;
        }
        int index_ = CustList.FIRST_INDEX;
        for (CustPoint p: points_) {
            EqList<CustPoint> others_ = new EqList<CustPoint>();
            int next_;
            int nextOthOne_;
            int nextOthTwo_;
            if (index_ <= CustList.SECOND_INDEX) {
                if (index_ == CustList.FIRST_INDEX) {
                    next_ = CustList.SECOND_INDEX;
                } else {
                    next_ = CustList.FIRST_INDEX;
                }
                nextOthOne_ = CustList.SECOND_INDEX + CustList.ONE_ELEMENT;
                nextOthTwo_ = nextOthOne_ + CustList.ONE_ELEMENT;
            } else {
                if (index_ == CustList.SECOND_INDEX + CustList.ONE_ELEMENT) {
                    next_ = index_ + CustList.ONE_ELEMENT;
                } else {
                    next_ = index_ - CustList.ONE_ELEMENT;
                }
                nextOthOne_ = CustList.FIRST_INDEX;
                nextOthTwo_ = CustList.SECOND_INDEX;
            }
            CustPoint o_ = points_.get(next_);
            others_.add(points_.get(nextOthOne_));
            others_.add(points_.get(nextOthTwo_));
            SortableCustList<SitePoint> sites_ = new SortableCustList<SitePoint>();
            VectTwoDims v_ = new VectTwoDims(p, o_);
            for (CustPoint n: others_) {
                sites_.add(new SitePoint(n, p, v_));
            }
            sites_.sort();
            if (sites_.first().getNumber() >= SitePoint.QUAD_THREE) {
                return false;
            }
            if (sites_.last().getNumber() < SitePoint.QUAD_THREE) {
                return false;
            }
            index_ ++;
        }
        return true;
    }

    public boolean containsPoint(CustPoint _c) {
        VectTwoDims one_ = new VectTwoDims(first, _c);
        VectTwoDims two_ = new VectTwoDims(second, _c);
        return one_.det(two_) == 0 && one_.scal(two_) <= 0;
    }

    @Override
    public String toString() {
        return display();
    }

    public CustPoint getFirst() {
        return first;
    }

    public void setFirst(CustPoint _first) {
        first = _first;
    }

    public CustPoint getSecond() {
        return second;
    }

    public void setSecond(CustPoint _second) {
        second = _second;
    }

    @Override
    public String display() {
        return first.display()+SEPARATOR+second.display();
    }
}
