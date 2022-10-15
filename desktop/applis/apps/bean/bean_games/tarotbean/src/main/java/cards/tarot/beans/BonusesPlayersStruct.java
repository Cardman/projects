package cards.tarot.beans;

import code.expressionlanguage.structs.AbNullStruct;

public final class BonusesPlayersStruct extends AbNullStruct {
    private final BonusesPlayers bonusesPlayers;

    public BonusesPlayersStruct(BonusesPlayers _bonusesPlayers) {
        this.bonusesPlayers = _bonusesPlayers;
    }

    public BonusesPlayers getBonusesPlayers() {
        return bonusesPlayers;
    }
}
