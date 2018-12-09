package cards.network.president.displaying.errors;
import cards.president.enumerations.CardPresident;


public final class ErrorPlayingPresident {

    private CardPresident card;

    private String reason;

    private boolean passIssue;

    public CardPresident getCard() {
        return card;
    }

    public void setCard(CardPresident _card) {
        card = _card;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String _reason) {
        reason = _reason;
    }

    public boolean isPassIssue() {
        return passIssue;
    }

    public void setPassIssue(boolean _passIssue) {
        passIssue = _passIssue;
    }
}
