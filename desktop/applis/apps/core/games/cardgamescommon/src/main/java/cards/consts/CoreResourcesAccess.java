package cards.consts;

import code.format.Format;

public final class CoreResourcesAccess {

    public static final String NOM_DOSSIER = "resources_cards/const_enum";
    public static final String NOM_FICHIER = "common.txt";
    public static final String SYMBOL_CARDS_TXT = "symbol_cards.txt";
    public static final String CHARS = "cards.consts.CardChar";
    public static final String MIX = "cards.consts.MixCardsChoice";
    public static final String STATUS = "cards.consts.Status";
    public static final String SUIT = "cards.consts.Suit";
    private CoreResourcesAccess(){}

    public static String key(Suit _b) {
        return Format.concatParts(CoreResourcesAccess.SUIT, EnumCardsExporterUtil.fromSuit(_b));
    }

    public static String key(MixCardsChoice _b) {
        return Format.concatParts(CoreResourcesAccess.MIX, EnumCardsExporterUtil.fromMixCardsChoice(_b));
    }
}
