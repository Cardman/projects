package cards.network.tarot.actions;
import code.util.EnumList;
import code.util.annot.RwXml;
import cards.network.common.PlayerActionGame;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;

@RwXml
public class PlayingCardTarot extends PlayerActionGame {

    private CardTarot playedCard;

    private Handfuls choosenHandful;

    private HandTarot handful;

    private HandTarot excludedTrumps;

    private EnumList<Miseres> miseres;

    private boolean aCalledCard;

    private byte takerIndex;

    public CardTarot getPlayedCard() {
        return playedCard;
    }

    public void setPlayedCard(CardTarot _playedCard) {
        playedCard = _playedCard;
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

    public HandTarot getExcludedTrumps() {
        return excludedTrumps;
    }

    public void setExcludedTrumps(HandTarot _excludedTrumps) {
        excludedTrumps = _excludedTrumps;
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

    public byte getTakerIndex() {
        return takerIndex;
    }

    public void setTakerIndex(byte _takerIndex) {
        takerIndex = _takerIndex;
    }
}
