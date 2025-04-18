package cards.tarot.beans;

import cards.consts.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.maths.Rate;
import code.util.*;
import org.junit.Test;

public final class DetailsResultsTarotBeanTest extends BeanTarotCommonTs {

    private static final String FOLD = "fold";
    private static final String TAKE = "take";
    private static final String WITHOUT = "without";
    private static final String TAKER = "taker";
    private static final String CALLED = "called";
    private static final String DEFENDER = "defender";
    private static final String FOUR = "four";
    private static final String SUIT = "suit";
    private static final String CHAR = "char";
    private static final String LOW = "low";

    @Test
    public void rate1() {
        assertEq(0,callDetailsResultsTarotBeanRate(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game9(),0)))));
    }

    @Test
    public void rate2() {
        assertEq(1,callDetailsResultsTarotBeanRate(displayingDetail(beanDetailResultsTarot(EN,results(game3(),0)))));
    }

    @Test
    public void differenceScoreTaker() {
        assertEq(55,callDetailsResultsTarotBeanDifferenceScoreTaker(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void basePoints() {
        assertEq(25,callDetailsResultsTarotBeanBasePoints(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void small() {
        assertEq("10",callDetailsResultsTarotBeanSmall(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void plSmall() {
        assertEq("0",callDetailsResultsTarotBeanPlayerSmall(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void multipliedTmp() {
        assertEq(360,callDetailsResultsTarotBeanMultipliedTmp(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void sumPlayers() {
        assertEq(105,callDetailsResultsTarotBeanSumPlayers(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void bonusAtt() {
        assertEq(400,callDetailsResultsTarotBeanAdditionnalBonusesAttack(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void bonusDef() {
        assertEq(0,callDetailsResultsTarotBeanAdditionnalBonusesDefense(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void bonusDiff() {
        assertEq(400,callDetailsResultsTarotBeanDiffAttackDefenseBonuses(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))));
    }

    @Test
    public void playerScores() {
        assertEq(5,callDetailsResultsTarotBeanPlayersScores(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))).size());
    }

    @Test
    public void scoreNickname() {
        assertEq("0",callScoresPlayersNickname(eltScore(callDetailsResultsTarotBeanPlayersScores(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)));
    }

    @Test
    public void scoreRate() {
        assertEq(Rate.newRate("2"),callScoresPlayersRate(eltScore(callDetailsResultsTarotBeanPlayersScores(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)));
    }

    @Test
    public void scoreScore() {
        assertEq(1730,callScoresPlayersScore(eltScore(callDetailsResultsTarotBeanPlayersScores(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)));
    }

    @Test
    public void scoreSum() {
        assertEq(Rate.newRate("2"),callScoresPlayersSum(eltScore(callDetailsResultsTarotBeanPlayersScores(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)));
    }

    @Test
    public void bonusesPlayers() {
        assertEq(5,callDetailsResultsTarotBeanBonuses(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))).size());
    }

    @Test
    public void bonusNickname() {
        assertEq("0",callBonusesPlayersNickname(elt(callDetailsResultsTarotBeanBonuses(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))),0)));
    }

    @Test
    public void bonusScore() {
        assertEq(200,callBonusesPlayersBonus(elt(callDetailsResultsTarotBeanBonuses(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))),0)));
    }

    @Test
    public void linesDeclaring1() {
        assertEq(5,callDetailsResultsTarotBeanLinesDeclaring(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))).size());
    }
    @Test
    public void linesDeclaring2() {
        assertEq(5,callDetailsResultsTarotBeanLinesDeclaring(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))).size());
    }

    @Test
    public void declNickname() {
        assertEq("0",callTarotSumDeclaringPlayerNickname(eltSum(callDetailsResultsTarotBeanLinesDeclaring(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)));
    }

    @Test
    public void declSum() {
        assertEq(105,callTarotSumDeclaringPlayerSum(eltSum(callDetailsResultsTarotBeanLinesDeclaring(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)));
    }

    @Test
    public void declStatus() {
        assertEq(TAKER,callTarotSumDeclaringPlayerStatus(eltSum(callDetailsResultsTarotBeanLinesDeclaring(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)));
    }

    @Test
    public void declHand() {
        assertEq(1,callTarotSumDeclaringPlayerHandfuls(eltSum(callDetailsResultsTarotBeanLinesDeclaring(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)).size());
    }

    @Test
    public void handFirst1() {
        assertEq(FOUR, firstStrNb(callTarotSumDeclaringPlayerHandfuls(eltSum(callDetailsResultsTarotBeanLinesDeclaring(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)),0));
    }

    @Test
    public void handSecond1() {
        assertEq(50,secondStrNb(callTarotSumDeclaringPlayerHandfuls(eltSum(callDetailsResultsTarotBeanLinesDeclaring(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)),0));
    }

    @Test
    public void declMiseres() {
        assertEq(3,callTarotSumDeclaringPlayerMiseres(eltSum(callDetailsResultsTarotBeanLinesDeclaring(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)).size());
    }

    @Test
    public void misereFirst1() {
        assertEq(CHAR,firstStrNb(callTarotSumDeclaringPlayerMiseres(eltSum(callDetailsResultsTarotBeanLinesDeclaring(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)),0));
    }

    @Test
    public void misereSecond1() {
        assertEq(5,secondStrNb(callTarotSumDeclaringPlayerMiseres(eltSum(callDetailsResultsTarotBeanLinesDeclaring(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)),0));
    }

    @Test
    public void misereFirst2() {
        assertEq(SUIT,firstStrNb(callTarotSumDeclaringPlayerMiseres(eltSum(callDetailsResultsTarotBeanLinesDeclaring(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)),1));
    }

    @Test
    public void misereSecond2() {
        assertEq(30,secondStrNb(callTarotSumDeclaringPlayerMiseres(eltSum(callDetailsResultsTarotBeanLinesDeclaring(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)),1));
    }

    @Test
    public void misereFirst3() {
        assertEq(LOW,firstStrNb(callTarotSumDeclaringPlayerMiseres(eltSum(callDetailsResultsTarotBeanLinesDeclaring(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)),2));
    }

    @Test
    public void misereSecond3() {
        assertEq(20,secondStrNb(callTarotSumDeclaringPlayerMiseres(eltSum(callDetailsResultsTarotBeanLinesDeclaring(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game4(),0)))),0)),2));
    }
    @Test
    public void orderedPlayers1() {
        assertEq(5, callDetailsResultsTarotBeanOrderedPlayers(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game8(),0)))).size());
    }

    @Test
    public void orderedPlayers2() {
        assertEq(5, callDetailsResultsTarotBeanOrderedPlayers(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))).size());
    }

    @Test
    public void ordNick() {
        assertEq("0", callRankingPlayerVariantGameNickname(eltRk(callDetailsResultsTarotBeanOrderedPlayers(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))),0)));
    }

    @Test
    public void ordFinalPos() {
        assertEq(1, callRankingPlayerVariantGameFinalPosition(eltRk(callDetailsResultsTarotBeanOrderedPlayers(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))),0)));
    }

    @Test
    public void ordPosChar() {
        assertEq(1, callRankingPlayerVariantGamePositionCharacters(eltRk(callDetailsResultsTarotBeanOrderedPlayers(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))),0)));
    }

    @Test
    public void ordDiff() {
        assertEq(1, callRankingPlayerVariantGamePositionDiff(eltRk(callDetailsResultsTarotBeanOrderedPlayers(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))),0)));
    }

    @Test
    public void ordOud() {
        assertEq(1, callRankingPlayerVariantGamePositionOudlers(eltRk(callDetailsResultsTarotBeanOrderedPlayers(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))),0)));
    }

    @Test
    public void ordStr() {
        assertEq(1, callRankingPlayerVariantGamePositionStrengthCharacters(eltRk(callDetailsResultsTarotBeanOrderedPlayers(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))),0)));
    }

    @Test
    public void pointsPlayers() {
        assertEq(5, callDetailsResultsTarotBeanPointsPlayers(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))).size());
    }

    @Test
    public void ptsNick() {
        assertEq("0", callPointsPlayerVariantGameNickname(eltPt(callDetailsResultsTarotBeanPointsPlayers(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))),0)));
    }

    @Test
    public void ptsDiff() {
        assertEq(Rate.newRate("40"), callPointsPlayerVariantGameDifferenceScore(eltPt(callDetailsResultsTarotBeanPointsPlayers(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))),0)));
    }

    @Test
    public void ptsMin() {
        assertEq(41, callPointsPlayerVariantGameMinimumPoints(eltPt(callDetailsResultsTarotBeanPointsPlayers(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))),0)));
    }

    @Test
    public void ptsTrs() {
        assertEq(Rate.newRate("81"), callPointsPlayerVariantGamePointsTricks(eltPt(callDetailsResultsTarotBeanPointsPlayers(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))),0)));
    }

    @Test
    public void ptsRate() {
        assertEq(6, callPointsPlayerVariantGameRate(eltPt(callDetailsResultsTarotBeanPointsPlayers(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))),0)));
    }


    @Test
    public void ptsSc() {
        assertEq(6744, callPointsPlayerVariantGameScore(eltPt(callDetailsResultsTarotBeanPointsPlayers(displayingDetail(beanDetailResultsTarot(EN,resultsFive(game7(),0)))),0)));
    }

//    @Test
//    public void init1() {
//        StringMap<String> other_ = MessTarotPage.ms();
////        NavigationCore.adjust(other_);
//        StringMap<TranslationsAppli> mes_ = new StringMap<TranslationsAppli>();
//        mes_.addEntry(EN,MessTarotPage.enTarot());
//        mes_.addEntry(FR,MessTarotPage.frTarot());
//        TarotStandardsDetailResults stds_ = new TarotStandardsDetailResults();
//        NatNavigation nav_ = stds_.nav(new StringList(EN,FR), new DetailsTarotLoader(), PagesTarots.buildDetails(),other_,mes_);
//        nav_.setLanguage(EN);
//        stds_.setDataBase(resultsFive(game4(), 0));
//        stds_.initializeRendSessionDoc(nav_);
//        assertFalse(nav_.getHtmlText().isEmpty());
////        assertEq("<html xmlns:c=\"javahtml\"><head><title>Results</title><link href=\""+TarotScriptPages.CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
////                "\tcolor:blue;\n" +
////                "}\n" +
////                "td,caption{\n" +
////                "\tborder:1px solid black;\n" +
////                "}\n" +
////                "</style></head><body><h1>Calculation of bidding points</h1><p>Base points for bidding:25</p><p>Player who has led the trump ace to the last trick:0</p><p>Difference between taker's points and necessary points in order to win this deal:55</p><p>Rate in relationship with bidding:4</p><p>Taker's score without declaring: ( 25 + 10 + 55 ) * 4 = 360 points</p><h1>Calculation of players's declaring</h1><ul><li>0's declaring (taker):<br/><ul><li>four : 50</li><li>char : 5</li><li>suit : 30</li><li>low : 20</li><li>Sum :105</li></ul></li><li>1's declaring (called):<br/><ul><li>Sum :0</li></ul></li><li>2's declaring (defender):<br/><ul><li>Sum :0</li></ul></li><li>3's declaring (defender):<br/><ul><li>Sum :0</li></ul></li><li>4's declaring (defender):<br/><ul><li>Sum :0</li></ul></li><li>Sum of players' declaring:105</li></ul><br/><h1>Additional bonuses</h1>Bonuses for attack team:400<br/>Bonuses for defense team:0<br/>Sum of additional bonuses:400<br/><table border=\"1\"><caption>Rates and scores of this deal for each player</caption><thead><tr><td>Player</td><td>Rate</td><td>Score</td></tr></thead><tbody><tr><td>0</td><td>2</td><td>1730</td></tr><tr><td>1</td><td>1</td><td>865</td></tr><tr><td>2</td><td>-1</td><td>-865</td></tr><tr><td>3</td><td>-1</td><td>-865</td></tr><tr><td>4</td><td>-1</td><td>-865</td></tr></tbody></table></body></html>",nav_.getHtmlText());
//    }
//
//    @Test
//    public void init2() {
//        StringMap<String> other_ = MessTarotPage.ms();
////        NavigationCore.adjust(other_);
//        StringMap<TranslationsAppli> mes_ = new StringMap<TranslationsAppli>();
//        mes_.addEntry(EN,MessTarotPage.enTarot());
//        mes_.addEntry(FR,MessTarotPage.frTarot());
//        TarotStandardsDetailResults stds_ = new TarotStandardsDetailResults();
//        NatNavigation nav_ = stds_.nav(new StringList(EN,FR), new DetailsTarotLoader(), PagesTarots.buildDetails(),other_,mes_);
//        nav_.setLanguage(EN);
//        stds_.setDataBase(resultsFive(game7(), 0));
//        stds_.initializeRendSessionDoc(nav_);
//        assertFalse(nav_.getHtmlText().isEmpty());
////        assertEq("<html xmlns:c=\"javahtml\"><head><title>Results</title><link href=\""+TarotScriptPages.CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
////                "\tcolor:blue;\n" +
////                "}\n" +
////                "td,caption{\n" +
////                "\tborder:1px solid black;\n" +
////                "}\n" +
////                "</style></head><body><table border=\"1\"><caption>Steps of calculation of players' ranking by criteria</caption><thead><tr><td>Player</td><td>Ranking in relationship with the difference of points</td><td>Ranking in relationship with the number of oudlers</td><td>Ranking in relationship with the number of characters</td><td>Ranking in relationship with the strength of characters</td><td>Final rank</td></tr></thead><tbody><tr><td>0</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td></tr><tr><td>1</td><td>2</td><td>2</td><td>2</td><td>2</td><td>2</td></tr><tr><td>2</td><td>3</td><td>3</td><td>3</td><td>3</td><td>3</td></tr><tr><td>3</td><td>3</td><td>3</td><td>3</td><td>3</td><td>3</td></tr><tr><td>4</td><td>3</td><td>3</td><td>3</td><td>3</td><td>3</td></tr></tbody></table><table border=\"1\"><caption>Calculation of players' points</caption><thead><tr><td>Nickname</td><td>Won points in the tricks</td><td>Minimum score for winning</td><td>Differences of points</td><td>Rate</td><td>Score</td></tr></thead><tbody><tr><td>0</td><td>81</td><td>41</td><td>40</td><td>6</td><td>6744</td></tr><tr><td>1</td><td>9/2</td><td>51</td><td>-93/2</td><td>0</td><td>-1260</td></tr><tr><td>2</td><td>0</td><td>56</td><td>-56</td><td>-2</td><td>-1828</td></tr><tr><td>3</td><td>0</td><td>56</td><td>-56</td><td>-2</td><td>-1828</td></tr><tr><td>4</td><td>0</td><td>56</td><td>-56</td><td>-2</td><td>-1828</td></tr></tbody></table><h1>Calculation of players's declaring</h1><ul><li>0's declaring:<br/><ul><li>four</li><li>char</li><li>suit</li><li>low</li></ul></li><li>1's declaring:<br/><ul/></li><li>2's declaring:<br/><ul/></li><li>3's declaring:<br/><ul/></li><li>4's declaring:<br/><ul/></li></ul><h1>Bonuses</h1><table border=\"1\"><caption>Players' additional bonuses</caption><thead><tr><td>Nickname</td><td>Bonus</td></tr></thead><tbody><tr><td>0</td><td>200</td></tr><tr><td>1</td><td>0</td></tr><tr><td>2</td><td>0</td></tr><tr><td>3</td><td>0</td></tr><tr><td>4</td><td>0</td></tr></tbody></table></body></html>",nav_.getHtmlText());
//    }
//
//    @Test
//    public void init3() {
//        StringMap<String> other_ = MessTarotPage.ms();
////        NavigationCore.adjust(other_);
//        StringMap<TranslationsAppli> mes_ = new StringMap<TranslationsAppli>();
//        mes_.addEntry(EN,MessTarotPage.enTarot());
//        mes_.addEntry(FR,MessTarotPage.frTarot());
//        TarotStandardsDetailResults stds_ = new TarotStandardsDetailResults();
//        NatNavigation nav_ = stds_.nav(new StringList(EN,FR), new DetailsTarotLoader(), PagesTarots.buildDetails(),other_,mes_);
//        nav_.setLanguage(EN);
//        stds_.setDataBase(resultsFive(game8(), 0));
//        stds_.initializeRendSessionDoc(nav_);
//        assertFalse(nav_.getHtmlText().isEmpty());
////        assertEq("<html xmlns:c=\"javahtml\"><head><title>Results</title><link href=\""+TarotScriptPages.CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
////                "\tcolor:blue;\n" +
////                "}\n" +
////                "td,caption{\n" +
////                "\tborder:1px solid black;\n" +
////                "}\n" +
////                "</style></head><body><table border=\"1\"><caption>Steps of calculation of players' ranking by criteria</caption><thead><tr><td>Player</td><td>Ranking in relationship with the difference of points</td><td>Ranking in relationship with the number of oudlers</td><td>Ranking in relationship with the number of characters</td><td>Ranking in relationship with the strength of characters</td><td>Final rank</td></tr></thead><tbody><tr><td>0</td><td>5</td><td>5</td><td>5</td><td>5</td><td>5</td></tr><tr><td>1</td><td>4</td><td>4</td><td>4</td><td>4</td><td>4</td></tr><tr><td>2</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td></tr><tr><td>3</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td></tr><tr><td>4</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td></tr></tbody></table><table border=\"1\"><caption>Calculation of players' points</caption><thead><tr><td>Nickname</td><td>Won points in the tricks</td><td>Minimum score for winning</td><td>Differences of points</td><td>Rate</td><td>Score</td></tr></thead><tbody><tr><td>0</td><td>81</td><td>41</td><td>-40</td><td>-6</td><td>-2088</td></tr><tr><td>1</td><td>9/2</td><td>51</td><td>93/2</td><td>0</td><td>0</td></tr><tr><td>2</td><td>0</td><td>56</td><td>56</td><td>2</td><td>696</td></tr><tr><td>3</td><td>0</td><td>56</td><td>56</td><td>2</td><td>696</td></tr><tr><td>4</td><td>0</td><td>56</td><td>56</td><td>2</td><td>696</td></tr></tbody></table><h1>Calculation of players's declaring</h1><ul/><h1>Bonuses</h1><table border=\"1\"><caption>Players' additional bonuses</caption><thead><tr><td>Nickname</td><td>Bonus</td></tr></thead><tbody/></table></body></html>",nav_.getHtmlText());
//    }

    private static ResultsTarot results(GameTarot _g, int _user) {
        ResultsTarot res_ = new ResultsTarot();
        res_.setGame(_g);
        res_.getRes().setUser(_user);
        res_.initialize(fourPseudos("0","1","2","3"), new CustList<Longs>());
//        res_.getRes().setGeneral(CoreResourcesAccess.key(Suit.SPADE)+SEP+ SPADE);
        res_.getRes().setGeneral(new StringMap<String>());
        StringMap<String> e_ = new  StringMap<String>();
        e_.addEntry(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.FOLD),FOLD);
        e_.addEntry(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.TAKE),TAKE);
        e_.addEntry(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.GUARD_WITHOUT),WITHOUT);
        res_.getRes().setSpecific(e_);
//        res_.getRes().setGeneral(readCoreResource());
//        res_.getRes().setSpecific(readResource());
        return res_;
    }
    private static ResultsTarot resultsFive(GameTarot _g, int _user) {
        ResultsTarot res_ = new ResultsTarot();
        res_.setGame(_g);
        res_.getRes().setUser(_user);
        res_.initialize(fivePseudos("0","1","2","3","4"), new CustList<Longs>());
//        res_.getRes().setGeneral(CoreResourcesAccess.key(Suit.SPADE)+SEP+ SPADE);
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry(EnumCardsExporterUtil.ROLE +EnumCardsExporterUtil.fromRole(Role.TAKER),TAKER);
        s_.addEntry(EnumCardsExporterUtil.ROLE +EnumCardsExporterUtil.fromRole(Role.CALLED_PLAYER),CALLED);
        s_.addEntry(EnumCardsExporterUtil.ROLE +EnumCardsExporterUtil.fromRole(Role.DEFENDER),DEFENDER);
        res_.getRes().setGeneral(s_);
        StringMap<String> e_ = new  StringMap<String>();
        e_.addEntry(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.FOLD),FOLD);
        e_.addEntry(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.TAKE),TAKE);
        e_.addEntry(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.GUARD_WITHOUT),WITHOUT);
        e_.addEntry(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(Handfuls.FOUR),FOUR);
        e_.addEntry(TarotCardsExporterUtil.MISERES+TarotCardsExporterUtil.fromMiseres(Miseres.SUIT),SUIT);
        e_.addEntry(TarotCardsExporterUtil.MISERES+TarotCardsExporterUtil.fromMiseres(Miseres.LOW_CARDS),LOW);
        e_.addEntry(TarotCardsExporterUtil.MISERES+TarotCardsExporterUtil.fromMiseres(Miseres.CHARACTER),CHAR);
        res_.getRes().setSpecific(e_);
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
        RulesTarot rules_ = new RulesTarot(5);
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.getMiseres().add(Miseres.SUIT);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        rules_.getMiseres().add(Miseres.CHARACTER);
        rules_.getAllowedHandfuls().put(Handfuls.FOUR,15);
        DealTarot deal_ = dealFivePlayers(4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.firstLead();
        handful(game_, Handfuls.FOUR,fifteen(CardTarot.TRUMP_21,CardTarot.TRUMP_20,CardTarot.TRUMP_19,
                CardTarot.TRUMP_18,CardTarot.TRUMP_17,CardTarot.TRUMP_16,
                CardTarot.TRUMP_15,CardTarot.TRUMP_14,CardTarot.TRUMP_13,
                CardTarot.TRUMP_12,CardTarot.TRUMP_11,CardTarot.TRUMP_10,
                CardTarot.TRUMP_9,CardTarot.TRUMP_8,CardTarot.TRUMP_1));
        miseres(game_, three(Miseres.SUIT,Miseres.CHARACTER,Miseres.LOW_CARDS));
        playedCards(game_);
        //CheckerGameTarotWithRules.check(game_);
        return game_;
    }

    private static GameTarot game8() {
        RulesTarot rules_ = new RulesTarot(5);
        rules_.setMode(ModeTarot.MISERE);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        DealTarot deal_ = dealFivePlayers(4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        game_.firstLead();
        playedCards(game_);
        //CheckerGameTarotWithRules.check(game_);
        return game_;
    }

    private static GameTarot game9() {
        RulesTarot rules_ = new RulesTarot(5);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        DealTarot deal_ = dealFivePlayers(4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        bidsFive3(deal_, game_);
        //CheckerGameTarotWithRules.check(game_);
        return game_;
    }
    private static GameTarot game4() {
        RulesTarot rules_ = new RulesTarot(5);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.getMiseres().add(Miseres.SUIT);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        rules_.getMiseres().add(Miseres.CHARACTER);
        rules_.getAllowedHandfuls().put(Handfuls.FOUR,15);
        DealTarot deal_ = dealFivePlayers(4);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        bidsFive(deal_, game_);
        game_.initConfianceAppeleUtilisateur(single(CardTarot.HEART_KING));
        gererChienInconnuChelemDirect(game_);
        handful(game_, Handfuls.FOUR,fifteen(CardTarot.TRUMP_21,CardTarot.TRUMP_20,CardTarot.TRUMP_19,
                CardTarot.TRUMP_18,CardTarot.TRUMP_17,CardTarot.TRUMP_16,
                CardTarot.TRUMP_15,CardTarot.TRUMP_14,CardTarot.TRUMP_13,
                CardTarot.TRUMP_12,CardTarot.TRUMP_11,CardTarot.TRUMP_10,
                CardTarot.TRUMP_9,CardTarot.TRUMP_8,CardTarot.TRUMP_1));
        miseres(game_, three(Miseres.SUIT,Miseres.CHARACTER,Miseres.LOW_CARDS));
        playedCards(game_);
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

//    private static void playedCards2(GameTarot _game) {
//        play(_game,2, CardTarot.HEART_4);
//        play(_game,3, CardTarot.HEART_8);
//        play(_game,4, CardTarot.SPADE_10);
//        play(_game,0, CardTarot.TRUMP_21);
//        play(_game,1, CardTarot.EXCUSE);
//        secPlayedCards(_game);
//    }

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
        first_ = _game.playerAfter(first_);
        _game.ajouterContrat(BidTarot.FOLD);
        first_ = _game.playerAfter(first_);
        _game.ajouterContrat(BidTarot.FOLD);
        first_ = _game.playerAfter(first_);
        _game.ajouterContrat(BidTarot.FOLD);
        first_ = _game.playerAfter(first_);
        _game.ajouterContrat(BidTarot.FOLD);
    }
//
//    private static void bidsFive2(DealTarot _deal, GameTarot _game) {
//        int first_ = _game.playerAfter(_deal.getDealer());
//        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
//        first_ = _game.playerAfter((byte) first_);
//        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
//        first_ = _game.playerAfter((byte) first_);
//        _game.ajouterContrat(BidTarot.GUARD_AGAINST, (byte) first_);
////        first_ = _game.playerAfter((byte) first_);
////        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
////        first_ = _game.playerAfter((byte) first_);
////        _game.ajouterContrat(BidTarot.FOLD, (byte) first_);
//    }

    private static void bidsFive3(DealTarot _deal, GameTarot _game) {
        int first_ = _game.playerAfter(_deal.getDealer());
        _game.ajouterContrat(BidTarot.FOLD);
        first_ = _game.playerAfter(first_);
        _game.ajouterContrat(BidTarot.FOLD);
        first_ = _game.playerAfter(first_);
        _game.ajouterContrat(BidTarot.FOLD);
        first_ = _game.playerAfter(first_);
        _game.ajouterContrat(BidTarot.FOLD);
        first_ = _game.playerAfter(first_);
        _game.ajouterContrat(BidTarot.FOLD);
    }


    private static GameTarot game3() {
        RulesTarot rules_ = new RulesTarot(4);
        rules_.setMode(ModeTarot.NORMAL);
        rules_.setDealing(DealingTarot.DEAL_1_VS_3);
        DealTarot deal_ = dealLoose(3);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.TAKE);
        first_ = game_.playerAfter(first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter(first_);
        game_.ajouterContrat(BidTarot.FOLD);
        first_ = game_.playerAfter(first_);
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

    private static void tr(GameTarot _game) {
        _game.ajouterPetitAuBoutPliEnCours();
    }

    private static void play(GameTarot _game, CardTarot _card) {
        _game.ajouterUneCarteDansPliEnCours(_card);
    }

    private static DealTarot dealFivePlayers(int _dealer) {
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

    private static DealTarot dealLoose(int _dealer) {
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

}
