package cards.tarot.beans;

import code.bean.nat.CommNatStruct;

public final class TarotSumDeclaringPlayerStruct extends CommNatStruct {
    private final TarotSumDeclaringPlayer sumDeclaringPlayer;

    public TarotSumDeclaringPlayerStruct(TarotSumDeclaringPlayer _sumDeclaringPlayer, String _className) {
        super(_className);
        this.sumDeclaringPlayer = _sumDeclaringPlayer;
    }

    public TarotSumDeclaringPlayer getSumDeclaringPlayer() {
        return sumDeclaringPlayer;
    }
}
