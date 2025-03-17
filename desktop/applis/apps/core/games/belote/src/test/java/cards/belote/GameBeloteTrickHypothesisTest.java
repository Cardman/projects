package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Order;
import cards.consts.PossibleTrickWinner;
import cards.consts.Suit;
import code.util.*;
import code.util.core.BoolVal;
import org.junit.Test;

public final class GameBeloteTrickHypothesisTest extends CommonGameBelote {
    @Test
    public void beatByTrumpNormalSuitStrength1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.DIAMOND_7);
        possibleSure(t_, 1, CardBelote.HEART_QUEEN);
        Ints beat_ = new Ints();
        beat_.add(1);
        assertTrue(beatByTrumpNormalSuitStrength(t_,beat_));
    }
    @Test
    public void beatByTrumpNormalSuitStrength2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        Ints beat_ = new Ints();
        beat_.add(1);
        assertTrue(beatByTrumpNormalSuitStrength(t_,beat_));
    }
    @Test
    public void beatByTrumpNormalSuitStrength3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_7);
        Ints beat_ = new Ints();
        beat_.add(1);
        assertTrue(beatByTrumpNormalSuitStrength(t_,beat_));
    }
    @Test
    public void beatByTrumpNormalSuitStrength4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_KING);
        Ints beat_ = new Ints();
        beat_.add(1);
        assertTrue(!beatByTrumpNormalSuitStrength(t_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_1);
        possibleSure(t_, 0, CardBelote.HEART_10);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(!beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_7);
        possibleSure(t_, 0, CardBelote.HEART_10);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_7);
        possibleSure(t_, 0, CardBelote.HEART_QUEEN);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(!beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_8);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_JACK);
        playable_.ajouter(CardBelote.DIAMOND_8);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_7);
        possibleSure(t_, 0, CardBelote.HEART_JACK);
        possibleSure(t_, 0, CardBelote.DIAMOND_8);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(!beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit5Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_8);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.DIAMOND_8);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_7);
        possibleSure(t_, 0, CardBelote.DIAMOND_8);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(!beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit6Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        HandBelote playable_ = new HandBelote();
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_7);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(!beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_QUEEN);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_10);
        Ints beat_ = new Ints();
        beat_.add(1);
        assertTrue(!ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_QUEEN);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_10);
        possibleSure(t_, 1, CardBelote.DIAMOND_8);
        Ints beat_ = new Ints();
        beat_.add(1);
        assertTrue(ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_QUEEN);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        Ints beat_ = new Ints();
        beat_.add(1);
        assertTrue(ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_QUEEN);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_10);
        possibleSure(t_, 1, CardBelote.DIAMOND_1);
        Ints beat_ = new Ints();
        beat_.add(1);
        assertTrue(!ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv5Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_QUEEN);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_10);
        Ints beat_ = new Ints();
        beat_.add(1);
        assertTrue(!ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv6Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_QUEEN);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.DIAMOND_1);
        Ints beat_ = new Ints();
        beat_.add(1);
        assertTrue(!ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv7Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setBid(BidBelote.NO_TRUMP);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_QUEEN);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_10);
        Ints beat_ = new Ints();
        beat_.add(1);
        assertTrue(ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv8Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setBid(BidBelote.NO_TRUMP);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_QUEEN);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.DIAMOND_10);
        Ints beat_ = new Ints();
        beat_.add(1);
        assertTrue(!ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void existeJoueurNonJoueBattantAdv1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_1);
        possibleSure(t_, 0, CardBelote.HEART_10);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(!existeJoueurNonJoueBattantAdv(t_,dom_,beat_));
    }
    @Test
    public void existeJoueurNonJoueBattantAdv2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_1);
        possibleSure(t_, 0, CardBelote.HEART_10);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(!existeJoueurNonJoueBattantAdv(t_,dom_,beat_));
    }
    @Test
    public void existeJoueurNonJoueBattantAdv3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.DIAMOND_8);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_1);
        playable_.ajouter(CardBelote.DIAMOND_8);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_10);
        possibleSure(t_, 0, CardBelote.HEART_1);
        possibleSure(t_, 0, CardBelote.DIAMOND_8);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(!existeJoueurNonJoueBattantAdv(t_,dom_,beat_));
    }
    @Test
    public void existeJoueurNonJoueBattantAdv4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_1);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_1);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_10);
        possibleSure(t_, 0, CardBelote.HEART_1);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(existeJoueurNonJoueBattantAdv(t_,dom_,beat_));
    }
    @Test
    public void getPossibleOverTrump1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_1);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_1);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_10);
        possibleSure(t_, 0, CardBelote.HEART_1);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertSame(PossibleTrickWinner.TEAM, GameBeloteTrickHypothesis.getPossibleOverTrump(t_,dom_,beat_,PossibleTrickWinner.TEAM,PossibleTrickWinner.FOE_TEAM));
    }
    @Test
    public void getPossibleOverTrump2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_1);
        possibleSure(t_, 0, CardBelote.HEART_10);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertSame(PossibleTrickWinner.FOE_TEAM, GameBeloteTrickHypothesis.getPossibleOverTrump(t_,dom_,beat_,PossibleTrickWinner.TEAM,PossibleTrickWinner.FOE_TEAM));
    }
    @Test
    public void getPossibleOverTrump3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_10);
        Ints beat_ = new Ints();
        beat_.add(0);
        Ints dom_ = new Ints();
        dom_.add(3);
        assertSame(PossibleTrickWinner.TEAM, GameBeloteTrickHypothesis.getPossibleOverTrump(t_,dom_,beat_,PossibleTrickWinner.TEAM,PossibleTrickWinner.FOE_TEAM));
    }
    @Test
    public void getPossibleOverTrump4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_9);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        addPossibleCard(t_,0,CardBelote.HEART_9);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        Ints beat_ = new Ints();
        beat_.add(0);
        Ints dom_ = new Ints();
        dom_.add(1);
        assertSame(PossibleTrickWinner.UNKNOWN, GameBeloteTrickHypothesis.getPossibleOverTrump(t_,dom_,beat_,PossibleTrickWinner.TEAM,PossibleTrickWinner.FOE_TEAM));
    }
    @Test
    public void beatFoeTrumpDemand1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_9);
        addPossibleCard(t_,0,CardBelote.HEART_9);
        Ints beat_ = new Ints();
        beat_.add(1);
        assertTrue(!beatFoeTrumpDemand(t_,beat_));
    }
    @Test
    public void beatFoeTrumpDemand2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_JACK);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_9);
        addPossibleCard(t_,0,CardBelote.HEART_9);
        Ints beat_ = new Ints();
        beat_.add(1);
        assertTrue(beatFoeTrumpDemand(t_,beat_));
    }
    @Test
    public void beatFoeTrumpDemand3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_10);
        Ints beat_ = new Ints();
        beat_.add(1);
        assertTrue(beatFoeTrumpDemand(t_,beat_));
    }
    @Test
    public void beatSureListTrumpDemand1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_KING);
        possibleSure(t_, 0, CardBelote.HEART_10);
        possibleSure(t_, 1, CardBelote.HEART_1);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(!beatSureListTrumpDemand(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpDemand2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_9);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_KING);
        possibleSure(t_, 0, CardBelote.HEART_1);
        possibleSure(t_, 1, CardBelote.HEART_10);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(!beatSureListTrumpDemand(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpDemand3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_KING);
        possibleSure(t_, 0, CardBelote.HEART_1);
        possibleSure(t_, 1, CardBelote.HEART_10);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(beatSureListTrumpDemand(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpDemand4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_KING);
        addPossibleCard(t_,0,CardBelote.HEART_10);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(!beatSureListTrumpDemand(t_,dom_,beat_));
    }
    @Test
    public void existeJouBatAdvDemat1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_KING);
        possibleSure(t_, 0, CardBelote.HEART_10);
        possibleSure(t_, 1, CardBelote.HEART_1);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(!existeJouBatAdvDemat(t_,dom_,beat_));
    }
    @Test
    public void existeJouBatAdvDemat2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_KING);
        possibleSure(t_, 0, CardBelote.HEART_1);
        possibleSure(t_, 1, CardBelote.HEART_10);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(existeJouBatAdvDemat(t_,dom_,beat_));
    }
    @Test
    public void existeJouBatAdvDemat3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_KING);
        addPossibleCard(t_,0,CardBelote.HEART_10);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(!existeJouBatAdvDemat(t_,dom_,beat_));
    }
    @Test
    public void existPlayerNoTrump1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,0,CardBelote.HEART_10);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(!existPlayerNoTrump(t_,dom_,beat_));
    }
    @Test
    public void existPlayerNoTrump2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_7);
        possibleSure(t_, 0, CardBelote.DIAMOND_KING);
        possibleSure(t_, 1, CardBelote.DIAMOND_1);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(!existPlayerNoTrump(t_,dom_,beat_));
    }
    @Test
    public void existPlayerNoTrump3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_7);
        possibleSure(t_, 0, CardBelote.DIAMOND_KING);
        possibleSure(t_, 1, CardBelote.DIAMOND_QUEEN);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(!existPlayerNoTrump(t_,dom_,beat_));
    }
    @Test
    public void existPlayerNoTrump4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_7);
        possibleSure(t_, 0, CardBelote.DIAMOND_1);
        possibleSure(t_, 1, CardBelote.DIAMOND_QUEEN);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertTrue(existPlayerNoTrump(t_,dom_,beat_));
    }
    @Test
    public void getPossibleTrickWinnerOtherTrump1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_7);
        possibleSure(t_, 0, CardBelote.HEART_1);
        possibleSure(t_, 1, CardBelote.HEART_QUEEN);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertSame(PossibleTrickWinner.FOE_TEAM, GameBeloteTrickHypothesis.getPossibleTrickWinnerOtherTrump(t_,PossibleTrickWinner.FOE_TEAM,dom_,PossibleTrickWinner.TEAM,beat_));
    }
    @Test
    public void getPossibleTrickWinnerOtherTrump2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_7);
        possibleSure(t_, 0, CardBelote.HEART_1);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertSame(PossibleTrickWinner.FOE_TEAM, GameBeloteTrickHypothesis.getPossibleTrickWinnerOtherTrump(t_,PossibleTrickWinner.FOE_TEAM,dom_,PossibleTrickWinner.TEAM,beat_));
    }
    @Test
    public void getPossibleTrickWinnerOtherTrump3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_7);
        possibleSure(t_, 0, CardBelote.HEART_QUEEN);
        possibleSure(t_, 1, CardBelote.HEART_1);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertSame(PossibleTrickWinner.TEAM, GameBeloteTrickHypothesis.getPossibleTrickWinnerOtherTrump(t_,PossibleTrickWinner.FOE_TEAM,dom_,PossibleTrickWinner.TEAM,beat_));
    }
    @Test
    public void getPossibleTrickWinnerOtherTrump4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,0,CardBelote.HEART_QUEEN);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_QUEEN);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertSame(PossibleTrickWinner.UNKNOWN, GameBeloteTrickHypothesis.getPossibleTrickWinnerOtherTrump(t_,PossibleTrickWinner.FOE_TEAM,dom_,PossibleTrickWinner.TEAM,beat_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,0,CardBelote.HEART_QUEEN);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_QUEEN);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        assertSame(PossibleTrickWinner.UNKNOWN,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,1,CardBelote.HEART_QUEEN);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        assertSame(PossibleTrickWinner.UNKNOWN,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_7);
        possibleSure(t_, 1, CardBelote.DIAMOND_1);
        assertSame(PossibleTrickWinner.TEAM,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_7);
        possibleSure(t_, 0, CardBelote.DIAMOND_1);
        assertSame(PossibleTrickWinner.FOE_TEAM,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump5Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,0,CardBelote.DIAMOND_1);
        possibleSure(t_, 1, CardBelote.DIAMOND_10);
        assertSame(PossibleTrickWinner.UNKNOWN,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump6Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_7);
        possibleSure(t_, 0, CardBelote.DIAMOND_1);
        possibleSure(t_, 1, CardBelote.DIAMOND_10);
        assertSame(PossibleTrickWinner.FOE_TEAM,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump7Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        current_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 0, CardBelote.DIAMOND_7);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        assertSame(PossibleTrickWinner.UNKNOWN,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump8Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        current_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 0, CardBelote.DIAMOND_7);
        possibleSure(t_, 1, CardBelote.DIAMOND_1);
        assertSame(PossibleTrickWinner.FOE_TEAM,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump9Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        current_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 0, CardBelote.DIAMOND_7);
        possibleSure(t_, 1, CardBelote.DIAMOND_1);
        assertSame(PossibleTrickWinner.FOE_TEAM,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump10Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        current_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 0, CardBelote.DIAMOND_7);
        addPossibleCard(t_,1,CardBelote.DIAMOND_1);
        assertSame(PossibleTrickWinner.UNKNOWN,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump11Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 0, CardBelote.DIAMOND_7);
        possibleSure(t_, 1, CardBelote.DIAMOND_KING);
        assertSame(PossibleTrickWinner.TEAM,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpDemand1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_KING);
        possibleSure(t_, 0, CardBelote.HEART_10);
        possibleSure(t_, 1, CardBelote.HEART_1);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertSame(PossibleTrickWinner.TEAM,GameBeloteTrickHypothesis.getPossibleTrickWinnerTrumpDemand(t_,PossibleTrickWinner.FOE_TEAM,dom_,PossibleTrickWinner.TEAM,beat_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpDemand2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_KING);
        possibleSure(t_, 0, CardBelote.HEART_1);
        possibleSure(t_, 1, CardBelote.HEART_10);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameBeloteTrickHypothesis.getPossibleTrickWinnerTrumpDemand(t_,PossibleTrickWinner.FOE_TEAM,dom_,PossibleTrickWinner.TEAM,beat_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpDemand3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_1);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_KING);
        possibleSure(t_, 0, CardBelote.HEART_QUEEN);
        possibleSure(t_, 1, CardBelote.HEART_10);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameBeloteTrickHypothesis.getPossibleTrickWinnerTrumpDemand(t_,PossibleTrickWinner.FOE_TEAM,dom_,PossibleTrickWinner.TEAM,beat_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpDemand4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_KING);
        addPossibleCard(t_,0,CardBelote.HEART_10);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        Ints beat_ = new Ints();
        beat_.add(1);
        Ints dom_ = new Ints();
        dom_.add(0);
        assertSame(PossibleTrickWinner.UNKNOWN,GameBeloteTrickHypothesis.getPossibleTrickWinnerTrumpDemand(t_,PossibleTrickWinner.FOE_TEAM,dom_,PossibleTrickWinner.TEAM,beat_));
    }
    @Test
    public void equipeQuiVaFairePliToutAtout1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_KING);
        addPossibleCard(t_,0,CardBelote.HEART_10);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        assertSame(PossibleTrickWinner.UNKNOWN,GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliToutAtout2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_JACK);
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure2(t_, 3, CardBelote.HEART_KING);
        possibleSure2(t_, 3, CardBelote.HEART_JACK);
        possibleSure2(t_, 0, CardBelote.HEART_10);
        possibleSure2(t_, 1, CardBelote.HEART_1);
        assertSame(PossibleTrickWinner.UNKNOWN,GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliToutAtout3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_KING);
        possibleSure(t_, 0, CardBelote.HEART_10);
        possibleSure(t_, 1, CardBelote.HEART_1);
        assertSame(PossibleTrickWinner.TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliToutAtout4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_KING);
        possibleSure(t_, 0, CardBelote.HEART_1);
        possibleSure(t_, 1, CardBelote.HEART_10);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliToutAtout5Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_QUEEN);
        possibleSure(t_, 0, CardBelote.HEART_1);
        possibleSure(t_, 1, CardBelote.HEART_10);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliToutAtout6Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_QUEEN);
        possibleSure(t_, 0, CardBelote.HEART_1);
        possibleSure(t_, 1, CardBelote.HEART_JACK);
        assertSame(PossibleTrickWinner.TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliToutAtout7Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_JACK);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_QUEEN);
        possibleSure(t_, 0, CardBelote.HEART_1);
        possibleSure(t_, 1, CardBelote.HEART_KING);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliToutAtout8Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_QUEEN);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addPossibleCard(t_,0,CardBelote.HEART_JACK);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_JACK);
        assertSame(PossibleTrickWinner.UNKNOWN,GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliToutAtout9Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_KING);
        current_.ajouter(CardBelote.HEART_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 0, CardBelote.HEART_QUEEN);
        possibleSure(t_, 1, CardBelote.HEART_10);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliToutAtout10Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_KING);
        current_.ajouter(CardBelote.HEART_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 0, CardBelote.HEART_QUEEN);
        possibleSure(t_, 1, CardBelote.HEART_7);
        assertSame(PossibleTrickWinner.TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliToutAtout11Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_JACK);
        current_.ajouter(CardBelote.HEART_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 0, CardBelote.HEART_QUEEN);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        assertSame(PossibleTrickWinner.TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliToutAtout12Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_KING);
        current_.ajouter(CardBelote.HEART_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 0, CardBelote.HEART_QUEEN);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        assertSame(PossibleTrickWinner.UNKNOWN,GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliToutAtout13Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        hand_.ajouter(CardBelote.HEART_JACK);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        playable_.ajouter(CardBelote.HEART_JACK);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_KING);
        current_.ajouter(CardBelote.HEART_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 0, CardBelote.HEART_JACK);
        possibleSure(t_, 0, CardBelote.HEART_10);
        possibleSure(t_, 1, CardBelote.HEART_1);
        assertSame(PossibleTrickWinner.UNKNOWN,GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliToutAtout14Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        HandBelote playable_ = new HandBelote();
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_KING);
        current_.ajouter(CardBelote.HEART_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        assertSame(PossibleTrickWinner.UNKNOWN,GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliSansAtout1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_KING);
        addPossibleCard(t_,0,CardBelote.HEART_10);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        assertSame(PossibleTrickWinner.UNKNOWN,GameBeloteTrickHypothesis.equipeQuiVaFairePliSansAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliSansAtout2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_8);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_8);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_8);
        possibleSure(t_, 0, CardBelote.HEART_10);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliSansAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliSansAtout3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_8);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_8);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_8);
        possibleSure(t_, 1, CardBelote.HEART_10);
        assertSame(PossibleTrickWinner.TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliSansAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliSansAtout4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        HandBelote playable_ = new HandBelote();
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 0, CardBelote.HEART_10);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliSansAtout(t_));
    }
    @Test
    public void equipeQuiVaFairePliSansAtout5Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        HandBelote playable_ = new HandBelote();
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_10);
        assertSame(PossibleTrickWinner.TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliSansAtout(t_));
    }
    @Test
    public void getPossibleTrickWinnerOverTrump1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.DIAMOND_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.DIAMOND_JACK);
        playable_.ajouter(CardBelote.DIAMOND_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_JACK);
        possibleSure(t_, 3, CardBelote.DIAMOND_10);
        possibleSure(t_, 0, CardBelote.DIAMOND_9);
        possibleSure(t_, 1, CardBelote.DIAMOND_1);
        assertSame(PossibleTrickWinner.UNKNOWN,GameBeloteTrickHypothesis.getPossibleTrickWinnerOverTrump(t_, GameBeloteCommon.hand(t_.getCartesCertaines(), t_.getCouleurAtout(), t_.getNextPlayer()).premiereCarte().strength(t_.getProgressingTrick().couleurDemandee(), t_.getContrat())));
    }
    @Test
    public void getPossibleTrickWinnerOverTrump2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.DIAMOND_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_10);
        possibleSure(t_, 0, CardBelote.DIAMOND_9);
        possibleSure(t_, 1, CardBelote.DIAMOND_1);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameBeloteTrickHypothesis.getPossibleTrickWinnerOverTrump(t_, GameBeloteCommon.hand(t_.getCartesCertaines(), t_.getCouleurAtout(), t_.getNextPlayer()).premiereCarte().strength(t_.getProgressingTrick().couleurDemandee(), t_.getContrat())));
    }
    @Test
    public void getPossibleTrickWinnerOverTrump3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.DIAMOND_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_10);
        possibleSure(t_, 0, CardBelote.DIAMOND_1);
        possibleSure(t_, 1, CardBelote.DIAMOND_9);
        assertSame(PossibleTrickWinner.TEAM,GameBeloteTrickHypothesis.getPossibleTrickWinnerOverTrump(t_, GameBeloteCommon.hand(t_.getCartesCertaines(), t_.getCouleurAtout(), t_.getNextPlayer()).premiereCarte().strength(t_.getProgressingTrick().couleurDemandee(), t_.getContrat())));
    }
    @Test
    public void getPossibleTrickWinnerOverTrump4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.DIAMOND_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_10);
        addPossibleCard(t_,0,CardBelote.DIAMOND_1);
        addPossibleCard(t_,0,CardBelote.DIAMOND_9);
        addPossibleCard(t_,1,CardBelote.DIAMOND_1);
        addPossibleCard(t_,1,CardBelote.DIAMOND_9);
        assertSame(PossibleTrickWinner.UNKNOWN,GameBeloteTrickHypothesis.getPossibleTrickWinnerOverTrump(t_, GameBeloteCommon.hand(t_.getCartesCertaines(), t_.getCouleurAtout(), t_.getNextPlayer()).premiereCarte().strength(t_.getProgressingTrick().couleurDemandee(), t_.getContrat())));
    }
    @Test
    public void equipeQuiVaFairePliCouleurDominante1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.DIAMOND_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        current_.ajouter(CardBelote.HEART_8);
        current_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.DIAMOND_10);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(t_));
    }
    @Test
    public void equipeQuiVaFairePliCouleurDominante2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.DIAMOND_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        current_.ajouter(CardBelote.DIAMOND_JACK);
        current_.ajouter(CardBelote.HEART_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.DIAMOND_10);
        assertSame(PossibleTrickWinner.TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(t_));
    }
    @Test
    public void equipeQuiVaFairePliCouleurDominante3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        current_.ajouter(CardBelote.HEART_8);
        current_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_10);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(t_));
    }
    @Test
    public void equipeQuiVaFairePliCouleurDominante4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        current_.ajouter(CardBelote.DIAMOND_JACK);
        current_.ajouter(CardBelote.HEART_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.HEART_10);
        assertSame(PossibleTrickWinner.TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(t_));
    }
    @Test
    public void equipeQuiVaFairePliCouleurDominante5Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        HandBelote playable_ = new HandBelote();
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        current_.ajouter(CardBelote.HEART_8);
        current_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(t_));
    }
    @Test
    public void equipeQuiVaFairePliCouleurDominante6Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        HandBelote playable_ = new HandBelote();
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        current_.ajouter(CardBelote.DIAMOND_JACK);
        current_.ajouter(CardBelote.HEART_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        assertSame(PossibleTrickWinner.TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(t_));
    }
    @Test
    public void equipeQuiVaFairePliCouleurDominante7Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.DIAMOND_9);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.DIAMOND_JACK);
        playable_.ajouter(CardBelote.DIAMOND_9);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        current_.ajouter(CardBelote.HEART_8);
        current_.ajouter(CardBelote.DIAMOND_10);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 1, CardBelote.DIAMOND_JACK);
        possibleSure(t_, 1, CardBelote.DIAMOND_9);
        assertSame(PossibleTrickWinner.UNKNOWN,GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(t_));
    }
    @Test
    public void equipeQuiVaFairePliCouleurDominante8Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.DIAMOND_JACK);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_JACK);
        possibleSure(t_, 0, CardBelote.DIAMOND_1);
        possibleSure(t_, 1, CardBelote.DIAMOND_10);
        assertSame(PossibleTrickWinner.UNKNOWN,GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(t_));
    }
    @Test
    public void equipeQuiVaFairePliCouleurDominante9Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_JACK);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_JACK);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_JACK);
        possibleSure(t_, 0, CardBelote.DIAMOND_1);
        possibleSure(t_, 1, CardBelote.DIAMOND_10);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(t_));
    }
    @Test
    public void equipeQuiVaFairePliCouleurDominante10Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_JACK);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_JACK);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_JACK);
        possibleSure(t_, 0, CardBelote.DIAMOND_10);
        possibleSure(t_, 1, CardBelote.DIAMOND_1);
        assertSame(PossibleTrickWinner.TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(t_));
    }
    @Test
    public void equipeQuiVaFairePliCouleurDominante11Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_JACK);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_JACK);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_JACK);
        possibleSure(t_, 0, CardBelote.HEART_1);
        possibleSure(t_, 1, CardBelote.HEART_10);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(t_));
    }
    @Test
    public void equipeQuiVaFairePliCouleurDominante12Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_JACK);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_JACK);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_JACK);
        possibleSure(t_, 0, CardBelote.HEART_10);
        possibleSure(t_, 1, CardBelote.HEART_1);
        assertSame(PossibleTrickWinner.TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(t_));
    }
    @Test
    public void equipeQuiVaFairePliCouleurDominante13Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_JACK);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_JACK);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_JACK);
        possibleSure(t_, 0, CardBelote.DIAMOND_10);
        possibleSure(t_, 1, CardBelote.HEART_KING);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(t_));
    }
    @Test
    public void equipeQuiVaFairePliCouleurDominante14Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.HEART_1);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        playable_.ajouter(CardBelote.HEART_1);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.HEART_KING);
        possibleSure(t_, 3, CardBelote.HEART_1);
        addPossibleCard(t_,0,CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        assertSame(PossibleTrickWinner.UNKNOWN,GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(t_));
    }
    @Test
    public void equipeQuiVaFairePliCouleurDominante15Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.DIAMOND_JACK);
        TrickBelote current_ = new TrickBelote(2);
        current_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        possibleSure(t_, 3, CardBelote.DIAMOND_JACK);
        addPossibleCard(t_,0,CardBelote.DIAMOND_10);
        addPossibleCard(t_,1,CardBelote.DIAMOND_10);
        assertSame(PossibleTrickWinner.UNKNOWN,GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(t_));
    }
    static PossibleTrickWinner getPossibleTrickWinnerNoCurrentTrump(BeloteInfoPliEnCours _t) {
        TrickBelote cur_ = _t.getProgressingTrick();
        int nbPlayers_ = _t.getNbPlayers();
        BidBeloteSuit bid_ = _t.getContrat();
        CardBelote card_ = cur_.carteDuJoueur(cur_.getRamasseurPliEnCours(nbPlayers_, bid_), nbPlayers_);
        return GameBeloteTrickHypothesis.getPossibleTrickWinnerNoCurrentTrump(_t,card_);
    }
    static boolean existPlayerNoTrump(BeloteInfoPliEnCours _t,Ints _dom,Ints _beat) {
        IdMap<Suit, CustList<HandBelote>> sure_ = _t.getCartesCertaines();
        TrickBelote cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        int nbPlayers_ = _t.getNbPlayers();
        BidBeloteSuit bid_ = _t.getContrat();
        CardBelote card_ = cur_.carteDuJoueur(cur_.getRamasseurPliEnCours(nbPlayers_, bid_), nbPlayers_);
        return GameBeloteTrickHypothesis.existPlayerNoTrump(_t,_beat,_dom,suit_,card_,sure_);
    }
    static boolean existeJouBatAdvDemat(BeloteInfoPliEnCours _t,Ints _dom,Ints _beat) {
        TrickBelote cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        return GameBeloteTrickHypothesis.existeJouBatAdvDemat(_t,_beat,_dom,suit_);
    }
    static boolean beatSureListTrumpDemand(BeloteInfoPliEnCours _t,Ints _dom,Ints _beat) {
        IdMap<Suit, CustList<HandBelote>> poss_ = _t.getCartesPossibles();
        IdMap<Suit, CustList<HandBelote>> sure_ = _t.getCartesCertaines();
        TrickBelote cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        int nbPlayers_ = _t.getNbPlayers();
        BidBeloteSuit bid_ = _t.getContrat();
        int strength_ = cur_.carteDuJoueur(cur_.getRamasseurPliEnCours(nbPlayers_,bid_),nbPlayers_).strength(suit_,bid_);
        return GameBeloteTrickHypothesis.beatSureListTrumpDemand(_beat,_dom,suit_,bid_,poss_,sure_,strength_);
    }
    static boolean beatFoeTrumpDemand(BeloteInfoPliEnCours _t, Ints _beat) {
        TrickBelote cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        int nbPlayers_ = _t.getNbPlayers();
        BidBeloteSuit bid_ = _t.getContrat();
        int strength_ = cur_.carteDuJoueur(cur_.getRamasseurPliEnCours(nbPlayers_,bid_),nbPlayers_).strength(suit_,bid_);
        return beatFoeTrumpDemand(_t, _beat, strength_);
    }
    static boolean beatFoeTrumpDemand(BeloteInfoPliEnCours _t, Ints _beat, int _str) {
        IdMap<Suit, CustList<HandBelote>> poss_ = _t.getCartesPossibles();
        TrickBelote cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        BidBeloteSuit bid_ = _t.getContrat();
        return GameBeloteTrickHypothesis.beatFoeTrumpDemand(_beat,suit_,bid_,poss_, _str);
    }
    static boolean existeJoueurNonJoueBattantAdv(BeloteInfoPliEnCours _t,Ints _dom,Ints _beat) {
        TrickBelote cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        return GameBeloteTrickHypothesis.existeJoueurNonJoueBattantAdv(_t,_beat,_dom,suit_);
    }
    
    static boolean ramasseurBatSsCprAdv(BeloteInfoPliEnCours _t,Ints _beat) {
        TrickBelote cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        BidBeloteSuit bid_ = _t.getContrat();
        int nbPlayers_ = _t.getNbPlayers();
        int strength_ = cur_.carteDuJoueur(cur_.getRamasseurPliEnCours(nbPlayers_,bid_),nbPlayers_).strength(suit_,bid_);
        return GameBeloteTrickHypothesis.ramasseurBatSsCprAdv(_t,_beat,suit_,strength_);
    }
    static boolean beatSureListTrumpNormalSuit(BeloteInfoPliEnCours _t, Ints _dom,Ints _beat) {
        TrickBelote cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        BidBeloteSuit bid_ = _t.getContrat();
        int nbPlayers_ = _t.getNbPlayers();
        int strength_ = cur_.carteDuJoueur(cur_.getRamasseurPliEnCours(nbPlayers_,bid_),nbPlayers_).strength(suit_,bid_);
        return beatSureListTrumpNormalSuit(_t,_dom,_beat, strength_);
    }
    static boolean beatSureListTrumpNormalSuit(BeloteInfoPliEnCours _t, Ints _dom,Ints _beat,int _str) {
        IdMap<Suit, CustList<HandBelote>> poss_ = _t.getCartesPossibles();
        IdMap<Suit, CustList<HandBelote>> sure_ = _t.getCartesCertaines();
        Suit suit_ = _t.getProgressingTrick().couleurDemandee();
        BidBeloteSuit bid_ = _t.getContrat();
        return GameBeloteTrickHypothesis.beatSureListTrumpNormalSuit(_beat,_dom,suit_,bid_,poss_,sure_, _str);
    }
    static boolean beatByTrumpNormalSuitStrength(BeloteInfoPliEnCours _t, Ints _beat) {
        int nbPlayers_ = _t.getNbPlayers();
        TrickBelote cur_ = _t.getProgressingTrick();
        BidBeloteSuit bid_ = _t.getContrat();
        int strength_ = cur_.carteDuJoueur(cur_.getRamasseurPliEnCours(nbPlayers_,bid_),nbPlayers_).strength(cur_.couleurDemandee(),bid_);
        return beatByTrumpNormalSuitStrength(_t, _beat, strength_);
    }
    static boolean beatByTrumpNormalSuitStrength(BeloteInfoPliEnCours _t, Ints _beat,int _str) {
        IdMap<Suit, CustList<HandBelote>> poss_ = _t.getCartesPossibles();
        IdMap<Suit, CustList<HandBelote>> sure_ = _t.getCartesCertaines();
        Suit suit_ = _t.getProgressingTrick().couleurDemandee();
        BidBeloteSuit bid_ = _t.getContrat();
        return GameBeloteTrickHypothesis.beatByTrumpNormalSuitStrength(_beat,suit_,bid_,poss_,sure_, _str);
    }
    BeloteInfoPliEnCours initInformations(
            HandBelote _cartes,
            HandBelote _cartesJouables,
            TrickBelote _current,
            int _taker,
            HandBelote _playedCard,
            BidBeloteSuit _bid,
            int _nbPlayers,
            RulesBelote _rules) {
        Ints played_ = _current.playersHavingPlayed(_nbPlayers);
        int nextPlayer_ = _current.getNextPlayer(_nbPlayers);
        IdMap<Suit,HandBelote> repartition_ = _cartes.couleurs(_bid);
        IdMap<Suit,HandBelote> repartitionCartesJouees_ = _playedCard.couleurs(_bid);
        Ints joueursNonJoue_ = GameBeloteTeamsRelation.autresJoueurs(played_, _nbPlayers);

        int ramasseurVirtuel_ = _current.getRamasseurPliEnCours(_nbPlayers, _bid);
        IdMap<Suit,CustList<HandBelote>> suitesTouteCouleur_ = _cartes.eclaterTout(repartitionCartesJouees_, _bid);

        IdMap<Suit,HandBelote> cartesMaitresses_ = GameBeloteCommon.cartesMaitresses(
                repartition_, repartitionCartesJouees_, _bid);

        GameBeloteTeamsRelation teamsRelation_ = new GameBeloteTeamsRelation(_taker,_rules);
        BeloteInfoPliEnCours info_ = new BeloteInfoPliEnCours();
        info_.setContrat(_bid);
        info_.setProgressingTrick(_current);
        info_.setNbPlayers(_nbPlayers);
        info_.setNextPlayer(nextPlayer_);
        info_.setJoueursConfiance(teamsRelation_.partenaires(nextPlayer_));
        info_.setJoueursNonConfiance(teamsRelation_.adversaires(nextPlayer_));
        info_.setJoueursNonJoue(joueursNonJoue_);
        info_.setPlisFaits(new CustList<TrickBelote>());
        info_.setCartesJouees(_playedCard);
        info_.setRepartitionCartesJouees(repartitionCartesJouees_);
        info_.setCartesPossibles(generate(_nbPlayers));
        info_.setCartesCertaines(generate(_nbPlayers));
        info_.setRamasseurVirtuel(ramasseurVirtuel_);
        info_.setSuitesTouteCouleur(suitesTouteCouleur_);
        info_.setCouleursMaitresses(new IdList<Suit>());
        info_.setStrictCouleursMaitresses(new IdList<Suit>());
        info_.setCartesMaitresses(cartesMaitresses_);
        return info_;
    }

    private void possibleSure(BeloteInfoPliEnCours _b, int _p, CardBelote _card) {
        _b.getCartesPossibles().getVal(_card.getId().getCouleur()).get(_p).ajouter(_card);
        _b.getCartesCertaines().getVal(_card.getId().getCouleur()).get(_p).ajouter(_card);
    }

    private void possibleSure2(BeloteInfoPliEnCours _b, int _p, CardBelote _card) {
        HandBelote poss_ = _b.getCartesPossibles().getVal(_card.getId().getCouleur()).get(_p);
        poss_.ajouter(_card);
        Order order_ = HandBelote.order(_b.getContrat(), _card.getId().getCouleur());
        HandBelote.sortList(true,poss_.premiereCarte().getId().getCouleur(),poss_.getCards(),order_);
        Suit s_ = _card.getId().getCouleur();
        HandBelote sure_ = _b.getCartesCertaines().getVal(s_).get(_p);
        sure_.ajouter(_card);
        Order order2_ = HandBelote.order(_b.getContrat(), s_);
        HandBelote.sortList(true, sure_.premiereCarte().getId().getCouleur(), sure_.getCards(), order2_);
    }

    private static IdMap<Suit,CustList<HandBelote>> generate(int _nbPlayer) {
        return generate();
//        IdMap<Suit,CustList<HandBelote>> e_ = new IdMap<Suit,CustList<HandBelote>>();
//        EnumList<Suit> s_ = new EnumList<Suit>();
//        s_.addAllElts(Suit.couleursOrdinaires());
//        for (Suit s: s_) {
//            CustList<HandBelote> l_ = new CustList<HandBelote>();
//            for (int i = 0; i < _nbPlayer; i++) {
//                l_.add(new HandBelote());
//            }
//            e_.addEntry(s,l_);
//        }
//        return e_;
    }
}
