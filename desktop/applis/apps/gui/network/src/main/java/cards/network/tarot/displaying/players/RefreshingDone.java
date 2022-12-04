package cards.network.tarot.displaying.players;
import cards.network.common.PlayerActionGame;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.IdList;


public final class RefreshingDone extends PlayerActionGame {

    private CardTarot card;

    private Handfuls choosenHandful;

    private HandTarot handful;

    private IdList<Miseres> miseres;

    private boolean calledCard;

    public CardTarot getCard() {
        return card;
    }

    public void setCard(CardTarot _card) {
        card = _card;
    }

    public Handfuls getChoosenHandful() {
        return choosenHandful;
    }

    public void setChoosenHandful(Handfuls _choosenHandful) {
        choosenHandful = _choosenHandful;
    }

    public HandTarot getHandful() {
        return handful;
    }

    public void setHandful(HandTarot _handful) {
        handful = _handful;
    }

    public IdList<Miseres> getMiseres() {
        return miseres;
    }

    public void setMiseres(IdList<Miseres> _miseres) {
        miseres = _miseres;
    }

    public boolean isCalledCard() {
        return calledCard;
    }

    public void setCalledCard(boolean _calledCard) {
        calledCard = _calledCard;
    }
}
