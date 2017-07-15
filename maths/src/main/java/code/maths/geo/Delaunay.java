package code.maths.geo;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdList;
import code.util.IdMap;
import code.util.Numbers;
import code.util.SortableCustList;

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
        compute(_points, false, false);
    }

    public void compute(EqList<CustPoint> _points, boolean _addMids, boolean _apply) {
        mainCompute(_points);
        if (_points.isEmpty()) {
            return;
        }
        if (triangles.isEmpty()) {
            return;
        }
        if (triangles.size() == CustList.ONE_ELEMENT) {
            convexHull = convexHull.getConvexHull();
            nextPoints = calculateNextPoints();
            edges = evaluateEdges(_addMids);
            nextTriangles = calculateNextTriangles();
            return;
        }
//        triangles.clear();
//        convexHull.clear();
//        edges.clear();
//        nextTriangles.clear();
//        nextPoints.clear();
//        if (_points.isEmpty()) {
//            return;
//        }
//        int xMax_ = _points.first().getXcoords();
//        int xMin_ = _points.first().getXcoords();
//        int yMax_ = _points.first().getYcoords();
//        int yMin_ = _points.first().getYcoords();
//        Triangle superTriangle_;
//        for (CustPoint c: _points) {
//            if (c.getXcoords() < xMin_) {
//                xMin_ = c.getXcoords();
//            }
//            if (c.getXcoords() > xMax_) {
//                xMax_ = c.getXcoords();
//            }
//            if (c.getYcoords() < yMin_) {
//                yMin_ = c.getYcoords();
//            }
//            if (c.getYcoords() > yMax_) {
//                yMax_ = c.getYcoords();
//            }
//        }
//        xMin_ --;
//        yMin_ --;
//        xMax_ ++;
//        yMax_ ++;
//        CustPoint firstPoint_ = new CustPoint(xMin_, yMin_);
//        CustPoint secondPoint_ = new CustPoint(xMax_ + xMax_ - xMin_, yMin_);
//        CustPoint thirdPoint_ = new CustPoint(xMin_, yMax_ + yMax_ - yMin_);
//        superTriangle_ = new Triangle(firstPoint_, secondPoint_, thirdPoint_);
//        triangles.add(superTriangle_);
//        for (CustPoint c: _points) {
//            convexHull.add(c);
//            CustList<Triangle> badTriangles_ = new CustList<Triangle>();
//            for (Triangle t: triangles) {
//                if (t.isInCircum(c)) {
//                    badTriangles_.add(t);
//                }
//            }
//            CustList<Edge> edges_;
//            edges_ = new CustList<Edge>();
//            for (Triangle t: badTriangles_) {
//                for (Edge e: t.getEdges()) {
//                    boolean addEdge_ = addEdge(badTriangles_, t, e);
//                    if (addEdge_) {
//                        edges_.add(e);
//                    }
//                }
//            }
//            for (Triangle t: badTriangles_) {
//                triangles.remove(t);
//            }
//            for (Edge e: edges_) {
//                triangles.add(new Triangle(e.getFirst(), e.getSecond(), c));
//            }
//        }
//        CustList<Triangle> badTriangles_ = new CustList<Triangle>();
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
        if (_apply) {
            Polygon old_ = convexHull;
            convexHull = convexHull.getConvexHull();
            CustList<Triangle> newTriangles_ = new CustList<Triangle>();
//            boolean wasOne_ = triangles.size() <= 1;
//            CustList<Triangle> goodTriangles_ = new CustList<Triangle>();
            for (Edge e: convexHull.getEdges()) {
                boolean found_ = false;
                for (Triangle b: triangles) {
                    for (Edge o: b.getEdges()) {
                        if (e.isSame(o)) {
                            found_ = true;
                            break;
                        }
                    }
                    if (found_) {
                        break;
                    }
                }
//                if (!found_) {
//                    int indexOne_ = CustList.INDEX_NOT_FOUND_ELT;
//                    int indexTwo_ = CustList.INDEX_NOT_FOUND_ELT;
//                    int index_ = CustList.FIRST_INDEX;
//                    for (CustPoint p: old_) {
//                        if (p == e.getFirst()) {
//                            indexOne_ = index_;
//                        }
//                        if (p == e.getSecond()) {
//                            indexTwo_ = index_;
//                        }
//                        index_ ++;
//                    }
//                    Numbers<Integer> sorted_ = new Numbers<Integer>();
//                    int len_ = old_.size();
//                    if (indexTwo_ > indexOne_) {
//                        for (int i = indexTwo_; i <len_ ; i++) {
//                            sorted_.add(i);
//                        }
//                        for (int i = CustList.FIRST_INDEX; i <=indexOne_ ; i++) {
//                            sorted_.add(i);
//                        }
//                    } else {
//                        for (int i = indexOne_; i <len_ ; i++) {
//                            sorted_.add(i);
//                        }
//                        for (int i = CustList.FIRST_INDEX; i <=indexTwo_ ; i++) {
//                            sorted_.add(i);
//                        }
//                    }
//                    CustList<CustPoint> points_ = new CustList<CustPoint>();
//                    for (int i: sorted_) {
//                        points_.add(old_.get(i));
//                    }
//                    Delaunay d_ = new Delaunay();
//                    d_.mainCompute(points_);
//                    newTriangles_.addAllElts(d_.triangles);
//                }
                int indexOne_ = CustList.INDEX_NOT_FOUND_ELT;
                int indexTwo_ = CustList.INDEX_NOT_FOUND_ELT;
                int index_ = CustList.FIRST_INDEX;
                for (CustPoint p: old_) {
                    if (p == e.getFirst()) {
                        indexOne_ = index_;
                    }
                    if (p == e.getSecond()) {
                        indexTwo_ = index_;
                    }
                    index_ ++;
                }
                Numbers<Integer> sorted_ = new Numbers<Integer>();
                int len_ = old_.size();
                boolean compute_ = true;
                if (indexTwo_ > indexOne_) {
                    if (indexOne_ + 1 == indexTwo_ || (indexTwo_ + 1) % len_ == indexOne_) {
                        compute_ = false;
                    }
                    for (int i = indexTwo_; i <len_ ; i++) {
                        sorted_.add(i);
                    }
                    for (int i = CustList.FIRST_INDEX; i <=indexOne_ ; i++) {
                        sorted_.add(i);
                    }
                } else {
                    if (indexTwo_ + 1 == indexOne_ || (indexOne_ + 1) % len_ == indexTwo_) {
                        compute_ = false;
                    }
                    for (int i = indexOne_; i <len_ ; i++) {
                        sorted_.add(i);
                    }
                    for (int i = CustList.FIRST_INDEX; i <=indexTwo_ ; i++) {
                        sorted_.add(i);
                    }
                }
                if (!compute_) {
                    continue;
                }
                EqList<CustPoint> points_ = new EqList<CustPoint>();
                for (int i: sorted_) {
                    points_.add(old_.get(i));
                }
                Delaunay d_ = new Delaunay();
                d_.mainCompute(points_);
                newTriangles_.addAllElts(d_.triangles);
            }
            triangles.addAllElts(newTriangles_);
        } else {
            convexHull = convexHull.getConvexHull();
        }
        nextPoints = calculateNextPoints();
        edges = evaluateEdges(_addMids);
        nextTriangles = calculateNextTriangles();
    }

    public void computeIncr(EqList<CustPoint> _points) {
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
        xMin_ --;
        yMin_ --;
        xMax_ ++;
        yMax_ ++;
        CustPoint firstPoint_ = new CustPoint(xMin_, yMin_);
        CustPoint secondPoint_ = new CustPoint(xMax_ + xMax_ - xMin_, yMin_);
        CustPoint thirdPoint_ = new CustPoint(xMin_, yMax_ + yMax_ - yMin_);
        superTriangle_ = new Triangle(firstPoint_, secondPoint_, thirdPoint_);
        triangles.add(superTriangle_);
        for (CustPoint c: points_) {
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
                edges_.addAllElts(t.getEdges());
            }
            for (int j = edges_.size() - 1; j >= 0; j--) {
                for (int k = edges_.size(); k >= j + 1; k--) {
                    if (j < edges_.size() && k < edges_.size() && edges_.get(j).isSame(edges_.get(k))) {
                        edges_.removeAt(j);
                        edges_.removeAt(k-1);
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
        convexHull = convexHull.getConvexHull();
        nextPoints = calculateNextPoints();
        edges = evaluateEdges(true);
        nextTriangles = calculateNextTriangles();
    }

    private void mainCompute(EqList<CustPoint> _points) {
        triangles.clear();
        convexHull.clear();
        edges.clear();
        nextTriangles.clear();
        nextPoints.clear();
        if (_points.isEmpty()) {
            return;
        }
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
        xMin_ --;
        yMin_ --;
        xMax_ ++;
        yMax_ ++;
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
//            CustList<Edge> keepEdges_ = new CustList<Edge>();
            for (Triangle t: badTriangles_) {
                edges_.addAllElts(t.getEdges());
            }
            for (int j = edges_.size() - 1; j >= 0; j--) {
                for (int k = edges_.size() - 1; k >= j + 1; k--) {
                    if (edges_.get(j).isSame(edges_.get(k))) {
                        edges_.removeAt(j);
                        edges_.removeAt(k-1);
                    }
                }
            }
//            for (Edge e: edges_) {
//                boolean same_ = false;
//                for (Edge f: edges_) {
//                    if (e == f) {
//                        continue;
//                    }
//                    if (e.isSame(f)) {
//                        same_ = true;
//                        break;
//                    }
//                }
//                if (!same_) {
//                    keepEdges_.add(e);
//                }
//            }
//            for (Triangle t: badTriangles_) {
//                for (Edge e: t.getEdges()) {
//                    boolean addEdge_ = addEdge(badTriangles_, t, e);
//                    if (addEdge_) {
//                        edges_.add(e);
//                    }
//                }
//            }
            for (Triangle t: badTriangles_) {
                triangles.removeObj(t);
            }
            for (Edge e: edges_) {
                triangles.add(new Triangle(e.getFirst(), e.getSecond(), c));
            }
            //B BK
//            for (Edge e: edges_) {
//                triangles.add(new Triangle(e.getFirst(), e.getSecond(), c));
//            }
            //E BK
//            for (Triangle t: badTriangles_) {
//                boolean add_ = false;
//                for (CustPoint s: superTriangle_.getPoints()) {
//                    for (CustPoint p: t.getPoints()) {
//                        if (s == p) {
//                            add_ = true;
//                            break;
//                        }
//                    }
//                    if (add_) {
//                        break;
//                    }
//                }
//                if (add_) {
//                    triangles.add(t);
//                }
//            }
//            triangles.add(superTriangle_);
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
            index_ ++;
        }
        if (index_ >= points_.size()) {
            return;
        }
        CustPoint third_ = points_.get(index_);
        triangles.add(new Triangle(first_, second_, third_));
        convexHull.add(first_);
        convexHull.add(second_);
        convexHull.add(third_);
        points_.removeAt(index_);
        points_.removeAt(CustList.SECOND_INDEX);
        points_.removeAt(CustList.FIRST_INDEX);
        EqList<CustPoint> all_ = new EqList<CustPoint>();
        all_.add(first_);
        all_.add(second_);
        all_.add(third_);
//        all_.addAll(points_);
        Polygon p_ = convexHull.getConvexHull();
        for (CustPoint c: points_) {
            CustList<Triangle> del_ = new CustList<Triangle>();
            CustList<Triangle> nearlyDel_ = new CustList<Triangle>();
            for (Triangle t: triangles) {
//                if (t.isInCircum(c)) {
//                    del_.add(t);
//                }
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
                    if (vOne_.det(vTwo_) == 0) {
                        continue;
                    }
                    triangles.add(toBeIns_);
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
                                if (vOne_.det(vTwo_) != 0) {
                                    triangles.add(toBeIns_);
                                }
                            } else {
                                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                                    if (p_.get(i) == e.getFirst() && p_.get((i+1)%len_) == e.getSecond()) {
                                        indexPt_ = i;
                                        break;
                                    } else if (p_.get(i) == e.getSecond() && p_.get((i+1)%len_) == e.getFirst()) {
                                        indexPt_ = i;
                                        break;
                                    }
                                }
                                p_.add((indexPt_+1)%len_,c);
                            }
//                            for (Edge f: copy_.getEdges()) {
//                                if (e.isSame(f)) {
//                                    Triangle toBeIns_ = new Triangle(e.getFirst(), e.getSecond(), c);
//                                    triangles.add(toBeIns_);
//                                    break;
//                                }
//                            }
                        }
                    }
                    all_.add(c);
                    convexHull.add(c);
                    if (triangles.size() < Triangle.NB_POINTS) {
                        p_ = convexHull.getConvexHull();
                        continue;
                    }
                    int bef_ = indexPt_;
                    int befBef_ = indexPt_ - 1;
                    if (befBef_ < 0) {
                        befBef_ = len_ - 1;
                    }
                    int next_ = (indexPt_+2)%len_;
                    int nextNext_ = (indexPt_+Triangle.NB_POINTS)%len_;
                    Edge eOne_ = new Edge(p_.get(befBef_), c);
                    Edge eTwo_ = new Edge(p_.get(nextNext_), c);
                    if (!p_.intersectEdge(eOne_)) {
                        Triangle toBeIns_ = new Triangle(c, p_.get(befBef_), p_.get(bef_));
                        VectTwoDims vOne_ = new VectTwoDims(p_.get(befBef_), p_.get(bef_));
                        VectTwoDims vTwo_ = new VectTwoDims(p_.get(befBef_), c);
                        if (vOne_.det(vTwo_) != 0) {
                            triangles.add(toBeIns_);
                        }
                    }
                    if (!p_.intersectEdge(eTwo_)) {
                        Triangle toBeIns_ = new Triangle(c, p_.get(nextNext_), p_.get(next_));
                        VectTwoDims vOne_ = new VectTwoDims(p_.get(nextNext_), p_.get(next_));
                        VectTwoDims vTwo_ = new VectTwoDims(p_.get(nextNext_), c);
                        if (vOne_.det(vTwo_) != 0) {
                            triangles.add(toBeIns_);
                        }
                    }
//                    for (Triangle t: p_.getTriangles()) {
//                        triangles.add(t);
//                    }
                    p_ = convexHull.getConvexHull();
//                    CustList<Edge> hull_ = p_.getEdges();
//                    CustList<CustPoint> pts_ = new CustList<CustPoint>();
//                    int len_ = p_.size();
//                    for (int i = CustList.FIRST_INDEX; i < len_; i++) {
//                        CustPoint c_ = p_.get(i);
//                        boolean contains_ = false;
//                        for (Edge e: hull_) {
//                            if (e.intersectNotContainsBound(new Edge(c, c_))) {
//                                contains_ = true;
//                                break;
//                            }
//                        }
//                        if (!contains_) {
//                            pts_.add(c_);
//                        }
//                    }
//                    edges_.add(new Edge(pts_.first(), pts_.get(1)));
//                    int lenPts_ = pts_.size() - 1;
//                    for (int i = CustList.SECOND_INDEX; i < lenPts_; i++) {
//                        edges_.add(new Edge(pts_.get(i), pts_.get(i+1)));
//                    }
//                    removeDuplicates(edges_);
//                    for (Edge e: edges_) {
//                        Triangle toBeIns_ = new Triangle(e.getFirst(), e.getSecond(), c);
//                        VectTwoDims vOne_ = new VectTwoDims(e.getFirst(), e.getSecond());
//                        VectTwoDims vTwo_ = new VectTwoDims(e.getFirst(), c);
//                        if (vOne_.det(vTwo_) == 0) {
//                            continue;
//                        }
//                        triangles.add(toBeIns_);
//                    }
//                    convexHull.add(c);
//                    p_ = convexHull.getConvexHull();
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
                    if (vOne_.det(vTwo_) == 0) {
                        continue;
                    }
                    triangles.add(toBeIns_);
                }
//                CustList<Edge> allAddedEdges_ = new CustList<Edge>();
////                Polygon locPol_ = new Polygon();
////                for (Edge e: edges_) {
////                    locPol_.add(e.getFirst());
////                    locPol_.add(e.getSecond());
////                }
//                for (Edge e: edges_) {
//                    Triangle toBeIns_ = new Triangle(e.getFirst(), e.getSecond(), c);
//                    VectTwoDims vOne_ = new VectTwoDims(e.getFirst(), e.getSecond());
//                    VectTwoDims vTwo_ = new VectTwoDims(e.getFirst(), c);
//                    if (vOne_.det(vTwo_) == 0) {
//                        continue;
//                    }
//                    boolean contains_ = false;
//                    CustList<Edge> addedEdges_ = new CustList<Edge>();
//                    addedEdges_.add(new Edge(e.getFirst(), c));
//                    addedEdges_.add(new Edge(e.getSecond(), c));
//                    CustList<Edge> union_ = new CustList<Edge>(allAddedEdges_);
//                    union_.add(e);
//                    for (Edge g: union_) {
//                        if (e.isSame(g)) {
//                            continue;
//                        }
//                        if (e.intersectNotContainsBound(g)) {
//                            contains_ = true;
//                            break;
//                        }
//                    }
//                    if (contains_) {
//                        continue;
//                    }
//                    for (CustPoint o: all_) {
//                        if (o == e.getFirst()) {
//                            continue;
//                        }
//                        if (o == e.getSecond()) {
//                            continue;
//                        }
//                        if (toBeIns_.isInCircum(o)) {
//                            contains_ = true;
//                            break;
//                        }
//                    }
//                    if (!contains_) {
//                        allAddedEdges_.addAllElts(toBeIns_.getEdges());
//                        removeDuplicates(allAddedEdges_);
//                        triangles.add(toBeIns_);
//                    }
//                }
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

    public void computeIncrRect(EqList<CustPoint> _points) {
        computeIncrRect(_points, true);
    }

    public void computeIncrRect(EqList<CustPoint> _points, boolean _bounds) {
        triangles.clear();
        convexHull.clear();
        edges.clear();
        nextTriangles.clear();
        nextPoints.clear();
        EqList<CustPoint> points_ = new EqList<CustPoint>(_points);
        EqList<CustPoint> bounds_ = new EqList<CustPoint>();
        EqList<CustPoint> pureBounds_ = new EqList<CustPoint>();
        points_.removeDuplicates();
        if (points_.size() < Triangle.NB_POINTS) {
            return;
        }
        int xMax_ = points_.first().getXcoords();
        int xMin_ = points_.first().getXcoords();
        int yMax_ = points_.first().getYcoords();
        int yMin_ = points_.first().getYcoords();
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
//        xMin_--;
//        yMin_--;
//        xMax_++;
//        yMax_++;
        CustPoint firstPoint_ = new CustPoint(xMin_, yMin_);
        CustPoint secondPoint_ = new CustPoint(xMax_, yMin_);
        CustPoint thirdPoint_ = new CustPoint(xMin_, yMax_);
        CustPoint fourthPoint_ = new CustPoint(xMax_, yMax_);
        bounds_.add(firstPoint_);
        bounds_.add(secondPoint_);
        bounds_.add(thirdPoint_);
        bounds_.add(fourthPoint_);
        for (CustPoint c: bounds_) {
            boolean found_ = false;
            for (CustPoint p: points_) {
                if (c.eq(p)) {
                    found_ = true;
                    break;
                }
            }
            if (!found_) {
                pureBounds_.add(c);
            }
            if (_bounds) {
                convexHull.add(c);
            }
        }
        triangles.add(new Triangle(firstPoint_, secondPoint_, thirdPoint_));
        triangles.add(new Triangle(fourthPoint_, secondPoint_, thirdPoint_));
        if (_bounds) {
            for (CustPoint c: points_) {
                insertPoint(c, new CustList<Triangle>());
                convexHull.add(c);
            }
            convexHull = convexHull.getConvexHull();
            nextPoints = calculateNextPoints();
            edges = evaluateEdges(true);
            nextTriangles = calculateNextTriangles();
            return;
        }
        EqList<CustPoint> addedPoints_ = new EqList<CustPoint>();
        for (CustPoint c: points_) {
            if (c.getXcoords() == xMin_) {
                insertPoint(c, getBadTriangles(pureBounds_, _bounds));
                addedPoints_.add(c);
                convexHull.add(c);
            } else if (c.getXcoords() == xMax_) {
                insertPoint(c, getBadTriangles(pureBounds_, _bounds));
                addedPoints_.add(c);
                convexHull.add(c);
            } else if (c.getYcoords() == yMin_) {
                insertPoint(c, getBadTriangles(pureBounds_, _bounds));
                addedPoints_.add(c);
                convexHull.add(c);
            } else if (c.getYcoords() == yMax_) {
                insertPoint(c, getBadTriangles(pureBounds_, _bounds));
                addedPoints_.add(c);
                convexHull.add(c);
            }
        }
        points_.removeAllElements(addedPoints_);
        addedPoints_.clear();
        Polygon allPoints_ = new Polygon();
        for (CustPoint c: points_) {
            allPoints_.add(c);
        }
        Polygon h_ = allPoints_.getConvexHull();
        for (CustPoint c: allPoints_) {
            boolean add_ = false;
            for (Edge e: h_.getEdges()) {
                if (e.containsPoint(c)) {
                    add_ = true;
                    break;
                }
            }
            if (!add_) {
                continue;
            }
            insertPoint(c, getBadTriangles(pureBounds_, _bounds));
            addedPoints_.add(c);
            convexHull.add(c);
        }
        points_.removeAllElements(addedPoints_);
        CustList<Triangle> badTrianles_ = getBadTriangles(pureBounds_, _bounds);
        triangles.removeAllElements(badTrianles_);
//        Polygon p_ = convexHull.getConvexHull();
        for (CustPoint c: points_) {
            insertPoint(c, new CustList<Triangle>());
//            convexHull.add(c);
//            if (!Polygon.containsInside(p_, c)) {
//                convexHull.add(c);
//                p_ = convexHull.getConvexHull();
//            }
        }
//        convexHull = p_;
//        convexHull = convexHull.getConvexHull();
        convexHull = convexHull.getConvexHull();
        nextPoints = calculateNextPoints();
        edges = evaluateEdges(true);
        nextTriangles = calculateNextTriangles();
    }

    private CustList<Triangle> getBadTriangles(EqList<CustPoint> _pureBounds, boolean _bounds) {
        CustList<Triangle> badTrianles_ = new CustList<Triangle>();
        if (_bounds) {
            return badTrianles_;
        }
        for (Triangle t: triangles) {
            boolean found_ = false;
            for (CustPoint p: t.getPoints()) {
                for (CustPoint b: _pureBounds) {
                    if (p.eq(b)) {
                        found_ = true;
                        break;
                    }
                }
                if (found_) {
                    break;
                }
            }
            if (found_) {
                badTrianles_.add(t);
            }
        }
        return badTrianles_;
    }

    public void insertPoint(CustPoint _c, CustList<Triangle> _t) {
        CustList<Triangle> del_ = new CustList<Triangle>();
        CustList<Triangle> nearlyDel_ = new CustList<Triangle>();
        for (Triangle t: triangles) {
//                if (t.isInCircum(c)) {
//                    del_.add(t);
//                }
            if (t.isInCircum(_c)) {
                del_.add(t);
            } else if (t.isInCircumBorder(_c)) {
                nearlyDel_.add(t);
            } else {
                for (Triangle o: _t) {
                    if (t == o) {
                        del_.add(t);
                        break;
                    }
                }
            }
        }
//            Polygon hullPoints_ = new Polygon();
//            for (Triangle t: del_) {
//                for (CustPoint p: t.getPoints()) {
//                    hullPoints_.add(p);
//                }
//            }
//            for (Triangle t: nearlyDel_) {
//                for (CustPoint p: t.getPoints()) {
//                    hullPoints_.add(p);
//                }
//            }
        CustList<Edge> edges_ = new CustList<Edge>();
//        CustList<Edge> keepEdges_ = new CustList<Edge>();
//        for (Triangle t: del_) {
//            edges_.addAll(t.getEdges());
//        }
//        for (Triangle t: nearlyDel_) {
//            edges_.addAll(t.getEdges());
//        }
//        CustList<Edge> edges_;
//        edges_ = new CustList<Edge>();
//        CustList<Edge> keepEdges_ = new CustList<Edge>();
        for (Triangle t: del_) {
            edges_.addAllElts(t.getEdges());
        }
        for (Triangle t: nearlyDel_) {
            edges_.addAllElts(t.getEdges());
        }
        for (int j = edges_.size() - 2; j >= 0; j--) {
            for (int k = edges_.size() - 1; k >= j + 1; k--) {
                if (k < edges_.size() && edges_.get(j).isSame(edges_.get(k))) {
                    edges_.removeAt(j);
                    edges_.removeAt(k-1);
                }
            }
        }
//        for (Edge e: edges_) {
//            boolean same_ = false;
//            for (Edge f: edges_) {
//                if (e == f) {
//                    continue;
//                }
//                if (e.isSame(f)) {
//                    same_ = true;
//                    break;
//                }
//            }
//            if (!same_) {
//                keepEdges_.add(e);
//            }
//        }
//            edges_.clear();
//            for (Triangle t: triangles) {
//                edges_.addAll(t.getEdges());
//            }
//            removeDuplicates(edges_);
        triangles.removeAllElements(del_);
        triangles.removeAllElements(nearlyDel_);
        for (Edge e: edges_) {
            Triangle toBeIns_ = new Triangle(e.getFirst(), e.getSecond(), _c);
            VectTwoDims vOne_ = new VectTwoDims(e.getFirst(), e.getSecond());
            VectTwoDims vTwo_ = new VectTwoDims(e.getFirst(), _c);
            if (vOne_.det(vTwo_) == 0) {
                continue;
            }
            triangles.add(toBeIns_);
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
//        CustPoint second_ = points_.get(1);
//        VectTwoDims v_ = new VectTwoDims(first_,second_);
//        int index_ = Triangle.NB_POINTS - 1;
//        while (index_ < points_.size()) {
//            VectTwoDims vLoc_ = new VectTwoDims(first_,points_.get(index_));
//            if (v_.det(vLoc_) != 0) {
//                break;
//            }
//            index_ ++;
//        }
//        if (index_ >= points_.size()) {
//            return;
//        }
//        CustPoint third_ = points_.get(index_);
//        triangles.add(new Triangle(first_, second_, third_));
//        convexHull.add(first_);
//        convexHull.add(second_);
//        convexHull.add(third_);
//        points_.removeAt(index_);
//        points_.removeAt(CustList.SECOND_INDEX);
//        points_.removeAt(CustList.FIRST_INDEX);
        EqList<CustPoint> all_ = new EqList<CustPoint>(visited_);
//        all_.add(first_);
//        all_.add(second_);
//        all_.add(third_);
//        all_.addAll(points_);
        Polygon p_ = convexHull.getConvexHull();
        for (CustPoint c: points_) {
            CustList<Triangle> del_ = new CustList<Triangle>();
            CustList<Triangle> nearlyDel_ = new CustList<Triangle>();
            for (Triangle t: triangles) {
//                if (t.isInCircum(c)) {
//                    del_.add(t);
//                }
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
        while (true) {
            if(i_ >= _edges.size()) {
                break;
            }
            int j_ = i_;
            j_++;
            while (true) {
                if (j_ >= _edges.size()) {
                    break;
                }
                if (_edges.get(i_).isSame(_edges.get(j_))) {
                    _edges.removeAt(j_);
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
            index_ ++;
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
        xMin_ --;
        yMin_ --;
        xMax_ ++;
        yMax_ ++;
        CustPoint firstPoint_ = new CustPoint(xMin_, yMin_);
        CustPoint secondPoint_ = new CustPoint(xMax_ + xMax_ - xMin_, yMin_);
        CustPoint thirdPoint_ = new CustPoint(xMin_, yMax_ + yMax_ - yMin_);
        superTriangle_ = new Triangle(firstPoint_, secondPoint_, thirdPoint_);
        triangles.add(superTriangle_);
        points_.removeAt(index_);
        points_.removeAt(CustList.SECOND_INDEX);
        points_.removeAt(CustList.FIRST_INDEX);
        EqList<CustPoint> all_ = new EqList<CustPoint>();
        all_.add(first_);
        all_.add(second_);
        all_.add(third_);
//        all_.addAll(points_);
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
//                    VectTwoDims vOne_ = new VectTwoDims(e.getFirst(), e.getSecond());
//                    VectTwoDims vTwo_ = new VectTwoDims(e.getFirst(), c);
//                    if (vOne_.det(vTwo_) == 0) {
//                        continue;
//                    }
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
//            for (Triangle t: triangles) {
//                edges_.addAll(t.getEdges());
//            }
//            removeDuplicates(edges_);
//            triangles.removeAllElements(del_);
//            triangles.removeAllElements(nearlyDel_);
//            triangles.add(superTriangle_);
//            CustList<Edge> edgesRem_ = new CustList<Edge>(edges_);
            //ADDED END
//            CustList<Triangle> add_ = new CustList<Triangle>();
//            for (Edge e: edges_) {
//                Triangle toBeIns_ = new Triangle(e.getFirst(), e.getSecond(), c);
//                VectTwoDims vOne_ = new VectTwoDims(e.getFirst(), e.getSecond());
//                VectTwoDims vTwo_ = new VectTwoDims(e.getFirst(), c);
//                if (vOne_.det(vTwo_) == 0) {
//                    continue;
//                }
//                boolean contains_ = false;
//                CustList<Triangle> union_ = new CustList<Triangle>();
//                union_.addAllElts(triangles);
//                union_.addAllElts(add_);
//                CustList<Edge> edgesLoc_ = new CustList<Edge>();
//                edgesLoc_.add(new Edge(e.getFirst(), c));
//                edgesLoc_.add(new Edge(e.getSecond(), c));
//                for (Triangle t: union_) {
//                    if (t == superTriangle_) {
//                        continue;
//                    }
//                    for (Edge f: t.getEdges()) {
//                        for (Edge h: edgesLoc_) {
//                            if (f.intersectNotContainsBound(h)) {
//                                contains_ = true;
//                                break;
//                            }
//                        }
//                        if (contains_) {
//                            break;
//                        }
//                    }
//                    if (contains_) {
//                        break;
//                    }
//                }
//                if (contains_) {
//                    continue;
//                }
//                for (CustPoint o: all_) {
//                    if (o == e.getFirst()) {
//                        continue;
//                    }
//                    if (o == e.getSecond()) {
//                        continue;
//                    }
//                    if (toBeIns_.isInCircum(o)) {
//                        contains_ = true;
//                        break;
//                    }
//                }
//                if (!contains_) {
////                    triangles.add(toBeIns_);
//                    add_.add(toBeIns_);
//                }
//            }
//            triangles.addAllElts(add_);
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
//            for (Edge e: b.getEdges()) {
//                boolean convexHull_ = false;
//                for (Edge h: convexHull.getEdges()) {
//                    if (e.isSame(h)) {
//                        convexHull_ = true;
//                        break;
//                    }
//                }
//                if (convexHull_) {
//                    continue;
//                }
//                int nb_ = 0;
//                for (Edge f: allEdges_) {
//                    if (e.isSame(f)) {
//                        nb_++;
//                    }
//                }
//                if (nb_ == 1) {
//                    badTriangles_.add(b);
//                    break;
//                }
//            }
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

//    private boolean addEdge(CustList<Triangle> _badTriangles, Triangle _triangle,
//            Edge _edge) {
//        boolean addEdge_ = true;
//        for (Triangle o: _badTriangles) {
//            if (o == _triangle) {
//                continue;
//            }
//            boolean break_ = false;
//            for (Edge e_: o.getEdges()) {
//                if (_edge.isSame(e_)) {
//                    addEdge_ = false;
//                    break_ = true;
//                    break;
//                }
//            }
//            if (break_) {
//                break;
//            }
//        }
//        return addEdge_;
//    }

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
//        xMin_ --;
//        yMin_ --;
//        xMax_ ++;
//        yMax_ ++;
//        CustPoint firstPoint_ = new CustPoint(xMin_, yMin_);
//        CustPoint secondPoint_ = new CustPoint(xMax_ + xMax_ - xMin_, yMin_);
//        CustPoint thirdPoint_ = new CustPoint(xMin_, yMax_ + yMax_ - yMin_);
//        superTriangle_ = new Triangle(firstPoint_, secondPoint_, thirdPoint_);
//        triangles.add(superTriangle_);
//        CustList<Triangle> badTriangles_ = new CustList<Triangle>();
//        for (Triangle t: triangles) {
//            if (t.isInCircum(_pt)) {
//                badTriangles_.add(t);
//            }
//        }
//        CustList<Edge> edges_;
//        edges_ = new CustList<Edge>();
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
//        badTriangles_ = new CustList<Triangle>();
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
                                nbPoints_ ++;
                            }
                            if (p == one_) {
                                nbPoints_ ++;
                            }
                            if (p == two_) {
                                nbPoints_ ++;
                            }
                        }
                        if (nbPoints_ == Triangle.NB_POINTS) {
                            CompactPlanePoint c_;
                            c_ = t.getCircumCenter();
                            x_ = (int) (c_.getPair().getFirst() / c_.getCommon());
                            y_ = (int) (c_.getPair().getSecond() / c_.getCommon());
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
                                nbPoints_ ++;
                            }
                            if (p == one_) {
                                nbPoints_ ++;
                            }
                            if (p == two_) {
                                nbPoints_ ++;
                            }
                        }
                        if (nbPoints_ == Triangle.NB_POINTS) {
                            CompactPlanePoint c_;
                            c_ = t.getCircumCenter();
                            x_ = (int) (c_.getPair().getFirst() / c_.getCommon());
                            y_ = (int) (c_.getPair().getSecond() / c_.getCommon());
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
                            nbPoints_ ++;
                        }
                        if (p == one_) {
                            nbPoints_ ++;
                        }
                        if (p == two_) {
                            nbPoints_ ++;
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

    IdMap<CustPoint, IdList<CustPoint>> calculateNextPoints() {
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
//            CustList<CustPoint> next_ = new CustList<CustPoint>();
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
//                    boolean contained_ = containsPoint(next_, n);
//                    if (!contained_) {
//                        next_.add(n);
//                    }
                }
            }
            IdList<CustPoint> next_ = all_.getKeys();
            CustPoint first_ = next_.first();
//            CustList<CustPoint> once_ = all_.getKeys(CustList.ONE_ELEMENT);
            EqList<CustPoint> once_ = new EqList<CustPoint>();
            for (EntryCust<CustPoint, Integer> e: all_.entryList()) {
                if (!Numbers.eq(e.getValue().intValue(), CustList.ONE_ELEMENT)) {
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
                if (sOne_.cmp(sTwo_) == CustList.NO_SWAP_SORT) {
                    first_ = eTwo_;
                } else {
                    first_ = eOne_;
                }
            }
            SortableCustList<SitePoint> sites_ = new SortableCustList<SitePoint>();
            VectTwoDims v_ = new VectTwoDims(p, first_);
            for (CustPoint n: next_) {
                sites_.add(new SitePoint(n, p, v_));
            }
            sites_.sort();
            next_.clear();
            for (SitePoint s: sites_) {
                next_.add(s.getPoint());
            }
            id_.put(p, next_);
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

    public CustList<Triangle> getNextTriangles(Triangle _t) {
        CustList<Triangle> next_ = new CustList<Triangle>();
        for (Triangle t: triangles) {
            if (t == _t) {
                continue;
            }
            boolean add_ = false;
            for (CustPoint o: t.getPoints()) {
                for (CustPoint p: _t.getPoints()) {
                    if (o == p) {
                        add_ = true;
                        break;
                    }
                }
                if (add_) {
                    break;
                }
            }
            if (add_) {
                next_.add(t);
            }
        }
        return next_;
    }
}
