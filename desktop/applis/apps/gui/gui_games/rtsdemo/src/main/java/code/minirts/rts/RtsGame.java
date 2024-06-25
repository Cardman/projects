package code.minirts.rts;

import code.maths.*;
import code.maths.geo.*;
import code.util.*;

public final class RtsGame {

    private final LongMap<Soldier> soldiers = new LongMap<Soldier>();

    private Rate xTopLeftSelect = Rate.zero();
    private Rate yTopLeftSelect = Rate.zero();

    private Rate widthSelect = Rate.one();
    private Rate heightSelect = Rate.one();

//    private int xTopLeftScreen = -100;
//    private int yTopLeftScreen = -100;
//    private Rate xTopLeftScreen = new Rate(100);
//    private Rate yTopLeftScreen = new Rate(100);
    private RatePoint topLeftScreen = new RatePoint(new Rate(100),new Rate(100));

    private boolean added;

    public void loop(RtsDataBase _data) {
        for (EntryCust<Long, Soldier> u: soldiers.entryList()) {
            Soldier u_ = u.getValue();
            Delta d_ = getDelta(u_);
            if (!d_.isMoving()) {
                continue;
            }
            Rate curX_ = u_.getContent().getLocx();
            Rate curY_ = u_.getContent().getLocy();
            Rate nextX_ = Rate.plus(curX_, d_.getDx());
            Rate nextY_ = Rate.plus(curY_, d_.getDy());
            if (isEmpty(u.getKey(), d_, _data)) {
                moveSoldier(u_, nextX_, nextY_);
            }
        }
    }

    public Delta getDelta(Soldier _u) {
        return _u.getContent().getDelta();
//        Rate curX_ = _u.getContent().getLocx();
//        Rate curY_ = _u.getContent().getLocy();
//        Rate deltax_;
//        Rate deltay_;
//        Rate destX_ = _u.getContent().getDestx();
//        Rate destY_ = _u.getContent().getDesty();
//        deltax_ = Rate.minus(destX_, curX_);
//        deltay_ = Rate.minus(destY_, curY_);
//        Rate realDeltax_ = Rate.zero();
//        Rate realDeltay_ = Rate.zero();
//        if (deltax_.isZero() && deltay_.isZero()) {
//            Delta d_ = new Delta();
//            d_.setDx(realDeltax_);
//            d_.setDy(realDeltay_);
//            return d_;
//        }
//        if (Rate.eq(deltax_, deltay_)) {
//            if (deltay_.signum().ll() < 0) {
//                realDeltax_ = Rate.minusOne();
//                realDeltay_ = Rate.minusOne();
//            } else {
//                realDeltax_ = Rate.one();
//                realDeltay_ = Rate.one();
//            }
//        } else if (deltax_.isZero()) {
//            if (deltay_.signum().ll() < 0) {
//                realDeltay_ = Rate.minusOne();
//            } else {
//                realDeltay_ = Rate.one();
//            }
//        } else if (deltay_.isZero()) {
//            if (deltax_.signum().ll() < 0) {
//                realDeltax_ = Rate.minusOne();
//            } else {
//                realDeltax_ = Rate.one();
//            }
//        } else if (Rate.strLower(deltax_.absNb(),deltay_.absNb())) {
//            if (deltay_.signum().ll() < 0) {
//                realDeltay_ = Rate.minusOne();
//            } else {
//                realDeltay_ = Rate.one();
//            }
//        } else {
//            if (deltax_.signum().ll() < 0) {
//                realDeltax_ = Rate.minusOne();
//            } else {
//                realDeltax_ = Rate.one();
//            }
//        }
//        Delta d_ = new Delta();
//        d_.setDx(realDeltax_);
//        d_.setDy(realDeltay_);
//        return d_;
    }

    public Soldier addNewSoldier(Rate _x, Rate _y, RtsDataBase _data, long _next) {
        if (!isEmpty(_x, _y, _data)) {
            added = false;
            return null;
        }
        added = true;
        Soldier soldierLabel_ = new Soldier();
        soldierLabel_.getContent().setLocx(_x);
        soldierLabel_.getContent().setDestx(_x);
        soldierLabel_.getContent().setLocy(_y);
        soldierLabel_.getContent().setDesty(_y);
        soldiers.put(_next,soldierLabel_);
        return soldierLabel_;
    }

    public boolean isEmpty(long _this, Delta _d, RtsDataBase _data) {
        return isEmpty(_this,soldiers.getVal(_this), _d,_data);
    }
    public boolean isEmpty(long _this, Soldier _s, Delta _d, RtsDataBase _data) {
        Rate xThisLeftTop_ = _s.getContent().getLocx();
        Rate yThisLeftTop_ = _s.getContent().getLocy();
        Rect s_ = _data.getSoldierPattern();
//        int xThisRightBottom_ = this_.getLocx() + s_.getWidth();
//        int yThisRightBottom_ = this_.getLocy() + s_.getHeight();
//        int[] xThis_ = new int[]{xThisLeftTop_, xThisRightBottom_, xThisRightBottom_, xThisLeftTop_};
//        int[] yThis_ = new int[]{yThisLeftTop_, yThisLeftTop_, yThisRightBottom_, yThisRightBottom_};
//        Polygon pThis_ = new Polygon(xThis_, yThis_, 4);
        Rect pThis_ = newRect(xThisLeftTop_, yThisLeftTop_, s_.getWidth(), s_.getHeight());
        for (EntryCust<Long, Soldier> u: soldiers.entryList()) {
            if (u.getKey() == _this) {
                continue;
            }
            if (!isOutside(pThis_, u.getValue(), _d, _data)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty(Rate _x, Rate _y, RtsDataBase _data) {
        Rect s_ = _data.getSoldierPattern();
//        int xThisRightBottom_ = _x + s_.getWidth();
//        int yThisRightBottom_ = _y + s_.getHeight();
//        int[] xThis_ = new int[]{xThisLeftTop_, xThisRightBottom_, xThisRightBottom_, xThisLeftTop_};
//        int[] yThis_ = new int[]{yThisLeftTop_, yThisLeftTop_, yThisRightBottom_, yThisRightBottom_};
//        Polygon pThis_ = new Polygon(xThis_, yThis_, 4);
        Rect pThis_ = newRect(_x, _y, s_.getWidth(), s_.getHeight());
        Delta d_ = new Delta();
        for (EntryCust<Long, Soldier> u: soldiers.entryList()) {
            if (!isOutside(pThis_, u.getValue(), d_, _data)) {
                return false;
            }
        }
        return true;
    }

    boolean isOutside(Rect _p, Soldier _u, Delta _d, RtsDataBase _data) {
        return _u.getContent().isOutside(_p,_d,_data.getSoldierPattern());
        //Polygon
//        Rate xULeftTop_ = _u.getContent().getLocx();
//        Rate yULeftTop_ = _u.getContent().getLocy();
//        Rect s_ = _data.getSoldierPattern();
//        Rate xURightBottom_ = Rate.plus(xULeftTop_,s_.getWidth());
//        Rate yURightBottom_ = Rate.plus(yULeftTop_,s_.getHeight());
//        if (_p.containsInside(new RatePoint(Rate.minus(xULeftTop_, _d.getDx()), Rate.minus(yULeftTop_, _d.getDy())))) {
//            return false;
//        }
//        if (_p.containsInside(new RatePoint(Rate.minus(xURightBottom_, _d.getDx()), Rate.minus(yULeftTop_, _d.getDy())))) {
//            return false;
//        }
//        if (_p.containsInside(new RatePoint(Rate.minus(xURightBottom_, _d.getDx()), Rate.minus(yURightBottom_, _d.getDy())))) {
//            return false;
//        }
//        return !_p.containsInside(new RatePoint(Rate.minus(xULeftTop_, _d.getDx()), Rate.minus(yURightBottom_, _d.getDy())));
    }

//    public void selectOrDeselect(RtsDataBase _data) {
//        setRectangle(_first, _last);
//        selectOrDeselectMany(_data);
//    }

    public void selectOrDeselect(RtsDataBase _data) {
//        setRectangle(new Point(_x, _y), new Point(_x, _y));
//        setRectangle(_x, _y);
        selectOrDeselectMany(_data);
//        SoldierPattern s_ = _data.getSoldierPattern();
//        for (Entry<UnitMapKey,Soldier> u: soldiers.entryList()) {
//            Soldier u_ = u.getValue();
//            boolean select_ = true;
//            int x_ = u_.getLocx();
//            int y_ = u_.getLocy();
//            int endx_ = x_ + s_.getWidth();
//            int endy_ = y_ + s_.getHeight();
//            if (_x < x_) {
//                select_ = false;
//            } else if (_x > endx_) {
//                select_ = false;
//            } else if (_y < y_) {
//                select_ = false;
//            } else if (_y > endy_) {
//                select_ = false;
//            }
//            u_.setSelected(select_);
//        }
    }

    public void setNewLocation(Rate _x, Rate _y) {
        for (EntryCust<Long, Soldier> u: soldiers.entryList()) {
            Soldier u_ = u.getValue();
            if (!u_.getContent().isSelected()) {
                continue;
            }
            u_.getContent().setDestx(_x);
            u_.getContent().setDesty(_y);
        }
    }

    public static void moveSoldier(Soldier _u, Rate _x, Rate _y) {
        _u.getContent().setLocx(_x);
        _u.getContent().setLocy(_y);
    }

    public void selectOrDeselectMany(RtsDataBase _data) {
        Rect s_ = _data.getSoldierPattern();
        Rect rect_ = getSelection();
        for (EntryCust<Long, Soldier> u: soldiers.entryList()) {
            Soldier u_ = u.getValue();
            Rate xThisLeftTop_ = u_.getContent().getLocx();
            Rate yThisLeftTop_ = u_.getContent().getLocy();
//            int xThisRightBottom_ = u_.getLocx() +s_.getWidth();
//            int yThisRightBottom_ = u_.getLocy() + s_.getHeight();
//            int[] xThis_ = new int[]{xThisLeftTop_, xThisRightBottom_, xThisRightBottom_, xThisLeftTop_};
//            int[] yThis_ = new int[]{yThisLeftTop_, yThisLeftTop_, yThisRightBottom_, yThisRightBottom_};
//            Polygon pThis_ = new Polygon(xThis_, yThis_, 4);
            Rect pThis_ = newRect(xThisLeftTop_, yThisLeftTop_, s_.getWidth(), s_.getHeight());
            u_.getContent().setSelected(pThis_.intersects(rect_));
        }
    }

    public void setRectangle(Rate _x, Rate _y) {
        setRectangle(new RatePoint(_x, _y), new RatePoint(_x, _y));
    }

    public void setRectangle(RatePoint _first, RatePoint _last) {
        Rate xLeft_ = Rect.min(_first.getXcoords(), _last.getXcoords());
        Rate xRight_ = Rect.max(_first.getXcoords(), _last.getXcoords());
        Rate yTop_ = Rect.min(_first.getYcoords(), _last.getYcoords());
        Rate yBottom_ = Rect.max(_first.getYcoords(), _last.getYcoords());
        xTopLeftSelect = xLeft_;
        yTopLeftSelect = yTop_;
        widthSelect = Rate.plus(Rate.minus(xRight_, xLeft_), Rate.one());
        heightSelect = Rate.plus(Rate.minus(yBottom_, yTop_), Rate.one());
    }

    public Rect getSelection() {
        return newRect(xTopLeftSelect, yTopLeftSelect, widthSelect, heightSelect);
    }

    public CustList<Long> getSoldierKeys() {
        return soldiers.getKeys();
    }

    public Soldier getSoldier(long _u) {
        return soldiers.getVal(_u);
    }
//
//    public UnitMapKey getLastSoldierKey() {
//        int size_ = soldiers.size();
//        return soldiers.getKeys().get(size_ - 1);
//    }

    public Soldier getLastSoldier() {
        int size_ = soldiers.size();
        return soldiers.values().get(size_ - 1);
    }

    public void moveCamera(Rate _x, Rate _y, Rate _xBound, Rate _yBound, RtsDataBase _data) {
        topLeftScreen = DeltaSelectionUnit.moveCamera(topLeftScreen,_x,_y,_xBound,_yBound, _data.getScreen());
//        Rate deltax_ = Rate.zero();
//        Rate deltay_ = Rate.zero();
//        if (_x < -xTopLeftScreen) {
//            if (_y < -yTopLeftScreen) {
//                return;
//            }
//            if (_y > -yTopLeftScreen + _yBound) {
//                return;
//            }
//            deltax_ = 10;
//        } else if (_x > -xTopLeftScreen + _xBound) {
//            if (_y < -yTopLeftScreen) {
//                return;
//            }
//            if (_y > -yTopLeftScreen + _yBound) {
//                return;
//            }
//            deltax_ = -10;
//        } else if (_y < -yTopLeftScreen) {
//            if (_x < -xTopLeftScreen) {
//                return;
//            }
//            if (_x > -xTopLeftScreen + _xBound) {
//                return;
//            }
//            deltay_ = 10;
//        } else if (_y > -yTopLeftScreen + _yBound) {
//            if (_x < -xTopLeftScreen) {
//                return;
//            }
//            if (_x > -xTopLeftScreen + _xBound) {
//                return;
//            }
//            deltay_ = -10;
//        }
//        xTopLeftScreen += deltax_;
//        yTopLeftScreen += deltay_;
//        if (Rate.strLower(_x,xTopLeftScreen)) {
//            if (Rate.strLower(_y,yTopLeftScreen)) {
//                return;
//            }
//            if (Rate.strGreater(_y,Rate.plus(yTopLeftScreen,_yBound))) {
//                return;
//            }
//            deltax_ = new Rate(-10);
//        } else if (Rate.strGreater(_x,Rate.plus(xTopLeftScreen,_xBound))) {
//            if (Rate.strLower(_y,yTopLeftScreen)) {
//                return;
//            }
//            if (Rate.strGreater(_y,Rate.plus(yTopLeftScreen,_yBound))) {
//                return;
//            }
//            deltax_ = new Rate(10);
//        } else if (Rate.strLower(_y,yTopLeftScreen)) {
//            if (Rate.strGreater(_x,Rate.plus(xTopLeftScreen,_xBound))) {
//                return;
//            }
//            deltay_ = new Rate(-10);
//        } else if (Rate.strGreater(_y,Rate.plus(yTopLeftScreen,_yBound))) {
//            if (Rate.strGreater(_x,Rate.plus(xTopLeftScreen,_xBound))) {
//                return;
//            }
//            deltay_ = new Rate(10);
//        }
//        xTopLeftScreen = Rate.plus(xTopLeftScreen, deltax_);
//        yTopLeftScreen = Rate.plus(yTopLeftScreen, deltay_);
    }

    public CustList<EntryCust<Long,Soldier>> getVisibleSoldiers(Rate _w, Rate _h, RtsDataBase _data) {
        Rect s_ = _data.getSoldierPattern();
//        Rectangle rect_ = new Rectangle(-xTopLeftScreen, -yTopLeftScreen, _w, _h);
        Rect rect_ = newRect(topLeftScreen.getXcoords(), topLeftScreen.getYcoords(), _w, _h);
        CustList<EntryCust<Long,Soldier>> l_ = new CustList<EntryCust<Long,Soldier>>();
        for (EntryCust<Long, Soldier> u: soldiers.entryList()) {
            Soldier u_ = u.getValue();
            Rate xThisLeftTop_ = u_.getContent().getLocx();
            Rate yThisLeftTop_ = u_.getContent().getLocy();
//            int xThisRightBottom_ = u_.getLocx() +s_.getWidth();
//            int yThisRightBottom_ = u_.getLocy() + s_.getHeight();
//            int[] xThis_ = new int[]{xThisLeftTop_, xThisRightBottom_, xThisRightBottom_, xThisLeftTop_};
//            int[] yThis_ = new int[]{yThisLeftTop_, yThisLeftTop_, yThisRightBottom_, yThisRightBottom_};
//            Polygon pThis_ = new Polygon(xThis_, yThis_, 4);
            Rect pThis_ = newRect(xThisLeftTop_, yThisLeftTop_, s_.getWidth(), s_.getHeight());
            if (pThis_.intersects(rect_)) {
                l_.add(u);
            }
        }
        return l_;
    }

    public static Rect newRect(Rate _one, Rate _two, Rate _three, Rate _four) {
        return new Rect(_one,_two,_three,_four);
    }
    public void setxTopLeftScreen(Rate _xTopLeftScreen) {
        topLeftScreen.setXcoords(_xTopLeftScreen);
    }

    public void setyTopLeftScreen(Rate _yTopLeftScreen) {
        topLeftScreen.setYcoords(_yTopLeftScreen);
    }

    public RatePoint getTopLeftPoint() {
        return new RatePoint(topLeftScreen.getXcoords(), topLeftScreen.getYcoords());
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean _added) {
        added = _added;
    }
}
