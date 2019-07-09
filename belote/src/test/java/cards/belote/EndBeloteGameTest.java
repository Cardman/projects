package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.consts.EndGameState;
import cards.consts.GameType;
import cards.consts.Suit;
import code.util.Bytes;
import code.util.CustList;
import code.util.Shorts;
import org.junit.Test;

import static cards.belote.EquallableBeloteUtil.assertEq;
import static org.junit.Assert.assertSame;

public final class EndBeloteGameTest {

    @Test
    public void getTeamTrickTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Slam((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_JACK);
        game_.getDistribution().jouer((byte) 1,CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_KING);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_QUEEN);
        game_.getDistribution().jouer((byte) 3,CardBelote.CLUB_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_7);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_9);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_9);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_8);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_7);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_9);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_7);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_9);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_10);
        game_.getDistribution().jouer((byte) 1,CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_QUEEN);
        game_.getDistribution().jouer((byte) 3,CardBelote.DIAMOND_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_JACK);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_7);
        game_.getDistribution().jouer((byte) 3,CardBelote.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_KING);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_10);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_KING);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_JACK);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_10);
        game_.getDistribution().jouer((byte) 2,CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_1);
        game_.getDistribution().jouer((byte)3,CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_QUEEN);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_KING);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_JACK);
        game_.getDistribution().jouer((byte) 2,CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_10);
        game_.getDistribution().jouer((byte) 3,CardBelote.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.setEntameur();
        game_.setPliEnCours();
        byte taker_ = game_.getPreneur();
        Bytes team_ = game_.getTeamsRelation().partenaires(taker_);
        team_.add(taker_);
        CustList<TrickBelote> teamTrick_ = EndBeloteGame.getTeamTrick(team_, game_.getTricks(), game_.getBid());
        assertEq(8, teamTrick_.size());
        assertEq(0, EndBeloteGame.valeurCapot(teamTrick_));
        team_ = game_.getTeamsRelation().adversaires(taker_);
        teamTrick_ = EndBeloteGame.getTeamTrick(team_, game_.getTricks(), game_.getBid());
        assertEq(0, teamTrick_.size());
        assertEq(100, EndBeloteGame.valeurCapot(teamTrick_));
    }

    @Test
    public void pointsTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Slam((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_JACK);
        game_.getDistribution().jouer((byte) 1,CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_KING);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_QUEEN);
        game_.getDistribution().jouer((byte) 3,CardBelote.CLUB_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_7);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_9);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_9);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_8);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_7);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_9);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_7);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_9);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_10);
        game_.getDistribution().jouer((byte) 1,CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_QUEEN);
        game_.getDistribution().jouer((byte) 3,CardBelote.DIAMOND_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_JACK);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_7);
        game_.getDistribution().jouer((byte) 3,CardBelote.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_KING);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_10);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_KING);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_JACK);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_10);
        game_.getDistribution().jouer((byte) 2,CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_1);
        game_.getDistribution().jouer((byte)3,CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_QUEEN);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_KING);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_JACK);
        game_.getDistribution().jouer((byte) 2,CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_10);
        game_.getDistribution().jouer((byte) 3,CardBelote.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.setEntameur();
        game_.setPliEnCours();
        EndBeloteGame end_ = game_.getEndBeloteGame();
        assertEq(262, end_.pointsAttackWithBonus());
        assertEq(0, end_.pointsDefenseWithBonus());
    }
    @Test
    public void getUserState1Test() {
         assertSame(EndGameState.LOOSE, EndBeloteGame.getUserState((byte)0,new Shorts((short)50,(short)112,(short)50,(short)112),new Bytes((byte)1,(byte)3)));
    }
    @Test
    public void getUserState2Test() {
        assertSame(EndGameState.WIN, EndBeloteGame.getUserState((byte)0,new Shorts((short)112,(short)50,(short)112,(short)50),new Bytes((byte)1,(byte)3)));
    }
    @Test
    public void getUserState3Test() {
        assertSame(EndGameState.EQUALLITY, EndBeloteGame.getUserState((byte)0,new Shorts((short)81,(short)81,(short)81,(short)81),new Bytes((byte)1,(byte)3)));
    }
    @Test
    public void scoreDefinitifAttaque1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        assertEq(262, EndBeloteGame.scoreDefinitifAttaque(262,0,r_,new CustList<TrickBelote>(),b_));
    }
    @Test
    public void scoreDefinitifAttaque2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        assertEq(0, EndBeloteGame.scoreDefinitifAttaque(80,82,r_,new CustList<TrickBelote>(),b_));
    }
    @Test
    public void scoreDefinitifAttaque3Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.COINCHE_2_VS_2);
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        b_.setPoints(100);
        assertEq(262, EndBeloteGame.scoreDefinitifAttaque(262,0,r_,new CustList<TrickBelote>(),b_));
    }
    @Test
    public void scoreDefinitifAttaque4Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.COINCHE_2_VS_2);
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        b_.setPoints(100);
        assertEq(0, EndBeloteGame.scoreDefinitifAttaque(90,72,r_,new CustList<TrickBelote>(),b_));
    }
    @Test
    public void scoreDefinitifAttaque5Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.COINCHE_2_VS_2);
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        b_.setPoints(162);
        CustList<TrickBelote> def_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote((byte)1);
        t_.ajouter(CardBelote.HEART_1);
        t_.ajouter(CardBelote.HEART_KING);
        t_.ajouter(CardBelote.HEART_10);
        t_.ajouter(CardBelote.HEART_QUEEN);
        def_.add(t_);
        assertEq(0, EndBeloteGame.scoreDefinitifAttaque(134,28,r_,def_,b_));
    }
    @Test
    public void scoreDefinitifAttaque6Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.COINCHE_2_VS_2);
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        b_.setPoints(162);
        assertEq(262, EndBeloteGame.scoreDefinitifAttaque(262,0,r_,new CustList<TrickBelote>(),b_));
    }
    @Test
    public void scoreDefinitifAttaque7Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setComptePointsClassique(false);
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        assertEq(110, EndBeloteGame.scoreDefinitifAttaque(110,52,r_,new CustList<TrickBelote>(),b_));
    }
    @Test
    public void scoreDefinitifDefense1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        int att_ = EndBeloteGame.scoreDefinitifAttaque(262, 0, r_, new CustList<TrickBelote>(), b_);
        assertEq(0, EndBeloteGame.scoreDefinitifDefense(att_,0,b_));
    }
    @Test
    public void scoreDefinitifDefense2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        int att_ = EndBeloteGame.scoreDefinitifAttaque(80, 82, r_, new CustList<TrickBelote>(), b_);
        assertEq(162, EndBeloteGame.scoreDefinitifDefense(att_,82,b_));
    }
    @Test
    public void scoresTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Slam((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_JACK);
        game_.getDistribution().jouer((byte) 1,CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_KING);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_QUEEN);
        game_.getDistribution().jouer((byte) 3,CardBelote.CLUB_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_7);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_9);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_9);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_8);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_7);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_9);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_7);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_9);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_10);
        game_.getDistribution().jouer((byte) 1,CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_QUEEN);
        game_.getDistribution().jouer((byte) 3,CardBelote.DIAMOND_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_JACK);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_7);
        game_.getDistribution().jouer((byte) 3,CardBelote.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_KING);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_10);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_KING);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_JACK);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_10);
        game_.getDistribution().jouer((byte) 2,CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_1);
        game_.getDistribution().jouer((byte)3,CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_QUEEN);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_KING);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_JACK);
        game_.getDistribution().jouer((byte) 2,CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_10);
        game_.getDistribution().jouer((byte) 3,CardBelote.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.setEntameur();
        game_.setPliEnCours();
        EndBeloteGame end_ = game_.getEndBeloteGame();
        int pointsAttaqueTemporaire_ = end_.pointsAttackWithBonus();
        int pointsDefenseTemporaire_ = end_.pointsDefenseWithBonus();
        int pointsAttaqueDefinitif_ = end_.scoreDefinitifAttaque(pointsAttaqueTemporaire_, pointsDefenseTemporaire_);
        int pointsDefenseDefinitif_ = end_.scoreDefinitifDefense(pointsAttaqueDefinitif_, pointsDefenseTemporaire_);
        Shorts scores_ = end_.scores(pointsAttaqueDefinitif_, pointsDefenseDefinitif_);
        assertEq(4,scores_.size());
        assertEq(262,scores_.get(0));
        assertEq(0,scores_.get(1));
        assertEq(262,scores_.get(2));
        assertEq(0,scores_.get(3));
    }
    private static DealBelote deal1Slam(byte _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.HEART_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }
}
