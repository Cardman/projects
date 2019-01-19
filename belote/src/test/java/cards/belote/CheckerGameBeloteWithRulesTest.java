package cards.belote;
import static cards.belote.EquallableBeloteUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.GameType;
import cards.consts.Suit;
import code.util.EqList;


public class CheckerGameBeloteWithRulesTest {

    @Test
    public void check1Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(-1, game_.getPreneur());
        assertEq(BidBelote.FOLD, game_.getContrat().getEnchere());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check2Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(-1, game_.getPreneur());
        assertEq(BidBelote.FOLD, game_.getContrat().getEnchere());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check3Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(-1, game_.getPreneur());
        assertEq(BidBelote.FOLD, game_.getContrat().getEnchere());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check4Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(-1, game_.getPreneur());
        assertEq(BidBelote.FOLD, game_.getContrat().getEnchere());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    private static DealBelote deal1Classic(byte _dealer) {
        EqList<HandBelote> hands_ = new EqList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.DIAMOND_9);
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
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }

    @Test
    public void check5Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(0, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check6Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.OTHER_SUIT);
        bid_.setCouleur(Suit.CLUB);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.OTHER_SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.CLUB, game_.getContrat().getCouleur());
        assertEq(0, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check7Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(80, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check8Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(90);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(2, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(90, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check9Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(90);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(2, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(90, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check10Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(90);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(2, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(90, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check11Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getEncheresAutorisees().put(BidBelote.NO_TRUMP, true);
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setCouleur(Suit.SPADE);
        bid_.setEnchere(BidBelote.SUIT);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.NO_TRUMP);
        bid_.setCouleur(Suit.UNDEFINED);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.NO_TRUMP, game_.getContrat().getEnchere());
        assertEq(Suit.UNDEFINED, game_.getContrat().getCouleur());
        assertEq(0, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check12Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getEncheresAutorisees().put(BidBelote.NO_TRUMP, true);
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setCouleur(Suit.SPADE);
        bid_.setEnchere(BidBelote.SUIT);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.NO_TRUMP);
        bid_.setCouleur(Suit.UNDEFINED);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.NO_TRUMP, game_.getContrat().getEnchere());
        assertEq(Suit.UNDEFINED, game_.getContrat().getCouleur());
        assertEq(0, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check13Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getEncheresAutorisees().put(BidBelote.NO_TRUMP, true);
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setCouleur(Suit.SPADE);
        bid_.setEnchere(BidBelote.SUIT);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.NO_TRUMP);
        bid_.setCouleur(Suit.UNDEFINED);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.NO_TRUMP, game_.getContrat().getEnchere());
        assertEq(Suit.UNDEFINED, game_.getContrat().getCouleur());
        assertEq(0, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check14Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getEncheresAutorisees().put(BidBelote.NO_TRUMP, true);
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setCouleur(Suit.SPADE);
        bid_.setEnchere(BidBelote.SUIT);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.NO_TRUMP);
        bid_.setCouleur(Suit.UNDEFINED);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.NO_TRUMP, game_.getContrat().getEnchere());
        assertEq(Suit.UNDEFINED, game_.getContrat().getCouleur());
        assertEq(0, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check15Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(0, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    private static DealBelote deal1(byte _dealer) {
        EqList<HandBelote> hands_ = new EqList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.HEART_8);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.HEART_10);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_7);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }

    @Test
    public void check16Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(80, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check17Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_1);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(80, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check18Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_1);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(0, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check19Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_8);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(0, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check20Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.HEART_KING);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(80, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    private static DealBelote deal2Classic(byte _dealer) {
        EqList<HandBelote> hands_ = new EqList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.SPADE_7);
        hand_.ajouter(CardBelote.HEART_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.SPADE_1);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }

    @Test
    public void check21Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(0, game_.getContrat().getPoints());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check22Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(0, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check24Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(0, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check25Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_1);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(0, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check26Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(162);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setEntameur(game_.getPreneur());
        game_.setPliEnCours();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(2, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(162, game_.getContrat().getPoints());
        assertEq(2, game_.getEntameur());
        assertEq(2, game_.getRamasseur());
    }

    @Test
    public void check27Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(162);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setEntameur(game_.getPreneur());
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_7);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(2, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(162, game_.getContrat().getPoints());
        assertEq(2, game_.getEntameur());
        assertEq(2, game_.getRamasseur());
    }

    @Test
    public void check28Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAnnoncesAutorisees().put(DeclaresBelote.THIRTY, true);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.annoncer((byte) 0);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(0, game_.getContrat().getPoints());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check29Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAnnoncesAutorisees().put(DeclaresBelote.THIRTY, true);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(0, game_.getContrat().getPoints());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check30Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAnnoncesAutorisees().put(DeclaresBelote.THIRTY, true);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getContrat().getEnchere());
        assertEq(Suit.SPADE, game_.getContrat().getCouleur());
        assertEq(0, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check31Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        rules_.getEncheresAutorisees().put(BidBelote.ALL_TRUMP, true);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.ALL_TRUMP);
        bid_.setCouleur(Suit.UNDEFINED);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.ALL_TRUMP, game_.getContrat().getEnchere());
        assertEq(Suit.UNDEFINED, game_.getContrat().getCouleur());
        assertEq(80, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check32Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(-1, game_.getPreneur());
        assertEq(BidBelote.FOLD, game_.getContrat().getEnchere());
        assertEq(Suit.UNDEFINED, game_.getContrat().getCouleur());
        assertEq(0, game_.getContrat().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check1FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.HEART);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check2FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(70);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check3FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.OTHER_SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check4FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(90);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check5FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(90);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check6FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check7FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getEncheresAutorisees().put(BidBelote.NO_TRUMP, true);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.NO_TRUMP);
        bid_.setCouleur(Suit.UNDEFINED);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check8FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getEncheresAutorisees().put(BidBelote.ALL_TRUMP, true);
        rules_.getEncheresAutorisees().put(BidBelote.NO_TRUMP, true);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.NO_TRUMP);
        bid_.setCouleur(Suit.UNDEFINED);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }


    @Test
    public void check9FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getEncheresAutorisees().put(BidBelote.NO_TRUMP, true);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.NO_TRUMP);
        bid_.setCouleur(Suit.UNDEFINED);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.OTHER_SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check10FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getEncheresAutorisees().put(BidBelote.ALL_TRUMP, true);
        rules_.getEncheresAutorisees().put(BidBelote.NO_TRUMP, true);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.NO_TRUMP);
        bid_.setCouleur(Suit.UNDEFINED);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.OTHER_SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check11FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_7);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check12FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_1);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check13FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.HEART_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_10);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check14FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.NO_TRUMP);
        bid_.setCouleur(Suit.UNDEFINED);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check15FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_8);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check16FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAnnoncesAutorisees().put(DeclaresBelote.THIRTY, true);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.annoncer((byte) 0);
        game_.getAnnonce((byte) 0).getMain().supprimerCartes();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check17FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.annoncer((byte) 0);
        game_.getAnnonce((byte) 0).setAnnonce(DeclaresBelote.THIRTY);
        game_.getAnnonce((byte) 0).getMain().ajouter(CardBelote.DIAMOND_10);
        game_.getAnnonce((byte) 0).getMain().ajouter(CardBelote.DIAMOND_9);
        game_.getAnnonce((byte) 0).getMain().ajouter(CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check18FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.getAnnoncesBeloteRebelote((byte) 1).jouer(CardBelote.SPADE_KING);
        game_.getAnnoncesBeloteRebelote((byte) 1).ajouter(CardBelote.SPADE_QUEEN);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check19FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.getAnnoncesBeloteRebelote((byte) 1).jouer(CardBelote.SPADE_KING);
        game_.getAnnoncesBeloteRebelote((byte) 0).ajouter(CardBelote.SPADE_KING);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check20FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.getPliEnCours().setEntameur(1);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check21FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAnnoncesAutorisees().put(DeclaresBelote.THIRTY, true);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.getPliEnCours().setEntameur(2);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check22FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAnnoncesAutorisees().put(DeclaresBelote.THIRTY, true);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.getPliEnCours().setEntameur(2);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check23FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.ALL_TRUMP);
        bid_.setCouleur(Suit.UNDEFINED);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check24FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.getDistribution().getDonne().add(new HandBelote());
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check25FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        HandBelote hand_ = game_.getDistribution().derniereMain();
        hand_.ajouter(game_.getDistribution().main().jouer(0));
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check26FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAnnoncesAutorisees().put(DeclaresBelote.THIRTY, true);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check27FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAnnoncesAutorisees().put(DeclaresBelote.THIRTY, true);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_9);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check28FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAnnoncesAutorisees().put(DeclaresBelote.THIRTY, true);
        DealBelote deal_ = deal2Classic((byte) 3);
        deal_.main().jouer(0);
        deal_.main().ajouter(CardBelote.HEART_1);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check29FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAnnoncesAutorisees().put(DeclaresBelote.THIRTY, true);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(-10);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check30FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAnnoncesAutorisees().put(DeclaresBelote.THIRTY, true);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.OTHER_SUIT);
        bid_.setCouleur(Suit.DIAMOND);
        bid_.setPoints(-10);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check31FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setRepartition(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_, new DisplayingBelote());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(90);
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        bid_.setPoints(100);
        game_.ajouterContrat(bid_, (byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }
}
