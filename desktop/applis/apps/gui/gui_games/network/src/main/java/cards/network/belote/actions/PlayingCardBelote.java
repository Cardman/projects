package cards.network.belote.actions;
import cards.belote.DeclareHandBelote;
import cards.belote.enumerations.CardBelote;
import cards.network.common.PlayerActionGame;


public final class PlayingCardBelote extends PlayerActionGame {

    private CardBelote playedCard;

    private boolean declaring;

    private boolean declaringBeloteRebelote;

    private DeclareHandBelote declare;

    private boolean refreshing;

    private int takerIndex;

    public CardBelote getPlayedCard() {
        return playedCard;
    }

    public void setPlayedCard(CardBelote _playedCard) {
        playedCard = _playedCard;
    }

    public boolean isDeclaring() {
        return declaring;
    }

    public void setDeclaring(boolean _declaring) {
        declaring = _declaring;
    }

    public boolean isDeclaringBeloteRebelote() {
        return declaringBeloteRebelote;
    }

    public void setDeclaringBeloteRebelote(boolean _declaringBeloteRebelote) {
        declaringBeloteRebelote = _declaringBeloteRebelote;
    }

    public DeclareHandBelote getDeclare() {
        return declare;
    }

    public void setDeclare(DeclareHandBelote _declare) {
        declare = _declare;
    }

    public boolean isRefreshing() {
        return refreshing;
    }

    public void setRefreshing(boolean _r) {
        this.refreshing = _r;
    }

    public int getTakerIndex() {
        return takerIndex;
    }

    public void setTakerIndex(int _takerIndex) {
        takerIndex = _takerIndex;
    }
}
