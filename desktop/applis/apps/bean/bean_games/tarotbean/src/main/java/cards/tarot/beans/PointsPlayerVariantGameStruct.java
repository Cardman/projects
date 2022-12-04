package cards.tarot.beans;

import code.bean.nat.*;

public final class PointsPlayerVariantGameStruct extends NaNuSt {
    private final PointsPlayerVariantGame pointsPlayerVariantGame;

    public PointsPlayerVariantGameStruct(PointsPlayerVariantGame _pointsPlayerVariantGame) {
        this.pointsPlayerVariantGame = _pointsPlayerVariantGame;
    }

    public PointsPlayerVariantGame getPointsPlayerVariantGame() {
        return pointsPlayerVariantGame;
    }
}
