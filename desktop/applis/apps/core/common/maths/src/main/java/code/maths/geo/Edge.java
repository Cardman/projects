package code.maths.geo;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.ints.Displayable;

public final class Edge implements Displayable {

    private static final String SEPARATOR = " ";

    private RatePoint first;

    private RatePoint second;

    public Edge(RatePoint _first, RatePoint _second) {
        first = _first;
        second = _second;
    }

    public boolean isSame(Edge _other) {
        if (first == _other.first && second == _other.second) {
            return true;
        }
        if (first == _other.second) {
            return second == _other.first;
        }
        return false;
    }

    public boolean isEqual(Edge _other) {
        if (first.eq(_other.first) && second.eq(_other.second)) {
            return true;
        }
        if (first.eq(_other.second)) {
            return second.eq(_other.first);
        }
        return false;
    }

    public boolean intersectNotContains(Edge _other) {
        return intersectBoundsOpt(_other,false);
    }

    public boolean intersectNotContainsBound(Edge _other) {
        CustList<RatePoint> points_ = new CustList<RatePoint>();
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
        return procLines(_other, points_);
    }

    private boolean procLines(Edge _other, CustList<RatePoint> _points) {
        if (containsPoint(_other.second)) {
            return true;
        }
        if (containsPoint(_other.first)) {
            return true;
        }
        if (_other.containsPoint(first)) {
            return true;
        }
        if (_other.containsPoint(second)) {
            return true;
        }
        return lookForIntersectEdges(_points);
    }

    private static boolean lookForIntersectEdges(CustList<RatePoint> _points) {
        int index_ = IndexConstants.FIRST_INDEX;
        for (RatePoint p: _points) {
            CustList<RatePoint> others_ = new CustList<RatePoint>();
            int next_;
            int nextOthOne_;
            int nextOthTwo_;
            if (index_ <= IndexConstants.SECOND_INDEX) {
                next_ = getNext(index_);
                nextOthOne_ = IndexConstants.SECOND_INDEX + IndexConstants.ONE_ELEMENT;
                nextOthTwo_ = nextOthOne_ + IndexConstants.ONE_ELEMENT;
            } else {
                next_ = getNext2(index_);
                nextOthOne_ = IndexConstants.FIRST_INDEX;
                nextOthTwo_ = IndexConstants.SECOND_INDEX;
            }
            CustList<Site> sites_ = getSites(_points, p, others_, next_, nextOthOne_, nextOthTwo_);
            if (noIntersect(sites_)) {
                return false;
            }
            index_++;
        }
        return true;
    }

    static int getNext2(int _index) {
        int next_;
        if (_index == IndexConstants.SECOND_INDEX + IndexConstants.ONE_ELEMENT) {
            next_ = _index + IndexConstants.ONE_ELEMENT;
        } else {
            next_ = _index - IndexConstants.ONE_ELEMENT;
        }
        return next_;
    }

    static int getNext(int _index) {
        int next_;
        if (_index == IndexConstants.FIRST_INDEX) {
            next_ = IndexConstants.SECOND_INDEX;
        } else {
            next_ = IndexConstants.FIRST_INDEX;
        }
        return next_;
    }

    private static CustList<Site> getSites(CustList<RatePoint> _points, RatePoint _p, CustList<RatePoint> _others, int _next, int _nextOthOne, int _nextOthTwo) {
        RatePoint o_ = _points.get(_next);
        _others.add(_points.get(_nextOthOne));
        _others.add(_points.get(_nextOthTwo));
        CustList<Site> sites_ = new CustList<Site>();
        VectTwoDims v_ = new VectTwoDims(_p, o_);
        for (RatePoint n: _others) {
            sites_.add(new SitePoint(n, _p, v_));
        }
        sites_.sortElts(new SiteComparing());
        return sites_;
    }

    private static boolean noIntersect(CustList<Site> _sites) {
        return _sites.first().getInfo().getNumber() >= SiteInfo.QUAD_THREE || _sites.last().getInfo().getNumber() < SiteInfo.QUAD_THREE;
    }

    public boolean intersect(Edge _other) {
        return intersectBoundsOpt(_other,true);
    }

    private boolean intersectBoundsOpt(Edge _other, boolean _nonStrict) {
        CustList<RatePoint> points_ = new CustList<RatePoint>();
        points_.add(first);
        points_.add(second);
        points_.add(_other.first);
        points_.add(_other.second);
        if (containsPoint(_other.second)) {
            return _nonStrict;
        }
        if (_other.containsPoint(first)) {
            return _nonStrict;
        }
        if (_other.containsPoint(second)) {
            return _nonStrict;
        }
        if (containsPoint(_other.first)) {
            return _nonStrict;
        }
        return lookForIntersectEdges(points_);
    }

    public boolean containsPoint(RatePoint _c) {
        VectTwoDims one_ = new VectTwoDims(first, _c);
        VectTwoDims two_ = new VectTwoDims(second, _c);
        return one_.det(two_).isZero() && one_.scal(two_).isZeroOrLt();
    }

    public RatePoint getFirst() {
        return first;
    }

    public void setFirst(RatePoint _first) {
        first = _first;
    }

    public RatePoint getSecond() {
        return second;
    }

    public void setSecond(RatePoint _second) {
        second = _second;
    }

    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder(first.display());
        str_.append(SEPARATOR);
        str_.append(second.display());
        return str_.toString();
    }
}
