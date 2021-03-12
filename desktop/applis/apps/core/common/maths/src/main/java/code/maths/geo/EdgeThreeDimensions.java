package code.maths.geo;
import code.maths.Rate;
import code.util.CustList;
import code.util.core.IndexConstants;


public final class EdgeThreeDimensions {

    private RatePointThreeDims first;

    private RatePointThreeDims second;

    public EdgeThreeDimensions(RatePointThreeDims _first, RatePointThreeDims _second) {
        first = _first;
        second = _second;
    }

    public boolean intersection(EdgeThreeDimensions _e2) {
        VectThreeDims a_ = new VectThreeDims(getFirst(), getSecond());
        VectThreeDims b_ = new VectThreeDims(_e2.getFirst(), _e2.getSecond());
        VectThreeDims standard_ = a_.vectProd(b_);
        Rate det_ = standard_.quickDet(a_, b_);
//        boolean linear_ = det_.isZero();
        boolean linear_ = det_.isZero();
        if (!linear_) {
            VectThreeDims vectStrOne_ = new VectThreeDims(getFirst().getXcoords(),
                getFirst().getYcoords(),
                getFirst().getZcoords());
            VectThreeDims vectStrTwo_ = new VectThreeDims(_e2.getFirst().getXcoords(),
                _e2.getFirst().getYcoords(),
                _e2.getFirst().getZcoords());
            Rate scale_ = vectStrOne_.scal(standard_);
            if (!scale_.eq(vectStrTwo_.scal(standard_))) {
                return false;
            }
        } else {
            VectThreeDims c_ = new VectThreeDims(getFirst(), _e2.getFirst());
            standard_ = a_.vectProd(c_);
            det_ = standard_.quickDet(a_, c_);
//            if (!det_.isZero()) {
//                return false;
//            }
            if (!det_.isZero()) {
                return false;
            }
        }
        CustList<RatePointThreeDims> points_ = new CustList<RatePointThreeDims>();
        points_.add(first);
        points_.add(second);
        points_.add(_e2.first);
        points_.add(_e2.second);
        if (containsPoint(_e2.second)) {
            return true;
        }
        if (containsPoint(_e2.first)) {
            return true;
        }
        if (_e2.containsPoint(first)) {
            return true;
        }
        if (_e2.containsPoint(second)) {
            return true;
        }
        return loop(points_);
//        if (!linear_) {
////            added_ = new Vect();
////            added_.add(new Rate(standard_.getDeltax()));
////            added_.add(new Rate(standard_.getDeltay()));
////            added_.add(new Rate(standard_.getDeltaz()));
////            Rate scale_ = vectStrOne_.scale(added_);
////            if (!Rate.eq(scale_, vectStrThree_.scale(added_))) {
////                return false;
////            }
//            CustList<CustPointThreeDims> points_ = new CustList<CustPointThreeDims>();
//            points_.add(first);
//            points_.add(second);
//            points_.add(_e2.first);
//            points_.add(_e2.second);
//            if (containsPoint(_e2.second)) {
//                return true;
//            }
//            if (_e2.containsPoint(first)) {
//                return true;
//            }
//            if (_e2.containsPoint(second)) {
//                return true;
//            }
//            if (containsPoint(_e2.first)) {
//                return true;
//            }
//            int index_ = CustList.FIRST_INDEX;
//            for (CustPointThreeDims p: points_) {
//                CustList<CustPointThreeDims> others_ = new CustList<CustPointThreeDims>();
//                int next_;
//                int nextOthOne_;
//                int nextOthTwo_;
//                if (index_ <= CustList.SECOND_INDEX) {
//                    if (index_ == CustList.FIRST_INDEX) {
//                        next_ = CustList.SECOND_INDEX;
//                    } else {
//                        next_ = CustList.FIRST_INDEX;
//                    }
//                    nextOthOne_ = CustList.SECOND_INDEX + CustList.ONE_ELEMENT;
//                    nextOthTwo_ = nextOthOne_ + CustList.ONE_ELEMENT;
//                } else {
//                    if (index_ == CustList.SECOND_INDEX + CustList.ONE_ELEMENT) {
//                        next_ = index_ + CustList.ONE_ELEMENT;
//                    } else {
//                        next_ = index_ - CustList.ONE_ELEMENT;
//                    }
//                    nextOthOne_ = CustList.FIRST_INDEX;
//                    nextOthTwo_ = CustList.SECOND_INDEX;
//                }
//                CustPointThreeDims o_ = points_.get(next_);
//                others_.add(points_.get(nextOthOne_));
//                others_.add(points_.get(nextOthTwo_));
//                SortableList<SitePointThreeDims> sites_ = new SortableList<SitePointThreeDims>();
//                VectThreeDims v_ = new VectThreeDims(p, o_);
//                for (CustPointThreeDims n: others_) {
//                    sites_.add(new SitePointThreeDims(n, p, v_));
//                }
//                sites_.sort();
//                if (sites_.first().getNumber() >= SitePoint.QUAD_THREE) {
//                    return false;
//                }
//                if (sites_.last().getNumber() < SitePoint.QUAD_THREE) {
//                    return false;
//                }
//                index_ ++;
//            }
//            return true;
//        }
//        VectThreeDims c_ = new VectThreeDims(getFirst(), _e2.getFirst());
//        standard_ = a_.vectProd(c_);
//        det_ = standard_.det(a_, c_);
//        if (!det_.isZero()) {
//            return false;
//        }
//        Vect vectOne_ = new Vect();
//        vectOne_.add(Rate.minus(new Rate(getFirst().getXcoords()), new Rate(_e2.getFirst().getXcoords())));
//        vectOne_.add(Rate.minus(new Rate(getFirst().getYcoords()), new Rate(_e2.getFirst().getYcoords())));
//        vectOne_.add(Rate.minus(new Rate(getFirst().getZcoords()), new Rate(_e2.getFirst().getZcoords())));
//        Vect vectTwo_ = new Vect();
//        vectTwo_.add(Rate.minus(new Rate(getFirst().getXcoords()), new Rate(_e2.getSecond().getXcoords())));
//        vectTwo_.add(Rate.minus(new Rate(getFirst().getYcoords()), new Rate(_e2.getSecond().getYcoords())));
//        vectTwo_.add(Rate.minus(new Rate(getFirst().getZcoords()), new Rate(_e2.getSecond().getZcoords())));
//        if (vectOne_.scale(vectTwo_).isZeroOrLt()) {
//            return true;
//        }
//        vectOne_ = new Vect();
//        vectOne_.add(Rate.minus(new Rate(getSecond().getXcoords()), new Rate(_e2.getFirst().getXcoords())));
//        vectOne_.add(Rate.minus(new Rate(getSecond().getYcoords()), new Rate(_e2.getFirst().getYcoords())));
//        vectOne_.add(Rate.minus(new Rate(getSecond().getZcoords()), new Rate(_e2.getFirst().getZcoords())));
//        vectTwo_ = new Vect();
//        vectTwo_.add(Rate.minus(new Rate(getSecond().getXcoords()), new Rate(_e2.getSecond().getXcoords())));
//        vectTwo_.add(Rate.minus(new Rate(getSecond().getYcoords()), new Rate(_e2.getSecond().getYcoords())));
//        vectTwo_.add(Rate.minus(new Rate(getSecond().getZcoords()), new Rate(_e2.getSecond().getZcoords())));
//        if (vectOne_.scale(vectTwo_).isZeroOrLt()) {
//            return true;
//        }
//        vectOne_ = new Vect();
//        vectOne_.add(Rate.minus(new Rate(_e2.getFirst().getXcoords()), new Rate(getFirst().getXcoords())));
//        vectOne_.add(Rate.minus(new Rate(_e2.getFirst().getYcoords()), new Rate(getFirst().getYcoords())));
//        vectOne_.add(Rate.minus(new Rate(_e2.getFirst().getZcoords()), new Rate(getFirst().getZcoords())));
//        vectTwo_ = new Vect();
//        vectTwo_.add(Rate.minus(new Rate(_e2.getFirst().getXcoords()), new Rate(getSecond().getXcoords())));
//        vectTwo_.add(Rate.minus(new Rate(_e2.getFirst().getYcoords()), new Rate(getSecond().getYcoords())));
//        vectTwo_.add(Rate.minus(new Rate(_e2.getFirst().getZcoords()), new Rate(getSecond().getZcoords())));
//        if (vectOne_.scale(vectTwo_).isZeroOrLt()) {
//            return true;
//        }
////        vectOne_ = new Vect();
////        vectOne_.add(Rate.minus(new Rate(_e2.getSecond().getXcoords()), new Rate(getFirst().getXcoords())));
////        vectOne_.add(Rate.minus(new Rate(_e2.getSecond().getYcoords()), new Rate(getFirst().getYcoords())));
////        vectOne_.add(Rate.minus(new Rate(_e2.getSecond().getZcoords()), new Rate(getFirst().getZcoords())));
////        vectTwo_ = new Vect();
////        vectTwo_.add(Rate.minus(new Rate(_e2.getSecond().getXcoords()), new Rate(getSecond().getXcoords())));
////        vectTwo_.add(Rate.minus(new Rate(_e2.getSecond().getYcoords()), new Rate(getSecond().getYcoords())));
////        vectTwo_.add(Rate.minus(new Rate(_e2.getSecond().getZcoords()), new Rate(getSecond().getZcoords())));
////        if (vectOne_.scale(vectTwo_).isZeroOrLt()) {
////            return true;
////        }
//        return false;
    }

    private static boolean loop(CustList<RatePointThreeDims> _points) {
        int index_ = IndexConstants.FIRST_INDEX;
        for (RatePointThreeDims p: _points) {
            CustList<RatePointThreeDims> others_ = new CustList<RatePointThreeDims>();
            int next_;
            int nextOthOne_;
            int nextOthTwo_;
            if (index_ <= IndexConstants.SECOND_INDEX) {
                next_ = getNextThreeDims(index_);
                nextOthOne_ = IndexConstants.SECOND_INDEX + IndexConstants.ONE_ELEMENT;
                nextOthTwo_ = nextOthOne_ + IndexConstants.ONE_ELEMENT;
            } else {
                next_ = getNextThreeDims2(index_);
                nextOthOne_ = IndexConstants.FIRST_INDEX;
                nextOthTwo_ = IndexConstants.SECOND_INDEX;
            }
            RatePointThreeDims o_ = _points.get(next_);
            others_.add(_points.get(nextOthOne_));
            others_.add(_points.get(nextOthTwo_));
            CustList<Site> sites_ = getSites(p, others_, o_);
            if (noIntersect(sites_)) {
                return false;
            }
            index_++;
        }
        return true;
    }

    private static int getNextThreeDims2(int _index) {
        return Edge.getNext2(_index);
    }

    private static int getNextThreeDims(int _index) {
        return Edge.getNext(_index);
    }

    private static CustList<Site> getSites(RatePointThreeDims _p, CustList<RatePointThreeDims> _others, RatePointThreeDims _o) {
        CustList<Site> sites_ = new CustList<Site>();
        VectThreeDims v_ = new VectThreeDims(_p, _o);
        for (RatePointThreeDims n: _others) {
            sites_.add(new SitePointThreeDims(n, _p, v_));
        }
        sites_.sortElts(new SiteComparing());
        return sites_;
    }

    private static boolean noIntersect(CustList<Site> _sites) {
        return _sites.first().getInfo().getNumber() >= SiteInfo.QUAD_THREE || _sites.last().getInfo().getNumber() < SiteInfo.QUAD_THREE;
    }

    public boolean containsPoint(RatePointThreeDims _c) {
        VectThreeDims one_ = new VectThreeDims(first, _c);
        VectThreeDims two_ = new VectThreeDims(second, _c);
        VectThreeDims standard_ = one_.vectProd(two_);
//        return standard_.quickDet(one_, two_).isZero() && one_.scal(two_) <= 0;
        return standard_.quickDet(one_, two_).isZero() && one_.scal(two_).isZeroOrLt();
    }

//    public boolean intersect(EdgeThreeDimensions _other) {
//        CustList<CustPoint> points_ = new CustList<CustPoint>();
//        points_.add(first);
//        points_.add(second);
//        points_.add(_other.first);
//        points_.add(_other.second);
//        if (containsPoint(_other.second)) {
//            return true;
//        }
//        if (_other.containsPoint(first)) {
//            return true;
//        }
//        if (_other.containsPoint(second)) {
//            return true;
//        }
//        if (containsPoint(_other.first)) {
//            return true;
//        }
//        int index_ = CustList.FIRST_INDEX;
//        for (CustPoint p: points_) {
//            CustList<CustPoint> others_ = new CustList<CustPoint>();
//            int next_;
//            int nextOthOne_;
//            int nextOthTwo_;
//            if (index_ <= CustList.SECOND_INDEX) {
//                if (index_ == CustList.FIRST_INDEX) {
//                    next_ = CustList.SECOND_INDEX;
//                } else {
//                    next_ = CustList.FIRST_INDEX;
//                }
//                nextOthOne_ = CustList.SECOND_INDEX + CustList.ONE_ELEMENT;
//                nextOthTwo_ = nextOthOne_ + CustList.ONE_ELEMENT;
//            } else {
//                if (index_ == CustList.SECOND_INDEX + CustList.ONE_ELEMENT) {
//                    next_ = index_ + CustList.ONE_ELEMENT;
//                } else {
//                    next_ = index_ - CustList.ONE_ELEMENT;
//                }
//                nextOthOne_ = CustList.FIRST_INDEX;
//                nextOthTwo_ = CustList.SECOND_INDEX;
//            }
//            CustPoint o_ = points_.get(next_);
//            others_.add(points_.get(nextOthOne_));
//            others_.add(points_.get(nextOthTwo_));
//            SortableList<SitePoint> sites_ = new SortableList<SitePoint>();
//            VectTwoDims v_ = new VectTwoDims(p, o_);
//            for (CustPoint n: others_) {
//                sites_.add(new SitePoint(n, p, v_));
//            }
//            sites_.sort();
//            if (sites_.first().getNumber() >= SitePoint.QUAD_THREE) {
//                return false;
//            }
//            if (sites_.last().getNumber() < SitePoint.QUAD_THREE) {
//                return false;
//            }
//            index_ ++;
//        }
//        return true;
//    }
//
//    public boolean containsPoint(CustPoint _c) {
//        VectTwoDims one_ = new VectTwoDims(first, _c);
//        VectTwoDims two_ = new VectTwoDims(second, _c);
//        return one_.det(two_) == 0 && one_.scal(two_) <= 0;
//    }

    public RatePointThreeDims getFirst() {
        return first;
    }

    public void setFirst(RatePointThreeDims _first) {
        first = _first;
    }

    public RatePointThreeDims getSecond() {
        return second;
    }

    public void setSecond(RatePointThreeDims _second) {
        second = _second;
    }
}
