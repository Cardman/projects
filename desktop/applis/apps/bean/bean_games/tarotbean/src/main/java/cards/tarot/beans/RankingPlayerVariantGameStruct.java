package cards.tarot.beans;

import code.bean.nat.CommNatStruct;

public final class RankingPlayerVariantGameStruct extends CommNatStruct {
    private final RankingPlayerVariantGame rankingPlayerVariantGame;

    public RankingPlayerVariantGameStruct(RankingPlayerVariantGame _rankingPlayerVariantGame, String _className) {
        super(_className);
        this.rankingPlayerVariantGame = _rankingPlayerVariantGame;
    }

    public RankingPlayerVariantGame getRankingPlayerVariantGame() {
        return rankingPlayerVariantGame;
    }
}
