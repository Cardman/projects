package cards.tarot;

import cards.consts.PossibleTrickWinner;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.*;
import org.junit.Test;

import static cards.tarot.EquallableTarotUtil.assertEq;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public final class GameTarotTrickHypothesisProgTest extends CommonGameTarot {
    @Test
    public void joueursPouvantCouperCouleursTest() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.HEART_JACK);
        addPossibleCard(t_,1,CardTarot.TRUMP_11);
        addSureCard(t_,1, CardTarot.TRUMP_11);
        addPossibleCard(t_,3,CardTarot.TRUMP_12);
        addSureCard(t_,3, CardTarot.TRUMP_12);
        addPossibleCard(t_,4,CardTarot.HEART_KNIGHT);
        addSureCard(t_,4, CardTarot.HEART_KNIGHT);
        Bytes players_ = GameTarotTrickHypothesis.joueursPouvantCouperCouleurs(hand_, GameTarotTeamsRelation.tousJoueurs((byte) 5), t_.getCartesPossibles(), Suit.couleursOrdinaires());
        assertEq(2, players_.size());
        assertTrue(players_.contains(1));
        assertTrue(players_.contains(3));
    }
    @Test
    public void beatByTrumpNormalSuitStrength1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_5);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_2);
        addSureCard(t_,1,CardTarot.DIAMOND_2);
        addPossibleCard(t_,1,CardTarot.TRUMP_11);
        addSureCard(t_,1, CardTarot.TRUMP_11);
        addPossibleCard(t_,0,CardTarot.TRUMP_3);
        addSureCard(t_,0, CardTarot.TRUMP_3);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 0);
        beat_.add((byte) 1);
        assertTrue(beatByTrumpNormalSuitStrength(t_,beat_));
    }
    @Test
    public void beatByTrumpNormalSuitStrength2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_5);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_2);
        addSureCard(t_,1,CardTarot.DIAMOND_2);
        addPossibleCard(t_,1,CardTarot.TRUMP_11);
        addSureCard(t_,1, CardTarot.TRUMP_11);
        addPossibleCard(t_,0,CardTarot.TRUMP_13);
        addSureCard(t_,0, CardTarot.TRUMP_13);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 0);
        beat_.add((byte) 1);
        assertTrue(!beatByTrumpNormalSuitStrength(t_,beat_));
    }
    @Test
    public void beatByTrumpNormalSuitStrength3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_5);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_2);
        addSureCard(t_,1,CardTarot.DIAMOND_2);
        addPossibleCard(t_,1,CardTarot.TRUMP_11);
        addSureCard(t_,1, CardTarot.TRUMP_11);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 0);
        beat_.add((byte) 1);
        assertTrue(beatByTrumpNormalSuitStrength(t_,beat_));
    }
    @Test
    public void beatByTrumpNormalSuit1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_5);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_11);
        addSureCard(t_,1, CardTarot.TRUMP_11);
        addPossibleCard(t_,0,CardTarot.TRUMP_13);
        addSureCard(t_,0, CardTarot.TRUMP_13);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(beatByTrumpNormalSuit(t_,beat_,0));
    }
    @Test
    public void beatByTrumpNormalSuit2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_5);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_13);
        addSureCard(t_,1, CardTarot.TRUMP_13);
        addPossibleCard(t_,0,CardTarot.TRUMP_11);
        addSureCard(t_,0, CardTarot.TRUMP_11);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(!beatByTrumpNormalSuit(t_,beat_,0));
    }
    @Test
    public void beatSureListTrumpNormalSuit1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,0,CardTarot.TRUMP_14);
        addSureCard(t_,0, CardTarot.TRUMP_14);
        addPossibleCard(t_,1,CardTarot.TRUMP_15);
        addSureCard(t_,1, CardTarot.TRUMP_15);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,0,CardTarot.TRUMP_15);
        addSureCard(t_,0, CardTarot.TRUMP_15);
        addPossibleCard(t_,1,CardTarot.TRUMP_14);
        addSureCard(t_,1, CardTarot.TRUMP_14);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,0,CardTarot.TRUMP_4);
        addSureCard(t_,0, CardTarot.TRUMP_4);
        addPossibleCard(t_,1,CardTarot.TRUMP_5);
        addSureCard(t_,1, CardTarot.TRUMP_5);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit4Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,0,CardTarot.TRUMP_5);
        addSureCard(t_,0, CardTarot.TRUMP_5);
        addPossibleCard(t_,1,CardTarot.TRUMP_4);
        addSureCard(t_,1, CardTarot.TRUMP_4);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit5Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,0,CardTarot.TRUMP_14);
        addSureCard(t_,0, CardTarot.TRUMP_14);
        addPossibleCard(t_,1,CardTarot.TRUMP_15);
        addSureCard(t_,1, CardTarot.TRUMP_15);
        addPossibleCard(t_,1,CardTarot.DIAMOND_2);
        addSureCard(t_,1, CardTarot.DIAMOND_2);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit6Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_15);
        addSureCard(t_,1, CardTarot.TRUMP_15);
        addPossibleCard(t_,1,CardTarot.DIAMOND_2);
        addSureCard(t_,1, CardTarot.DIAMOND_2);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpNormalSuit7Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,0,CardTarot.DIAMOND_2);
        addSureCard(t_,0, CardTarot.DIAMOND_2);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!beatSureListTrumpNormalSuit(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpDemandPast1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_5);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_15);
        addSureCard(t_,1, CardTarot.TRUMP_15);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(!beatSureListTrumpDemandPast(t_,beat_));
    }
    @Test
    public void beatSureListTrumpDemandPast2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_5);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_8);
        addSureCard(t_,1, CardTarot.TRUMP_8);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(beatSureListTrumpDemandPast(t_,beat_));
    }
    @Test
    public void beatSureListTrumpDemandPast3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_5);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(beatSureListTrumpDemandPast(t_,beat_));
    }
    @Test
    public void beatSureListTrumpDemand1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_5);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,0,CardTarot.TRUMP_21);
        addSureCard(t_,0, CardTarot.TRUMP_21);
        addPossibleCard(t_,1,CardTarot.TRUMP_20);
        addSureCard(t_,1, CardTarot.TRUMP_20);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(beatSureListTrumpDemand(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpDemand2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_5);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,0,CardTarot.TRUMP_20);
        addSureCard(t_,0, CardTarot.TRUMP_20);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addSureCard(t_,1, CardTarot.TRUMP_21);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!beatSureListTrumpDemand(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpDemand3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_5);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,0,CardTarot.TRUMP_11);
        addSureCard(t_,0, CardTarot.TRUMP_11);
        addPossibleCard(t_,1,CardTarot.TRUMP_10);
        addSureCard(t_,1, CardTarot.TRUMP_10);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!beatSureListTrumpDemand(t_,dom_,beat_));
    }
    @Test
    public void beatSureListTrumpDemand4Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_5);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addSureCard(t_,1, CardTarot.TRUMP_21);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!beatSureListTrumpDemand(t_,dom_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_QUEEN);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addSureCard(t_,1, CardTarot.TRUMP_21);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(!ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_QUEEN);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addSureCard(t_,1, CardTarot.TRUMP_21);
        addPossibleCard(t_,1,CardTarot.DIAMOND_2);
        addSureCard(t_,1, CardTarot.DIAMOND_2);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_QUEEN);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv4Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_QUEEN);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_KING);
        addSureCard(t_,1, CardTarot.DIAMOND_KING);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(!ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv5Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_QUEEN);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_JACK);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void ramasseurBatSsCprAdv6Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_QUEEN);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_JACK);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_KING);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        assertTrue(!ramasseurBatSsCprAdv(t_,beat_));
    }
    @Test
    public void existeJoueurNonJoueBattantAdv1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_QUEEN);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_KING);
        addSureCard(t_,1, CardTarot.DIAMOND_KING);
        addPossibleCard(t_,0,CardTarot.TRUMP_2);
        addSureCard(t_,0, CardTarot.TRUMP_2);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(existeJoueurNonJoueBattantAdv(t_,dom_,beat_));
    }
    @Test
    public void existeJoueurNonJoueBattantAdv2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_QUEEN);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_KING);
        addSureCard(t_,1, CardTarot.DIAMOND_KING);
        addPossibleCard(t_,0,CardTarot.DIAMOND_KNIGHT);
        addSureCard(t_,0, CardTarot.DIAMOND_KNIGHT);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!existeJoueurNonJoueBattantAdv(t_,dom_,beat_));
    }
    @Test
    public void existeJoueurNonJoueBattantAdv3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_QUEEN);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addSureCard(t_,1, CardTarot.TRUMP_21);
        addPossibleCard(t_,0,CardTarot.TRUMP_11);
        addSureCard(t_,0, CardTarot.TRUMP_11);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!existeJoueurNonJoueBattantAdv(t_,dom_,beat_));
    }
    @Test
    public void existeJouBatAdvDemat1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_14);
        current_.ajouter(CardTarot.TRUMP_12);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addSureCard(t_,1, CardTarot.TRUMP_21);
        addPossibleCard(t_,0,CardTarot.TRUMP_11);
        addSureCard(t_,0, CardTarot.TRUMP_11);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!existeJouBatAdvDemat(t_,dom_,beat_));
    }
    @Test
    public void existeJouBatAdvDemat2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_14);
        current_.ajouter(CardTarot.TRUMP_12);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_16);
        addSureCard(t_,1, CardTarot.TRUMP_16);
        addPossibleCard(t_,0,CardTarot.TRUMP_21);
        addSureCard(t_,0, CardTarot.TRUMP_21);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(existeJouBatAdvDemat(t_,dom_,beat_));
    }
    @Test
    public void existeJouBatAdvDemat3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_14);
        current_.ajouter(CardTarot.TRUMP_12);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addSureCard(t_,1, CardTarot.TRUMP_21);
        Bytes beat_ = new Bytes();
        beat_.add((byte) 1);
        Bytes dom_ = new Bytes();
        dom_.add((byte) 0);
        assertTrue(!existeJouBatAdvDemat(t_,dom_,beat_));
    }
    @Test
    public void getPossibleTrickWinnerTrump1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_14);
        current_.ajouter(CardTarot.TRUMP_12);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addSureCard(t_,1, CardTarot.TRUMP_21);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrump2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_14);
        current_.ajouter(CardTarot.TRUMP_12);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addSureCard(t_,1, CardTarot.TRUMP_21);
        addPossibleCard(t_,0,CardTarot.TRUMP_16);
        addSureCard(t_,0, CardTarot.TRUMP_16);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrump3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_14);
        current_.ajouter(CardTarot.TRUMP_12);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_16);
        addSureCard(t_,1, CardTarot.TRUMP_16);
        addPossibleCard(t_,0,CardTarot.TRUMP_21);
        addSureCard(t_,0, CardTarot.TRUMP_21);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrump4Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_14);
        current_.ajouter(CardTarot.TRUMP_12);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_16);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addPossibleCard(t_,0,CardTarot.TRUMP_16);
        addPossibleCard(t_,0,CardTarot.TRUMP_21);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrump5Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_14);
        current_.ajouter(CardTarot.TRUMP_12);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addSureCard(t_,1, CardTarot.TRUMP_21);
        addPossibleCard(t_,4,CardTarot.TRUMP_13);
        addSureCard(t_,4, CardTarot.TRUMP_13);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrump6Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_14);
        current_.ajouter(CardTarot.TRUMP_12);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addSureCard(t_,1, CardTarot.TRUMP_21);
        addPossibleCard(t_,0,CardTarot.TRUMP_16);
        addSureCard(t_,0, CardTarot.TRUMP_16);
        addPossibleCard(t_,4,CardTarot.TRUMP_13);
        addSureCard(t_,4, CardTarot.TRUMP_13);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrump7Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_14);
        current_.ajouter(CardTarot.TRUMP_12);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_16);
        addSureCard(t_,1, CardTarot.TRUMP_16);
        addPossibleCard(t_,0,CardTarot.TRUMP_21);
        addSureCard(t_,0, CardTarot.TRUMP_21);
        addPossibleCard(t_,4,CardTarot.TRUMP_13);
        addSureCard(t_,4, CardTarot.TRUMP_13);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrump8Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_14);
        current_.ajouter(CardTarot.TRUMP_12);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_16);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addPossibleCard(t_,0,CardTarot.TRUMP_16);
        addPossibleCard(t_,0,CardTarot.TRUMP_21);
        addPossibleCard(t_,4,CardTarot.TRUMP_13);
        addSureCard(t_,4, CardTarot.TRUMP_13);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrump9Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_12);
        current_.ajouter(CardTarot.TRUMP_14);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addSureCard(t_,1, CardTarot.TRUMP_21);
        addPossibleCard(t_,4,CardTarot.TRUMP_13);
        addSureCard(t_,4, CardTarot.TRUMP_13);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrump10Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_12);
        current_.ajouter(CardTarot.TRUMP_14);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_11);
        addSureCard(t_,1, CardTarot.TRUMP_11);
        addPossibleCard(t_,0,CardTarot.TRUMP_16);
        addSureCard(t_,0, CardTarot.TRUMP_16);
        addPossibleCard(t_,4,CardTarot.TRUMP_13);
        addSureCard(t_,4, CardTarot.TRUMP_13);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrump11Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_12);
        current_.ajouter(CardTarot.TRUMP_14);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_16);
        addSureCard(t_,1, CardTarot.TRUMP_16);
        addPossibleCard(t_,0,CardTarot.TRUMP_21);
        addSureCard(t_,0, CardTarot.TRUMP_21);
        addPossibleCard(t_,4,CardTarot.TRUMP_13);
        addSureCard(t_,4, CardTarot.TRUMP_13);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrump12Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_12);
        current_.ajouter(CardTarot.TRUMP_14);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_16);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addPossibleCard(t_,0,CardTarot.TRUMP_16);
        addPossibleCard(t_,0,CardTarot.TRUMP_21);
        addPossibleCard(t_,4,CardTarot.TRUMP_13);
        addSureCard(t_,4, CardTarot.TRUMP_13);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrump13Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_12);
        current_.ajouter(CardTarot.TRUMP_14);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addSureCard(t_,1, CardTarot.TRUMP_21);
        addPossibleCard(t_,4,CardTarot.TRUMP_15);
        addSureCard(t_,4, CardTarot.TRUMP_15);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrump14Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_12);
        current_.ajouter(CardTarot.TRUMP_14);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_16);
        addSureCard(t_,1, CardTarot.TRUMP_16);
        addPossibleCard(t_,0,CardTarot.TRUMP_21);
        addSureCard(t_,0, CardTarot.TRUMP_21);
        addPossibleCard(t_,4,CardTarot.TRUMP_15);
        addSureCard(t_,4, CardTarot.TRUMP_15);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrump15Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_12);
        current_.ajouter(CardTarot.TRUMP_14);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_16);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addPossibleCard(t_,0,CardTarot.TRUMP_16);
        addPossibleCard(t_,0,CardTarot.TRUMP_21);
        addPossibleCard(t_,4,CardTarot.TRUMP_15);
        addSureCard(t_,4, CardTarot.TRUMP_15);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrump16Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_12);
        current_.ajouter(CardTarot.TRUMP_14);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_16);
        addPossibleCard(t_,1,CardTarot.TRUMP_20);
        addPossibleCard(t_,0,CardTarot.TRUMP_16);
        addPossibleCard(t_,0,CardTarot.TRUMP_20);
        addPossibleCard(t_,4,CardTarot.TRUMP_15);
        addSureCard(t_,4, CardTarot.TRUMP_15);
        addPossibleCard(t_,4,CardTarot.TRUMP_21);
        addSureCard(t_,4, CardTarot.TRUMP_21);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_8);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_KING);
        addSureCard(t_,1,CardTarot.DIAMOND_KING);
        addPossibleCard(t_,0,CardTarot.DIAMOND_QUEEN);
        addSureCard(t_,0,CardTarot.DIAMOND_QUEEN);
        addPossibleCard(t_,4,CardTarot.DIAMOND_6);
        addSureCard(t_,4,CardTarot.DIAMOND_6);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_8);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_4);
        addSureCard(t_,1,CardTarot.DIAMOND_4);
        addPossibleCard(t_,0,CardTarot.DIAMOND_5);
        addSureCard(t_,0,CardTarot.DIAMOND_5);
        addPossibleCard(t_,4,CardTarot.DIAMOND_6);
        addSureCard(t_,4,CardTarot.DIAMOND_6);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_8);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_QUEEN);
        addSureCard(t_,1,CardTarot.DIAMOND_QUEEN);
        addPossibleCard(t_,0,CardTarot.DIAMOND_KING);
        addSureCard(t_,0,CardTarot.DIAMOND_KING);
        addPossibleCard(t_,4,CardTarot.DIAMOND_6);
        addSureCard(t_,4,CardTarot.DIAMOND_6);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump4Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_KING);
        addSureCard(t_,1,CardTarot.DIAMOND_KING);
        addPossibleCard(t_,0,CardTarot.DIAMOND_QUEEN);
        addSureCard(t_,0,CardTarot.DIAMOND_QUEEN);
        addPossibleCard(t_,4,CardTarot.DIAMOND_6);
        addSureCard(t_,4,CardTarot.DIAMOND_6);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump5Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_4);
        addSureCard(t_,1,CardTarot.DIAMOND_4);
        addPossibleCard(t_,0,CardTarot.DIAMOND_5);
        addSureCard(t_,0,CardTarot.DIAMOND_5);
        addPossibleCard(t_,4,CardTarot.DIAMOND_6);
        addSureCard(t_,4,CardTarot.DIAMOND_6);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump6Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_QUEEN);
        addSureCard(t_,1,CardTarot.DIAMOND_QUEEN);
        addPossibleCard(t_,0,CardTarot.DIAMOND_KING);
        addSureCard(t_,0,CardTarot.DIAMOND_KING);
        addPossibleCard(t_,4,CardTarot.DIAMOND_6);
        addSureCard(t_,4,CardTarot.DIAMOND_6);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump7Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_8);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_2);
        addPossibleCard(t_,1,CardTarot.DIAMOND_QUEEN);
        addPossibleCard(t_,0,CardTarot.DIAMOND_KING);
        addSureCard(t_,0,CardTarot.DIAMOND_KING);
        addPossibleCard(t_,4,CardTarot.DIAMOND_6);
        addSureCard(t_,4,CardTarot.DIAMOND_6);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump8Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_KING);
        addSureCard(t_,1,CardTarot.DIAMOND_KING);
        addPossibleCard(t_,0,CardTarot.DIAMOND_QUEEN);
        addPossibleCard(t_,0,CardTarot.TRUMP_2);
        addPossibleCard(t_,4,CardTarot.DIAMOND_6);
        addSureCard(t_,4,CardTarot.DIAMOND_6);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump9Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_4);
        addSureCard(t_,1,CardTarot.DIAMOND_4);
        addPossibleCard(t_,0,CardTarot.DIAMOND_5);
        addSureCard(t_,0,CardTarot.DIAMOND_5);
        addPossibleCard(t_,4,CardTarot.DIAMOND_6);
        addSureCard(t_,4,CardTarot.DIAMOND_6);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump10Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_8);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_2);
        addPossibleCard(t_,1,CardTarot.DIAMOND_QUEEN);
        addPossibleCard(t_,0,CardTarot.TRUMP_3);
        addPossibleCard(t_,0,CardTarot.DIAMOND_KING);
        addPossibleCard(t_,4,CardTarot.DIAMOND_6);
        addSureCard(t_,4,CardTarot.DIAMOND_6);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump11Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_2);
        addPossibleCard(t_,1,CardTarot.DIAMOND_QUEEN);
        addPossibleCard(t_,0,CardTarot.TRUMP_3);
        addPossibleCard(t_,0,CardTarot.DIAMOND_KING);
        addPossibleCard(t_,4,CardTarot.DIAMOND_6);
        addSureCard(t_,4,CardTarot.DIAMOND_6);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump12Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_7);
        addSureCard(t_,1,CardTarot.DIAMOND_7);
        addPossibleCard(t_,0,CardTarot.DIAMOND_9);
        addSureCard(t_,0,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.DIAMOND_6);
        addSureCard(t_,4,CardTarot.DIAMOND_6);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump13Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_11);
        addPossibleCard(t_,1,CardTarot.DIAMOND_7);
        addPossibleCard(t_,0,CardTarot.DIAMOND_9);
        addSureCard(t_,0,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.DIAMOND_6);
        addSureCard(t_,4,CardTarot.DIAMOND_6);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump14Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,0,CardTarot.DIAMOND_9);
        addSureCard(t_,0,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.DIAMOND_6);
        addSureCard(t_,4,CardTarot.DIAMOND_6);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump15Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_2);
        addPossibleCard(t_,1,CardTarot.DIAMOND_7);
        addSureCard(t_,1,CardTarot.DIAMOND_7);
        addPossibleCard(t_,0,CardTarot.DIAMOND_9);
        addSureCard(t_,0,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.DIAMOND_6);
        addSureCard(t_,4,CardTarot.DIAMOND_6);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump16Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.TRUMP_2);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.TRUMP_2);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_4);
        addPossibleCard(t_,1,CardTarot.TRUMP_3);
        addPossibleCard(t_,0,CardTarot.TRUMP_4);
        addPossibleCard(t_,0,CardTarot.TRUMP_3);
        addPossibleCard(t_,4,CardTarot.TRUMP_2);
        addSureCard(t_,4,CardTarot.TRUMP_2);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump17Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.TRUMP_2);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.TRUMP_2);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_3);
        addSureCard(t_,1,CardTarot.TRUMP_3);
        addPossibleCard(t_,0,CardTarot.DIAMOND_9);
        addSureCard(t_,0,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.TRUMP_2);
        addSureCard(t_,4,CardTarot.TRUMP_2);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump18Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_21);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.TRUMP_2);
        playable_.ajouter(CardTarot.TRUMP_21);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_3);
        addSureCard(t_,1,CardTarot.TRUMP_3);
        addPossibleCard(t_,0,CardTarot.DIAMOND_9);
        addSureCard(t_,0,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.TRUMP_21);
        addSureCard(t_,4,CardTarot.TRUMP_21);
        addPossibleCard(t_,4,CardTarot.TRUMP_2);
        addSureCard(t_,4,CardTarot.TRUMP_2);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump19Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_20);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.TRUMP_2);
        playable_.ajouter(CardTarot.TRUMP_20);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_3);
        addSureCard(t_,1,CardTarot.TRUMP_3);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addSureCard(t_,1,CardTarot.TRUMP_21);
        addPossibleCard(t_,0,CardTarot.DIAMOND_9);
        addSureCard(t_,0,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.TRUMP_20);
        addSureCard(t_,4,CardTarot.TRUMP_20);
        addPossibleCard(t_,4,CardTarot.TRUMP_2);
        addSureCard(t_,4,CardTarot.TRUMP_2);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump20Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_10);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.TRUMP_2);
        playable_.ajouter(CardTarot.TRUMP_10);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_3);
        addSureCard(t_,1,CardTarot.TRUMP_3);
        addPossibleCard(t_,1,CardTarot.TRUMP_20);
        addSureCard(t_,1,CardTarot.TRUMP_20);
        addPossibleCard(t_,0,CardTarot.TRUMP_21);
        addSureCard(t_,0,CardTarot.TRUMP_21);
        addPossibleCard(t_,4,CardTarot.TRUMP_10);
        addSureCard(t_,4,CardTarot.TRUMP_10);
        addPossibleCard(t_,4,CardTarot.TRUMP_2);
        addSureCard(t_,4,CardTarot.TRUMP_2);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump21Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_7);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_7);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_JACK);
        addSureCard(t_,1,CardTarot.DIAMOND_JACK);
        addPossibleCard(t_,0,CardTarot.DIAMOND_10);
        addSureCard(t_,0,CardTarot.DIAMOND_10);
        addPossibleCard(t_,4,CardTarot.DIAMOND_7);
        addSureCard(t_,4,CardTarot.DIAMOND_7);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump22Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_9);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_JACK);
        addSureCard(t_,1,CardTarot.DIAMOND_JACK);
        addPossibleCard(t_,0,CardTarot.DIAMOND_10);
        addSureCard(t_,0,CardTarot.DIAMOND_10);
        addPossibleCard(t_,4,CardTarot.DIAMOND_9);
        addSureCard(t_,4,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.DIAMOND_KING);
        addSureCard(t_,4,CardTarot.DIAMOND_KING);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump23Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_9);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_8);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.DIAMOND_JACK);
        addSureCard(t_,1,CardTarot.DIAMOND_JACK);
        addPossibleCard(t_,0,CardTarot.DIAMOND_10);
        addSureCard(t_,0,CardTarot.DIAMOND_10);
        addPossibleCard(t_,4,CardTarot.DIAMOND_9);
        addSureCard(t_,4,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.DIAMOND_KING);
        addSureCard(t_,4,CardTarot.DIAMOND_KING);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump24Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_9);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_8);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_2);
        addSureCard(t_,1,CardTarot.TRUMP_2);
        addPossibleCard(t_,0,CardTarot.DIAMOND_10);
        addSureCard(t_,0,CardTarot.DIAMOND_10);
        addPossibleCard(t_,4,CardTarot.DIAMOND_9);
        addSureCard(t_,4,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.DIAMOND_KING);
        addSureCard(t_,4,CardTarot.DIAMOND_KING);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump25Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_9);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_8);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_3);
        addSureCard(t_,1,CardTarot.TRUMP_3);
        addPossibleCard(t_,0,CardTarot.TRUMP_2);
        addSureCard(t_,0,CardTarot.TRUMP_2);
        addPossibleCard(t_,4,CardTarot.DIAMOND_9);
        addSureCard(t_,4,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.DIAMOND_KING);
        addSureCard(t_,4,CardTarot.DIAMOND_KING);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump26Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_9);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_8);
        current_.ajouter(CardTarot.DIAMOND_1);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_2);
        addSureCard(t_,1,CardTarot.TRUMP_2);
        addPossibleCard(t_,0,CardTarot.TRUMP_3);
        addSureCard(t_,0,CardTarot.TRUMP_3);
        addPossibleCard(t_,4,CardTarot.DIAMOND_9);
        addSureCard(t_,4,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.DIAMOND_KING);
        addSureCard(t_,4,CardTarot.DIAMOND_KING);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump27Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_9);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_8);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_3);
        addSureCard(t_,1,CardTarot.TRUMP_3);
        addPossibleCard(t_,0,CardTarot.TRUMP_2);
        addPossibleCard(t_,4,CardTarot.DIAMOND_9);
        addSureCard(t_,4,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.DIAMOND_KING);
        addSureCard(t_,4,CardTarot.DIAMOND_KING);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerNoTrump28Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_9);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_8);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_3);
        addSureCard(t_,1,CardTarot.TRUMP_3);
        addPossibleCard(t_,0,CardTarot.TRUMP_4);
        addPossibleCard(t_,4,CardTarot.DIAMOND_9);
        addSureCard(t_,4,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.DIAMOND_KING);
        addSureCard(t_,4,CardTarot.DIAMOND_KING);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerNoTrump(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_9);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_3);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_4);
        addSureCard(t_,1,CardTarot.TRUMP_4);
        addPossibleCard(t_,0,CardTarot.TRUMP_5);
        addPossibleCard(t_,4,CardTarot.DIAMOND_9);
        addSureCard(t_,4,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.DIAMOND_KING);
        addSureCard(t_,4,CardTarot.DIAMOND_KING);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_9);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_3);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_4);
        addSureCard(t_,1,CardTarot.TRUMP_4);
        addPossibleCard(t_,0,CardTarot.TRUMP_5);
        addSureCard(t_,0,CardTarot.TRUMP_5);
        addPossibleCard(t_,4,CardTarot.DIAMOND_9);
        addSureCard(t_,4,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.DIAMOND_KING);
        addSureCard(t_,4,CardTarot.DIAMOND_KING);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_9);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_3);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_5);
        addSureCard(t_,1,CardTarot.TRUMP_5);
        addPossibleCard(t_,0,CardTarot.TRUMP_4);
        addSureCard(t_,0,CardTarot.TRUMP_4);
        addPossibleCard(t_,4,CardTarot.DIAMOND_9);
        addSureCard(t_,4,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.DIAMOND_KING);
        addSureCard(t_,4,CardTarot.DIAMOND_KING);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit4Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_9);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_5);
        addSureCard(t_,1,CardTarot.TRUMP_5);
        addPossibleCard(t_,0,CardTarot.TRUMP_4);
        addSureCard(t_,0,CardTarot.TRUMP_4);
        addPossibleCard(t_,4,CardTarot.DIAMOND_9);
        addSureCard(t_,4,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.DIAMOND_KING);
        addSureCard(t_,4,CardTarot.DIAMOND_KING);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit5Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_9);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)3,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_3);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 2);
        team_.add((byte) 3);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,4,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_4);
        addSureCard(t_,1,CardTarot.TRUMP_4);
        addPossibleCard(t_,2,CardTarot.TRUMP_5);
        addPossibleCard(t_,0,CardTarot.DIAMOND_9);
        addSureCard(t_,0,CardTarot.DIAMOND_9);
        addPossibleCard(t_,0,CardTarot.DIAMOND_KING);
        addSureCard(t_,0,CardTarot.DIAMOND_KING);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit6Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_9);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)3,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_3);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 2);
        team_.add((byte) 3);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,4,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,2,CardTarot.TRUMP_4);
        addSureCard(t_,2,CardTarot.TRUMP_4);
        addPossibleCard(t_,1,CardTarot.TRUMP_5);
        addSureCard(t_,1,CardTarot.TRUMP_5);
        addPossibleCard(t_,0,CardTarot.DIAMOND_9);
        addSureCard(t_,0,CardTarot.DIAMOND_9);
        addPossibleCard(t_,0,CardTarot.DIAMOND_KING);
        addSureCard(t_,0,CardTarot.DIAMOND_KING);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit7Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_9);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)3,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_3);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 2);
        team_.add((byte) 3);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,4,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,2,CardTarot.TRUMP_5);
        addSureCard(t_,2,CardTarot.TRUMP_5);
        addPossibleCard(t_,1,CardTarot.TRUMP_4);
        addSureCard(t_,1,CardTarot.TRUMP_4);
        addPossibleCard(t_,0,CardTarot.DIAMOND_9);
        addSureCard(t_,0,CardTarot.DIAMOND_9);
        addPossibleCard(t_,0,CardTarot.DIAMOND_KING);
        addSureCard(t_,0,CardTarot.DIAMOND_KING);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit8Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_9);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)3,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 2);
        team_.add((byte) 3);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,4,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,2,CardTarot.TRUMP_5);
        addSureCard(t_,2,CardTarot.TRUMP_5);
        addPossibleCard(t_,1,CardTarot.TRUMP_4);
        addSureCard(t_,1,CardTarot.TRUMP_4);
        addPossibleCard(t_,0,CardTarot.DIAMOND_9);
        addSureCard(t_,0,CardTarot.DIAMOND_9);
        addPossibleCard(t_,0,CardTarot.DIAMOND_KING);
        addSureCard(t_,0,CardTarot.DIAMOND_KING);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit9Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_JACK);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.HEART_9);
        playable_.ajouter(CardTarot.HEART_JACK);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.HEART_1);
        current_.ajouter(CardTarot.TRUMP_3);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        t_.setCarteAppeleeJouee(true);
        addPossibleCard(t_,1,CardTarot.TRUMP_4);
        addSureCard(t_,1,CardTarot.TRUMP_4);
        addPossibleCard(t_,0,CardTarot.TRUMP_5);
        addPossibleCard(t_,4,CardTarot.HEART_9);
        addSureCard(t_,4,CardTarot.HEART_9);
        addPossibleCard(t_,4,CardTarot.HEART_JACK);
        addSureCard(t_,4,CardTarot.HEART_JACK);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit10Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_JACK);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.HEART_9);
        playable_.ajouter(CardTarot.HEART_JACK);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.HEART_1);
        current_.ajouter(CardTarot.TRUMP_3);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_4);
        addSureCard(t_,1,CardTarot.TRUMP_4);
        addPossibleCard(t_,0,CardTarot.TRUMP_5);
        addPossibleCard(t_,4,CardTarot.HEART_9);
        addSureCard(t_,4,CardTarot.HEART_9);
        addPossibleCard(t_,4,CardTarot.HEART_JACK);
        addSureCard(t_,4,CardTarot.HEART_JACK);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit11Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_JACK);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.HEART_9);
        playable_.ajouter(CardTarot.HEART_JACK);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.HEART_1);
        current_.ajouter(CardTarot.TRUMP_3);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,1,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_4);
        addSureCard(t_,1,CardTarot.TRUMP_4);
        addPossibleCard(t_,0,CardTarot.TRUMP_5);
        addPossibleCard(t_,4,CardTarot.HEART_9);
        addSureCard(t_,4,CardTarot.HEART_9);
        addPossibleCard(t_,4,CardTarot.HEART_JACK);
        addSureCard(t_,4,CardTarot.HEART_JACK);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit12Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.HEART_9);
        playable_.ajouter(CardTarot.HEART_KING);
        TrickTarot current_ = new TrickTarot((byte)0,true);
        current_.ajouter(CardTarot.HEART_1);
        current_.ajouter(CardTarot.TRUMP_3);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 1);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,1,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,3,CardTarot.TRUMP_5);
        addPossibleCard(t_,4,CardTarot.HEART_9);
        addSureCard(t_,4,CardTarot.HEART_9);
        addPossibleCard(t_,1,CardTarot.HEART_KING);
        addSureCard(t_,1,CardTarot.HEART_KING);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit13Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_JACK);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.HEART_9);
        playable_.ajouter(CardTarot.HEART_JACK);
        TrickTarot current_ = new TrickTarot((byte)4,true);
        current_.ajouter(CardTarot.HEART_1);
        current_.ajouter(CardTarot.TRUMP_3);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 2);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,1,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        t_.setCarteAppeleeJouee(true);
        addPossibleCard(t_,0,CardTarot.TRUMP_5);
        addPossibleCard(t_,1,CardTarot.HEART_9);
        addSureCard(t_,1,CardTarot.HEART_9);
        addPossibleCard(t_,1,CardTarot.HEART_JACK);
        addSureCard(t_,1,CardTarot.HEART_JACK);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit14Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.HEART_9);
        playable_.ajouter(CardTarot.HEART_KING);
        TrickTarot current_ = new TrickTarot((byte)4,true);
        current_.ajouter(CardTarot.HEART_1);
        current_.ajouter(CardTarot.TRUMP_3);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 2);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,0,CardTarot.TRUMP_5);
        addPossibleCard(t_,1,CardTarot.HEART_9);
        addSureCard(t_,1,CardTarot.HEART_9);
        addPossibleCard(t_,1,CardTarot.HEART_KING);
        addSureCard(t_,1,CardTarot.HEART_KING);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit15Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_JACK);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.HEART_9);
        playable_.ajouter(CardTarot.HEART_JACK);
        TrickTarot current_ = new TrickTarot((byte)4,true);
        current_.ajouter(CardTarot.HEART_1);
        current_.ajouter(CardTarot.TRUMP_3);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 2);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,1,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,0,CardTarot.TRUMP_5);
        addPossibleCard(t_,1,CardTarot.HEART_9);
        addSureCard(t_,1,CardTarot.HEART_9);
        addPossibleCard(t_,1,CardTarot.HEART_JACK);
        addSureCard(t_,1,CardTarot.HEART_JACK);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit16Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_1);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.TRUMP_2);
        playable_.ajouter(CardTarot.TRUMP_1);
        TrickTarot current_ = new TrickTarot((byte)3,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 2);
        team_.add((byte) 3);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,4,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,2,CardTarot.TRUMP_5);
        addSureCard(t_,2,CardTarot.TRUMP_5);
        addPossibleCard(t_,1,CardTarot.TRUMP_4);
        addSureCard(t_,1,CardTarot.TRUMP_4);
        addPossibleCard(t_,0,CardTarot.TRUMP_2);
        addSureCard(t_,0,CardTarot.TRUMP_2);
        addPossibleCard(t_,0,CardTarot.TRUMP_1);
        addSureCard(t_,0,CardTarot.TRUMP_1);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit17Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        TrickTarot current_ = new TrickTarot((byte)3,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_13);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 2);
        team_.add((byte) 3);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,4,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,2,CardTarot.TRUMP_5);
        addSureCard(t_,2,CardTarot.TRUMP_5);
        addPossibleCard(t_,1,CardTarot.TRUMP_4);
        addSureCard(t_,1,CardTarot.TRUMP_4);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit18Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        TrickTarot current_ = new TrickTarot((byte)3,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_8);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 2);
        team_.add((byte) 3);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,4,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,2,CardTarot.TRUMP_15);
        addSureCard(t_,2,CardTarot.TRUMP_15);
        addPossibleCard(t_,1,CardTarot.TRUMP_14);
        addSureCard(t_,1,CardTarot.TRUMP_14);
        addPossibleCard(t_,0,CardTarot.TRUMP_13);
        addSureCard(t_,0,CardTarot.TRUMP_13);
        addPossibleCard(t_,0,CardTarot.TRUMP_21);
        addSureCard(t_,0,CardTarot.TRUMP_21);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit19Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        TrickTarot current_ = new TrickTarot((byte)3,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_8);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 2);
        team_.add((byte) 3);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,4,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,2,CardTarot.TRUMP_15);
        addSureCard(t_,2,CardTarot.TRUMP_15);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addSureCard(t_,1,CardTarot.TRUMP_21);
        addPossibleCard(t_,0,CardTarot.TRUMP_13);
        addSureCard(t_,0,CardTarot.TRUMP_13);
        addPossibleCard(t_,0,CardTarot.TRUMP_14);
        addSureCard(t_,0,CardTarot.TRUMP_14);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit20Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        TrickTarot current_ = new TrickTarot((byte)3,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_8);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 2);
        team_.add((byte) 3);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,4,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,2,CardTarot.TRUMP_21);
        addSureCard(t_,2,CardTarot.TRUMP_21);
        addPossibleCard(t_,1,CardTarot.TRUMP_15);
        addSureCard(t_,1,CardTarot.TRUMP_15);
        addPossibleCard(t_,0,CardTarot.TRUMP_13);
        addSureCard(t_,0,CardTarot.TRUMP_13);
        addPossibleCard(t_,0,CardTarot.TRUMP_14);
        addSureCard(t_,0,CardTarot.TRUMP_14);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit21Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_8);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 0);
        team_.add((byte) 3);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,0,CardTarot.TRUMP_15);
        addSureCard(t_,0,CardTarot.TRUMP_15);
        addPossibleCard(t_,1,CardTarot.TRUMP_20);
        addSureCard(t_,1,CardTarot.TRUMP_20);
        addPossibleCard(t_,4,CardTarot.TRUMP_21);
        addSureCard(t_,4,CardTarot.TRUMP_21);
        addPossibleCard(t_,4,CardTarot.TRUMP_14);
        addSureCard(t_,4,CardTarot.TRUMP_14);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void getPossibleTrickWinnerTrumpSuit22Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_8);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 0);
        team_.add((byte) 3);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,0,CardTarot.TRUMP_17);
        addPossibleCard(t_,0,CardTarot.TRUMP_21);
        addPossibleCard(t_,1,CardTarot.TRUMP_17);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addPossibleCard(t_,4,CardTarot.TRUMP_14);
        addSureCard(t_,4,CardTarot.TRUMP_14);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.getPossibleTrickWinnerTrumpSuit(t_));
    }
    @Test
    public void equipeQuiVaFairePli1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        TrickTarot current_ = new TrickTarot((byte)3,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.TRUMP_8);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 2);
        team_.add((byte) 3);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,4,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,2,CardTarot.TRUMP_21);
        addSureCard(t_,2,CardTarot.TRUMP_21);
        addPossibleCard(t_,1,CardTarot.TRUMP_15);
        addSureCard(t_,1,CardTarot.TRUMP_15);
        addPossibleCard(t_,0,CardTarot.TRUMP_13);
        addSureCard(t_,0,CardTarot.TRUMP_13);
        addPossibleCard(t_,0,CardTarot.TRUMP_14);
        addSureCard(t_,0,CardTarot.TRUMP_14);
        assertSame(PossibleTrickWinner.TEAM,GameTarotTrickHypothesis.equipeQuiVaFairePli(t_));
    }
    @Test
    public void equipeQuiVaFairePli2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_9);
        playable_.ajouter(CardTarot.DIAMOND_KING);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.DIAMOND_1);
        current_.ajouter(CardTarot.DIAMOND_8);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_3);
        addSureCard(t_,1,CardTarot.TRUMP_3);
        addPossibleCard(t_,0,CardTarot.TRUMP_4);
        addPossibleCard(t_,4,CardTarot.DIAMOND_9);
        addSureCard(t_,4,CardTarot.DIAMOND_9);
        addPossibleCard(t_,4,CardTarot.DIAMOND_KING);
        addSureCard(t_,4,CardTarot.DIAMOND_KING);
        assertSame(PossibleTrickWinner.UNKNOWN,GameTarotTrickHypothesis.equipeQuiVaFairePli(t_));
    }
    @Test
    public void equipeQuiVaFairePli3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_6);
        HandTarot playable_ = new HandTarot();
        playable_.ajouter(CardTarot.HEART_QUEEN);
        playable_.ajouter(CardTarot.DIAMOND_6);
        TrickTarot current_ = new TrickTarot((byte)2,true);
        current_.ajouter(CardTarot.TRUMP_14);
        current_.ajouter(CardTarot.TRUMP_12);
        HandTarot played_ = new HandTarot();
        Bytes team_ = new Bytes();
        team_.add((byte) 3);
        team_.add((byte) 0);
        TarotInfoPliEnCours t_ = initInformations(hand_,playable_,current_,2,played_,team_,5);
        t_.getCalledSuits().add(Suit.HEART);
        addPossibleCard(t_,1,CardTarot.TRUMP_21);
        addSureCard(t_,1, CardTarot.TRUMP_21);
        assertSame(PossibleTrickWinner.FOE_TEAM,GameTarotTrickHypothesis.equipeQuiVaFairePli(t_));
    }
    static boolean existeJouBatAdvDemat(TarotInfoPliEnCours _t,Bytes _dom,Bytes _beat) {
        EnumMap<Suit, CustList<HandTarot>> poss_ = _t.getCartesPossibles();
        EnumMap<Suit, CustList<HandTarot>> sure_ = _t.getCartesCertaines();
        return GameTarotTrickHypothesis.existeJouBatAdvDemat(_beat,_dom,poss_,sure_);
    }
    static boolean existeJoueurNonJoueBattantAdv(TarotInfoPliEnCours _t,Bytes _dom,Bytes _beat) {
        EnumMap<Suit, CustList<HandTarot>> poss_ = _t.getCartesPossibles();
        EnumMap<Suit, CustList<HandTarot>> sure_ = _t.getCartesCertaines();
        TrickTarot cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        return GameTarotTrickHypothesis.existeJoueurNonJoueBattantAdv(_beat,_dom,suit_,poss_,sure_);
    }
    static boolean ramasseurBatSsCprAdv(TarotInfoPliEnCours _t,Bytes _beat) {
        EnumMap<Suit, CustList<HandTarot>> poss_ = _t.getCartesPossibles();
        EnumMap<Suit, CustList<HandTarot>> sure_ = _t.getCartesCertaines();
        TrickTarot cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        byte nbPlayers_ = _t.getNbPlayers();
        byte strength_ = cur_.carteDuJoueur(cur_.getRamasseur(nbPlayers_),nbPlayers_).strength(suit_);
        return GameTarotTrickHypothesis.ramasseurBatSsCprAdv(_beat,suit_,strength_,poss_,sure_);
    }
    static boolean beatSureListTrumpDemand(TarotInfoPliEnCours _t,Bytes _dom,Bytes _beat) {
        TrickTarot cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        byte nbPlayers_ = _t.getNbPlayers();
        byte strength_ = cur_.carteDuJoueur(cur_.getRamasseur(nbPlayers_),nbPlayers_).strength(suit_);
        return beatSureListTrumpDemand(_t,_dom,_beat, strength_);
    }
    static boolean beatSureListTrumpDemand(TarotInfoPliEnCours _t,Bytes _dom,Bytes _beat,int _str) {
        EnumMap<Suit, CustList<HandTarot>> poss_ = _t.getCartesPossibles();
        EnumMap<Suit, CustList<HandTarot>> sure_ = _t.getCartesCertaines();
        return GameTarotTrickHypothesis.beatSureListTrumpDemand(_beat,_dom,poss_,sure_, (byte) _str);
    }
    static boolean beatSureListTrumpDemandPast(TarotInfoPliEnCours _t,Bytes _beat) {
        TrickTarot cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        byte nbPlayers_ = _t.getNbPlayers();
        byte strength_ = cur_.carteDuJoueur(cur_.getRamasseur(nbPlayers_),nbPlayers_).strength(suit_);
        return beatSureListTrumpDemandPast(_t,_beat, strength_);
    }
    static boolean beatSureListTrumpDemandPast(TarotInfoPliEnCours _t,Bytes _beat,int _str) {
        EnumMap<Suit, CustList<HandTarot>> poss_ = _t.getCartesPossibles();
        return GameTarotTrickHypothesis.beatSureListTrumpDemandPast(_beat,poss_, (byte) _str);
    }
    static boolean beatSureListTrumpNormalSuit(TarotInfoPliEnCours _t, Bytes _dom,Bytes _beat) {
        TrickTarot cur_ = _t.getProgressingTrick();
        Suit suit_ = cur_.couleurDemandee();
        byte nbPlayers_ = _t.getNbPlayers();
        byte strength_ = cur_.carteDuJoueur(cur_.getRamasseur(nbPlayers_),nbPlayers_).strength(suit_);
        return beatSureListTrumpNormalSuit(_t,_dom,_beat, strength_);
    }
    static boolean beatSureListTrumpNormalSuit(TarotInfoPliEnCours _t, Bytes _dom,Bytes _beat,int _str) {
        EnumMap<Suit, CustList<HandTarot>> poss_ = _t.getCartesPossibles();
        EnumMap<Suit, CustList<HandTarot>> sure_ = _t.getCartesCertaines();
        Suit suit_ = _t.getProgressingTrick().couleurDemandee();
        return GameTarotTrickHypothesis.beatSureListTrumpNormalSuit(_beat,_dom,suit_,poss_,sure_, (byte) _str);
    }
    static boolean beatByTrumpNormalSuitStrength(TarotInfoPliEnCours _t, Bytes _beat) {
        byte nbPlayers_ = _t.getNbPlayers();
        TrickTarot cur_ = _t.getProgressingTrick();
        byte strength_ = cur_.carteDuJoueur(cur_.getRamasseur(nbPlayers_),nbPlayers_).strength(cur_.couleurDemandee());
        return beatByTrumpNormalSuitStrength(_t, _beat, strength_);
    }
    static boolean beatByTrumpNormalSuitStrength(TarotInfoPliEnCours _t, Bytes _beat,int _str) {
        EnumMap<Suit, CustList<HandTarot>> poss_ = _t.getCartesPossibles();
        EnumMap<Suit, CustList<HandTarot>> sure_ = _t.getCartesCertaines();
        Suit suit_ = _t.getProgressingTrick().couleurDemandee();
        return GameTarotTrickHypothesis.beatByTrumpNormalSuitStrength(_beat,suit_,poss_,sure_, (byte) _str);
    }
    static boolean beatByTrumpNormalSuit(TarotInfoPliEnCours _t, Bytes _beat,int _pl) {
        EnumMap<Suit, CustList<HandTarot>> poss_ = _t.getCartesPossibles();
        EnumMap<Suit, CustList<HandTarot>> sure_ = _t.getCartesCertaines();
        Suit suit_ = _t.getProgressingTrick().couleurDemandee();
        return GameTarotTrickHypothesis.beatByTrumpNormalSuit(_beat,suit_,poss_,sure_, (byte) _pl);
    }
    static TarotInfoPliEnCours initInformations(
            HandTarot _cartes,
            HandTarot _cartesJouables,
            TrickTarot _current,
            int _taker,
            HandTarot _playedCard,
            Bytes _team, int _nbPlayers) {
        Bytes played_ = _current.joueursAyantJoue((byte) _nbPlayers);
        byte nextPlayer_ = _current.getNextPlayer((byte) _nbPlayers);
        _team.add(nextPlayer_);
        EnumMap<Suit,HandTarot> repartition_ = _cartes.couleurs();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _playedCard.couleurs();
        Bytes notConfident_ = GameTarotTeamsRelation.autresJoueurs(_team, (byte) _nbPlayers);
        Bytes joueursNonJoue_ = GameTarotTeamsRelation.autresJoueurs(played_, (byte) _nbPlayers);
        EnumMap<Suit,CustList<HandTarot>> suitesTouteCouleur_ = _cartes.eclaterToutEnCours(repartitionCartesJouees_);

        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                repartition_, repartitionCartesJouees_);

        TarotInfoPliEnCours info_ = new TarotInfoPliEnCours();
        info_.setCurrentPlayer(nextPlayer_);
        info_.setJoueursNonJoue(joueursNonJoue_);
        info_.setCartesJouables(_cartesJouables);
        info_.setPlisFaits(new CustList<TrickTarot>());
        info_.setCartesJouees(_playedCard);
        info_.setRepartitionCartesJouees(repartitionCartesJouees_);
        info_.setCartesPossibles(generate(_nbPlayers));
        info_.setCartesCertaines(generate(_nbPlayers));
        info_.setRamasseurVirtuel(_current.getRamasseur((byte) _nbPlayers));
        info_.setSuitesTouteCouleur(suitesTouteCouleur_);
        info_.setCouleursMaitresses(new EnumList<Suit>());
        info_.setCartesMaitresses(cartesMaitresses_);
        info_.setCoupesFranches(new EnumList<Suit>());
        info_.setCalledSuits(new EnumList<Suit>());
        info_.setProgressingTrick(_current);
        info_.setNbPlayers((byte) _nbPlayers);
        info_.setTaker((byte) _taker);
        info_.setJoueursConfiance(_team);
        info_.setJoueursNonConfiance(notConfident_);
        return info_;
    }
    private static EnumMap<Suit,CustList<HandTarot>> generate(int _nbPlayer) {
        EnumMap<Suit,CustList<HandTarot>> e_ = new EnumMap<Suit,CustList<HandTarot>>();
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.add(Suit.UNDEFINED);
        s_.add(Suit.TRUMP);
        s_.addAllElts(Suit.couleursOrdinaires());
        for (Suit s: s_) {
            CustList<HandTarot> l_ = new CustList<HandTarot>();
            for (int i = 0; i <= _nbPlayer; i++) {
                l_.add(new HandTarot());
            }
            e_.addEntry(s,l_);
        }
        return e_;
    }
}
