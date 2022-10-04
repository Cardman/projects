package cards.tarot.beans;

import cards.consts.beans.LineDealStruct;
import cards.tarot.ResultsTarot;
import cards.tarot.RulesTarot;
import code.bean.nat.*;
import code.expressionlanguage.structs.*;
import code.maths.Rate;
import code.scripts.confs.EquallableTarotBeanUtil;
import code.util.Longs;

public abstract class BeanTarotCommonTs extends EquallableTarotBeanUtil {
    public static final String EN = "en";

    public static final String RETURNE_LINE = "\n";
    public static final String SEP = ":";


    public static Struct callScoresPlayersNickname(Struct _str, long... _args) {
        return callLongs(new ScoresPlayersNickname(),_str,_args);
    }

    public static Struct callScoresPlayersRate(Struct _str, long... _args) {
        return callLongs(new ScoresPlayersRate(),_str,_args);
    }

    public static Struct callScoresPlayersScore(Struct _str, long... _args) {
        return callLongs(new ScoresPlayersScore(),_str,_args);
    }

    public static Struct callScoresPlayersSum(Struct _str, long... _args) {
        return callLongs(new ScoresPlayersSum(),_str,_args);
    }

    public static Struct callDetailsResultsTarotBeanPlayersScores(Struct _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanPlayersScores(),_str,_args);
    }

    public static Struct callDetailsResultsTarotBeanDiffAttackDefenseBonuses(Struct _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanDiffAttackDefenseBonuses(),_str,_args);
    }

    public static Struct callDetailsResultsTarotBeanAdditionnalBonusesDefense(Struct _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanAdditionnalBonusesDefense(),_str,_args);
    }

    public static Struct callDetailsResultsTarotBeanAdditionnalBonusesAttack(Struct _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanAdditionnalBonusesAttack(),_str,_args);
    }
    public static Struct callDetailsResultsTarotBeanDifferenceScoreTaker(Struct _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanDifferenceScoreTaker(),_str,_args);
    }

    public static Struct callDetailsResultsTarotBeanMultipliedTmp(Struct _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanMultipliedTmp(),_str,_args);
    }

    public static Struct callDetailsResultsTarotBeanSumPlayers(Struct _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanSumPlayers(),_str,_args);
    }

    public static Struct callDetailsResultsTarotBeanSmall(Struct _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanSmall(),_str,_args);
    }

    public static Struct callDetailsResultsTarotBeanPlayerSmall(Struct _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanPlayerSmall(),_str,_args);
    }

    public static Struct callDetailsResultsTarotBeanBasePoints(Struct _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanBasePoints(),_str,_args);
    }

    public static Struct callDetailsResultsTarotBeanRate(Struct _str, long... _args) {
        return callLongs(new DetailsResultsTarotBeanRate(),_str,_args);
    }
    public static Struct beanDetailResultsTarot(String _language, ResultsTarot _dataBase) {
        TarotStandardsDetailResults stds_ = new TarotStandardsDetailResults();
        stds_.setDataBase(_dataBase);
        return stds_.beanDetailResults(_language);
    }
    public static Struct alone() {
        return callLongs(new ResultsTarotBeanAloneTrumpAcePlayer(),NullStruct.NULL_VALUE);
    }
    public static Struct callTarotBeanGetScores(Struct _str, long... _args) {
        return callLongs(new TarotBeanGetScores(),_str,_args);
    }

    public static Struct callTarotBeanNicknames(Struct _str, long... _args) {
        return callLongs(new TarotBeanNicknames(),_str,_args);
    }
    public static Struct callTarotBeanGetNicknames(Struct _str, long... _args) {
        return callLongs(new TarotBeanGetNicknames(),_str,_args);
    }

    public static Struct callResultsTarotBeanLinesDeal(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanLinesDeal(),_str,_args);
    }

    public static Struct callResultsTarotBeanMaxDoubledDifference(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanMaxDoubledDifference(),_str,_args);
    }

    public static Struct callResultsTarotBeanMaxDifference(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanMaxDifference(),_str,_args);
    }

    public static Struct callResultsTarotBeanAdditionnalBonusesDefense(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanAdditionnalBonusesDefense(),_str,_args);
    }


    public static Struct callResultsTarotBeanAdditionnalBonusesAttack(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanAdditionnalBonusesAttack(),_str,_args);
    }

    public static Struct callResultsTarotBeanTaker(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanTaker(),_str,_args);
    }

    public static Struct callResultsTarotBeanScoreTaker(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanScoreTaker(),_str,_args);
    }

    public static Struct callResultsTarotBeanScoreTakerWithoutDeclaring(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanScoreTakerWithoutDeclaring(),_str,_args);
    }

    public static Struct callResultsTarotBeanDifferenceScoreTaker(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanDifferenceScoreTaker(),_str,_args);
    }

    public static Struct callResultsTarotBeanNeedlyScoresTaker(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanNeedlyScoresTaker(),_str,_args);
    }
    public static Struct callResultsTarotBeanNumberOudlersTaker(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanNumberOudlersTaker(),_str,_args);
    }

    public static Struct callResultsTarotBeanCalledPlayers(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanCalledPlayers(),_str,_args);
    }

    public static Struct callResultsTarotBeanCalledCardsList(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanCalledCardsList(),_str,_args);
    }

    public static Struct callResultsTarotBeanAbsoluteDiff(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanAbsoluteDiff(),_str,_args);
    }

    public static Struct callResultsTarotBeanBidString(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanBidString(),_str,_args);
    }

    public static Struct callTarotBeanPlayClassicGame(Struct _str, long... _args) {
        return callLongs(new TarotBeanPlayClassicGame(),_str,_args);
    }

    public static Struct callTarotBeanPlayVariantModeGame(Struct _str, long... _args) {
        return callLongs(new TarotBeanPlayVariantModeGame(),_str,_args);
    }

    public static Struct callResultsTarotBeanInitialUserPosition(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanInitialUserPosition(),_str,_args);
    }

    public static Struct callResultsTarotBeanFinalUserPosition(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanFinalUserPosition(),_str,_args);
    }

    public static Struct callResultsTarotBeanNoSlamDefense(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanNoSlamDefense(),_str,_args);
    }

    public static Struct callResultsTarotBeanSlamDefense(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanSlamDefense(),_str,_args);
    }


    public static Struct callResultsTarotBeanNoSlamAttack(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanNoSlamAttack(),_str,_args);
    }

    public static Struct callResultsTarotBeanFailedSlamAttack(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanFailedSlamAttack(),_str,_args);
    }

    public static Struct callResultsTarotBeanSuccessfulNoDeclaredSlamAttack(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanSuccessfulNoDeclaredSlamAttack(),_str,_args);
    }

    public static Struct callResultsTarotBeanSuccessfulDeclaredSlamAttack(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanSuccessfulDeclaredSlamAttack(),_str,_args);
    }

    public static Struct callResultsTarotBeanLoose(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanLoose(),_str,_args);
    }

    public static Struct callResultsTarotBeanWin(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanWin(),_str,_args);
    }


    public static Struct callResultsTarotBeanEquality(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanEquality(),_str,_args);
    }

    public static Struct callResultsTarotBeanFailedBid(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanFailedBid(),_str,_args);
    }

    public static Struct callResultsTarotBeanSuccessfulBid(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanSuccessfulBid(),_str,_args);
    }


    public static Struct callResultsTarotBeanMidBid(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanMidBid(),_str,_args);
    }

    public static Struct beanResultsTarot(String _language, ResultsTarot _dataBase) {
        TarotStandardsResults stds_ = new TarotStandardsResults();
        stds_.setDataBase(_dataBase);
        return stds_.beanResults(_language);
    }
    public static Struct callRulesTarotBeanRepartition(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanRepartition(),_str,_args);
    }


    public static Struct callRulesTarotBeanCartesBattues(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanCartesBattues(),_str,_args);
    }

    public static Struct callRulesTarotBeanMiseres(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanMiseres(),_str,_args);
    }


    public static Struct callRulesTarotBeanContrats(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanContrats(),_str,_args);
    }

    public static Struct callRulesTarotBeanMode(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanMode(),_str,_args);
    }

    public static Struct callRulesTarotBeanPoigneesAutorisees(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanPoigneesAutorisees(),_str,_args);
    }


    public static Struct callRulesTarotBeanFinPartieTarot(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanFinPartieTarot(),_str,_args);
    }

    public static Struct callRulesTarotBeanAllowPlayCalledSuit(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanAllowPlayCalledSuit(),_str,_args);
    }

    public static Struct callRulesTarotBeanDiscardAfterCall(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanDiscardAfterCall(),_str,_args);
    }




    public static Struct beanRules(String _language, RulesTarot _dataBase) {
        TarotStandardsRules stds_ = new TarotStandardsRules();
        stds_.setDataBaseRules(_dataBase);
        return stds_.beanRules(_language);
    }
    public static Struct callLongs(NatCaller _caller, Struct _str, long... _args) {
        return _caller.re(_str,getLongArray(_args));
    }

    public Struct displaying(Struct _b) {
        beforeDisplaying(_b);
        return _b;
    }

    public static void beforeDisplaying(Struct _bean) {
        ((BeanStruct)_bean).beforeDisplaying();
    }
    public static Struct[] getLongArray(long... _ls){
        return BeanNatCommonLgNames.getLongArray(Longs.newList(_ls)).getInstance();
    }

    public static void assertSizeEq(int _exp, Struct _result, int _index) {
        assertEq(_exp,(((LineDealStruct)((ArrayStruct)_result).get(_index)).getLineDeal().getScores().size()));
    }

    public static void assertSizeLongsEq(int _exp, Struct _result, int _index) {
        assertEq(_exp,(((ArrayStruct)((ArrayStruct)_result).get(_index)).getLength()));
    }
    public static void assertNumberEq(int _exp, Struct _result, int _index) {
        assertEq(_exp,(((LineDealStruct)((ArrayStruct)_result).get(_index)).getLineDeal().getNumber()));
    }
    public static void assertEq(long _exp, Struct _result, int _index, int _second) {
        assertEq(_exp,((LineDealStruct)(((ArrayStruct)_result).get(_index))).getLineDeal().getScores().get(_second));
    }
    public static void assertLongsEq(long _exp, Struct _result, int _index, int _second) {
        assertEq(_exp,((ArrayStruct)(((ArrayStruct)_result).get(_index))).get(_second));
    }

    public static void assertEq(String _exp, Struct _result) {
        assertEq(_exp,((StringStruct)_result).getInstance());
    }
    public static void assertEq(Rate _exp, Struct _result) {
        assertTrue(_exp.eq(((RateStruct)_result).getInstance()));
    }
    public static void assertEq(long _exp, Struct _result) {
        assertEq(_exp,(((NumberStruct)_result).longStruct()));
    }
    public static void assertTrue(Struct _value) {
        assertSame(BooleanStruct.of(true),_value);
    }
    public static void assertFalse(Struct _value) {
        assertSame(BooleanStruct.of(false),_value);
    }
    public static void assertSizeEq(int _exp, Struct _result) {
        assertEq(_exp,(((ArrayStruct)_result).getLength()));
    }
    public static void assertEq(String _exp, Struct _result, int _index) {
        assertEq(_exp,(((ArrayStruct)_result).get(_index)));
    }
    public static void assertEq(Rate _exp, Struct _result, int _index) {
        assertEq(_exp,(((ArrayStruct)_result).get(_index)));
    }
    public static void assertEq(long _exp, Struct _result, int _index) {
        assertEq(_exp,(((ArrayStruct)_result).get(_index)));
    }
    public static void assertFirstEq(String _exp, Struct _result) {
        assertEq(_exp,(((PairStruct)_result).getFirst()));
    }
    public static void assertSecondEq(long _exp, Struct _result) {
        assertEq(_exp,(((PairStruct)_result).getSecond()));
    }
    public static Struct elt(Struct _arr, int _index) {
        return ((ArrayStruct)_arr).get(_index);
    }
}
