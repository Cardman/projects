package aiki.util;
import code.sml.FromAndToString;
import code.util.Numbers;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class LevelPoint implements Equallable<LevelPoint>, Displayable {

    static final char SEPARATOR = '_';

    private byte levelIndex;

    private Point point;

    public LevelPoint() {
    }

    public LevelPoint(LevelPoint _levelPoint) {
        levelIndex = _levelPoint.levelIndex;
        point = new Point(_levelPoint.point);
    }

    public LevelPoint(String _string) {
        StringList elements_ = StringList.splitChars(_string, SEPARATOR);
        levelIndex = Byte.parseByte(elements_.first());
        point = new Point(elements_.last());
    }

    @FromAndToString
    public static LevelPoint newLevelPoint(String _string) {
        return new LevelPoint(_string);
    }

    public void affect(LevelPoint _levelPoint) {
        levelIndex = _levelPoint.levelIndex;
        if (_levelPoint.point != null) {
            if (point != null) {
                point.affect(_levelPoint.point);
            } else {
                point = new Point(_levelPoint.point);
            }
        } else {
            point = null;
        }
    }

    public boolean isValid() {
        return point != null;
    }

    public static boolean eq(LevelPoint _lp1,LevelPoint _lp2) {
        if (_lp1 == null) {
            return _lp2 == null;
        }
        return _lp1.eq(_lp2);
    }

    @Override
    public boolean eq(LevelPoint _obj) {
        if (!Numbers.eq(levelIndex,_obj.levelIndex)) {
            return false;
        }
        if (!Point.eq(point,_obj.point)) {
            return false;
        }
        return true;
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

    @FromAndToString
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(levelIndex);
        str_.append(SEPARATOR);
        str_.append(point.display());
        return str_.toString();
    }

}
