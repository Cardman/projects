package code.maths.geo;

import code.maths.Rate;

public final class DeltaSelectionUnit {

    private Rate locx = Rate.zero();

    private Rate locy = Rate.zero();

    private Rate destx = Rate.zero();

    private Rate desty = Rate.zero();

    private boolean selected;
    public static RatePoint moveCamera(RatePoint _cam, Rate _x, Rate _y, Rate _xBound, Rate _yBound, Rect _all) {
        if (_x.isZero() && _y.isZero()) {
            return _cam;
        }
        Rate xTopLeftScreen_ = _cam.getXcoords();
        Rate yTopLeftScreen_ = _cam.getYcoords();
        if (_x.isZero()) {
            if (_y.isZeroOrLt()) {
                Rate nexty_ = Rate.plus(yTopLeftScreen_, _y);
                if (nexty_.isZeroOrLt()) {
                    return new RatePoint(xTopLeftScreen_,Rate.zero());
                }
                return new RatePoint(xTopLeftScreen_, nexty_);
            }
            Rate nexty_ = Rate.plus(yTopLeftScreen_, Rate.plus(_yBound,_y));
            if (Rate.greaterEq(nexty_,_all.getHeight())) {
                return new RatePoint(xTopLeftScreen_, Rate.minus(_all.getHeight(), _yBound));
            }
            return new RatePoint(xTopLeftScreen_, Rate.plus(yTopLeftScreen_, _y));
        }
        if (_x.isZeroOrLt()) {
            Rate nextx_ = Rate.plus(xTopLeftScreen_, _x);
            if (nextx_.isZeroOrLt()) {
                return new RatePoint(Rate.zero(),yTopLeftScreen_);
            }
            return new RatePoint(nextx_, yTopLeftScreen_);
        }
        Rate nextx_ = Rate.plus(xTopLeftScreen_, Rate.plus(_xBound,_x));
        if (Rate.greaterEq(nextx_,_all.getWidth())) {
            return new RatePoint(Rate.minus(_all.getWidth(), _xBound),yTopLeftScreen_);
        }
        return new RatePoint(Rate.plus(xTopLeftScreen_, _x), yTopLeftScreen_);
    }
    public boolean isOutside(Rect _p, Delta _d, Rect _data) {
        Rate xULeftTop_ = getLocx();
        Rate yULeftTop_ = getLocy();
        Rate xURightBottom_ = Rate.plus(xULeftTop_,_data.getWidth());
        Rate yURightBottom_ = Rate.plus(yULeftTop_,_data.getHeight());
        Rate left_ = Rate.minus(xULeftTop_, _d.getDx());
        Rate top_ = Rate.minus(yULeftTop_, _d.getDy());
        Rate right_ = Rate.minus(xURightBottom_, _d.getDx());
        Rate bottom_ = Rate.minus(yURightBottom_, _d.getDy());
        return !_p.intersects(new Rect(left_, top_, Rate.minus(right_, left_), Rate.minus(bottom_, top_)));
//        if (_p.containsInside(new RatePoint(left_, top_))) {
//            return false;
//        }
//        if (_p.containsInside(new RatePoint(right_, top_))) {
//            return false;
//        }
//        if (_p.containsInside(new RatePoint(right_, bottom_))) {
//            return false;
//        }
//        return !_p.containsInside(new RatePoint(left_, bottom_));
    }

    public Delta getDelta() {
        Rate curX_ = getLocx();
        Rate curY_ = getLocy();
        Rate destX_ = getDestx();
        Rate destY_ = getDesty();
        Rate deltax_ = Rate.minus(destX_, curX_);
        Rate deltay_ = Rate.minus(destY_, curY_);
        Rate realDeltax_ = Rate.zero();
        Rate realDeltay_ = Rate.zero();
        if (deltax_.isZero() && deltay_.isZero()) {
            Delta d_ = new Delta();
            d_.setDx(realDeltax_);
            d_.setDy(realDeltay_);
            return d_;
        }
        if (Rate.eq(deltax_, deltay_)) {
            if (deltay_.signum().ll() < 0) {
                realDeltax_ = Rate.minusOne();
                realDeltay_ = Rate.minusOne();
            } else {
                realDeltax_ = Rate.one();
                realDeltay_ = Rate.one();
            }
            Delta d_ = new Delta();
            d_.setDx(realDeltax_);
            d_.setDy(realDeltay_);
            return d_;
        }
        if (Rate.eq(deltax_.opposNb(), deltay_)) {
            if (deltay_.signum().ll() < 0) {
                realDeltax_ = Rate.one();
                realDeltay_ = Rate.minusOne();
            } else {
                realDeltax_ = Rate.minusOne();
                realDeltay_ = Rate.one();
            }
            Delta d_ = new Delta();
            d_.setDx(realDeltax_);
            d_.setDy(realDeltay_);
            return d_;
        }
        if (deltax_.isZero()) {
            return deltay(deltay_);
        }
        if (deltay_.isZero()) {
            return deltax(deltax_);
        }
        if (Rate.strLower(deltax_.absNb(),deltay_.absNb())) {
            return deltay(deltay_);
        }
        return deltax(deltax_);
    }

    private Delta deltay(Rate _deltay) {
        Rate realDeltax_ = Rate.zero();
        Rate realDeltay_;
        if (_deltay.signum().ll() < 0) {
            realDeltay_ = Rate.minusOne();
        } else {
            realDeltay_ = Rate.one();
        }
        Delta d_ = new Delta();
        d_.setDx(realDeltax_);
        d_.setDy(realDeltay_);
        return d_;
    }

    private Delta deltax(Rate _deltax) {
        Rate realDeltay_ = Rate.zero();
        Rate realDeltax_;
        if (_deltax.signum().ll() < 0) {
            realDeltax_ = Rate.minusOne();
        } else {
            realDeltax_ = Rate.one();
        }
        Delta d_ = new Delta();
        d_.setDx(realDeltax_);
        d_.setDy(realDeltay_);
        return d_;
    }

    public Rate getLocx() {
        return locx;
    }

    public void setLocx(Rate _x) {
        locx = _x;
    }

    public Rate getLocy() {
        return locy;
    }

    public void setLocy(Rate _y) {
        locy = _y;
    }

    public Rate getDestx() {
        return destx;
    }

    public void setDestx(Rate _destx) {
        destx = _destx;
    }

    public Rate getDesty() {
        return desty;
    }

    public void setDesty(Rate _desty) {
        desty = _desty;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }
}
