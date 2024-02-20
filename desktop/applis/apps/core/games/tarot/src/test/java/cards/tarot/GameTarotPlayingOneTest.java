package cards.tarot;

import cards.tarot.enumerations.ModeTarot;
import code.util.CustList;
import code.util.core.BoolVal;
import org.junit.Test;

import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import code.util.IdMap;

public class GameTarotPlayingOneTest extends CommonTarotGame {

    static DealTarot initializeHands(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_16);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_8);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.CLUB_KING);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.HEART_10);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
    }
    static RulesTarot initializeRulesWithBids() {
        RulesTarot regles_=new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        regles_.allowAllBids();
        return regles_;
    }
    static RulesTarot initializeRulesWithBidsVariant() {
        RulesTarot regles_=new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_CHAR);
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        regles_.allowAllBids();
        return regles_;
    }
    @Test
    public void playableCards_beginningTrickFree1Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 4), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_QUEEN);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        discard(game_);
        firstTrick(game_);
        assertEq(0,game_.getEntameur());
        HandTarot hand_ = game_.getDistribution().hand(game_.getEntameur());
        IdMap<Suit,HandTarot> suits_ = hand_.couleurs();
        HandTarot playableCards_ = game_.playableCards(suits_);
        assertEq(hand_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(hand_));
        assertEq(Suit.UNDEFINED,game_.getPliEnCours().couleurDemandee());
        assertEq(0,game_.getAnnoncesPoigneesPossibles((byte) 0).size());
    }
    @Test
    public void ajouterUtilisateurTest() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 4), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 0, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_KING);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        game_.ajouterCartes(game_.getPreneur(), game_.derniereMain());
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_10);
        game_.retirerUneCarteDuChien(CardTarot.DIAMOND_10);
        game_.ajouterUtilisateur(CardTarot.DIAMOND_10);
        assertEq(0,game_.getProgressingTrick().total());
    }
    @Test
    public void playableCards_beginningTrickWithConstraint2Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 3), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        discard(game_);
        firstTrick(game_);
        assertEq(4,game_.getEntameur());
        HandTarot hand_ = game_.getDistribution().hand(game_.getEntameur());
        HandTarot playableCards_ = game_.playableCards(hand_.couleurs());
        assertEq(hand_.total(),playableCards_.total());
        assertEq(Suit.UNDEFINED,game_.getPliEnCours().couleurDemandee());
    }
    @Test
    public void playableCards_beginningTrickWithConstraintOnExcuse3Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 3), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        discard(game_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.EXCUSE);

        assertEq(4,game_.getEntameur());

        HandTarot hand_ = game_.getDistribution().hand(game_.playerAfter(game_.getEntameur()));
        HandTarot playableCards_ = game_.playableCards(hand_.couleurs());
        assertEq(hand_.total(),playableCards_.total());
        assertEq(Suit.UNDEFINED,game_.getPliEnCours().couleurDemandee());
    }
    @Test
    public void playableCards_beginningTrickWithConstraintBeingCalledPlayer4Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 0), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_QUEEN);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        discard(game_);
        firstTrick(game_);
        assertEq(1,game_.getEntameur());

        HandTarot hand_ = game_.getDistribution().hand(game_.getEntameur());
        HandTarot playableCards_ = game_.playableCards(hand_.couleurs());
        assertEq(hand_.total(),playableCards_.total());
        assertEq(Suit.UNDEFINED,game_.getPliEnCours().couleurDemandee());
    }
    @Test
    public void playableCards_beginningTrickWithConstraintOnExcuse4Test() {
        RulesTarot regles_=initializeRulesWithBidsVariant();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 3), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.TRUMP_21);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        discard(game_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.EXCUSE);

        assertEq(4,game_.getEntameur());

        HandTarot hand_ = game_.getDistribution().hand(game_.playerAfter(game_.getEntameur()));
        HandTarot playableCards_ = game_.playableCards(hand_.couleurs());
        assertEq(hand_.total(),playableCards_.total());
        assertEq(Suit.UNDEFINED,game_.getPliEnCours().couleurDemandee());
    }
    @Test
    public void playableCards_followingCardPlainSuit5Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 2), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_QUEEN);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        discard(game_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        assertEq(Suit.DIAMOND,game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        HandTarot hand_ = game_.getDistribution().hand(player_);
        IdMap<Suit,HandTarot> suits_ = hand_.couleurs();
        HandTarot playableCards_ = game_.playableCards(suits_);
        HandTarot expected_ = suits_.getVal(game_.getPliEnCours().couleurDemandee());
        expected_.ajouter(CardTarot.EXCUSE);
        assertEq(expected_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(expected_));
    }
    @Test
    public void playableCards_trumpingTrickFirstTime6Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 0), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_QUEEN);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        discard(game_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_7);
        assertEq(Suit.DIAMOND,game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        HandTarot hand_ = game_.getDistribution().hand(player_);
        IdMap<Suit,HandTarot> suits_ = hand_.couleurs();
        HandTarot playableCards_ = game_.playableCards(suits_);
        HandTarot expected_ = suits_.getVal(Suit.TRUMP);
        assertEq(expected_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(expected_));
    }
    @Test
    public void strategieJeuCarteUnique1Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 0), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        discard(game_);
        firstTrick(game_);
        CardTarot cardTarot_ = game_.strategieJeuCarteUnique();
        assertSame(CardTarot.DIAMOND_7,cardTarot_);
    }
    @Test
    public void strategieJeuCarteUnique2Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 0), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        discard(game_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_7);
        CardTarot cardTarot_ = game_.strategieJeuCarteUnique();
        assertSame(CardTarot.TRUMP_5,cardTarot_);
    }
    @Test
    public void strategieJeuCarteUnique3Test() {
        RulesTarot regles_=initializeRulesWithoutBids(ModeTarot.ONE_FOR_ONE);
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 0), regles_);
        //game.resetNbPlisTotal();
        firstTrick(game_);
        CardTarot cardTarot_ = game_.strategieJeuCarteUnique();
        assertSame(CardTarot.HEART_1,cardTarot_);
    }
    @Test
    public void strategieJeuCarteUnique4Test() {
        RulesTarot regles_=initializeRulesWithoutBids(ModeTarot.ONE_FOR_ONE);
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 0), regles_);
        //game.resetNbPlisTotal();
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_1);
        CardTarot cardTarot_ = game_.strategieJeuCarteUnique();
        assertSame(CardTarot.HEART_9,cardTarot_);
    }
    @Test
    public void strategieJeuCarteUnique5Test() {
        RulesTarot regles_=initializeRulesWithoutBids(ModeTarot.MISERE);
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 0), regles_);
        //game.resetNbPlisTotal();
        firstTrick(game_);
        CardTarot cardTarot_ = game_.strategieJeuCarteUnique();
        assertSame(CardTarot.TRUMP_7,cardTarot_);
    }
    @Test
    public void strategieJeuCarteUnique6Test() {
        RulesTarot regles_=initializeRulesWithoutBids(ModeTarot.MISERE);
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 0), regles_);
        //game.resetNbPlisTotal();
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_7);
        CardTarot cardTarot_ = game_.strategieJeuCarteUnique();
        assertSame(CardTarot.TRUMP_5,cardTarot_);
    }

    @Test
    public void changerConfiance1Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 0), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        discard(game_);
        firstTrick(game_);
        CustList<CustList<BoolVal>> cf_ = game_.changerConfiance();
        assertSame(BoolVal.TRUE,GameTarot.confiance(cf_,(byte)1,(byte)1));
        assertSame(BoolVal.FALSE,GameTarot.confiance(cf_,(byte)1,(byte)0));
        assertSame(BoolVal.FALSE,GameTarot.confiance(cf_,(byte)1,(byte)2));
        assertSame(BoolVal.FALSE,GameTarot.confiance(cf_,(byte)1,(byte)3));
        assertSame(BoolVal.FALSE,GameTarot.confiance(cf_,(byte)1,(byte)4));
    }
    @Test
    public void changerConfiance2Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 0), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        discard(game_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_7);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_5);
        CustList<CustList<BoolVal>> cf_ = game_.changerConfiance();
        assertSame(BoolVal.TRUE,GameTarot.confiance(cf_,(byte)3,(byte)3));
        assertSame(BoolVal.FALSE,GameTarot.confiance(cf_,(byte)3,(byte)0));
        assertSame(BoolVal.FALSE,GameTarot.confiance(cf_,(byte)3,(byte)2));
        assertSame(BoolVal.FALSE,GameTarot.confiance(cf_,(byte)3,(byte)1));
        assertSame(BoolVal.TRUE,GameTarot.confiance(cf_,(byte)3,(byte)4));
    }
    @Test
    public void changerConfiance3Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 0), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        discard(game_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_7);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_5);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_QUEEN);
        CustList<CustList<BoolVal>> cf_ = game_.changerConfiance();
        assertSame(BoolVal.TRUE,GameTarot.confiance(cf_,(byte)4,(byte)4));
        assertSame(BoolVal.FALSE,GameTarot.confiance(cf_,(byte)4,(byte)0));
        assertSame(BoolVal.FALSE,GameTarot.confiance(cf_,(byte)4,(byte)2));
        assertSame(BoolVal.FALSE,GameTarot.confiance(cf_,(byte)4,(byte)1));
        assertSame(BoolVal.TRUE,GameTarot.confiance(cf_,(byte)4,(byte)3));
    }
    @Test
    public void changerConfiance4Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 0), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        discard(game_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_7);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_5);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_21);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        CustList<CustList<BoolVal>> cf_ = game_.changerConfiance();
        assertSame(BoolVal.TRUE,GameTarot.confiance(cf_,(byte)4,(byte)4));
        assertSame(BoolVal.FALSE,GameTarot.confiance(cf_,(byte)4,(byte)0));
        assertSame(BoolVal.FALSE,GameTarot.confiance(cf_,(byte)4,(byte)2));
        assertSame(BoolVal.FALSE,GameTarot.confiance(cf_,(byte)4,(byte)1));
        assertSame(BoolVal.TRUE,GameTarot.confiance(cf_,(byte)4,(byte)3));
    }
    @Test
    public void changerConfianceJeuCarteUnique1Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 0), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        discard(game_);
        firstTrick(game_);
        CardTarot cardTarot_ = game_.changerConfianceJeuCarteUnique();
        assertSame(CardTarot.DIAMOND_7,cardTarot_);
    }
    @Test
    public void changerConfianceJeuCarteUnique2Test() {
        RulesTarot regles_=initializeRulesWithoutBids(ModeTarot.MISERE);
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 0), regles_);
        //game.resetNbPlisTotal();
        firstTrick(game_);
        CardTarot cardTarot_ = game_.changerConfianceJeuCarteUnique();
        assertSame(CardTarot.TRUMP_7,cardTarot_);
    }
    @Test
    public void changerConfianceJeuCarteUnique3Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 0), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        discard(game_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_7);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_5);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_21);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        CardTarot cardTarot_ = game_.changerConfianceJeuCarteUnique();
        assertSame(CardTarot.TRUMP_20,cardTarot_);
    }
    @Test
    public void changerConfianceJeuCarteUnique4Test() {
        RulesTarot regles_=initializeRulesWithoutBids(ModeTarot.MISERE);
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 0), regles_);
        //game.resetNbPlisTotal();
        firstTrick(game_);
        CardTarot cardTarot_ = new DefGameTarot().changerConfianceJeuCarteUniqueQuick(game_);
        assertSame(CardTarot.TRUMP_7,cardTarot_);
    }
    @Test
    public void currentPlayerHasPlayedTest() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 0), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        discard(game_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_7);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_5);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_21);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        assertTrue(!game_.currentPlayerHasPlayed(game_.getEntameur()));
        assertTrue(game_.currentPlayerHasPlayed(game_.getEntameur()));
    }

    private void discard(GameTarot _game) {
        _game.ajouterCartes(_game.getPreneur(), _game.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.TRUMP_6);
        discardedCards_.ajouter(CardTarot.TRUMP_2);
        discardedCards_.ajouter(CardTarot.HEART_10);
        _game.supprimerCartes(_game.getPreneur(),discardedCards_);

        _game.setPliEnCours(false);
        _game.ajouterUneCarteDansPliEnCoursSimple(CardTarot.TRUMP_6);
        _game.ajouterUneCarteDansPliEnCoursSimple(CardTarot.TRUMP_2);
        _game.ajouterUneCarteDansPliEnCoursSimple(CardTarot.HEART_10);
        _game.addCurTrick();
    }

    private void firstTrick(GameTarot _game) {
        _game.setEntameur(_game.playerAfter(_game.getDistribution().getDealer()));
        _game.setPliEnCours(true);
    }

    static RulesTarot initializeRulesWithoutBids(ModeTarot _mode) {
        RulesTarot regles_=new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        regles_.setMode(_mode);
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        regles_.allowAllBids();
        return regles_;
    }

}
