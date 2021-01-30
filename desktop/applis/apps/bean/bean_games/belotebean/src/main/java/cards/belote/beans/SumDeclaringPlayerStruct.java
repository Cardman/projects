package cards.belote.beans;

import code.bean.nat.CommNatStruct;

public final class SumDeclaringPlayerStruct extends CommNatStruct {
    private final SumDeclaringPlayer sumDeclaringPlayer;

    public SumDeclaringPlayerStruct(SumDeclaringPlayer _sumDeclaringPlayer, String _className) {
        super(_className);
        this.sumDeclaringPlayer = _sumDeclaringPlayer;
    }

    public SumDeclaringPlayer getSumDeclaringPlayer() {
        return sumDeclaringPlayer;
    }
}
