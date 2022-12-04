package cards.network.belote.displaying.players;
import cards.belote.DeclareHandBelote;
import cards.belote.enumerations.CardBelote;
import cards.network.common.PlayerActionGame;


public final class RefreshHandPlayingBelote extends PlayerActionGame {

    private CardBelote card;

    private boolean declaring;

    private boolean declaringBeloteRebelote;

    private DeclareHandBelote declare;

    public CardBelote getCard() {
        return card;
    }

    public void setCard(CardBelote _card) {
        card = _card;
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
}
