package cards.solitaire;

import code.util.core.*;

public final class SolitaireCardsRetrieverUtil {
    private SolitaireCardsRetrieverUtil() {
    }
    public static CardSolitaire toCardSolitaire(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (CardSolitaire c: HandSolitaire.pileBase()) {
            if (StringUtil.quickEq(r_,Integer.toString(c.getId().nb()))) {
                return c;
            }
        }
        return CardSolitaire.WHITE;
    }
}
