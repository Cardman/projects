package aiki.db;

import aiki.fight.util.*;

public final class ChangeStringFieldLevelMove implements ChangeStringField {
    private final LevelMove levelMove;

    public ChangeStringFieldLevelMove(LevelMove _l) {
        this.levelMove = _l;
    }

    @Override
    public String value() {
        return levelMove.getMove();
    }

    @Override
    public void value(String _v) {
        levelMove.setMove(_v);
    }
}
