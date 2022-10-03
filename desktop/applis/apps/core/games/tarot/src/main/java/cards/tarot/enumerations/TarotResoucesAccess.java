package cards.tarot.enumerations;

import code.format.Format;

public final class TarotResoucesAccess {

    public static final String NOM_DOSSIER = "resources_cards/const_enum";
    public static final String NOM_FICHIER = "tarot.txt";
    public static final String TAROT_CHOICE = "cards.tarot.enumerations.ChoiceTarot";
    public static final String TAROT_BID = "cards.tarot.enumerations.BidTarot";
    public static final String TAROT_BONUS = "cards.tarot.enumerations.BonusTarot";
    public static final String TAROT_CARD = "cards.tarot.enumerations.CardTarot";
    public static final String TAROT_DEAL = "cards.tarot.enumerations.DealingTarot";
    public static final String TAROT_MODE = "cards.tarot.enumerations.ModeTarot";
    public static final String TAROT_END = "cards.tarot.enumerations.EndDealTarot";
    public static final String TAROT_HANDFULS = "cards.tarot.enumerations.Handfuls";
    public static final String TAROT_MISERES = "cards.tarot.enumerations.Miseres";

    private TarotResoucesAccess(){}

    public static String key(BidTarot _b) {
        return Format.concatParts(TAROT_BID, TarotCardsExporterUtil.fromBidTarot(_b));
    }

    public static String key(ModeTarot _b) {
        return Format.concatParts(TAROT_MODE, TarotCardsExporterUtil.fromModeTarot(_b));
    }

    public static String key(DealingTarot _b) {
        return Format.concatParts(TAROT_DEAL, TarotCardsExporterUtil.fromDealingTarot(_b));
    }

    public static String key(Handfuls _b) {
        return Format.concatParts(TAROT_HANDFULS, TarotCardsExporterUtil.fromHandfuls(_b));
    }

    public static String key(EndDealTarot _b) {
        return Format.concatParts(TAROT_END, TarotCardsExporterUtil.fromEndDealTarot(_b));
    }

    public static String key(Miseres _b) {
        return Format.concatParts(TAROT_MISERES, TarotCardsExporterUtil.fromMiseres(_b));
    }

    public static String key(CardTarot _b) {
        return Format.concatParts(TAROT_CARD, TarotCardsExporterUtil.fromCardTarot(_b));
    }
}
