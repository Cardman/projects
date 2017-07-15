package aiki.map.levels;
import aiki.util.Point;
import code.datacheck.CheckedData;

@CheckedData
public class LevelOutdoor extends Level {

    @Override
    public boolean isEmptyForAdding(Point _point) {
        return true;
    }
    @Override
    public boolean isEmpty(Point _point) {
        return true;
    }
    @Override
    public void clearElements(Point _point) {
    }
    @Override
    public void translateElement(Point _id, Point _target) {
    }
}
