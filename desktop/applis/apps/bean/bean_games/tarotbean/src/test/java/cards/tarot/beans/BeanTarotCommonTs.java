package cards.tarot.beans;

import cards.consts.LineDeal;
import cards.tarot.ResultsTarot;
import cards.tarot.RulesTarot;
import code.maths.Rate;
import code.scripts.confs.EquallableTarotBeanUtil;
import code.scripts.pages.cards.MessagesTarotPage;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class BeanTarotCommonTs extends EquallableTarotBeanUtil {
    public static final String EN = StringUtil.EN;
    public static final String FR = StringUtil.FR;

    public static final String RETURNE_LINE = "\n";
    public static final String SEP = ":";

    public static long callPointsPlayerVariantGameScore(PointsPlayerVariantGame _str, int... _args) {
        return _str.getScore();
    }

    public static long callPointsPlayerVariantGameRate(PointsPlayerVariantGame _str, int... _args) {
        return _str.getRate();
    }

    public static Rate callPointsPlayerVariantGamePointsTricks(PointsPlayerVariantGame _str, int... _args) {
        return _str.getPointsTricks();
    }

    public static long callPointsPlayerVariantGameMinimumPoints(PointsPlayerVariantGame _str, int... _args) {
        return _str.getMinimumPoints();
    }

    public static Rate callPointsPlayerVariantGameDifferenceScore(PointsPlayerVariantGame _str, int... _args) {
        return _str.getDifferenceScore();
    }
    public static String callPointsPlayerVariantGameNickname(PointsPlayerVariantGame _str, int... _args) {
        return _str.getNickname();
    }
    public static CustList<PointsPlayerVariantGame> callDetailsResultsTarotBeanPointsPlayers(DetailsResultsTarotBean _str, int... _args) {
        return _str.getPointsPlayers();
    }
    public static PointsPlayerVariantGame eltPt(CustList<PointsPlayerVariantGame> _ls, int _i) {
        return _ls.get(_i);
    }
    public static String callRankingPlayerVariantGameNickname(RankingPlayerVariantGame _str, int... _args) {
        return _str.getNickname();
    }

    public static long callRankingPlayerVariantGameFinalPosition(RankingPlayerVariantGame _str, int... _args) {
        return _str.getFinalPosition();
    }

    public static long callRankingPlayerVariantGamePositionCharacters(RankingPlayerVariantGame _str, int... _args) {
        return _str.getPositionCharacters();
    }
    public static long callRankingPlayerVariantGamePositionDiff(RankingPlayerVariantGame _str, int... _args) {
        return _str.getPositionDiff();
    }

    public static long callRankingPlayerVariantGamePositionOudlers(RankingPlayerVariantGame _str, int... _args) {
        return _str.getPositionOudlers();
    }

    public static long callRankingPlayerVariantGamePositionStrengthCharacters(RankingPlayerVariantGame _str, int... _args) {
        return _str.getPositionStrengthCharacters();
    }
    public static CustList<RankingPlayerVariantGame> callDetailsResultsTarotBeanOrderedPlayers(DetailsResultsTarotBean _str, int... _args) {
        return _str.getOrderedPlayers();
    }

    public static RankingPlayerVariantGame eltRk(CustList<RankingPlayerVariantGame> _ls, int _i) {
        return _ls.get(_i);
    }

    public static StringMap<Long> callTarotSumDeclaringPlayerHandfuls(TarotSumDeclaringPlayer _str, int... _args) {
        return _str.getHandfuls();
    }

    public static StringMap<Long> callTarotSumDeclaringPlayerMiseres(TarotSumDeclaringPlayer _str, int... _args) {
        return _str.getMiseres();
    }
    public static String firstStrNb(StringMap<Long> _arr, int _index) {
        return _arr.getKey(_index);
    }
    public static long secondStrNb(StringMap<Long> _arr, int _index) {
        return _arr.getValue(_index);
    }
    public static String callTarotSumDeclaringPlayerNickname(TarotSumDeclaringPlayer _str, int... _args) {
        return _str.getNickname();
    }
    public static long callTarotSumDeclaringPlayerSum(TarotSumDeclaringPlayer _str, int... _args) {
        return _str.getSum();
    }
    public static String callTarotSumDeclaringPlayerStatus(TarotSumDeclaringPlayer _str, int... _args) {
        return _str.getStatus();
    }

    public static CustList<TarotSumDeclaringPlayer> callDetailsResultsTarotBeanLinesDeclaring(DetailsResultsTarotBean _str, int... _args) {
        return _str.getLinesDeclaring();
    }
    protected static TarotSumDeclaringPlayer eltSum(CustList<TarotSumDeclaringPlayer> _ls, int _i) {
        return _ls.get(_i);
    }

    public static long callBonusesPlayersBonus(BonusesPlayers _str, int... _args) {
        return _str.getBonus();
    }

    public static String callBonusesPlayersNickname(BonusesPlayers _str, int... _args) {
        return _str.getNickname();
    }


    public static CustList<BonusesPlayers> callDetailsResultsTarotBeanBonuses(DetailsResultsTarotBean _str, int... _args) {
        return _str.getBonuses();
    }


    public static String callScoresPlayersNickname(ScoresPlayers _str, int... _args) {
        return _str.getNickname();
    }

    public static Rate callScoresPlayersRate(ScoresPlayers _str, int... _args) {
        return _str.getRate();
    }

    public static long callScoresPlayersScore(ScoresPlayers _str, int... _args) {
        return _str.getScore();
    }

    public static Rate callScoresPlayersSum(ScoresPlayers _str, int... _args) {
        return _str.getRate();
    }

    public static CustList<ScoresPlayers> callDetailsResultsTarotBeanPlayersScores(DetailsResultsTarotBean _str, int... _args) {
        return _str.getPlayersScores();
    }
    public static ScoresPlayers eltScore(CustList<ScoresPlayers> _ls, int _i) {
        return _ls.get(_i);
    }

    public static long callDetailsResultsTarotBeanDiffAttackDefenseBonuses(DetailsResultsTarotBean _str, int... _args) {
        return _str.getDiffAttackDefenseBonuses();
    }

    public static long callDetailsResultsTarotBeanAdditionnalBonusesDefense(DetailsResultsTarotBean _str, int... _args) {
        return _str.getAdditionnalBonusesDefense();
    }

    public static long callDetailsResultsTarotBeanAdditionnalBonusesAttack(DetailsResultsTarotBean _str, int... _args) {
        return _str.getAdditionnalBonusesAttack();
    }
    public static long callDetailsResultsTarotBeanDifferenceScoreTaker(DetailsResultsTarotBean _str, int... _args) {
        return _str.getDifferenceScoreTaker();
    }

    public static long callDetailsResultsTarotBeanMultipliedTmp(DetailsResultsTarotBean _str, int... _args) {
        return _str.getMultipliedTmp();
    }

    public static long callDetailsResultsTarotBeanSumPlayers(DetailsResultsTarotBean _str, int... _args) {
        return _str.getSumPlayers();
    }

    public static String callDetailsResultsTarotBeanSmall(DetailsResultsTarotBean _str, int... _args) {
        return _str.getSmall();
    }

    public static String callDetailsResultsTarotBeanPlayerSmall(DetailsResultsTarotBean _str, int... _args) {
        return _str.getPlayerSmall();
    }

    public static long callDetailsResultsTarotBeanBasePoints(DetailsResultsTarotBean _str, int... _args) {
        return _str.getBasePoints();
    }

    public static long callDetailsResultsTarotBeanRate(DetailsResultsTarotBean _str, int... _args) {
        return _str.getRate();
    }
    public static DetailsResultsTarotBean beanDetailResultsTarot(String _language, ResultsTarot _dataBase) {
        DetailsResultsTarotBean bean_ = new DetailsResultsTarotBean();
        bean_.setBuilder(builder());
        bean_.setDataBase(_dataBase,null);
//        bean_.setLanguage(_language);
        return bean_;
    }
    public static String alone() {
        return "";
    }
    public static CustList<LineDeal> callTarotBeanGetScores(ResultsTarotBean _str, int... _args) {
        return _str.getHistory();
    }

    public static StringList callTarotBeanNicknames(ResultsTarotBean _str, int... _args) {
        return _str.getNicknames();
    }
    public static StringList callTarotBeanGetNicknames(ResultsTarotBean _str, int... _args) {
        return _str.getNicknames();
    }

    public static CustList<LineDeal> callResultsTarotBeanLinesDeal(ResultsTarotBean _str, int... _args) {
        return _str.getLinesDeal();
    }

    public static long callResultsTarotBeanMaxDoubledDifference(ResultsTarotBean _str, int... _args) {
        return _str.getMaxDoubledDifference();
    }

    public static long callResultsTarotBeanMaxDifference(ResultsTarotBean _str, int... _args) {
        return _str.getMaxDifference();
    }

    public static long callResultsTarotBeanAdditionnalBonusesDefense(ResultsTarotBean _str, int... _args) {
        return _str.getAdditionnalBonusesDefense();
    }


    public static long callResultsTarotBeanAdditionnalBonusesAttack(ResultsTarotBean _str, int... _args) {
        return _str.getAdditionnalBonusesAttack();
    }

    public static String callResultsTarotBeanTaker(ResultsTarotBean _str, int... _args) {
        return _str.getTaker();
    }

    public static long callResultsTarotBeanScoreTaker(ResultsTarotBean _str, int... _args) {
        return _str.getScoreTaker();
    }

    public static long callResultsTarotBeanScoreTakerWithoutDeclaring(ResultsTarotBean _str, int... _args) {
        return _str.getScoreTakerWithoutDeclaring();
    }

    public static long callResultsTarotBeanDifferenceScoreTaker(ResultsTarotBean _str, int... _args) {
        return _str.getTakerResult().getDifferenceScoreTaker();
    }

    public static long callResultsTarotBeanNeedlyScoresTaker(ResultsTarotBean _str, int... _args) {
        return _str.getNeedlyScoresTaker();
    }
    public static long callResultsTarotBeanNumberOudlersTaker(ResultsTarotBean _str, int... _args) {
        return _str.getNumberOudlersTaker();
    }

    public static StringList callResultsTarotBeanCalledPlayers(ResultsTarotBean _str, int... _args) {
        return _str.getCalledPlayers();
    }

    public static StringList callResultsTarotBeanCalledCardsList(ResultsTarotBean _str, int... _args) {
        return _str.getCalledCardsList();
    }

    public static long callResultsTarotBeanAbsoluteDiff(ResultsTarotBean _str, int... _args) {
        return _str.getTakerResult().absoluteDiff();
    }

    public static String callResultsTarotBeanBidString(ResultsTarotBean _str, int... _args) {
        return _str.bidString();
    }

    public static boolean callTarotBeanPlayClassicGame(ResultsTarotBean _str, int... _args) {
        return _str.playClassicGame();
    }

    public static boolean callTarotBeanPlayVariantModeGame(ResultsTarotBean _str, int... _args) {
        return _str.playVariantModeGame();
    }

    public static long callResultsTarotBeanInitialUserPosition(ResultsTarotBean _str, int... _args) {
        return _str.getInitialUserPosition();
    }

    public static long callResultsTarotBeanFinalUserPosition(ResultsTarotBean _str, int... _args) {
        return _str.getFinalUserPosition();
    }

    public static boolean callResultsTarotBeanNoSlamDefense(ResultsTarotBean _str, int... _args) {
        return _str.noSlamDefense();
    }

    public static boolean callResultsTarotBeanSlamDefense(ResultsTarotBean _str, int... _args) {
        return _str.slamDefense();
    }


    public static boolean callResultsTarotBeanNoSlamAttack(ResultsTarotBean _str, int... _args) {
        return _str.noSlamAttack();
    }

    public static boolean callResultsTarotBeanFailedSlamAttack(ResultsTarotBean _str, int... _args) {
        return _str.failedSlamAttack();
    }

    public static boolean callResultsTarotBeanSuccessfulNoDeclaredSlamAttack(ResultsTarotBean _str, int... _args) {
        return _str.successfulNoDeclaredSlamAttack();
    }

    public static boolean callResultsTarotBeanSuccessfulDeclaredSlamAttack(ResultsTarotBean _str, int... _args) {
        return _str.successfulDeclaredSlamAttack();
    }

    public static boolean callResultsTarotBeanLoose(ResultsTarotBean _str, int... _args) {
        return _str.getTakerResult().loose();
    }

    public static boolean callResultsTarotBeanWin(ResultsTarotBean _str, int... _args) {
        return _str.getTakerResult().win();
    }


    public static boolean callResultsTarotBeanEquality(ResultsTarotBean _str, int... _args) {
        return _str.getTakerResult().equality();
    }

    public static boolean callResultsTarotBeanFailedBid(ResultsTarotBean _str, int... _args) {
        return _str.getTakerResult().failedBid();
    }

    public static boolean callResultsTarotBeanSuccessfulBid(ResultsTarotBean _str, int... _args) {
        return _str.getTakerResult().successfulBid();
    }


    public static boolean callResultsTarotBeanMidBid(ResultsTarotBean _str, int... _args) {
        return _str.getTakerResult().midBid();
    }

    public static ResultsTarotBean beanResultsTarot(String _language, ResultsTarot _dataBase) {
        ResultsTarotBean bean_ = new ResultsTarotBean();
        bean_.setBuilder(builder());
        bean_.setDataBase(_dataBase,null);
//        bean_.setLanguage(_language);
        return bean_;
    }
    public static String callRulesTarotBeanRepartition(RulesTarotBean _str, int... _args) {
        return _str.getRepartition();
    }


    public static String callRulesTarotBeanCartesBattues(RulesTarotBean _str, int... _args) {
        return _str.getCartesBattues();
    }

    public static StringList callRulesTarotBeanMiseres(RulesTarotBean _str, int... _args) {
        return _str.getMiseres();
    }


    public static StringList callRulesTarotBeanContrats(RulesTarotBean _str, int... _args) {
        return _str.getContrats();
    }

    public static String callRulesTarotBeanMode(RulesTarotBean _str, int... _args) {
        return _str.getMode();
    }

    public static StringMap<Integer> callRulesTarotBeanPoigneesAutorisees(RulesTarotBean _str, int... _args) {
        return _str.getPoigneesAutorisees();
    }


    public static String callRulesTarotBeanFinPartieTarot(RulesTarotBean _str, int... _args) {
        return _str.getFinPartieTarot();
    }

    public static boolean callRulesTarotBeanAllowPlayCalledSuit(RulesTarotBean _str, int... _args) {
        return _str.isAllowPlayCalledSuit();
    }

    public static boolean callRulesTarotBeanDiscardAfterCall(RulesTarotBean _str, int... _args) {
        return _str.isDiscardAfterCall();
    }




    public static RulesTarotBean beanRules(String _language, RulesTarot _dataBase) {
        RulesTarotBean bean_ = new RulesTarotBean();
        bean_.setBuilder(builder());
        bean_.setDataBase(null,_dataBase);
//        bean_.setLanguage(_language);
        return bean_;
    }
//    public static NaSt callLongs(NatCaller _caller, ResultsTarotBean _str, long... _args) {
//        return _caller.re(_str,getLongArray(_args));
//    }

    public RulesTarotBean displaying(RulesTarotBean _b) {
        _b.build();
        return _b;
    }

    public ResultsTarotBean displayingGame(ResultsTarotBean _b) {
        _b.build();
        return _b;
    }

    public DetailsResultsTarotBean displayingDetail(DetailsResultsTarotBean _b) {
        _b.build();
        return _b;
    }

//    public static NaSt[] getLongArray(long... _ls){
//        return BeanNatCommonLgNames.getLongArray(Longs.newList(_ls)).getInstance();
//    }

//    public static void assertSizeEq(int _exp, NaSt _result, int _index) {
//        assertEq(_exp,(((LineDealStruct)((NatArrayStruct)_result).get(_index)).getLineDeal().getScores().size()));
//    }
//
//    public static void assertSizeLongsEq(int _exp, NaSt _result, int _index) {
//        assertEq(_exp,(((NatArrayStruct)((NatArrayStruct)_result).get(_index)).getLength()));
//    }
//    public static void assertNumberEq(int _exp, NaSt _result, int _index) {
//        assertEq(_exp,(((LineDealStruct)((NatArrayStruct)_result).get(_index)).getLineDeal().getNumber()));
//    }
//    public static void assertEq(long _exp, NaSt _result, int _index, int _second) {
//        assertEq(_exp,((LineDealStruct)(((NatArrayStruct)_result).get(_index))).getLineDeal().getScores().get(_second));
//    }
//    public static void assertLongsEq(long _exp, NaSt _result, int _index, int _second) {
//        assertEq(_exp,((NatArrayStruct)(((NatArrayStruct)_result).get(_index))).get(_second));
//    }

//    public static void assertEq(String _exp, NaSt _result) {
//        assertEq(_exp,((NaStSt)_result).getInstance());
//    }
//    public static void assertEq(Rate _exp, NaSt _result) {
//        assertTrue(_exp.eq(((RtSt)_result).getInstance()));
//    }
    public static void assertEq(Rate _exp, Rate _result) {
        assertTrue(_exp.eq(_result));
    }
//    public static void assertEq(long _exp, NaSt _result) {
//        assertEq(_exp,(((NaNbSt)_result).longStruct()));
//    }
//    public static void assertTrue(NaSt _value) {
//        assertSame(NaBoSt.of(true),_value);
//    }
//    public static void assertFalse(NaSt _value) {
//        assertSame(NaBoSt.of(false),_value);
//    }
//    public static void assertSizeEq(int _exp, NaSt _result) {
//        assertEq(_exp,(((NatArrayStruct)_result).getLength()));
//    }
//    public static void assertEq(String _exp, NaSt _result, int _index) {
//        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
//    }
//    public static void assertEq(Rate _exp, NaSt _result, int _index) {
//        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
//    }
//    public static void assertEq(long _exp, NaSt _result, int _index) {
//        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
//    }
//    public static void assertFirstEq(String _exp, NaSt _result) {
//        assertEq(_exp,(((PairStruct)_result).getFirst()));
//    }
//    public static void assertSecondEq(long _exp, NaSt _result) {
//        assertEq(_exp,(((PairStruct)_result).getSecond()));
//    }
    public static BonusesPlayers elt(CustList<BonusesPlayers> _arr, int _index) {
        return _arr.get(_index);
    }

    private static IntBeanBuilderHelperTarotImpl builder() {
        IntBeanBuilderHelperTarotImpl builder_ = new IntBeanBuilderHelperTarotImpl();
        Translations ts_ = new Translations();
        ts_.getMapping().addEntry(EN,new TranslationsLg());
        ts_.getMapping().addEntry(FR,new TranslationsLg());
        ts_.getMapping().getVal(EN).getMapping().addEntry(MessagesTarotPage.APP_BEAN,new TranslationsAppli());
        ts_.getMapping().getVal(FR).getMapping().addEntry(MessagesTarotPage.APP_BEAN,new TranslationsAppli());
        ts_.getMapping().getVal(EN).getMapping().getVal(MessagesTarotPage.APP_BEAN).getMapping().addEntry("",MessagesTarotPage.en());
        ts_.getMapping().getVal(FR).getMapping().getVal(MessagesTarotPage.APP_BEAN).getMapping().addEntry("",MessagesTarotPage.fr());
        builder_.setTranslations(ts_);
        return builder_;
    }
}