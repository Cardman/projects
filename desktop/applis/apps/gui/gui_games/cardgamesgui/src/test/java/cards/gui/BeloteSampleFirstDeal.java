package cards.gui;

import cards.belote.DealBelote;
import cards.belote.GameBelote;
import cards.belote.HandBelote;
import cards.belote.RulesBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.gui.containers.ContainerBelote;
import cards.gui.containers.IntFirstDealBelote;
import code.util.CustList;

public final class BeloteSampleFirstDeal implements IntFirstDealBelote {
    @Override
    public GameBelote deal(ContainerBelote _container, RulesBelote _rules, long _nb) {
        DealBelote donne_=dealStdClassic(0);
        return new GameBelote(GameType.RANDOM,donne_,_rules);
    }

    private static DealBelote dealStdClassic(int _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        firstHands(hands_);
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.SPADE_7);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.CLUB_7);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.HEART_7);
        hands_.add(hand_);
        return new DealBelote(hands_, (byte) _dealer);
    }

    private static void firstHands(CustList<HandBelote> _hands) {
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.HEART_10);
        hand_.ajouter(CardBelote.HEART_KING);
        _hands.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.SPADE_KING);
        _hands.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        _hands.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.CLUB_KING);
        _hands.add(hand_);
    }
}
