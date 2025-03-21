package cards.network.tarot.actions;
import cards.network.common.PlayerActionGame;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.IdList;


public final class PlayingCardTarot extends PlayerActionGame {

    private CardTarot playedCard;

    private Handfuls choosenHandful;

    private HandTarot handful;

    private HandTarot excludedTrumps;

    private IdList<Miseres> miseres;

    private boolean calledCard;

    private boolean refreshing;

    private boolean firstRound;

    private int takerIndex;

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

    public boolean isRefreshing() {
        return refreshing;
    }

    public void setRefreshing(boolean _r) {
        this.refreshing = _r;
    }

    public boolean isFirstRound() {
        return firstRound;
    }

    public void setFirstRound(boolean _f) {
        this.firstRound = _f;
    }

    public int getTakerIndex() {
        return takerIndex;
    }

    public void setTakerIndex(int _takerIndex) {
        takerIndex = _takerIndex;
    }
}
