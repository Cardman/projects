package cards.tarot.beans;

import code.bean.nat.*;

public final class RankingPlayerVariantGameStruct extends NaNuSt {
    private final RankingPlayerVariantGame rankingPlayerVariantGame;

    public RankingPlayerVariantGameStruct(RankingPlayerVariantGame _rankingPlayerVariantGame) {
        this.rankingPlayerVariantGame = _rankingPlayerVariantGame;
    }

    public RankingPlayerVariantGame getRankingPlayerVariantGame() {
        return rankingPlayerVariantGame;
    }
}
