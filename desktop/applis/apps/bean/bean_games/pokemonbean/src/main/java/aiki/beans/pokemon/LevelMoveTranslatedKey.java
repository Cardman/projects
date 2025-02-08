package aiki.beans.pokemon;

import aiki.beans.TranslatedKey;
import aiki.fight.util.LevelMove;

public final class LevelMoveTranslatedKey {
    private final TranslatedKey move;
    private final long level;

    public LevelMoveTranslatedKey(TranslatedKey _m, long _l) {
        this.move = _m;
        this.level = _l;
    }
    public LevelMove lm() {
        return new LevelMove(getLevel(), move.getTranslation());
    }

    public TranslatedKey getMove() {
        return move;
    }

    public long getLevel() {
        return level;
    }
}
