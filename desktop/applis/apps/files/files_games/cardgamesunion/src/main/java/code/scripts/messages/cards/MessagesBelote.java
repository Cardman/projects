package code.scripts.messages.cards;

import cards.belote.enumerations.*;
import code.sml.util.TranslationsFile;

public final class MessagesBelote {
    public static final String BELOTE_OVER_TRUMP_FOE = "0";
    public static final String BELOTE_OVER_TRUMP_PARTNER = "1";
    public static final String BELOTE_PLAY_STRONGER_CARD = "2";
    public static final String BELOTE_PLAY_SUIT = "3";
    public static final String BELOTE_TRUMP_FOE = "4";
    public static final String BELOTE_UNDER_TRUMP_FOE = "5";
    public static final String BELOTE_UNDER_TRUMP_PARTNER = "6";
    private MessagesBelote() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(BeloteCardsExporterUtil.BID+BeloteCardsExporterUtil.fromBidBelote(BidBelote.FOLD),"Pass");
        e_.add(BeloteCardsExporterUtil.BID+BeloteCardsExporterUtil.fromBidBelote(BidBelote.SUIT),"Trump suit");
        e_.add(BeloteCardsExporterUtil.BID+BeloteCardsExporterUtil.fromBidBelote(BidBelote.OTHER_SUIT),"Other trump suit");
        e_.add(BeloteCardsExporterUtil.BID+BeloteCardsExporterUtil.fromBidBelote(BidBelote.NO_TRUMP),"No trump");
        e_.add(BeloteCardsExporterUtil.BID+BeloteCardsExporterUtil.fromBidBelote(BidBelote.ALL_TRUMP),"All trump");
        e_.add(BeloteCardsExporterUtil.TRUMPING+BeloteCardsExporterUtil.fromBeloteTrumpPartner(BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP),"Neither overtrumping nor undertrumping");
        e_.add(BeloteCardsExporterUtil.TRUMPING+BeloteCardsExporterUtil.fromBeloteTrumpPartner(BeloteTrumpPartner.OVERTRUMP_ONLY),"Only overtrumping");
        e_.add(BeloteCardsExporterUtil.TRUMPING+BeloteCardsExporterUtil.fromBeloteTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_ONLY),"Only undertrumping");
        e_.add(BeloteCardsExporterUtil.TRUMPING+BeloteCardsExporterUtil.fromBeloteTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP),"Overtrumping and undertrumping");
        e_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.UNDEFINED),"Undefined");
        e_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_1),"Four aces");
        e_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_KING),"Four kings");
        e_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_QUEEN),"Four queens");
        e_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_JACK),"Four jacks");
        e_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_10),"Four tens");
        e_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_9),"Four nines");
        e_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_8),"Four eights");
        e_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_7),"Four sevens");
        e_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.HUNDRED),"Flush with five cards of a same suit");
        e_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FIFTY),"Flush with four cards of a same suit");
        e_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.THIRTY),"Flush with three cards of a same suit");
        e_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.CLASSIC_2_VS_2),"2 against 2");
        e_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.COINCHE_2_VS_2),"2 against 2");
        e_.add(BeloteCardsExporterUtil.LAST_TRICK,"Ten points at last trick");
        e_.add(BeloteCardsExporterUtil.DECLARE_PAIR,"Belote rebelote");
        return e_;
    }
    public static TranslationsFile enGame(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(BELOTE_OVER_TRUMP_FOE,"You must overtrump with a card of the suit {0} over your foe.");
        e_.add(BELOTE_OVER_TRUMP_PARTNER,"You must overtrump with a card of the suit {0} over your partner.");
        e_.add(BELOTE_PLAY_STRONGER_CARD,"You must play a stronger card than {0}.");
        e_.add(BELOTE_PLAY_SUIT,"You must follow a card of the suit {0}.");
        e_.add(BELOTE_TRUMP_FOE,"You must trump with a card of the suit {0} over your foe.");
        e_.add(BELOTE_UNDER_TRUMP_FOE,"You must undertrump with a card of the suit {0} under your foe.");
        e_.add(BELOTE_UNDER_TRUMP_PARTNER,"You must undertrump with a card of the suit {0} under your partner.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(BeloteCardsExporterUtil.BID+BeloteCardsExporterUtil.fromBidBelote(BidBelote.FOLD),"Passe");
        f_.add(BeloteCardsExporterUtil.BID+BeloteCardsExporterUtil.fromBidBelote(BidBelote.SUIT),"Couleur");
        f_.add(BeloteCardsExporterUtil.BID+BeloteCardsExporterUtil.fromBidBelote(BidBelote.OTHER_SUIT),"Autre couleur");
        f_.add(BeloteCardsExporterUtil.BID+BeloteCardsExporterUtil.fromBidBelote(BidBelote.NO_TRUMP),"Sans atout");
        f_.add(BeloteCardsExporterUtil.BID+BeloteCardsExporterUtil.fromBidBelote(BidBelote.ALL_TRUMP),"Tout atout");
        f_.add(BeloteCardsExporterUtil.TRUMPING+BeloteCardsExporterUtil.fromBeloteTrumpPartner(BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP),"Ni sur - coupe ni sous - coupe obligatoire");
        f_.add(BeloteCardsExporterUtil.TRUMPING+BeloteCardsExporterUtil.fromBeloteTrumpPartner(BeloteTrumpPartner.OVERTRUMP_ONLY),"Sur - couper uniquement");
        f_.add(BeloteCardsExporterUtil.TRUMPING+BeloteCardsExporterUtil.fromBeloteTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_ONLY),"Sous - couper uniquement");
        f_.add(BeloteCardsExporterUtil.TRUMPING+BeloteCardsExporterUtil.fromBeloteTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP),"Sous - couper et sur - couper");
        f_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.UNDEFINED),"Indéterminé");
        f_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_1),"Carré as");
        f_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_KING),"Carré rois");
        f_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_QUEEN),"Carré dames");
        f_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_JACK),"Carré valets");
        f_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_10),"Carré dix");
        f_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_9),"Carré neuf");
        f_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_8),"Carré huit");
        f_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_7),"Carré sept");
        f_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.HUNDRED),"Suite avec cinq cartes de même couleur");
        f_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FIFTY),"Suite avec quatre cartes de même couleur");
        f_.add(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.THIRTY),"Suite avec trois cartes de même couleur");
        f_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.CLASSIC_2_VS_2),"2 contre 2");
        f_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.COINCHE_2_VS_2),"2 contre 2");
        f_.add(BeloteCardsExporterUtil.LAST_TRICK,"Dix de der");
        f_.add(BeloteCardsExporterUtil.DECLARE_PAIR,"Belote rebelote");
        return f_;
    }

    public static TranslationsFile frGame(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(BELOTE_OVER_TRUMP_FOE,"Vous devez sur-couper avec une carte de la couleur {0} au dessus de votre adversaire.");
        f_.add(BELOTE_OVER_TRUMP_PARTNER,"Vous devez sur-couper avec une carte de la couleur {0} en dessous de votre partenaire.");
        f_.add(BELOTE_PLAY_STRONGER_CARD,"Vous devez jouer une carte plus forte que {0}.");
        f_.add(BELOTE_PLAY_SUIT,"Vous devez fournir une carte de la couleur {0}.");
        f_.add(BELOTE_TRUMP_FOE,"Vous devez couper avec une carte de la couleur {0} au dessus de votre adversaire.");
        f_.add(BELOTE_UNDER_TRUMP_FOE,"Vous devez sous-couper avec une carte de la couleur {0} en dessous de votre adversaire.");
        f_.add(BELOTE_UNDER_TRUMP_PARTNER,"Vous devez sous-couper avec une carte de la couleur {0} en dessous de votre partenaire.");
        return f_;
    }
}
