package cards.belote.beans;

import code.bean.nat.*;

public final class BeloteSumDeclaringPlayerStruct extends NaNuSt {
    private final BeloteSumDeclaringPlayer sumDeclaringPlayer;

    public BeloteSumDeclaringPlayerStruct(BeloteSumDeclaringPlayer _sumDeclaringPlayer) {
        this.sumDeclaringPlayer = _sumDeclaringPlayer;
    }

    public BeloteSumDeclaringPlayer getSumDeclaringPlayer() {
        return sumDeclaringPlayer;
    }
}
