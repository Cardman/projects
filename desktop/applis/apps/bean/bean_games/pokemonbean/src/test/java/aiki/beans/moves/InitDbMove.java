package aiki.beans.moves;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.PointViewChangementType;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.util.LevelMove;
import aiki.game.fight.Fight;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.*;

public abstract class InitDbMove extends InitDbMoves {

    public static final String TIME = "time";

    public static Struct callMoveBeanAbilitiesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanAbilitiesGet(),_str,_args);
    }

    public static Struct callMoveBeanAccuracyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanAccuracyGet(),_str,_args);
    }

    public static Struct callMoveBeanAchieveDisappearedPkUsingMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanAchieveDisappearedPkUsingMoveGet(),_str,_args);
    }

    public static Struct callMoveBeanAffectedByMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanAffectedByMovesGet(),_str,_args);
    }

    public static Struct callMoveBeanBoostedTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanBoostedTypesGet(),_str,_args);
    }

    public static Struct callMoveBeanCanBeLearnt(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanCanBeLearnt(),_str,_args);
    }

    public static Struct callMoveBeanCannotKoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanCannotKoGet(),_str,_args);
    }

    public static Struct callMoveBeanCategoryGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanCategoryGet(),_str,_args);
    }

    public static Struct callMoveBeanClickAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanClickAbility(),_str,_args);
    }

    public static Struct callMoveBeanClickDeletedStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanClickDeletedStatus(),_str,_args);
    }

    public static Struct callMoveBeanClickItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanClickItem(),_str,_args);
    }

    public static Struct callMoveBeanClickItemSecEffect(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanClickItemSecEffect(),_str,_args);
    }

    public static Struct callMoveBeanClickMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanClickMove(),_str,_args);
    }

    public static Struct callMoveBeanClickMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanClickMoves(),_str,_args);
    }

    public static String callMoveBeanClickPokemon(Struct _str, long... _args) {
        return navigateData(new MoveBeanClickPokemon(),AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML,AikiBeansMovesStd.BEAN_MOVE+NAV_SEP+"clickPokemon(,)",_str,_args);
    }

    public static String callMoveBeanClickPokemonId(Struct _str, long... _args) {
        callMoveBeanClickPokemon(_str, _args);
        return getValPkId(_str);
    }

//    public static Struct callMoveBeanClickPokemon(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MoveBeanClickPokemon(),_str,_args);
//    }

    public static Struct callMoveBeanClickPokemonHm(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanClickPokemonHm(),_str,_args);
    }

    public static Struct callMoveBeanClickPokemonMt(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanClickPokemonMt(),_str,_args);
    }

    public static Struct callMoveBeanClickPokemonTm(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanClickPokemonTm(),_str,_args);
    }

    public static Struct callMoveBeanClickRequiredStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanClickRequiredStatus(),_str,_args);
    }

    public static Struct callMoveBeanClickTypesByOwnedItems(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanClickTypesByOwnedItems(),_str,_args);
    }

    public static Struct callMoveBeanClickTypesByWeathers(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanClickTypesByWeathers(),_str,_args);
    }

    public static Struct callMoveBeanConstUserChoiceGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanConstUserChoiceGet(),_str,_args);
    }

    public static Struct callMoveBeanDeletedStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanDeletedStatusGet(),_str,_args);
    }

    public static Struct callMoveBeanDisappearBeforeUseGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanDisappearBeforeUseGet(),_str,_args);
    }

    public static Struct callMoveBeanDisplayNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanDisplayNameGet(),_str,_args);
    }

    public static Struct callMoveBeanEffectsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanEffectsGet(),_str,_args);
    }

    public static Struct callMoveBeanGetDeletedStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetDeletedStatus(),_str,_args);
    }

    public static Struct callMoveBeanGetPage(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetPage(),_str,_args);
    }

    public static Struct callMoveBeanGetRequiredStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetRequiredStatus(),_str,_args);
    }

    public static Struct callMoveBeanGetTrAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrAbility(),_str,_args);
    }

    public static Struct callMoveBeanGetTrAchieveDisappearedPkUsingMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrAchieveDisappearedPkUsingMove(),_str,_args);
    }

    public static String callMoveBeanClickAchieveDisappearedPkUsingMove(Struct _str, long... _args) {
        return navigateData(new MoveBeanClickAchieveDisappearedPkUsingMove(),AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML,"",_str,_args);
    }

    public static String callMoveBeanClickAchieveDisappearedPkUsingMoveId(Struct _str, long... _args) {
        callMoveBeanClickAchieveDisappearedPkUsingMove(_str, _args);
        return getValMoveId(_str);
    }
    public static Struct callMoveBeanGetTrItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrItem(),_str,_args);
    }

    public static Struct callMoveBeanGetTrMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrMove(),_str,_args);
    }

    public static Struct callMoveBeanGetTrPokemon(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrPokemon(),_str,_args);
    }

    public static Struct callMoveBeanGetTrPokemonHm(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrPokemonHm(),_str,_args);
    }

    public static Struct callMoveBeanGetTrPokemonMt(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrPokemonMt(),_str,_args);
    }

    public static Struct callMoveBeanGetTrPokemonTm(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrPokemonTm(),_str,_args);
    }

    public static Struct callMoveBeanGetTrTypesByOwnedItems(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrTypesByOwnedItems(),_str,_args);
    }

    public static Struct callMoveBeanGetTrTypesByWeathers(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrTypesByWeathers(),_str,_args);
    }

    public static Struct callMoveBeanHasDefaultTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanHasDefaultTypesGet(),_str,_args);
    }

    public static Struct callMoveBeanIgnVarAccurUserNegGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIgnVarAccurUserNegGet(),_str,_args);
    }

    public static Struct callMoveBeanIgnVarEvasTargetPosGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIgnVarEvasTargetPosGet(),_str,_args);
    }

    public static Struct callMoveBeanIsAdjAdv(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsAdjAdv(),_str,_args);
    }

    public static Struct callMoveBeanIsAdjMult(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsAdjMult(),_str,_args);
    }

    public static Struct callMoveBeanIsAdjUniq(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsAdjUniq(),_str,_args);
    }

    public static Struct callMoveBeanIsAfterPrimaryEffect(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsAfterPrimaryEffect(),_str,_args);
    }

    public static Struct callMoveBeanIsAllie(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsAllie(),_str,_args);
    }

    public static Struct callMoveBeanIsAllies(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsAllies(),_str,_args);
    }

    public static Struct callMoveBeanIsAnyFoe(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsAnyFoe(),_str,_args);
    }

    public static Struct callMoveBeanIsAutreUniq(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsAutreUniq(),_str,_args);
    }

    public static Struct callMoveBeanIsBeforePrimaryEffect(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsBeforePrimaryEffect(),_str,_args);
    }

    public static Struct callMoveBeanIsConstAccuracy(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsConstAccuracy(),_str,_args);
    }

    public static Struct callMoveBeanIsDamagingDirectMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsDamagingDirectMove(),_str,_args);
    }

    public static Struct callMoveBeanIsDamagingMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsDamagingMove(),_str,_args);
    }

    public static Struct callMoveBeanIsEndRoundEffect(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsEndRoundEffect(),_str,_args);
    }

    public static Struct callMoveBeanIsGlobale(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsGlobale(),_str,_args);
    }

    public static Struct callMoveBeanIsItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsItem(),_str,_args);
    }

    public static Struct callMoveBeanIsLanceur(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsLanceur(),_str,_args);
    }

    public static Struct callMoveBeanIsPrimaryEffect(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsPrimaryEffect(),_str,_args);
    }

    public static Struct callMoveBeanIsPseudoGlobale(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsPseudoGlobale(),_str,_args);
    }

    public static Struct callMoveBeanIsRepeatedRound(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsRepeatedRound(),_str,_args);
    }

    public static Struct callMoveBeanIsTousAdv(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsTousAdv(),_str,_args);
    }

    public static Struct callMoveBeanIsUniqueImporte(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsUniqueImporte(),_str,_args);
    }

    public static Struct callMoveBeanIsWeather(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsWeather(),_str,_args);
    }

    public static Struct callMoveBeanIsZeroPrepaRound(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsZeroPrepaRound(),_str,_args);
    }

    public static Struct callMoveBeanIsZeroPriority(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsZeroPriority(),_str,_args);
    }

    public static Struct callMoveBeanItemsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanItemsGet(),_str,_args);
    }

    public static Struct callMoveBeanMapVarsAccuracyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanMapVarsAccuracyGet(),_str,_args);
    }

    public static Struct callMoveBeanMovesHmLearntByPokemonGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanMovesHmLearntByPokemonGet(),_str,_args);
    }

    public static Struct callMoveBeanMovesLevelLearntByPokemonGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanMovesLevelLearntByPokemonGet(),_str,_args);
    }

    public static Struct callMoveBeanMovesMtLearntByPokemonGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanMovesMtLearntByPokemonGet(),_str,_args);
    }

    public static Struct callMoveBeanMovesTmLearntByPokemonGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanMovesTmLearntByPokemonGet(),_str,_args);
    }

    public static Struct callMoveBeanNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanNameGet(),_str,_args);
    }

    public static Struct callMoveBeanNbPrepaRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanNbPrepaRoundGet(),_str,_args);
    }

    public static Struct callMoveBeanPpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanPpGet(),_str,_args);
    }

    public static Struct callMoveBeanPriorityGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanPriorityGet(),_str,_args);
    }

    public static Struct callMoveBeanRankIncrementNbRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanRankIncrementNbRoundGet(),_str,_args);
    }

    public static Struct callMoveBeanRechargeRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanRechargeRoundGet(),_str,_args);
    }

    public static Struct callMoveBeanRepeatRoundLawGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanRepeatRoundLawGet(),_str,_args);
    }

    public static Struct callMoveBeanRequiredStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanRequiredStatusGet(),_str,_args);
    }

    public static Struct callMoveBeanSecEffectIfNoDamageGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanSecEffectIfNoDamageGet(),_str,_args);
    }

    public static Struct callMoveBeanSecEffectsByItemGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanSecEffectsByItemGet(),_str,_args);
    }

    public static Struct callMoveBeanSwitchAfterUsingMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanSwitchAfterUsingMove(),_str,_args);
    }

    public static Struct callMoveBeanTranslateItemSecEffect(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanTranslateItemSecEffect(),_str,_args);
    }

    public static Struct callMoveBeanTypesByOwnedItemsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanTypesByOwnedItemsGet(),_str,_args);
    }

    public static Struct callMoveBeanTypesByWeathersGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanTypesByWeathersGet(),_str,_args);
    }

    public static Struct callMoveBeanTypesDependOnWeatherAndItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanTypesDependOnWeatherAndItem(),_str,_args);
    }

    public static Struct callMoveBeanTypesDependOnlyOnItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanTypesDependOnlyOnItem(),_str,_args);
    }

    public static Struct callMoveBeanTypesDependOnlyOnWeather(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanTypesDependOnlyOnWeather(),_str,_args);
    }

    public static Struct callMoveBeanTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanTypesGet(),_str,_args);
    }

    public static Struct callMoveBeanEffPrimOrBeforeNotEndRound(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanEffPrimOrBeforeNotEndRound(),_str,_args);
    }

    public static Struct callMoveBeanSecNotEndRound(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanSecNotEndRound(),_str,_args);
    }
    protected static Struct dispMove(FacadeGame _fac, int _index) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<Struct> all_ = beanToMove(pk_);
        transitToAllMoves(pk_, all_);
        StringMap<String> mapping_ = mappingToMove();
        Struct moveline_ = displayMoveLine(all_, _index, mapping_);
        Struct mbean_ = all_.getVal(AikiBeansMovesStd.BEAN_MOVE);
        transit(pk_,new MoveLineBeanClickMove(), moveline_, mbean_,toInt(callMoveLineBeanIndexGet(moveline_)));
        return mbean_;
    }
    public static StringMap<Struct> beanToMove(PkData _pk) {
        StringMap<Struct> map_ = beanToMoves(_pk);
        map_.addEntry(AikiBeansMovesStd.BEAN_MOVE,_pk.beanMoveBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToMove() {
        StringMap<String> map_ = mappingToMoves();
        map_.addEntry(AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML,AikiBeansMovesStd.BEAN_MOVE);
        return map_;
    }
    protected static FacadeGame feedDbMoveStaStatis(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _t, boolean _count) {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_DAM, moveDam(TargetChoice.ANY_FOE));
        StatusMoveData sta_ = Instances.newStatusMoveData();
        feed(sta_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_DAM, M_WEA, 1, 1);
        feed(sta_, _t, _count);
        EffectStatistic ef_ = Instances.newEffectStatistic();
        target(sta_, ef_);
        facade_.getData().completeMembers(M_STA, sta_);
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveStaEndRound(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _t, boolean _count, int _prepa, int _prioNb) {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_DAM, moveDam(TargetChoice.ANY_FOE));
        StatusMoveData sta_ = Instances.newStatusMoveData();
        feed(sta_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_DAM, M_WEA, _prepa, _prioNb);
        feed(sta_, _t, _count);
        EffectEndRoundIndividual ef_ = Instances.newEffectEndRoundIndividual();
        target(sta_, ef_);
        facade_.getData().completeMembers(M_STA, sta_);
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveStaEndRoundAffect(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _t, boolean _count, int _prepa, int _prioNb) {
        FacadeGame facade_ = facade();
        StatusMoveData protMult_ = moveSta(TargetChoice.ANY_FOE);
        protMult_.getEffects().add(buildProt(false,false,Rate.zero(),true,false,false));
        facade_.getData().completeMembers(M_DAM, protMult_);
        StatusMoveData sta_ = Instances.newStatusMoveData();
        feed(sta_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_DAM, M_WEA, _prepa, _prioNb);
        feed(sta_, _t, _count);
        EffectEndRoundIndividual ef_ = Instances.newEffectEndRoundIndividual();
        target(sta_, ef_);
        facade_.getData().completeMembers(M_STA, sta_);
        StatusMoveData protSingle_ = moveSta(TargetChoice.TOUS_ADV);
        protSingle_.getEffects().add(buildProt(true,false,Rate.zero(),false,false,false));
        facade_.getData().completeMembers(M_WEA, protSingle_);
        StatusMoveData prDam_ = moveSta(TargetChoice.TOUS_ADV);
        prDam_.getEffects().add(pv(PointViewChangementType.THIEF_BONUSES));
        facade_.getData().completeMembers(M_DAM_POW, prDam_);
        StatusMoveData protStat_ = moveSta(TargetChoice.TOUS_ADV);
        protStat_.getEffects().add(buildProt(false, false, Rate.zero(), false, true, false));
        facade_.getData().completeMembers(M_DAM_VAR, protStat_);
        StatusMoveData protKo_ = moveSta(TargetChoice.TOUS_ADV);
        protKo_.getEffects().add(pv(PointViewChangementType.MIRROR_AGAINST_THROWER));
        facade_.getData().completeMembers(M_DAM_BAD, protKo_);
        StatusMoveData protPrio_ = moveSta(TargetChoice.TOUS_ADV);
        protPrio_.getEffects().add(buildProt(false, false, Rate.zero(), false, false, true));
        facade_.getData().completeMembers(M_DAM_VERY_BAD, protPrio_);
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }

    private static EffectSwitchPointView pv(PointViewChangementType _n) {
        EffectSwitchPointView p_ = Instances.newEffectSwitchPointView();
        p_.setPointViewChangement(_n);
        return p_;
    }

    private static EffectProtection buildProt(boolean _single, boolean _protTeamAgainstDamageMoves, Rate _ag, boolean _protTeamAgainstMultTargets, boolean _protTeamAgainstStatusMoves, boolean _protTeamAgainstPrio) {
        EffectProtection e_ = Instances.newEffectProtection();
        e_.setProtSingle(_single);
        e_.setProtTeamAgainstDamageMoves(_protTeamAgainstDamageMoves);
        e_.setProtSingleAgainstKo(_ag);
        e_.setProtTeamAgainstMultTargets(_protTeamAgainstMultTargets);
        e_.setProtTeamAgainstStatusMoves(_protTeamAgainstStatusMoves);
        e_.setProtTeamAgainstPrio(_protTeamAgainstPrio);
        return e_;
    }
    protected static FacadeGame feedDbMoveDamComp(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power) {
        FacadeGame f_ = feedDbMoveDam(_targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, _s, _k, _dir, _power);
        f_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
        return f_;
    }
    protected static FacadeGame feedDbMoveDam(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_STA, M_WEA, 1, 1);
        feed(dam_, _s, _k, _dir);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower(_power);
        target(dam_, ef_);
        facade_.getData().completeMembers(M_DAM, dam_);
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveDamAb(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_STA, M_WEA, 1, 1);
        feed(dam_, _s, _k, _dir);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower(_power);
        target(dam_, ef_);
        facade_.getData().completeMembers(M_DAM, dam_);
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        AbilityData ab_ = Instances.newAbilityData();
        ab_.getImmuMove().add(M_DAM);
        facade_.getData().completeMembers(A_ABILITY, ab_);
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveDamItBatNot(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_STA, M_WEA, 1, 1);
        feed(dam_, _s, _k, _dir);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower(_power);
        target(dam_, ef_);
        facade_.getData().completeMembers(M_DAM, dam_);
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,Instances.newItemForBattle());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        AbilityData ab_ = Instances.newAbilityData();
        ab_.getImmuMove().add(M_DAM);
        facade_.getData().completeMembers(A_ABILITY, ab_);
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveDamItBat(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_STA, M_WEA, 1, 1);
        feed(dam_, _s, _k, _dir);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower(_power);
        target(dam_, ef_);
        facade_.getData().completeMembers(M_DAM, dam_);
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        ItemForBattle ot_ = Instances.newItemForBattle();
        ot_.getImmuMoves().add(M_DAM);
        facade_.getData().completeMembers(I_ITEM, ot_);
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveDamAffect(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_STA, M_WEA, 1, 1);
        feed(dam_, _s, _k, _dir);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower(_power);
        target(dam_, ef_);
        facade_.getData().completeMembers(M_DAM, dam_);
        StatusMoveData protMult_ = moveSta(TargetChoice.TOUS_ADV);
        protMult_.getEffects().add(buildProt(false,false,Rate.zero(),true,false,false));
        facade_.getData().completeMembers(M_STA, protMult_);
        StatusMoveData protSingle_ = moveSta(TargetChoice.TOUS_ADV);
        protSingle_.getEffects().add(buildProt(true,false,Rate.zero(),false,false,false));
        facade_.getData().completeMembers(M_WEA, protSingle_);
        StatusMoveData prDam_ = moveSta(TargetChoice.TOUS_ADV);
        prDam_.getEffects().add(buildProt(false,true,Rate.zero(),false,false,false));
        facade_.getData().completeMembers(M_DAM_POW, prDam_);
        StatusMoveData protStat_ = moveSta(TargetChoice.TOUS_ADV);
        protStat_.getEffects().add(buildProt(false, false, Rate.zero(), false, true, false));
        facade_.getData().completeMembers(M_DAM_VAR, protStat_);
        StatusMoveData protKo_ = moveSta(TargetChoice.TOUS_ADV);
        protKo_.getEffects().add(buildProt(false, false, Rate.one(), false, false, false));
        facade_.getData().completeMembers(M_DAM_BAD, protKo_);
        StatusMoveData protPrio_ = moveSta(TargetChoice.TOUS_ADV);
        protPrio_.getEffects().add(buildProt(false, false, Rate.zero(), false, false, true));
        facade_.getData().completeMembers(M_DAM_VERY_BAD, protPrio_);
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveDamFullLearn(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power, int _hm, int _tm) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_STA, M_WEA, 1, 1);
        feed(dam_, _s, _k, _dir);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower(_power);
        target(dam_, ef_);
        facade_.getData().completeMembers(M_DAM, dam_);
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_DAM_VAR,moveDam(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_DAM_BAD,moveDam(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_DAM_VERY_BAD,moveDam(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_DAM_POW,moveDam(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        PokemonData pk_ = Instances.newPokemonData();
        pk_.setBaseEvo(P_POKEMON);
        pk_.setTypes(new StringList(T_TYPE1));
        pk_.setAbilities(new StringList(A_ABILITY));
        pk_.setHeight(Rate.one());
        pk_.setWeight(Rate.one());
        pk_.setHappiness((short) 1);
        pk_.setHappinessHatch((short) 1);
        pk_.setExpRate(1);
        pk_.setCatchingRate((short) 1);
        pk_.setExpEvo(ExpType.E);
        pk_.setGenderRep(GenderRepartition.NO_GENDER);
        pk_.setHatchingSteps(LgInt.one());
        pk_.setHiddenMoves(Shorts.newList((short) _hm));
        pk_.setTechnicalMoves(Shorts.newList((short) _tm));
        pk_.setEggGroups(new StringList("__"));
        pk_.setMoveTutors(new StringList(M_DAM));
        CustList<LevelMove> lv_ = new CustList<LevelMove>();
        lv_.add(new LevelMove((short) 1,M_WEA));
        lv_.add(new LevelMove((short) 1,M_DAM_VAR));
        lv_.add(new LevelMove((short) 3,M_STA));
        pk_.setLevMoves(lv_);
        statAdv(pk_);
        facade_.getData().completeMembers(P_POKEMON, pk_);
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(), facade_.getData().getTmPrice(), 1,M_DAM_POW);
        feedHm(facade_.getData().getHm(), 1,M_DAM_BAD);
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveDamTwo(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_STA, M_WEA, 1, 1);
        feed(dam_, _s, _k, _dir);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower(_power);
        target(dam_, ef_);
        facade_.getData().completeMembers(M_DAM, dam_);
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(P_PIKA,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_PIKA,P_PIKA_TR);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveDamNonRepeated(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_STA, M_WEA, 1, 1);
        feed(dam_, _s, _k, _dir);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower(_power);
        target(dam_, ef_);
        dam_.setRepeatRoundLaw(new MonteCarloNumber());
        facade_.getData().completeMembers(M_DAM, dam_);
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveDamDefType(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_STA, M_WEA, 1, 1);
        feed(dam_, _s, _k, _dir);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower(_power);
        target(dam_, ef_);
        dam_.getTypesByWeather().addEntry(NULL_REF, T_TYPE2);
        facade_.getData().completeMembers(M_DAM, dam_);
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveDamDefTypeNoItem(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_STA, M_WEA, 1, 1);
        feed(dam_, _s, _k, _dir);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower(_power);
        target(dam_, ef_);
        dam_.getTypesByWeather().addEntry(NULL_REF, T_TYPE2);
        dam_.getTypesByOwnedItem().clear();
        facade_.getData().completeMembers(M_DAM, dam_);
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveDamDefTypeNo(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_STA, M_WEA, 1, 1);
        feed(dam_, _s, _k, _dir);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower(_power);
        target(dam_, ef_);
        dam_.getTypesByWeather().clear();
        dam_.getTypesByOwnedItem().clear();
        facade_.getData().completeMembers(M_DAM, dam_);
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveDamBig(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_STA, M_WEA, 1, 1);
        feed(dam_, _s, _k, _dir);
        EffectStatistic pre_ = Instances.newEffectStatistic();
        pre_.setTargetChoice(TargetChoice.LANCEUR);
        dam_.getEffects().add(pre_);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower(_power);
        target(dam_, ef_);
        EffectStatus st_ = Instances.newEffectStatus();
        st_.setTargetChoice(TargetChoice.LANCEUR);
        dam_.getEffects().add(st_);
        EffectEndRoundIndividual end_ = Instances.newEffectEndRoundIndividual();
        end_.setTargetChoice(TargetChoice.LANCEUR);
        dam_.getEffects().add(end_);
        facade_.getData().completeMembers(M_DAM, dam_);
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveDamDefTypeIt(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_STA, M_WEA, 1, 1);
        feed(dam_, _s, _k, _dir);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower(_power);
        target(dam_, ef_);
        dam_.getTypesByWeather().clear();
        dam_.getTypesByOwnedItem().addEntry(NULL_REF,T_TYPE1);
        dam_.getTypesByOwnedItem().addEntry(I_ITEM,T_TYPE2);
        facade_.getData().completeMembers(M_DAM, dam_);
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveDamDefTypeItWeather(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_STA, M_WEA, 1, 1);
        feed(dam_, _s, _k, _dir);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower(_power);
        target(dam_, ef_);
        dam_.getTypesByWeather().addEntry(NULL_REF, T_TYPE2);
        dam_.getTypesByOwnedItem().addEntry(NULL_REF,T_TYPE1);
        dam_.getTypesByOwnedItem().addEntry(I_ITEM,T_TYPE2);
        facade_.getData().completeMembers(M_DAM, dam_);
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void trs(FacadeGame _facade) {
        _facade.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedAbilities().getVal(EN).addEntry(A_ABILITY,A_ABILITY_TR);
        _facade.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE1, T_TYPE1_TR);
        _facade.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE2, T_TYPE2_TR);
        _facade.getData().getTranslatedCategories().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedCategories().getVal(EN).addEntry(C_CAT, C_CAT1_TR);
        _facade.getData().getTranslatedCategories().getVal(EN).addEntry(DataBase.AUTRE,C_CAT2_TR);
        _facade.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_ITEM,I_ITEM_TR);
        _facade.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_WEA,M_WEA_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VERY_BAD,M_DAM_VERY_BAD_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_POW,M_DAM_POW_TR);
        _facade.getData().getTranslatedPokemon().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POKEMON,P_POKEMON_TR);
        _facade.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_REL,S_STA_REL_TR);
        _facade.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_SIM,S_STA_SIM_TR);
        _facade.getData().getTranslatedStatistics().addEntry(EN,new IdMap<Statistic, String>());
        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ATTACK,ST_ATT_TR);
        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.DEFENSE,ST_DEF_TR);
        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPECIAL_ATTACK,ST_ATT_SPE_TR);
        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPECIAL_DEFENSE,ST_DEF_SPE_TR);
        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPEED,ST_SPEED_TR);
        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.HP,ST_HP_TR);
        _facade.getData().getTranslatedBooleans().addEntry(EN,new IdMap<SelectedBoolean, String>());
        _facade.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.NO, B_NO);
        _facade.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.YES, B_YES);
        _facade.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.YES_AND_NO," ");
        _facade.getData().getLitterals().addEntry(EN,new StringMap<String>());
    }

    private static void target(MoveData _dam, Effect _ef) {
        _ef.setTargetChoice(_dam.getTargetChoice());
        _dam.getEffects().add(_ef);
    }
}
