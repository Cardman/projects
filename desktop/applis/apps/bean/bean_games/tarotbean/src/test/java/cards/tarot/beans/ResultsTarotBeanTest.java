package cards.tarot.beans;

import cards.consts.CouleurValeur;
import cards.consts.GameType;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.bean.nat.NatNavigation;
import code.bean.nat.*;
import code.scripts.confs.TarotScriptPages;
import code.scripts.pages.cards.MessTarotPage;
import code.scripts.pages.cards.PagesTarots;
import code.sml.util.TranslationsAppli;
import code.util.*;
import org.junit.Test;

public final class ResultsTarotBeanTest extends BeanTarotCommonTs {

    private static final String FOLD = "fold";
    private static final String WITHOUT = "without";
    private static final String HEART_KING = "heart king";

    @Test
    public void eq1() {
        assertTrue(callResultsTarotBeanEquality(displaying(beanResultsTarot(EN,results(game1(),0)))));
    }
    @Test
    public void eq2() {
        assertFalse(callResultsTarotBeanEquality(displaying(beanResultsTarot(EN,results(game2(),0)))));
    }

    @Test
    public void loose1() {
        assertFalse(callResultsTarotBeanLoose(displaying(beanResultsTarot(EN,results(game1(),0)))));
    }
    @Test
    public void loose2() {
        assertTrue(callResultsTarotBeanLoose(displaying(beanResultsTarot(EN,results(game2(),0)))));
    }

    @Test
    public void win1() {
        assertFalse(callResultsTarotBeanWin(displaying(beanResultsTarot(EN,results(game2(),0)))));
    }
    @Test
    public void win2() {
        assertTrue(callResultsTarotBeanWin(displaying(beanResultsTarot(EN,results(game2(),1)))));
    }

    @Test
    public void failBid1() {
        assertFalse(callResultsTarotBeanFailedBid(displaying(beanResultsTarot(EN,results(game1(),0)))));
    }
    @Test
    public void failBid2() {
        assertTrue(callResultsTarotBeanFailedBid(displaying(beanResultsTarot(EN,results(game2(),0)))));
    }

    @Test
    public void midBid1() {
        assertTrue(callResultsTarotBeanMidBid(displaying(beanResultsTarot(EN,results(game1(),0)))));
    }
    @Test
    public void midBid2() {
        assertFalse(callResultsTarotBeanMidBid(displaying(beanResultsTarot(EN,results(game2(),0)))));
    }

    @Test
    public void sucBid1() {
        assertTrue(callResultsTarotBeanSuccessfulBid(displaying(beanResultsTarot(EN,results(game3(),0)))));
    }
    @Test
    public void sucBid2() {
        assertFalse(callResultsTarotBeanSuccessfulBid(displaying(beanResultsTarot(EN,results(game2(),0)))));
    }
    @Test
    public void slam1() {
        assertTrue(callResultsTarotBeanSuccessfulDeclaredSlamAttack(displaying(beanResultsTarot(EN,resultsFive(game4(),0)))));
    }
    @Test
    public void slam2() {
        assertFalse(callResultsTarotBeanSuccessfulDeclaredSlamAttack(displaying(beanResultsTarot(EN,results(game2(),0)))));
    }
    @Test
    public void slamNotDecl1() {
        assertTrue(callResultsTarotBeanSuccessfulNoDeclaredSlamAttack(displaying(beanResultsTarot(EN,resultsFive(game5(),0)))));
    }
    @Test
    public void slamNotDecl2() {
        assertFalse(callResultsTarotBeanSuccessfulNoDeclaredSlamAttack(displaying(beanResultsTarot(EN,resultsFive(game4(),0)))));
    }
    @Test
    public void slamNotDecl3() {
        assertFalse(callResultsTarotBeanSuccessfulNoDeclaredSlamAttack(displaying(beanResultsTarot(EN,results(game2(),0)))));
    }
    @Test
    public void noSlamAtt1() {
        assertFalse(callResultsTarotBeanNoSlamAttack(displaying(beanResultsTarot(EN,resultsFive(game5(),0)))));
    }
    @Test
    public void noSlamAtt2() {
        assertTrue(callResultsTarotBeanNoSlamAttack(displaying(beanResultsTarot(EN,results(game2(),0)))));
    }
    @Test
    public void failedSlam1() {
        assertTrue(callResultsTarotBeanFailedSlamAttack(displaying(beanResultsTarot(EN,resultsFive(game6(),0)))));
    }
    @Test
    public void failedSlam2() {
        assertFalse(callResultsTarotBeanFailedSlamAttack(displaying(beanResultsTarot(EN,resultsFive(game4(),0)))));
    }
    @Test
    public void slamDef1() {
        assertFalse(callResultsTarotBeanSlamDefense(displaying(beanResultsTarot(EN,resultsFive(game4(),0)))));
    }
    @Test
    public void slamDef2() {
        assertTrue(callResultsTarotBeanSlamDefense(displaying(beanResultsTarot(EN,results(game6(),0)))));
    }
    @Test
    public void noSlamDef1() {
        assertTrue(callResultsTarotBeanNoSlamDefense(displaying(beanResultsTarot(EN,resultsFive(game4(),0)))));
    }
    @Test
    public void noSlamDef2() {
        assertFalse(callResultsTarotBeanNoSlamDefense(displaying(beanResultsTarot(EN,results(game6(),0)))));
    }
    @Test
    public void initialUserPosition1() {
        assertEq(1,callResultsTarotBeanInitialUserPosition(displaying(beanResultsTarot(EN,results(game7(),0)))));
    }
    @Test
    public void finalUserPosition1() {
        assertEq(1,callResultsTarotBeanFinalUserPosition(displaying(beanResultsTarot(EN,results(game7(),0)))));
    }
    @Test
    public void initialUserPosition2() {
        assertEq(5,callResultsTarotBeanInitialUserPosition(displaying(beanResultsTarot(EN,results(game8(),0)))));
    }
    @Test
    public void finalUserPosition2() {
        assertEq(5,callResultsTarotBeanFinalUserPosition(displaying(beanResultsTarot(EN,results(game8(),0)))));
    }
    @Test
    public void playClassic1() {
        assertTrue(callTarotBeanPlayClassicGame(displaying(beanResultsTarot(EN,results(game4(),0)))));
    }
    @Test
    public void playClassic2() {
        assertFalse(callTarotBeanPlayClassicGame(displaying(beanResultsTarot(EN,results(game7(),0)))));
    }
    @Test
    public void playClassic3() {
        assertFalse(callTarotBeanPlayClassicGame(displaying(beanResultsTarot(EN,results(game9(),0)))));
    }
    @Test
    public void playVariant1() {
        assertFalse(callTarotBeanPlayVariantModeGame(displaying(beanResultsTarot(EN,results(game4(),0)))));
    }
    @Test
    public void playVariant2() {
        assertTrue(callTarotBeanPlayVariantModeGame(displaying(beanResultsTarot(EN,results(game7(),0)))));
    }
    @Test
    public void playVariant3() {
        assertFalse(callTarotBeanPlayVariantModeGame(displaying(beanResultsTarot(EN,results(game9(),0)))));
    }
    @Test
    public void bidStr() {
        assertEq(WITHOUT,callResultsTarotBeanBidString(displaying(beanResultsTarot(EN,resultsFive(game4(),0)))));
    }
    @Test
    public void diff() {
        assertEq(55,callResultsTarotBeanAbsoluteDiff(displaying(beanResultsTarot(EN,resultsFive(game4(),0)))));
    }
    @Test
    public void calledPlayers() {
        NaSt pl_ = callResultsTarotBeanCalledPlayers(displaying(beanResultsTarot(EN, resultsFive(game4(), 0))));
        assertSizeEq(1, pl_);
        assertEq("1", elt(pl_,0));
    }
    @Test
    public void calledCards() {
        NaSt pl_ = callResultsTarotBeanCalledCardsList(displaying(beanResultsTarot(EN, resultsFive(game4(), 0))));
        assertSizeEq(1, pl_);
        assertEq(HEART_KING, elt(pl_,0));
    }
    @Test
    public void taker1() {
        assertEq("0", callResultsTarotBeanTaker(displaying(beanResultsTarot(EN, resultsFive(game4(), 0)))));
    }
    @Test
    public void taker2() {
        assertEq(91, callResultsTarotBeanScoreTaker(displaying(beanResultsTarot(EN, resultsFive(game4(), 0)))));
    }
    @Test
    public void taker3() {
        assertEq(3, callResultsTarotBeanNumberOudlersTaker(displaying(beanResultsTarot(EN, resultsFive(game4(), 0)))));
    }
    @Test
    public void taker4() {
        assertEq(36, callResultsTarotBeanNeedlyScoresTaker(displaying(beanResultsTarot(EN, resultsFive(game4(), 0)))));
    }
    @Test
    public void taker5() {
        assertEq(55, callResultsTarotBeanDifferenceScoreTaker(displaying(beanResultsTarot(EN, resultsFive(game4(), 0)))));
    }
    @Test
    public void taker6() {
        assertEq(90, callResultsTarotBeanScoreTakerWithoutDeclaring(displaying(beanResultsTarot(EN, resultsFive(game4(), 0)))));
    }
    @Test
    public void bonus1() {
        assertEq(400, callResultsTarotBeanAdditionnalBonusesAttack(displaying(beanResultsTarot(EN, resultsFive(game4(), 0)))));
    }
    @Test
    public void bonus2() {
        assertEq(0, callResultsTarotBeanAdditionnalBonusesDefense(displaying(beanResultsTarot(EN, resultsFive(game4(), 0)))));
    }
    @Test
    public void maxDiff1() {
        assertEq(46, callResultsTarotBeanMaxDifference(displaying(beanResultsTarot(EN, resultsFive(game7(), 0)))));
    }
    @Test
    public void maxDiff2() {
        assertEq(80,callResultsTarotBeanMaxDoubledDifference(displaying(beanResultsTarot(EN, resultsFive(game7(), 0)))));
    }
    @Test
    public void ld() {
        NaSt res_ = callResultsTarotBeanLinesDeal(displaying(beanResultsTarot(EN, resultsFive(game4(), 0))));
        assertSizeEq(1, res_);
        assertSizeEq(5, res_,0);
        assertNumberEq(0, res_,0);
        assertEq(1730, res_,0,0);
        assertEq(865, res_,0,1);
        assertEq(-865, res_,0,2);
        assertEq(-865, res_,0,3);
        assertEq(-865, res_,0,4);
    }
    @Test
    public void scores() {
        NaSt res_ = callTarotBeanGetScores(displaying(beanResultsTarot(EN, resultsFive(game4(), 0))));
        assertSizeEq(1, res_);
        assertSizeLongsEq(5, res_,0);
        assertLongsEq(1730, res_,0,0);
        assertLongsEq(865, res_,0,1);
        assertLongsEq(-865, res_,0,2);
        assertLongsEq(-865, res_,0,3);
        assertLongsEq(-865, res_,0,4);
    }
    @Test
    public void nicknames() {
        assertSizeEq(5,callTarotBeanNicknames(displaying(beanResultsTarot(EN, resultsFive(game4(), 0)))));
        NaSt pl_ = callTarotBeanGetNicknames(displaying(beanResultsTarot(EN, resultsFive(game4(), 0))));
        assertSizeEq(5, pl_);
        assertEq("0", elt(pl_,0));
        assertEq("1", elt(pl_,1));
        assertEq("2", elt(pl_,2));
        assertEq("3", elt(pl_,3));
        assertEq("4", elt(pl_,4));
    }
    @Test
    public void alon() {
        assertEq("",alone());
    }

    @Test
    public void init1() {
        StringMap<String> other_ = MessTarotPage.ms();
//        NavigationCore.adjust(other_);
        StringMap<TranslationsAppli> mes_ = new StringMap<TranslationsAppli>();
        mes_.addEntry(EN,MessTarotPage.enTarot());
        mes_.addEntry(FR,MessTarotPage.frTarot());
        TarotStandardsResults stds_ = new TarotStandardsResults();
        NatNavigation nav_ = stds_.nav(new StringList(EN,FR), new ResultsTarotLoader(), PagesTarots.build(),other_,mes_);
        nav_.setLanguage(EN);
        stds_.setDataBase(resultsFive(game4(), 0));
        stds_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Results</title><link href=\""+TarotScriptPages.CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "td,caption{\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "</style></head><body><h1>1 Calculation of attack team's points</h1><ul><li>Number of oudlers won in the attack team's tricks:3</li><li>Number of necessary points in order that the taker wins:36</li><li>Number of points won in the attack team's tricks:91</li></ul><h1>2 Attack team</h1><ul><li>Taker:0</li><li>Taker's partners:<ul><li>1</li></ul></li><li>Called cards:<ul><li>heart king</li></ul></li><li>Bid:without</li></ul><h1>3 Results</h1><p>You win.</p><br/><p>The bid without is passed of 55 points.</p><br/><p>The attack's team has achieved the grand slam by declaring it.The defense's team has not won all tricks.</p><br/><br/><table border=\"1\"><caption>Scores</caption><thead><tr><td/><td>0</td><td>1</td><td>2</td><td>3</td><td>4</td></tr></thead><tbody><tr><td>0</td><td>1730</td><td>865</td><td>-865</td><td>-865</td><td>-865</td></tr></tbody></table><br/></body></html>",nav_.getHtmlText());
    }

    @Test
    public void init2() {
        StringMap<String> other_ = MessTarotPage.ms();
//        NavigationCore.adjust(other_);
        StringMap<TranslationsAppli> mes_ = new StringMap<TranslationsAppli>();
        mes_.addEntry(EN,MessTarotPage.enTarot());
        mes_.addEntry(FR,MessTarotPage.frTarot());
        TarotStandardsResults stds_ = new TarotStandardsResults();
        NatNavigation nav_ = stds_.nav(new StringList(EN,FR), new ResultsTarotLoader(), PagesTarots.build(),other_,mes_);
        nav_.setLanguage(EN);
        stds_.setDataBase(resultsFive(game7(), 0));
        stds_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Results</title><link href=\""+TarotScriptPages.CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "td,caption{\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "</style></head><body><h1>Results</h1><ul><li>The greatest difference of points:46</li><li>Your position before deciding:1</li><li>Your final position:1</li></ul><br/><table border=\"1\"><caption>Scores</caption><thead><tr><td/><td>0</td><td>1</td><td>2</td><td>3</td><td>4</td></tr></thead><tbody><tr><td>0</td><td>6744</td><td>-1260</td><td>-1828</td><td>-1828</td><td>-1828</td></tr></tbody></table><br/></body></html>",nav_.getHtmlText());
    }

    @Test
    public void init3() {
        StringMap<String> other_ = MessTarotPage.ms();
//        NavigationCore.adjust(other_);
        StringMap<TranslationsAppli> mes_ = new StringMap<TranslationsAppli>();
        mes_.addEntry(EN,MessTarotPage.enTarot());
        mes_.addEntry(FR,MessTarotPage.frTarot());
        TarotStandardsResults stds_ = new TarotStandardsResults();
        NatNavigation nav_ = stds_.nav(new StringList(EN,FR), new ResultsTarotLoader(), PagesTarots.build(),other_,mes_);
        nav_.setLanguage(EN);
        stds_.setDataBase(resultsFive(game8(), 0));
        stds_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Results</title><link href=\""+TarotScriptPages.CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "td,caption{\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "</style></head><body><h1>Results</h1><ul><li>The greatest difference of points:62</li><li>Your position before deciding:5</li><li>Your final position:5</li></ul><br/><table border=\"1\"><caption>Scores</caption><thead><tr><td/><td>0</td><td>1</td><td>2</td><td>3</td><td>4</td></tr></thead><tbody><tr><td>0</td><td>-2088</td><td>0</td><td>696</td><td>696</td><td>696</td></tr></tbody></table><br/></body></html>",nav_.getHtmlText());
    }
    private static ResultsTarot results(GameTarot _g, int _user) {
        ResultsTarot res_ = new ResultsTarot();
        res_.setGame(_g);
        res_.getRes().setUser((byte) _user);
        res_.initialize(fourPseudos("0","1","2","3"), new CustList<Longs>());
//        res_.getRes().setGeneral(CoreResourcesAccess.key(Suit.SPADE)+SEP+ SPADE);
        res_.getRes().setGeneral(new StringMap<String>());
        res_.getRes().setSpecific(new StringMap<String>());
//        res_.getRes().setGeneral(readCoreResource());
//        res_.getRes().setSpecific(readResource());
        return res_;
    }
    private static ResultsTarot resultsFive(GameTarot _g, int _user) {
        ResultsTarot res_ = new ResultsTarot();
        res_.setGame(_g);
        res_.getRes().setUser((byte) _user);
        res_.initialize(fivePseudos("0","1","2","3","4"), new CustList<Longs>());
//        res_.getRes().setGeneral(CoreResourcesAccess.key(Suit.SPADE)+SEP+ SPADE);
        res_.getRes().setGeneral(new StringMap<String>());
        StringMap<String> e_ = new  StringMap<String>();
        e_.addEntry(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.FOLD),FOLD);
        e_.addEntry(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.GUARD_WITHOUT),WITHOUT);
        res_.getRes().setSpecific(e_);
        StringMap<String> cards_ = new StringMap<String>();
        cards_.addEntry(CouleurValeur.HEART_KING+"",HEART_KING);
        res_.getRes().setGeneralCards(cards_);
//        res_.getRes().setGeneral(readCoreResource());
//        res_.getRes().setSpecific(readResource());
        return res_;
    }

    private static StringList fourPseudos(String _one, String _two, String _three, String _four) {
        StringList ps_ = new StringList();
        ps_.add(_one);
        ps_.add(_two);
        ps_.add(_three);
        ps_.add(_four);
        return ps_;
    }

    private static StringList fivePseudos(String _one, String _two, String _three, String _four, String _five) {
        StringList ps_ = new StringList();
        ps_.add(_one);
        ps_.add(_two);
        ps_.add(_three);
        ps_.add(_four);
        ps_.add(_five);
        return ps_;
    }

    private static GameTarot game7() {
        RulesTarot rules_ = new RulesTarot((byte) 5);
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.getMiseres().add(Miseres.SUIT);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        rules_.getMiseres().add(Miseres.CHARACTER);
        rules_.getAllowedHandfuls().put(Handfuls.FOUR,15);
        DealTarot deal_ = dealFivePlayers((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.firstLead();
        playedCards3(game_);
        //CheckerGameTarotWithRules.check(game_);
        return game_;
    }

    private static void playedCards3(GameTarot _game) {
        handful(_game, Handfuls.FOUR,fifteen(CardTarot.TRUMP_21,CardTarot.TRUMP_20,CardTarot.TRUMP_19,
                CardTarot.TRUMP_18,CardTarot.TRUMP_17,CardTarot.TRUMP_16,
                CardTarot.TRUMP_15,CardTarot.TRUMP_14,CardTarot.TRUMP_13,
                CardTarot.TRUMP_12,CardTarot.TRUMP_11,CardTarot.TRUMP_10,
                CardTarot.TRUMP_9,CardTarot.TRUMP_8,CardTarot.TRUMP_1));
        miseres(_game, three(Miseres.SUIT,Miseres.CHARACTER,Miseres.LOW_CARDS));
        playedCards(_game);
    }

    private static GameTarot game8() {
        RulesTarot rules_ = new RulesTarot((byte) 5);
        rules_.setMode(ModeTarot.MISERE);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        DealTarot deal_ = dealFivePlayers((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.firstLead();
        playedCards(game_);
        //CheckerGameTarotWithRules.check(game_);
        return game_;
    }

    private static GameTarot game9() {
        RulesTarot rules_ = new RulesTarot((byte) 5);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        DealTarot deal_ = dealFivePlayers((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        bidsFive3(deal_, game_);
        //CheckerGameTarotWithRules.check(game_);
        return game_;
    }
    private static GameTarot game6() {
        RulesTarot rules_ = new RulesTarot((byte) 5);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.getMiseres().add(Miseres.SUIT);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        rules_.getMiseres().add(Miseres.CHARACTER);
        rules_.getAllowedHandfuls().put(Handfuls.FOUR,15);
        DealTarot deal_ = dealFivePlayers((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        bidsFive2(deal_, game_);
        game_.initConfianceAppeleUtilisateur(single(CardTarot.SPADE_KING));
        gererChienInconnuChelemDirect(game_);
        playedCards2(game_);
        //CheckerGameTarotWithRules.check(game_);
        return game_;
    }

    private static GameTarot game5() {
        RulesTarot rules_ = new RulesTarot((byte) 5);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.getMiseres().add(Miseres.SUIT);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        rules_.getMiseres().add(Miseres.CHARACTER);
        rules_.getAllowedHandfuls().put(Handfuls.FOUR,15);
        DealTarot deal_ = dealFivePlayers((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        bidsFive(deal_, game_);
        game_.initConfianceAppeleUtilisateur(single(CardTarot.HEART_KING));
        gererChienInconnuDirect(game_);
        playedCards3(game_);
        //CheckerGameTarotWithRules.check(game_);
        return game_;
    }

    private static GameTarot game4() {
        RulesTarot rules_ = new RulesTarot((byte) 5);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.getMiseres().add(Miseres.SUIT);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        rules_.getMiseres().add(Miseres.CHARACTER);
        rules_.getAllowedHandfuls().put(Handfuls.FOUR,15);
        DealTarot deal_ = dealFivePlayers((byte) 4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        bidsFive(deal_, game_);
        game_.initConfianceAppeleUtilisateur(single(CardTarot.HEART_KING));
        gererChienInconnuChelemDirect(game_);
        playedCards3(game_);
        //CheckerGameTarotWithRules.check(game_);
        return game_;
    }

    private static void playedCards(GameTarot _game) {
        play(_game, CardTarot.TRUMP_21);
        play(_game, CardTarot.EXCUSE);
        play(_game, CardTarot.HEART_4);
        play(_game, CardTarot.HEART_8);
        play(_game, CardTarot.SPADE_10);
        secPlayedCards(_game);
    }

    private static void playedCards2(GameTarot _game) {
        play(_game, CardTarot.HEART_4);
        play(_game, CardTarot.HEART_8);
        play(_game, CardTarot.SPADE_10);
        handful(_game, Handfuls.FOUR,fifteen(CardTarot.TRUMP_21,CardTarot.TRUMP_20,CardTarot.TRUMP_19,
                CardTarot.TRUMP_18,CardTarot.TRUMP_17,CardTarot.TRUMP_16,
                CardTarot.TRUMP_15,CardTarot.TRUMP_14,CardTarot.TRUMP_13,
                CardTarot.TRUMP_12,CardTarot.TRUMP_11,CardTarot.TRUMP_10,
                CardTarot.TRUMP_9,CardTarot.TRUMP_8,CardTarot.TRUMP_1));
        miseres(_game, three(Miseres.SUIT,Miseres.CHARACTER,Miseres.LOW_CARDS));
        play(_game, CardTarot.TRUMP_21);
        play(_game, CardTarot.EXCUSE);
        secPlayedCards(_game);
    }

    private static void secPlayedCards(GameTarot _game) {
        tr(_game);
        play(_game, CardTarot.TRUMP_20);
        play(_game, CardTarot.TRUMP_2);
        play(_game, CardTarot.HEART_3);
        play(_game, CardTarot.HEART_7);
        play(_game, CardTarot.SPADE_9);
        tr(_game);
        play(_game, CardTarot.TRUMP_19);
        play(_game, CardTarot.TRUMP_3);
        play(_game, CardTarot.HEART_2);
        play(_game, CardTarot.HEART_6);
        play(_game, CardTarot.DIAMOND_10);
        tr(_game);
        play(_game, CardTarot.TRUMP_18);
        play(_game, CardTarot.TRUMP_4);
        play(_game, CardTarot.HEART_1);
        play(_game, CardTarot.HEART_5);
        play(_game, CardTarot.DIAMOND_9);
        tr(_game);
        play(_game, CardTarot.TRUMP_17);
        play(_game, CardTarot.TRUMP_5);
        play(_game, CardTarot.SPADE_4);
        play(_game, CardTarot.SPADE_8);
        play(_game, CardTarot.CLUB_10);
        tr(_game);
        play(_game, CardTarot.TRUMP_16);
        play(_game, CardTarot.TRUMP_6);
        play(_game, CardTarot.SPADE_3);
        play(_game, CardTarot.SPADE_7);
        play(_game, CardTarot.CLUB_9);
        tr(_game);
        play(_game, CardTarot.TRUMP_15);
        play(_game, CardTarot.TRUMP_7);
        play(_game, CardTarot.SPADE_2);
        play(_game, CardTarot.SPADE_6);
        play(_game, CardTarot.CLUB_8);
        tr(_game);
        play(_game, CardTarot.TRUMP_14);
        play(_game, CardTarot.HEART_KING);
        play(_game, CardTarot.SPADE_1);
        play(_game, CardTarot.SPADE_5);
        play(_game, CardTarot.CLUB_7);
        tr(_game);
        play(_game, CardTarot.TRUMP_13);
        play(_game, CardTarot.HEART_QUEEN);
        play(_game, CardTarot.DIAMOND_4);
        play(_game, CardTarot.DIAMOND_8);
        play(_game, CardTarot.DIAMOND_JACK);
        tr(_game);
        play(_game, CardTarot.TRUMP_12);
        play(_game, CardTarot.HEART_KNIGHT);
        play(_game, CardTarot.DIAMOND_3);
        play(_game, CardTarot.DIAMOND_7);
        play(_game, CardTarot.DIAMOND_KNIGHT);
        tr(_game);
        play(_game, CardTarot.TRUMP_11);
        play(_game, CardTarot.HEART_JACK);
        play(_game, CardTarot.DIAMOND_2);
        play(_game, CardTarot.DIAMOND_6);
        play(_game, CardTarot.DIAMOND_QUEEN);
        tr(_game);
        play(_game, CardTarot.TRUMP_10);
        play(_game, CardTarot.SPADE_QUEEN);
        play(_game, CardTarot.DIAMOND_1);
        play(_game, CardTarot.DIAMOND_5);
        play(_game, CardTarot.CLUB_JACK);
        tr(_game);
        play(_game, CardTarot.TRUMP_9);
        play(_game, CardTarot.SPADE_KNIGHT);
        play(_game, CardTarot.CLUB_3);
        play(_game, CardTarot.CLUB_6);
        play(_game, CardTarot.CLUB_KNIGHT);
        tr(_game);
        play(_game, CardTarot.TRUMP_8);
        play(_game, CardTarot.SPADE_JACK);
        play(_game, CardTarot.CLUB_2);
        play(_game, CardTarot.CLUB_5);
        play(_game, CardTarot.CLUB_QUEEN);
        tr(_game);
        play(_game, CardTarot.TRUMP_1);
        play(_game, CardTarot.DIAMOND_KING);
        play(_game, CardTarot.CLUB_1);
        play(_game, CardTarot.CLUB_4);
        play(_game, CardTarot.CLUB_KING);
        tr(_game);
    }

    private static void miseres(GameTarot _game, IdList<Miseres> _miseres) {
        _game.setAnnoncesMiseres(_miseres);
    }
    private static IdList<Miseres> three(Miseres _one, Miseres _two, Miseres _three) {
        IdList<Miseres> miseres_ = new IdList<Miseres>();
        miseres_.add(_one);
        miseres_.add(_two);
        miseres_.add(_three);
        return miseres_;
    }
    private static void handful(GameTarot _game, Handfuls _hf, HandTarot _cs) {
        _game.setAnnoncesPoignees(single(_hf));
        _game.ajouterPoignee(_cs);
    }

    private static IdList<Handfuls> single(Handfuls _card) {
        IdList<Handfuls> h_ = new IdList<Handfuls>();
        h_.add(_card);
        return h_;
    }
    private static HandTarot single(CardTarot _card) {
        HandTarot h_ = new HandTarot();
        h_.ajouter(_card);
        return h_;
    }
    private static HandTarot fifteen(CardTarot _one, CardTarot _two, CardTarot _three,
                                     CardTarot _four, CardTarot _five, CardTarot _six,
                                     CardTarot _seven, CardTarot _eight, CardTarot _nine,
                                     CardTarot _ten, CardTarot _eleven, CardTarot _twelve,
                                     CardTarot _thirteen, CardTarot _fourteen, CardTarot _fifteen) {
        HandTarot h_ = new HandTarot();
        h_.ajouter(_one);
        h_.ajouter(_two);
        h_.ajouter(_three);
        h_.ajouter(_four);
        h_.ajouter(_five);
        h_.ajouter(_six);
        h_.ajouter(_seven);
        h_.ajouter(_eight);
        h_.ajouter(_nine);
        h_.ajouter(_ten);
        h_.ajouter(_eleven);
        h_.ajouter(_twelve);
        h_.ajouter(_thirteen);
        h_.ajouter(_fourteen);
        h_.ajouter(_fifteen);
        return h_;
    }

    private static void bidsFive(DealTarot _deal, GameTarot _game) {
        int first_ = _game.playerAfter(_deal.getDealer());
        _game.ajouterContrat(BidTarot.GUARD_WITHOUT);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.FOLD);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.FOLD);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.FOLD);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.FOLD);
    }

    private static void bidsFive2(DealTarot _deal, GameTarot _game) {
        int first_ = _game.playerAfter(_deal.getDealer());
        _game.ajouterContrat(BidTarot.FOLD);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.FOLD);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.GUARD_AGAINST);
//        first_ = _game.playerAfter((byte) first_);
//        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
//        first_ = _game.playerAfter((byte) first_);
//        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
    }

    private static void bidsFive3(DealTarot _deal, GameTarot _game) {
        int first_ = _game.playerAfter(_deal.getDealer());
        _game.ajouterContrat(BidTarot.FOLD);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.FOLD);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.FOLD);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.FOLD);
        first_ = _game.playerAfter((byte) first_);
        _game.ajouterContrat(BidTarot.FOLD);
    }

    private static GameTarot game3() {
        RulesTarot rules_ = new RulesTarot((byte) 4);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_1_VS_3);
        DealTarot deal_ = dealLoose((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.TAKE);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
//        game_.initDefense();

        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.SPADE_KNIGHT);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_KNIGHT);
        game_.addCurTrickDiscarded();
        play(game_, CardTarot.TRUMP_21);
        play(game_, CardTarot.TRUMP_3);
        play(game_, CardTarot.TRUMP_11);
        play(game_, CardTarot.TRUMP_15);
        tr(game_);
        play(game_, CardTarot.HEART_KING);
        play(game_, CardTarot.HEART_3);
        play(game_, CardTarot.HEART_JACK);
        play(game_, CardTarot.HEART_KNIGHT);
        tr(game_);
        play(game_, CardTarot.HEART_1);
        play(game_, CardTarot.HEART_4);
        play(game_, CardTarot.HEART_5);
        play(game_, CardTarot.HEART_7);
        tr(game_);
        play(game_, CardTarot.CLUB_6);
        play(game_, CardTarot.TRUMP_1);
        play(game_, CardTarot.CLUB_1);
        play(game_, CardTarot.CLUB_2);
        tr(game_);
        play(game_, CardTarot.HEART_2);
        play(game_, CardTarot.TRUMP_4);
        play(game_, CardTarot.HEART_6);
        play(game_, CardTarot.HEART_8);
        tr(game_);
        play(game_, CardTarot.CLUB_9);
        play(game_, CardTarot.CLUB_3);
        play(game_, CardTarot.CLUB_7);
        play(game_, CardTarot.TRUMP_2);
        tr(game_);
        play(game_, CardTarot.HEART_9);
        play(game_, CardTarot.TRUMP_5);
        play(game_, CardTarot.TRUMP_12);
        play(game_, CardTarot.EXCUSE);
        tr(game_);
        play(game_, CardTarot.DIAMOND_KING);
        play(game_, CardTarot.DIAMOND_1);
        play(game_, CardTarot.DIAMOND_9);
        play(game_, CardTarot.DIAMOND_2);
        tr(game_);
        play(game_, CardTarot.DIAMOND_JACK);
        play(game_, CardTarot.DIAMOND_4);
        play(game_, CardTarot.DIAMOND_10);
        play(game_, CardTarot.DIAMOND_7);
        tr(game_);
        play(game_, CardTarot.DIAMOND_6);
        play(game_, CardTarot.DIAMOND_5);
        play(game_, CardTarot.SPADE_9);
        play(game_, CardTarot.DIAMOND_8);
        tr(game_);
        play(game_, CardTarot.CLUB_KING);
        play(game_, CardTarot.CLUB_KNIGHT);
        play(game_, CardTarot.CLUB_8);
        play(game_, CardTarot.SPADE_10);
        tr(game_);
        play(game_, CardTarot.CLUB_JACK);
        play(game_, CardTarot.CLUB_4);
        play(game_, CardTarot.CLUB_10);
        play(game_, CardTarot.HEART_10);
        tr(game_);
        play(game_, CardTarot.SPADE_KING);
        play(game_, CardTarot.SPADE_JACK);
        play(game_, CardTarot.SPADE_8);
        play(game_, CardTarot.SPADE_1);
        tr(game_);
        play(game_, CardTarot.TRUMP_6);
        play(game_, CardTarot.TRUMP_13);
        play(game_, CardTarot.TRUMP_16);
        play(game_, CardTarot.SPADE_2);
        tr(game_);
        play(game_, CardTarot.TRUMP_20);
        play(game_, CardTarot.SPADE_3);
        play(game_, CardTarot.TRUMP_7);
        play(game_, CardTarot.TRUMP_14);
        tr(game_);
        play(game_, CardTarot.TRUMP_19);
        play(game_, CardTarot.SPADE_4);
        play(game_, CardTarot.TRUMP_8);
        play(game_, CardTarot.SPADE_7);
        tr(game_);
        play(game_, CardTarot.TRUMP_18);
        play(game_, CardTarot.SPADE_5);
        play(game_, CardTarot.TRUMP_9);
        play(game_, CardTarot.CLUB_5);
        tr(game_);
        play(game_, CardTarot.TRUMP_17);
        play(game_, CardTarot.SPADE_6);
        play(game_, CardTarot.TRUMP_10);
        play(game_, CardTarot.DIAMOND_3);
        tr(game_);
        return game_;
    }
    private static GameTarot game2() {
        RulesTarot rules_ = new RulesTarot((byte) 4);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_1_VS_3);
        DealTarot deal_ = dealLoose((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.TAKE);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
//        game_.initDefense();

        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.SPADE_KNIGHT);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_KNIGHT);
        game_.addCurTrickDiscarded();
        play(game_, CardTarot.TRUMP_21);
        play(game_, CardTarot.TRUMP_3);
        play(game_, CardTarot.TRUMP_11);
        play(game_, CardTarot.TRUMP_15);
        tr(game_);
        play(game_, CardTarot.HEART_KING);
        play(game_, CardTarot.HEART_3);
        play(game_, CardTarot.HEART_5);
        play(game_, CardTarot.HEART_7);
        tr(game_);
        play(game_, CardTarot.HEART_1);
        play(game_, CardTarot.HEART_4);
        play(game_, CardTarot.HEART_JACK);
        play(game_, CardTarot.HEART_KNIGHT);
        tr(game_);
        play(game_, CardTarot.CLUB_6);
        play(game_, CardTarot.TRUMP_1);
        play(game_, CardTarot.CLUB_1);
        play(game_, CardTarot.CLUB_2);
        tr(game_);
        play(game_, CardTarot.HEART_2);
        play(game_, CardTarot.TRUMP_4);
        play(game_, CardTarot.HEART_6);
        play(game_, CardTarot.HEART_8);
        tr(game_);
        play(game_, CardTarot.CLUB_9);
        play(game_, CardTarot.CLUB_3);
        play(game_, CardTarot.CLUB_7);
        play(game_, CardTarot.TRUMP_2);
        tr(game_);
        play(game_, CardTarot.HEART_9);
        play(game_, CardTarot.TRUMP_5);
        play(game_, CardTarot.TRUMP_12);
        play(game_, CardTarot.EXCUSE);
        tr(game_);
        play(game_, CardTarot.DIAMOND_KING);
        play(game_, CardTarot.DIAMOND_1);
        play(game_, CardTarot.DIAMOND_9);
        play(game_, CardTarot.DIAMOND_2);
        tr(game_);
        play(game_, CardTarot.DIAMOND_JACK);
        play(game_, CardTarot.DIAMOND_4);
        play(game_, CardTarot.DIAMOND_10);
        play(game_, CardTarot.DIAMOND_7);
        tr(game_);
        play(game_, CardTarot.DIAMOND_6);
        play(game_, CardTarot.DIAMOND_5);
        play(game_, CardTarot.SPADE_9);
        play(game_, CardTarot.DIAMOND_8);
        tr(game_);
        play(game_, CardTarot.CLUB_KING);
        play(game_, CardTarot.CLUB_KNIGHT);
        play(game_, CardTarot.CLUB_8);
        play(game_, CardTarot.SPADE_10);
        tr(game_);
        play(game_, CardTarot.CLUB_JACK);
        play(game_, CardTarot.CLUB_4);
        play(game_, CardTarot.CLUB_10);
        play(game_, CardTarot.HEART_10);
        tr(game_);
        play(game_, CardTarot.SPADE_KING);
        play(game_, CardTarot.SPADE_JACK);
        play(game_, CardTarot.SPADE_8);
        play(game_, CardTarot.SPADE_1);
        tr(game_);
        play(game_, CardTarot.TRUMP_6);
        play(game_, CardTarot.TRUMP_13);
        play(game_, CardTarot.TRUMP_16);
        play(game_, CardTarot.SPADE_2);
        tr(game_);
        play(game_, CardTarot.TRUMP_20);
        play(game_, CardTarot.SPADE_3);
        play(game_, CardTarot.TRUMP_7);
        play(game_, CardTarot.TRUMP_14);
        tr(game_);
        play(game_, CardTarot.TRUMP_19);
        play(game_, CardTarot.SPADE_4);
        play(game_, CardTarot.TRUMP_8);
        play(game_, CardTarot.SPADE_7);
        tr(game_);
        play(game_, CardTarot.TRUMP_18);
        play(game_, CardTarot.SPADE_5);
        play(game_, CardTarot.TRUMP_9);
        play(game_, CardTarot.CLUB_5);
        tr(game_);
        play(game_, CardTarot.TRUMP_17);
        play(game_, CardTarot.SPADE_6);
        play(game_, CardTarot.TRUMP_10);
        play(game_, CardTarot.DIAMOND_3);
        tr(game_);
        return game_;
    }
    private static GameTarot game1() {
        RulesTarot rules_ = new RulesTarot((byte) 4);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_1_VS_3);
        DealTarot deal_ = dealEq((byte) 3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.TAKE);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD);
//        game_.initDefense();

        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.SPADE_KNIGHT);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_KNIGHT);
        game_.addCurTrickDiscarded();
        play(game_, CardTarot.TRUMP_21);
        play(game_, CardTarot.TRUMP_3);
        play(game_, CardTarot.TRUMP_11);
        play(game_, CardTarot.TRUMP_15);
        tr(game_);
        play(game_, CardTarot.HEART_KING);
        play(game_, CardTarot.HEART_3);
        play(game_, CardTarot.HEART_KNIGHT);
        play(game_, CardTarot.HEART_7);
        tr(game_);
        play(game_, CardTarot.HEART_1);
        play(game_, CardTarot.HEART_4);
        play(game_, CardTarot.HEART_5);
        play(game_, CardTarot.HEART_JACK);
        tr(game_);
        play(game_, CardTarot.CLUB_6);
        play(game_, CardTarot.TRUMP_1);
        play(game_, CardTarot.CLUB_1);
        play(game_, CardTarot.CLUB_2);
        tr(game_);
        play(game_, CardTarot.HEART_2);
        play(game_, CardTarot.TRUMP_4);
        play(game_, CardTarot.HEART_6);
        play(game_, CardTarot.HEART_8);
        tr(game_);
        play(game_, CardTarot.CLUB_9);
        play(game_, CardTarot.CLUB_3);
        play(game_, CardTarot.CLUB_7);
        play(game_, CardTarot.TRUMP_2);
        tr(game_);
        play(game_, CardTarot.HEART_9);
        play(game_, CardTarot.TRUMP_5);
        play(game_, CardTarot.TRUMP_12);
        play(game_, CardTarot.EXCUSE);
        tr(game_);
        play(game_, CardTarot.DIAMOND_KING);
        play(game_, CardTarot.DIAMOND_1);
        play(game_, CardTarot.DIAMOND_9);
        play(game_, CardTarot.DIAMOND_2);
        tr(game_);
        play(game_, CardTarot.DIAMOND_JACK);
        play(game_, CardTarot.DIAMOND_4);
        play(game_, CardTarot.DIAMOND_10);
        play(game_, CardTarot.DIAMOND_7);
        tr(game_);
        play(game_, CardTarot.DIAMOND_6);
        play(game_, CardTarot.DIAMOND_5);
        play(game_, CardTarot.SPADE_9);
        play(game_, CardTarot.DIAMOND_8);
        tr(game_);
        play(game_, CardTarot.CLUB_KING);
        play(game_, CardTarot.CLUB_KNIGHT);
        play(game_, CardTarot.CLUB_8);
        play(game_, CardTarot.SPADE_10);
        tr(game_);
        play(game_, CardTarot.CLUB_JACK);
        play(game_, CardTarot.CLUB_4);
        play(game_, CardTarot.CLUB_10);
        play(game_, CardTarot.HEART_10);
        tr(game_);
        play(game_, CardTarot.SPADE_KING);
        play(game_, CardTarot.SPADE_JACK);
        play(game_, CardTarot.SPADE_8);
        play(game_, CardTarot.SPADE_1);
        tr(game_);
        play(game_, CardTarot.TRUMP_6);
        play(game_, CardTarot.TRUMP_13);
        play(game_, CardTarot.TRUMP_16);
        play(game_, CardTarot.SPADE_2);
        tr(game_);
        play(game_, CardTarot.TRUMP_20);
        play(game_, CardTarot.SPADE_3);
        play(game_, CardTarot.TRUMP_7);
        play(game_, CardTarot.TRUMP_14);
        tr(game_);
        play(game_, CardTarot.TRUMP_19);
        play(game_, CardTarot.SPADE_4);
        play(game_, CardTarot.TRUMP_8);
        play(game_, CardTarot.SPADE_7);
        tr(game_);
        play(game_, CardTarot.TRUMP_18);
        play(game_, CardTarot.SPADE_5);
        play(game_, CardTarot.TRUMP_9);
        play(game_, CardTarot.CLUB_5);
        tr(game_);
        play(game_, CardTarot.TRUMP_17);
        play(game_, CardTarot.SPADE_6);
        play(game_, CardTarot.TRUMP_10);
        play(game_, CardTarot.DIAMOND_3);
        tr(game_);
        return game_;
    }


    private static void tr(GameTarot _game) {
        _game.ajouterPetitAuBoutPliEnCours();
    }

    private static void play(GameTarot _game, CardTarot _card) {
        _game.ajouterUneCarteDansPliEnCours(_card);
    }

    private static DealTarot dealFivePlayers(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.SPADE_KING);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
    }
    private static DealTarot dealLoose(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_1);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_3);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_14);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_20);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_6);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
    }
    private static DealTarot dealEq(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_1);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_3);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_14);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_20);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_6);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
    }
}
