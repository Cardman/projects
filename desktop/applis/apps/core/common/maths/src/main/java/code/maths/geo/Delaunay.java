package code.maths.geo;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdList;
import code.util.IdMap;
import code.util.*;

public final class Delaunay {

    private IdList<Triangle> triangles = new IdList<Triangle>();

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
        int xMax_ = _points.first().getXcoords();
        int xMin_ = _points.first().getXcoords();
        int yMax_ = _points.first().getYcoords();
        int yMin_ = _points.first().getYcoords();
        Triangle superTriangle_;
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
        CustPoint firstPoint_ = new CustPoint(xMin_, yMin_);
        CustPoint secondPoint_ = new CustPoint(xMax_ + xMax_ - xMin_, yMin_);
        CustPoint thirdPoint_ = new CustPoint(xMin_, yMax_ + yMax_ - yMin_);
        superTriangle_ = new Triangle(firstPoint_, secondPoint_, thirdPoint_);
        triangles.add(superTriangle_);
        for (CustPoint c: _points) {
            convexHull.add(c);
            CustList<Triangle> badTriangles_ = new CustList<Triangle>();
            for (Triangle t: triangles) {
                if (t.isInCircum(c)) {
                    badTriangles_.add(t);
                }
            }
            CustList<Edge> edges_;
            edges_ = new CustList<Edge>();
            for (Triangle t: badTriangles_) {
                for (Edge e: t.getEdges()) {
                    boolean addEdge_ = addEdge(badTriangles_, t, e);
                    if (addEdge_) {
                        edges_.add(e);
                    }
                }
            }
            for (Triangle t: badTriangles_) {
                triangles.removeObj(t);
            }
            for (Edge e: edges_) {
                triangles.add(new Triangle(e.getFirst(), e.getSecond(), c));
            }
        }
        CustList<Triangle> badTriangles_ = new CustList<Triangle>();
        for (Triangle b: triangles) {
            boolean remove_ = false;
            for (CustPoint s: superTriangle_.getPoints()) {
                for (CustPoint p: b.getPoints()) {
                    if (s == p) {
                        remove_ = true;
                        break;
                    }
                }
                if (remove_) {
                    break;
                }
            }
            if (remove_) {
                badTriangles_.add(b);
            }
        }
        for (Triangle b: badTriangles_) {
            triangles.removeObj(b);
        }
    }

    private static boolean addEdge(CustList<Triangle> _badTriangles, Triangle _triangle,
                                   Edge _edge) {
        boolean addEdge_ = true;
        for (Triangle o: _badTriangles) {
            if (o == _triangle) {
                continue;
            }
            boolean break_ = false;
            for (Edge e_: o.getEdges()) {
                if (_edge.isSame(e_)) {
                    addEdge_ = false;
                    break_ = true;
                    break;
                }
            }
            if (break_) {
                break;
            }
        }
        return addEdge_;
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
        int index_ = Triangle.NB_POINTS - 1;
        while (index_ < points_.size()) {
            VectTwoDims vLoc_ = new VectTwoDims(first_,points_.get(index_));
            if (v_.det(vLoc_) != 0) {
                break;
            }
            index_++;
        }
        if (index_ >= points_.size()) {
            return;
        }
        CustPoint third_ = points_.get(index_);
        triangles.add(new Triangle(first_, second_, third_));
        convexHull.add(first_);
        convexHull.add(second_);
        convexHull.add(third_);
        points_.remove(index_);
        points_.remove((int) CustList.SECOND_INDEX);
        points_.remove((int) CustList.FIRST_INDEX);
        EqList<CustPoint> all_ = new EqList<CustPoint>();
        all_.add(first_);
        all_.add(second_);
        all_.add(third_);
        Polygon p_ = convexHull.getConvexHull();
        for (CustPoint c: points_) {
            CustList<Triangle> del_ = new CustList<Triangle>();
            CustList<Triangle> nearlyDel_ = new CustList<Triangle>();
            for (Triangle t: triangles) {
                if (t.isInCircum(c)) {
                    del_.add(t);
                } else if (t.isInCircumBorder(c)) {
                    nearlyDel_.add(t);
                }
            }
            CustList<Edge> edges_ = new CustList<Edge>();
            if (del_.isEmpty()) {
                //&& !Polygon.containsInside(p_, c)
                CustList<Edge> hull_ = p_.getEdges();
                EqList<CustPoint> pts_ = new EqList<CustPoint>();
                int len_ = p_.size();
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    CustPoint c_ = p_.get(i);
                    boolean contains_ = false;
                    for (Edge e: hull_) {
                        if (e.intersectNotContainsBound(new Edge(c, c_))) {
                            contains_ = true;
                            break;
                        }
                    }
                    if (!contains_) {
                        pts_.add(c_);
                    }
                }
                edges_.add(new Edge(pts_.first(), pts_.get(1)));
                int lenPts_ = pts_.size() - 1;
                for (int i = CustList.SECOND_INDEX; i < lenPts_; i++) {
                    edges_.add(new Edge(pts_.get(i), pts_.get(i+1)));
                }
                removeDuplicates(edges_);
                for (Edge e: edges_) {
                    Triangle toBeIns_ = new Triangle(e.getFirst(), e.getSecond(), c);
                    VectTwoDims vOne_ = new VectTwoDims(e.getFirst(), e.getSecond());
                    VectTwoDims vTwo_ = new VectTwoDims(e.getFirst(), c);
                    addIfPlainTriangle(toBeIns_,vOne_,vTwo_);
                }
            } else {
                if (!Polygon.containsInside(p_, c)) {
                    triangles.removeAllElements(del_);
                    int indexPt_ = CustList.INDEX_NOT_FOUND_ELT;
                    int len_ = p_.size();
                    for (Triangle t: del_) {
                        for (Edge e: t.getEdges()) {
                            boolean added_ = true;
                            for (CustPoint q: t.getPoints()) {
                                if (e.intersectNotContainsBound(new Edge(c, q))) {
                                    added_ = false;
                                    break;
                                }
                            }
                            if (added_) {
                                Triangle toBeIns_ = new Triangle(e.getFirst(), e.getSecond(), c);
                                VectTwoDims vOne_ = new VectTwoDims(e.getFirst(), e.getSecond());
                                VectTwoDims vTwo_ = new VectTwoDims(e.getFirst(), c);
                                addIfPlainTriangle(toBeIns_, vOne_, vTwo_);
                            } else {
                                indexPt_ = addPoint(p_, c, indexPt_, len_, e);
                            }
                        }
                    }
                    all_.add(c);
                    convexHull.add(c);
                    if (triangles.size() < Triangle.NB_POINTS) {
                        p_ = convexHull.getConvexHull();
                        continue;
                    }
                    int befBef_ = Numbers.mod(indexPt_ - 1,len_);
                    int bef_ = Numbers.mod(indexPt_,len_);
                    int next_ = Numbers.mod(indexPt_+2,len_);
                    int nextNext_ = Numbers.mod(indexPt_+Triangle.NB_POINTS,len_);
                    Edge eOne_ = new Edge(p_.get(befBef_), c);
                    Edge eTwo_ = new Edge(p_.get(nextNext_), c);
                    addIfNotIntersect(p_, c, bef_, befBef_, eOne_);
                    addIfNotIntersect(p_, c, next_, nextNext_, eTwo_);
                    p_ = convexHull.getConvexHull();
                    continue;
                }
                CustList<Edge> keepEdges_ = new CustList<Edge>();
                for (Triangle t: del_) {
                    edges_.addAllElts(t.getEdges());
                }
                for (Triangle t: nearlyDel_) {
                    edges_.addAllElts(t.getEdges());
                }
                for (Edge e: edges_) {
                    boolean same_ = false;
                    for (Edge f: edges_) {
                        if (e == f) {
                            continue;
                        }
                        if (e.isSame(f)) {
                            same_ = true;
                            break;
                        }
                    }
                    if (!same_) {
                        keepEdges_.add(e);
                    }
                }
                edges_.clear();
                for (Triangle t: triangles) {
                    edges_.addAllElts(t.getEdges());
                }
                removeDuplicates(edges_);
                triangles.removeAllElements(del_);
                triangles.removeAllElements(nearlyDel_);
                for (Edge e: keepEdges_) {
                    Triangle toBeIns_ = new Triangle(e.getFirst(), e.getSecond(), c);
                    VectTwoDims vOne_ = new VectTwoDims(e.getFirst(), e.getSecond());
                    VectTwoDims vTwo_ = new VectTwoDims(e.getFirst(), c);
                    addIfPlainTriangle(toBeIns_,vOne_,vTwo_);
                }
            }
            all_.add(c);
            if (!Polygon.containsInside(p_, c)) {
                convexHull.add(c);
                p_ = convexHull.getConvexHull();
            }
        }
        convexHull = p_;
        nextPoints = calculateNextPoints();
        edges = evaluateEdges(_addMids);
        nextTriangles = calculateNextTriangles();
    }

    int addPoint(Polygon _polygon, CustPoint _pt, int _indexPt, int _len, Edge _e) {
        int indexPt_ = _indexPt;
        for (int i = CustList.FIRST_INDEX; i < _len; i++) {
            Edge e_ = new Edge(_polygon.get(i),_polygon.get(Numbers.mod(i+1, _len)));
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
        CustList<Edge> farEdges_ = new CustList<Edge>();
        for (Edge e: convexHull.getEdges()) {
            if (e.getFirst() == first_) {
                continue;
            }
            if (e.getSecond() == first_) {
                continue;
            }
            farEdges_.add(e);
        }
        for (Edge e: farEdges_) {
            triangles.add(new Triangle(first_, e.getFirst(), e.getSecond()));
        }
        EqList<CustPoint> visited_ = new EqList<CustPoint>();
        for (CustPoint c: points_) {
            for (CustPoint p: convexHull) {
                if (c == p) {
                    visited_.add(c);
                    break;
                }
            }
        }
        for (CustPoint c: visited_) {
            points_.removeObj(c);
        }
        EqList<CustPoint> all_ = new EqList<CustPoint>(visited_);
        Polygon p_ = convexHull.getConvexHull();
        for (CustPoint c: points_) {
            CustList<Triangle> del_ = new CustList<Triangle>();
            CustList<Triangle> nearlyDel_ = new CustList<Triangle>();
            for (Triangle t: triangles) {
                if (t.isInCircum(c)) {
                    del_.add(t);
                } else if (t.isInCircumBorder(c)) {
                    nearlyDel_.add(t);
                }
            }
            CustList<Edge> edges_ = new CustList<Edge>();

            CustList<Edge> keepEdges_ = new CustList<Edge>();
            for (Triangle t: del_) {
                edges_.addAllElts(t.getEdges());
            }
            for (Triangle t: nearlyDel_) {
                edges_.addAllElts(t.getEdges());
            }
            for (Edge e: edges_) {
                boolean same_ = false;
                for (Edge f: edges_) {
                    if (e == f) {
                        continue;
                    }
                    if (e.isSame(f)) {
                        same_ = true;
                        break;
                    }
                }
                if (!same_) {
                    keepEdges_.add(e);
                }
            }
            edges_.clear();
            for (Triangle t: triangles) {
                edges_.addAllElts(t.getEdges());
            }
            removeDuplicates(edges_);
            triangles.removeAllElements(del_);
            triangles.removeAllElements(nearlyDel_);
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

    private static void removeDuplicates(CustList<Edge> _edges) {
        //setModified();
        int i_ = CustList.FIRST_INDEX;
        while (i_ < _edges.size()) {
            int j_ = i_;
            j_++;
            while (true) {
                if (j_ >= _edges.size()) {
                    break;
                }
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
        int index_ = Triangle.NB_POINTS - 1;
        while (index_ < points_.size()) {
            VectTwoDims vLoc_ = new VectTwoDims(first_,points_.get(index_));
            if (v_.det(vLoc_) != 0) {
                break;
            }
            index_++;
        }
        if (index_ >= points_.size()) {
            return;
        }
        CustPoint third_ = points_.get(index_);
        triangles.add(new Triangle(first_, second_, third_));
        convexHull.add(first_);
        convexHull.add(second_);
        convexHull.add(third_);
        int xMax_ = points_.first().getXcoords();
        int xMin_ = points_.first().getXcoords();
        int yMax_ = points_.first().getYcoords();
        int yMin_ = points_.first().getYcoords();
        Triangle superTriangle_;
        for (CustPoint c: points_) {
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
        CustPoint firstPoint_ = new CustPoint(xMin_, yMin_);
        CustPoint secondPoint_ = new CustPoint(xMax_ + xMax_ - xMin_, yMin_);
        CustPoint thirdPoint_ = new CustPoint(xMin_, yMax_ + yMax_ - yMin_);
        superTriangle_ = new Triangle(firstPoint_, secondPoint_, thirdPoint_);
        triangles.add(superTriangle_);
        points_.remove(index_);
        points_.remove((int) CustList.SECOND_INDEX);
        points_.remove((int) CustList.FIRST_INDEX);
        EqList<CustPoint> all_ = new EqList<CustPoint>();
        all_.add(first_);
        all_.add(second_);
        all_.add(third_);
        Polygon p_ = convexHull.getConvexHull();
        for (CustPoint c: points_) {
            CustList<Triangle> del_ = new CustList<Triangle>();
            CustList<Triangle> delStr_ = new CustList<Triangle>();
            CustList<Triangle> nearlyDel_ = new CustList<Triangle>();
            for (Triangle t: triangles) {
                if (t.isInCircum(c)) {
                    del_.add(t);
                    if (t != superTriangle_) {
                        delStr_.add(t);
                    }
                } else if (t.isInCircumBorder(c)) {
                    nearlyDel_.add(t);
                }
            }
            CustList<Edge> edges_ = new CustList<Edge>();
            if (delStr_.isEmpty() && !nearlyDel_.isEmpty()) {
                CustList<Edge> hull_ = p_.getEdges();
                Polygon pts_ = new Polygon();
                int len_ = p_.size();
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    CustPoint c_ = p_.get(i);
                    boolean contains_ = false;
                    for (Edge e: hull_) {
                        if (e.intersectNotContainsBound(new Edge(c, c_))) {
                            contains_ = true;
                            break;
                        }
                    }
                    if (!contains_) {
                        pts_.add(c_);
                    }
                }
                edges_ = pts_.getEdges();
                removeDuplicates(edges_);
                for (Edge e: edges_) {
                    Triangle toBeIns_ = new Triangle(e.getFirst(), e.getSecond(), c);
                    triangles.add(toBeIns_);
                }
                continue;
            }
            for (Triangle t: triangles) {
                if (superTriangle_ == t) {
                    continue;
                }
                edges_.addAllElts(t.getEdges());
            }
            removeDuplicates(edges_);
            triangles.removeAllElements(del_);
            triangles.removeAllElements(nearlyDel_);
            //ADDED BEGIN
            triangles.add(superTriangle_);
            CustList<Edge> allAddedEdges_ = new CustList<Edge>();
            for (Edge e: edges_) {
                Triangle toBeIns_ = new Triangle(e.getFirst(), e.getSecond(), c);
                VectTwoDims vOne_ = new VectTwoDims(e.getFirst(), e.getSecond());
                VectTwoDims vTwo_ = new VectTwoDims(e.getFirst(), c);
                if (vOne_.det(vTwo_) == 0) {
                    continue;
                }
                boolean contains_ = false;
                CustList<Edge> addedEdges_ = new CustList<Edge>();
                addedEdges_.add(new Edge(e.getFirst(), c));
                addedEdges_.add(new Edge(e.getSecond(), c));
                CustList<Edge> union_ = new CustList<Edge>(allAddedEdges_);
                union_.add(e);
                for (Edge g: union_) {
                    if (e.isSame(g)) {
                        continue;
                    }
                    if (e.intersectNotContainsBound(g)) {
                        contains_ = true;
                        break;
                    }
                }
                if (contains_) {
                    continue;
                }
                for (CustPoint o: all_) {
                    if (o == e.getFirst()) {
                        continue;
                    }
                    if (o == e.getSecond()) {
                        continue;
                    }
                    if (toBeIns_.isInCircum(o)) {
                        contains_ = true;
                        break;
                    }
                }
                if (!contains_) {
                    allAddedEdges_.addAllElts(toBeIns_.getEdges());
                    removeDuplicates(allAddedEdges_);
                    triangles.add(toBeIns_);
                }
            }
            all_.add(c);
            if (!Polygon.containsInside(p_, c)) {
                convexHull.add(c);
                p_ = convexHull.getConvexHull();
            }
        }
        CustList<Triangle> badTriangles_ = new CustList<Triangle>();
        CustList<Edge> allEdges_ = new CustList<Edge>();
        for (Triangle b: triangles) {
            if (b == superTriangle_) {
                continue;
            }
            allEdges_.addAllElts(b.getEdges());
        }
        for (Triangle b: triangles) {
            boolean remove_ = false;
            for (CustPoint s: superTriangle_.getPoints()) {
                for (CustPoint p: b.getPoints()) {
                    if (s == p) {
                        remove_ = true;
                        break;
                    }
                }
                if (remove_) {
                    break;
                }
            }
            if (remove_) {
                badTriangles_.add(b);
                continue;
            }
        }
        for (Triangle b: badTriangles_) {
            triangles.removeObj(b);
        }
        triangles.removeObj(superTriangle_);
        convexHull = p_;
        nextPoints = calculateNextPoints();
        edges = evaluateEdges(true);
        nextTriangles = calculateNextTriangles();
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
        IdMap<CustPoint,CustList<Edge>> id_;
        id_ = new IdMap<CustPoint,CustList<Edge>>();
        if (_addMids) {
            for (EntryCust<CustPoint, IdList<CustPoint>> e: nextPoints.entryList()) {
                CustPoint k_ = e.getKey();
                IdList<CustPoint> v_ = e.getValue();
                Polygon p_ = new Polygon();
                boolean contained_ = false;
                for (CustPoint c: convexHull) {
                    if (c == k_) {
                        contained_ = true;
                        break;
                    }
                }
                int x_;
                int y_;
                x_ = (v_.first().getXcoords() + k_.getXcoords()) / 2;
                y_ = (v_.first().getYcoords() + k_.getYcoords()) / 2;
                CustPoint midOne_ = new CustPoint(x_, y_);
                if (contained_) {
                    p_.add(midOne_);
                }
                CustList<Edge> edges_ = new CustList<Edge>();
                int len_ = v_.size();
                for (int i = 0; i < len_; i++) {
                    CustPoint one_ = v_.get(i);
                    CustPoint two_ = v_.get((i + 1) % len_);
                    for (Triangle t: triangles) {
                        int nbPoints_ = CustList.SIZE_EMPTY;
                        for (CustPoint p: t.getPoints()) {
                            if (p == k_) {
                                nbPoints_++;
                            }
                            if (p == one_) {
                                nbPoints_++;
                            }
                            if (p == two_) {
                                nbPoints_++;
                            }
                        }
                        if (nbPoints_ == Triangle.NB_POINTS) {
                            CompactPlanePoint c_;
                            c_ = t.getCircumCenter();
                            x_ = (int) (c_.getXcoords() / c_.getCommon());
                            y_ = (int) (c_.getYcoords() / c_.getCommon());
                            p_.add(new CustPoint(x_, y_));
                            break;
                        }
                    }
                }
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
        } else {
            for (EntryCust<CustPoint, IdList<CustPoint>> e: nextPoints.entryList()) {
                CustPoint k_ = e.getKey();
                IdList<CustPoint> v_ = e.getValue();
                Polygon p_ = new Polygon();
                int x_;
                int y_;
                CustList<Edge> edges_ = new CustList<Edge>();
                int len_ = v_.size();
                for (int i = 0; i < len_; i++) {
                    CustPoint one_ = v_.get(i);
                    CustPoint two_ = v_.get((i + 1) % len_);
                    for (Triangle t: triangles) {
                        int nbPoints_ = CustList.SIZE_EMPTY;
                        for (CustPoint p: t.getPoints()) {
                            if (p == k_) {
                                nbPoints_++;
                            }
                            if (p == one_) {
                                nbPoints_++;
                            }
                            if (p == two_) {
                                nbPoints_++;
                            }
                        }
                        if (nbPoints_ == Triangle.NB_POINTS) {
                            CompactPlanePoint c_;
                            c_ = t.getCircumCenter();
                            x_ = (int) (c_.getXcoords() / c_.getCommon());
                            y_ = (int) (c_.getYcoords() / c_.getCommon());
                            p_.add(new CustPoint(x_, y_));
                            break;
                        }
                    }
                }
                len_ = p_.size();
                for (int i = 0; i < len_; i++) {
                    CustPoint one_ = p_.get(i);
                    CustPoint two_ = p_.get((i+1)%len_);
                    Edge e_ = new Edge(one_, two_);
                    edges_.add(e_);
                }
                id_.put(k_, edges_);
            }
        }
        return id_;
    }

    private IdMap<CustPoint,CustList<Triangle>> calculateNextTriangles() {
        IdMap<CustPoint,CustList<Triangle>> id_;
        id_ = new IdMap<CustPoint,CustList<Triangle>>();
        for (EntryCust<CustPoint, IdList<CustPoint>> e: nextPoints.entryList()) {
            CustPoint k_ = e.getKey();
            IdList<CustPoint> v_ = e.getValue();
            int len_ = v_.size();
            CustList<Triangle> ts_ = new CustList<Triangle>();
            for (int i = 0; i < len_; i++) {
                CustPoint one_ = v_.get(i);
                CustPoint two_ = v_.get((i + 1) % len_);
                for (Triangle t: triangles) {
                    int nbPoints_ = CustList.SIZE_EMPTY;
                    for (CustPoint p: t.getPoints()) {
                        if (p == k_) {
                            nbPoints_++;
                        }
                        if (p == one_) {
                            nbPoints_++;
                        }
                        if (p == two_) {
                            nbPoints_++;
                        }
                    }
                    if (nbPoints_ == Triangle.NB_POINTS) {
                        ts_.add(t);
                        break;
                    }
                }
            }
            id_.put(k_, ts_);
        }
        return id_;
    }

    private IdMap<CustPoint, IdList<CustPoint>> calculateNextPoints() {
        IdMap<CustPoint, IdList<CustPoint>> id_;
        id_ = new IdMap<CustPoint, IdList<CustPoint>>();
        EqList<CustPoint> list_ = new EqList<CustPoint>();
        for (Triangle t: triangles) {
            for (CustPoint p: t.getPoints()) {
                boolean contained_ = containsPoint(list_, p);
                if (!contained_) {
                    list_.add(p);
                }
            }
        }
        for (CustPoint p: list_) {
            boolean contained_ = false;
            for (CustPoint c: convexHull) {
                if (c == p) {
                    contained_ = true;
                    break;
                }
            }
            IdMap<CustPoint,Integer> all_ = new IdMap<CustPoint,Integer>();
            for (Triangle t: triangles) {
                boolean found_ = false;
                for (CustPoint n: t.getPoints()) {
                    if (n == p) {
                        found_ = true;
                        break;
                    }
                }
                if (!found_) {
                    continue;
                }
                for (CustPoint n: t.getPoints()) {
                    if (n == p) {
                        continue;
                    }
                    if (all_.contains(n)) {
                        all_.put(n, all_.getVal(n) + 1);
                    } else {
                        all_.put(n, 1);
                    }
                }
            }
            CustList<CustPoint> next_ = all_.getKeys();
            CustPoint first_ = next_.first();
            EqList<CustPoint> once_ = new EqList<CustPoint>();
            for (EntryCust<CustPoint, Integer> e: all_.entryList()) {
                if (!Numbers.eq(e.getValue(), CustList.ONE_ELEMENT)) {
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
                if (new SiteComparing().compare(sOne_,sTwo_) == CustList.NO_SWAP_SORT) {
                    first_ = eTwo_;
                } else {
                    first_ = eOne_;
                }
            }
            CustList<Site> sites_ = new CustList<Site>();
            VectTwoDims v_ = new VectTwoDims(p, first_);
            for (CustPoint n: next_) {
                sites_.add(new SitePoint(n, p, v_));
            }
            sites_.sortElts(new SiteComparing());
            IdList<CustPoint> pts_ = new IdList<CustPoint>();
            for (Site s: sites_) {
                pts_.add(((SitePoint)s).getPoint());
            }
            id_.put(p, pts_);
        }
        return id_;
    }

    private static boolean containsPoint(EqList<CustPoint> _points, CustPoint _pt) {
        boolean contained_ = false;
        for (CustPoint a: _points) {
            if (a == _pt) {
                contained_ = true;
                break;
            }
        }
        return contained_;
    }

}
