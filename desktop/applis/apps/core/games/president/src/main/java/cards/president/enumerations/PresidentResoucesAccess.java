package cards.president.enumerations;

import code.format.Format;

public final class PresidentResoucesAccess {

    public static final String NOM_DOSSIER = "resources_cards/const_enum";
    public static final String NOM_FICHIER = "president.txt";
    public static final String PRESIDENT_CARD = "cards.president.enumerations.CardPresident";
    public static final String PRESIDENT_EQUAL_PLAY = "cards.president.enumerations.EqualtyPlaying";
    public static final String PRESIDENT_PLAY = "cards.president.enumerations.Playing";

    private PresidentResoucesAccess(){}

    public static String key(EqualtyPlaying _b) {
        return Format.concatParts(PRESIDENT_EQUAL_PLAY, PresidentCardsExporterUtil.fromEqualtyPlaying(_b));
    }

    public static String key(CardPresident _b) {
        return Format.concatParts(PRESIDENT_CARD, PresidentCardsExporterUtil.fromCardPresident(_b));
    }
}
