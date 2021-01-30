package cards.tarot.beans;

import code.bean.nat.CommNatStruct;

public final class PointsPlayerVariantGameStruct extends CommNatStruct {
    private final PointsPlayerVariantGame pointsPlayerVariantGame;

    public PointsPlayerVariantGameStruct(PointsPlayerVariantGame _pointsPlayerVariantGame, String _className) {
        super(_className);
        this.pointsPlayerVariantGame = _pointsPlayerVariantGame;
    }

    public PointsPlayerVariantGame getPointsPlayerVariantGame() {
        return pointsPlayerVariantGame;
    }
}
