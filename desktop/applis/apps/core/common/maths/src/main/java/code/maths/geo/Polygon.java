package code.maths.geo;
import java.util.Iterator;

import code.util.CustList;
import code.util.EqList;
import code.util.core.IndexConstants;
import code.util.ints.Displayable;


public final class Polygon implements Iterable<CustPoint>, HasEdges, Displayable {
    private static final String SEPARATOR = ";";

    private EqList<CustPoint> points = new EqList<CustPoint>();

    public Polygon() {
    }

    public Polygon(Polygon _other) {
        points.addAllElts(_other.points);
    }

    public Polygon(Triangle _triangle) {
        add(_triangle.getFirstPoint());
        add(_triangle.getSecondPoint());
        add(_triangle.getThirdPoint());
    }

    public Polygon(Rect _rect) {
        add(new CustPoint(_rect.getLeft(), _rect.getTop()));
        add(new CustPoint(_rect.getLeft(), _rect.getBottom()));
        add(new CustPoint(_rect.getRight(), _rect.getBottom()));
        add(new CustPoint(_rect.getRight(), _rect.getTop()));
    }

    @Override
    public Iterator<CustPoint> iterator() {
        return points.iterator();
    }

    public boolean intersect(Polygon _other) {
        for (Edge e: getEdges()) {
            for (Edge f: _other.getEdges()) {
                if (e.intersect(f)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean intersectEdge(Edge _other) {
        for (Edge e: getEdges()) {
            if (e.intersect(_other)) {
                return true;
            }
        }
        return false;
    }

    public boolean intersectEdgeNotBound(Edge _other) {
        for (Edge e: getEdges()) {
            if (e.intersectNotContainsBound(_other)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CustList<Edge> getEdges() {
        if (isEmpty()) {
            return new CustList<Edge>();
        }
        CustList<Edge> l_ = new CustList<Edge>();
        int nbVertices_ = size();
        for (int i = IndexConstants.FIRST_INDEX; i < nbVertices_; i++) {
            l_.add(new Edge(get(i), get((i + 1)%nbVertices_)));
        }
        return l_;
    }

    @Override
    public CustList<CustPoint> getPoints() {
        return points;
    }

    public CustPoint first() {
        return points.first();
    }

    public CustPoint last() {
        return points.last();
    }

    public int size() {
        return points.size();
    }

    public CustPoint get(int _index) {
        return points.get(_index);
    }

    public void set(int _index, CustPoint _element) {
        points.set(_index, _element);
    }

    public void add(CustPoint _e) {
        points.add(_e);
    }

    public void add(int _i,CustPoint _e) {
        points.add(_i, _e);
    }

    public void remove(int _index) {
        points.remove(_index);
    }

    public void clear() {
        points.clear();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    @Override
    public String display() {
        if (points.isEmpty()) {
            return "";
        }
        StringBuilder return_ = new StringBuilder(points.first().display());
        int size_ = points.size();
        for (int i=1;i<size_;i++) {
            return_.append(SEPARATOR);
            return_.append(points.get(i).display());
        }
        return return_.toString();
    }

    /**
    jarvis(S)
   pointOnHull = leftmost point in S
   i = 0
   repeat
      P[i] = pointOnHull
      endpoint = S[0]      // initial endpoint for a candidate edge on the hull
      for j from 1 to |S|
         if (endpoint == pointOnHull) or (S[j] is on left of line from P[i] to endpoint)
            endpoint = S[j]   // found greater left turn, update endpoint
      i = i+1
      pointOnHull = endpoint
   until endpoint == P[0]      // wrapped around to first hull point
     */
    public Polygon getConvexHull() {
        Polygon p_ = new Polygon();
        if (isEmpty()) {
            return p_;
        }
        return getConvexHull(p_);
    }

    private Polygon getConvexHull(Polygon _p) {
        CustPoint cust_ = first();
        for (CustPoint p: getPoints()) {
            if (hasToRedef(cust_, p)) {
                cust_ = p;
            }
        }
        CustPoint endPoint_ = first();
        int nbVertices_ = size();
        while (_p.isEmpty() || endPoint_ != _p.first()) {
            _p.add(cust_);
            endPoint_ = first();
            for (int j = IndexConstants.SECOND_INDEX; j < nbVertices_; j++) {
                if (endPoint_ == cust_) {
                    endPoint_ = get(j);
                } else {
                    CustPoint b_ = _p.last();
                    VectTwoDims affineSegment_ = substract(b_, endPoint_);
                    VectTwoDims affinePoint_ = substract(get(j), endPoint_);
                    LinearDirection currentSide_ = getSide(affineSegment_, affinePoint_);
                    if (currentSide_ == LinearDirection.LEFT) {
                        endPoint_ = get(j);
                    }
                }
            }
            cust_ = endPoint_;
        }
        return _p;
    }

    private static boolean hasToRedef(CustPoint _cust, CustPoint _p) {
        return _p.getXcoords() < _cust.getXcoords() || (_p.getXcoords() == _cust.getXcoords() && _p.getYcoords() < _cust.getYcoords());
    }

    public Polygon getStrictHull() {
        Polygon convexHull_ = getConvexHull();
        if (convexHull_.isEmpty()) {
            return convexHull_;
        }
        Polygon cp_ = new Polygon();
        cp_.add(convexHull_.last());
        int len_ = convexHull_.size();
        for (int i = 0; i < len_; i++) {
            cp_.add(convexHull_.get(i));
        }
        cp_.add(convexHull_.first());
        Polygon h_ = new Polygon();
        for (int i = 1; i <= len_; i++) {
            CustPoint p_ = cp_.get(i-1);
            CustPoint c_ = cp_.get(i);
            CustPoint n_ = cp_.get(i+1);
            VectTwoDims affineSegment_ = substract(p_, c_);
            VectTwoDims affinePoint_ = substract(n_, c_);
            LinearDirection currentSide_ = getSide(affineSegment_, affinePoint_);
            if (currentSide_ != LinearDirection.NONE) {
                h_.add(c_);
            }
        }
        return h_;
    }

    public boolean containsObj(CustPoint _element) {
        return points.containsObj(_element);
    }

    public boolean containsInside(CustPoint _point) {
        if (size() < Rect.NB_POINTS) {
            return containsInsideConvexHull(_point);
        }
        CustList<Triangle> t_ = getTriangles();
        for (Triangle t: t_) {
            Polygon p_ = new Polygon(t);
            if (containsInside(p_, _point)) {
                return false;
            }
        }
        return containsInsideConvexHull(_point);
    }

    public CustList<Triangle> getTriangles() {
        if (size() < Rect.NB_POINTS) {
            return new CustList<Triangle>();
        }
        return getTriangles2();
    }

    public CustList<Triangle> getTriangles2() {
        Polygon copy_ = new Polygon();
        CustPoint cust_ = first();
        for (CustPoint p: points) {
            if (p.getXcoords() < cust_.getXcoords() || (p.getXcoords() == cust_.getXcoords() && p.getYcoords() < cust_.getYcoords())) {
                cust_ = p;
            }
        }
        int index_ = points.indexOfObj(cust_);
        int len_ = size();
        addPoints(copy_, index_, len_);
        CustList<Triangle> triangles_ = new CustList<Triangle>();
        while (!copy_.isConvex()) {
            int i_ = 1;
            while (true) {
                int locLen_ = copy_.size();
                if (i_ >= locLen_) {
                    break;
                }
                CustPoint before_ = copy_.get(i_ - 1);
                CustPoint curr_ = copy_.get(i_);
                CustPoint after_ = copy_.get((i_ + 1) % locLen_);
                VectTwoDims prev_ = new VectTwoDims(curr_, before_);
                VectTwoDims next_ = new VectTwoDims(curr_, after_);
                long det_ = prev_.det(next_);
                if (det_ <= 0) {
                    procRem(copy_, triangles_, i_, before_, curr_, after_, det_);
                } else {
                    i_++;
                }
            }
        }
        return triangles_;
    }
    private static void procRem(Polygon _copy, CustList<Triangle> _triangles, int _i, CustPoint _before, CustPoint _curr, CustPoint _after, long _det) {
        if (_det < 0) {
            Triangle t_;
            t_ = new Triangle(_before, _curr, _after);
            _triangles.add(t_);
        }
        _copy.remove(_i);
    }

    private void addPoints(Polygon _copy, int _index, int _len) {
        for (int i = _index; i < _len; i++) {
            _copy.add(get(i));
        }
        for (int i = IndexConstants.FIRST_INDEX; i < _index; i++) {
            _copy.add(get(i));
        }
    }

    public boolean isConvex() {
        if (size() < Rect.NB_POINTS) {
            return true;
        }
        int len_ = size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            CustPoint before_ = get(mod(i - 1, len_));
            CustPoint curr_ = get(i);
            CustPoint after_ = get((i + 1) % len_);
            VectTwoDims prev_ = new VectTwoDims(curr_, before_);
            VectTwoDims next_ = new VectTwoDims(curr_, after_);
            long det_ = prev_.det(next_);
            if (det_ <= 0) {
                return false;
            }
        }
        return true;
    }

    private static int mod(int _a, int _b) {
        int a_ = _a;
        while (a_ < 0) {
            a_ += _b;
        }
        return a_ % _b;
    }

    public boolean containsInsideConvexHull(CustPoint _point) {
        Polygon h_ = getConvexHull();
        if (h_.isEmpty()) {
            return false;
        }
        return containsInside(h_, _point);
    }

    static boolean containsInside(Polygon _h, CustPoint _point) {
        LinearDirection previousSide_ = LinearDirection.NONE;
        int nbVertices_ = _h.size();
        for (int n = 0; n < nbVertices_; n++) {
            CustPoint a_ = _h.get(n);
            CustPoint b_ = _h.get((n+1)%nbVertices_);
            VectTwoDims affineSegment_ = substract(b_, a_);
            VectTwoDims affinePoint_ = substract(_point, a_);
            LinearDirection currentSide_ = getSide(affineSegment_, affinePoint_);
            if (currentSide_ == LinearDirection.NONE) {
                return false;
            }
            if (previousSide_ == LinearDirection.NONE) {
                previousSide_ = currentSide_;
            } else {
                if (previousSide_ != currentSide_) {
                    return false;
                }
            }
        }
        return true;
    }

    static LinearDirection getSide(VectTwoDims _a,VectTwoDims _b){
        long x_ = _a.det(_b);
        if (x_ < 0) {
            return LinearDirection.LEFT;
        }
        if (x_ > 0) {
            return LinearDirection.RIGHT;
        }
        return LinearDirection.NONE;
    }

    static VectTwoDims substract(CustPoint _a,CustPoint _b){
        return new VectTwoDims(_b, _a);
    }

    public void setPoints(EqList<CustPoint> _points) {
        points = _points;
    }
//    static int det(VectTwoDims _a, VectTwoDims _b) {
//        return _a.getDeltax()*_b.getDeltay()-_a.getDeltay()*_b.getDeltax();
//    }
}
