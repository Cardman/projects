package cards.tarot;

import code.util.CustList;
import code.util.StringMap;
import code.util.core.BoolVal;
import org.junit.Test;

import cards.consts.GameType;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import cards.tarot.enumerations.ModeTarot;
import code.util.IdList;


public final class CheckerGameTarotWithRulesTest extends EquallableTarotUtil {

    @Test
    public void check1Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        DealTarot deal_ = deal();
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        assertTrue(!game_.isCallingState());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(1, game_.getEntameur());
        //assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check2Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal();
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertTrue(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertEq(1, game_.getEntameur());
        //assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check3Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        DealTarot deal_ = deal();
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(1, game_.getEntameur());
        //assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check4Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        DealTarot deal_ = deal();
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        firstTrick(game_);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(2, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(1, game_.getEntameur());
        //assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check5Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        DealTarot deal_ = deal();
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        firstTrick(game_);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(2, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(1, game_.getEntameur());
        //assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check6Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal();
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertTrue(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertEq(1, game_.getEntameur());
        //assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check7Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal();
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(2, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.GUARD_WITHOUT, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertTrue(game_.chienPasVuOuIa());
        //assertEq(2, game_.getEntameur());
        //assertEq(2, game_.getRamasseur());
    }

    @Test
    public void check7_Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal();
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(4, game_.getBids().size());
        assertTrue(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertEq(2, game_.getEntameur());
        //assertEq(2, game_.getRamasseur());
    }
    @Test
    public void check71Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_CHAR);
        DealTarot deal_ = deal();
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(2, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.GUARD_WITHOUT, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertTrue(game_.chienPasVuOuIa());
        //assertEq(2, game_.getEntameur());
        //assertEq(2, game_.getRamasseur());
    }

    @Test
    public void check8Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        intelligenceArtificielleAppel(game_);
        game_.gererChienInconnu();
        game_.slam(new DefGameTarot());
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(2, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(4, game_.getAppele().first());
        assertEq(BidTarot.GUARD_WITHOUT, game_.getContrat());
        assertEq(0, game_.getProgressingTrick().total());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
        //assertEq(1, game_.getEntameur());
        //assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check9Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        intelligenceArtificielleAppel(game_);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(2, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(4, game_.getAppele().first());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertTrue(game_.chienPasVuOuIa());
        //assertEq(2, game_.getEntameur());
        //assertEq(2, game_.getRamasseur());
    }

    @Test
    public void check9_Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(2, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertTrue(game_.chienPasVuOuIa());
        //assertEq(2, game_.getEntameur());
        //assertEq(2, game_.getRamasseur());
    }

    @Test
    public void check10Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        intelligenceArtificielleAppel(game_);
        game_.ecarter(new DefGameTarot());
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getProgressingTrick().total());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check11Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.appelApresEcart(new DefGameTarot());
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getProgressingTrick().total());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check12Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        intelligenceArtificielleAppel(game_);
        game_.ajouterCartesUtilisateur();
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertFalse(game_.chienPasVuOuIa());
        assertFalse(game_.appelSimple());
        //assertEq(0, game_.getEntameur());
        //assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check13Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        intelligenceArtificielleAppel(game_);
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertFalse(game_.chienPasVuOuIa());
        assertFalse(game_.appelSimple());
        //assertEq(0, game_.getEntameur());
        //assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check14Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        intelligenceArtificielleAppel(game_);
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        game_.retirerUneCarteDuChien(CardTarot.CLUB_6);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first());
        assertEq(BidTarot.GUARD, game_.getContrat());
        //assertEq(0, game_.getEntameur());
        //assertEq(0, game_.getRamasseur());
//        assertEq(0, game_.getCardsToBeDiscarded());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertFalse(game_.chienPasVuOuIa());
        assertFalse(game_.appelSimple());
    }

    @Test
    public void check15Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertFalse(game_.chienPasVuOuIa());
        assertFalse(game_.appelSimple());
        //assertEq(0, game_.getEntameur());
        //assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check16Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        discard(game_, CardTarot.HEART_1);
        discard(game_, CardTarot.HEART_7);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertFalse(game_.chienPasVuOuIa());
        assertFalse(game_.appelSimple());
        //assertEq(0, game_.getEntameur());
        //assertEq(0, game_.getRamasseur());
    }

//addCurTrick()
    @Test
    public void check17Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(2, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check18Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        game_.ajouterPetitAuBoutPliEnCours();
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check19Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        intelligenceArtificielleAppel(game_);
        game_.ajouterCartesUtilisateur();
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertFalse(game_.chienPasVuOuIa());
        assertFalse(game_.appelSimple());
        //assertEq(0, game_.getEntameur());
        //assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check20Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_2);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(2, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check21Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        discard(game_, CardTarot.HEART_1);
        discard(game_, CardTarot.HEART_7);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        game_.addCurTrick();
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getProgressingTrick().total());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check22Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        discard(game_, CardTarot.HEART_1);
        discard(game_, CardTarot.HEART_7);
        game_.addCurTrick();
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getProgressingTrick().total());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check23Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        discard(game_, CardTarot.HEART_1);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertFalse(game_.chienPasVuOuIa());
        assertFalse(game_.appelSimple());
        //assertEq(0, game_.getEntameur());
        //assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check24Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_QUEEN);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check25Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 5);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertTrue(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertEq(0, game_.getEntameur());
        //assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check26Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 5);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.initEquipeDeterminee();
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertFalse(game_.chienPasVuOuIa());
        assertFalse(game_.appelSimple());
        //assertEq(0, game_.getEntameur());
        //assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check261Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        gererChienInconnuDirect(game_);
//        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_5);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_6);
        game_.ajouterPetitAuBoutPliEnCours();
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first());
        assertEq(BidTarot.GUARD_WITHOUT, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
        //assertEq(1, game_.getEntameur());
        //assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check262Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        gererChienInconnuDirect(game_);
//        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_5);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_3);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(1, game_.getAppele().first());
        assertEq(BidTarot.GUARD_WITHOUT, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
        //assertEq(0, game_.getEntameur());
        //assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check263Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check264Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        game_.addCurTrick();
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check27Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 5);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.initEquipeDeterminee();
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        discard(game_, CardTarot.HEART_1);
        discard(game_, CardTarot.HEART_7);
        discard(game_, CardTarot.SPADE_QUEEN);
        discard(game_, CardTarot.SPADE_10);
        discard(game_, CardTarot.DIAMOND_2);
        game_.addCurTrick();
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getProgressingTrick().total());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
        //assertEq(0, game_.getEntameur());
        //assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check28Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.initEquipeDeterminee();
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        discard(game_, CardTarot.HEART_1);
        discard(game_, CardTarot.HEART_7);
        discard(game_, CardTarot.SPADE_QUEEN);
        discard(game_, CardTarot.SPADE_10);
        discard(game_, CardTarot.DIAMOND_2);
        game_.ajouterChelemUtilisateur();
        game_.addCurTrickDiscarded();
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first());
        assertEq(BidTarot.SLAM_GUARD, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
        //assertEq(0, game_.getEntameur());
        //assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check29Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        game_.initEquipeDeterminee();
        afterSlamWithout(game_);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first());
        assertEq(BidTarot.SLAM_GUARD_AGAINST, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
        //assertEq(0, game_.getEntameur());
        //assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check30Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.getAllowedBids().put(BidTarot.SLAM, BoolVal.TRUE);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.SLAM);
        game_.initEquipeDeterminee();
        without(game_);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first());
        assertEq(BidTarot.SLAM, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
        //assertEq(0, game_.getEntameur());
        //assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check31Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.getAllowedBids().put(BidTarot.SLAM, BoolVal.TRUE);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.SLAM);
        game_.initEquipeDeterminee();
        without(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_21);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first());
        assertEq(BidTarot.SLAM, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
        //assertEq(0, game_.getEntameur());
        //assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check32Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        game_.initEquipeDeterminee();
        afterSlamWithout(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_21);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first());
        assertEq(BidTarot.SLAM_GUARD_AGAINST, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
        //assertEq(0, game_.getEntameur());
        //assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check33Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        game_.initEquipeDeterminee();
        afterSlamWithout(game_);
        game_.setAnnoncesPoignees(new IdList<Handfuls>(Handfuls.ONE));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_17);
        handful_.ajouter(CardTarot.TRUMP_21);
        game_.ajouterPoignee(handful_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first());
        assertEq(BidTarot.SLAM_GUARD_AGAINST, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
        //assertEq(5, game_.getEntameur());
        //assertEq(5, game_.getRamasseur());
    }

    @Test
    public void check34Test() {
        RulesTarot rules_ = new RulesTarot((byte)3);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        DealTarot deal_ = deal3((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(0, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        assertTrue(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertEq(1, game_.getEntameur());
        //assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check35Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal4((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        game_.initEquipeDeterminee();
        afterSlamWithout(game_);
        game_.setAnnoncesMiseres(new IdList<Miseres>(Miseres.LOW_CARDS));
        game_.setAnnoncesPoignees(new IdList<Handfuls>(Handfuls.ONE));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_17);
        handful_.ajouter(CardTarot.TRUMP_21);
        game_.ajouterPoignee(handful_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first());
        assertEq(BidTarot.SLAM_GUARD_AGAINST, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
        //assertEq(5, game_.getEntameur());
        //assertEq(5, game_.getRamasseur());
    }

    @Test
    public void check36Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        game_.initEquipeDeterminee();
        afterSlamWithout(game_);
        game_.setAnnoncesPoignees(new IdList<Handfuls>(Handfuls.ONE));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_21);
        handful_.ajouter(CardTarot.EXCUSE);
        game_.ajouterPoignee(handful_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first());
        assertEq(BidTarot.SLAM_GUARD_AGAINST, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
        //assertEq(5, game_.getEntameur());
        //assertEq(5, game_.getRamasseur());
    }

    @Test
    public void check37Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        game_.ajouterPetitAuBoutPliEnCours();
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check38Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.initPlayWithoutBid();
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check39Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.initPlayWithoutBid();
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        game_.ajouterPetitAuBoutPliEnCours();
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check40Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.initPlayWithoutBid();
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_6);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check41Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.initPlayWithoutBid();
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        game_.ajouterPetitAuBoutPliEnCours();
        check(game_);
        assertTrue(game_.getError().isEmpty());
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_10);
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check42Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check43Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.initPlayWithoutBid();
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check44Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check45Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.initPlayWithoutBid();
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_6);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }
    @Test
    public void check46Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = dealSlam((byte) 2);

        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartes(game_.getPreneur(),game_.getDistribution().derniereMain());
        dog(game_);
        afterSlamWith(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_7);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_1);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_10);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_6);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_14);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_5);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_17);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_9);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_21);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_5);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_7);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_10);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_18);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_KNIGHT);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_KING);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KNIGHT);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_2);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_1);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_13);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_QUEEN);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_20);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_3);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_KNIGHT);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_QUEEN);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_8);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_4);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_12);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.EXCUSE);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_5);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_9);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_10);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_15);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_5);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_7);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_19);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_5);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_7);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_6);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_10);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_QUEEN);
        game_.ajouterPetitAuBoutPliEnCours();
//        game_.setPliEnCours(false);
        check(game_);
        assertTrue(game_.getError().isEmpty());
    }

    @Test
    public void check47Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = dealSlam((byte) 2);

        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.TAKE);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartes(game_.getPreneur(),game_.getDistribution().derniereMain());
        dog(game_);
        afterSlamWith(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_7);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_1);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_10);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_6);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_14);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_5);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_17);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_9);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_21);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_5);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_7);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_10);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_18);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_KNIGHT);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_KING);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KNIGHT);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_2);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_1);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_13);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_QUEEN);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_20);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_3);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_KNIGHT);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_QUEEN);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_8);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_4);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_12);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.EXCUSE);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_5);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_9);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_10);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_15);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_5);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_7);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_19);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_5);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_7);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_6);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_10);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_QUEEN);
        game_.ajouterPetitAuBoutPliEnCours();
//        game_.setPliEnCours(false);
        check(game_);
        assertTrue(game_.getError().isEmpty());
    }

    @Test
    public void check48Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        DealTarot deal_ = deal2((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.initEquipeDeterminee();
        afterSlamWithout(game_);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first());
        assertEq(BidTarot.SLAM_GUARD_WITHOUT, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
        //assertEq(0, game_.getEntameur());
        //assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check49Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        intelligenceArtificielleAppel(game_);
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.SPADE_2);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.SPADE_5);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(1, game_.getTricks().size());
        assertEq(2, game_.getPreneur());
        assertEq(0, game_.getProgressingTrick().total());
        assertEq(1, game_.getProgressingTrick().getEntameur());
        assertFalse(game_.keepBidding());
        //assertTrue(game_.chienPasVuOuIa());
    }

    @Test
    public void check50Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        discard(game_, CardTarot.HEART_1);
        discard(game_, CardTarot.HEART_7);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getTricks().size());
        assertEq(0, game_.getPreneur());
        assertEq(3, game_.getProgressingTrick().total());
        assertEq(0, game_.getProgressingTrick().getEntameur());
        assertFalse(game_.keepBidding());
        //assertFalse(game_.chienPasVuOuIa());
    }

    @Test
    public void check51Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
//        discard(game_, CardTarot.CLUB_6);
//        discard(game_, CardTarot.HEART_1);
//        discard(game_, CardTarot.HEART_7);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getTricks().size());
        assertEq(0, game_.getPreneur());
        assertEq(0, game_.getProgressingTrick().total());
        assertEq(0, game_.getProgressingTrick().getEntameur());
        assertFalse(game_.keepBidding());
        //assertFalse(game_.chienPasVuOuIa());
    }

    @Test
    public void check52Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = dealSlam((byte) 2);

        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.TAKE);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartes(game_.getPreneur(),game_.getDistribution().derniereMain());
        dog(game_);
        afterSlamWith(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_7);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_1);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_10);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_6);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_14);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_5);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_17);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_9);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_21);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_5);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_7);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_10);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_18);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_KNIGHT);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_KING);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KNIGHT);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_2);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_1);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_13);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_QUEEN);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_20);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_3);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_KNIGHT);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_QUEEN);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_8);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_4);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_12);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.EXCUSE);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_5);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_9);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_10);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_15);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_5);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_7);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_19);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_5);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_7);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_6);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_10);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_QUEEN);
        game_.ajouterPetitAuBoutPliEnCours();
//        game_.setPliEnCours(false);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
    }

    @Test
    public void check53Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidTarot.GUARD_AGAINST,game_.getContrat());
        assertEq(0, game_.getProgressingTrick().total());
        assertEq(0, game_.getTricks().size());
//        assertTrue(HandTarot.equalsSet(game_.getDeal().derniereMain(),game_.getTricks().get(0).getCartes()));
        assertFalse(game_.keepBidding());
    }

    @Test
    public void check54Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(5, game_.getPreneur());
        assertEq(BidTarot.GUARD_AGAINST,game_.getContrat());
        assertEq(0, game_.getProgressingTrick().total());
        assertEq(0, game_.getTricks().size());
        assertFalse(game_.keepBidding());
        //assertTrue(game_.chienPasVuOuIa());
    }

    @Test
    public void check55Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getProgressingTrick().total());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertFalse(game_.chienPasVuOuIa());
        assertTrue(game_.appelSimple());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check56Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.GUARD, game_.getContrat());
        assertEq(0, game_.getProgressingTrick().total());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertFalse(game_.chienPasVuOuIa());
        assertFalse(game_.appelSimple());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check57Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(0, game_.getProgressingTrick().total());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertFalse(game_.chienPasVuOuIa());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check58Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_2);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(1, game_.getProgressingTrick().total());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertFalse(game_.chienPasVuOuIa());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check59Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_JACK);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.CLUB_6);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(3, game_.getProgressingTrick().total());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
        //assertFalse(game_.chienPasVuOuIa());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check60Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        intelligenceArtificielleAppel(game_);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidTarot.GUARD_AGAINST,game_.getContrat());
        assertEq(0, game_.getProgressingTrick().total());
        assertEq(0, game_.getTricks().size());
//        assertTrue(HandTarot.equalsSet(game_.getDeal().derniereMain(),game_.getTricks().get(0).getCartes()));
        assertFalse(game_.keepBidding());
    }
    @Test
    public void check61Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidTarot.GUARD_AGAINST,game_.getContrat());
        assertEq(0, game_.getProgressingTrick().total());
        assertEq(0, game_.getTricks().size());
        assertFalse(game_.keepBidding());
        assertTrue(game_.appelSimple());
    }

    @Test
    public void check62Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 1);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidTarot.GUARD_AGAINST,game_.getContrat());
        assertEq(0, game_.getProgressingTrick().total());
        assertEq(0, game_.getTricks().size());
        assertFalse(game_.keepBidding());
        assertFalse(game_.appelSimple());
    }

    @Test
    public void check63Test() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal4((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.initEquipeDeterminee();
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.SPADE_4);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.CLUB_6);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_JACK);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.TRUMP_3);
        game_.addCurTrickDiscarded();
        game_.ajouterChelem(true);
        game_.firstLead();
        game_.setAnnoncesMiseres(new IdList<Miseres>(Miseres.LOW_CARDS));
        game_.setAnnoncesPoignees(new IdList<Handfuls>(Handfuls.ONE));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_17);
        handful_.ajouter(CardTarot.TRUMP_21);
        game_.ajouterPoignee(handful_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(game_.getError().isEmpty());
        //assertEq(1, game_.getNbPlisTotal());
        assertEq(0, game_.getPreneur());
        assertEq(1, game_.getAppele().size());
        assertEq(3, game_.getAppele().first());
        assertEq(BidTarot.SLAM_GUARD, game_.getContrat());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
    }
    @Test
    public void check1FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.TAKE);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        intelligenceArtificielleAppel(game_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
        game_.getReason();
    }

    @Test
    public void check2FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.SLAM);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        intelligenceArtificielleAppel(game_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check3FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        intelligenceArtificielleAppel(game_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check4FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        intelligenceArtificielleAppel(game_);
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_KING);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check5FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.EXCUSE);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check6FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        HandTarot cartesAppel_ = new HandTarot();
        game_.setCarteAppelee(cartesAppel_);
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_6);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check7FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_1);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check8FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_KING);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check9FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        discard(game_, CardTarot.HEART_1);
        discard(game_, CardTarot.HEART_7);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.EXCUSE);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }
    @Test
    public void check10FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        discard(game_, CardTarot.HEART_1);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check11FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        discard(game_, CardTarot.HEART_1);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        game_.addCurTrick();
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check12FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        game_.initEquipeDeterminee();
        afterSlamWithout(game_);
        game_.setAnnoncesPoignees(new IdList<Handfuls>(Handfuls.TWO));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_21);
        handful_.ajouter(CardTarot.EXCUSE);
        game_.ajouterPoignee(handful_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check121FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.MISERE);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.firstLead();
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_21);
        handful_.ajouter(CardTarot.EXCUSE);
        game_.setProgressingTrick(new TrickTarot((byte)0));
        game_.ajouterPoignee(handful_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check122FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL_WITH_MISERE);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.initPlayWithoutBid();
        game_.firstLead();
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_21);
        handful_.ajouter(CardTarot.EXCUSE);
        game_.setProgressingTrick(new TrickTarot((byte)0));
        game_.ajouterPoignee(handful_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }
    @Test
    public void check13FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        rules_.getAllowedHandfuls().put(Handfuls.FOUR, 0);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        game_.initEquipeDeterminee();
        afterSlamWithout(game_);
        game_.setAnnoncesPoignees(new IdList<Handfuls>(Handfuls.FOUR));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_21);
        handful_.ajouter(CardTarot.EXCUSE);
        game_.ajouterPoignee(handful_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check14FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        game_.initEquipeDeterminee();
        afterSlamWithout(game_);
        game_.setAnnoncesPoignees(new IdList<Handfuls>(Handfuls.FOUR, Handfuls.ONE));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_21);
        handful_.ajouter(CardTarot.EXCUSE);
        game_.ajouterPoignee(handful_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check15FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        game_.initEquipeDeterminee();
        afterSlamWithout(game_);
        game_.setAnnoncesMiseres(new IdList<Miseres>(Miseres.LOW_CARDS));
        game_.setAnnoncesPoignees(new IdList<Handfuls>(Handfuls.ONE));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_21);
        handful_.ajouter(CardTarot.EXCUSE);
        game_.ajouterPoignee(handful_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check16FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal4((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        game_.initEquipeDeterminee();
        afterSlamWithout(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_4);
        game_.setAnnoncesMiseres(new IdList<Miseres>(Miseres.TRUMP));
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check17FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        game_.initEquipeDeterminee();
        afterSlamWithout(game_);
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_21);
        handful_.ajouter(CardTarot.EXCUSE);
        game_.ajouterPoignee(handful_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }
//
//    @Test
//    public void check18FailTest() {
//        RulesTarot rules_ = new RulesTarot((byte)6);
//        rules_.setMode(ModeTarot.NORMAL);
//        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
//        rules_.getMiseres().add(Miseres.LOW_CARDS);
//        DealTarot deal_ = deal5((byte) 4);
//        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
//        int first_ = game_.playerAfter(deal_.getDealer());
//        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
//        first_ = game_.playerAfter((byte) first_);
//        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
//        game_.initEquipeDeterminee();
//        game_.gererChienInconnu();
//        game_.ajouterChelemUtilisateur();
//        game_.setPliEnCours(true);
//        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
//        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
//        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
//        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
//        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
//        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
//        game_.ajouterPetitAuBoutPliEnCours();
//        game_.setPliEnCours(true);
//        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.HEART_KING);
//        game_.getPliEnCours().setEntameur(0);
//        CheckerGameTarotWithRules.check(game_);
//        assertTrue(!game_.getError().isEmpty());
//    }
//
//    @Test
//    public void check19FailTest() {
//        RulesTarot rules_ = new RulesTarot((byte)6);
//        rules_.setMode(ModeTarot.NORMAL);
//        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
//        rules_.getMiseres().add(Miseres.LOW_CARDS);
//        DealTarot deal_ = deal5((byte) 4);
//        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
//        int first_ = game_.playerAfter(deal_.getDealer());
//        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
//        first_ = game_.playerAfter((byte) first_);
//        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
//        game_.initEquipeDeterminee();
//        game_.gererChienInconnu();
//        game_.ajouterChelemUtilisateur();
//        game_.setPliEnCours(true);
//        game_.getPliEnCours().setEntameur(5);
//        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
//        CheckerGameTarotWithRules.check(game_);
//        assertTrue(!game_.getError().isEmpty());
//    }
//
//    @Test
//    public void check20FailTest() {
//        RulesTarot rules_ = new RulesTarot((byte)6);
//        rules_.setMode(ModeTarot.NORMAL);
//        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
//        rules_.getMiseres().add(Miseres.LOW_CARDS);
//        DealTarot deal_ = deal5((byte) 4);
//        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
//        int first_ = game_.playerAfter(deal_.getDealer());
//        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
//        first_ = game_.playerAfter((byte) first_);
//        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
//        game_.initEquipeDeterminee();
//        game_.gererChienInconnu();
//        game_.ajouterChelemUtilisateur();
//        game_.setPliEnCours(true);
//        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
//        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
//        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
//        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
//        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
//        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
//        game_.getPliEnCours().setEntameur(5);
//        game_.ajouterPetitAuBoutPliEnCours();
//        game_.setPliEnCours(true);
//        CheckerGameTarotWithRules.check(game_);
//        assertTrue(!game_.getError().isEmpty());
//    }

//    @Test
//    public void check21FailTest() {
//        RulesTarot rules_ = new RulesTarot((byte)6);
//        rules_.setMode(ModeTarot.NORMAL);
//        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
//        rules_.getMiseres().add(Miseres.LOW_CARDS);
//        DealTarot deal_ = deal5((byte) 4);
//        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
//        int first_ = game_.playerAfter(deal_.getDealer());
//        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
//        first_ = game_.playerAfter((byte) first_);
//        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
//        game_.initEquipeDeterminee();
//        game_.gererChienInconnu();
//        game_.ajouterChelemUtilisateur();
//        game_.setPliEnCours(true);
//        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
//        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
//        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
//        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
//        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
//        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
//        game_.ajouterPetitAuBoutPliEnCours();
//        game_.setPliEnCours(true);
//        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.DIAMOND_KING);
//        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_2);
//        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.DIAMOND_6);
//        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.DIAMOND_1);
//        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.DIAMOND_3);
//        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_10);
//        game_.ajouterPetitAuBoutPliEnCours();
//        game_.setPliEnCours(true);
//        game_.getPliEnCours().setEntameur(0);
//        CheckerGameTarotWithRules.check(game_);
//        assertTrue(!game_.getError().isEmpty());
//    }
//
//    @Test
//    public void check22FailTest() {
//        RulesTarot rules_ = new RulesTarot((byte)6);
//        rules_.setMode(ModeTarot.NORMAL);
//        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
//        rules_.getMiseres().add(Miseres.LOW_CARDS);
//        DealTarot deal_ = deal5((byte) 4);
//        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
//        int first_ = game_.playerAfter(deal_.getDealer());
//        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
//        first_ = game_.playerAfter((byte) first_);
//        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
//        game_.initEquipeDeterminee();
//        game_.gererChienInconnu();
//        game_.ajouterChelemUtilisateur();
//        game_.setPliEnCours(true);
//        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
//        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
//        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
//        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
//        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
//        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
//        game_.ajouterPetitAuBoutPliEnCours();
//        game_.setPliEnCours(true);
//        game_.getPliEnCours().setEntameur(0);
//        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.DIAMOND_KING);
//        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_2);
//        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.DIAMOND_6);
//        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.DIAMOND_1);
//        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.DIAMOND_3);
//        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.DIAMOND_10);
//        game_.ajouterPetitAuBoutPliEnCours();
//        game_.setPliEnCours(true);
//        CheckerGameTarotWithRules.check(game_);
//        assertTrue(!game_.getError().isEmpty());
//    }
//
//    @Test
//    public void check23FailTest() {
//        RulesTarot rules_ = new RulesTarot((byte)6);
//        rules_.setMode(ModeTarot.NORMAL);
//        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
//        rules_.getAllowedBids().put(BidTarot.SLAM, BoolVal.TRUE);
//        rules_.getMiseres().add(Miseres.LOW_CARDS);
//        DealTarot deal_ = deal5((byte) 4);
//        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
//        int first_ = game_.playerAfter(deal_.getDealer());
//        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
//        first_ = game_.playerAfter((byte) first_);
//        game_.ajouterContrat(BidTarot.SLAM, (byte) first_);
//        game_.initEquipeDeterminee();
//        game_.gererChienInconnu();
//        game_.ajouterChelemUtilisateur();
//        game_.setPliEnCours(true);
//        CheckerGameTarotWithRules.check(game_);
//        assertTrue(!game_.getError().isEmpty());
//    }

    @Test
    public void check24FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal5((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
//        game_.unionPlis(true).first().getCartes().jouer(CarteTarot.SixTrefle);
//        game_.unionPlis(true).first().getCartes().ajouter(CarteTarot.Atout21);
        game_.getDistribution().hand().jouer(CardTarot.TRUMP_21);
        game_.getDistribution().hand().ajouter(CardTarot.CLUB_6);
        game_.getDistribution().derniereMain().ajouter(CardTarot.TRUMP_21);
        game_.getDistribution().derniereMain().jouer(CardTarot.CLUB_6);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check25FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        discard(game_, CardTarot.HEART_1);
        discard(game_, CardTarot.HEART_7);
        game_.addCurTrick();
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.EXCUSE);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check26FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        discard(game_, CardTarot.HEART_1);
        game_.addCurTrick();
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.EXCUSE);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check27FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        discard(game_, CardTarot.HEART_1);
        game_.addCurTrick();
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check28FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check29FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 3);
        deal_.hand().jouer(CardTarot.TRUMP_21);
        deal_.hand((byte) 1).ajouter(CardTarot.TRUMP_21);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check30FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        discard(game_, CardTarot.HEART_1);
        discard(game_, CardTarot.HEART_7);
        discard(game_, CardTarot.SPADE_4);
        //game_.addCurTrick();
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check31FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.getDistribution().hand((byte) 1).ajouter(CardTarot.EXCUSE);
        game_.getDistribution().hand((byte) 2).jouer(CardTarot.EXCUSE);
        //game_.addCurTrick();
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check32FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        discard(game_, CardTarot.HEART_1);
        discard(game_, CardTarot.HEART_7);
        discard(game_, CardTarot.SPADE_4);
        game_.addCurTrick();
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check33FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        discard(game_, CardTarot.HEART_1);
        game_.addCurTrick();
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check34FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_10);
        game_.ajouterPetitAuBoutPliEnCours();
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check35FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_10);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check36FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        deal_.hand().ajouterCartes(deal_.derniereMain());
        deal_.hand().jouer(CardTarot.TRUMP_14);
        deal_.hand().jouer(CardTarot.TRUMP_17);
        deal_.hand().jouer(CardTarot.TRUMP_21);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check37FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(CardTarot.SPADE_KING);
        game_.initConfianceAppeleUtilisateur(cartesAppel_);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check38FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        firstTrick(game_);
        game_.setAnnoncesMiseres(new IdList<Miseres>(Miseres.TRUMP));
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check39FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        firstTrick(game_);
        game_.setAnnoncesPoignees(new IdList<Handfuls>(Handfuls.ONE));
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check40FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        firstTrick(game_);
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_21);
        game_.ajouterPoignee(hand_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check41FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        deal_.derniereMain().ajouter(deal_.hand().jouer(0));
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check42FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        deal_.getDeal().add(new HandTarot());
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

//    @Test
//    public void check43FailTest() {
//        RulesTarot rules_ = new RulesTarot((byte)6);
//        rules_.setMode(ModeTarot.NORMAL);
//        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
//        rules_.getMiseres().add(Miseres.LOW_CARDS);
//        DealTarot deal_ = deal5((byte) 4);
//        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
//        int first_ = game_.playerAfter(deal_.getDealer());
//        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
//        first_ = game_.playerAfter((byte) first_);
//        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
//        game_.initEquipeDeterminee();
//        afterSlamWithout(game_);
//        game_.setAnnoncesPoignees((byte) 0, new IdList<Handfuls>(Handfuls.ONE));
//        HandTarot handful_ = new HandTarot();
//        handful_.ajouter(CardTarot.TRUMP_1);
//        handful_.ajouter(CardTarot.TRUMP_4);
//        handful_.ajouter(CardTarot.TRUMP_10);
//        handful_.ajouter(CardTarot.TRUMP_14);
//        handful_.ajouter(CardTarot.TRUMP_21);
//        handful_.ajouter(CardTarot.EXCUSE);
//        game_.ajouterPoignee(handful_, (byte) 0);
//        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
//        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
//        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
//        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
//        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
//        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
//        game_.ajouterPetitAuBoutPliEnCours();
//        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.DIAMOND_KING);
//        game_.getTricks().first().setSeenByAllPlayers(true);
//        CheckerGameTarotWithRules.check(game_);
//        assertTrue(!game_.getError().isEmpty());
//    }
//
//    @Test
//    public void check44FailTest() {
//        RulesTarot rules_ = new RulesTarot((byte)6);
//        rules_.setMode(ModeTarot.NORMAL);
//        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
//        rules_.getMiseres().add(Miseres.LOW_CARDS);
//        DealTarot deal_ = deal5((byte) 4);
//        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
//        int first_ = game_.playerAfter(deal_.getDealer());
//        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
//        first_ = game_.playerAfter((byte) first_);
//        game_.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
//        game_.initEquipeDeterminee();
//        afterSlamWithout(game_);
//        game_.setAnnoncesPoignees((byte) 0, new IdList<Handfuls>(Handfuls.ONE));
//        HandTarot handful_ = new HandTarot();
//        handful_.ajouter(CardTarot.TRUMP_1);
//        handful_.ajouter(CardTarot.TRUMP_4);
//        handful_.ajouter(CardTarot.TRUMP_10);
//        handful_.ajouter(CardTarot.TRUMP_14);
//        handful_.ajouter(CardTarot.TRUMP_21);
//        handful_.ajouter(CardTarot.EXCUSE);
//        game_.ajouterPoignee(handful_, (byte) 0);
//        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.TRUMP_4);
//        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.TRUMP_6);
//        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.TRUMP_8);
//        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.TRUMP_9);
//        game_.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_11);
//        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_16);
//        game_.ajouterPetitAuBoutPliEnCours();
//        game_.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.DIAMOND_KING);
//        game_.getTricks().last().setSeenByAllPlayers(false);
//        CheckerGameTarotWithRules.check(game_);
//        assertTrue(!game_.getError().isEmpty());
//    }

    @Test
    public void check45FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        game_.ajouterPetitAuBoutPliEnCours();
        check(game_);
        assertTrue(!game_.getError().isEmpty());
        //assertEq(3, game_.getNbPlisTotal());
        assertEq(-1, game_.getPreneur());
        assertEq(0, game_.getAppele().size());
        assertEq(BidTarot.FOLD, game_.getContrat());
        //assertEq(4, game_.getEntameur());
        //assertEq(4, game_.getRamasseur());
    }

    @Test
    public void check46FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check47FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        intelligenceArtificielleAppel(game_);
        game_.gererChienInconnu();
        game_.slam(new DefGameTarot());
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check48FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        HandTarot called_ = new HandTarot();
        called_.ajouter(CardTarot.SPADE_KING);
        game_.setCarteAppelee(called_);
        game_.gererChienInconnu();
        game_.setPreneur((byte) 2);
        game_.slam(new DefGameTarot());
        firstTrick(game_);
        invalid(game_,(byte) 4, CardTarot.DIAMOND_KING);
        invalid(game_,(byte) 0, CardTarot.DIAMOND_2);
        invalid(game_,(byte) 1, CardTarot.DIAMOND_6);
        invalid(game_,(byte) 2, CardTarot.DIAMOND_1);
        invalid(game_,(byte) 3, CardTarot.DIAMOND_3);
        game_.ajouterPetitAuBoutPliEnCours();
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check49FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.MISERE);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }
    @Test
    public void check50FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }
    @Test
    public void check51FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.TAKE);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        HandTarot called_ = new HandTarot();
        called_.ajouter(CardTarot.WHITE);
        game_.setCarteAppelee(called_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }
    @Test
    public void check51_FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.TAKE);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        HandTarot called_ = new HandTarot();
        called_.ajouter(CardTarot.HEART_KING);
        called_.ajouter(CardTarot.SPADE_KING);
        game_.setCarteAppelee(called_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }
    @Test
    public void check51__FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        HandTarot called_ = new HandTarot();
        called_.ajouter(CardTarot.HEART_KING);
        called_.ajouter(CardTarot.SPADE_KING);
        game_.setCarteAppelee(called_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }
    @Test
    public void check52FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.getAllowedHandfuls().put(Handfuls.ONE,-1);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }
    @Test
    public void check53FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        //invalid
        game_.getDeclaresHandfuls().removeLast();
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }
    @Test
    public void check54FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        //invalid
        game_.getHandfuls().removeLast();
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check55FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterCartesUtilisateur();
        discard(game_, CardTarot.CLUB_6);
        discard(game_, CardTarot.HEART_1);
        discard(game_, CardTarot.DIAMOND_2);
        game_.addCurTrickDiscarded();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_1);
        check(game_);
        assertFalse(game_.getError().isEmpty());
        //assertEq(0, game_.getEntameur());
        //assertEq(0, game_.getRamasseur());
    }
//    @Test
//    public void check55FailTest() {
//        RulesTarot rules_ = new RulesTarot();
//        rules_.setMode(ModeTarot.NORMAL);
//        DealTarot deal_ = deal1((byte) 0);
//        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
//        //invalid
//        game_.getConfidence().removeLast();
//        CheckerGameTarotWithRules.check(game_);
//        assertTrue(!game_.getError().isEmpty());
//    }
//    @Test
//    public void check56FailTest() {
//        RulesTarot rules_ = new RulesTarot();
//        rules_.setMode(ModeTarot.NORMAL);
//        DealTarot deal_ = deal1((byte) 0);
//        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
//        //invalid
//        game_.getConfidence().first().removeLast();
//        CheckerGameTarotWithRules.check(game_);
//        assertTrue(!game_.getError().isEmpty());
//    }
    @Test
    public void check57FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        //invalid
        game_.getDeclaresMiseres().removeLast();
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check58FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        DealTarot deal_ = deal();
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.getScoresRef().clear();
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check59FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal6((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_AGAINST);
        game_.initEquipeDeterminee();
        afterSlamWithout(game_);
        game_.setAnnoncesPoignees(new IdList<Handfuls>(Handfuls.ONE));
        HandTarot handful_ = new HandTarot();
        handful_.ajouter(CardTarot.TRUMP_1);
        handful_.ajouter(CardTarot.TRUMP_4);
        handful_.ajouter(CardTarot.TRUMP_10);
        handful_.ajouter(CardTarot.TRUMP_14);
        handful_.ajouter(CardTarot.TRUMP_21);
        handful_.ajouter(CardTarot.EXCUSE);
        game_.ajouterPoignee(handful_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_8);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_9);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        game_.ajouterPetitAuBoutPliEnCours();
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check60FailTest() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        intelligenceArtificielleAppel(game_);
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.SPADE_1);
        check(game_);
        assertFalse(game_.getError().isEmpty());
    }

    @Test
    public void check61FailTest() {
        RulesTarot rules_ = new RulesTarot((byte)6);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        DealTarot deal_ = deal4((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.initEquipeDeterminee();
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.SPADE_4);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.CLUB_6);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_JACK);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.TRUMP_4);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.TRUMP_3);
        game_.addCurTrickDiscarded();
        game_.ajouterChelem(true);
        game_.firstLead();
        check(game_);
        assertFalse(game_.getError().isEmpty());
//        game_.autoriseEcartDe(CardTarot.SPADE_QUEEN);
    }

    private static void check(GameTarot _game) {
        CheckerGameTarotWithRules.check(_game, mesCheck());
    }
    public static StringMap<String> mesCheck(){
        StringMap<String> m_ = new StringMap<String>();
        m_.addEntry(CheckerGameTarotWithRules.BAD_DECLARING,CheckerGameTarotWithRules.BAD_DECLARING);
        m_.addEntry(CheckerGameTarotWithRules.BAD_PLAYING,CheckerGameTarotWithRules.BAD_PLAYING);
        m_.addEntry(CheckerGameTarotWithRules.A_CARD_IS_MISSING_OR_EXCEDING_FOR_DISCARDING,CheckerGameTarotWithRules.A_CARD_IS_MISSING_OR_EXCEDING_FOR_DISCARDING);
        m_.addEntry(CheckerGameTarotWithRules.A_CARD_IS_MISSING_FOR_DISCARDING,CheckerGameTarotWithRules.A_CARD_IS_MISSING_FOR_DISCARDING);
        m_.addEntry(CheckerGameTarotWithRules.THIS_CARD_IS_NOT_DISCARDABLE,CheckerGameTarotWithRules.THIS_CARD_IS_NOT_DISCARDABLE);
        m_.addEntry(CheckerGameTarotWithRules.A_CARD_MUST_BE_CALLED_AMONG_ALL_POSSIBLE,CheckerGameTarotWithRules.A_CARD_MUST_BE_CALLED_AMONG_ALL_POSSIBLE);
        m_.addEntry(CheckerGameTarotWithRules.THERE_SHOULD_NOT_BE_ANY_TRICK,CheckerGameTarotWithRules.THERE_SHOULD_NOT_BE_ANY_TRICK);
        m_.addEntry(CheckerGameTarotWithRules.NO_ALLOWED_BID,CheckerGameTarotWithRules.NO_ALLOWED_BID);
        m_.addEntry(CheckerGameTarotWithRules.ERROR_CALLING_MUST_BE_BEFORE_DISCARD,CheckerGameTarotWithRules.ERROR_CALLING_MUST_BE_BEFORE_DISCARD);
        m_.addEntry(CheckerGameTarotWithRules.ERROR_BID_MUST_BE_BEFORE_CALLING,CheckerGameTarotWithRules.ERROR_BID_MUST_BE_BEFORE_CALLING);
        m_.addEntry(CheckerGameTarotWithRules.TOO_MUCH_BIDS,CheckerGameTarotWithRules.TOO_MUCH_BIDS);
        m_.addEntry(CheckerGameTarotWithRules.INVALID_BID,CheckerGameTarotWithRules.INVALID_BID);
        m_.addEntry(CheckerGameTarotWithRules.ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE,CheckerGameTarotWithRules.ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE);
        m_.addEntry(CheckerGameTarotWithRules.BAD_COUNT_FOR_HANDS,CheckerGameTarotWithRules.BAD_COUNT_FOR_HANDS);
        m_.addEntry(CheckerGameTarotWithRules.TRICK_WITH_BAD_COUNT,CheckerGameTarotWithRules.TRICK_WITH_BAD_COUNT);
        m_.addEntry(CheckerGameTarotWithRules.BAD_COUNT_FOR_REMAINING_CARDS,CheckerGameTarotWithRules.BAD_COUNT_FOR_REMAINING_CARDS);
        m_.addEntry(CheckerGameTarotWithRules.BAD_COUNT_FOR_DEAL,CheckerGameTarotWithRules.BAD_COUNT_FOR_DEAL);
        m_.addEntry(CheckerGameTarotWithRules.INVALID_RULES,CheckerGameTarotWithRules.INVALID_RULES);
        m_.addEntry(CheckerGameTarotWithRules.BAD_CARD,CheckerGameTarotWithRules.BAD_CARD);
        return m_;
    }
    private static DealTarot deal1(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
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

    private static DealTarot deal2(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
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

    private static DealTarot deal3(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
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

    private static DealTarot deal4(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
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

    private static DealTarot deal5(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
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

    private static DealTarot dealSlam(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_15);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
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
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_1);
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
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_10);
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
    private static DealTarot deal6(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
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
        hand_.ajouter(CardTarot.TRUMP_3);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_2);
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

    private DealTarot deal() {
        DealTarot deal_ = new DealTarot(0);
        deal_.setDealer((byte) 0);
        deal_.getDeal().add(create(CardTarot.SPADE_JACK,CardTarot.SPADE_KING,CardTarot.SPADE_6,CardTarot.HEART_5,CardTarot.SPADE_1,CardTarot.HEART_10,CardTarot.DIAMOND_10,CardTarot.TRUMP_1,CardTarot.DIAMOND_5,CardTarot.TRUMP_6,CardTarot.CLUB_KING,CardTarot.TRUMP_11,CardTarot.CLUB_9,CardTarot.TRUMP_16,CardTarot.CLUB_4));
        deal_.getDeal().add(create(CardTarot.SPADE_KNIGHT,CardTarot.SPADE_8,CardTarot.HEART_3,CardTarot.SPADE_3,CardTarot.HEART_8,CardTarot.DIAMOND_KNIGHT,CardTarot.HEART_QUEEN,CardTarot.DIAMOND_7,CardTarot.TRUMP_4,CardTarot.DIAMOND_2,CardTarot.TRUMP_9,CardTarot.CLUB_JACK,CardTarot.TRUMP_14,CardTarot.CLUB_6,CardTarot.TRUMP_19));
        deal_.getDeal().add(create(CardTarot.SPADE_10,CardTarot.HEART_1,CardTarot.SPADE_5,CardTarot.HEART_6,CardTarot.DIAMOND_KING,CardTarot.HEART_JACK,CardTarot.DIAMOND_9,CardTarot.TRUMP_2,CardTarot.DIAMOND_4,CardTarot.TRUMP_7,CardTarot.CLUB_QUEEN,CardTarot.TRUMP_12,CardTarot.CLUB_8,CardTarot.TRUMP_17,CardTarot.CLUB_3));
        deal_.getDeal().add(create(CardTarot.SPADE_QUEEN,CardTarot.SPADE_7,CardTarot.HEART_4,CardTarot.SPADE_2,CardTarot.HEART_9,CardTarot.DIAMOND_JACK,CardTarot.HEART_KING,CardTarot.DIAMOND_6,CardTarot.TRUMP_5,CardTarot.DIAMOND_1,CardTarot.TRUMP_10,CardTarot.CLUB_10,CardTarot.TRUMP_15,CardTarot.CLUB_5,CardTarot.TRUMP_20));
        deal_.getDeal().add(create(CardTarot.SPADE_9,CardTarot.HEART_2,CardTarot.SPADE_4,CardTarot.HEART_7,CardTarot.DIAMOND_QUEEN,CardTarot.HEART_KNIGHT,CardTarot.DIAMOND_8,CardTarot.TRUMP_3,CardTarot.DIAMOND_3,CardTarot.TRUMP_8,CardTarot.CLUB_KNIGHT,CardTarot.TRUMP_13,CardTarot.CLUB_7,CardTarot.TRUMP_18,CardTarot.CLUB_2));
        deal_.getDeal().add(create(CardTarot.EXCUSE,CardTarot.TRUMP_21,CardTarot.CLUB_1));
        return deal_;
    }

    private void afterSlamWith(GameTarot _game) {
        _game.ajouterChelem(true);
        _game.addCurTrickDiscarded();
//        _game.addCurTrick();
//        _game.firstLead();
    }


    private void firstTrick(GameTarot _game) {
        _game.firstLead();
    }

    private void afterSlamWithout(GameTarot _game) {
        gererChienInconnuChelemDirect(_game);
    }

    private void without(GameTarot _game) {
        gererChienInconnuDirect(_game);
    }

    private void discard(GameTarot _game, CardTarot _card) {
        _game.ajouterUneCarteDansPliEnCoursPreneur(_card);
    }

    public void invalid(GameTarot _g,byte _numero, CardTarot _c) {
        _g.jouer(_numero,_c);
        _g.ajouterUneCarteDansPliEnCoursSimple(_c);
    }

    private void dog(GameTarot _game) {
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_JACK);
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.SPADE_4);
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.CLUB_6);
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_JACK);
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.SPADE_KNIGHT);
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.SPADE_JACK);
    }

}
