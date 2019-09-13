package code.minirts.rts;

import code.maths.geo.CustPoint;
import code.maths.geo.Rect;
import code.util.CustList;
import code.util.EqList;

public final class Facade {

    private Game game = new Game();

    private DataBase data = new DataBase();

    public SoldierPattern getSoldierPattern() {
        return data.getSoldierPattern();
    }

    public void loop() {
        game.loop(data);
    }

    public void addNewSoldier(int _x, int _y) {
        game.addNewSoldier(_x, _y, data);
    }

    public boolean isEmpty(UnitMapKey _this, int _x, int _y, Delta _d) {
        return game.isEmpty(_this, _x, _y, _d, data);
    }

    public boolean isEmpty(int _x, int _y) {
        return game.isEmpty(_x, _y, data);
    }

    public void selectOrDeselectMany() {
        game.selectOrDeselectMany(data);
    }

    public void setRectangle(CustPoint _first, CustPoint _last) {
        game.setRectangle(_first, _last);
    }

    public void setRectangle(int _x, int _y) {
        game.setRectangle(_x, _y);
    }

    public void selectOrDeselect(CustPoint _first, CustPoint _last) {
        game.selectOrDeselect(_first, _last, data);
    }

    public void selectOrDeselect(int _x, int _y) {
        game.selectOrDeselect(_x, _y, data);
    }

    public void setNewLocation(int _x, int _y) {
        game.setNewLocation(_x, _y);
    }

    public Rect getSelection() {
        return game.getSelection();
    }

    public CustPoint getTopLeftPoint() {
        return game.getTopLeftPoint();
    }

    public void moveCamera(int _x, int _y, int _xBound, int _yBound) {
        game.moveCamera(_x, _y, _xBound, _yBound);
    }

    public EqList<UnitMapKey> getVisibleSoldiers(int _w, int _h) {
        return game.getVisibleSoldiers(_w, _h, data);
    }

    public void setxTopLeftScreen(int _xTopLeftScreen) {
        game.setxTopLeftScreen(_xTopLeftScreen);
    }

    public void setyTopLeftScreen(int _yTopLeftScreen) {
        game.setyTopLeftScreen(_yTopLeftScreen);
    }

    public CustList<UnitMapKey> getSoldierKeys() {
        return game.getSoldierKeys();
    }

    public UnitMapKey getLastSoldierKey() {
        return game.getLastSoldierKey();
    }

    public Soldier getLastSoldier() {
        return game.getLastSoldier();
    }

    public Soldier getSoldier(UnitMapKey _u) {
        return game.getSoldier(_u);
    }

    public boolean isAdded() {
        return game.isAdded();
    }

    public void setAdded(boolean _added) {
        game.setAdded(_added);
    }
}
