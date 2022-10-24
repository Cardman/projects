package aiki.beans.fight;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.CommonBean;
import aiki.beans.InitDbBean;
import aiki.beans.PkFight;
import aiki.beans.facade.fight.*;
import aiki.beans.fight.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.NameLevel;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.status.StatusSimple;
import aiki.fight.status.StatusType;
import aiki.fight.util.LevelMove;
import aiki.fight.util.ListEffectCombo;
import aiki.fight.util.ListEffectCombos;
import aiki.fight.util.TypesDuo;
import aiki.game.Game;
import aiki.game.fight.*;
import aiki.game.fight.util.MoveTarget;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.game.player.Player;
import aiki.instances.Instances;
import aiki.map.DataMap;
import aiki.map.characters.DualFight;
import aiki.map.characters.TrainerLeague;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.util.CoordsLists;
import aiki.util.LawNumber;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.scripts.confs.PkScriptPagesInit;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;

public abstract class InitDbFight extends InitDbBean {
    protected static final String SPEC = "SPEC";
    protected static final String HP_TR = "tit";
    protected static final String CRIT_TR = "uit";

    protected static final String NO_TEAM = "no_team";
    protected static final String NO_FIGHTER = "no_fighter";
    protected static final String NO_DISPLAY = "__";
    protected static final String SAMPLE_TYPE = "SAMPLE_TYPE";
    protected static final String SAMPLE_TYPE_TR = "sample_type";

    protected static final String M_ROQUE = "M_ROQUE";
    protected static final String M_ROQUE_TR = "m_roque";
    protected static final String M_TEAM_TR = "team_move";
    protected static final String M_CST_CHOICE_TR = "cst_choice";
    protected static final String M_USE_ACTION_TR = "mv_use_action";
    protected static final String M_TRACK_TR = "mv_track";
    protected static final String M_ACC_TR = "mv_acc";
    protected static final String M_COPY_TR = "m_copy";
    protected static final String M_ALLY_TR = "m_ally";
    protected static final String M_NB_FIGHTER_TR = "m_nb_fighter";
    protected static final String M_COUNTER_TR = "count_move";
    protected static final String M_SWITCH_TR = "sw_move";
    protected static final String M_PROT_TR = "protect_move";
    protected static final String M_UNPROT_TR = "unprotect_move";
    protected static final String M_END_TR = "move_end";
    protected static final String M_TEAM_SEND_TR = "team_move_send";
    protected static final String M_USE_TR = "use_move";
    protected static final String M_STACK_TR = "stack_move";
    protected static final String M_ANT_TR = "ant_move";
    protected static final String S_SIMPLE_TR = "st_simple";
    protected static final String S_RELATION_TR = "st_relation";
    protected static final String SPEC_TR = "spectre";
    protected static final String I_SAMPLE_TR = "item_cust";

    protected static final String M_TEAM = "M_TEAM";
    protected static final String M_CST_CHOICE = "M_CST_CHOICE";
    protected static final String M_USE_ACTION = "M_USE_ACTION";
    protected static final String M_TRACK = "M_TRACK";
    protected static final String M_ACC = "M_ACC";
    protected static final String M_COPY = "M_COPY";
    protected static final String M_ALLY = "M_ALLY";
    protected static final String M_NB_FIGHTER = "M_NB_FIGHTER";
    protected static final String M_COUNTER = "M_COUNTER";
    protected static final String M_SWITCH = "M_SWITCH";
    protected static final String M_PROT = "M_PROT";
    protected static final String M_UNPROT = "M_UNPROT";
    protected static final String M_END = "M_END";
    protected static final String M_TEAM_SEND = "M_TEAM_SEND";
    protected static final String M_USE = "M_USE";
    protected static final String M_STACK = "M_STACK";
    protected static final String M_ANT = "M_ANT";
    protected static final String S_SIMPLE = "S_SIMPLE";
    protected static final String S_RELATION = "S_RELATION";
    protected static final String I_SAMPLE = "I_SAMPLE";
    protected static final String PIKA_2 = "PIKACHU2";
    protected static final String PIKA_2_TR = "PIKACHU_TR2";
    protected static final String NICK_NA = "NICK_NA";
    static final String NICKNAME = "CARDTEAM";

//    private static final String FIGHT="fight";
//    private static final String TEAM="team";
//    private static final String M_CLICK_FOE="clickFoe";
//    private static final String M_CLICK_PLAYER="clickPlayer";
//    private static final String M_CLICK_FIGHTER_1="clickFighter()";
    //private static final String M_CLICK_FIGHTER_2="clickFighter(,)";

    public static Struct beanFight(String _language, FacadeGame _dataBase) {
        return beanFight(new PkFight(),_language,_dataBase);
    }

    public static Struct callFightCalculationBeanGetFighterWildFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightCalculationBeanGetFighterWildFight(),_str,_args);
    }
    public static Struct callFightCalculationBeanGetFighter(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightCalculationBeanGetFighter(),_str,_args);
    }
    public static Struct callFightCalculationBeanSortedFightersWildFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightCalculationBeanSortedFightersWildFightGet(),_str,_args);
    }

    public static Struct callFightCalculationBeanSortedFightersGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightCalculationBeanSortedFightersGet(),_str,_args);
    }
    public static Struct callFightCalculationBeanGetTargetNameAllyChoice(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightCalculationBeanGetTargetNameAllyChoice(),_str,_args);
    }

    public static Struct callFightCalculationBeanGetTargetNameAllyChoiceCondition(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightCalculationBeanGetTargetNameAllyChoiceCondition(),_str,_args);
    }
    public static Struct callFightCalculationBeanIsFoeTargetChoiceTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightCalculationBeanIsFoeTargetChoiceTeam(),_str,_args);
    }

    public static Struct callFightCalculationBeanIsFoeTargetTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightCalculationBeanIsFoeTargetTeam(),_str,_args);
    }

    public static Struct callFightCalculationBeanIsBackTargetChoiceTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightCalculationBeanIsBackTargetChoiceTeam(),_str,_args);
    }

    public static Struct callFightCalculationBeanIsBackTargetTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightCalculationBeanIsBackTargetTeam(),_str,_args);
    }

    public static Struct callFightCalculationBeanAllyChoiceGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightCalculationBeanAllyChoiceGet(),_str,_args);
    }
    public static Struct callFightCalculationBeanGetTargetNameFoeChoice(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightCalculationBeanGetTargetNameFoeChoice(),_str,_args);
    }

    public static Struct callFightCalculationBeanGetFoeFighterName(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightCalculationBeanGetFoeFighterName(),_str,_args);
    }

    public static Struct callFightCalculationBeanIsChosenTarget(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightCalculationBeanIsChosenTarget(),_str,_args);
    }

    public static Struct callFightCalculationBeanIsFoeTargetChTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightCalculationBeanIsFoeTargetChTeam(),_str,_args);
    }
    public static Struct callMoveTargetGetTarget(Struct _str, long... _args) {
        return callTargetCoordsGetPosition(BeanPokemonCommonTs.callLongs(new MoveTargetGetTarget(),_str,_args));
    }

    public static Struct callMoveTargetGetMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveTargetGetMove(),_str,_args);
    }

    public static Struct callFightCalculationBeanFoeChoicesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightCalculationBeanFoeChoicesGet(),_str,_args);
    }
    public static Struct callKeyHypothesisIsBelongsToUser(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new KeyHypothesisIsBelongsToUser(),_str,_args);
    }

    public static Struct callKeyHypothesisGetMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new KeyHypothesisGetMove(),_str,_args);
    }

    public static Struct callKeyHypothesisGetDamage(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new KeyHypothesisGetDamage(),_str,_args);
    }

    public static Struct callKeyHypothesisGetDamageSecond(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new KeyHypothesisGetDamageSecond(),_str,_args);
    }

    public static Struct callKeyHypothesisGetPlayerPokemon(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new KeyHypothesisGetPlayerPokemon(),_str,_args);
    }

    public static Struct callKeyHypothesisGetTargetPokemon(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new KeyHypothesisGetTargetPokemon(),_str,_args);
    }

    public static Struct callKeyHypothesisGetNumberPlayer(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new KeyHypothesisGetNumberPlayer(),_str,_args);
    }

    public static Struct callKeyHypothesisGetNumberTarget(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new KeyHypothesisGetNumberTarget(),_str,_args);
    }

    public static Struct callFightCalculationBeanDamageGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightCalculationBeanDamageGet(),_str,_args);
    }
    public Struct beanFightCalculation(FacadeGame _dataBase) {
        return displaying(beanFightCalculation(new PkFight(),EN,_dataBase));
    }

    public static Struct beanFightCalculation(PkFight _stds,String _language, FacadeGame _dataBase) {
        _stds.setDataBase(_dataBase);
        return _stds.beanFightCalculation(_language);
    }

    public static Struct beanFight(PkFight _stds,String _language, FacadeGame _dataBase) {
        _stds.setDataBase(_dataBase);
        return _stds.beanFight(_language);
    }

    public static Struct beanTeam(PkFight _stds,String _language, FacadeGame _dataBase) {
        _stds.setDataBase(_dataBase);
        return _stds.beanTeam(_language);
    }

    public static Struct beanFighter(PkFight _stds,String _language, FacadeGame _dataBase) {
        _stds.setDataBase(_dataBase);
        return _stds.beanFighter(_language);
    }


    protected Struct beanTeam(PkFight _pkFight,NatCaller _caller) {
        FacadeGame facade_ = facadeBigTeams(dbTeam());
        return beanTeam(_pkFight, _caller, facade_);
    }

    protected Struct beanTeam(PkFight _pkFight, NatCaller _caller, FacadeGame _facade) {
        Struct bFigtht_ = beanFight(_pkFight,EN, _facade);
        Struct bTeam_ = beanTeam(_pkFight,EN, _facade);
        transit(_pkFight, _caller,displaying(bFigtht_),bTeam_);
        return bTeam_;
    }

    public static String navigateFightPlayer(Struct _str, long... _args) {
        return navigateFight(clickPlayerCaller(),"","",_str,_args);
    }

    public static String navigateFightFoe(Struct _str, long... _args) {
        return navigateFight(clickFoeCaller(),"","",_str,_args);
    }

    public static String navigateTeamFighter(Struct _str, long... _args) {
        return navigateFight(clickTeamFighterCaller(),"","",_str,_args);
    }

    public static NatCaller clickPlayerCaller() {
        return new FightBeanClickPlayer();
    }

    public static NatCaller clickFoeCaller() {
        return new FightBeanClickFoe();
    }

    public static NatCaller clickTeamFighterCaller() {
        return new TeamBeanClickFighter();
    }
    public static String navigateFight(NatCaller _caller, String _url, String _concat, Struct _str, long... _args) {
        return navigate(_caller,_url, PkScriptPagesInit.initConfFight(new Configuration()),_concat,_str,_args);
    }

    public static Struct callFighterBeanGetStatusRelatTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanGetStatusRelatTeam(),_str,_args);
    }
    public static Struct callFighterBeanGetIncrTrappingMovesTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanGetIncrTrappingMovesTeam(),_str,_args);
    }

    public static Struct callFighterBeanGetIncrTrackingMovesTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanGetIncrTrackingMovesTeam(),_str,_args);
    }

    public static Struct callFighterBeanGetIncrPrivateMovesTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanGetIncrPrivateMovesTeam(),_str,_args);
    }

    public static Struct callFighterBeanGetIncrUserAccuracyTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanGetIncrUserAccuracyTeam(),_str,_args);
    }

    public static Struct callFighterBeanIsFoeTrappingMovesTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanIsFoeTrappingMovesTeam(),_str,_args);
    }

    public static Struct callFighterBeanIsFoeTrackingMovesTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanIsFoeTrackingMovesTeam(),_str,_args);
    }

    public static Struct callFighterBeanIsFoeStatusRelatTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanIsFoeStatusRelatTeam(),_str,_args);
    }

    public static Struct callFighterBeanIsFoePrivateMovesTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanIsFoePrivateMovesTeam(),_str,_args);
    }

    public static Struct callFighterBeanIsFoeIncrUserAccuracyTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanIsFoeIncrUserAccuracyTeam(),_str,_args);
    }
    public static Struct callStatisticInfoGetEv(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatisticInfoGetEv(),_str,_args);
    }

    public static Struct callStatisticInfoGetIv(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatisticInfoGetIv(),_str,_args);
    }

    public static Struct callStatisticInfoGetDisplayStatistic(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatisticInfoGetDisplayStatistic(),_str,_args);
    }

    public static Struct callStatisticInfoGetStatisBoost(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatisticInfoGetStatisBoost(),_str,_args);
    }

    public static Struct callStatisticInfoGetStatisBase(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatisticInfoGetStatisBase(),_str,_args);
    }

    public static Struct callStatisticInfoIsBase(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatisticInfoIsBase(),_str,_args);
    }

    public static Struct callStatisticInfoIsBoost(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatisticInfoIsBoost(),_str,_args);
    }
    public static Struct callFighterBeanStatisticsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanStatisticsGet(),_str,_args);
    }
    public static Struct callCopiedMoveGetMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new CopiedMoveGetMove(),_str,_args);
    }

    public static Struct callCopiedMoveGetPp(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new CopiedMoveGetPp(),_str,_args);
    }

    public static Struct callMultPowerMovesGetMultSuffering(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MultPowerMovesGetMultSuffering(),_str,_args);
    }

    public static Struct callMultPowerMovesGetMultInflicted(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MultPowerMovesGetMultInflicted(),_str,_args);
    }

    public static Struct callSufferedDamageCategoryGetUsing(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SufferedDamageCategoryGetUsing(),_str,_args);
    }

    public static Struct callSufferedDamageCategoryGetRound(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SufferedDamageCategoryGetRound(),_str,_args);
    }

    public static Struct callFighterBeanDamageRateByTypeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanDamageRateByTypeGet(),_str,_args);
    }
    public static Struct callFighterBeanCopiedMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanCopiedMovesGet(),_str,_args);
    }
    public static Struct callFighterBeanDamageSufferedCategGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanDamageSufferedCategGet(),_str,_args);
    }

    public static Struct callAffectedMoveGetActivity(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AffectedMoveGetActivity(),_str,_args);
    }

    public static Struct callAffectedMoveGetMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AffectedMoveGetMove(),_str,_args);
    }
    public static Struct callFighterBeanTrckingMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanTrackingMovesGet(),_str,_args);
    }
    public static Struct callFighterBeanTrappingMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanTrappingMovesGet(),_str,_args);
    }

    public static Struct callFighterBeanPrivateMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanPrivateMovesGet(),_str,_args);
    }
    public static Struct callFighterBeanStatusRelatGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanStatusRelatGet(),_str,_args);
    }
    public static Struct callFighterBeanEnabledMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanEnabledMovesGet(),_str,_args);
    }
    public static Struct callFighterBeanStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanStatusGet(),_str,_args);
    }
    public static Struct callFighterBeanNbUsesMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanNbUsesMovesGet(),_str,_args);
    }

    public static Struct callFighterBeanMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanMovesGet(),_str,_args);
    }

    public static Struct callFighterBeanCurrentMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanCurrentMovesGet(),_str,_args);
    }
    public static Struct callFighterBeanLastSufferedMoveTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanLastSufferedMoveTypesGet(),_str,_args);
    }

    public static Struct callFighterBeanProtectedAgainstMoveTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanProtectedAgainstMoveTypesGet(),_str,_args);
    }

    public static Struct callFighterBeanTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanTypesGet(),_str,_args);
    }

    public static Struct callFighterBeanAlreadyInvokedMovesRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanAlreadyInvokedMovesRoundGet(),_str,_args);
    }
    public static Struct callFighterBeanWonExpSinceLastLevelGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanWonExpSinceLastLevelGet(),_str,_args);
    }

    public static Struct callFighterBeanNecessaryPointsNextLevelGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanNecessaryPointsNextLevelGet(),_str,_args);
    }
    public static Struct callFighterFighterBeanHappinessGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanHappinessGet(),_str,_args);
    }

    public static Struct callFighterBeanCloneStrGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanCloneStrGet(),_str,_args);
    }

    public static Struct callFighterBeanCloneGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanCloneGet(),_str,_args);
    }

    public static Struct callFighterBeanHeightStrGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanHeightStrGet(),_str,_args);
    }

    public static Struct callFighterBeanHeightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanHeightGet(),_str,_args);
    }

    public static Struct callFighterBeanWeightStrGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanWeightStrGet(),_str,_args);
    }

    public static Struct callFighterBeanWeightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanWeightGet(),_str,_args);
    }

    public static Struct callFighterBeanRemainingHpStrGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanRemainingHpStrGet(),_str,_args);
    }

    public static Struct callFighterBeanRemainingHpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanRemainingHpGet(),_str,_args);
    }

    public static Struct callFighterBeanRemainingHpStrPerCentGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanRemainingHpStrPerCentGet(),_str,_args);
    }
    public static Struct callFighterBeanLevelGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanLevelGet(),_str,_args);
    }

    public static Struct callFighterBeanNbPrepaRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanNbPrepaRoundGet(),_str,_args);
    }

    public static Struct callFighterBeanNbRoundsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanNbRoundsGet(),_str,_args);
    }


    public static Struct callFighterBeanNbRepeatingSuccessfulMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanNbRepeatingSuccessfulMovesGet(),_str,_args);
    }

    public static Struct callFighterBeanLastUsedMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanLastUsedMoveGet(),_str,_args);
    }

    public static Struct callFighterBeanUsedMoveLastRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanUsedMoveLastRoundGet(),_str,_args);
    }

    public static Struct callFighterBeanLastSufferedMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanLastSufferedMoveGet(),_str,_args);
    }
    public static Struct callFighterBeanLastSuccessfulMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanLastSuccessfulMoveGet(),_str,_args);
    }
    public static Struct callFighterBeanLastUsedItemGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanLastUsedItemGet(),_str,_args);
    }

    public static Struct callFighterBeanExpItemGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanExpItemGet(),_str,_args);
    }

    public static Struct callFighterBeanItemGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanItemGet(),_str,_args);
    }

    public static Struct callFighterBeanAbilityGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanAbilityGet(),_str,_args);
    }

    public static Struct callFighterBeanCurrentAbilityGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanCurrentAbilityGet(),_str,_args);
    }

    public static Struct callFighterBeanCurrentNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanCurrentNameGet(),_str,_args);
    }

    public static Struct callFighterBeanCurrentGenderGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanCurrentGenderGet(),_str,_args);
    }

    public static Struct callFighterBeanGenderGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanGenderGet(),_str,_args);
    }

    public static Struct callFighterBeanGroundPlaceSubstGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanGroundPlaceSubstGet(),_str,_args);
    }

    public static Struct callFighterBeanGroundPlaceGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanGroundPlaceGet(),_str,_args);
    }

    public static Struct callFighterBeanUsedBallCatchingGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanUsedBallCatchingGet(),_str,_args);
    }

    public static Struct callFighterBeanNicknameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanNicknameGet(),_str,_args);
    }
    public static Struct callMoveTeamPositionGetMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveTeamPositionGetMove(),_str,_args);
    }
    public static Struct callFighterBeanIncrUserAccuracyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanIncrUserAccuracyGet(),_str,_args);
    }
    public static Struct callFighterBeanEnabledMovesForAllyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanEnabledMovesForAllyGet(),_str,_args);
    }
    public static Struct callFighterBeanBelongingToPlayerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanBelongingToPlayerGet(),_str,_args);
    }
    public static Struct callFighterBeanIsBackSubst(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanIsBackSubst(),_str,_args);
    }
    public static Struct callFighterBeanIsBack(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanIsBack(),_str,_args);
    }
    public static Struct callFighterBeanIsEnabled(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanIsEnabled(),_str,_args);
    }

    public static Struct callFighterBeanChangedGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanChangedGet(),_str,_args);
    }

    public static Struct callFighterBeanActedGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanActedGet(),_str,_args);
    }

    public static Struct callFighterBeanSuccessfulMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanSuccessfulMoveGet(),_str,_args);
    }

    public static Struct callFighterBeanUsingItemGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanUsingItemGet(),_str,_args);
    }

    public static Struct callFighterBeanDisappearedGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanDisappearedGet(),_str,_args);
    }

    public static Struct callFighterBeanNeedingToRechargeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanNeedingToRechargeGet(),_str,_args);
    }
    public static Struct callFighterBeanNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FighterBeanNameGet(),_str,_args);
    }
    public static Struct callTeamBeanPlayerFightersAgainstFoeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TeamBeanPlayerFightersAgainstFoeGet(),_str,_args);
    }

    public static Struct callTeamBeanGetPlayerFigtherAgainstFoe(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TeamBeanGetPlayerFigtherAgainstFoe(),_str,_args);
    }

    public static Struct callTeamBeanGetFoeFigtherAgainstFoe(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TeamBeanGetFoeFigtherAgainstFoe(),_str,_args);
    }

    public static Struct callTeamBeanEnabledMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TeamBeanEnabledMovesGet(),_str,_args);
    }

    public static Struct callTeamBeanEnabledMovesByGroupGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TeamBeanEnabledMovesByGroupGet(),_str,_args);
    }

    public static Struct callTeamBeanGetKey(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TeamBeanGetKey(),_str,_args);
    }

    public static Struct callStacksOfUsesGetNbRounds(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StacksOfUsesGetNbRounds(),_str,_args);
    }

    public static Struct callStacksOfUsesIsFirstStacked(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StacksOfUsesIsFirstStacked(),_str,_args);
    }

    public static Struct callStacksOfUsesIsLastStacked(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StacksOfUsesIsLastStacked(),_str,_args);
    }

    public static Struct callAnticipationGetTargetPositionValue(Struct _str, long... _args) {
        return callTargetCoordsGetPosition(BeanPokemonCommonTs.callLongs(new AnticipationGetTargetPosition(),_str,_args));
    }

    public static Struct callTargetCoordsGetPosition(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TargetCoordsGetPosition(),_str,_args);
    }

    public static Struct callAnticipationGetDamage(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AnticipationGetDamage(),_str,_args);
    }

    public static Struct callAnticipationGetNbRounds(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AnticipationGetNbRounds(),_str,_args);
    }

    public static Struct callAnticipationIsIncrementing(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AnticipationIsIncrementing(),_str,_args);
    }

    public static Struct callTeamBeanMovesAnticipationGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TeamBeanMovesAnticipationGet(),_str,_args);
    }

    public static Struct callTeamBeanIsBackMovesAnticipationTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TeamBeanIsBackMovesAnticipationTeam(),_str,_args);
    }

    public static Struct callTeamBeanIsFoeMovesAnticipationTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TeamBeanIsFoeMovesAnticipationTeam(),_str,_args);
    }

    public static Struct callTeamBeanIsPlayerMovesAnticipationTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TeamBeanIsPlayerMovesAnticipationTeam(),_str,_args);
    }
    public static Struct callTeamBeanTeamBeanHealAfterGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TeamBeanHealAfterGet(),_str,_args);
    }

    public static Struct callTeamBeanEnabledMovesWhileSendingFoeUsesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TeamBeanEnabledMovesWhileSendingFoeUsesGet(),_str,_args);
    }

    public static Struct callTeamBeanNbUsesMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TeamBeanNbUsesMovesGet(),_str,_args);
    }
    public static Struct callTeamBeanGetTrPokemonLink(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TeamBeanGetTrPokemonLink(),_str,_args);
    }

    public static Struct callTeamBeanGetMembers(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TeamBeanGetMembers(),_str,_args);
    }
    public static Struct callTeamBeanFoeTeamGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TeamBeanFoeTeamGet(),_str,_args);
    }

    public static Struct callFightBeanMultGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightBeanMultGet(),_str,_args);
    }

    public static Struct callFightBeanEnabledMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightBeanEnabledMovesGet(),_str,_args);
    }

    public static Struct callFightBeanIsStillEnabled(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightBeanIsStillEnabled(),_str,_args);
    }

    public static Struct callFightBeanWinningMoneyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightBeanWinningMoneyGet(),_str,_args);
    }

    public static Struct callFightBeanNbRoundsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightBeanNbRoundsGet(),_str,_args);
    }

    public static Struct callFightBeanNbFleeAttemptGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FightBeanNbFleeAttemptGet(),_str,_args);
    }

    public static Struct callActivityOfMoveGetNbTurn(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ActivityOfMoveGetNbTurn(),_str,_args);
    }

    public static Struct callActivityOfMoveIsEnabled(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ActivityOfMoveIsEnabled(),_str,_args);
    }

    public static Struct callActivityOfMoveIsIncrementCount(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ActivityOfMoveIsIncrementCount(),_str,_args);
    }

    protected DataBase dbFighter() {
        DataBase data_ = dbBase();
        secondPk(data_);
        DamagingMoveData damage_ = Instances.newDamagingMoveData();
        damage_.setCategory(SPEC);
        EffectDamage effDam_ = Instances.newEffectDamage();
        effDam_.setPower(DataBase.VAR_PREFIX+Fighter.LANCEUR_NB_UTILISATION+DataBase.SEP_BETWEEN_KEYS+ M_NB_FIGHTER);
        damage_.getEffects().add(effDam_);
        data_.completeMembers(M_NB_FIGHTER, damage_);
        StatusMoveData mrestrdef_ = Instances.newStatusMoveData();
        mrestrdef_.getEffects().add(Instances.newEffectRestriction());
        data_.completeMembers(M_TEAM, mrestrdef_);
        StatusMoveData mrestrcst_ = Instances.newStatusMoveData();
        EffectRestriction rcst_ = Instances.newEffectRestriction();
        rcst_.setChoiceRestriction(MoveChoiceRestrictionType.LANCEUR_ATTAQUES);
        mrestrcst_.getEffects().add(rcst_);
        mrestrcst_.setConstUserChoice(true);
        mrestrcst_.getRepeatRoundLaw().addQuickEvent(Rate.one(), LgInt.one());
        data_.completeMembers(M_CST_CHOICE, mrestrcst_);
        StatusMoveData mrestract_ = Instances.newStatusMoveData();
        EffectRestriction ract_ = Instances.newEffectRestriction();
        ract_.setChoiceRestriction(MoveChoiceRestrictionType.FORCE);
        mrestract_.getEffects().add(ract_);
        data_.completeMembers(M_USE_ACTION, mrestract_);
        StatusMoveData mcounter_ = Instances.newStatusMoveData();
        mcounter_.getEffects().add(Instances.newEffectCounterAttack());
        data_.completeMembers(M_COUNTER, mcounter_);
        StatusMoveData msw_ = Instances.newStatusMoveData();
        msw_.getEffects().add(Instances.newEffectSwitchMoveTypes());
        data_.completeMembers(M_SWITCH, msw_);
        StatusMoveData mun_ = Instances.newStatusMoveData();
        mun_.getEffects().add(Instances.newEffectUnprotectFromTypes());
        data_.completeMembers(M_UNPROT, mun_);
        StatusMoveData mpr_ = Instances.newStatusMoveData();
        mpr_.getEffects().add(Instances.newEffectProtectFromTypes());
        data_.completeMembers(M_PROT, mpr_);
        StatusMoveData mi_ = Instances.newStatusMoveData();
        mi_.getEffects().add(Instances.newEffectEndRoundIndividual());
        data_.completeMembers(M_END, mi_);
        StatusMoveData mtrack_ = Instances.newStatusMoveData();
        mtrack_.getEffects().add(Instances.newEffectEndRoundSingleRelation());
        data_.completeMembers(M_TRACK, mtrack_);
        StatusMoveData mac_ = Instances.newStatusMoveData();
        mac_.getEffects().add(Instances.newEffectAccuracy());
        data_.completeMembers(M_ACC, mac_);
        StatusMoveData mcopy_ = Instances.newStatusMoveData();
        EffectCopyMove efcp_ = Instances.newEffectCopyMove();
        efcp_.setCopyingMoveForUser((short) 1);
        mcopy_.getEffects().add(efcp_);
        data_.completeMembers(M_COPY, mcopy_);
        StatusMoveData mally_ = Instances.newStatusMoveData();
        mally_.getEffects().add(Instances.newEffectAlly());
        data_.completeMembers(M_ALLY, mally_);
        StatusSimple simple_ = Instances.newStatusSimple();
        simple_.setStatusType(StatusType.INDIVIDUEL);
        data_.completeMembers(S_SIMPLE,simple_);
        StatusSimple relation_ = Instances.newStatusSimple();
        relation_.setStatusType(StatusType.RELATION_UNIQUE);
        data_.completeMembers(S_RELATION,relation_);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_NB_FIGHTER, M_NB_FIGHTER_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_TEAM, M_TEAM_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_CST_CHOICE, M_CST_CHOICE_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_USE_ACTION, M_USE_ACTION_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_COUNTER, M_COUNTER_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_SWITCH, M_SWITCH_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_UNPROT, M_UNPROT_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_PROT, M_PROT_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_END, M_END_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_TRACK, M_TRACK_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_ACC, M_ACC_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_COPY, M_COPY_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_ALLY, M_ALLY_TR);
        data_.getTranslatedTypes().getVal(LANGUAGE).addEntry(NULL_REF, NO_DISPLAY);
        data_.getTranslatedTypes().getVal(LANGUAGE).addEntry(SAMPLE_TYPE, SAMPLE_TYPE_TR);
        data_.getTranslatedTypes().getVal(LANGUAGE).addEntry(ELECTRICK, ELECTRICK_TR);
        data_.getTranslatedItems().getVal(LANGUAGE).addEntry(I_SAMPLE, I_SAMPLE_TR);
        data_.getTranslatedStatus().getVal(LANGUAGE).addEntry(S_SIMPLE, S_SIMPLE_TR);
        data_.getTranslatedStatus().getVal(LANGUAGE).addEntry(S_RELATION, S_RELATION_TR);
        data_.getTranslatedCategories().getVal(LANGUAGE).clear();
        data_.getTranslatedCategories().getVal(LANGUAGE).addEntry(SPEC, SPEC_TR);
        data_.getTranslatedStatistics().getVal(LANGUAGE).set(Statistic.HP, HP_TR);
        data_.getTranslatedStatistics().getVal(LANGUAGE).set(Statistic.CRITICAL_HIT, CRIT_TR);
        data_.getTypes().add(NULL_REF);
        data_.getTypes().add(SAMPLE_TYPE);
        data_.getTypes().add(ELECTRICK);
        data_.completeVariables();
        return data_;
    }
    protected DataBase dbTeam() {
        DataBase data_ = dbBase();
        secondPk(data_);
        StatusMoveData mteam_ = Instances.newStatusMoveData();
        mteam_.getEffects().add(Instances.newEffectTeam());
        data_.completeMembers(M_TEAM, mteam_);
        StatusMoveData send_ = Instances.newStatusMoveData();
        send_.getEffects().add(Instances.newEffectTeamWhileSendFoe());
        data_.completeMembers(M_TEAM_SEND, send_);
        DamagingMoveData used_ = Instances.newDamagingMoveData();
        used_.setCategory(SPEC);
        EffectDamage dam_ = Instances.newEffectDamage();
        dam_.setPower(DataBase.VAR_PREFIX+Team.EQUIPE_NB_UTILISATION+DataBase.SEP_BETWEEN_KEYS+M_USE);
        used_.getEffects().add(dam_);
        data_.completeMembers(M_USE, used_);
        StatusMoveData heal_ = Instances.newStatusMoveData();
        heal_.getEffects().add(Instances.newEffectEndRoundPositionRelation());
        data_.completeMembers(M_STACK,heal_);
        StatusMoveData ant_ = Instances.newStatusMoveData();
        ant_.getEffects().add(Instances.newEffectEndRoundPositionTargetRelation());
        data_.completeMembers(M_ANT,ant_);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_TEAM, M_TEAM_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_TEAM_SEND, M_TEAM_SEND_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_USE, M_USE_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_STACK, M_STACK_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_ANT, M_ANT_TR);
        data_.getCombos().getEffects().add(new ListEffectCombo(new StringList(M_TEAM,M_TEAM_SEND),Instances.newEffectCombo()));
        data_.completeVariables();
        return data_;
    }

    private void secondPk(DataBase _data) {
        PokemonData pkData_ = Instances.newPokemonData();
        pkData_.setBaseEvo(PIKA_2);
        pkData_.setEggGroups(new StringList(_data.getDefaultEggGroup()));
        pkData_.setTypes(new StringList(ELECTRICK));
        statBase(pkData_);
        pkData_.getLevMoves().add(new LevelMove((short)1,ECLAIR));
        pkData_.getLevMoves().add(new LevelMove((short)1,CHARGE));
        pkData_.setExpRate(1);
        pkData_.setHeight(Rate.one());
        pkData_.setWeight(Rate.one());
        pkData_.setCatchingRate((short) 1);
        pkData_.setHappiness((short) 1);
        pkData_.setHappinessHatch((short) 1);
        pkData_.setAbilities(new StringList(PARATONNERRE));
        _data.completeMembers(PIKA_2,pkData_);
        StringMap<String> trPks_ = new StringMap<String>();
        trPks_.addEntry(PIKACHU,PIKACHU_TR);
        trPks_.addEntry(PIKA_2, PIKA_2_TR);
        _data.getTranslatedPokemon().clear();
        _data.getTranslatedPokemon().addEntry(LANGUAGE,trPks_);
    }

    protected DataBase db() {
        DataBase data_ = dbBase();
        data_.completeVariables();
        return data_;
    }

    protected DataBase dbBaseCalcRoque() {
        DataBase data_ = dbBaseCalcCom();
        StatusMoveData al_ = Instances.newStatusMoveData();
        al_.setPp((short) 20);
        EffectSwitchPosition e_ = Instances.newEffectSwitchPosition();
        e_.setTargetChoice(TargetChoice.ALLIE);
        al_.setTargetChoice(TargetChoice.ALLIE);
        al_.getEffects().add(e_);
        data_.completeMembers(M_ROQUE, al_);
        data_.getTranslatedMoves().getVal(EN).addEntry(M_ROQUE, M_ROQUE_TR);
        data_.completeVariables();
        return data_;
    }
    protected DataBase dbBaseCalc() {
        DataBase data_ = dbBaseCalcCom();
        data_.completeVariables();
        return data_;
    }

    private DataBase dbBaseCalcCom() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        data_.completeMembers(ECLAIR, damMoveAcc(TargetChoice.ANY_FOE, "40"));
        data_.completeMembers(CHARGE, damMoveAcc(TargetChoice.TOUS_ADV, "20"));
        data_.completeMembers(M_TEAM, damMoveAcc(TargetChoice.ADJ_ADV, "15"));
        data_.completeMembers(CHARGE2, damMoveAcc(TargetChoice.TOUS_ADV, "25"));
        data_.getTypes().add(ELECTRICK);
        data_.completeMembers(PIKACHU,pkData(data_, PIKACHU));
        data_.completeMembers(PIKA_2,pkData(data_, PIKA_2));
        data_.completeMembers(PARATONNERRE,Instances.newAbilityData());
        data_.getTableTypes().addEntry(new TypesDuo(ELECTRICK,ELECTRICK),Rate.one());
        data_.addConstNumTest(DataBase.PP_MAX,Rate.newRate("256"));
        data_.addConstNumTest("DEF_MAX_ATT",Rate.newRate("2"));
        data_.getTranslatedPokemon().addEntry(EN,new StringMap<String>());
        data_.getTranslatedPokemon().getVal(EN).addEntry(PIKACHU,PIKACHU_TR);
        data_.getTranslatedPokemon().getVal(EN).addEntry(PIKA_2, PIKA_2_TR);
        data_.getTranslatedMoves().addEntry(EN,new StringMap<String>());
        data_.getTranslatedMoves().getVal(EN).addEntry(ECLAIR,ECLAIR_TR);
        data_.getTranslatedMoves().getVal(EN).addEntry(CHARGE,CHARGE_TR);
        data_.getTranslatedMoves().getVal(EN).addEntry(CHARGE2,CHARGE_TR2);
        data_.getTranslatedMoves().getVal(EN).addEntry(M_TEAM,M_TEAM_TR);
        data_.getCombos().setEffects(new ListEffectCombos());
        data_.getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MIN,new LawNumber(law(),(short)1));
        data_.getLawsDamageRate().put(DifficultyModelLaw.CROISSANT,new LawNumber(law(),(short)1));
        data_.getLawsDamageRate().put(DifficultyModelLaw.UNIFORME,new LawNumber(law(),(short)1));
        data_.getLawsDamageRate().put(DifficultyModelLaw.DECROISSANT,new LawNumber(law(),(short)1));
        data_.getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MAX,new LawNumber(law(),(short)5));
        return data_;
    }

    private MonteCarloNumber law() {
        MonteCarloNumber monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        return monteCarloNumber_;
    }

    private PokemonData pkData(DataBase _data, String _base) {
        PokemonData pkData_ = Instances.newPokemonData();
        pkData_.setBaseEvo(_base);
        pkData_.setEggGroups(new StringList(_data.getDefaultEggGroup()));
        pkData_.setTypes(new StringList(ELECTRICK));
        statBase(pkData_);
        pkData_.getLevMoves().add(new LevelMove((short)1,ECLAIR));
        pkData_.getLevMoves().add(new LevelMove((short)1,CHARGE));
        pkData_.setExpRate(1);
        pkData_.setHeight(Rate.one());
        pkData_.setWeight(Rate.one());
        pkData_.setCatchingRate((short) 1);
        pkData_.setHappiness((short) 1);
        pkData_.setHappinessHatch((short) 1);
        pkData_.setAbilities(new StringList(PARATONNERRE));
        return pkData_;
    }

    private DamagingMoveData damMoveAcc(TargetChoice _target, String _power) {
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        move_.setPp((short) 20);
        move_.setCategory(SPEC);
        move_.setAccuracy("1");
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(_target);
        EffectDamage one_ = Instances.newEffectDamage();
        one_.setPower(_power);
        one_.setTargetChoice(_target);
        move_.getEffects().add(one_);
        return move_;
    }

    private DataBase dbBase() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        move_.setPp((short) 20);
        move_.setCategory(SPEC);
        data_.completeMembers(ECLAIR, move_);
        StatusMoveData sta_ = Instances.newStatusMoveData();
        EffectGlobal egl_ = Instances.newEffectGlobal();
        egl_.setWeather(true);
        sta_.getEffects().add(egl_);
        data_.completeMembers(CHARGE, sta_);
        StatusMoveData staTmp_ = Instances.newStatusMoveData();
        staTmp_.getEffects().add(Instances.newEffectGlobal());
        data_.completeMembers(CHARGE2, staTmp_);
        PokemonData pkData_ = Instances.newPokemonData();
        pkData_.setBaseEvo(PIKACHU);
        pkData_.setEggGroups(new StringList(data_.getDefaultEggGroup()));
        pkData_.setTypes(new StringList(ELECTRICK));
        statBase(pkData_);
        pkData_.getLevMoves().add(new LevelMove((short)1,ECLAIR));
        pkData_.getLevMoves().add(new LevelMove((short)1,CHARGE));
        pkData_.setExpRate(1);
        pkData_.setHeight(Rate.one());
        pkData_.setWeight(Rate.one());
        pkData_.setCatchingRate((short) 1);
        pkData_.setHappiness((short) 1);
        pkData_.setHappinessHatch((short) 1);
        pkData_.setAbilities(new StringList(PARATONNERRE));
        data_.completeMembers(PIKACHU,pkData_);
        Ball ball_ = Instances.newBall();
        data_.completeMembers(POKE_BALL,ball_);
        data_.getMiniPk().addEntry(PIKACHU,new int[1][1]);
        data_.getMaxiPkBack().addEntry(PIKACHU,new int[1][1]);
        data_.getMaxiPkFront().addEntry(PIKACHU,new int[1][1]);
        IdMap<Gender, String> gdrs_ = new IdMap<Gender, String>();
        gdrs_.addEntry(Gender.NO_GENDER, NO_G);
        gdrs_.addEntry(Gender.FEMALE,"FE");
        gdrs_.addEntry(Gender.MALE,"MA");
        data_.getTranslatedGenders().addEntry(LANGUAGE, gdrs_);
        IdMap<SelectedBoolean, String> bools_ = new IdMap<SelectedBoolean, String>();
        bools_.addEntry(SelectedBoolean.NO,"NO");
        bools_.addEntry(SelectedBoolean.YES,"YES");
        bools_.addEntry(SelectedBoolean.YES_AND_NO,"YES_AND_NO");
        data_.getTranslatedBooleans().addEntry(LANGUAGE, bools_);
        IdMap<DifficultyWinPointsFight, String> wfn_ = new IdMap<DifficultyWinPointsFight, String>();
        wfn_.addEntry(DifficultyWinPointsFight.TRES_DIFFICILE,"TRES_DIFFICILE");
        wfn_.addEntry(DifficultyWinPointsFight.DIFFICILE,"DIFFICILE");
        wfn_.addEntry(DifficultyWinPointsFight.FACILE,"FACILE");
        wfn_.addEntry(DifficultyWinPointsFight.TRES_FACILE,"TRES_FACILE");
        data_.getTranslatedDiffWinPts().addEntry(LANGUAGE, wfn_);
        IdMap<DifficultyModelLaw, String> ml_ = new IdMap<DifficultyModelLaw, String>();
        ml_.addEntry(DifficultyModelLaw.CONSTANT_MIN,"CONSTANT_MIN");
        ml_.addEntry(DifficultyModelLaw.CROISSANT,"CROISSANT");
        ml_.addEntry(DifficultyModelLaw.UNIFORME,"UNIFORME");
        ml_.addEntry(DifficultyModelLaw.DECROISSANT,"DECROISSANT");
        ml_.addEntry(DifficultyModelLaw.CONSTANT_MAX,"CONSTANT_MAX");
        data_.getTranslatedDiffModelLaw().addEntry(LANGUAGE, ml_);
        IdMap<EnvironmentType, String> et_ = new IdMap<EnvironmentType, String>();
        et_.addEntry(EnvironmentType.ROAD,"ROAD");
        et_.addEntry(EnvironmentType.ROCK,"ROCK");
        et_.addEntry(EnvironmentType.NOTHING,"NOTHING");
        et_.addEntry(EnvironmentType.BUILDING,"BUILDING");
        et_.addEntry(EnvironmentType.GRASS,"GRASS");
        et_.addEntry(EnvironmentType.DESERT,"DESERT");
        et_.addEntry(EnvironmentType.ICE,"ICE");
        et_.addEntry(EnvironmentType.SNOW,"SNOW");
        et_.addEntry(EnvironmentType.WATER,"WATER");
        data_.getTranslatedEnvironment().addEntry(LANGUAGE, et_);
        IdMap<Statistic, String> stats_ = new IdMap<Statistic, String>();
        stats_.addEntry(Statistic.ATTACK,"ATTACK");
        stats_.addEntry(Statistic.SPECIAL_ATTACK,"SPECIAL_ATTACK");
        stats_.addEntry(Statistic.DEFENSE,"DEFENSE");
        stats_.addEntry(Statistic.SPECIAL_DEFENSE,"SPECIAL_DEFENSE");
        stats_.addEntry(Statistic.SPEED, SPEED_TR);
        stats_.addEntry(Statistic.ACCURACY,"ACCURACY");
        stats_.addEntry(Statistic.EVASINESS,"EVASINESS");
        stats_.addEntry(Statistic.HP,"HP");
        stats_.addEntry(Statistic.PV_RESTANTS,"PV_RESTANTS");
        stats_.addEntry(Statistic.CRITICAL_HIT,"CRITICAL_HIT");
        data_.getTranslatedStatistics().addEntry(LANGUAGE, stats_);
        IdMap<TargetChoice, String> tar_ = new IdMap<TargetChoice, String>();
        tar_.addEntry(TargetChoice.ADJ_ADV,"ADJ_ADV");
        tar_.addEntry(TargetChoice.ADJ_MULT,"ADJ_MULT");
        tar_.addEntry(TargetChoice.ANY_FOE,"ANY_FOE");
        tar_.addEntry(TargetChoice.ALLIE,"ALLIE");
        tar_.addEntry(TargetChoice.ALLIES,"ALLIES");
        tar_.addEntry(TargetChoice.AUTRE_UNIQ,"AUTRE_UNIQ");
        tar_.addEntry(TargetChoice.ADJ_UNIQ,"ADJ_UNIQ");
        tar_.addEntry(TargetChoice.GLOBALE,"GLOBALE");
        tar_.addEntry(TargetChoice.LANCEUR,"LANCEUR");
        tar_.addEntry(TargetChoice.NOTHING,"NOTHING");
        tar_.addEntry(TargetChoice.TOUS_ADV,"TOUS_ADV");
        tar_.addEntry(TargetChoice.PSEUDO_GLOBALE,"PSEUDO_GLOBALE");
        tar_.addEntry(TargetChoice.UNIQUE_IMPORTE,"UNIQUE_IMPORTE");
        data_.getTranslatedTargets().addEntry(LANGUAGE, tar_);
        data_.getTranslatedTypes().addEntry(LANGUAGE,tr(ELECTRICK,ELECTRICK_TR));
        data_.getTranslatedPokemon().addEntry(LANGUAGE,tr(PIKACHU,PIKACHU_TR));
        data_.getTranslatedItems().addEntry(LANGUAGE,tr(POKE_BALL,POKE_BALL_TR));
        data_.getTranslatedAbilities().addEntry(LANGUAGE,tr(PARATONNERRE,PARATONNERRE_TR));
        StringMap<String> trMov_ = tr(LUTTE, LUTTE);
        trMov_.addAllEntries(tr(ECLAIR,ECLAIR_TR));
        trMov_.addAllEntries(tr(CHARGE,CHARGE_TR));
        trMov_.addAllEntries(tr(CHARGE2,CHARGE_TR2));
        data_.getTranslatedMoves().addEntry(LANGUAGE, trMov_);
        data_.getTranslatedStatus().addEntry(LANGUAGE,new StringMap<String>());
        data_.getTranslatedCategories().addEntry(LANGUAGE,tr(SPEC, SPEC));
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        WildPk pkm_ = new WildPk();
        pkm_.setName(PIKACHU);
        pkm_.setAbility(PARATONNERRE);
        pkm_.setGender(Gender.NO_GENDER);
        pkm_.setItem(NULL_REF);
        pkm_.setLevel((short) 7);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 1));
        data_.getCombos().setEffects(new ListEffectCombos());
        pts(data_);
        return data_;
    }

    protected FacadeGame facadeEnMoves(DataBase _data) {
        FacadeGame f_ = facade(_data);
        Fight fight_ = f_.getFight();
        ActivityOfMove ever_ = new ActivityOfMove(false);
        ever_.setEnabled(true);
        fight_.getEnabledMoves().set(CHARGE2, ever_);
        ActivityOfMove some_ = new ActivityOfMove(true);
        some_.setEnabled(false);
        fight_.getEnabledMoves().set(CHARGE, some_);
        return f_;
    }

    protected FacadeGame facadeEnMovesAct(DataBase _data) {
        FacadeGame f_ = facadeEnMoves(_data);
        Fight fight_ = f_.getFight();
        ActivityOfMove some_ = new ActivityOfMove(true);
        some_.setEnabled(true);
        some_.setNbTurn((short) 1);
        fight_.getEnabledMoves().set(CHARGE, some_);
        return f_;
    }
    protected FacadeGame facadeCalculation(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,4),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,5),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,6),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,7),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,8),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,9),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,10),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(ECLAIR,M_TEAM)));
        TrainerLeague trainer_ = Instances.newTrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 4);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        FightFacade.initTypeEnv(fight_,EnvironmentType.ROAD,diff_,_data);
        return fac_;
    }
    protected FacadeGame facadeCalculation2(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,4),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,5),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,6),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,7),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,8),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,9),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,10),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(ECLAIR)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(ECLAIR)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(ECLAIR)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(ECLAIR)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(ECLAIR)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(ECLAIR)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(ECLAIR)));
        TrainerLeague trainer_ = Instances.newTrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 4);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        FightFacade.initTypeEnv(fight_,EnvironmentType.ROAD,diff_,_data);
        return fac_;
    }
    protected FacadeGame facadeCalculation3(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,4),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,5),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,6),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,7),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,8),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,9),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,10),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(ECLAIR,M_TEAM)));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(ECLAIR,M_TEAM)));
        DualFight trainer_ = Instances.newDualFight();
        trainer_.getFoeTrainer().setTeam(foeTeam_);
        trainer_.getFoeTrainer().setReward((short) 200);
        trainer_.getAlly().setTeam(allyTeam_);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        FightFacade.initTypeEnv(fight_,EnvironmentType.ROAD,diff_,_data);
        return fac_;
    }
    protected FacadeGame facadeCalculation4(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,4),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,5),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,6),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,7),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,8),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,9),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,10),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(ECLAIR,M_TEAM)));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(ECLAIR,M_TEAM)));
        DualFight trainer_ = Instances.newDualFight();
        trainer_.getFoeTrainer().setTeam(foeTeam_);
        trainer_.getFoeTrainer().setReward((short) 200);
        trainer_.getAlly().setTeam(allyTeam_);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        FightFacade.initTypeEnv(fight_,EnvironmentType.ROAD,diff_,_data);
        fight_.getAllyChoice().clear();
        fight_.getAllyChoice().addEntry(MoveTarget.def(),MoveTarget.def());
        return fac_;
    }
    protected FacadeGame facadeCalculation5(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, toWildPk(new NameLevel(PIKA_2,3)), _data);
        FightFacade.initTypeEnv(fight_,EnvironmentType.ROAD,diff_,_data);
        return fac_;
    }
    protected FacadeGame facadeCalculation6(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,4),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,5),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,6),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,7),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,8),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,9),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,10),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(M_ROQUE)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(M_ROQUE)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(M_ROQUE)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(M_ROQUE)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(M_ROQUE)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(M_ROQUE)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(M_ROQUE)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(M_ROQUE)));
        TrainerLeague trainer_ = Instances.newTrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 4);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        FightFacade.initTypeEnv(fight_,EnvironmentType.ROAD,diff_,_data);
        return fac_;
    }
    protected FacadeGame facadeCalculation7(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,3),new StringList(M_TEAM)));
        TrainerLeague trainer_ = Instances.newTrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 1);
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        FightFacade.initTypeEnv(fight_,EnvironmentType.ROAD,diff_,_data);
        return fac_;
    }
    protected FacadeGame facadeBigTeams(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,4),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,5),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,6),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,7),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,8),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,9),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,10),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(ECLAIR,M_TEAM)));
        TrainerLeague trainer_ = Instances.newTrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 4);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        updateMoves(fight_);
        return fac_;
    }

    protected FacadeGame facadeFighters(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,4),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,5),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,6),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,7),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,8),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,9),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,10),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(ECLAIR,M_TEAM)));
        TrainerLeague trainer_ = Instances.newTrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 4);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        fight_.getUserTeam().getMembers().getValue(0).getTrackingMoves().getList().get(0).getValue().getActivity().setEnabled(true);
        fight_.getUserTeam().getMembers().getValue(0).getTrackingMoves().getList().get(0).getValue().setMove(M_TEAM);
        fight_.getUserTeam().getMembers().getValue(0).getPrivateMoves().getList().get(0).setValue(new StringList(M_TEAM));
        fight_.getUserTeam().getMembers().getValue(0).getCopiedMoves().getValue(0).setMove(M_TEAM);
        fight_.getUserTeam().getMembers().getValue(0).getCopiedMoves().getValue(0).setPp((short) 3);
        fight_.getUserTeam().getMembers().getValue(0).getIncrUserAccuracy().getList().get(0).setValue(BoolVal.TRUE);
        fight_.getUserTeam().getMembers().getValue(0).getEnabledMovesForAlly().setValue(0, BoolVal.TRUE);
        fight_.getUserTeam().getMembers().getValue(0).getAlreadyInvokedMovesRound().add(M_TEAM);
        fight_.getUserTeam().getMembers().getValue(0).setLastSufferedMove(M_TEAM);
        fight_.getUserTeam().getMembers().getValue(0).setLastUsedMove(M_TEAM);
        fight_.getUserTeam().getMembers().getValue(0).setLastSuccessfulMove(M_TEAM);
        fight_.getUserTeam().getMembers().getValue(0).setUsedMoveLastRound(M_TEAM);
        fight_.getUserTeam().getMembers().getValue(0).getLastSufferedMoveTypes().add(ELECTRICK);
        fight_.getUserTeam().getMembers().getValue(0).getProtectedAgainstMoveTypes().add(ELECTRICK);
        fight_.getUserTeam().getMembers().getValue(0).setCurrentAbility(NULL_REF);
        fight_.getUserTeam().getMembers().getValue(0).setItem(I_SAMPLE);
        fight_.getUserTeam().getMembers().getValue(0).setExpItem(I_SAMPLE);
        fight_.getUserTeam().getMembers().getValue(0).setLastUsedItem(I_SAMPLE);
        fight_.getUserTeam().getMembers().getValue(0).setUsedBallCatching(I_SAMPLE);
        fight_.getUserTeam().getMembers().getValue(0).getStatusRelat().getList().get(0).setValue((short) 1);
        fight_.getUserTeam().getMembers().getValue(0).setChanged(true);
        fight_.getUserTeam().getMembers().getValue(0).setActed(true);
        fight_.getUserTeam().getMembers().getValue(0).setUsingItem(true);
        fight_.getUserTeam().getMembers().getValue(0).setSuccessfulMove(true);
        fight_.getUserTeam().getMembers().getValue(0).setDisappeared(true);
        fight_.getUserTeam().getMembers().getValue(0).setNeedingToRecharge(true);
        fight_.getUserTeam().getMembers().getValue(0).setNickname(NICK_NA);
        return fac_;
    }
    private void updateMoves(Fight _fight) {
        _fight.getUserTeam().getHealAfter().getVal(M_STACK).getValue(0).setNbRounds((byte) 1);
        _fight.getUserTeam().getHealAfter().getVal(M_STACK).getValue(0).setLastStacked(true);
        _fight.getUserTeam().getHealAfter().getVal(M_STACK).getValue(0).setFirstStacked(true);
        _fight.getFoeTeam().getHealAfter().getVal(M_STACK).getValue(0).setNbRounds((byte) 0);
        _fight.getFoeTeam().getHealAfter().getVal(M_STACK).getValue(0).setLastStacked(false);
        _fight.getFoeTeam().getHealAfter().getVal(M_STACK).getValue(0).setFirstStacked(false);
        _fight.getUserTeam().getMovesAnticipation().getVal(M_ANT).getValue(0).setIncrementing(true);
        _fight.getUserTeam().getMovesAnticipation().getVal(M_ANT).getValue(0).setNbRounds((byte) 1);
        _fight.getUserTeam().getMovesAnticipation().getVal(M_ANT).getValue(0).setDamage(Rate.one());
        _fight.getUserTeam().getMovesAnticipation().getVal(M_ANT).getValue(0).setTargetPosition(new TargetCoords((short) -1,Fighter.BACK));
        _fight.getFoeTeam().getMovesAnticipation().getVal(M_ANT).getValue(0).setIncrementing(false);
        _fight.getFoeTeam().getMovesAnticipation().getVal(M_ANT).getValue(0).setNbRounds((byte) 0);
        _fight.getFoeTeam().getMovesAnticipation().getVal(M_ANT).getValue(0).setDamage(Rate.zero());
        _fight.getFoeTeam().getMovesAnticipation().getVal(M_ANT).getValue(0).setTargetPosition(TargetCoords.toFoeTarget((short) 0));
        _fight.getFoeTeam().getMovesAnticipation().getVal(M_ANT).getValue(1).setIncrementing(false);
        _fight.getFoeTeam().getMovesAnticipation().getVal(M_ANT).getValue(1).setNbRounds((byte) 0);
        _fight.getFoeTeam().getMovesAnticipation().getVal(M_ANT).getValue(1).setDamage(Rate.zero());
        _fight.getFoeTeam().getMovesAnticipation().getVal(M_ANT).getValue(1).setTargetPosition(TargetCoords.toUserTarget((short) 0));
    }
    protected FacadeGame facade(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,true,_data);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
//        PkTrainer foePokemon_ = Instances.newPkTrainer();
//        foePokemon_.setName(PIKACHU);
////        foePokemon_.setItem(MAGNET);
//        foePokemon_.setAbility(PARATONNERRE);
//        foePokemon_.setGender(Gender.NO_GENDER);
//        foePokemon_.setLevel((short) 3);
//        foePokemon_.setMoves(new StringList(ECLAIR));
//        foeTeam_.add(foePokemon_);
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR)));
//        foePokemon_ = Instances.newPkTrainer();
//        foePokemon_.setName(PIKACHU);
////        foePokemon_.setItem(MAGNET);
//        foePokemon_.setAbility(PARATONNERRE);
//        foePokemon_.setGender(Gender.NO_GENDER);
//        foePokemon_.setLevel((short) 4);
//        foePokemon_.setMoves(new StringList(ECLAIR));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,4),new StringList(ECLAIR)));
        TrainerLeague trainer_ = Instances.newTrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        return fac_;
    }

    private FacadeGame initFacade(DataBase _data) {
        FacadeGame fac_ = new FacadeGame();
        fac_.setLanguages(new StringList(EN));
        StringMap<String> displayLanguages_ = new StringMap<String>();
        displayLanguages_.addEntry(EN,EN);
        fac_.setDisplayLanguages(displayLanguages_);
        fac_.setData(_data);
        _data.setMessages(fac_.getData());
        fac_.setLoadedData(true);
        fac_.setZipName("");
        fac_.setData(_data);
        fac_.setLanguage(EN);
        return fac_;
    }

    private static void pts(DataBase _data) {
        _data.getExpGrowth().put(ExpType.E,"2*NIVEAU");
        _data.getExpGrowth().put(ExpType.L,"5/4*puis(VAR__NIVEAU,3)");
        _data.getExpGrowth().put(ExpType.M,"puis(VAR__NIVEAU,3)");
        _data.getExpGrowth().put(ExpType.P,"puis(VAR__NIVEAU,2)");
        _data.getExpGrowth().put(ExpType.F,"VAR__NIVEAU");
        _data.getExpGrowth().put(ExpType.R,"4/5*puis(VAR__NIVEAU,3)");
        _data.getRates().put(DifficultyWinPointsFight.TRES_FACILE, "4");
        _data.getRates().put(DifficultyWinPointsFight.FACILE, "2");
        _data.getRates().put(DifficultyWinPointsFight.DIFFICILE, "1");
        _data.getRates().put(DifficultyWinPointsFight.TRES_DIFFICILE, "1/2");
    }
    private static StringMap<Short> move(StringMap<Short> _moves, String _move, int _pp) {
        _moves.addEntry(_move, (short) _pp);
        return _moves;
    }
    private static PokemonPlayer pkPlayer(NameLevel _nameLevel, StringMap<Short> _moves, Difficulty _diff, DataBase _data) {
        WildPk w_ = Instances.newWildPk();
        w_.setName(_nameLevel.getName());
        w_.setLevel(_nameLevel.getLevel());
        w_.setAbility(PARATONNERRE);
        w_.setItem(NULL_REF);
        w_.setGender(Gender.NO_GENDER);
        PokemonPlayer lasPk_ = new PokemonPlayer(w_, _data, _moves);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data);
        return lasPk_;
    }

    private static PkTrainer toPkTrainer(NameLevel _nameLevel, StringList _moves) {
        PkTrainer pk_ = Instances.newPkTrainer();
        pk_.setName(_nameLevel.getName());
        pk_.setLevel(_nameLevel.getLevel());
        pk_.setAbility(PARATONNERRE);
        pk_.setItem(NULL_REF);
        pk_.setMoves(_moves);
        pk_.setGender(Gender.NO_GENDER);
        return pk_;
    }

    private static WildPk toWildPk(NameLevel _nameLevel) {
        WildPk pk_ = Instances.newWildPk();
        pk_.setName(_nameLevel.getName());
        pk_.setLevel(_nameLevel.getLevel());
        pk_.setAbility(PARATONNERRE);
        pk_.setItem(NULL_REF);
        pk_.setGender(Gender.NO_GENDER);
        return pk_;
    }
}