package aiki.util;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class LevelPoint implements Equallable<LevelPoint>, Displayable {

    static final char SEPARATOR = '_';

    private byte levelIndex;

    private Point point;

    public LevelPoint() {
        point = new Point();
    }

    public LevelPoint(LevelPoint _levelPoint) {
        levelIndex = _levelPoint.levelIndex;
        point = new Point(_levelPoint.point);
    }

    public LevelPoint(String _string) {
        StringList elements_ = StringUtil.splitChars(_string, SEPARATOR);
        levelIndex = (byte) NumberUtil.parseInt(elements_.first());
        point = new Point(elements_.last());
    }

    
    public static LevelPoint newLevelPoint(String _string) {
        return new LevelPoint(_string);
    }

    public void affect(LevelPoint _levelPoint) {
        levelIndex = _levelPoint.levelIndex;
        point.affect(_levelPoint.point);
    }

    public static boolean eq(LevelPoint _lp1,LevelPoint _lp2) {
        return _lp1.eq(_lp2);
    }

    @Override
    public boolean eq(LevelPoint _obj) {
        if (!NumberUtil.eq(levelIndex,_obj.levelIndex)) {
            return false;
        }
        return Point.eq(point, _obj.point);
    }
    public byte getLevelIndex() {
        return levelIndex;
    }

    public void setLevelIndex(byte _levelIndex) {
        levelIndex = _levelIndex;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point _point) {
        point = _point;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(levelIndex);
        str_.append(SEPARATOR);
        str_.append(point.display());
        return str_.toString();
    }

}
