package cards.tarot;

import cards.tarot.enumerations.ModeTarot;
import code.util.CustList;
import org.junit.Test;

import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import code.util.EnumMap;

public class GameTarotPlayingOneTest extends CommonTarotGame {
    private GameTarot game;

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
        regles_.setRepartition(DealingTarot.DEAL_2_VS_3_CALL_KING);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        EnumMap<BidTarot,Boolean> contrats_ = new EnumMap<BidTarot,Boolean>();
        for (BidTarot b: regles_.getContrats().getKeys()) {
            contrats_.put(b,true);
        }
        regles_.setContrats(contrats_);
        return regles_;
    }
    static RulesTarot initializeRulesWithBidsVariant() {
        RulesTarot regles_=new RulesTarot();
        regles_.setRepartition(DealingTarot.DEAL_2_VS_3_CALL_CHAR);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        EnumMap<BidTarot,Boolean> contrats_ = new EnumMap<BidTarot,Boolean>();
        for (BidTarot b: regles_.getContrats().getKeys()) {
            contrats_.put(b,true);
        }
        regles_.setContrats(contrats_);
        return regles_;
    }
    @Test
    public void playableCards_beginningTrickFree1Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 4),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_QUEEN);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.TRUMP_6);
        discardedCards_.ajouter(CardTarot.TRUMP_2);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game.supprimerCartes(game.getPreneur(),discardedCards_);

        game.setPliEnCours(false);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game.addCurTrick();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        assertEq(0,game.getEntameur());
        HandTarot hand_ = game.getDistribution().hand(game.getEntameur());
        EnumMap<Suit,HandTarot> suits_ = hand_.couleurs();
        HandTarot playableCards_ = game.playableCards(suits_);
        assertEq(hand_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(hand_));
        assertEq(Suit.UNDEFINED,game.getPliEnCours().couleurDemandee());
        assertEq(0,game.getAnnoncesPoigneesPossibles((byte) 0).size());
    }
    @Test
    public void ajouterUtilisateurTest() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 4),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 0, game);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_KING);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        game.ajouterUneCarteDansPliEnCours(game.getPreneur(),CardTarot.DIAMOND_10);
        game.retirerUneCarteDuChien(CardTarot.DIAMOND_10);
        game.ajouterUtilisateur(CardTarot.DIAMOND_10);
        assertEq(0,game.getProgressingTrick().total());
    }
    @Test
    public void playableCards_beginningTrickWithConstraint2Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 3),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.TRUMP_6);
        discardedCards_.ajouter(CardTarot.TRUMP_2);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game.supprimerCartes(game.getPreneur(),discardedCards_);

        game.setPliEnCours(false);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game.addCurTrick();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        assertEq(4,game.getEntameur());
        HandTarot hand_ = game.getDistribution().hand(game.getEntameur());
        HandTarot playableCards_ = game.playableCards(hand_.couleurs());
        assertEq(hand_.total(),playableCards_.total());
        assertEq(Suit.UNDEFINED,game.getPliEnCours().couleurDemandee());
    }
    @Test
    public void playableCards_beginningTrickWithConstraintOnExcuse3Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 3),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.TRUMP_6);
        discardedCards_.ajouter(CardTarot.TRUMP_2);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game.supprimerCartes(game.getPreneur(),discardedCards_);

        game.setPliEnCours(false);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game.addCurTrick();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        game.jouer(game.getPreneur(), CardTarot.EXCUSE);
        game.ajouterUneCarteDansPliEnCours(CardTarot.EXCUSE);

        assertEq(4,game.getEntameur());

        HandTarot hand_ = game.getDistribution().hand(game.playerAfter(game.getEntameur()));
        HandTarot playableCards_ = game.playableCards(hand_.couleurs());
        assertEq(hand_.total(),playableCards_.total());
        assertEq(Suit.UNDEFINED,game.getPliEnCours().couleurDemandee());
    }
    @Test
    public void playableCards_beginningTrickWithConstraintBeingCalledPlayer4Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 0),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_QUEEN);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.TRUMP_6);
        discardedCards_.ajouter(CardTarot.TRUMP_2);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game.supprimerCartes(game.getPreneur(),discardedCards_);

        game.setPliEnCours(false);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game.addCurTrick();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        assertEq(1,game.getEntameur());

        HandTarot hand_ = game.getDistribution().hand(game.getEntameur());
        HandTarot playableCards_ = game.playableCards(hand_.couleurs());
        assertEq(hand_.total(),playableCards_.total());
        assertEq(Suit.UNDEFINED,game.getPliEnCours().couleurDemandee());
    }
    @Test
    public void playableCards_beginningTrickWithConstraintOnExcuse4Test() {
        RulesTarot regles_=initializeRulesWithBidsVariant();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 3),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.TRUMP_21);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.TRUMP_6);
        discardedCards_.ajouter(CardTarot.TRUMP_2);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game.supprimerCartes(game.getPreneur(),discardedCards_);

        game.setPliEnCours(false);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game.addCurTrick();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        game.jouer(game.getPreneur(), CardTarot.EXCUSE);
        game.ajouterUneCarteDansPliEnCours(CardTarot.EXCUSE);

        assertEq(4,game.getEntameur());

        HandTarot hand_ = game.getDistribution().hand(game.playerAfter(game.getEntameur()));
        HandTarot playableCards_ = game.playableCards(hand_.couleurs());
        assertEq(hand_.total(),playableCards_.total());
        assertEq(Suit.UNDEFINED,game.getPliEnCours().couleurDemandee());
    }
    @Test
    public void playableCards_followingCardPlainSuit5Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 2),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_QUEEN);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.TRUMP_6);
        discardedCards_.ajouter(CardTarot.TRUMP_2);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game.supprimerCartes(game.getPreneur(),discardedCards_);

        game.setPliEnCours(false);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game.addCurTrick();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        game.jouer(game.getEntameur(),CardTarot.DIAMOND_3);
        game.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        assertEq(Suit.DIAMOND,game.getPliEnCours().couleurDemandee());
        byte player_ = game.playerAfter(game.getEntameur());
        HandTarot hand_ = game.getDistribution().hand(player_);
        EnumMap<Suit,HandTarot> suits_ = hand_.couleurs();
        HandTarot playableCards_ = game.playableCards(suits_);
        HandTarot expected_ = suits_.getVal(game.getPliEnCours().couleurDemandee());
        expected_.ajouter(CardTarot.EXCUSE);
        assertEq(expected_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(expected_));
    }
    @Test
    public void playableCards_trumpingTrickFirstTime6Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 0),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_QUEEN);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.TRUMP_6);
        discardedCards_.ajouter(CardTarot.TRUMP_2);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game.supprimerCartes(game.getPreneur(),discardedCards_);

        game.setPliEnCours(false);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game.addCurTrick();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        game.jouer(game.getEntameur(),CardTarot.DIAMOND_7);
        game.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_7);
        assertEq(Suit.DIAMOND,game.getPliEnCours().couleurDemandee());
        byte player_ = game.playerAfter(game.getEntameur());
        HandTarot hand_ = game.getDistribution().hand(player_);
        EnumMap<Suit,HandTarot> suits_ = hand_.couleurs();
        HandTarot playableCards_ = game.playableCards(suits_);
        HandTarot expected_ = suits_.getVal(Suit.TRUMP);
        assertEq(expected_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(expected_));
    }
    @Test
    public void strategieJeuCarteUnique1Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 0),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.TRUMP_6);
        discardedCards_.ajouter(CardTarot.TRUMP_2);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game.supprimerCartes(game.getPreneur(),discardedCards_);

        game.setPliEnCours(false);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game.addCurTrick();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        CardTarot cardTarot_ = game.strategieJeuCarteUnique();
        assertSame(CardTarot.DIAMOND_7,cardTarot_);
    }
    @Test
    public void strategieJeuCarteUnique2Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 0),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.TRUMP_6);
        discardedCards_.ajouter(CardTarot.TRUMP_2);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game.supprimerCartes(game.getPreneur(),discardedCards_);

        game.setPliEnCours(false);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game.addCurTrick();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        game.jouer(game.getEntameur(),CardTarot.DIAMOND_7);
        game.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_7);
        CardTarot cardTarot_ = game.strategieJeuCarteUnique();
        assertSame(CardTarot.TRUMP_5,cardTarot_);
    }
    @Test
    public void strategieJeuCarteUnique3Test() {
        RulesTarot regles_=initializeRulesWithoutBids(ModeTarot.ONE_FOR_ONE);
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 0),regles_);
        //game.resetNbPlisTotal();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        CardTarot cardTarot_ = game.strategieJeuCarteUnique();
        assertSame(CardTarot.HEART_1,cardTarot_);
    }
    @Test
    public void strategieJeuCarteUnique4Test() {
        RulesTarot regles_=initializeRulesWithoutBids(ModeTarot.ONE_FOR_ONE);
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 0),regles_);
        //game.resetNbPlisTotal();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        game.jouer(game.getEntameur(),CardTarot.HEART_1);
        game.ajouterUneCarteDansPliEnCours(CardTarot.HEART_1);
        CardTarot cardTarot_ = game.strategieJeuCarteUnique();
        assertSame(CardTarot.HEART_9,cardTarot_);
    }
    @Test
    public void strategieJeuCarteUnique5Test() {
        RulesTarot regles_=initializeRulesWithoutBids(ModeTarot.MISERE);
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 0),regles_);
        //game.resetNbPlisTotal();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        CardTarot cardTarot_ = game.strategieJeuCarteUnique();
        assertSame(CardTarot.TRUMP_7,cardTarot_);
    }
    @Test
    public void strategieJeuCarteUnique6Test() {
        RulesTarot regles_=initializeRulesWithoutBids(ModeTarot.MISERE);
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 0),regles_);
        //game.resetNbPlisTotal();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        game.jouer(game.getEntameur(),CardTarot.TRUMP_7);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_7);
        CardTarot cardTarot_ = game.strategieJeuCarteUnique();
        assertSame(CardTarot.TRUMP_5,cardTarot_);
    }
    @Test
    public void changerConfiance1Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 0),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.TRUMP_6);
        discardedCards_.ajouter(CardTarot.TRUMP_2);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game.supprimerCartes(game.getPreneur(),discardedCards_);

        game.setPliEnCours(false);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game.addCurTrick();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        game.changerConfiance();
        assertTrue(game.confiance((byte)1,(byte)1));
        assertTrue(!game.confiance((byte)1,(byte)0));
        assertTrue(!game.confiance((byte)1,(byte)2));
        assertTrue(!game.confiance((byte)1,(byte)3));
        assertTrue(!game.confiance((byte)1,(byte)4));
    }
    @Test
    public void changerConfiance2Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 0),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.TRUMP_6);
        discardedCards_.ajouter(CardTarot.TRUMP_2);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game.supprimerCartes(game.getPreneur(),discardedCards_);

        game.setPliEnCours(false);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game.addCurTrick();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.TRUMP_7);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.TRUMP_5);
        game.changerConfiance();
        assertTrue(game.confiance((byte)3,(byte)3));
        assertTrue(!game.confiance((byte)3,(byte)0));
        assertTrue(!game.confiance((byte)3,(byte)2));
        assertTrue(!game.confiance((byte)3,(byte)1));
        assertTrue(game.confiance((byte)3,(byte)4));
    }
    @Test
    public void changerConfiance3Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 0),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.TRUMP_6);
        discardedCards_.ajouter(CardTarot.TRUMP_2);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game.supprimerCartes(game.getPreneur(),discardedCards_);

        game.setPliEnCours(false);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game.addCurTrick();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.TRUMP_7);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.TRUMP_5);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.SPADE_QUEEN);
        game.changerConfiance();
        assertTrue(game.confiance((byte)4,(byte)4));
        assertTrue(!game.confiance((byte)4,(byte)0));
        assertTrue(!game.confiance((byte)4,(byte)2));
        assertTrue(!game.confiance((byte)4,(byte)1));
        assertTrue(game.confiance((byte)4,(byte)3));
    }
    @Test
    public void changerConfiance4Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 0),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.TRUMP_6);
        discardedCards_.ajouter(CardTarot.TRUMP_2);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game.supprimerCartes(game.getPreneur(),discardedCards_);

        game.setPliEnCours(false);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game.addCurTrick();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.TRUMP_7);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.TRUMP_5);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.SPADE_QUEEN);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.TRUMP_21);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.TRUMP_16);
        game.ajouterPliEnCours();
        game.setPliEnCours(true);
        game.changerConfiance();
        assertTrue(game.confiance((byte)4,(byte)4));
        assertTrue(!game.confiance((byte)4,(byte)0));
        assertTrue(!game.confiance((byte)4,(byte)2));
        assertTrue(!game.confiance((byte)4,(byte)1));
        assertTrue(game.confiance((byte)4,(byte)3));
    }
    @Test
    public void changerConfianceJeuCarteUnique1Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 0),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.TRUMP_6);
        discardedCards_.ajouter(CardTarot.TRUMP_2);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game.supprimerCartes(game.getPreneur(),discardedCards_);

        game.setPliEnCours(false);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game.addCurTrick();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        game.changerConfianceJeuCarteUnique();
        CardTarot cardTarot_ = game.getCarteJoueee();
        assertSame(CardTarot.DIAMOND_7,cardTarot_);
    }
    @Test
    public void changerConfianceJeuCarteUnique2Test() {
        RulesTarot regles_=initializeRulesWithoutBids(ModeTarot.MISERE);
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 0),regles_);
        //game.resetNbPlisTotal();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        game.changerConfianceJeuCarteUnique();
        CardTarot cardTarot_ = game.getCarteJoueee();
        assertSame(CardTarot.TRUMP_7,cardTarot_);
    }
    @Test
    public void changerConfianceJeuCarteUnique3Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 0),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.TRUMP_6);
        discardedCards_.ajouter(CardTarot.TRUMP_2);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game.supprimerCartes(game.getPreneur(),discardedCards_);

        game.setPliEnCours(false);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game.addCurTrick();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.TRUMP_7);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.TRUMP_5);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.SPADE_QUEEN);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.TRUMP_21);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.TRUMP_16);
        game.ajouterPliEnCours();
        game.setPliEnCours(true);
        game.changerConfianceJeuCarteUnique();
        CardTarot cardTarot_ = game.getCarteJoueee();
        assertSame(CardTarot.TRUMP_20,cardTarot_);
    }
    @Test
    public void currentPlayerHasPlayedTest() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 0),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_QUEEN);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.TRUMP_6);
        discardedCards_.ajouter(CardTarot.TRUMP_2);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game.supprimerCartes(game.getPreneur(),discardedCards_);

        game.setPliEnCours(false);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game.addCurTrick();
        game.setEntameur(game.playerAfter(game.getDistribution().getDealer()));
        game.setPliEnCours(true);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.TRUMP_7);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.TRUMP_5);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.SPADE_QUEEN);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.TRUMP_21);
        game.ajouterUneCarteDansPliEnCours(game.playerHavingToPlay(),CardTarot.TRUMP_16);
        game.ajouterPliEnCours();
        game.setPliEnCours(true);
        assertTrue(!game.currentPlayerHasPlayed(game.getEntameur()));
        assertTrue(game.currentPlayerHasPlayed(game.getEntameur()));
    }
    static RulesTarot initializeRulesWithoutBids(ModeTarot _mode) {
        RulesTarot regles_=new RulesTarot();
        regles_.setRepartition(DealingTarot.DEAL_2_VS_3_CALL_KING);
        regles_.setMode(_mode);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        EnumMap<BidTarot,Boolean> contrats_ = new EnumMap<BidTarot,Boolean>();
        for (BidTarot b: regles_.getContrats().getKeys()) {
            contrats_.put(b,true);
        }
        regles_.setContrats(contrats_);
        return regles_;
    }
}
