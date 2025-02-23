package aiki.beans;

import aiki.map.util.MiniMapCoordsTileInts;

public final class MiniSecondCoordMapper implements IntSecondCoordMapper {
    private final MiniMapCoordsTileInts min;

    public MiniSecondCoordMapper(MiniMapCoordsTileInts _o) {
        this.min = _o;
    }

    @Override
    public int length() {
        return min.size();
    }

    @Override
    public int sec(int _i) {
        return min.getKey(_i).getYcoords();
    }
}
