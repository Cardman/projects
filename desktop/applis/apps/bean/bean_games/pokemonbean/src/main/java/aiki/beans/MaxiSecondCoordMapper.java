package aiki.beans;

import aiki.comparators.DictionaryComparator;
import aiki.util.Point;

public final class MaxiSecondCoordMapper implements IntSecondCoordMapper {
    private final DictionaryComparator<Point,int[][]> max;

    public MaxiSecondCoordMapper(DictionaryComparator<Point, int[][]> _o) {
        this.max = _o;
    }

    @Override
    public int length() {
        return max.size();
    }

    @Override
    public int sec(int _i) {
        return max.getKey(_i).gety();
    }
}
