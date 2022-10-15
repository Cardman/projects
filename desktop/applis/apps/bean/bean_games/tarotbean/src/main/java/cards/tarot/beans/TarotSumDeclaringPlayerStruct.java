package cards.tarot.beans;

import code.expressionlanguage.structs.AbNullStruct;

public final class TarotSumDeclaringPlayerStruct extends AbNullStruct {
    private final TarotSumDeclaringPlayer sumDeclaringPlayer;

    public TarotSumDeclaringPlayerStruct(TarotSumDeclaringPlayer _sumDeclaringPlayer) {
        this.sumDeclaringPlayer = _sumDeclaringPlayer;
    }

    public TarotSumDeclaringPlayer getSumDeclaringPlayer() {
        return sumDeclaringPlayer;
    }
}
