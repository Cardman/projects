package cards.belote.beans;

import code.bean.nat.CommNatStruct;

public final class BeloteSumDeclaringPlayerStruct extends CommNatStruct {
    private final BeloteSumDeclaringPlayer sumDeclaringPlayer;

    public BeloteSumDeclaringPlayerStruct(BeloteSumDeclaringPlayer _sumDeclaringPlayer, String _className) {
        super(_className);
        this.sumDeclaringPlayer = _sumDeclaringPlayer;
    }

    public BeloteSumDeclaringPlayer getSumDeclaringPlayer() {
        return sumDeclaringPlayer;
    }
}
