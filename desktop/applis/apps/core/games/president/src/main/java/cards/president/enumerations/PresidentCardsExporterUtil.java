package cards.president.enumerations;

import cards.consts.CouleurValeur;

public final class PresidentCardsExporterUtil {
    public static final String EQUALTY = "0";
    public static final String PLAYING = "1";
    private PresidentCardsExporterUtil() {
    }

    public static String fromEqualtyPlaying(EqualtyPlaying _role) {
        return _role.getSt();
    }
    public static String fromCardPresident(CardPresident _ct) {
        return CouleurValeur.fromCard(_ct.getId());
    }
}
