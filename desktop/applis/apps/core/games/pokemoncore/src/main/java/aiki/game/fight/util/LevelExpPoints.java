package aiki.game.fight.util;
import code.maths.Rate;

public final class LevelExpPoints {

    private final long level;

    private final Rate expPoints;

    public LevelExpPoints(long _level, Rate _expPoints) {
        level = _level;
        expPoints = _expPoints;
    }

    public long getLevel() {
        return level;
    }

    public Rate getExpPoints() {
        return expPoints;
    }

}
