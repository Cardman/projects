package aiki.beans.game;

import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.util.CustList;
public final class AikiBeansGameStd{
//    public static final String TYPE_DIFFICULTY_BEAN = "aiki.beans.game.DifficultyBean";
    public static final String TYPE_DIFFICULTY_COMMON_BEAN = "aiki.beans.DiCo";
    public static final String BEAN_DIFFICULTY_COMMON = "difficulty_common";
//    private static final String CHANGE = "change";
    private static final String D_WIN_POINTS_FIGHT = "winPointsFight";
    private static final String D_DIFF_WINNING_EXP_PTS_FIGHT = "diffWinningExpPtsFight";
    private static final String D_ALLOW_CATCHING_KO = "allowCatchingKo";
    private static final String D_ALLOWED_SWITCH_PLACES_END_ROUND = "allowedSwitchPlacesEndRound";
    private static final String D_WIN_TRAINER_EXP = "winTrainerExp";
    private static final String D_RATE_WINNING_EXP_PTS_FIGHT = "rateWinningExpPtsFight";
    private static final String D_END_FIGHT_IF_ONE_TEAM_KO = "endFightIfOneTeamKo";
    private static final String D_IV_PLAYER = "ivPlayer";
    private static final String D_IV_FOE = "ivFoe";
    private static final String D_RATE_WIN_MONEY_BASE = "rateWinMoneyBase";
    private static final String D_RATE_LOOSE_MONEY_WIN = "rateLooseMoneyWin";
    private static final String D_RESTORED_MOVES_END_FIGHT = "restoredMovesEndFight";
    private static final String D_ENABLED_CLOSING = "enabledClosing";
    private static final String D_RANDOM_WILD_FIGHT = "randomWildFight";
    private static final String D_STILL_POSSIBLE_FLEE = "stillPossibleFlee";
    private static final String D_SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL = "skipLearningMovesWhileNotGrowingLevel";
    private static final String D_DAMAGE_RATES = "damageRates";
    private static final String D_DAMAGE_RATE_PLAYER = "damageRatePlayer";
    private static final String D_DAMAGE_RATE_PLAYER_TABLE = "damageRatePlayerTable";
    private static final String D_DAMAGE_RATE_LAW_FOE = "damageRateLawFoe";
    private static final String D_DAMAGE_RATE_FOE_TABLE = "damageRateFoeTable";

    private AikiBeansGameStd(){}
//
//    public static void buildDifficultyBean(PokemonStandards _std){
//        CustList<StandardField> fields_=new CustList<StandardField>();
//        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
//        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.TYPE_BEAN);
//        fields_.add(new StandardField("d", BeanNatCommonLgNames.STRING, new CstNatCaller(PkScriptPages.DIFF_COMMON_HTML),null));
//        fields_.add(new StandardField("c", TYPE_DIFFICULTY_COMMON_BEAN, new DifficultyBeanComGet(),null));
//        methods_.add( new SpecNatMethod(CHANGE, BeanNatCommonLgNames.VOID, new DifficultyBeanChange()));
//        _std.getStds().addEntry(TYPE_DIFFICULTY_BEAN, type_);
//    }

    public static void buildDifficultyCommonBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.TYPE_BEAN);
        fields_.add(new StandardField(D_WIN_POINTS_FIGHT, BeanNatCommonLgNames.TYPE_MAP, new DifficultyCommonBeanWinPointsFightGet(),null));
        fields_.add(new StandardField(D_DIFF_WINNING_EXP_PTS_FIGHT,BeanNatCommonLgNames.STRING, new DifficultyCommonBeanDiffWinningExpPtsFightGet(),new DifficultyCommonBeanDiffWinningExpPtsFightSet()));
        fields_.add(new StandardField(D_ALLOW_CATCHING_KO,BeanNatCommonLgNames.PRIM_BOOLEAN, new DifficultyCommonBeanAllowCatchingKoGet(),new DifficultyCommonBeanAllowCatchingKoSet()));
        fields_.add(new StandardField(D_ALLOWED_SWITCH_PLACES_END_ROUND,BeanNatCommonLgNames.PRIM_BOOLEAN, new DifficultyCommonBeanAllowedSwitchPlacesEndRoundGet(),new DifficultyCommonBeanAllowedSwitchPlacesEndRoundSet()));
        fields_.add(new StandardField(D_WIN_TRAINER_EXP,BeanNatCommonLgNames.TYPE_RATE, new DifficultyCommonBeanWinTrainerExpGet(),new DifficultyCommonBeanWinTrainerExpSet()));
        fields_.add(new StandardField(D_RATE_WINNING_EXP_PTS_FIGHT,BeanNatCommonLgNames.TYPE_RATE, new DifficultyCommonBeanRateWinningExpPtsFightGet(),new DifficultyCommonBeanRateWinningExpPtsFightSet()));
        fields_.add(new StandardField(D_END_FIGHT_IF_ONE_TEAM_KO,BeanNatCommonLgNames.PRIM_BOOLEAN, new DifficultyCommonBeanEndFightIfOneTeamKoGet(),new DifficultyCommonBeanEndFightIfOneTeamKoSet()));
        fields_.add(new StandardField(D_IV_PLAYER, BeanNatCommonLgNames.PRIM_INTEGER, new DifficultyCommonBeanIvPlayerGet(),new DifficultyCommonBeanIvPlayerSet()));
        fields_.add(new StandardField(D_IV_FOE, BeanNatCommonLgNames.PRIM_INTEGER, new DifficultyCommonBeanIvFoeGet(),new DifficultyCommonBeanIvFoeSet()));
        fields_.add(new StandardField(D_RATE_WIN_MONEY_BASE,BeanNatCommonLgNames.TYPE_RATE, new DifficultyCommonBeanRateWinMoneyBaseGet(),new DifficultyCommonBeanRateWinMoneyBaseSet()));
        fields_.add(new StandardField(D_RATE_LOOSE_MONEY_WIN,BeanNatCommonLgNames.TYPE_RATE, new DifficultyCommonBeanRateLooseMoneyWinGet(),new DifficultyCommonBeanRateLooseMoneyWinSet()));
        fields_.add(new StandardField(D_RESTORED_MOVES_END_FIGHT,BeanNatCommonLgNames.PRIM_BOOLEAN, new DifficultyCommonBeanRestoredMovesEndFightGet(),new DifficultyCommonBeanRestoredMovesEndFightSet()));
        fields_.add(new StandardField(D_ENABLED_CLOSING,BeanNatCommonLgNames.PRIM_BOOLEAN, new DifficultyCommonBeanEnabledClosingGet(),new DifficultyCommonBeanEnabledClosingSet()));
        fields_.add(new StandardField(D_RANDOM_WILD_FIGHT,BeanNatCommonLgNames.PRIM_BOOLEAN, new DifficultyCommonBeanRandomWildFightGet(),new DifficultyCommonBeanRandomWildFightSet()));
        fields_.add(new StandardField(D_STILL_POSSIBLE_FLEE,BeanNatCommonLgNames.PRIM_BOOLEAN, new DifficultyCommonBeanStillPossibleFleeGet(),new DifficultyCommonBeanStillPossibleFleeSet()));
        fields_.add(new StandardField(D_SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL,BeanNatCommonLgNames.PRIM_BOOLEAN, new DifficultyCommonBeanSkipLearningMovesWhileNotGrowingLevelGet(),new DifficultyCommonBeanSkipLearningMovesWhileNotGrowingLevelSet()));
        fields_.add(new StandardField(D_DAMAGE_RATES, BeanNatCommonLgNames.TYPE_MAP, new DifficultyCommonBeanDamageRatesGet(),null));
        fields_.add(new StandardField(D_DAMAGE_RATE_PLAYER,BeanNatCommonLgNames.STRING, new DifficultyCommonBeanDamageRatePlayerGet(),new DifficultyCommonBeanDamageRatePlayerSet()));
        fields_.add(new StandardField(D_DAMAGE_RATE_PLAYER_TABLE, BeanNatCommonLgNames.TYPE_MAP, new DifficultyCommonBeanDamageRatePlayerTableGet(),null));
        fields_.add(new StandardField(D_DAMAGE_RATE_LAW_FOE,BeanNatCommonLgNames.STRING, new DifficultyCommonBeanDamageRateLawFoeGet(),new DifficultyCommonBeanDamageRateLawFoeSet()));
        fields_.add(new StandardField(D_DAMAGE_RATE_FOE_TABLE, BeanNatCommonLgNames.TYPE_MAP, new DifficultyCommonBeanDamageRateFoeTableGet(),null));
//        fields_.add(new StandardField("c", TYPE_DIFFICULTY_COMMON_BEAN, null,new DifficultyBeanComSet()));
        _std.getStds().addEntry(TYPE_DIFFICULTY_COMMON_BEAN, type_);
    }

}
