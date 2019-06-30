package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.PossibleTrickWinner;
import cards.consts.Suit;
import code.util.*;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public final class GameBeloteTrickHypothesisTest extends CommonGameBelote {
    @Test
    public void beatByTrumpNormalSuitStrength1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.DIAMOND_7);
        addSureCard(t_,1,CardBelote.DIAMOND_7);
        addPossibleCard(t_,1,CardBelote.HEART_QUEEN);
        addSureCard(t_,1, CardBelote.HEART_QUEEN);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(beatByTrumpNormalSuitStrength(t_,beat_));
    }
    @Test
    public void beatByTrumpNormalSuitStrength2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(beatByTrumpNormalSuitStrength(t_,beat_));
    }
    @Test
    public void beatByTrumpNormalSuitStrength3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_7);
        addSureCard(t_,1, CardBelote.HEART_7);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(beatByTrumpNormalSuitStrength(t_,beat_));
    }
    @Test
    public void beatByTrumpNormalSuitStrength4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_KING);
        addSureCard(t_,1, CardBelote.HEART_KING);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(!beatByTrumpNormalSuitStrength(t_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        addSureCard(t_,1, CardBelote.HEART_1);
        addPossibleCard(t_,0,CardBelote.HEART_10);
        addSureCard(t_,0, CardBelote.HEART_10);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_7);
        addSureCard(t_,1, CardBelote.HEART_7);
        addPossibleCard(t_,0,CardBelote.HEART_10);
        addSureCard(t_,0, CardBelote.HEART_10);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_7);
        addSureCard(t_,1, CardBelote.HEART_7);
        addPossibleCard(t_,0,CardBelote.HEART_QUEEN);
        addSureCard(t_,0, CardBelote.HEART_QUEEN);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_8);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_JACK);
        playable_.ajouter(CardBelote.DIAMOND_8);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_7);
        addSureCard(t_,1, CardBelote.HEART_7);
        addPossibleCard(t_,0,CardBelote.HEART_JACK);
        addSureCard(t_,0, CardBelote.HEART_JACK);
        addPossibleCard(t_,0,CardBelote.DIAMOND_8);
        addSureCard(t_,0, CardBelote.DIAMOND_8);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit5Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_8);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.DIAMOND_8);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_7);
        addSureCard(t_,1, CardBelote.HEART_7);
        addPossibleCard(t_,0,CardBelote.DIAMOND_8);
        addSureCard(t_,0, CardBelote.DIAMOND_8);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit6Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        HandBelote playable_ = new HandBelote();
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_7);
        addSureCard(t_,1, CardBelote.HEART_7);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_QUEEN);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addSureCard(t_,1, CardBelote.HEART_10);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(!ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_QUEEN);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addSureCard(t_,1, CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.DIAMOND_8);
        addSureCard(t_,1, CardBelote.DIAMOND_8);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_QUEEN);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_QUEEN);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addSureCard(t_,1, CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.DIAMOND_1);
        addSureCard(t_,1, CardBelote.DIAMOND_1);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(!ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv5Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_QUEEN);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addSureCard(t_,1, CardBelote.HEART_10);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(!ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv6Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_QUEEN);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.DIAMOND_1);
        addSureCard(t_,1, CardBelote.DIAMOND_1);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(!ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv7Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,true);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setEnchere(BidBelote.NO_TRUMP);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_QUEEN);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addSureCard(t_,1, CardBelote.HEART_10);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv8Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,true);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setEnchere(BidBelote.NO_TRUMP);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_QUEEN);
        playable_.ajouter(CardBelote.DIAMOND_QUEEN);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_8);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.DIAMOND_10);
        addSureCard(t_,1, CardBelote.DIAMOND_10);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(!ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void existeJoueurNonJoueBattantAdv1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        addSureCard(t_,1, CardBelote.HEART_1);
        addPossibleCard(t_,0,CardBelote.HEART_10);
        addSureCard(t_,0, CardBelote.HEART_10);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!existeJoueurNonJoueBattantAdv(t_,dom_,beat_));
    }
    @Test
    public void existeJoueurNonJoueBattantAdv2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        addSureCard(t_,1, CardBelote.HEART_1);
        addPossibleCard(t_,0,CardBelote.HEART_10);
        addSureCard(t_,0, CardBelote.HEART_10);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!existeJoueurNonJoueBattantAdv(t_,dom_,beat_));
    }
    @Test
    public void existeJoueurNonJoueBattantAdv3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.DIAMOND_8);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_1);
        playable_.ajouter(CardBelote.DIAMOND_8);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addSureCard(t_,1, CardBelote.HEART_10);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addSureCard(t_,0, CardBelote.HEART_1);
        addPossibleCard(t_,0,CardBelote.DIAMOND_8);
        addSureCard(t_,0, CardBelote.DIAMOND_8);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!existeJoueurNonJoueBattantAdv(t_,dom_,beat_));
    }
    @Test
    public void existeJoueurNonJoueBattantAdv4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_1);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_1);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addSureCard(t_,1, CardBelote.HEART_10);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addSureCard(t_,0, CardBelote.HEART_1);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(existeJoueurNonJoueBattantAdv(t_,dom_,beat_));
    }
    @Test
    public void getPossibleOverTrump1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_1);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_1);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addSureCard(t_,1, CardBelote.HEART_10);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addSureCard(t_,0, CardBelote.HEART_1);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertSame(PossibleTrickWinner.TEAM, GameBeloteTrickHypothesis.getPossibleOverTrump(t_,dom_,beat_,PossibleTrickWinner.TEAM,PossibleTrickWinner.FOE_TEAM));
    }
    @Test
    public void getPossibleOverTrump2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.HEART_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        addSureCard(t_,1, CardBelote.HEART_1);
        addPossibleCard(t_,0,CardBelote.HEART_10);
        addSureCard(t_,0, CardBelote.HEART_10);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertSame(PossibleTrickWinner.FOE_TEAM, GameBeloteTrickHypothesis.getPossibleOverTrump(t_,dom_,beat_,PossibleTrickWinner.TEAM,PossibleTrickWinner.FOE_TEAM));
    }
    @Test
    public void getPossibleOverTrump3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.HEART_10);
        addSureCard(t_,3, CardBelote.HEART_10);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 0);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 3);
        assertSame(PossibleTrickWinner.TEAM, GameBeloteTrickHypothesis.getPossibleOverTrump(t_,dom_,beat_,PossibleTrickWinner.TEAM,PossibleTrickWinner.FOE_TEAM));
    }
    @Test
    public void getPossibleOverTrump4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.HEART_10);
        addSureCard(t_,3, CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_9);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        addPossibleCard(t_,0,CardBelote.HEART_9);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 0);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 1);
        assertSame(PossibleTrickWinner.UNKNOWN, GameBeloteTrickHypothesis.getPossibleOverTrump(t_,dom_,beat_,PossibleTrickWinner.TEAM,PossibleTrickWinner.FOE_TEAM));
    }
    @Test
    public void beatFoeTrumpDemand1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.HEART_10);
        addSureCard(t_,3, CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_9);
        addPossibleCard(t_,0,CardBelote.HEART_9);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(!beatFoeTrumpDemand(t_,beat_));
    }
    @Test
    public void beatFoeTrumpDemand2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.HEART_JACK);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.HEART_10);
        addSureCard(t_,3, CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_9);
        addPossibleCard(t_,0,CardBelote.HEART_9);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(beatFoeTrumpDemand(t_,beat_));
    }
    @Test
    public void beatFoeTrumpDemand3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_10);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.HEART_10);
        addSureCard(t_,3, CardBelote.HEART_10);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(beatFoeTrumpDemand(t_,beat_));
    }
    @Test
    public void beatSureListTrumpDemand1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.HEART_KING);
        addSureCard(t_,3, CardBelote.HEART_KING);
        addPossibleCard(t_,0,CardBelote.HEART_10);
        addSureCard(t_,0, CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        addSureCard(t_,1, CardBelote.HEART_1);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!beatSureListTrumpDemand(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpDemand2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.HEART_9);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.HEART_KING);
        addSureCard(t_,3, CardBelote.HEART_KING);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addSureCard(t_,0, CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addSureCard(t_,1, CardBelote.HEART_10);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!beatSureListTrumpDemand(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpDemand3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.HEART_KING);
        addSureCard(t_,3, CardBelote.HEART_KING);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addSureCard(t_,0, CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addSureCard(t_,1, CardBelote.HEART_10);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(beatSureListTrumpDemand(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpDemand4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.HEART_KING);
        addSureCard(t_,3, CardBelote.HEART_KING);
        addPossibleCard(t_,0,CardBelote.HEART_10);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!beatSureListTrumpDemand(t_,dom_,beat_));
    }
    @Test
    public void existeJouBatAdvDemat1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.HEART_KING);
        addSureCard(t_,3, CardBelote.HEART_KING);
        addPossibleCard(t_,0,CardBelote.HEART_10);
        addSureCard(t_,0, CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        addSureCard(t_,1, CardBelote.HEART_1);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!existeJouBatAdvDemat(t_,dom_,beat_));
    }
    @Test
    public void existeJouBatAdvDemat2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.HEART_KING);
        addSureCard(t_,3, CardBelote.HEART_KING);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addSureCard(t_,0, CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addSureCard(t_,1, CardBelote.HEART_10);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(existeJouBatAdvDemat(t_,dom_,beat_));
    }
    @Test
    public void existeJouBatAdvDemat3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.HEART_KING);
        addSureCard(t_,3, CardBelote.HEART_KING);
        addPossibleCard(t_,0,CardBelote.HEART_10);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!existeJouBatAdvDemat(t_,dom_,beat_));
    }
    @Test
    public void existPlayerNoTrump1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.DIAMOND_7);
        addSureCard(t_,3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,0,CardBelote.HEART_10);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_10);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!existPlayerNoTrump(t_,dom_,beat_));
    }
    @Test
    public void existPlayerNoTrump2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.DIAMOND_7);
        addSureCard(t_,3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,0,CardBelote.DIAMOND_KING);
        addSureCard(t_,0, CardBelote.DIAMOND_KING);
        addPossibleCard(t_,1,CardBelote.DIAMOND_1);
        addSureCard(t_,1, CardBelote.DIAMOND_1);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!existPlayerNoTrump(t_,dom_,beat_));
    }
    @Test
    public void existPlayerNoTrump3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.DIAMOND_7);
        addSureCard(t_,3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,0,CardBelote.DIAMOND_KING);
        addSureCard(t_,0, CardBelote.DIAMOND_KING);
        addPossibleCard(t_,1,CardBelote.DIAMOND_QUEEN);
        addSureCard(t_,1, CardBelote.DIAMOND_QUEEN);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!existPlayerNoTrump(t_,dom_,beat_));
    }
    @Test
    public void existPlayerNoTrump4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.DIAMOND_7);
        addSureCard(t_,3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,0,CardBelote.DIAMOND_1);
        addSureCard(t_,0, CardBelote.DIAMOND_1);
        addPossibleCard(t_,1,CardBelote.DIAMOND_QUEEN);
        addSureCard(t_,1, CardBelote.DIAMOND_QUEEN);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(existPlayerNoTrump(t_,dom_,beat_));
    }
    @Test
    public void getPossibleTrickWinnerOtherTrump1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.DIAMOND_7);
        addSureCard(t_,3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addSureCard(t_,0, CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_QUEEN);
        addSureCard(t_,1, CardBelote.HEART_QUEEN);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertSame(PossibleTrickWinner.FOE_TEAM, GameBeloteTrickHypothesis.getPossibleTrickWinnerOtherTrump(t_,PossibleTrickWinner.FOE_TEAM,dom_,PossibleTrickWinner.TEAM,beat_));
    }
    @Test
    public void getPossibleTrickWinnerOtherTrump2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.DIAMOND_7);
        addSureCard(t_,3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addSureCard(t_,0, CardBelote.HEART_1);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertSame(PossibleTrickWinner.FOE_TEAM, GameBeloteTrickHypothesis.getPossibleTrickWinnerOtherTrump(t_,PossibleTrickWinner.FOE_TEAM,dom_,PossibleTrickWinner.TEAM,beat_));
    }
    @Test
    public void getPossibleTrickWinnerOtherTrump3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.DIAMOND_7);
        addSureCard(t_,3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,0,CardBelote.HEART_QUEEN);
        addSureCard(t_,0, CardBelote.HEART_QUEEN);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        addSureCard(t_,1, CardBelote.HEART_1);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertSame(PossibleTrickWinner.TEAM, GameBeloteTrickHypothesis.getPossibleTrickWinnerOtherTrump(t_,PossibleTrickWinner.FOE_TEAM,dom_,PossibleTrickWinner.TEAM,beat_));
    }
    @Test
    public void getPossibleTrickWinnerOtherTrump4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.DIAMOND_7);
        addSureCard(t_,3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,0,CardBelote.HEART_QUEEN);
        addPossibleCard(t_,0,CardBelote.HEART_1);
        addPossibleCard(t_,1,CardBelote.HEART_QUEEN);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertSame(PossibleTrickWinner.UNKNOWN, GameBeloteTrickHypothesis.getPossibleTrickWinnerOtherTrump(t_,PossibleTrickWinner.FOE_TEAM,dom_,PossibleTrickWinner.TEAM,beat_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.DIAMOND_7);
        addSureCard(t_,3, CardBelote.DIAMOND_7);
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
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.DIAMOND_7);
        addSureCard(t_,3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,1,CardBelote.HEART_QUEEN);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        assertSame(PossibleTrickWinner.UNKNOWN,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.DIAMOND_7);
        addSureCard(t_,3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,1,CardBelote.DIAMOND_1);
        addSureCard(t_,1, CardBelote.DIAMOND_1);
        assertSame(PossibleTrickWinner.TEAM,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump4Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.DIAMOND_7);
        addSureCard(t_,3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,0,CardBelote.DIAMOND_1);
        addSureCard(t_,0, CardBelote.DIAMOND_1);
        assertSame(PossibleTrickWinner.FOE_TEAM,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump5Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.DIAMOND_7);
        addSureCard(t_,3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,0,CardBelote.DIAMOND_1);
        addPossibleCard(t_,1,CardBelote.DIAMOND_10);
        addSureCard(t_,1,CardBelote.DIAMOND_10);
        assertSame(PossibleTrickWinner.UNKNOWN,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump6Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,3,CardBelote.DIAMOND_7);
        addSureCard(t_,3, CardBelote.DIAMOND_7);
        addPossibleCard(t_,0,CardBelote.DIAMOND_1);
        addSureCard(t_,0,CardBelote.DIAMOND_1);
        addPossibleCard(t_,1,CardBelote.DIAMOND_10);
        addSureCard(t_,1,CardBelote.DIAMOND_10);
        assertSame(PossibleTrickWinner.FOE_TEAM,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump7Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        current_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,0,CardBelote.DIAMOND_7);
        addSureCard(t_,0, CardBelote.DIAMOND_7);
        addPossibleCard(t_,1,CardBelote.HEART_1);
        assertSame(PossibleTrickWinner.UNKNOWN,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump8Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        current_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,0,CardBelote.DIAMOND_7);
        addSureCard(t_,0, CardBelote.DIAMOND_7);
        addPossibleCard(t_,1,CardBelote.DIAMOND_1);
        addSureCard(t_,1,CardBelote.DIAMOND_1);
        assertSame(PossibleTrickWinner.FOE_TEAM,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump9Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        current_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,0,CardBelote.DIAMOND_7);
        addSureCard(t_,0, CardBelote.DIAMOND_7);
        addPossibleCard(t_,1,CardBelote.DIAMOND_1);
        addSureCard(t_,1,CardBelote.DIAMOND_1);
        assertSame(PossibleTrickWinner.FOE_TEAM,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump10Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_KING);
        current_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,0,CardBelote.DIAMOND_7);
        addSureCard(t_,0, CardBelote.DIAMOND_7);
        addPossibleCard(t_,1,CardBelote.DIAMOND_1);
        assertSame(PossibleTrickWinner.UNKNOWN,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoCurrentTrump11Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setEnchere(BidBelote.SUIT);
        HandBelote hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        HandBelote playable_ = new HandBelote();
        playable_.ajouter(CardBelote.HEART_KING);
        TrickBelote current_ = new TrickBelote((byte)2);
        current_.ajouter(CardBelote.DIAMOND_1);
        current_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote played_ = new HandBelote();
        BeloteInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,bid_,4,r_);
        addPossibleCard(t_,0,CardBelote.DIAMOND_7);
        addSureCard(t_,0, CardBelote.DIAMOND_7);
        addPossibleCard(t_,1,CardBelote.DIAMOND_KING);
        addSureCard(t_,1,CardBelote.DIAMOND_KING);
        assertSame(PossibleTrickWinner.TEAM,getPossibleTrickWinnerNoCurrentTrump(t_));
    }
    static PossibleTrickWinner getPossibleTrickWinnerNoCurrentTrump(BeloteInfoPliEnCours _t) {
        TrickBelote cur_ = _t.getProgressingTrick();
        byte nbPlayers_ = _t.getNbPlayers();
        BidBeloteSuit bid_ = _t.getContrat();
        CardBelote card_ = cur_.carteDuJoueur(cur_.getRamasseurPliEnCours(nbPlayers_, bid_), nbPlayers_);
        return GameBeloteTrickHypothesis.getPossibleTrickWinnerNoCurrentTrump(_t,card_);
    }
    static boolean existPlayerNoTrump(BeloteInfoPliEnCours _t,Bytes _dom,Bytes _beat) {
        EnumMap<Suit, EqList<HandBelote>> sure_ = _t.getCartesCertaines();
        TrickBelote cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        byte nbPlayers_ = _t.getNbPlayers();
        BidBeloteSuit bid_ = _t.getContrat();
        CardBelote card_ = cur_.carteDuJoueur(cur_.getRamasseurPliEnCours(nbPlayers_, bid_), nbPlayers_);
        return GameBeloteTrickHypothesis.existPlayerNoTrump(_t,_beat,_dom,suit_,card_,sure_);
    }
    static boolean existeJouBatAdvDemat(BeloteInfoPliEnCours _t,Bytes _dom,Bytes _beat) {
        TrickBelote cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        return GameBeloteTrickHypothesis.existeJouBatAdvDemat(_t,_beat,_dom,suit_);
    }
    static boolean beatSureListTrumpDemand(BeloteInfoPliEnCours _t,Bytes _dom,Bytes _beat) {
        EnumMap<Suit, EqList<HandBelote>> poss_ = _t.getCartesPossibles();
        EnumMap<Suit, EqList<HandBelote>> sure_ = _t.getCartesCertaines();
        TrickBelote cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        byte nbPlayers_ = _t.getNbPlayers();
        BidBeloteSuit bid_ = _t.getContrat();
        byte strength_ = cur_.carteDuJoueur(cur_.getRamasseurPliEnCours(nbPlayers_,bid_),nbPlayers_).strength(suit_,bid_);
        return GameBeloteTrickHypothesis.beatSureListTrumpDemand(_beat,_dom,suit_,bid_,poss_,sure_,strength_);
    }
    static boolean beatFoeTrumpDemand(BeloteInfoPliEnCours _t, Bytes _beat) {
        TrickBelote cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        byte nbPlayers_ = _t.getNbPlayers();
        BidBeloteSuit bid_ = _t.getContrat();
        byte strength_ = cur_.carteDuJoueur(cur_.getRamasseurPliEnCours(nbPlayers_,bid_),nbPlayers_).strength(suit_,bid_);
        return beatFoeTrumpDemand(_t, _beat, strength_);
    }
    static boolean beatFoeTrumpDemand(BeloteInfoPliEnCours _t, Bytes _beat, int _str) {
        EnumMap<Suit, EqList<HandBelote>> poss_ = _t.getCartesPossibles();
        TrickBelote cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        BidBeloteSuit bid_ = _t.getContrat();
        return GameBeloteTrickHypothesis.beatFoeTrumpDemand(_beat,suit_,bid_,poss_, (byte) _str);
    }
    static boolean existeJoueurNonJoueBattantAdv(BeloteInfoPliEnCours _t,Bytes _dom,Bytes _beat) {
        TrickBelote cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        return GameBeloteTrickHypothesis.existeJoueurNonJoueBattantAdv(_t,_beat,_dom,suit_);
    }
    
    static boolean ramasseurBatSsCprAdv(BeloteInfoPliEnCours _t,Bytes _beat) {
        TrickBelote cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        BidBeloteSuit bid_ = _t.getContrat();
        byte nbPlayers_ = _t.getNbPlayers();
        byte strength_ = cur_.carteDuJoueur(cur_.getRamasseurPliEnCours(nbPlayers_,bid_),nbPlayers_).strength(suit_,bid_);
        return GameBeloteTrickHypothesis.ramasseurBatSsCprAdv(_t,_beat,suit_,strength_);
    }
    static boolean beatSureListTrumpNormalSuit(BeloteInfoPliEnCours _t, Bytes _dom,Bytes _beat) {
        TrickBelote cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        BidBeloteSuit bid_ = _t.getContrat();
        byte nbPlayers_ = _t.getNbPlayers();
        byte strength_ = cur_.carteDuJoueur(cur_.getRamasseurPliEnCours(nbPlayers_,bid_),nbPlayers_).strength(suit_,bid_);
        return beatSureListTrumpNormalSuit(_t,_dom,_beat, strength_);
    }
    static boolean beatSureListTrumpNormalSuit(BeloteInfoPliEnCours _t, Bytes _dom,Bytes _beat,int _str) {
        EnumMap<Suit, EqList<HandBelote>> poss_ = _t.getCartesPossibles();
        EnumMap<Suit, EqList<HandBelote>> sure_ = _t.getCartesCertaines();
        Suit suit_ = _t.getProgressingTrick().couleurDemandee();
        BidBeloteSuit bid_ = _t.getContrat();
        return GameBeloteTrickHypothesis.beatSureListTrumpNormalSuit(_beat,_dom,suit_,bid_,poss_,sure_, (byte) _str);
    }
    static boolean beatByTrumpNormalSuitStrength(BeloteInfoPliEnCours _t, Bytes _beat) {
        byte nbPlayers_ = _t.getNbPlayers();
        TrickBelote cur_ = _t.getProgressingTrick();
        BidBeloteSuit bid_ = _t.getContrat();
        byte strength_ = cur_.carteDuJoueur(cur_.getRamasseurPliEnCours(nbPlayers_,bid_),nbPlayers_).strength(cur_.couleurDemandee(),bid_);
        return beatByTrumpNormalSuitStrength(_t, _beat, strength_);
    }
    static boolean beatByTrumpNormalSuitStrength(BeloteInfoPliEnCours _t, Bytes _beat,int _str) {
        EnumMap<Suit, EqList<HandBelote>> poss_ = _t.getCartesPossibles();
        EnumMap<Suit, EqList<HandBelote>> sure_ = _t.getCartesCertaines();
        Suit suit_ = _t.getProgressingTrick().couleurDemandee();
        BidBeloteSuit bid_ = _t.getContrat();
        return GameBeloteTrickHypothesis.beatByTrumpNormalSuitStrength(_beat,suit_,bid_,poss_,sure_, (byte) _str);
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
        Bytes played_ = _current.playersHavingPlayed((byte) _nbPlayers);
        byte nextPlayer_ = _current.getNextPlayer((byte) _nbPlayers);
        EnumMap<Suit,HandBelote> repartition_ = _cartes.couleurs(_bid);
        EnumMap<Suit,HandBelote> repartitionCartesJouees_ = _playedCard.couleurs(_bid);
        Bytes joueursNonJoue_ = GameBeloteTeamsRelation.autresJoueurs(played_, (byte) _nbPlayers);

        byte ramasseurVirtuel_ = _current.getRamasseurPliEnCours((byte) _nbPlayers, _bid);
        EnumMap<Suit,EqList<HandBelote>> suitesTouteCouleur_ = _cartes.eclaterTout(repartitionCartesJouees_, _bid);

        EnumMap<Suit,HandBelote> cartesMaitresses_ = GameBeloteCommon.cartesMaitresses(
                repartition_, repartitionCartesJouees_, _bid);

        GameBeloteTeamsRelation teamsRelation_ = new GameBeloteTeamsRelation((byte) _taker,_rules);
        BeloteInfoPliEnCours info_ = new BeloteInfoPliEnCours();
        info_.setContrat(_bid);
        info_.setCartesJouables(_cartesJouables);
        info_.setProgressingTrick(_current);
        info_.setNbPlayers((byte) _nbPlayers);
        info_.setNextPlayer(nextPlayer_);
        info_.setJoueursConfiance(teamsRelation_.partenaires(nextPlayer_));
        info_.setJoueursNonConfiance(teamsRelation_.adversaires(nextPlayer_));
        info_.setJoueursNonJoue(joueursNonJoue_);
        info_.setJoueursJoue(played_);
        info_.setPlisFaits(new CustList<TrickBelote>());
        info_.setCartesJouees(_playedCard);
        info_.setRepartitionCartesJouees(repartitionCartesJouees_);
        info_.setCartesPossibles(generate(_nbPlayers));
        info_.setCartesCertaines(generate(_nbPlayers));
        info_.setRamasseurVirtuel(ramasseurVirtuel_);
        info_.setSuitesTouteCouleur(suitesTouteCouleur_);
        info_.setCouleursMaitresses(new EnumList<Suit>());
        info_.setStrictCouleursMaitresses(new EnumList<Suit>());
        info_.setCartesMaitresses(cartesMaitresses_);
        return info_;
    }
    private static EnumMap<Suit,EqList<HandBelote>> generate(int _nbPlayer) {
        EnumMap<Suit,EqList<HandBelote>> e_ = new EnumMap<Suit,EqList<HandBelote>>();
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.addAllElts(Suit.couleursOrdinaires());
        for (Suit s: s_) {
            EqList<HandBelote> l_ = new EqList<HandBelote>();
            for (int i = 0; i < _nbPlayer; i++) {
                l_.add(new HandBelote());
            }
            e_.addEntry(s,l_);
        }
        return e_;
    }
}
