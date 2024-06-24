package code.minirts.rts;

import code.maths.Rate;
import code.maths.geo.RatePoint;
import code.maths.geo.Rect;
import code.util.CustList;
import code.util.EntryCust;

public final class Facade {

    private final RtsGame game = new RtsGame();

    private final RtsDataBase data = new RtsDataBase();

    public Rect getSoldierPattern() {
        return data.getSoldierPattern();
    }

    public void loop() {
        game.loop(data);
    }

    public Soldier addNewSoldier(Rate _x, Rate _y, long _next) {
        return game.addNewSoldier(_x, _y, data,_next);
    }

    public boolean isEmpty(long _this, Delta _d) {
        return game.isEmpty(_this, _d, data);
    }

    public boolean isEmpty(Rate _x, Rate _y) {
        return game.isEmpty(_x, _y, data);
    }

    public void selectOrDeselectMany() {
        game.selectOrDeselectMany(data);
    }

    public void setRectangle(RatePoint _first, RatePoint _last) {
        game.setRectangle(_first, _last);
    }

    public void setRectangle(Rate _x, Rate _y) {
        game.setRectangle(_x, _y);
    }

    public void selectOrDeselect() {
        game.selectOrDeselect(data);
    }

    public void setNewLocation(Rate _x, Rate _y) {
        game.setNewLocation(_x, _y);
    }

    public Rect getSelection() {
        return game.getSelection();
    }

    public RatePoint getTopLeftPoint() {
        return game.getTopLeftPoint();
    }

    public void moveCamera(Rate _x, Rate _y, Rate _xBound, Rate _yBound) {
        game.moveCamera(_x, _y, _xBound, _yBound);
    }

    public CustList<EntryCust<Long,Soldier>> getVisibleSoldiers(Rate _w, Rate _h) {
        return game.getVisibleSoldiers(_w, _h, data);
    }

    public void setxTopLeftScreen(Rate _xTopLeftScreen) {
        game.setxTopLeftScreen(_xTopLeftScreen);
    }

    public void setyTopLeftScreen(Rate _yTopLeftScreen) {
        game.setyTopLeftScreen(_yTopLeftScreen);
    }

    public CustList<Long> getSoldierKeys() {
        return game.getSoldierKeys();
    }
//
//    public UnitMapKey getLastSoldierKey() {
//        return game.getLastSoldierKey();
//    }

    public Soldier getLastSoldier() {
        return game.getLastSoldier();
    }

    public Soldier getSoldier(long _u) {
        return game.getSoldier(_u);
    }

    public boolean isAdded() {
        return game.isAdded();
    }

    public void setAdded(boolean _added) {
        game.setAdded(_added);
    }
}
