package code.minirts.rts;

import code.maths.Rate;
import code.maths.geo.CustPoint;
import code.maths.geo.RatePoint;
import code.maths.geo.Rect;
import code.util.CustList;
import code.util.EntryCust;
import code.util.core.NumberUtil;

public final class RtsGame {

    private final UnitMapKeyMap<Soldier> soldiers = new UnitMapKeyMap<Soldier>();

    private int xTopLeftSelect;
    private int yTopLeftSelect;

    private int widthSelect = 1;
    private int heightSelect = 1;

//    private int xTopLeftScreen = -100;
//    private int yTopLeftScreen = -100;
    private int xTopLeftScreen = 100;
    private int yTopLeftScreen = 100;

    private boolean added;

    public void loop(RtsDataBase _data) {
        for (EntryCust<UnitMapKey,Soldier> u: soldiers.entryList()) {
            Soldier u_ = u.getValue();
            Delta d_ = getDelta(u_);
            if (!d_.isMoving()) {
                continue;
            }
            int curX_ = u_.getLocx();
            int curY_ = u_.getLocy();
            int nextX_ = curX_ + d_.getDx();
            int nextY_ = curY_ + d_.getDy();
            if (!isEmpty(u.getKey(), nextX_, nextY_, d_, _data)) {
                continue;
            }
            moveSoldier(u_, nextX_, nextY_);
        }
    }

    public Delta getDelta(Soldier _u) {
        int curX_ = _u.getLocx();
        int curY_ = _u.getLocy();
        int deltax_ = 0;
        int deltay_ = 0;
        int destX_ = _u.getDestx();
        int destY_ = _u.getDesty();
        deltax_ = destX_ - curX_;
        deltay_ = destY_ - curY_;
        int realDeltax_ = 0;
        int realDeltay_ = 0;
        if (deltax_ == 0 && deltay_ == 0) {
            Delta d_ = new Delta();
            d_.setDx(realDeltax_);
            d_.setDy(realDeltay_);
            return d_;
        }
        if (deltax_ == deltay_) {
            if (deltay_ < 0) {
                realDeltax_ = -1;
                realDeltay_ = -1;
            } else {
                realDeltax_ = 1;
                realDeltay_ = 1;
            }
        } else if (deltax_ == 0) {
            if (deltay_ < 0) {
                realDeltay_ = -1;
            } else {
                realDeltay_ = 1;
            }
        } else if (deltay_ == 0) {
            if (deltax_ < 0) {
                realDeltax_ = -1;
            } else {
                realDeltax_ = 1;
            }
        } else if (NumberUtil.abs(deltax_) < NumberUtil.abs(deltay_)) {
            if (deltay_ < 0) {
                realDeltay_ = -1;
            } else {
                realDeltay_ = 1;
            }
        } else {
            if (deltax_ < 0) {
                realDeltax_ = -1;
            } else {
                realDeltax_ = 1;
            }
        }
        Delta d_ = new Delta();
        d_.setDx(realDeltax_);
        d_.setDy(realDeltay_);
        return d_;
    }

    public void addNewSoldier(int _x, int _y, RtsDataBase _data, long _next) {
        if (!isEmpty(_x, _y, _data)) {
            added = false;
            return;
        }
        added = true;
        Soldier soldierLabel_ = new Soldier();
        soldierLabel_.setLocx(_x);
        soldierLabel_.setDestx(_x);
        soldierLabel_.setLocy(_y);
        soldierLabel_.setDesty(_y);
        soldiers.put(new UnitMapKey(_x, _y,_next),soldierLabel_);
    }

    public boolean isEmpty(UnitMapKey _this, int _x, int _y, Delta _d, RtsDataBase _data) {
        Soldier this_ = soldiers.getVal(_this);
        int xThisLeftTop_ = this_.getLocx();
        int yThisLeftTop_ = this_.getLocy();
        SoldierPattern s_ = _data.getSoldierPattern();
//        int xThisRightBottom_ = this_.getLocx() + s_.getWidth();
//        int yThisRightBottom_ = this_.getLocy() + s_.getHeight();
//        int[] xThis_ = new int[]{xThisLeftTop_, xThisRightBottom_, xThisRightBottom_, xThisLeftTop_};
//        int[] yThis_ = new int[]{yThisLeftTop_, yThisLeftTop_, yThisRightBottom_, yThisRightBottom_};
//        Polygon pThis_ = new Polygon(xThis_, yThis_, 4);
        Rect pThis_ = newRect(xThisLeftTop_, yThisLeftTop_, s_.getWidth(), s_.getHeight());
        for (EntryCust<UnitMapKey,Soldier> u: soldiers.entryList()) {
            if (u.getKey().eq(_this)) {
                continue;
            }
            if (!isOutside(pThis_, u.getValue(), _x, _y, _d, _data)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty(int _x, int _y, RtsDataBase _data) {
        SoldierPattern s_ = _data.getSoldierPattern();
//        int xThisRightBottom_ = _x + s_.getWidth();
//        int yThisRightBottom_ = _y + s_.getHeight();
//        int[] xThis_ = new int[]{xThisLeftTop_, xThisRightBottom_, xThisRightBottom_, xThisLeftTop_};
//        int[] yThis_ = new int[]{yThisLeftTop_, yThisLeftTop_, yThisRightBottom_, yThisRightBottom_};
//        Polygon pThis_ = new Polygon(xThis_, yThis_, 4);
        Rect pThis_ = newRect(_x, _y, s_.getWidth(), s_.getHeight());
        Delta d_ = new Delta();
        for (EntryCust<UnitMapKey,Soldier> u: soldiers.entryList()) {
            if (!isOutside(pThis_, u.getValue(), _x, _y, d_, _data)) {
                return false;
            }
        }
        return true;
    }

    boolean isOutside(Rect _p, Soldier _u, int _x, int _y, Delta _d, RtsDataBase _data) {
        //Polygon
        long xULeftTop_ = _u.getLocx();
        long yULeftTop_ = _u.getLocy();
        SoldierPattern s_ = _data.getSoldierPattern();
        long xURightBottom_ = xULeftTop_ + s_.getWidth();
        long yURightBottom_ = yULeftTop_ + s_.getHeight();
        if (_p.containsInside(new RatePoint(new Rate(xULeftTop_ - _d.getDx()), new Rate(yULeftTop_ - _d.getDy())))) {
            return false;
        }
        if (_p.containsInside(new RatePoint(new Rate(xURightBottom_ - _d.getDx()), new Rate(yULeftTop_ - _d.getDy())))) {
            return false;
        }
        if (_p.containsInside(new RatePoint(new Rate(xURightBottom_ - _d.getDx()), new Rate(yURightBottom_ - _d.getDy())))) {
            return false;
        }
        return !_p.containsInside(new RatePoint(new Rate(xULeftTop_ - _d.getDx()), new Rate(yURightBottom_ - _d.getDy())));
    }

    public void selectOrDeselect(CustPoint _first, CustPoint _last, RtsDataBase _data) {
//        setRectangle(_first, _last);
        selectOrDeselectMany(_data);
    }

    public void selectOrDeselect(int _x, int _y, RtsDataBase _data) {
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

    public void setNewLocation(int _x, int _y) {
        for (EntryCust<UnitMapKey,Soldier> u: soldiers.entryList()) {
            Soldier u_ = u.getValue();
            if (!u_.isSelected()) {
                continue;
            }
            u_.setDestx(_x);
            u_.setDesty(_y);
        }
    }

    public static void moveSoldier(Soldier _u, int _x, int _y) {
        _u.setLocx(_x);
        _u.setLocy(_y);
    }

    public void selectOrDeselectMany(RtsDataBase _data) {
        SoldierPattern s_ = _data.getSoldierPattern();
        Rect rect_ = getSelection();
        for (EntryCust<UnitMapKey,Soldier> u: soldiers.entryList()) {
            Soldier u_ = u.getValue();
            int xThisLeftTop_ = u_.getLocx();
            int yThisLeftTop_ = u_.getLocy();
//            int xThisRightBottom_ = u_.getLocx() +s_.getWidth();
//            int yThisRightBottom_ = u_.getLocy() + s_.getHeight();
//            int[] xThis_ = new int[]{xThisLeftTop_, xThisRightBottom_, xThisRightBottom_, xThisLeftTop_};
//            int[] yThis_ = new int[]{yThisLeftTop_, yThisLeftTop_, yThisRightBottom_, yThisRightBottom_};
//            Polygon pThis_ = new Polygon(xThis_, yThis_, 4);
            Rect pThis_ = newRect(xThisLeftTop_, yThisLeftTop_, s_.getWidth(), s_.getHeight());
            u_.setSelected(pThis_.intersects(rect_));
        }
    }

    public void setRectangle(int _x, int _y) {
        setRectangle(new CustPoint(_x, _y), new CustPoint(_x, _y));
    }

    public void setRectangle(CustPoint _first, CustPoint _last) {
        int xLeft_ = NumberUtil.min(_first.getXcoords(), _last.getXcoords());
        int xRight_ = NumberUtil.max(_first.getXcoords(), _last.getXcoords());
        int yTop_ = NumberUtil.min(_first.getYcoords(), _last.getYcoords());
        int yBottom_ = NumberUtil.max(_first.getYcoords(), _last.getYcoords());
        xTopLeftSelect = xLeft_;
        yTopLeftSelect = yTop_;
        widthSelect = xRight_ - xLeft_ + 1;
        heightSelect = yBottom_ - yTop_ + 1;
    }

    public Rect getSelection() {
        return newRect(xTopLeftSelect, yTopLeftSelect, widthSelect, heightSelect);
    }

    public CustList<UnitMapKey> getSoldierKeys() {
        return soldiers.getKeys();
    }

    public Soldier getSoldier(UnitMapKey _u) {
        return soldiers.getVal(_u);
    }

    public UnitMapKey getLastSoldierKey() {
        int size_ = soldiers.size();
        return soldiers.getKeys().get(size_ - 1);
    }

    public Soldier getLastSoldier() {
        int size_ = soldiers.size();
        return soldiers.values().get(size_ - 1);
    }

    public void moveCamera(int _x, int _y, int _xBound, int _yBound) {
        int deltax_ = 0;
        int deltay_ = 0;
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
        if (_x < xTopLeftScreen) {
            if (_y < yTopLeftScreen) {
                return;
            }
            if (_y > yTopLeftScreen + _yBound) {
                return;
            }
            deltax_ = -10;
        } else if (_x > xTopLeftScreen + _xBound) {
            if (_y < yTopLeftScreen) {
                return;
            }
            if (_y > yTopLeftScreen + _yBound) {
                return;
            }
            deltax_ = 10;
        } else if (_y < yTopLeftScreen) {
            if (_x > xTopLeftScreen + _xBound) {
                return;
            }
            deltay_ = -10;
        } else if (_y > yTopLeftScreen + _yBound) {
            if (_x > xTopLeftScreen + _xBound) {
                return;
            }
            deltay_ = 10;
        }
        xTopLeftScreen += deltax_;
        yTopLeftScreen += deltay_;
    }

    public CustList<UnitMapKey> getVisibleSoldiers(int _w, int _h, RtsDataBase _data) {
        SoldierPattern s_ = _data.getSoldierPattern();
//        Rectangle rect_ = new Rectangle(-xTopLeftScreen, -yTopLeftScreen, _w, _h);
        Rect rect_ = newRect(xTopLeftScreen, yTopLeftScreen, _w, _h);
        CustList<UnitMapKey> l_ = new CustList<UnitMapKey>();
        for (EntryCust<UnitMapKey,Soldier> u: soldiers.entryList()) {
            Soldier u_ = u.getValue();
            int xThisLeftTop_ = u_.getLocx();
            int yThisLeftTop_ = u_.getLocy();
//            int xThisRightBottom_ = u_.getLocx() +s_.getWidth();
//            int yThisRightBottom_ = u_.getLocy() + s_.getHeight();
//            int[] xThis_ = new int[]{xThisLeftTop_, xThisRightBottom_, xThisRightBottom_, xThisLeftTop_};
//            int[] yThis_ = new int[]{yThisLeftTop_, yThisLeftTop_, yThisRightBottom_, yThisRightBottom_};
//            Polygon pThis_ = new Polygon(xThis_, yThis_, 4);
            Rect pThis_ = newRect(xThisLeftTop_, yThisLeftTop_, s_.getWidth(), s_.getHeight());
            if (pThis_.intersects(rect_)) {
                l_.add(u.getKey());
            }
        }
        return l_;
    }

    public static Rect newRect(int _one, int _two, int _three, int _four) {
        return new Rect(new Rate(_one),new Rate(_two),new Rate(_three),new Rate(_four));
    }
    public void setxTopLeftScreen(int _xTopLeftScreen) {
        xTopLeftScreen = _xTopLeftScreen;
    }

    public void setyTopLeftScreen(int _yTopLeftScreen) {
        yTopLeftScreen = _yTopLeftScreen;
    }

    public CustPoint getTopLeftPoint() {
        return new CustPoint(xTopLeftScreen, yTopLeftScreen);
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean _added) {
        added = _added;
    }
}
