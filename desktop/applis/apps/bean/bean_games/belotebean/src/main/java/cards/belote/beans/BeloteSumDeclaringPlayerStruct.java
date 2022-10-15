package cards.belote.beans;

import code.expressionlanguage.structs.AbNullStruct;

public final class BeloteSumDeclaringPlayerStruct extends AbNullStruct {
    private final BeloteSumDeclaringPlayer sumDeclaringPlayer;

    public BeloteSumDeclaringPlayerStruct(BeloteSumDeclaringPlayer _sumDeclaringPlayer) {
        this.sumDeclaringPlayer = _sumDeclaringPlayer;
    }

    public BeloteSumDeclaringPlayer getSumDeclaringPlayer() {
        return sumDeclaringPlayer;
    }
}
