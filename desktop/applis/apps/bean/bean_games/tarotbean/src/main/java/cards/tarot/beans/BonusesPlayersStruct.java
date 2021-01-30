package cards.tarot.beans;

import code.bean.nat.CommNatStruct;

public final class BonusesPlayersStruct extends CommNatStruct {
    private final BonusesPlayers bonusesPlayers;

    public BonusesPlayersStruct(BonusesPlayers _bonusesPlayers, String _className) {
        super(_className);
        this.bonusesPlayers = _bonusesPlayers;
    }

    public BonusesPlayers getBonusesPlayers() {
        return bonusesPlayers;
    }
}
