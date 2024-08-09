package cards.tarot.beans;

import cards.consts.beans.LineDealStruct;
import cards.tarot.ResultsTarot;
import cards.tarot.RulesTarot;
import code.bean.nat.*;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.confs.EquallableTarotBeanUtil;
import code.util.Longs;

public abstract class BeanTarotCommonTs extends EquallableTarotBeanUtil {
    public static final String EN = "en";
    public static final String FR = "fr";

    public static final String RETURNE_LINE = "\n";
    public static final String SEP = ":";

    public static NaSt callPointsPlayerVariantGameScore(NaSt _str, long... _args) {
        return callLongs(new PointsPlayerVariantGameScore(),_str,_args);
    }

    public static NaSt callPointsPlayerVariantGameRate(NaSt _str, long... _args) {
        return callLongs(new PointsPlayerVariantGameRate(),_str,_args);
    }

    public static NaSt callPointsPlayerVariantGamePointsTricks(NaSt _str, long... _args) {
        return callLongs(new PointsPlayerVariantGamePointsTricks(),_str,_args);
    }

    public static NaSt callPointsPlayerVariantGameMinimumPoints(NaSt _str, long... _args) {
        return callLongs(new PointsPlayerVariantGameMinimumPoints(),_str,_args);
    }

    public static NaSt callPointsPlayerVariantGameDifferenceScore(NaSt _str, long... _args) {
        return callLongs(new PointsPlayerVariantGameDifferenceScore(),_str,_args);
    }
    public static NaSt callPointsPlayerVariantGameNickname(NaSt _str, long... _args) {
        return callLongs(new PointsPlayerVariantGameNickname(),_str,_args);
    }
    public static NaSt callDetailsResultsTarotBeanPointsPlayers(NaSt _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanPointsPlayers(),_str,_args);
    }
    public static NaSt callRankingPlayerVariantGameNickname(NaSt _str, long... _args) {
        return callLongs(new RankingPlayerVariantGameNickname(),_str,_args);
    }

    public static NaSt callRankingPlayerVariantGameFinalPosition(NaSt _str, long... _args) {
        return callLongs(new RankingPlayerVariantGameFinalPosition(),_str,_args);
    }

    public static NaSt callRankingPlayerVariantGamePositionCharacters(NaSt _str, long... _args) {
        return callLongs(new RankingPlayerVariantGamePositionCharacters(),_str,_args);
    }
    public static NaSt callRankingPlayerVariantGamePositionDiff(NaSt _str, long... _args) {
        return callLongs(new RankingPlayerVariantGamePositionDiff(),_str,_args);
    }

    public static NaSt callRankingPlayerVariantGamePositionOudlers(NaSt _str, long... _args) {
        return callLongs(new RankingPlayerVariantGamePositionOudlers(),_str,_args);
    }

    public static NaSt callRankingPlayerVariantGamePositionStrengthCharacters(NaSt _str, long... _args) {
        return callLongs(new RankingPlayerVariantGamePositionStrengthCharacters(),_str,_args);
    }
    public static NaSt callDetailsResultsTarotBeanOrderedPlayers(NaSt _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanOrderedPlayers(),_str,_args);
    }


    public static NaSt callTarotSumDeclaringPlayerHandfuls(NaSt _str, long... _args) {
        return callLongs(new TarotSumDeclaringPlayerHandfuls(),_str,_args);
    }

    public static NaSt callTarotSumDeclaringPlayerMiseres(NaSt _str, long... _args) {
        return callLongs(new TarotSumDeclaringPlayerMiseres(),_str,_args);
    }

    public static NaSt callTarotSumDeclaringPlayerNickname(NaSt _str, long... _args) {
        return callLongs(new TarotSumDeclaringPlayerNickname(),_str,_args);
    }
    public static NaSt callTarotSumDeclaringPlayerSum(NaSt _str, long... _args) {
        return callLongs(new TarotSumDeclaringPlayerSum(),_str,_args);
    }
    public static NaSt callTarotSumDeclaringPlayerStatus(NaSt _str, long... _args) {
        return callLongs(new TarotSumDeclaringPlayerStatus(),_str,_args);
    }

    public static NaSt callDetailsResultsTarotBeanLinesDeclaring(NaSt _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanLinesDeclaring(),_str,_args);
    }

    public static NaSt callBonusesPlayersBonus(NaSt _str, long... _args) {
        return callLongs(new BonusesPlayersBonus(),_str,_args);
    }

    public static NaSt callBonusesPlayersNickname(NaSt _str, long... _args) {
        return callLongs(new BonusesPlayersNickname(),_str,_args);
    }


    public static NaSt callDetailsResultsTarotBeanBonuses(NaSt _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanBonuses(),_str,_args);
    }


    public static NaSt callScoresPlayersNickname(NaSt _str, long... _args) {
        return callLongs(new ScoresPlayersNickname(),_str,_args);
    }

    public static NaSt callScoresPlayersRate(NaSt _str, long... _args) {
        return callLongs(new ScoresPlayersRate(),_str,_args);
    }

    public static NaSt callScoresPlayersScore(NaSt _str, long... _args) {
        return callLongs(new ScoresPlayersScore(),_str,_args);
    }

    public static NaSt callScoresPlayersSum(NaSt _str, long... _args) {
        return callLongs(new ScoresPlayersSum(),_str,_args);
    }

    public static NaSt callDetailsResultsTarotBeanPlayersScores(NaSt _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanPlayersScores(),_str,_args);
    }

    public static NaSt callDetailsResultsTarotBeanDiffAttackDefenseBonuses(NaSt _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanDiffAttackDefenseBonuses(),_str,_args);
    }

    public static NaSt callDetailsResultsTarotBeanAdditionnalBonusesDefense(NaSt _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanAdditionnalBonusesDefense(),_str,_args);
    }

    public static NaSt callDetailsResultsTarotBeanAdditionnalBonusesAttack(NaSt _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanAdditionnalBonusesAttack(),_str,_args);
    }
    public static NaSt callDetailsResultsTarotBeanDifferenceScoreTaker(NaSt _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanDifferenceScoreTaker(),_str,_args);
    }

    public static NaSt callDetailsResultsTarotBeanMultipliedTmp(NaSt _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanMultipliedTmp(),_str,_args);
    }

    public static NaSt callDetailsResultsTarotBeanSumPlayers(NaSt _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanSumPlayers(),_str,_args);
    }

    public static NaSt callDetailsResultsTarotBeanSmall(NaSt _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanSmall(),_str,_args);
    }

    public static NaSt callDetailsResultsTarotBeanPlayerSmall(NaSt _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanPlayerSmall(),_str,_args);
    }

    public static NaSt callDetailsResultsTarotBeanBasePoints(NaSt _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanBasePoints(),_str,_args);
    }

    public static NaSt callDetailsResultsTarotBeanRate(NaSt _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanRate(),_str,_args);
    }
    public static NaSt beanDetailResultsTarot(String _language, ResultsTarot _dataBase) {
        TarotStandardsDetailResults stds_ = new TarotStandardsDetailResults();
        stds_.setDataBase(_dataBase);
        return stds_.beanDetailResults(_language);
    }
    public static NaSt alone() {
        return callLongs(new ResultsTarotBeanAloneTrumpAcePlayer(),NaNu.NULL_VALUE);
    }
    public static NaSt callTarotBeanGetScores(NaSt _str, long... _args) {
        return callLongs(new TarotBeanGetScores(),_str,_args);
    }

    public static NaSt callTarotBeanNicknames(NaSt _str, long... _args) {
        return callLongs(new TarotBeanNicknames(),_str,_args);
    }
    public static NaSt callTarotBeanGetNicknames(NaSt _str, long... _args) {
        return callLongs(new TarotBeanGetNicknames(),_str,_args);
    }

    public static NaSt callResultsTarotBeanLinesDeal(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanLinesDeal(),_str,_args);
    }

    public static NaSt callResultsTarotBeanMaxDoubledDifference(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanMaxDoubledDifference(),_str,_args);
    }

    public static NaSt callResultsTarotBeanMaxDifference(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanMaxDifference(),_str,_args);
    }

    public static NaSt callResultsTarotBeanAdditionnalBonusesDefense(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanAdditionnalBonusesDefense(),_str,_args);
    }


    public static NaSt callResultsTarotBeanAdditionnalBonusesAttack(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanAdditionnalBonusesAttack(),_str,_args);
    }

    public static NaSt callResultsTarotBeanTaker(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanTaker(),_str,_args);
    }

    public static NaSt callResultsTarotBeanScoreTaker(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanScoreTaker(),_str,_args);
    }

    public static NaSt callResultsTarotBeanScoreTakerWithoutDeclaring(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanScoreTakerWithoutDeclaring(),_str,_args);
    }

    public static NaSt callResultsTarotBeanDifferenceScoreTaker(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanDifferenceScoreTaker(),_str,_args);
    }

    public static NaSt callResultsTarotBeanNeedlyScoresTaker(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanNeedlyScoresTaker(),_str,_args);
    }
    public static NaSt callResultsTarotBeanNumberOudlersTaker(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanNumberOudlersTaker(),_str,_args);
    }

    public static NaSt callResultsTarotBeanCalledPlayers(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanCalledPlayers(),_str,_args);
    }

    public static NaSt callResultsTarotBeanCalledCardsList(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanCalledCardsList(),_str,_args);
    }

    public static NaSt callResultsTarotBeanAbsoluteDiff(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanAbsoluteDiff(),_str,_args);
    }

    public static NaSt callResultsTarotBeanBidString(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanBidString(),_str,_args);
    }

    public static NaSt callTarotBeanPlayClassicGame(NaSt _str, long... _args) {
        return callLongs(new TarotBeanPlayClassicGame(),_str,_args);
    }

    public static NaSt callTarotBeanPlayVariantModeGame(NaSt _str, long... _args) {
        return callLongs(new TarotBeanPlayVariantModeGame(),_str,_args);
    }

    public static NaSt callResultsTarotBeanInitialUserPosition(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanInitialUserPosition(),_str,_args);
    }

    public static NaSt callResultsTarotBeanFinalUserPosition(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanFinalUserPosition(),_str,_args);
    }

    public static NaSt callResultsTarotBeanNoSlamDefense(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanNoSlamDefense(),_str,_args);
    }

    public static NaSt callResultsTarotBeanSlamDefense(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanSlamDefense(),_str,_args);
    }


    public static NaSt callResultsTarotBeanNoSlamAttack(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanNoSlamAttack(),_str,_args);
    }

    public static NaSt callResultsTarotBeanFailedSlamAttack(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanFailedSlamAttack(),_str,_args);
    }

    public static NaSt callResultsTarotBeanSuccessfulNoDeclaredSlamAttack(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanSuccessfulNoDeclaredSlamAttack(),_str,_args);
    }

    public static NaSt callResultsTarotBeanSuccessfulDeclaredSlamAttack(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanSuccessfulDeclaredSlamAttack(),_str,_args);
    }

    public static NaSt callResultsTarotBeanLoose(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanLoose(),_str,_args);
    }

    public static NaSt callResultsTarotBeanWin(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanWin(),_str,_args);
    }


    public static NaSt callResultsTarotBeanEquality(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanEquality(),_str,_args);
    }

    public static NaSt callResultsTarotBeanFailedBid(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanFailedBid(),_str,_args);
    }

    public static NaSt callResultsTarotBeanSuccessfulBid(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanSuccessfulBid(),_str,_args);
    }


    public static NaSt callResultsTarotBeanMidBid(NaSt _str, long... _args) {
        return callLongs(new ResultsTarotBeanMidBid(),_str,_args);
    }

    public static NaSt beanResultsTarot(String _language, ResultsTarot _dataBase) {
        TarotStandardsResults stds_ = new TarotStandardsResults();
        stds_.setDataBase(_dataBase);
        return stds_.beanResults(_language);
    }
    public static NaSt callRulesTarotBeanRepartition(NaSt _str, long... _args) {
        return callLongs(new RulesTarotBeanRepartition(),_str,_args);
    }


    public static NaSt callRulesTarotBeanCartesBattues(NaSt _str, long... _args) {
        return callLongs(new RulesTarotBeanCartesBattues(),_str,_args);
    }

    public static NaSt callRulesTarotBeanMiseres(NaSt _str, long... _args) {
        return callLongs(new RulesTarotBeanMiseres(),_str,_args);
    }


    public static NaSt callRulesTarotBeanContrats(NaSt _str, long... _args) {
        return callLongs(new RulesTarotBeanContrats(),_str,_args);
    }

    public static NaSt callRulesTarotBeanMode(NaSt _str, long... _args) {
        return callLongs(new RulesTarotBeanMode(),_str,_args);
    }

    public static NaSt callRulesTarotBeanPoigneesAutorisees(NaSt _str, long... _args) {
        return callLongs(new RulesTarotBeanPoigneesAutorisees(),_str,_args);
    }


    public static NaSt callRulesTarotBeanFinPartieTarot(NaSt _str, long... _args) {
        return callLongs(new RulesTarotBeanFinPartieTarot(),_str,_args);
    }

    public static NaSt callRulesTarotBeanAllowPlayCalledSuit(NaSt _str, long... _args) {
        return callLongs(new RulesTarotBeanAllowPlayCalledSuit(),_str,_args);
    }

    public static NaSt callRulesTarotBeanDiscardAfterCall(NaSt _str, long... _args) {
        return callLongs(new RulesTarotBeanDiscardAfterCall(),_str,_args);
    }




    public static NaSt beanRules(String _language, RulesTarot _dataBase) {
        TarotStandardsRules stds_ = new TarotStandardsRules();
        stds_.setDataBaseRules(_dataBase);
        return stds_.beanRules(_language);
    }
    public static NaSt callLongs(NatCaller _caller, NaSt _str, long... _args) {
        return _caller.re(_str,getLongArray(_args));
    }

    public NaSt displaying(NaSt _b) {
        beforeDisplaying(_b);
        return _b;
    }

    public static void beforeDisplaying(NaSt _bean) {
        ((BeanStruct)_bean).beforeDisplaying();
    }
    public static NaSt[] getLongArray(long... _ls){
        return BeanNatCommonLgNames.getLongArray(Longs.newList(_ls)).getInstance();
    }

    public static void assertSizeEq(int _exp, NaSt _result, int _index) {
        assertEq(_exp,(((LineDealStruct)((NatArrayStruct)_result).get(_index)).getLineDeal().getScores().size()));
    }

    public static void assertSizeLongsEq(int _exp, NaSt _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)((NatArrayStruct)_result).get(_index)).getLength()));
    }
    public static void assertNumberEq(int _exp, NaSt _result, int _index) {
        assertEq(_exp,(((LineDealStruct)((NatArrayStruct)_result).get(_index)).getLineDeal().getNumber()));
    }
    public static void assertEq(long _exp, NaSt _result, int _index, int _second) {
        assertEq(_exp,((LineDealStruct)(((NatArrayStruct)_result).get(_index))).getLineDeal().getScores().get(_second));
    }
    public static void assertLongsEq(long _exp, NaSt _result, int _index, int _second) {
        assertEq(_exp,((NatArrayStruct)(((NatArrayStruct)_result).get(_index))).get(_second));
    }

    public static void assertEq(String _exp, NaSt _result) {
        assertEq(_exp,((NaStSt)_result).getInstance());
    }
    public static void assertEq(Rate _exp, NaSt _result) {
        assertTrue(_exp.eq(((RtSt)_result).getInstance()));
    }
    public static void assertEq(long _exp, NaSt _result) {
        assertEq(_exp,(((NaNbSt)_result).longStruct()));
    }
    public static void assertTrue(NaSt _value) {
        assertSame(NaBoSt.of(true),_value);
    }
    public static void assertFalse(NaSt _value) {
        assertSame(NaBoSt.of(false),_value);
    }
    public static void assertSizeEq(int _exp, NaSt _result) {
        assertEq(_exp,(((NatArrayStruct)_result).getLength()));
    }
    public static void assertEq(String _exp, NaSt _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
    }
    public static void assertEq(Rate _exp, NaSt _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
    }
    public static void assertEq(long _exp, NaSt _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
    }
    public static void assertFirstEq(String _exp, NaSt _result) {
        assertEq(_exp,(((PairStruct)_result).getFirst()));
    }
    public static void assertSecondEq(long _exp, NaSt _result) {
        assertEq(_exp,(((PairStruct)_result).getSecond()));
    }
    public static NaSt elt(NaSt _arr, int _index) {
        return ((NatArrayStruct)_arr).get(_index);
    }
}
