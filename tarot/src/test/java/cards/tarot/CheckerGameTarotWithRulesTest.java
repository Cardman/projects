package cards.tarot;
import static cards.tarot.EquallableTarotUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cards.consts.GameType;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import cards.tarot.enumerations.ModeTarot;
import code.util.EnumList;
import code.util.EqList;
import code.util.consts.Constants;


public class CheckerGameTarotWithRulesTest {

    @Test
    public void check1Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        DealTarot deal_ = new DealTarot(0, HandTarot.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check2Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = new DealTarot(0, HandTarot.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check3Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        DealTarot deal_ = new DealTarot(0, HandTarot.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check4Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        DealTarot deal_ = new DealTarot(0, HandTarot.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(2, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check5Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        DealTarot deal_ = new DealTarot(0, HandTarot.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(2, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check6Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = new DealTarot(0, HandTarot.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check7Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = new DealTarot(0, HandTarot.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(2, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.GUARD_WITHOUT, game_.getContrat());
        assertEq(2, game_.getEntameur());
        assertEq(2, game_.getRamasseur());
    }
    @Test
    public void check71Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_CHAR);
        DealTarot deal_ = new DealTarot(0, HandTarot.pileBase());
        deal_.initDonneur((byte) 0);
        deal_.initDonne(rules_);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(2, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.GUARD_WITHOUT, game_.getContrat());
        assertEq(2, game_.getEntameur());
        assertEq(2, game_.getRamasseur());
    }
    private static DealTarot deal1(byte _dealer) {
        EqList<HandTarot> hands_ = new EqList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
    }

    @Test
    public void check8Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.intelligenceArtificielleAppel();
        game_.gererChienInconnu();
        game_.slam();
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(2, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(4, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD_WITHOUT, game_.getContrat());
        assertEq(2, game_.getEntameur());
        assertEq(2, game_.getRamasseur());
    }

    @Test
    public void check9Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.intelligenceArtificielleAppel();
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(2, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(4, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(2, game_.getEntameur());
        assertEq(2, game_.getRamasseur());
    }

    @Test
    public void check10Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.intelligenceArtificielleAppel();
        game_.ecarter(true);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first().intValue());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check11Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.appelApresEcart();
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check12Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.intelligenceArtificielleAppel();
        game_.ajouterCartesUtilisateur();
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check13Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.intelligenceArtificielleAppel();
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.CLUB_6, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_6);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check14Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.intelligenceArtificielleAppel();
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.CLUB_6, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_6);
        game_.retirerUneCarteDuChien(CardTarot.CLUB_6);
        game_.addCard(game_.getPreneur(), CardTarot.CLUB_6);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check15Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check16Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.CLUB_6, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_6);
        game_.autoriseEcartDe(CardTarot.HEART_1, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_1);
        game_.autoriseEcartDe(CardTarot.HEART_7, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_7);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

//ajouterPliAttaque()
    @Test
    public void check17Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(2, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(4, game_.getEntameur());
        assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check18Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.DIAMOND_3);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(4, game_.getEntameur());
        assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check19Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.intelligenceArtificielleAppel();
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.HEART_KING, Constants.getDefaultLanguage());
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check20Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_2);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(2, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(4, game_.getEntameur());
        assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check21Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.CLUB_6, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_6);
        game_.autoriseEcartDe(CardTarot.HEART_1, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_1);
        game_.autoriseEcartDe(CardTarot.HEART_7, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_7);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        game_.ajouterPliAttaque();
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check22Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.CLUB_6, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_6);
        game_.autoriseEcartDe(CardTarot.HEART_1, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_1);
        game_.autoriseEcartDe(CardTarot.HEART_7, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_7);
        game_.ajouterPliAttaque();
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check23Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.CLUB_6, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_6);
        game_.autoriseEcartDe(CardTarot.HEART_1, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_1);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check24Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.DIAMOND_3);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_QUEEN);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(4, game_.getEntameur());
        assertEq(4, game_.getRamasseur());
    }

    private static DealTarot deal2(byte _dealer) {
        EqList<HandTarot> hands_ = new EqList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_5);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
    }

    @Test
    public void check25Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 5);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check26Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 5);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.initEquipeDeterminee();
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check27Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 5);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.initEquipeDeterminee();
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.CLUB_6, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_6);
        game_.autoriseEcartDe(CardTarot.HEART_1, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_1);
        game_.autoriseEcartDe(CardTarot.HEART_7, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_7);
        game_.autoriseEcartDe(CardTarot.SPADE_QUEEN, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.SPADE_QUEEN);
        game_.autoriseEcartDe(CardTarot.SPADE_10, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.SPADE_10);
        game_.autoriseEcartDe(CardTarot.DIAMOND_2, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.DIAMOND_2);
        game_.ajouterPliAttaque();
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check28Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.initEquipeDeterminee();
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.CLUB_6, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_6);
        game_.autoriseEcartDe(CardTarot.HEART_1, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_1);
        game_.autoriseEcartDe(CardTarot.HEART_7, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_7);
        game_.autoriseEcartDe(CardTarot.SPADE_QUEEN, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.SPADE_QUEEN);
        game_.autoriseEcartDe(CardTarot.SPADE_10, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.SPADE_10);
        game_.autoriseEcartDe(CardTarot.DIAMOND_2, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.DIAMOND_2);
        game_.ajouterChelemUtilisateur();
        game_.ajouterPliAttaque();
        game_.setPliEnCours(true);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check29Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD_AGAINST, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check30Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.getContrats().put(BidTarot.SLAM, true);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.SLAM, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.setPliEnCours(true);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first().intValue());
        assertEq(BidTarot.SLAM, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check31Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.getContrats().put(BidTarot.SLAM, true);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.SLAM, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_21);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first().intValue());
        assertEq(BidTarot.SLAM, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check32Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_21);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD_AGAINST, game_.getContrat());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check33Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        game_.setAnnoncesPoignees((byte) 0, new EnumList<Handfuls>(Handfuls.ONE));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_17);
        handful_.ajouter(CardTarot.TRUMP_21);
        game_.ajouterPoignee(handful_, (byte) 0);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.DIAMOND_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD_AGAINST, game_.getContrat());
        assertEq(5, game_.getEntameur());
        assertEq(5, game_.getRamasseur());
    }

    private static DealTarot deal3(byte _dealer) {
        EqList<HandTarot> hands_ = new EqList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_5);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
    }

    @Test
    public void check34Test() {
        RulesTarot rules_ = new RulesTarot((byte)3);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_1_VS_2);
        DealTarot deal_ = deal3((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    private static DealTarot deal4(byte _dealer) {
        EqList<HandTarot> hands_ = new EqList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_5);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
    }

    @Test
    public void check35Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal4((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        game_.setAnnoncesMiseres((byte) 0, new EnumList<Miseres>(Miseres.LOW_CARDS));
        game_.setAnnoncesPoignees((byte) 0, new EnumList<Handfuls>(Handfuls.ONE));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_17);
        handful_.ajouter(CardTarot.TRUMP_21);
        game_.ajouterPoignee(handful_, (byte) 0);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.HEART_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.DIAMOND_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD_AGAINST, game_.getContrat());
        assertEq(5, game_.getEntameur());
        assertEq(5, game_.getRamasseur());
    }

    private static DealTarot deal5(byte _dealer) {
        EqList<HandTarot> hands_ = new EqList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_5);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
    }

    @Test
    public void check36Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        game_.setAnnoncesPoignees((byte) 0, new EnumList<Handfuls>(Handfuls.ONE));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_21);
        handful_.ajouter(CardTarot.EXCUSE);
        game_.ajouterPoignee(handful_, (byte) 0);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.DIAMOND_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first().intValue());
        assertEq(BidTarot.GUARD_AGAINST, game_.getContrat());
        assertEq(5, game_.getEntameur());
        assertEq(5, game_.getRamasseur());
    }

    @Test
    public void check37Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.DIAMOND_3);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(4, game_.getEntameur());
        assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check38Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.initPlayWithoutBid();
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(4, game_.getEntameur());
        assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check39Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.initPlayWithoutBid();
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.DIAMOND_3);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(4, game_.getEntameur());
        assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check40Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.initPlayWithoutBid();
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.DIAMOND_6);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(4, game_.getEntameur());
        assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check41Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.initPlayWithoutBid();
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.DIAMOND_3);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_10);
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(4, game_.getEntameur());
        assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check42Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(4, game_.getEntameur());
        assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check43Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.initPlayWithoutBid();
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(4, game_.getEntameur());
        assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check44Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(4, game_.getEntameur());
        assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check45Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.initPlayWithoutBid();
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.DIAMOND_6);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(4, game_.getEntameur());
        assertEq(4, game_.getRamasseur());
    }

    //ajouterCartesUtilisateur();
    @Test
    public void check1FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.TAKE, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.intelligenceArtificielleAppel();
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check2FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.SLAM, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.intelligenceArtificielleAppel();
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check3FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.intelligenceArtificielleAppel();
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check4FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.intelligenceArtificielleAppel();
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check5FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.EXCUSE);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check6FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        HandTarot cartesAppel_ = new HandTarot();
        game_.setCarteAppelee(cartesAppel_);
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur() ,CardTarot.CLUB_6);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check7FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_1);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check8FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check9FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.CLUB_6, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_6);
        game_.autoriseEcartDe(CardTarot.HEART_1, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_1);
        game_.autoriseEcartDe(CardTarot.HEART_7, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_7);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.EXCUSE);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check10FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.CLUB_6, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_6);
        game_.autoriseEcartDe(CardTarot.HEART_1, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_1);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check11FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.CLUB_6, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_6);
        game_.autoriseEcartDe(CardTarot.HEART_1, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_1);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        game_.ajouterPliAttaque();
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check12FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        game_.setAnnoncesPoignees((byte) 0, new EnumList<Handfuls>(Handfuls.TWO));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_21);
        handful_.ajouter(CardTarot.EXCUSE);
        game_.ajouterPoignee(handful_, (byte) 0);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.DIAMOND_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check13FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        rules_.getPoigneesAutorisees().put(Handfuls.FOUR, 0);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        game_.setAnnoncesPoignees((byte) 0, new EnumList<Handfuls>(Handfuls.FOUR));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_21);
        handful_.ajouter(CardTarot.EXCUSE);
        game_.ajouterPoignee(handful_, (byte) 0);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.DIAMOND_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check14FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        game_.setAnnoncesPoignees((byte) 0, new EnumList<Handfuls>(Handfuls.FOUR, Handfuls.ONE));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_21);
        handful_.ajouter(CardTarot.EXCUSE);
        game_.ajouterPoignee(handful_, (byte) 0);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.DIAMOND_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check15FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        game_.setAnnoncesMiseres((byte) 0, new EnumList<Miseres>(Miseres.LOW_CARDS));
        game_.setAnnoncesPoignees((byte) 0, new EnumList<Handfuls>(Handfuls.ONE));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_21);
        handful_.ajouter(CardTarot.EXCUSE);
        game_.ajouterPoignee(handful_, (byte) 0);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.DIAMOND_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check16FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal4((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
        game_.setAnnoncesMiseres((byte) 1, new EnumList<Miseres>(Miseres.TRUMP));
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.HEART_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.DIAMOND_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check17FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_21);
        handful_.ajouter(CardTarot.EXCUSE);
        game_.ajouterPoignee(handful_, (byte) 0);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.DIAMOND_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check18FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.HEART_KING);
        game_.getPliEnCours().setEntameur(0);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check19FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        game_.getPliEnCours().setEntameur(5);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check20FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
        game_.getPliEnCours().setEntameur(5);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check21FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.DIAMOND_3);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_10);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        game_.getPliEnCours().setEntameur(0);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check22FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        game_.getPliEnCours().setEntameur(0);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.DIAMOND_3);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_10);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check23FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getContrats().put(BidTarot.SLAM, true);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.SLAM, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check24FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
//        game_.unionPlis(true).first().getCartes().jouer(CarteTarot.SixTrefle);
//        game_.unionPlis(true).first().getCartes().ajouter(CarteTarot.Atout21);
        game_.getDistribution().main().jouer(CardTarot.TRUMP_21);
        game_.getDistribution().main().ajouter(CardTarot.CLUB_6);
        game_.getDistribution().derniereMain().ajouter(CardTarot.TRUMP_21);
        game_.getDistribution().derniereMain().jouer(CardTarot.CLUB_6);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check25FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.CLUB_6, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_6);
        game_.autoriseEcartDe(CardTarot.HEART_1, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_1);
        game_.autoriseEcartDe(CardTarot.HEART_7, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_7);
        game_.ajouterPliAttaque();
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.EXCUSE);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check26FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.CLUB_6, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_6);
        game_.autoriseEcartDe(CardTarot.HEART_1, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_1);
        game_.ajouterPliAttaque();
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.EXCUSE);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check27FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.CLUB_6, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_6);
        game_.autoriseEcartDe(CardTarot.HEART_1, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_1);
        game_.ajouterPliAttaque();
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check28FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check29FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        deal_.main().jouer(CardTarot.TRUMP_21);
        deal_.main((byte) 1).ajouter(CardTarot.TRUMP_21);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check30FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.CLUB_6, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_6);
        game_.autoriseEcartDe(CardTarot.HEART_1, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_1);
        game_.autoriseEcartDe(CardTarot.HEART_7, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_7);
        game_.autoriseEcartDe(CardTarot.SPADE_4, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.SPADE_4);
        //game_.ajouterPliAttaque();
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check31FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.getDistribution().main((byte) 1).ajouter(CardTarot.EXCUSE);
        game_.getDistribution().main((byte) 2).jouer(CardTarot.EXCUSE);
        //game_.ajouterPliAttaque();
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check32FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.CLUB_6, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_6);
        game_.autoriseEcartDe(CardTarot.HEART_1, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_1);
        game_.autoriseEcartDe(CardTarot.HEART_7, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_7);
        game_.autoriseEcartDe(CardTarot.SPADE_4, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.SPADE_4);
        game_.ajouterPliAttaque();
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check33FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.ajouterCartesUtilisateur();
        game_.autoriseEcartDe(CardTarot.CLUB_6, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_6);
        game_.autoriseEcartDe(CardTarot.HEART_1, Constants.getDefaultLanguage());
        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.HEART_1);
        game_.ajouterPliAttaque();
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check34FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.DIAMOND_3);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_10);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check35FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.DIAMOND_3);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_10);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check36FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        deal_.main().ajouterCartes(deal_.derniereMain());
        deal_.main().jouer(CardTarot.TRUMP_14);
        deal_.main().jouer(CardTarot.TRUMP_17);
        deal_.main().jouer(CardTarot.TRUMP_21);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check37FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check38FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.setAnnoncesMiseres((byte) 4, new EnumList<Miseres>(Miseres.TRUMP));
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check39FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.setAnnoncesPoignees((byte) 4, new EnumList<Handfuls>(Handfuls.ONE));
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check40FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_21);
        game_.ajouterPoignee(hand_, (byte) 4);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check41FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        deal_.derniereMain().ajouter(deal_.main().jouer(0));
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check42FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        deal_.getDonne().add(new HandTarot());
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check43FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        game_.setAnnoncesPoignees((byte) 0, new EnumList<Handfuls>(Handfuls.ONE));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_21);
        handful_.ajouter(CardTarot.EXCUSE);
        game_.ajouterPoignee(handful_, (byte) 0);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.DIAMOND_KING);
        game_.unionPlis(true).first().setSeenByAllPlayers(true);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check44FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        game_.ajouterChelemUtilisateur();
        game_.setPliEnCours(true);
        game_.setAnnoncesPoignees((byte) 0, new EnumList<Handfuls>(Handfuls.ONE));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_21);
        handful_.ajouter(CardTarot.EXCUSE);
        game_.ajouterPoignee(handful_, (byte) 0);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.DIAMOND_KING);
        game_.unionPlis(true).last().setSeenByAllPlayers(false);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check45FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.DIAMOND_3);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertEq(4, game_.getEntameur());
        assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check46FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.DIAMOND_1);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check47FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.intelligenceArtificielleAppel();
        game_.gererChienInconnu();
        game_.slam();
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check48FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        HandTarot called_ = new HandTarot();
        called_.ajouter(CardTarot.SPADE_KING);
        game_.setCarteAppelee(called_);
        game_.gererChienInconnu();
        game_.setPreneur((byte) 2);
        game_.slam();
        game_.setEntameur(game_.playerAfter(deal_.getDonneur()));
        game_.setPliEnCours(true);
        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.DIAMOND_3);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.setPliEnCours(true);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check49FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        CheckerGameTarotWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }
}
