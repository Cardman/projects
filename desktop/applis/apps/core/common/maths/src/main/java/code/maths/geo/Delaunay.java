package code.maths.geo;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdList;
import code.util.IdMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;

public final class Delaunay {

    private final IdList<Triangle> triangles = new IdList<Triangle>();

    private Polygon convexHull = new Polygon();

    private IdMap<CustPoint,CustList<Edge>> edges = new IdMap<CustPoint,CustList<Edge>>();

    private IdMap<CustPoint, IdList<CustPoint>> nextPoints = new IdMap<CustPoint, IdList<CustPoint>>();

    private IdMap<CustPoint,CustList<Triangle>> nextTriangles = new IdMap<CustPoint,CustList<Triangle>>();

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

    public void compute(EqList<CustPoint> _points) {
        compute(_points, false);
    }

    public void compute(EqList<CustPoint> _points, boolean _addMids) {
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

    private void mainCompute(EqList<CustPoint> _points) {
        triangles.clear();
        convexHull.clear();
        edges.clear();
        nextTriangles.clear();
        nextPoints.clear();
        if (_points.size() < Triangle.NB_POINTS) {
            return;
        }
        if (_points.size() == Triangle.NB_POINTS) {
            VectTwoDims vOne_ = new VectTwoDims(_points.get(0), _points.get(1));
            VectTwoDims vTwo_ = new VectTwoDims(_points.get(0), _points.get(2));
            if (vOne_.det(vTwo_) == 0) {
                return;
            }
            triangles.add(new Triangle(_points.get(0), _points.get(1), _points.get(2)));
            return;
        }
        Edge extendedEdge_ = getExtendedEdge(_points);
        int xMax_ = extendedEdge_.getSecond().getXcoords();
        int xMin_ = extendedEdge_.getFirst().getXcoords();
        int yMax_ =  extendedEdge_.getSecond().getYcoords();
        int yMin_ = extendedEdge_.getFirst().getYcoords();
        loopMain(_points, xMax_, xMin_, yMax_, yMin_);
    }

    private void loopMain(EqList<CustPoint> _points, int _xMax, int _xMin, int _yMax, int _yMin) {
        Triangle superTriangle_;
        CustPoint firstPoint_ = new CustPoint(_xMin, _yMin);
        CustPoint secondPoint_ = new CustPoint(_xMax + _xMax - _xMin, _yMin);
        CustPoint thirdPoint_ = new CustPoint(_xMin, _yMax + _yMax - _yMin);
        superTriangle_ = new Triangle(firstPoint_, secondPoint_, thirdPoint_);
        triangles.add(superTriangle_);
        for (CustPoint c: _points) {
            convexHull.add(c);
            CustList<Triangle> badTriangles_ = filterTriangles2(c);
            CustList<Edge> edges_ = getEdgesFromBadTriangles(badTriangles_);
            for (Triangle t: badTriangles_) {
                triangles.removeObj(t);
            }
            for (Edge e: edges_) {
                triangles.add(new Triangle(e.getFirst(), e.getSecond(), c));
            }
        }
        CustList<Triangle> badTriangles_ = filterTriangles(superTriangle_);
        for (Triangle b: badTriangles_) {
            triangles.removeObj(b);
        }
    }

    private CustList<Triangle> filterTriangles2(CustPoint _c) {
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
        for (CustPoint s : _superTriangle.getPoints()) {
            for (CustPoint p : _b.getPoints()) {
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

    public void mainComputeIncr(EqList<CustPoint> _points) {
        mainComputeIncr(_points, true);
    }

    public void mainComputeIncr(EqList<CustPoint> _points, boolean _addMids) {
        triangles.clear();
        convexHull.clear();
        edges.clear();
        nextTriangles.clear();
        nextPoints.clear();
        EqList<CustPoint> points_ = new EqList<CustPoint>(_points);
        points_.removeDuplicates();
        if (points_.size() < Triangle.NB_POINTS) {
            return;
        }
        CustPoint first_ = points_.first();
        CustPoint second_ = points_.get(1);
        VectTwoDims v_ = new VectTwoDims(first_,second_);
        int index_ = getIndex(points_, first_, v_);
        if (index_ >= points_.size()) {
            return;
        }
        CustPoint third_ = points_.get(index_);
        triangles.add(new Triangle(first_, second_, third_));
        convexHull.add(first_);
        convexHull.add(second_);
        convexHull.add(third_);
        points_.remove(index_);
        points_.remove(IndexConstants.SECOND_INDEX);
        points_.remove(IndexConstants.FIRST_INDEX);
        EqList<CustPoint> all_ = new EqList<CustPoint>();
        all_.add(first_);
        all_.add(second_);
        all_.add(third_);
        Polygon p_ = convexHull.getConvexHull();
        for (CustPoint c: points_) {
            p_ = loop(all_, p_, c);
        }
        convexHull = p_;
        nextPoints = calculateNextPoints();
        edges = evaluateEdges(_addMids);
        nextTriangles = calculateNextTriangles();
    }

    private Polygon loop(EqList<CustPoint> _all, Polygon _p, CustPoint _c) {
        Polygon p_ = _p;
        CustList<Triangle> del_ = new CustList<Triangle>();
        CustList<Triangle> nearlyDel_ = new CustList<Triangle>();
        fetchList(_c, del_, nearlyDel_);
        CustList<Edge> edges_ = new CustList<Edge>();
        if (del_.isEmpty()) {
            //&& !Polygon.containsInside(p_, c)
            CustList<Edge> hull_ = p_.getEdges();
            EqList<CustPoint> pts_ = getPts(_c, p_, hull_);
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

    private static EqList<CustPoint> getPts(CustPoint _c, Polygon _p, CustList<Edge> _hull) {
        EqList<CustPoint> pts_ = new EqList<CustPoint>();
        int len_ = _p.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            CustPoint c_ = _p.get(i);
            boolean contains_ = containsPt(_c, _hull, c_);
            if (!contains_) {
                pts_.add(c_);
            }
        }
        return pts_;
    }

    private Polygon notInPolygon(EqList<CustPoint> _all, CustPoint _c, Polygon _p, CustList<Triangle> _del) {
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

    private static boolean isAdded(CustPoint _c, Triangle _t, Edge _e) {
        boolean added_ = true;
        for (CustPoint q: _t.getPoints()) {
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
                if (e == f) {
                    continue;
                }
                if (e.isSame(f)) {
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

    private static boolean containsPt(CustPoint _c1, CustList<Edge> _hull, CustPoint _c2) {
        boolean contains_ = false;
        for (Edge e : _hull) {
            if (e.intersectNotContainsBound(new Edge(_c1, _c2))) {
                contains_ = true;
                break;
            }
        }
        return contains_;
    }

    static int addPoint(Polygon _polygon, CustPoint _pt, int _indexPt, int _len, Edge _e) {
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

    void addIfNotIntersect(Polygon _p, CustPoint _c, int _next, int _nextNext, Edge _e) {
        if (!_p.intersectEdge(_e)) {
            Triangle toBeIns_ = new Triangle(_c, _p.get(_nextNext), _p.get(_next));
            VectTwoDims vOne_ = new VectTwoDims(_p.get(_nextNext), _p.get(_next));
            VectTwoDims vTwo_ = new VectTwoDims(_p.get(_nextNext), _c);
            addIfPlainTriangle(toBeIns_, vOne_, vTwo_);
        }
    }

    void addIfPlainTriangle(Triangle _toBeIns, VectTwoDims _vOne, VectTwoDims _vTwo) {
        if (_vOne.det(_vTwo) != 0) {
            triangles.add(_toBeIns);
        }
    }

    public void mainComputeIncrConvex(EqList<CustPoint> _points) {
        triangles.clear();
        convexHull.clear();
        edges.clear();
        nextTriangles.clear();
        nextPoints.clear();
        EqList<CustPoint> points_ = new EqList<CustPoint>(_points);
        points_.removeDuplicates();
        if (points_.size() < Triangle.NB_POINTS) {
            return;
        }
        for (CustPoint c: points_) {
            convexHull.add(c);
        }
        convexHull = convexHull.getConvexHull();
        CustPoint first_ = convexHull.first();
        farEdges(first_);
        EqList<CustPoint> visited_ = getVisited(points_);
        for (CustPoint c: visited_) {
            points_.removeObj(c);
        }
        EqList<CustPoint> all_ = new EqList<CustPoint>(visited_);
        Polygon p_ = convexHull.getConvexHull();
        for (CustPoint c: points_) {
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
                if (vOne_.det(vTwo_) == 0) {
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

    private EqList<CustPoint> getVisited(EqList<CustPoint> _points) {
        EqList<CustPoint> visited_ = new EqList<CustPoint>();
        for (CustPoint c: _points) {
            for (CustPoint p: convexHull) {
                if (c == p) {
                    visited_.add(c);
                    break;
                }
            }
        }
        return visited_;
    }

    private void farEdges(CustPoint _first) {
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

    private void fetchList(CustPoint _c, CustList<Triangle> _del, CustList<Triangle> _nearlyDel) {
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

    public void mainComputeIncrSuperTriangle(EqList<CustPoint> _points) {
        triangles.clear();
        convexHull.clear();
        edges.clear();
        nextTriangles.clear();
        nextPoints.clear();
        EqList<CustPoint> points_ = new EqList<CustPoint>(_points);
        points_.removeDuplicates();
        if (points_.size() < Triangle.NB_POINTS) {
            return;
        }
        CustPoint first_ = points_.first();
        CustPoint second_ = points_.get(1);
        VectTwoDims v_ = new VectTwoDims(first_,second_);
        int index_ = getIndex(points_, first_, v_);
        if (index_ >= points_.size()) {
            return;
        }
        CustPoint third_ = points_.get(index_);
        triangles.add(new Triangle(first_, second_, third_));
        convexHull.add(first_);
        convexHull.add(second_);
        convexHull.add(third_);
        Edge extendedEdge_ = getExtendedEdge(points_);
        int xMax_ = extendedEdge_.getSecond().getXcoords();
        int xMin_ = extendedEdge_.getFirst().getXcoords();
        int yMax_ =  extendedEdge_.getSecond().getYcoords();
        int yMin_ = extendedEdge_.getFirst().getYcoords();
        CustPoint firstPoint_ = new CustPoint(xMin_, yMin_);
        CustPoint secondPoint_ = new CustPoint(xMax_ + xMax_ - xMin_, yMin_);
        CustPoint thirdPoint_ = new CustPoint(xMin_, yMax_ + yMax_ - yMin_);
        Triangle superTriangle_ = new Triangle(firstPoint_, secondPoint_, thirdPoint_);
        triangles.add(superTriangle_);
        points_.remove(index_);
        points_.remove(IndexConstants.SECOND_INDEX);
        points_.remove(IndexConstants.FIRST_INDEX);
        EqList<CustPoint> all_ = new EqList<CustPoint>();
        all_.add(first_);
        all_.add(second_);
        all_.add(third_);
        Polygon p_ = convexHull.getConvexHull();
        for (CustPoint c: points_) {
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

    private void mergeEdges(Triangle _superTriangle, CustList<Edge> _edges) {
        for (Triangle t: triangles) {
            if (_superTriangle == t) {
                continue;
            }
            _edges.addAllElts(t.getEdges());
        }
    }

    private void addTris(EqList<CustPoint> _all, CustPoint _c, CustList<Edge> _edges) {
        CustList<Edge> allAddedEdges_ = new CustList<Edge>();
        for (Edge e: _edges) {
            Triangle toBeIns_ = new Triangle(e.getFirst(), e.getSecond(), _c);
            VectTwoDims vOne_ = new VectTwoDims(e.getFirst(), e.getSecond());
            VectTwoDims vTwo_ = new VectTwoDims(e.getFirst(), _c);
            if (vOne_.det(vTwo_) == 0 || containsEdge(allAddedEdges_, e) || containsEdge2(_all, e, toBeIns_)) {
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

    private static Edge getExtendedEdge(CustList<CustPoint> _points) {
        int xMax_ = _points.first().getXcoords();
        int xMin_ = _points.first().getXcoords();
        int yMax_ = _points.first().getYcoords();
        int yMin_ = _points.first().getYcoords();
        for (CustPoint c: _points) {
            if (c.getXcoords() < xMin_) {
                xMin_ = c.getXcoords();
            }
            if (c.getXcoords() > xMax_) {
                xMax_ = c.getXcoords();
            }
            if (c.getYcoords() < yMin_) {
                yMin_ = c.getYcoords();
            }
            if (c.getYcoords() > yMax_) {
                yMax_ = c.getYcoords();
            }
        }
        xMin_--;
        yMin_--;
        xMax_++;
        yMax_++;
        return new Edge(new CustPoint(xMin_,yMin_),new CustPoint(xMax_,yMax_));
    }
    private static Polygon getCustPoints(Polygon _p, CustPoint _c, CustList<Edge> _hull) {
        Polygon pts_ = new Polygon();
        int len_ = _p.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            CustPoint c_ = _p.get(i);
            boolean contains_ = containsPt(_c, _hull, c_);
            if (!contains_) {
                pts_.add(c_);
            }
        }
        return pts_;
    }

    private static boolean containsEdge2(EqList<CustPoint> _all, Edge _e, Triangle _toBeIns) {
        boolean contains_ = false;
        for (CustPoint o: _all) {
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

    private void feedTriangles(Triangle _superTriangle, CustPoint _c, CustList<Triangle> _del, CustList<Triangle> _delStr, CustList<Triangle> _nearlyDel) {
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

    private static int getIndex(EqList<CustPoint> _points, CustPoint _first, VectTwoDims _v) {
        int index_ = Triangle.NB_POINTS - 1;
        while (index_ < _points.size()) {
            VectTwoDims vLoc_ = new VectTwoDims(_first, _points.get(index_));
            if (_v.det(vLoc_) != 0) {
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
    public IdMap<CustPoint,CustList<Edge>> getEdges() {
        return edges;
    }

    public IdMap<CustPoint, IdList<CustPoint>> getNextPoints() {
        return nextPoints;
    }

    public IdMap<CustPoint,CustList<Triangle>> getNextTriangles() {
        return nextTriangles;
    }

    private IdMap<CustPoint,CustList<Edge>> evaluateEdges(boolean _addMids) {
        if (_addMids) {
            return evaluateEdgesMids();
        }
        IdMap<CustPoint,CustList<Edge>> id_;
        id_ = new IdMap<CustPoint,CustList<Edge>>();
        for (EntryCust<CustPoint, IdList<CustPoint>> e: nextPoints.entryList()) {
            CustPoint k_ = e.getKey();
            IdList<CustPoint> v_ = e.getValue();
            Polygon p_ = new Polygon();
            CustList<Edge> edges_ = getEdges(k_, v_, p_);
            int len_;
            len_ = p_.size();
            for (int i = 0; i < len_; i++) {
                CustPoint one_ = p_.get(i);
                CustPoint two_ = p_.get((i+1)%len_);
                Edge e_ = new Edge(one_, two_);
                edges_.add(e_);
            }
            id_.put(k_, edges_);
        }
        return id_;
    }

    private IdMap<CustPoint, CustList<Edge>> evaluateEdgesMids() {
        IdMap<CustPoint,CustList<Edge>> id_;
        id_ = new IdMap<CustPoint,CustList<Edge>>();
        for (EntryCust<CustPoint, IdList<CustPoint>> e: nextPoints.entryList()) {
            CustPoint k_ = e.getKey();
            IdList<CustPoint> v_ = e.getValue();
            Polygon p_ = new Polygon();
            boolean contained_ = isContainedHull(k_);
            int x_;
            int y_;
            x_ = (v_.first().getXcoords() + k_.getXcoords()) / 2;
            y_ = (v_.first().getYcoords() + k_.getYcoords()) / 2;
            CustPoint midOne_ = new CustPoint(x_, y_);
            if (contained_) {
                p_.add(midOne_);
            }
            CustList<Edge> edges_ = getEdges(k_, v_, p_);
            int len_;
            x_ = (v_.last().getXcoords() + k_.getXcoords()) / 2;
            y_ = (v_.last().getYcoords() + k_.getYcoords()) / 2;
            CustPoint midTwo_ = new CustPoint(x_, y_);
            if (contained_) {
                p_.add(midTwo_);
            }
            len_ = p_.size();
            for (int i = 0; i < len_; i++) {
                CustPoint one_ = p_.get(i);
                CustPoint two_ = p_.get((i+1)%len_);
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

    private boolean isContainedHull(CustPoint _k) {
        boolean contained_ = false;
        for (CustPoint c : convexHull) {
            if (c == _k) {
                contained_ = true;
                break;
            }
        }
        return contained_;
    }

    private CustList<Edge> getEdges(CustPoint _k, IdList<CustPoint> _v, Polygon _p) {
        int x_;
        int y_;
        CustList<Edge> edges_ = new CustList<Edge>();
        int len_ = _v.size();
        for (int i = 0; i < len_; i++) {
            CustPoint one_ = _v.get(i);
            CustPoint two_ = _v.get((i + 1) % len_);
            for (Triangle t : triangles) {
                int nbPoints_ = getNbPoints(_k, one_, two_, t);
                if (nbPoints_ == Triangle.NB_POINTS) {
                    CompactPlanePoint c_;
                    c_ = t.getCircumCenter();
                    x_ = (int) (c_.getXcoords() / c_.getCommon());
                    y_ = (int) (c_.getYcoords() / c_.getCommon());
                    _p.add(new CustPoint(x_, y_));
                    break;
                }
            }
        }
        return edges_;
    }

    private static int getNbPoints(CustPoint _k, CustPoint _one, CustPoint _two, Triangle _t) {
        int nbPoints_ = IndexConstants.SIZE_EMPTY;
        for (CustPoint p : _t.getPoints()) {
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

    private IdMap<CustPoint,CustList<Triangle>> calculateNextTriangles() {
        IdMap<CustPoint,CustList<Triangle>> id_;
        id_ = new IdMap<CustPoint,CustList<Triangle>>();
        for (EntryCust<CustPoint, IdList<CustPoint>> e: nextPoints.entryList()) {
            CustPoint k_ = e.getKey();
            CustList<Triangle> ts_ = getTs(e, k_);
            id_.put(k_, ts_);
        }
        return id_;
    }

    private CustList<Triangle> getTs(EntryCust<CustPoint, IdList<CustPoint>> _e, CustPoint _k) {
        IdList<CustPoint> v_ = _e.getValue();
        int len_ = v_.size();
        CustList<Triangle> ts_ = new CustList<Triangle>();
        for (int i = 0; i < len_; i++) {
            CustPoint one_ = v_.get(i);
            CustPoint two_ = v_.get((i + 1) % len_);
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

    private IdMap<CustPoint, IdList<CustPoint>> calculateNextPoints() {
        IdMap<CustPoint, IdList<CustPoint>> id_;
        id_ = new IdMap<CustPoint, IdList<CustPoint>>();
        EqList<CustPoint> list_ = new EqList<CustPoint>();
        feedListPt(list_);
        for (CustPoint p: list_) {
            boolean contained_ = isContainedHull(p);
            IdMap<CustPoint,Integer> all_ = new IdMap<CustPoint,Integer>();
            feedMapping(p, all_);
            CustList<CustPoint> next_ = all_.getKeys();
            CustPoint first_ = next_.first();
            EqList<CustPoint> once_ = new EqList<CustPoint>();
            for (EntryCust<CustPoint, Integer> e: all_.entryList()) {
                if (!NumberUtil.eq(e.getValue(), IndexConstants.ONE_ELEMENT)) {
                    continue;
                }
                once_.add(e.getKey());
            }
            if (!once_.isEmpty() && contained_) {
                CustPoint eOne_ = once_.first();
                CustPoint eTwo_ = once_.last();
                VectTwoDims v_ = new VectTwoDims(p, eTwo_);
                SitePoint sOne_ = new SitePoint(eOne_, p, v_);
                v_ = new VectTwoDims(p, eOne_);
                SitePoint sTwo_ = new SitePoint(eTwo_, p, v_);
                if (new SiteComparing().compare(sOne_,sTwo_) == SortConstants.NO_SWAP_SORT) {
                    first_ = eTwo_;
                } else {
                    first_ = eOne_;
                }
            }
            put(id_, p, next_, first_);
        }
        return id_;
    }

    private static void put(IdMap<CustPoint, IdList<CustPoint>> _id, CustPoint _p, CustList<CustPoint> _next, CustPoint _first) {
        CustList<Site> sites_ = new CustList<Site>();
        VectTwoDims v_ = new VectTwoDims(_p, _first);
        for (CustPoint n: _next) {
            sites_.add(new SitePoint(n, _p, v_));
        }
        sites_.sortElts(new SiteComparing());
        IdList<CustPoint> pts_ = new IdList<CustPoint>();
        for (Site s: sites_) {
            pts_.add(((SitePoint)s).getPoint());
        }
        _id.put(_p, pts_);
    }

    private void feedMapping(CustPoint _p, IdMap<CustPoint, Integer> _all) {
        for (Triangle t: triangles) {
            boolean found_ = isFoundPoint(_p, t.getPoints());
            if (!found_) {
                continue;
            }
            for (CustPoint n: t.getPoints()) {
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

    private void feedListPt(EqList<CustPoint> _list) {
        for (Triangle t: triangles) {
            for (CustPoint p: t.getPoints()) {
                boolean contained_ = isFoundPoint(p, _list);
                if (!contained_) {
                    _list.add(p);
                }
            }
        }
    }

    private static boolean isFoundPoint(CustPoint _p, CustList<CustPoint> _points) {
        boolean found_ = false;
        for (CustPoint n : _points) {
            if (n == _p) {
                found_ = true;
                break;
            }
        }
        return found_;
    }

}
