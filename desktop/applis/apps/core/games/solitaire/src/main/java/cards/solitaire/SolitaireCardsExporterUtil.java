package cards.solitaire;

import cards.consts.CouleurValeur;

public final class SolitaireCardsExporterUtil {
    private SolitaireCardsExporterUtil() {
    }
    public static String fromCardSolitaire(CardSolitaire _ct) {
        return CouleurValeur.fromCard(_ct.getId());
    }
}
