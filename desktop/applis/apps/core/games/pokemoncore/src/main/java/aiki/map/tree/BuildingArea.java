package aiki.map.tree;
import aiki.map.buildings.Building;
import aiki.util.Point;

public class BuildingArea {

    private LevelArea level;

    public void initialize(Building _building) {
        level = new LevelArea();
        level.initialize(_building.getLevel());
    }

    public boolean isValid(Point _pt,boolean _accessible) {
        return level.isValid(_pt,_accessible);
    }

    public LevelArea getLevel() {
        return level;
    }
}
