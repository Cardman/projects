package aiki.game.fight.util;
import code.maths.Rate;

public final class LevelExpPoints {

    private final short level;

    private final Rate expPoints;

    public LevelExpPoints(short _level, Rate _expPoints) {
        level = _level;
        expPoints = _expPoints;
    }

    public short getLevel() {
        return level;
    }

    public Rate getExpPoints() {
        return expPoints;
    }

}
