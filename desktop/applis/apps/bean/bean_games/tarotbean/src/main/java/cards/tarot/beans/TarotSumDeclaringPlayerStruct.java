package cards.tarot.beans;

import code.bean.nat.*;

public final class TarotSumDeclaringPlayerStruct extends NaNuSt {
    private final TarotSumDeclaringPlayer sumDeclaringPlayer;

    public TarotSumDeclaringPlayerStruct(TarotSumDeclaringPlayer _sumDeclaringPlayer) {
        this.sumDeclaringPlayer = _sumDeclaringPlayer;
    }

    public TarotSumDeclaringPlayer getSumDeclaringPlayer() {
        return sumDeclaringPlayer;
    }
}
