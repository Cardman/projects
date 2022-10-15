package cards.tarot.beans;

import code.expressionlanguage.structs.AbNullStruct;

public final class RankingPlayerVariantGameStruct extends AbNullStruct {
    private final RankingPlayerVariantGame rankingPlayerVariantGame;

    public RankingPlayerVariantGameStruct(RankingPlayerVariantGame _rankingPlayerVariantGame) {
        this.rankingPlayerVariantGame = _rankingPlayerVariantGame;
    }

    public RankingPlayerVariantGame getRankingPlayerVariantGame() {
        return rankingPlayerVariantGame;
    }
}
