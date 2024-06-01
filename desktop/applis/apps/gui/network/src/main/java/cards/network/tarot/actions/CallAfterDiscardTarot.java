package cards.network.tarot.actions;

import cards.network.common.PlayerActionGame;
import cards.network.common.PlayerActionGameType;
import cards.tarot.HandTarot;

public final class CallAfterDiscardTarot extends PlayerActionGame {

    private HandTarot calledCards;
    public CallAfterDiscardTarot(PlayerActionGameType _actionType) {
        super(_actionType);
        setCalledCards(new HandTarot());
    }

    public HandTarot getCalledCards() {
        return calledCards;
    }

    public void setCalledCards(HandTarot _calledCards) {
        calledCards = _calledCards;
    }

}
