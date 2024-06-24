package code.maths.geo;

import code.maths.Rate;

public final class DeltaSelectionUnit {

    private Rate locx = Rate.zero();

    private Rate locy = Rate.zero();

    private Rate destx = Rate.zero();

    private Rate desty = Rate.zero();

    private boolean selected;
    public static RatePoint moveCamera(RatePoint _cam, Rate _x, Rate _y, Rate _xBound, Rate _yBound) {
        Rate deltax_ = Rate.zero();
        Rate deltay_ = Rate.zero();
        Rate xTopLeftScreen_ = _cam.getXcoords();
        Rate yTopLeftScreen_ = _cam.getYcoords();
        boolean out_ = Rate.strLower(_y, yTopLeftScreen_) || Rate.strGreater(_y, Rate.plus(yTopLeftScreen_, _yBound));
        if (Rate.strLower(_x,xTopLeftScreen_)) {
            if (out_) {
                return _cam;
            }
            deltax_ = new Rate(-10);
        } else if (Rate.strGreater(_x, Rate.plus(xTopLeftScreen_, _xBound))) {
            if (out_) {
                return _cam;
            }
            deltax_ = new Rate(10);
        } else if (Rate.strLower(_y, yTopLeftScreen_)) {
            deltay_ = new Rate(-10);
        } else if (Rate.strGreater(_y, Rate.plus(yTopLeftScreen_, _yBound))) {
            deltay_ = new Rate(10);
        }
        return new RatePoint(Rate.plus(xTopLeftScreen_, deltax_), Rate.plus(yTopLeftScreen_, deltay_));
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
