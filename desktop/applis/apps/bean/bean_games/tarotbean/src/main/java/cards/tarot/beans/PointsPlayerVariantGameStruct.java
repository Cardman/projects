package cards.tarot.beans;

import code.expressionlanguage.structs.AbNullStruct;

public final class PointsPlayerVariantGameStruct extends AbNullStruct {
    private final PointsPlayerVariantGame pointsPlayerVariantGame;

    public PointsPlayerVariantGameStruct(PointsPlayerVariantGame _pointsPlayerVariantGame) {
        this.pointsPlayerVariantGame = _pointsPlayerVariantGame;
    }

    public PointsPlayerVariantGame getPointsPlayerVariantGame() {
        return pointsPlayerVariantGame;
    }
}
