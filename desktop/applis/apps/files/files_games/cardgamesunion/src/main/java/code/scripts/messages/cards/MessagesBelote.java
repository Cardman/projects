package code.scripts.messages.cards;

import cards.belote.CheckerGameBeloteWithRules;
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
        e_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.CLASSIC_2_VS_2),"2 against 2 3,2 then 3 cards after bid");
        e_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.COINCHE_2_VS_2),"2 against 2 3,3,2 free suit");
        e_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.CLASSIC_1_VS_2),"1 against 2 3,3,2 when 8 switched cards for taker");
        e_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.COINCHE_1_VS_2),"1 against 2 3,3,2 when 8 switched cards for taker free suit");
        e_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.CLASSIC_1_VS_2_5),"1 against 2 3,3,3 when 5 switched cards for taker");
        e_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.COINCHE_1_VS_2_5),"1 against 2 3,3,3 when 5 switched cards for taker free suit");
        e_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.CLASSIC_1_VS_2_2),"1 against 2 3,2,3,2 when 2 switched cards for taker");
        e_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.COINCHE_1_VS_2_2),"1 against 2 3,2,3,2 when 2 switched cards for taker free suit");
        e_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.CLASSIC_1_VS_2_24),"1 against 2 3,2 then 3 cards after bid");
        e_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.COINCHE_1_VS_2_24),"1 against 2 3,3,2 free suit");
        e_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.CLASSIC_1_VS_1),"1 against 1 3,2,3,2 then 6 cards after bid");
        e_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.COINCHE_1_VS_1),"1 against 1 3,2,3,2 then 6 cards after bid free suit");
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
    public static TranslationsFile enCheck(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(CheckerGameBeloteWithRules.BAD_PLAYING,"Bad playing");
        e_.add(CheckerGameBeloteWithRules.BAD_DECLARING,"Bad declaring");
        e_.add(CheckerGameBeloteWithRules.BIDDING_TOO_MUCH_LOW,"Too many zero bids");
        e_.add(CheckerGameBeloteWithRules.BIDDING_LOWER,"Bad point number");
        e_.add(CheckerGameBeloteWithRules.TOO_MUCH_BIDS,"Too much bids");
        e_.add(CheckerGameBeloteWithRules.INVALID_BID,"Invalid bid");
        e_.add(CheckerGameBeloteWithRules.THERE_SHOULD_NOT_BE_ANY_TRICK,"There should not be any trick");
        e_.add(CheckerGameBeloteWithRules.ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE,"All cards are not used exactly once");
        e_.add(CheckerGameBeloteWithRules.BAD_COUNT_FOR_HANDS,"Bad count for hands");
        e_.add(CheckerGameBeloteWithRules.BAD_REP_FOR_HANDS,"Bad rep for hands");
        e_.add(CheckerGameBeloteWithRules.TRICK_WITH_BAD_COUNT,"Trick with bad count");
        e_.add(CheckerGameBeloteWithRules.BAD_COUNT_FOR_REMAINING_CARDS,"Bad count for remaining cards");
        e_.add(CheckerGameBeloteWithRules.BAD_COUNT_FOR_DEAL,"Bad count for deal");
        e_.add(CheckerGameBeloteWithRules.BAD_CARD,"Bad card");
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
        f_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.CLASSIC_2_VS_2),"2 contre 2 3,2 puis 3 cartes après contrat");
        f_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.COINCHE_2_VS_2),"2 contre 2 3,3,2 couleur libre");
        f_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.CLASSIC_1_VS_2),"1 contre 2 3,3,2 lors de 8 cartes échangées pour le preneur");
        f_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.COINCHE_1_VS_2),"1 contre 2 3,3,2 lors de 8 cartes échangées pour le preneur couleur libre");
        f_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.CLASSIC_1_VS_2_5),"1 contre 2 3,3,3 lors de 5 cartes échangées pour le preneur");
        f_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.COINCHE_1_VS_2_5),"1 contre 2 3,3,3 lors de 5 cartes échangées pour le preneur couleur libre");
        f_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.CLASSIC_1_VS_2_2),"1 contre 2 3,2,3,2 lors de 2 cartes échangées pour le preneur");
        f_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.COINCHE_1_VS_2_2),"1 contre 2 3,2,3,2 lors de 2 cartes échangées pour le preneur couleur libre");
        f_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.CLASSIC_1_VS_2_24),"1 contre 2 3,2 puis 3 cartes après contrat");
        f_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.COINCHE_1_VS_2_24),"1 contre 2 3,3,2 couleur libre");
        f_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.CLASSIC_1_VS_1),"1 contre 1 3,2,3,2 puis 6 cartes après contrat");
        f_.add(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.COINCHE_1_VS_1),"1 contre 1 3,2,3,2 puis 6 cartes après contrat couleur libre");
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
    public static TranslationsFile frCheck(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(CheckerGameBeloteWithRules.BAD_PLAYING,"Mauvais jeu");
        f_.add(CheckerGameBeloteWithRules.BAD_DECLARING,"Mauvaise annonce");
        f_.add(CheckerGameBeloteWithRules.BIDDING_TOO_MUCH_LOW,"Trop de contrats nuls");
        f_.add(CheckerGameBeloteWithRules.BIDDING_LOWER,"Mauvais nombre de points");
        f_.add(CheckerGameBeloteWithRules.TOO_MUCH_BIDS,"Trop de contrats");
        f_.add(CheckerGameBeloteWithRules.INVALID_BID,"Contrat invalide");
        f_.add(CheckerGameBeloteWithRules.THERE_SHOULD_NOT_BE_ANY_TRICK,"Il ne devrait pas avoir de pli");
        f_.add(CheckerGameBeloteWithRules.ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE,"Toutes les cartes ne sont pas utilisé une fois exactement");
        f_.add(CheckerGameBeloteWithRules.BAD_COUNT_FOR_HANDS,"Mauvais nombre pour les mains");
        f_.add(CheckerGameBeloteWithRules.BAD_REP_FOR_HANDS,"Mauvais répartition pour les mains");
        f_.add(CheckerGameBeloteWithRules.TRICK_WITH_BAD_COUNT,"Pli avec un mauvais nombre");
        f_.add(CheckerGameBeloteWithRules.BAD_COUNT_FOR_REMAINING_CARDS,"Mauvais nombre pour les cartes restantes");
        f_.add(CheckerGameBeloteWithRules.BAD_COUNT_FOR_DEAL,"Mauvais nombre pour la donne");
        f_.add(CheckerGameBeloteWithRules.BAD_CARD,"Mauvaise carte");
        return f_;
    }
}
