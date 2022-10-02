package cards.belote.enumerations;

import code.format.Format;

public final class BeloteResoucesAccess {

    public static final String NOM_DOSSIER = "resources_cards/const_enum";
    public static final String NOM_FICHIER = "belote.txt";
    public static final String BELOTE_TRUMP_PART = "cards.belote.enumerations.BeloteTrumpPartner";
    public static final String BELOTE_BID = "cards.belote.enumerations.BidBelote";
    public static final String BELOTE_BONUS = "cards.belote.enumerations.BonusBelote";
    public static final String BELOTE_CARD = "cards.belote.enumerations.CardBelote";
    public static final String BELOTE_DEAL = "cards.belote.enumerations.DealingBelote";
    public static final String BELOTE_DECLARES = "cards.belote.enumerations.DeclaresBelote";
    public static final String BELOTE_DECLARES_BEL_REB = "cards.belote.enumerations.DeclaresBeloteRebelote";
    private BeloteResoucesAccess(){}

    public static String key(BidBelote _b) {
        return Format.concatParts(BeloteResoucesAccess.BELOTE_BID, BeloteCardsExporterUtil.fromBidBelote(_b));
    }
}
