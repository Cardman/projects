package code.scripts.messages.cards;

import cards.tarot.CheckerGameTarotWithRules;
import cards.tarot.enumerations.*;
import code.sml.util.TranslationsFile;

public final class MessagesTarot {
    public static final String TAROT_HANDFUL_EXCUSE = "0";
    public static final String TAROT_HANDFUL_NOT_ENOUGH_TRUMPS = "1";
    public static final String TAROT_HANDFUL_TOO_MANY_TRUMPS = "2";
    public static final String TAROT_NO_DISCARDED_CHARACTER = "3";
    public static final String TAROT_NO_DISCARDED_OUDLER = "4";
    public static final String TAROT_NO_DISCARDED_TRUMP = "5";
    public static final String TAROT_OVERTRUMP = "6";
    public static final String TAROT_PLAY_FIRST_TRICK = "7";
    public static final String TAROT_PLAY_STRONGER_CARD = "8";
    public static final String TAROT_PLAY_SUIT = "9";
    public static final String TAROT_TOO_MANY_CARDS = "10";
    public static final String TAROT_TRUMP = "11";
    public static final String TAROT_UNDERTRUMP = "12";
    private MessagesTarot() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.FOLD),"Pass");
        e_.add(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.TAKE),"Take");
        e_.add(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.GUARD),"Guard");
        e_.add(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.GUARD_WITHOUT),"Guard without");
        e_.add(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.GUARD_AGAINST),"Guard against");
        e_.add(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.SLAM),"Slam");
        e_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_1_VS_1),"1 against 1");
        e_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_1_VS_2),"1 against 2");
        e_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_1_VS_3),"1 against 3");
        e_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_2_WITHOUT_CALL),"2 against 2 without call");
        e_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_2_CALL_KING),"2 against 2 call king first");
        e_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_2_CALL_CHAR),"2 against 2 call any character");
        e_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_1_VS_4),"1 against 4");
        e_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_3_CALL_KING),"2 against 3 call king first");
        e_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_3_CALL_CHAR),"2 against 3 call any character");
        e_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL),"2 against 4 without call");
        e_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_4_CALL_KING),"2 against 4 call king first");
        e_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_4_CALL_CHAR),"2 against 4 call any character");
        e_.add(TarotCardsExporterUtil.MODE+TarotCardsExporterUtil.fromModeTarot(ModeTarot.NORMAL),"Normal");
        e_.add(TarotCardsExporterUtil.MODE+TarotCardsExporterUtil.fromModeTarot(ModeTarot.NORMAL_WITH_MISERE),"Normal with discarding");
        e_.add(TarotCardsExporterUtil.MODE+TarotCardsExporterUtil.fromModeTarot(ModeTarot.NORMAL_WITH_ONE_FOR_ONE),"Normal with one for one");
        e_.add(TarotCardsExporterUtil.MODE+TarotCardsExporterUtil.fromModeTarot(ModeTarot.MISERE),"Discarding");
        e_.add(TarotCardsExporterUtil.MODE+TarotCardsExporterUtil.fromModeTarot(ModeTarot.ONE_FOR_ONE),"One for one");
        e_.add(TarotCardsExporterUtil.END_DEAL+TarotCardsExporterUtil.fromEndDealTarot(EndDealTarot.ATTACK_LOOSE),"The attack team loses");
        e_.add(TarotCardsExporterUtil.END_DEAL+TarotCardsExporterUtil.fromEndDealTarot(EndDealTarot.ATTACK_WIN),"The attack teams wins");
        e_.add(TarotCardsExporterUtil.END_DEAL+TarotCardsExporterUtil.fromEndDealTarot(EndDealTarot.ZERO),"No team wins");
        e_.add(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.NO),"No Handful");
        e_.add(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.ONE),"Handful");
        e_.add(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.TWO),"Double Handful");
        e_.add(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.THREE),"Triple Handful");
        e_.add(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.FOUR),"Quadruple Handful");
        e_.add(TarotCardsExporterUtil.MISERES+TarotCardsExporterUtil.fromMiseres(Miseres.TRUMP),"Trump");
        e_.add(TarotCardsExporterUtil.MISERES+TarotCardsExporterUtil.fromMiseres(Miseres.POINT),"Points");
        e_.add(TarotCardsExporterUtil.MISERES+TarotCardsExporterUtil.fromMiseres(Miseres.CHARACTER),"Character");
        e_.add(TarotCardsExporterUtil.MISERES+TarotCardsExporterUtil.fromMiseres(Miseres.SUIT),"Suit");
        e_.add(TarotCardsExporterUtil.MISERES+TarotCardsExporterUtil.fromMiseres(Miseres.LOW_CARDS),"Low cards");
        e_.add(TarotCardsExporterUtil.SMALL_BOUND,"Trump Ace to end");
        e_.add(TarotCardsExporterUtil.SLAM,"Slam");
        return e_;
    }
    public static TranslationsFile enGame(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(TAROT_HANDFUL_EXCUSE,"The Excuse can be shown in a handful only if no trump can fill the handful.");
        e_.add(TAROT_HANDFUL_NOT_ENOUGH_TRUMPS,"There are {0} less trumps in the handful than expected.");
        e_.add(TAROT_HANDFUL_TOO_MANY_TRUMPS,"There are {0} more trumps in the handful than expected.");
        e_.add(TAROT_NO_DISCARDED_CHARACTER,"You cannot discard {0} because it is a king.");
        e_.add(TAROT_NO_DISCARDED_OUDLER,"You cannot discard {0} because it is an oudler.");
        e_.add(TAROT_NO_DISCARDED_TRUMP,"You cannot discard {0} because it is a trump and it exists at least one always discardable card which will not be discarded.");
        e_.add(TAROT_OVERTRUMP,"You must overtrump with a trump.");
        e_.add(TAROT_PLAY_FIRST_TRICK,"You cannot lead the called suit {0} at the first trick except for {1} or if you have no other card even by playing just after the Excuse has been led.");
        e_.add(TAROT_PLAY_STRONGER_CARD,"You must play a stronger card than {0}.");
        e_.add(TAROT_PLAY_SUIT,"You must follow a card of the suit {0}.");
        e_.add(TAROT_TOO_MANY_CARDS,"You cannot discard one more card because the deck of discarded cards is full.");
        e_.add(TAROT_TRUMP,"You must trump with a trump.");
        e_.add(TAROT_UNDERTRUMP,"You must undertrump with a trump.");
        return e_;
    }
    public static TranslationsFile enCheck(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(CheckerGameTarotWithRules.BAD_DECLARING,"Bad declaring");
        e_.add(CheckerGameTarotWithRules.BAD_PLAYING,"Bad playing");
        e_.add(CheckerGameTarotWithRules.A_CARD_IS_MISSING_OR_EXCEDING_FOR_DISCARDING,"A card is missing or exceding for discarding");
        e_.add(CheckerGameTarotWithRules.A_CARD_IS_MISSING_FOR_DISCARDING,"A card is missing for discarding");
        e_.add(CheckerGameTarotWithRules.THIS_CARD_IS_NOT_DISCARDABLE,"This card is not discardable");
        e_.add(CheckerGameTarotWithRules.A_CARD_MUST_BE_CALLED_AMONG_ALL_POSSIBLE,"A card must be called among all possible cards");
        e_.add(CheckerGameTarotWithRules.THERE_SHOULD_NOT_BE_ANY_TRICK,"There should not be any trick");
        e_.add(CheckerGameTarotWithRules.NO_ALLOWED_BID,"no allowed bid");
        e_.add(CheckerGameTarotWithRules.ERROR_CALLING_MUST_BE_BEFORE_DISCARD,"calling must be before discard");
        e_.add(CheckerGameTarotWithRules.ERROR_BID_MUST_BE_BEFORE_CALLING,"bid must be before calling");
        e_.add(CheckerGameTarotWithRules.TOO_MUCH_BIDS,"too much bids");
        e_.add(CheckerGameTarotWithRules.INVALID_BID,"invalid bid");
        e_.add(CheckerGameTarotWithRules.ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE,"All cards are not used exactly once");
        e_.add(CheckerGameTarotWithRules.BAD_COUNT_FOR_HANDS,"Bad count for hands");
        e_.add(CheckerGameTarotWithRules.TRICK_WITH_BAD_COUNT,"trick with bad count");
        e_.add(CheckerGameTarotWithRules.BAD_COUNT_FOR_REMAINING_CARDS,"Bad count for remaining cards");
        e_.add(CheckerGameTarotWithRules.BAD_COUNT_FOR_DEAL,"Bad count for deal");
        e_.add(CheckerGameTarotWithRules.INVALID_RULES,"invalid rules");
        e_.add(CheckerGameTarotWithRules.BAD_CARD,"bad card");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.FOLD),"Passe");
        f_.add(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.TAKE),"Petite");
        f_.add(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.GUARD),"Garde");
        f_.add(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.GUARD_WITHOUT),"Garde sans");
        f_.add(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.GUARD_AGAINST),"Garde contre");
        f_.add(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.SLAM),"Chelem");
        f_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_1_VS_1),"1 contre 1");
        f_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_1_VS_2),"1 contre 2");
        f_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_1_VS_3),"1 contre 3");
        f_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_2_WITHOUT_CALL),"2 contre 2 sans appel");
        f_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_2_CALL_KING),"2 contre 2 appel au roi");
        f_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_2_CALL_CHAR),"2 contre 2 appel à la figure");
        f_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_1_VS_4),"1 contre 4");
        f_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_3_CALL_KING),"2 contre 3 appel au roi");
        f_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_3_CALL_CHAR),"2 contre 3 appel à la figure");
        f_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL),"2 contre 4 sans appel");
        f_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_4_CALL_KING),"2 contre 4 appel au roi");
        f_.add(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_4_CALL_CHAR),"2 contre 4 appel à la figure");
        f_.add(TarotCardsExporterUtil.MODE+TarotCardsExporterUtil.fromModeTarot(ModeTarot.NORMAL),"Normal");
        f_.add(TarotCardsExporterUtil.MODE+TarotCardsExporterUtil.fromModeTarot(ModeTarot.NORMAL_WITH_MISERE),"Normal avec misère");
        f_.add(TarotCardsExporterUtil.MODE+TarotCardsExporterUtil.fromModeTarot(ModeTarot.NORMAL_WITH_ONE_FOR_ONE),"Normal avec chacun pour soi");
        f_.add(TarotCardsExporterUtil.MODE+TarotCardsExporterUtil.fromModeTarot(ModeTarot.MISERE),"Misère");
        f_.add(TarotCardsExporterUtil.MODE+TarotCardsExporterUtil.fromModeTarot(ModeTarot.ONE_FOR_ONE),"Chacun pour soi");
        f_.add(TarotCardsExporterUtil.END_DEAL+TarotCardsExporterUtil.fromEndDealTarot(EndDealTarot.ATTACK_LOOSE),"L'attaque perd");
        f_.add(TarotCardsExporterUtil.END_DEAL+TarotCardsExporterUtil.fromEndDealTarot(EndDealTarot.ATTACK_WIN),"L'attaque gagne");
        f_.add(TarotCardsExporterUtil.END_DEAL+TarotCardsExporterUtil.fromEndDealTarot(EndDealTarot.ZERO),"Match nul");
        f_.add(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.NO),"Aucune poignée");
        f_.add(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.ONE),"Poignée");
        f_.add(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.TWO),"Double Poignée");
        f_.add(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.THREE),"Triple Poignée");
        f_.add(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.FOUR),"Quadruple Poignée");
        f_.add(TarotCardsExporterUtil.MISERES+TarotCardsExporterUtil.fromMiseres(Miseres.TRUMP),"Atout");
        f_.add(TarotCardsExporterUtil.MISERES+TarotCardsExporterUtil.fromMiseres(Miseres.POINT),"Tete");
        f_.add(TarotCardsExporterUtil.MISERES+TarotCardsExporterUtil.fromMiseres(Miseres.CHARACTER),"Figure");
        f_.add(TarotCardsExporterUtil.MISERES+TarotCardsExporterUtil.fromMiseres(Miseres.SUIT),"Couleur");
        f_.add(TarotCardsExporterUtil.MISERES+TarotCardsExporterUtil.fromMiseres(Miseres.LOW_CARDS),"Cartes basses");
        f_.add(TarotCardsExporterUtil.SMALL_BOUND,"Petit au bout");
        f_.add(TarotCardsExporterUtil.SLAM,"Chelem");
        return f_;
    }

    public static TranslationsFile frGame(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(TAROT_HANDFUL_EXCUSE,"L'Excuse ne peut être montrée dans une poignée que si aucun atout ne peut compléter la poignée.");
        f_.add(TAROT_HANDFUL_NOT_ENOUGH_TRUMPS,"Il y a {0} atous dans la poignée en moins.");
        f_.add(TAROT_HANDFUL_TOO_MANY_TRUMPS,"Il y a {0} atous dans la poignée en plus.");
        f_.add(TAROT_NO_DISCARDED_CHARACTER,"Vous ne pouvez pas écarter {0} car c''est un roi.");
        f_.add(TAROT_NO_DISCARDED_OUDLER,"Vous ne pouvez pas écarter {0} car c''est un Bout.");
        f_.add(TAROT_NO_DISCARDED_TRUMP,"Vous ne pouvez pas écarter {0} car c''est un atout et il existe au moins une carte toujours écartable qui ne sera pas écartée.");
        f_.add(TAROT_OVERTRUMP,"Vous devez sur - couper avec un atout.");
        f_.add(TAROT_PLAY_FIRST_TRICK,"Vous ne pouvez pas entamer le premier pli avec la couleur appelée {0} sauf {1} ou si vous n''avez pas d''autre carte même en jouant juste après que l''Excuse est entamée.");
        f_.add(TAROT_PLAY_STRONGER_CARD,"Vous devez jouer une carte plus forte que {0}.");
        f_.add(TAROT_PLAY_SUIT,"Vous devez fournir une carte de la couleur {0}.");
        f_.add(TAROT_TOO_MANY_CARDS,"Vous ne pouvez pas écarter une carte de plus car l'écart est plein.");
        f_.add(TAROT_TRUMP,"Vous devez couper avec un atout.");
        f_.add(TAROT_UNDERTRUMP,"Vous devez sous - couper avec un atout.");
        return f_;
    }
    public static TranslationsFile frCheck(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(CheckerGameTarotWithRules.BAD_DECLARING,"Mauvaise annonce declaring");
        f_.add(CheckerGameTarotWithRules.BAD_PLAYING,"Mauvais jeu");
        f_.add(CheckerGameTarotWithRules.A_CARD_IS_MISSING_OR_EXCEDING_FOR_DISCARDING,"Une carte manque ou excède pour l'écart");
        f_.add(CheckerGameTarotWithRules.A_CARD_IS_MISSING_FOR_DISCARDING,"Une carte manque pour l'écart");
        f_.add(CheckerGameTarotWithRules.THIS_CARD_IS_NOT_DISCARDABLE,"Cette carte n'est pas écartable");
        f_.add(CheckerGameTarotWithRules.A_CARD_MUST_BE_CALLED_AMONG_ALL_POSSIBLE,"Une carte doit être appelée parmi toutes les cartes possibles");
        f_.add(CheckerGameTarotWithRules.THERE_SHOULD_NOT_BE_ANY_TRICK,"Il ne doit pas y avoir un pli");
        f_.add(CheckerGameTarotWithRules.NO_ALLOWED_BID,"Pas de contrat autorisé");
        f_.add(CheckerGameTarotWithRules.ERROR_CALLING_MUST_BE_BEFORE_DISCARD,"L'appel doit être avant l'écart");
        f_.add(CheckerGameTarotWithRules.ERROR_BID_MUST_BE_BEFORE_CALLING,"Le contrat doit être avant l'appel");
        f_.add(CheckerGameTarotWithRules.TOO_MUCH_BIDS,"Trop de contrats");
        f_.add(CheckerGameTarotWithRules.INVALID_BID,"Contrat invalide");
        f_.add(CheckerGameTarotWithRules.ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE,"Toutes les cartes ne sont pas utilisé une fois exactement");
        f_.add(CheckerGameTarotWithRules.BAD_COUNT_FOR_HANDS,"Mauvais nombre pour les mains");
        f_.add(CheckerGameTarotWithRules.TRICK_WITH_BAD_COUNT,"Pli avec mauvais nombre");
        f_.add(CheckerGameTarotWithRules.BAD_COUNT_FOR_REMAINING_CARDS,"Mauvais nombre pour les cartes restantes");
        f_.add(CheckerGameTarotWithRules.BAD_COUNT_FOR_DEAL,"Mauvais nombre pour la donne");
        f_.add(CheckerGameTarotWithRules.INVALID_RULES,"Règles invalides");
        f_.add(CheckerGameTarotWithRules.BAD_CARD,"Mauvaise carte");
        return f_;
    }
}
