package aiki.game.fight.util;
import code.maths.Rate;

public final class LevelExpPoints {

    private final int level;

    private final Rate expPoints;

    public LevelExpPoints(int _level, Rate _expPoints) {
        level = _level;
        expPoints = _expPoints;
    }

    public int getLevel() {
        return level;
    }

    public Rate getExpPoints() {
        return expPoints;
    }

}
