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

public final class EndBeloteGameTest extends EquallableBeloteUtil {

    @Test
    public void getTeamTrickTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Slam((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
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
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        EndBeloteGame end_ = game_.getEndBeloteGame();
        assertEq(262, end_.pointsAttackWithBonus());
        assertEq(0, end_.pointsDefenseWithBonus());
    }
    @Test
    public void getUserState1Test() {
         assertSame(EndGameState.LOOSE, EndBeloteGame.getUserState((byte)0,Shorts.newList((short)50,(short)112,(short)50,(short)112),Bytes.newList((byte)1,(byte)3)));
    }
    @Test
    public void getUserState2Test() {
        assertSame(EndGameState.WIN, EndBeloteGame.getUserState((byte)0,Shorts.newList((short)112,(short)50,(short)112,(short)50),Bytes.newList((byte)1,(byte)3)));
    }
    @Test
    public void getUserState3Test() {
        assertSame(EndGameState.EQUALLITY, EndBeloteGame.getUserState((byte)0,Shorts.newList((short)81,(short)81,(short)81,(short)81),Bytes.newList((byte)1,(byte)3)));
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
        assertEq(0, EndBeloteGame.scoreDefinitifDefense(att_,0));
    }
    @Test
    public void scoreDefinitifDefense2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        int att_ = EndBeloteGame.scoreDefinitifAttaque(80, 82, r_, new CustList<TrickBelote>(), b_);
        assertEq(162, EndBeloteGame.scoreDefinitifDefense(att_,82));
    }
    @Test
    public void scoresTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Slam((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        EndBeloteGame end_ = game_.getEndBeloteGame();
        int pointsAttaqueTemporaire_ = end_.pointsAttackWithBonus();
        int pointsDefenseTemporaire_ = end_.pointsDefenseWithBonus();
        int pointsAttaqueDefinitif_ = end_.scoreDefinitifAttaque(pointsAttaqueTemporaire_, pointsDefenseTemporaire_);
        int pointsDefenseDefinitif_ = EndBeloteGame.scoreDefinitifDefense(pointsAttaqueDefinitif_, pointsDefenseTemporaire_);
        Shorts scores_ = end_.scores(pointsAttaqueDefinitif_, pointsDefenseDefinitif_);
        assertEq(4,scores_.size());
        assertEq(262,scores_.get(0));
        assertEq(0,scores_.get(1));
        assertEq(262,scores_.get(2));
        assertEq(0,scores_.get(3));
    }

    @Test
    public void initPartie1Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Slam((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        HandBelote one_ = new HandBelote(game_.getDeal().hand((byte) 0));
        HandBelote two_ = new HandBelote(game_.getDeal().hand((byte) 1));
        HandBelote three_ = new HandBelote(game_.getDeal().hand((byte) 2));
        HandBelote four_ = new HandBelote(game_.getDeal().hand((byte) 3));
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        HandBelote stack_ = game_.empiler();
        game_.restituerMainsDepartRejouerDonne();
        assertEq(32, stack_.total());
        assertEq(5, game_.getDeal().nombreDeMains());
        assertEq(5, game_.getDeal().hand((byte) 0).total());
        assertEq(5, game_.getDeal().hand((byte) 1).total());
        assertEq(5, game_.getDeal().hand((byte) 2).total());
        assertEq(5, game_.getDeal().hand((byte) 3).total());
        assertEq(12, game_.getDeal().hand((byte) 4).total());
        assertTrue(HandBelote.equalsSet(one_,game_.getDeal().hand((byte) 0)));
        assertTrue(HandBelote.equalsSet(two_,game_.getDeal().hand((byte) 1)));
        assertTrue(HandBelote.equalsSet(three_,game_.getDeal().hand((byte) 2)));
        assertTrue(HandBelote.equalsSet(four_,game_.getDeal().hand((byte) 3)));
    }
    @Test
    public void initPartie2Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Slam((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        HandBelote one_ = new HandBelote(game_.getDeal().hand((byte) 0));
        HandBelote two_ = new HandBelote(game_.getDeal().hand((byte) 1));
        HandBelote three_ = new HandBelote(game_.getDeal().hand((byte) 2));
        HandBelote four_ = new HandBelote(game_.getDeal().hand((byte) 3));
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
//        game_.finEncherePremierTour();
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        new DefGameBelote().empiler(1, game_,null);
        HandBelote stack_ = game_.empiler();
        game_.restituerMainsDepartRejouerDonne();
        assertEq(32, stack_.total());
        assertEq(5, game_.getDeal().nombreDeMains());
        assertEq(5, game_.getDeal().hand((byte) 0).total());
        assertEq(5, game_.getDeal().hand((byte) 1).total());
        assertEq(5, game_.getDeal().hand((byte) 2).total());
        assertEq(5, game_.getDeal().hand((byte) 3).total());
        assertEq(12, game_.getDeal().hand((byte) 4).total());
        assertTrue(HandBelote.equalsSet(one_,game_.getDeal().hand((byte) 0)));
        assertTrue(HandBelote.equalsSet(two_,game_.getDeal().hand((byte) 1)));
        assertTrue(HandBelote.equalsSet(three_,game_.getDeal().hand((byte) 2)));
        assertTrue(HandBelote.equalsSet(four_,game_.getDeal().hand((byte) 3)));
    }

    @Test
    public void initPartie3Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        HandBelote one_ = new HandBelote(game_.getDeal().hand((byte) 0));
        HandBelote two_ = new HandBelote(game_.getDeal().hand((byte) 1));
        HandBelote three_ = new HandBelote(game_.getDeal().hand((byte) 2));
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_8);
        game_.ajouterChelemUtilisateur();
        game_.validateDiscard();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        HandBelote stack_ = game_.empiler();
        game_.restituerMainsDepartRejouerDonne();
        assertEq(32, stack_.total());
        assertEq(4, game_.getDeal().nombreDeMains());
        assertEq(8, game_.getDeal().hand((byte) 0).total());
        assertEq(8, game_.getDeal().hand((byte) 1).total());
        assertEq(8, game_.getDeal().hand((byte) 2).total());
        assertEq(8, game_.getDeal().hand((byte) 3).total());
        assertTrue(HandBelote.equalsSet(one_,game_.getDeal().hand((byte) 0)));
        assertTrue(HandBelote.equalsSet(two_,game_.getDeal().hand((byte) 1)));
        assertTrue(HandBelote.equalsSet(three_,game_.getDeal().hand((byte) 2)));
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

    private static BidBeloteSuit bidSuit(Suit _suit, int _pts, BidBelote _bid) {
        BidBeloteSuit suit_ = new BidBeloteSuit();
        suit_.setSuit(_suit);
        suit_.setPoints(_pts);
        suit_.setBid(_bid);
        return suit_;
    }
    private static DealBelote dealThreePlayers() {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 1);
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,
                CardBelote.DIAMOND_10,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8,CardBelote.HEART_10));
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.CLUB_10,CardBelote.SPADE_9,CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_9));
        db_.getDeal().add(create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8,
                CardBelote.CLUB_1,CardBelote.HEART_1,CardBelote.DIAMOND_1,CardBelote.SPADE_1));
        return db_;
    }
}
