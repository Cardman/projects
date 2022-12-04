package cards.tarot.beans;

import code.bean.nat.*;

public final class BonusesPlayersStruct extends NaNuSt {
    private final BonusesPlayers bonusesPlayers;

    public BonusesPlayersStruct(BonusesPlayers _bonusesPlayers) {
        this.bonusesPlayers = _bonusesPlayers;
    }

    public BonusesPlayers getBonusesPlayers() {
        return bonusesPlayers;
    }
}
