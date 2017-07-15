package cards.network.tarot.displaying.players;
import code.util.EnumList;
import code.util.annot.RwXml;
import cards.network.common.PlayerActionGame;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;

@RwXml
public class RefreshingDone extends PlayerActionGame {

    private CardTarot card;

    private Handfuls choosenHandful;

    private HandTarot handful;

    private EnumList<Miseres> miseres;

    private boolean aCalledCard;

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

    public EnumList<Miseres> getMiseres() {
        return miseres;
    }

    public void setMiseres(EnumList<Miseres> _miseres) {
        miseres = _miseres;
    }

    public boolean isaCalledCard() {
        return aCalledCard;
    }

    public void setaCalledCard(boolean _aCalledCard) {
        aCalledCard = _aCalledCard;
    }
}
