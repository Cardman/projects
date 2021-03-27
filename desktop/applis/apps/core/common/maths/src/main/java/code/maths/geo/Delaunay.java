package code.maths.geo;
import code.maths.Rate;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdList;
import code.util.IdMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;

public final class Delaunay {

    private final IdList<Triangle> triangles = new IdList<Triangle>();

    private Polygon convexHull = new Polygon();

    private IdMap<RatePoint,CustList<Edge>> edges = new IdMap<RatePoint,CustList<Edge>>();

    private IdMap<RatePoint, IdList<RatePoint>> nextPoints = new IdMap<RatePoint, IdList<RatePoint>>();

    private IdMap<RatePoint,CustList<Triangle>> nextTriangles = new IdMap<RatePoint,CustList<Triangle>>();

    /**
    function BowyerWatson (pointList)
      // pointList is a set of coordinates defining the points to be triangulated
      triangulation := empty triangle mesh data structure
      add super-triangle to triangulation // must be large enough to completely contain all the points in pointList
      for each point in pointList do // add all the points one at a time to the triangulation
         badTriangles := empty set
         for each triangle in triangulation do // first find all the triangles that are no longer valid due to the insertion
            if point is inside circumcircle of triangle
               add triangle to badTriangles
         polygon := empty set
         for each triangle in badTriangles do // find the boundary of the polygonal hole
            for each edge in triangle do
               if edge is not shared by any other triangles in badTriangles
                  add edge to polygon
         for each triangle in badTriangles do // remove them from the data structure
            remove triangle from triangulation
         for each edge in polygon do // re-triangulate the polygonal hole
            newTri := form a triangle from edge to point
            add newTri to triangulation
      for each triangle in triangulation // done inserting points, now clean up
         if triangle contains a vertex from original super-triangle
            remove triangle from triangulation
      return triangulation*/

    public void compute(CustList<RatePoint> _points) {
        compute(_points, false);
    }

    public void compute(CustList<RatePoint> _points, boolean _addMids) {
        mainCompute(_points);
        if (_points.isEmpty()) {
            return;
        }
        if (triangles.isEmpty()) {
            return;
        }
        convexHull = convexHull.getConvexHull();
        nextPoints = calculateNextPoints();
        edges = evaluateEdges(_addMids);
        nextTriangles = calculateNextTriangles();
    }

    private void mainCompute(CustList<RatePoint> _points) {
        cleanBefore();
        if (_points.size() < Triangle.NB_POINTS) {
            return;
        }
        if (_points.size() == Triangle.NB_POINTS) {
            tryAddTriangle(_points.get(0), _points.get(1), _points.get(2));
            return;
        }
        Edge extendedEdge_ = getExtendedEdge(_points);
        Rate xMax_ = extendedEdge_.getSecond().getXcoords();
        Rate xMin_ = extendedEdge_.getFirst().getXcoords();
        Rate yMax_ =  extendedEdge_.getSecond().getYcoords();
        Rate yMin_ = extendedEdge_.getFirst().getYcoords();
        loopMain(_points, xMax_, xMin_, yMax_, yMin_);
    }

    private void loopMain(CustList<RatePoint> _points, Rate _xMax, Rate _xMin, Rate _yMax, Rate _yMin) {
        Triangle superTriangle_;
        RatePoint firstPoint_ = new RatePoint(_xMin, _yMin);
        RatePoint secondPoint_ = new RatePoint(Rate.minus(Rate.plus(_xMax, _xMax), _xMin), _yMin);
        RatePoint thirdPoint_ = new RatePoint(_xMin, Rate.minus(Rate.plus(_yMax, _yMax), _yMin));
        superTriangle_ = new Triangle(firstPoint_, secondPoint_, thirdPoint_);
        triangles.add(superTriangle_);
        for (RatePoint c: _points) {
            convexHull.add(c);
            CustList<Triangle> badTriangles_ = filterTriangles2(c);
            CustList<Edge> edges_ = getEdgesFromBadTriangles(badTriangles_);
            for (Triangle t: badTriangles_) {
                triangles.removeObj(t);
            }
            for (Edge e: edges_) {
                tryAddTriangle(e.getFirst(), e.getSecond(), c);
            }
        }
        CustList<Triangle> badTriangles_ = filterTriangles(superTriangle_);
        for (Triangle b: badTriangles_) {
            triangles.removeObj(b);
        }
    }

    private void tryAddTriangle(RatePoint _first, RatePoint _second, RatePoint _third) {
        VectTwoDims vOne_ = new VectTwoDims(_first, _second);
        VectTwoDims vTwo_ = new VectTwoDims(_first,_third);
        if (vOne_.det(vTwo_).isZero()) {
            return;
        }
        triangles.add(new Triangle(_first, _second, _third));
    }
    private CustList<Triangle> filterTriangles2(RatePoint _c) {
        CustList<Triangle> badTriangles_ = new CustList<Triangle>();
        for (Triangle t: triangles) {
            if (t.isInCircum(_c)) {
                badTriangles_.add(t);
            }
        }
        return badTriangles_;
    }

    private CustList<Triangle> filterTriangles(Triangle _superTriangle) {
        CustList<Triangle> badTriangles_ = new CustList<Triangle>();
        for (Triangle b: triangles) {
            boolean remove_ = hasToRemove(_superTriangle, b);
            if (remove_) {
                badTriangles_.add(b);
            }
        }
        return badTriangles_;
    }

    private static CustList<Edge> getEdgesFromBadTriangles(CustList<Triangle> _badTriangles) {
        CustList<Edge> edges_;
        edges_ = new CustList<Edge>();
        for (Triangle t: _badTriangles) {
            for (Edge e: t.getEdges()) {
                boolean addEdge_ = addEdge(_badTriangles, t, e);
                if (addEdge_) {
                    edges_.add(e);
                }
            }
        }
        return edges_;
    }

    private static boolean hasToRemove(Triangle _superTriangle, Triangle _b) {
        boolean remove_ = false;
        for (RatePoint s : _superTriangle.getPoints()) {
            for (RatePoint p : _b.getPoints()) {
                if (s == p) {
                    remove_ = true;
                    break;
                }
            }
            if (remove_) {
                break;
            }
        }
        return remove_;
    }

    private static boolean addEdge(CustList<Triangle> _badTriangles, Triangle _triangle,
                                   Edge _edge) {
        boolean addEdge_ = true;
        for (Triangle o: _badTriangles) {
            if (o != _triangle) {
                boolean break_ = foundSameEdge(_edge, o);
                if (break_) {
                    addEdge_ = false;
                    break;
                }
            }
        }
        return addEdge_;
    }

    private static boolean foundSameEdge(Edge _edge, Triangle _o) {
        boolean break_ = false;
        for (Edge e_ : _o.getEdges()) {
            if (_edge.isSame(e_)) {
                break_ = true;
                break;
            }
        }
        return break_;
    }

    public void mainComputeIncr(CustList<RatePoint> _points) {
        mainComputeIncr(_points, true);
    }

    public void mainComputeIncr(CustList<RatePoint> _points, boolean _addMids) {
        CustList<RatePoint> points_ = cleanBefore(_points);
        int index_ = getIndex2(points_);
        if (index_ >= points_.size()) {
            return;
        }
        RatePoint first_ = points_.first();
        RatePoint second_ = points_.get(1);
        RatePoint third_ = points_.get(index_);
        initHullTris(first_, second_, third_);
        points_.remove(index_);
        points_.remove(IndexConstants.SECOND_INDEX);
        points_.remove(IndexConstants.FIRST_INDEX);
        CustList<RatePoint> all_ = new CustList<RatePoint>();
        all_.add(first_);
        all_.add(second_);
        all_.add(third_);
        Polygon p_ = convexHull.getConvexHull();
        for (RatePoint c: points_) {
            p_ = loop(all_, p_, c);
        }
        convexHull = p_;
        nextPoints = calculateNextPoints();
        edges = evaluateEdges(_addMids);
        nextTriangles = calculateNextTriangles();
    }

    private void initHullTris(RatePoint _first, RatePoint _second, RatePoint _third) {
        triangles.add(new Triangle(_first, _second, _third));
        convexHull.add(_first);
        convexHull.add(_second);
        convexHull.add(_third);
    }

    private CustList<RatePoint> cleanBefore(CustList<RatePoint> _points) {
        cleanBefore();
        CustList<RatePoint> next_ = new CustList<RatePoint>();
        for (RatePoint p: _points) {
            if (!Polygon.containsObj(p,next_)) {
                next_.add(p);
            }
        }
        return next_;
    }

    private void cleanBefore() {
        triangles.clear();
        convexHull.clear();
        edges.clear();
        nextTriangles.clear();
        nextPoints.clear();
    }

    private Polygon loop(CustList<RatePoint> _all, Polygon _p, RatePoint _c) {
        Polygon p_ = _p;
        CustList<Triangle> del_ = new CustList<Triangle>();
        CustList<Triangle> nearlyDel_ = new CustList<Triangle>();
        fetchList(_c, del_, nearlyDel_);
        CustList<Edge> edges_ = new CustList<Edge>();
        if (del_.isEmpty()) {
            //&& !Polygon.containsInside(p_, c)
            CustList<Edge> hull_ = p_.getEdges();
            CustList<RatePoint> pts_ = getPts(_c, p_, hull_);
            edges_.add(new Edge(pts_.first(), pts_.get(1)));
            int lenPts_ = pts_.size() - 1;
            for (int i = IndexConstants.SECOND_INDEX; i < lenPts_; i++) {
                edges_.add(new Edge(pts_.get(i), pts_.get(i+1)));
            }
            removeDuplicates(edges_);
            for (Edge e: edges_) {
                Triangle toBeIns_ = new Triangle(e.getFirst(), e.getSecond(), _c);
                VectTwoDims vOne_ = new VectTwoDims(e.getFirst(), e.getSecond());
                VectTwoDims vTwo_ = new VectTwoDims(e.getFirst(), _c);
                addIfPlainTriangle(toBeIns_,vOne_,vTwo_);
            }
        } else {
            if (!Polygon.containsInside(p_, _c)) {
                return notInPolygon(_all, _c, p_, del_);
            }
            CustList<Edge> keepEdges_ = new CustList<Edge>();
            feedEdges(del_, nearlyDel_, edges_, keepEdges_);
            for (Edge e: keepEdges_) {
                Triangle toBeIns_ = new Triangle(e.getFirst(), e.getSecond(), _c);
                VectTwoDims vOne_ = new VectTwoDims(e.getFirst(), e.getSecond());
                VectTwoDims vTwo_ = new VectTwoDims(e.getFirst(), _c);
                addIfPlainTriangle(toBeIns_,vOne_,vTwo_);
            }
        }
        _all.add(_c);
        if (!Polygon.containsInside(p_, _c)) {
            convexHull.add(_c);
            p_ = convexHull.getConvexHull();
        }
        return p_;
    }

    private static CustList<RatePoint> getPts(RatePoint _c, Polygon _p, CustList<Edge> _hull) {
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        int len_ = _p.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            RatePoint c_ = _p.get(i);
            boolean contains_ = containsPt(_c, _hull, c_);
            if (!contains_) {
                pts_.add(c_);
            }
        }
        return pts_;
    }

    private Polygon notInPolygon(CustList<RatePoint> _all, RatePoint _c, Polygon _p, CustList<Triangle> _del) {
        Polygon p_ = _p;
        triangles.removeAllElements(_del);
        int indexPt_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        int len_ = p_.size();
        for (Triangle t: _del) {
            for (Edge e: t.getEdges()) {
                boolean added_ = isAdded(_c, t, e);
                if (added_) {
                    Triangle toBeIns_ = new Triangle(e.getFirst(), e.getSecond(), _c);
                    VectTwoDims vOne_ = new VectTwoDims(e.getFirst(), e.getSecond());
                    VectTwoDims vTwo_ = new VectTwoDims(e.getFirst(), _c);
                    addIfPlainTriangle(toBeIns_, vOne_, vTwo_);
                } else {
                    indexPt_ = addPoint(p_, _c, indexPt_, len_, e);
                }
            }
        }
        _all.add(_c);
        convexHull.add(_c);
        if (triangles.size() < Triangle.NB_POINTS) {
            p_ = convexHull.getConvexHull();
            return p_;
        }
        int befBef_ = NumberUtil.mod(indexPt_ - 1,len_);
        int bef_ = NumberUtil.mod(indexPt_,len_);
        int next_ = NumberUtil.mod(indexPt_+2,len_);
        int nextNext_ = NumberUtil.mod(indexPt_+Triangle.NB_POINTS,len_);
        Edge eOne_ = new Edge(p_.get(befBef_), _c);
        Edge eTwo_ = new Edge(p_.get(nextNext_), _c);
        addIfNotIntersect(p_, _c, bef_, befBef_, eOne_);
        addIfNotIntersect(p_, _c, next_, nextNext_, eTwo_);
        p_ = convexHull.getConvexHull();
        return p_;
    }

    private static boolean isAdded(RatePoint _c, Triangle _t, Edge _e) {
        boolean added_ = true;
        for (RatePoint q: _t.getPoints()) {
            if (_e.intersectNotContainsBound(new Edge(_c, q))) {
                added_ = false;
                break;
            }
        }
        return added_;
    }

    private void feedEdges(CustList<Triangle> _del, CustList<Triangle> _nearlyDel, CustList<Edge> _edges, CustList<Edge> _keepEdges) {
        for (Triangle t : _del) {
            _edges.addAllElts(t.getEdges());
        }
        for (Triangle t : _nearlyDel) {
            _edges.addAllElts(t.getEdges());
        }
        for (Edge e : _edges) {
            boolean same_ = false;
            for (Edge f : _edges) {
                if (e != f && e.isSame(f)) {
                    same_ = true;
                    break;
                }
            }
            if (!same_) {
                _keepEdges.add(e);
            }
        }
        _edges.clear();
        for (Triangle t : triangles) {
            _edges.addAllElts(t.getEdges());
        }
        removeDuplicates(_edges);
        triangles.removeAllElements(_del);
        triangles.removeAllElements(_nearlyDel);
    }

    private static boolean containsPt(RatePoint _c1, CustList<Edge> _hull, RatePoint _c2) {
        boolean contains_ = false;
        for (Edge e : _hull) {
            if (e.intersectNotContainsBound(new Edge(_c1, _c2))) {
                contains_ = true;
                break;
            }
        }
        return contains_;
    }

    static int addPoint(Polygon _polygon, RatePoint _pt, int _indexPt, int _len, Edge _e) {
        int indexPt_ = _indexPt;
        for (int i = IndexConstants.FIRST_INDEX; i < _len; i++) {
            Edge e_ = new Edge(_polygon.get(i),_polygon.get(NumberUtil.mod(i+1, _len)));
            if (e_.isSame(_e)) {
                indexPt_ = i;
                break;
            }
        }
        if (_len > 0) {
            _polygon.add((indexPt_ + 1) % _len, _pt);
        }
        return indexPt_;
    }

    void addIfNotIntersect(Polygon _p, RatePoint _c, int _next, int _nextNext, Edge _e) {
        if (!_p.intersectEdge(_e)) {
            Triangle toBeIns_ = new Triangle(_c, _p.get(_nextNext), _p.get(_next));
            VectTwoDims vOne_ = new VectTwoDims(_p.get(_nextNext), _p.get(_next));
            VectTwoDims vTwo_ = new VectTwoDims(_p.get(_nextNext), _c);
            addIfPlainTriangle(toBeIns_, vOne_, vTwo_);
        }
    }

    void addIfPlainTriangle(Triangle _toBeIns, VectTwoDims _vOne, VectTwoDims _vTwo) {
        if (!_vOne.det(_vTwo).isZero()) {
            triangles.add(_toBeIns);
        }
    }

    public void mainComputeIncrConvex(CustList<RatePoint> _points) {
        CustList<RatePoint> points_ = cleanBefore(_points);
        if (points_.size() < Triangle.NB_POINTS) {
            return;
        }
        for (RatePoint c: points_) {
            convexHull.add(c);
        }
        convexHull = convexHull.getConvexHull();
        RatePoint first_ = convexHull.first();
        farEdges(first_);
        CustList<RatePoint> visited_ = getVisited(points_);
        CustList<RatePoint> all_ = new CustList<RatePoint>(visited_);
        Polygon p_ = convexHull.getConvexHull();
        for (RatePoint c: points_) {
            CustList<Triangle> del_ = new CustList<Triangle>();
            CustList<Triangle> nearlyDel_ = new CustList<Triangle>();
            fetchList(c, del_, nearlyDel_);
            CustList<Edge> edges_ = new CustList<Edge>();

            CustList<Edge> keepEdges_ = new CustList<Edge>();
            feedEdges(del_, nearlyDel_, edges_, keepEdges_);
            for (Edge e: keepEdges_) {
                Triangle toBeIns_ = new Triangle(e.getFirst(), e.getSecond(), c);
                VectTwoDims vOne_ = new VectTwoDims(e.getFirst(), e.getSecond());
                VectTwoDims vTwo_ = new VectTwoDims(e.getFirst(), c);
                if (vOne_.det(vTwo_).isZero()) {
                    continue;
                }
                triangles.add(toBeIns_);
            }
            all_.add(c);
            if (!Polygon.containsInside(p_, c)) {
                convexHull.add(c);
                p_ = convexHull.getConvexHull();
            }
        }
        convexHull = p_;
        nextPoints = calculateNextPoints();
        edges = evaluateEdges(true);
        nextTriangles = calculateNextTriangles();
    }

    private CustList<RatePoint> getVisited(CustList<RatePoint> _points) {
        CustList<RatePoint> visited_ = new CustList<RatePoint>();
        for (RatePoint c: _points) {
            for (RatePoint p: convexHull) {
                if (c == p) {
                    visited_.add(c);
                    break;
                }
            }
        }
        return filter(_points, visited_);
    }

    private static CustList<RatePoint> filter(CustList<RatePoint> _points, CustList<RatePoint> _visited) {
        CustList<RatePoint> next_ = new CustList<RatePoint>();
        for (RatePoint c: _points) {
            if (!Polygon.containsObj(c, _visited)) {
                next_.add(c);
            }
        }
        _points.clear();
        _points.addAllElts(next_);
        return _visited;
    }

    private void farEdges(RatePoint _first) {
        CustList<Edge> farEdges_ = new CustList<Edge>();
        for (Edge e: convexHull.getEdges()) {
            if (e.getFirst() == _first || e.getSecond() == _first) {
                continue;
            }
            farEdges_.add(e);
        }
        for (Edge e: farEdges_) {
            triangles.add(new Triangle(_first, e.getFirst(), e.getSecond()));
        }
    }

    private void fetchList(RatePoint _c, CustList<Triangle> _del, CustList<Triangle> _nearlyDel) {
        for (Triangle t : triangles) {
            if (t.isInCircum(_c)) {
                _del.add(t);
            } else {
                if (t.isInCircumBorder(_c)) {
                    _nearlyDel.add(t);
                }
            }
        }
    }

    private static void removeDuplicates(CustList<Edge> _edges) {
        //setModified();
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < _edges.size()) {
            int j_ = i_;
            j_++;
            while (j_ < _edges.size()) {
                if (_edges.get(i_).isSame(_edges.get(j_))) {
                    _edges.remove(j_);
                } else {
                    j_++;
                }
            }
            i_++;
        }
    }

    public void mainComputeIncrSuperTriangle(CustList<RatePoint> _points) {
        CustList<RatePoint> points_ = cleanBefore(_points);
        int index_ = getIndex2(points_);
        if (index_ >= points_.size()) {
            return;
        }
        RatePoint first_ = points_.first();
        RatePoint second_ = points_.get(1);
        RatePoint third_ = points_.get(index_);
        initHullTris(first_, second_, third_);
        Edge extendedEdge_ = getExtendedEdge(points_);
        Rate xMax_ = extendedEdge_.getSecond().getXcoords();
        Rate xMin_ = extendedEdge_.getFirst().getXcoords();
        Rate yMax_ =  extendedEdge_.getSecond().getYcoords();
        Rate yMin_ = extendedEdge_.getFirst().getYcoords();
        RatePoint firstPoint_ = new RatePoint(xMin_, yMin_);
        RatePoint secondPoint_ = new RatePoint(Rate.minus(Rate.plus(xMax_, xMax_), xMin_), yMin_);
        RatePoint thirdPoint_ = new RatePoint(xMin_, Rate.minus(Rate.plus(yMax_, yMax_), yMin_));
        Triangle superTriangle_ = new Triangle(firstPoint_, secondPoint_, thirdPoint_);
        triangles.add(superTriangle_);
        points_.remove(index_);
        points_.remove(IndexConstants.SECOND_INDEX);
        points_.remove(IndexConstants.FIRST_INDEX);
        CustList<RatePoint> all_ = new CustList<RatePoint>();
        all_.add(first_);
        all_.add(second_);
        all_.add(third_);
        Polygon p_ = convexHull.getConvexHull();
        for (RatePoint c: points_) {
            CustList<Triangle> del_ = new CustList<Triangle>();
            CustList<Triangle> delStr_ = new CustList<Triangle>();
            CustList<Triangle> nearlyDel_ = new CustList<Triangle>();
            feedTriangles(superTriangle_, c, del_, delStr_, nearlyDel_);
            CustList<Edge> edges_ = new CustList<Edge>();
            if (delStr_.isEmpty() && !nearlyDel_.isEmpty()) {
                CustList<Edge> hull_ = p_.getEdges();
                Polygon pts_ = getCustPoints(p_, c, hull_);
                edges_ = pts_.getEdges();
                removeDuplicates(edges_);
                for (Edge e: edges_) {
                    Triangle toBeIns_ = new Triangle(e.getFirst(), e.getSecond(), c);
                    triangles.add(toBeIns_);
                }
                continue;
            }
            mergeEdges(superTriangle_, edges_);
            removeDuplicates(edges_);
            triangles.removeAllElements(del_);
            triangles.removeAllElements(nearlyDel_);
            //ADDED BEGIN
            triangles.add(superTriangle_);
            addTris(all_, c, edges_);
            all_.add(c);
            if (!Polygon.containsInside(p_, c)) {
                convexHull.add(c);
                p_ = convexHull.getConvexHull();
            }
        }
        afterLoop(superTriangle_, p_);
    }

    private static int getIndex2(CustList<RatePoint> _points) {
        if (_points.size() < Triangle.NB_POINTS) {
            return _points.size();
        }
        return getIndex(_points, _points.first(), new VectTwoDims(_points.first(), _points.get(1)));
    }

    private void mergeEdges(Triangle _superTriangle, CustList<Edge> _edges) {
        for (Triangle t: triangles) {
            if (_superTriangle == t) {
                continue;
            }
            _edges.addAllElts(t.getEdges());
        }
    }

    private void addTris(CustList<RatePoint> _all, RatePoint _c, CustList<Edge> _edges) {
        CustList<Edge> allAddedEdges_ = new CustList<Edge>();
        for (Edge e: _edges) {
            Triangle toBeIns_ = new Triangle(e.getFirst(), e.getSecond(), _c);
            VectTwoDims vOne_ = new VectTwoDims(e.getFirst(), e.getSecond());
            VectTwoDims vTwo_ = new VectTwoDims(e.getFirst(), _c);
            if (vOne_.det(vTwo_).isZero() || containsEdge(allAddedEdges_, e) || containsEdge2(_all, e, toBeIns_)) {
                continue;
            }
            allAddedEdges_.addAllElts(toBeIns_.getEdges());
            removeDuplicates(allAddedEdges_);
            triangles.add(toBeIns_);
        }
    }

    private void afterLoop(Triangle _superTriangle, Polygon _p) {
        CustList<Triangle> badTriangles_ = new CustList<Triangle>();
        CustList<Edge> allEdges_ = new CustList<Edge>();
        for (Triangle b: triangles) {
            if (b == _superTriangle) {
                continue;
            }
            allEdges_.addAllElts(b.getEdges());
        }
        for (Triangle b: triangles) {
            boolean remove_ = hasToRemove(_superTriangle, b);
            if (remove_) {
                badTriangles_.add(b);
            }
        }
        for (Triangle b: badTriangles_) {
            triangles.removeObj(b);
        }
        triangles.removeObj(_superTriangle);
        convexHull = _p;
        nextPoints = calculateNextPoints();
        edges = evaluateEdges(true);
        nextTriangles = calculateNextTriangles();
    }

    private static Edge getExtendedEdge(CustList<RatePoint> _points) {
        Rate xMax_ = _points.first().getXcoords();
        Rate xMin_ = _points.first().getXcoords();
        Rate yMax_ = _points.first().getYcoords();
        Rate yMin_ = _points.first().getYcoords();
        for (RatePoint c: _points) {
            if (Rate.strLower(c.getXcoords(), xMin_)) {
                xMin_ = c.getXcoords();
            }
            if (Rate.strGreater(c.getXcoords(), xMax_)) {
                xMax_ = c.getXcoords();
            }
            if (Rate.strLower(c.getYcoords(),yMin_)) {
                yMin_ = c.getYcoords();
            }
            if (Rate.strGreater(c.getYcoords(), yMax_)) {
                yMax_ = c.getYcoords();
            }
        }
        xMin_=Rate.minus(xMin_,Rate.one());
        yMin_=Rate.minus(yMin_,Rate.one());
        xMax_=Rate.plus(xMax_,Rate.one());
        yMax_=Rate.plus(yMax_,Rate.one());
        return new Edge(new RatePoint(xMin_,yMin_),new RatePoint(xMax_,yMax_));
    }
    private static Polygon getCustPoints(Polygon _p, RatePoint _c, CustList<Edge> _hull) {
        Polygon pts_ = new Polygon();
        int len_ = _p.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            RatePoint c_ = _p.get(i);
            boolean contains_ = containsPt(_c, _hull, c_);
            if (!contains_) {
                pts_.add(c_);
            }
        }
        return pts_;
    }

    private static boolean containsEdge2(CustList<RatePoint> _all, Edge _e, Triangle _toBeIns) {
        boolean contains_ = false;
        for (RatePoint o: _all) {
            if (o != _e.getFirst() && o != _e.getSecond() && _toBeIns.isInCircum(o)) {
                contains_ = true;
                break;
            }
        }
        return contains_;
    }

    private static boolean containsEdge(CustList<Edge> _allAddedEdges, Edge _e) {
        boolean contains_ = false;
        CustList<Edge> union_ = new CustList<Edge>(_allAddedEdges);
        union_.add(_e);
        for (Edge g: union_) {
            if (!_e.isSame(g) && _e.intersectNotContainsBound(g)) {
                contains_ = true;
                break;
            }
        }
        return contains_;
    }

    private void feedTriangles(Triangle _superTriangle, RatePoint _c, CustList<Triangle> _del, CustList<Triangle> _delStr, CustList<Triangle> _nearlyDel) {
        for (Triangle t: triangles) {
            if (t.isInCircum(_c)) {
                _del.add(t);
                if (t != _superTriangle) {
                    _delStr.add(t);
                }
            } else {
                if (t.isInCircumBorder(_c)) {
                    _nearlyDel.add(t);
                }
            }
        }
    }

    private static int getIndex(CustList<RatePoint> _points, RatePoint _first, VectTwoDims _v) {
        int index_ = Triangle.NB_POINTS - 1;
        while (index_ < _points.size()) {
            VectTwoDims vLoc_ = new VectTwoDims(_first, _points.get(index_));
            if (!_v.det(vLoc_).isZero()) {
                break;
            }
            index_++;
        }
        return index_;
    }

    public IdList<Triangle> getTriangles() {
        return triangles;
    }

    public Polygon getConvexHull() {
        return convexHull;
    }
    public IdMap<RatePoint,CustList<Edge>> getEdges() {
        return edges;
    }

    public IdMap<RatePoint, IdList<RatePoint>> getNextPoints() {
        return nextPoints;
    }

    public IdMap<RatePoint,CustList<Triangle>> getNextTriangles() {
        return nextTriangles;
    }

    private IdMap<RatePoint,CustList<Edge>> evaluateEdges(boolean _addMids) {
        if (_addMids) {
            return evaluateEdgesMids();
        }
        IdMap<RatePoint,CustList<Edge>> id_;
        id_ = new IdMap<RatePoint,CustList<Edge>>();
        for (EntryCust<RatePoint, IdList<RatePoint>> e: nextPoints.entryList()) {
            RatePoint k_ = e.getKey();
            IdList<RatePoint> v_ = e.getValue();
            Polygon p_ = new Polygon();
            fetchCircumCenters(k_, v_, p_);
            CustList<Edge> edges_ = new CustList<Edge>();
            int len_;
            len_ = p_.size();
            for (int i = 0; i < len_; i++) {
                RatePoint one_ = p_.get(i);
                RatePoint two_ = p_.get((i+1)%len_);
                Edge e_ = new Edge(one_, two_);
                edges_.add(e_);
            }
            id_.put(k_, edges_);
        }
        return id_;
    }

    private IdMap<RatePoint, CustList<Edge>> evaluateEdgesMids() {
        IdMap<RatePoint,CustList<Edge>> id_;
        id_ = new IdMap<RatePoint,CustList<Edge>>();
        for (EntryCust<RatePoint, IdList<RatePoint>> e: nextPoints.entryList()) {
            RatePoint k_ = e.getKey();
            IdList<RatePoint> v_ = e.getValue();
            Polygon p_ = new Polygon();
            boolean contained_ = isContainedHull(k_);
            Rate x_ = Rate.divide(Rate.plus(v_.first().getXcoords(), k_.getXcoords()), new Rate(2));
            Rate y_ = Rate.divide(Rate.plus(v_.first().getYcoords(), k_.getYcoords()), new Rate(2));
            RatePoint midOne_ = new RatePoint(x_, y_);
            if (contained_) {
                p_.add(midOne_);
            }
            fetchCircumCenters(k_, v_, p_);
            CustList<Edge> edges_ = new CustList<Edge>();
            x_ =Rate.divide(Rate.plus(v_.last().getXcoords(), k_.getXcoords()),new Rate(2));
            y_ =Rate.divide(Rate.plus(v_.last().getYcoords(), k_.getYcoords()),new Rate(2));
            RatePoint midTwo_ = new RatePoint(x_, y_);
            if (contained_) {
                p_.add(midTwo_);
            }
            int len_ = p_.size();
            for (int i = 0; i < len_; i++) {
                RatePoint one_ = p_.get(i);
                RatePoint two_ = p_.get((i+1)%len_);
                Edge e_ = new Edge(one_, two_);
                if (e_.isEqual(new Edge(midOne_, midTwo_))) {
                    continue;
                }
                edges_.add(e_);
            }
            id_.put(k_, edges_);
        }
        return id_;
    }

    private boolean isContainedHull(RatePoint _k) {
        boolean contained_ = false;
        for (RatePoint c : convexHull) {
            if (c == _k) {
                contained_ = true;
                break;
            }
        }
        return contained_;
    }

    private void fetchCircumCenters(RatePoint _k, IdList<RatePoint> _v, Polygon _p) {
        int len_ = _v.size();
        for (int i = 0; i < len_; i++) {
            RatePoint one_ = _v.get(i);
            RatePoint two_ = _v.get((i + 1) % len_);
            for (Triangle t : triangles) {
                int nbPoints_ = getNbPoints(_k, one_, two_, t);
                if (nbPoints_ == Triangle.NB_POINTS) {
                    RatePoint c_;
                    c_ = t.getCircumCenter();
                    _p.add(c_);
                    break;
                }
            }
        }
    }

    private static int getNbPoints(RatePoint _k, RatePoint _one, RatePoint _two, Triangle _t) {
        int nbPoints_ = IndexConstants.SIZE_EMPTY;
        for (RatePoint p : _t.getPoints()) {
            if (p == _k) {
                nbPoints_++;
            }
            if (p == _one) {
                nbPoints_++;
            }
            if (p == _two) {
                nbPoints_++;
            }
        }
        return nbPoints_;
    }

    private IdMap<RatePoint,CustList<Triangle>> calculateNextTriangles() {
        IdMap<RatePoint,CustList<Triangle>> id_;
        id_ = new IdMap<RatePoint,CustList<Triangle>>();
        for (EntryCust<RatePoint, IdList<RatePoint>> e: nextPoints.entryList()) {
            RatePoint k_ = e.getKey();
            CustList<Triangle> ts_ = getTs(k_, e.getValue());
            id_.put(k_, ts_);
        }
        return id_;
    }

    private CustList<Triangle> getTs(RatePoint _k, IdList<RatePoint> _nextPts) {
        int len_ = _nextPts.size();
        CustList<Triangle> ts_ = new CustList<Triangle>();
        for (int i = 0; i < len_; i++) {
            RatePoint one_ = _nextPts.get(i);
            RatePoint two_ = _nextPts.get((i + 1) % len_);
            for (Triangle t: triangles) {
                int nbPoints_ = getNbPoints(_k, one_, two_, t);
                if (nbPoints_ == Triangle.NB_POINTS) {
                    ts_.add(t);
                    break;
                }
            }
        }
        return ts_;
    }

    private IdMap<RatePoint, IdList<RatePoint>> calculateNextPoints() {
        IdMap<RatePoint, IdList<RatePoint>> id_;
        id_ = new IdMap<RatePoint, IdList<RatePoint>>();
        CustList<RatePoint> list_ = new CustList<RatePoint>();
        feedListPt(list_);
        for (RatePoint p: list_) {
            boolean contained_ = isContainedHull(p);
            IdMap<RatePoint,Integer> all_ = new IdMap<RatePoint,Integer>();
            feedMapping(p, all_);
            CustList<RatePoint> next_ = all_.getKeys();
            RatePoint first_ = next_.first();
            CustList<RatePoint> once_ = new CustList<RatePoint>();
            for (EntryCust<RatePoint, Integer> e: all_.entryList()) {
                if (!NumberUtil.eq(e.getValue(), IndexConstants.ONE_ELEMENT)) {
                    continue;
                }
                once_.add(e.getKey());
            }
            if (!once_.isEmpty() && contained_) {
                RatePoint eOne_ = once_.first();
                RatePoint eTwo_ = once_.last();
                VectTwoDims v_ = new VectTwoDims(p, eTwo_);
                SitePoint sOne_ = new SitePoint(eOne_, p, v_);
                v_ = new VectTwoDims(p, eOne_);
                SitePoint sTwo_ = new SitePoint(eTwo_, p, v_);
                if (SiteInfo.compare(sOne_.getInfo(),sTwo_.getInfo()) == SortConstants.NO_SWAP_SORT) {
                    first_ = eTwo_;
                } else {
                    first_ = eOne_;
                }
            }
            put(id_, p, next_, first_);
        }
        return id_;
    }

    private static void put(IdMap<RatePoint, IdList<RatePoint>> _id, RatePoint _p, CustList<RatePoint> _next, RatePoint _first) {
        CustList<Site> sites_ = new CustList<Site>();
        VectTwoDims v_ = new VectTwoDims(_p, _first);
        for (RatePoint n: _next) {
            sites_.add(new SitePoint(n, _p, v_));
        }
        sites_.sortElts(new SiteComparing());
        IdList<RatePoint> pts_ = new IdList<RatePoint>();
        for (Site s: sites_) {
            pts_.add(((SitePoint)s).getPoint());
        }
        _id.put(_p, pts_);
    }

    private void feedMapping(RatePoint _p, IdMap<RatePoint, Integer> _all) {
        for (Triangle t: triangles) {
            boolean found_ = isFoundPoint(_p, t.getPoints());
            if (!found_) {
                continue;
            }
            for (RatePoint n: t.getPoints()) {
                if (n == _p) {
                    continue;
                }
                if (_all.contains(n)) {
                    _all.put(n, _all.getVal(n) + 1);
                } else {
                    _all.put(n, 1);
                }
            }
        }
    }

    private void feedListPt(CustList<RatePoint> _list) {
        for (Triangle t: triangles) {
            for (RatePoint p: t.getPoints()) {
                boolean contained_ = isFoundPoint(p, _list);
                if (!contained_) {
                    _list.add(p);
                }
            }
        }
    }

    private static boolean isFoundPoint(RatePoint _p, CustList<RatePoint> _points) {
        boolean found_ = false;
        for (RatePoint n : _points) {
            if (n == _p) {
                found_ = true;
                break;
            }
        }
        return found_;
    }

}
