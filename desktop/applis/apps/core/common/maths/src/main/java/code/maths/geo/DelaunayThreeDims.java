package code.maths.geo;
import code.util.CustList;
import code.util.EqList;
import code.util.IdList;

public class DelaunayThreeDims {

    private final IdList<Tetrahedron> triangles = new IdList<Tetrahedron>();

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


    public void compute(EqList<CustPointThreeDims> _points) {
        triangles.clear();
        if (_points.isEmpty()) {
            return;
        }
        int xMax_ = _points.first().getXcoords();
        int xMin_ = _points.first().getXcoords();
        int yMax_ = _points.first().getYcoords();
        int yMin_ = _points.first().getYcoords();
        int zMax_ = _points.first().getZcoords();
        int zMin_ = _points.first().getZcoords();
        for (CustPointThreeDims c: _points) {
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
            if (c.getZcoords() < zMin_) {
                zMin_ = c.getZcoords();
            }
            if (c.getZcoords() > zMax_) {
                zMax_ = c.getZcoords();
            }
        }
        xMin_--;
        yMin_--;
        zMin_--;
        xMax_++;
        yMax_++;
        zMax_++;
        process(_points, xMax_, xMin_, yMax_, yMin_, zMax_, zMin_);
    }

    private void process(EqList<CustPointThreeDims> _points, int _xMax, int _xMin, int _yMax, int _yMin, int _zMax, int _zMin) {
        CustPointThreeDims firstPoint_ = new CustPointThreeDims(_xMin, _yMin, _zMin);
        CustPointThreeDims secondPoint_ = new CustPointThreeDims(_xMax + _xMax - _xMin, _yMin, _zMin);
        CustPointThreeDims thirdPoint_ = new CustPointThreeDims(_xMin, _yMax + _yMax - _yMin, _zMin);
        CustPointThreeDims fourthPoint_ = new CustPointThreeDims(_xMin, _yMin, _zMax + _zMax - _zMin);
        Tetrahedron superTriangle_ = new Tetrahedron(firstPoint_, secondPoint_, thirdPoint_, fourthPoint_);
        triangles.add(superTriangle_);
        for (CustPointThreeDims c: _points) {
            loop(c);
        }
        afterLoop(superTriangle_);
    }

    private void loop(CustPointThreeDims _c) {
        CustList<Tetrahedron> badTriangles_ = new CustList<Tetrahedron>();
        for (Tetrahedron t: triangles) {
            if (t.isInCircum(_c)) {
                badTriangles_.add(t);
            }
        }
        CustList<TriangleThreeDims> edges_;
        edges_ = new CustList<TriangleThreeDims>();
        for (Tetrahedron t: badTriangles_) {
            for (TriangleThreeDims e: t.getTriangles()) {
                boolean addEdge_ = addEdge(badTriangles_, t, e);
                if (addEdge_) {
                    edges_.add(e);
                }
            }
        }
        clean(badTriangles_);
        for (TriangleThreeDims e: edges_) {
            triangles.add(new Tetrahedron(e.getFirstPoint(), e.getSecondPoint(), e.getThirdPoint(), _c));
        }
    }

    private void afterLoop(Tetrahedron _superTriangle) {
        CustList<Tetrahedron> badTriangles_ = new CustList<Tetrahedron>();
        for (Tetrahedron b: triangles) {
            boolean remove_ = isRemove(_superTriangle, b);
            if (remove_) {
                badTriangles_.add(b);
            }
        }
        clean(badTriangles_);
    }

    private static boolean isRemove(Tetrahedron _superTriangle, Tetrahedron _b) {
        boolean remove_ = false;
        for (CustPointThreeDims s: _superTriangle.getPoints()) {
            for (CustPointThreeDims p: _b.getPoints()) {
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

    private void clean(CustList<Tetrahedron> _badTriangles) {
        for (Tetrahedron b : _badTriangles) {
            triangles.removeObj(b);
        }
    }

    private static boolean addEdge(CustList<Tetrahedron> _badTriangles, Tetrahedron _triangle,
            TriangleThreeDims _edge) {
        boolean addEdge_ = true;
        for (Tetrahedron o: _badTriangles) {
            if (o == _triangle) {
                continue;
            }
            boolean break_ = false;
            for (TriangleThreeDims e_: o.getTriangles()) {
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

//    public void increment(CustPoint _pt) {
//        int xMax_ = _pt.getXcoords();
//        int xMin_ = _pt.getXcoords();
//        int yMax_ = _pt.getYcoords();
//        int yMin_ = _pt.getYcoords();
//        Triangle superTriangle_;
//        for (Triangle t: triangles) {
//            for (CustPoint c: t.getPoints()) {
//                if (c.getXcoords() < xMin_) {
//                    xMin_ = c.getXcoords();
//                }
//                if (c.getXcoords() > xMax_) {
//                    xMax_ = c.getXcoords();
//                }
//                if (c.getYcoords() < yMin_) {
//                    yMin_ = c.getYcoords();
//                }
//                if (c.getYcoords() > yMax_) {
//                    yMax_ = c.getYcoords();
//                }
//            }
//        }
//        xMin_--;
//        yMin_--;
//        xMax_++;
//        yMax_++;
//        CustPoint firstPoint_ = new CustPoint(xMin_, yMin_);
//        CustPoint secondPoint_ = new CustPoint(xMax_ + xMax_ - xMin_, yMin_);
//        CustPoint thirdPoint_ = new CustPoint(xMin_, yMax_ + yMax_ - yMin_);
//        superTriangle_ = new Triangle(firstPoint_, secondPoint_, thirdPoint_);
//        triangles.add(superTriangle_);
//        List<Triangle> badTriangles_ = new List<Triangle>();
//        for (Triangle t: triangles) {
//            if (t.isInCircum(_pt)) {
//                badTriangles_.add(t);
//            }
//        }
//        List<Edge> edges_;
//        edges_ = new List<Edge>();
//        for (Triangle t: badTriangles_) {
//            for (Edge e: t.getEdges()) {
//                boolean addEdge_ = addEdge(badTriangles_, t, e);
//                if (addEdge_) {
//                    edges_.add(e);
//                }
//            }
//        }
//        for (Triangle t: badTriangles_) {
//            triangles.remove(t);
//        }
//        for (Edge e: edges_) {
//            triangles.add(new Triangle(e.getFirst(), e.getSecond(), _pt));
//        }
//        badTriangles_ = new List<Triangle>();
//        for (Triangle b: triangles) {
//            boolean remove_ = false;
//            for (CustPoint s: superTriangle_.getPoints()) {
//                for (CustPoint p: b.getPoints()) {
//                    if (s == p) {
//                        remove_ = true;
//                        break;
//                    }
//                }
//                if (remove_) {
//                    break;
//                }
//            }
//            if (remove_) {
//                badTriangles_.add(b);
//            }
//        }
//        for (Triangle b: badTriangles_) {
//            triangles.remove(b);
//        }
//    }
    public IdList<Tetrahedron> getTriangles() {
        return triangles;
    }

//    public IdMap<CustPoint, List<Edge>> evaluateEdges(boolean _addMids) {
//        IdMap<CustPoint, List<CustPoint>> idNextPts_;
//        idNextPts_ = getNextPoints();
//        IdMap<CustPoint, List<Edge>> id_;
//        id_ = new IdMap<CustPoint, List<Edge>>();
//        if (_addMids) {
//            for (EntryCust<CustPoint, List<CustPoint>> e: idNextPts_.entryList()) {
//                CustPoint k_ = e.getKey();
//                List<CustPoint> v_ = e.getValue();
//                Polygon p_ = new Polygon();
//                int x_;
//                int y_;
//                x_ = (v_.first().getXcoords() + k_.getXcoords()) / 2;
//                y_ = (v_.first().getYcoords() + k_.getYcoords()) / 2;
//                CustPoint midOne_ = new CustPoint(x_, y_);
//                p_.add(midOne_);
//                List<Edge> edges_ = new List<Edge>();
//                int len_ = v_.size();
//                for (int i = 0; i < len_; i++) {
//                    CustPoint one_ = v_.get(i);
//                    CustPoint two_ = v_.get((i + 1) % len_);
//                    for (Triangle t: triangles) {
//                        int nbPoints_ = List.SIZE_EMPTY;
//                        for (CustPoint p: t.getPoints()) {
//                            if (p == k_) {
//                                nbPoints_++;
//                            }
//                            if (p == one_) {
//                                nbPoints_++;
//                            }
//                            if (p == two_) {
//                                nbPoints_++;
//                            }
//                        }
//                        if (nbPoints_ == Triangle.NB_POINTS) {
//                            Pair<Pair<Long,Long>,Long> c_;
//                            c_ = t.getCircumCenter();
//                            x_ = (int) (c_.getFirst().getFirst() / c_.getSecond());
//                            y_ = (int) (c_.getFirst().getSecond() / c_.getSecond());
//                            p_.add(new CustPoint(x_, y_));
//                            break;
//                        }
//                    }
//                }
//                x_ = (v_.last().getXcoords() + k_.getXcoords()) / 2;
//                y_ = (v_.last().getYcoords() + k_.getYcoords()) / 2;
//                CustPoint midTwo_ = new CustPoint(x_, y_);
//                p_.add(midTwo_);
//                len_ = p_.size();
//                for (int i = 0; i < len_; i++) {
//                    CustPoint one_ = p_.get(i);
//                    CustPoint two_ = p_.get((i+1)%len_);
//                    Edge e_ = new Edge(one_, two_);
//                    if (e_.isEqual(new Edge(midOne_, midTwo_))) {
//                        continue;
//                    }
//                    edges_.add(e_);
//                }
//                id_.put(k_, edges_);
//            }
//        } else {
//            for (EntryCust<CustPoint, List<CustPoint>> e: idNextPts_.entryList()) {
//                CustPoint k_ = e.getKey();
//                List<CustPoint> v_ = e.getValue();
//                Polygon p_ = new Polygon();
//                int x_;
//                int y_;
//                List<Edge> edges_ = new List<Edge>();
//                int len_ = v_.size();
//                for (int i = 0; i < len_; i++) {
//                    CustPoint one_ = v_.get(i);
//                    CustPoint two_ = v_.get((i + 1) % len_);
//                    for (Triangle t: triangles) {
//                        int nbPoints_ = List.SIZE_EMPTY;
//                        for (CustPoint p: t.getPoints()) {
//                            if (p == k_) {
//                                nbPoints_++;
//                            }
//                            if (p == one_) {
//                                nbPoints_++;
//                            }
//                            if (p == two_) {
//                                nbPoints_++;
//                            }
//                        }
//                        if (nbPoints_ == Triangle.NB_POINTS) {
//                            Pair<Pair<Long,Long>,Long> c_;
//                            c_ = t.getCircumCenter();
//                            x_ = (int) (c_.getFirst().getFirst() / c_.getSecond());
//                            y_ = (int) (c_.getFirst().getSecond() / c_.getSecond());
//                            p_.add(new CustPoint(x_, y_));
//                            break;
//                        }
//                    }
//                }
//                len_ = p_.size();
//                for (int i = 0; i < len_; i++) {
//                    CustPoint one_ = p_.get(i);
//                    CustPoint two_ = p_.get((i+1)%len_);
//                    Edge e_ = new Edge(one_, two_);
//                    edges_.add(e_);
//                }
//                id_.put(k_, edges_);
//            }
//        }
//        return id_;
//    }

//    public IdMap<CustPoint, List<Triangle>> getNextTriangles() {
//        IdMap<CustPoint, List<CustPoint>> idNext_;
//        idNext_ = getNextPoints();
//        IdMap<CustPoint, List<Triangle>> id_;
//        id_ = new IdMap<CustPoint, List<Triangle>>();
//        for (EntryCust<CustPoint, List<CustPoint>> e: idNext_.entryList()) {
//            CustPoint k_ = e.getKey();
//            List<CustPoint> v_ = e.getValue();
//            int len_ = v_.size();
//            List<Triangle> ts_ = new List<Triangle>();
//            for (int i = 0; i < len_; i++) {
//                CustPoint one_ = v_.get(i);
//                CustPoint two_ = v_.get((i + 1) % len_);
//                for (Triangle t: triangles) {
//                    int nbPoints_ = List.SIZE_EMPTY;
//                    for (CustPoint p: t.getPoints()) {
//                        if (p == k_) {
//                            nbPoints_++;
//                        }
//                        if (p == one_) {
//                            nbPoints_++;
//                        }
//                        if (p == two_) {
//                            nbPoints_++;
//                        }
//                    }
//                    if (nbPoints_ == Triangle.NB_POINTS) {
//                        ts_.add(t);
//                        break;
//                    }
//                }
//            }
//            id_.put(k_, ts_);
//        }
//        return id_;
//    }

//    public IdMap<CustPoint, List<CustPoint>> getNextPoints() {
//        IdMap<CustPoint, List<CustPoint>> id_;
//        id_ = new IdMap<CustPoint, List<CustPoint>>();
//        List<CustPoint> list_ = new List<CustPoint>();
//        for (Triangle t: triangles) {
//            for (CustPoint p: t.getPoints()) {
//                boolean contained_ = containsPoint(list_, p);
//                if (!contained_) {
//                    list_.add(p);
//                }
//            }
//        }
//        for (CustPoint p: list_) {
////            List<CustPoint> next_ = new List<CustPoint>();
//            IdMap<CustPoint,Integer> all_ = new IdMap<CustPoint,Integer>();
//            for (Triangle t: triangles) {
//                boolean found_ = false;
//                for (CustPoint n: t.getPoints()) {
//                    if (n == p) {
//                        found_ = true;
//                        break;
//                    }
//                }
//                if (!found_) {
//                    continue;
//                }
//                for (CustPoint n: t.getPoints()) {
//                    if (n == p) {
//                        continue;
//                    }
//                    if (all_.contains(n)) {
//                        all_.put(n, all_.getVal(n) + 1);
//                    } else {
//                        all_.put(n, 1);
//                    }
////                    boolean contained_ = containsPoint(next_, n);
////                    if (!contained_) {
////                        next_.add(n);
////                    }
//                }
//            }
//            List<CustPoint> next_ = all_.getKeys();
//            CustPoint first_ = next_.first();
//            List<CustPoint> once_ = all_.getKeys(List.ONE_ELEMENT);
//            if (!once_.isEmpty()) {
//                CustPoint eOne_ = once_.first();
//                CustPoint eTwo_ = once_.last();
//                VectTwoDims v_ = new VectTwoDims(p, eTwo_);
//                SitePoint sOne_ = new SitePoint(eOne_, p, v_);
//                v_ = new VectTwoDims(p, eOne_);
//                SitePoint sTwo_ = new SitePoint(eTwo_, p, v_);
//                if (sOne_.compareTo(sTwo_) == List.NO_SWAP_SORT) {
//                    first_ = eTwo_;
//                } else {
//                    first_ = eOne_;
//                }
//            }
//            SortableList<SitePoint> sites_ = new SortableList<SitePoint>();
//            VectTwoDims v_ = new VectTwoDims(p, first_);
//            for (CustPoint n: next_) {
//                sites_.add(new SitePoint(n, p, v_));
//            }
//            sites_.sort();
//            next_.clear();
//            for (SitePoint s: sites_) {
//                next_.add(s.getPoint());
//            }
//            id_.put(p, next_);
//        }
//        return id_;
//    }

//    private boolean containsPoint(List<CustPoint> _points, CustPoint _pt) {
//        boolean contained_ = false;
//        for (CustPoint a: _points) {
//            if (a == _pt) {
//                contained_ = true;
//                break;
//            }
//        }
//        return contained_;
//    }

//    public List<Triangle> getNextTriangles(Triangle _t) {
//        List<Triangle> next_ = new List<Triangle>();
//        for (Triangle t: triangles) {
//            if (t == _t) {
//                continue;
//            }
//            boolean add_ = false;
//            for (CustPoint o: t.getPoints()) {
//                for (CustPoint p: _t.getPoints()) {
//                    if (o == p) {
//                        add_ = true;
//                        break;
//                    }
//                }
//                if (add_) {
//                    break;
//                }
//            }
//            if (add_) {
//                next_.add(t);
//            }
//        }
//        return next_;
//    }
}
