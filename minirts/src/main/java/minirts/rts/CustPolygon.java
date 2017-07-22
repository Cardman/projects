package minirts.rts;

import java.util.Iterator;

import code.util.CustList;

public class CustPolygon implements Iterable<CustPoint>{

    private CustList<CustPoint> points = new CustList<CustPoint>();

    @Override
    public Iterator<CustPoint> iterator() {
        return points.iterator();
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

    public void remove(int _index) {
        points.remove(_index);
    }

    public void clear() {
        points.clear();
    }

    @Override
    public String toString() {
        return points.toString();
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
    public CustPolygon getConvexHull() {
        CustPolygon p_ = new CustPolygon();
        if (points.isEmpty()) {
            return p_;
        }
        CustPoint cust_ = points.get(0);
        for (CustPoint p: points) {
            if (p.getX() < cust_.getX()) {
                cust_ = p;
                continue;
            }
            if (p.getX() == cust_.getX() && p.getY() < cust_.getY()) {
                cust_ = p;
                continue;
            }
        }
        CustPoint endPoint_ = null;
        int nbVertices_ = points.size();
        do {
            p_.points.add(cust_);
            endPoint_ = points.get(0);
            for (int j = 1; j < nbVertices_; j++) {
                if (endPoint_ == cust_) {
                    endPoint_ = points.get(j);
                } else {
//                    CustPoint a_ = endPoint_;
                    CustPoint b_ = p_.points.get(p_.points.size() - 1);
                    CustPoint affineSegment_ = substract(b_, endPoint_);
                    CustPoint affinePoint_ = substract(points.get(j), endPoint_);
                    //points.get(j)
                    LinearDirection currentSide_ = getSide(affineSegment_, affinePoint_);
                    if (currentSide_ == LinearDirection.LEFT) {
                        endPoint_ = points.get(j);
                    }
                }
            }
            cust_ = endPoint_;
        } while (endPoint_ != p_.points.get(0));
        return p_;
    }

    public boolean contains(CustPoint _point) {
        LinearDirection previousSide_ = LinearDirection.NONE;
        int nbVertices_ = points.size();
        for (int n = 0; n < nbVertices_; n++) {
            CustPoint a_ = points.get(n);
            CustPoint b_ = points.get((n+1)%nbVertices_);
            CustPoint affineSegment_ = substract(b_, a_);
            CustPoint affinePoint_ = substract(_point, a_);
            LinearDirection currentSide_ = getSide(affineSegment_, affinePoint_);
            if (currentSide_ == LinearDirection.NONE) {
                return false;
            } else if (previousSide_ == LinearDirection.NONE) {
                previousSide_ = currentSide_;
            } else if (previousSide_ != currentSide_) {
                return false;
            }
        }
        return true;
    }

    static LinearDirection getSide(CustPoint _a,CustPoint _b){
        int x_ = scalProduct(_a, _b);
        if (x_ < 0) {
            return LinearDirection.LEFT;
        } else if (x_ > 0) {
            return LinearDirection.RIGHT;
        }
        return LinearDirection.NONE;
    }

    static CustPoint substract(CustPoint _a,CustPoint _b){
        return new CustPoint(_a.getX()-_b.getX(), _a.getY()-_b.getY());
    }

    static int scalProduct(CustPoint _a, CustPoint _b) {
        return _a.getX()*_b.getY()-_a.getY()*_b.getX();
    }
}
