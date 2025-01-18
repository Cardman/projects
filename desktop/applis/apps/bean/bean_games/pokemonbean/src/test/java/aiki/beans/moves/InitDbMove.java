package aiki.beans.moves;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.db.MessagesDataBaseConstants;
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
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.scripts.confs.PkScriptPages;
import code.util.*;

public abstract class InitDbMove extends InitDbMoves {


    public static NaSt callMoveBeanAbilitiesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanAbilitiesGet(),_str,_args);
    }

    public static NaSt callMoveBeanAccuracyGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanAccuracyGet(),_str,_args);
    }

    public static NaSt callMoveBeanAchieveDisappearedPkUsingMoveGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanAchieveDisappearedPkUsingMoveGet(),_str,_args);
    }

    public static NaSt callMoveBeanAffectedByMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanAffectedByMovesGet(),_str,_args);
    }

    public static NaSt callMoveBeanBoostedTypesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanBoostedTypesGet(),_str,_args);
    }

    public static NaSt callMoveBeanCanBeLearnt(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanCanBeLearnt(),_str,_args);
    }

    public static NaSt callMoveBeanCannotKoGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanCannotKoGet(),_str,_args);
    }

    public static NaSt callMoveBeanCategoryGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanCategoryGet(),_str,_args);
    }

    public static String callMoveBeanClickAbility(NaSt _str, long... _args) {
        return navigateData(new MoveBeanClickAbility(),_str,_args);
    }

    public static String callMoveBeanClickAbilityId(NaSt _str, long... _args) {
        callMoveBeanClickAbility(_str, _args);
        return getValAbilityId(_str);
    }

    public static String callMoveBeanClickDeletedStatus(NaSt _str, long... _args) {
        return navigateData(new MoveBeanClickDeletedStatus(),_str,_args);
    }

    public static String callMoveBeanClickDeletedStatusId(NaSt _str, long... _args) {
        callMoveBeanClickDeletedStatus(_str, _args);
        return getValStatusId(_str);
    }

    public static String callMoveBeanClickItem(NaSt _str, long... _args) {
        return navigateData(new MoveBeanClickItem(),_str,_args);
    }

    public static String callMoveBeanClickItemId(NaSt _str, long... _args) {
        callMoveBeanClickItem(_str, _args);
        return getValItemId(_str);
    }

    public static String callMoveBeanClickItemSecEffect(NaSt _str, long... _args) {
        return navigateData(new MoveBeanClickItemSecEffect(),_str,_args);
    }

    public static String callMoveBeanClickItemSecEffectId(NaSt _str, long... _args) {
        callMoveBeanClickItemSecEffect(_str, _args);
        return getValItemId(_str);
    }

    public static String callMoveBeanClickMove(NaSt _str, long... _args) {
        return navigateData(new MoveBeanClickMove(),_str,_args);
    }

    public static String callMoveBeanClickMoveId(NaSt _str, long... _args) {
        callMoveBeanClickMove(_str, _args);
        return getValMoveId(_str);
    }
    public static String callMoveBeanClickMoves(NaSt _str, long... _args) {
        return navigateData(new MoveBeanClickMoves(),_str,_args);
    }

    public static String callMoveBeanClickPokemon(NaSt _str, long... _args) {
        return navigateData(new MoveBeanClickPokemon(),_str,_args);
    }

    public static String callMoveBeanClickPokemonId(NaSt _str, long... _args) {
        callMoveBeanClickPokemon(_str, _args);
        return getValPkId(_str);
    }

//    public static Struct callMoveBeanClickPokemon(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MoveBeanClickPokemon(),_str,_args);
//    }

    public static String callMoveBeanClickPokemonTm(NaSt _str, long... _args) {
        return navigateData(new MoveBeanClickPokemonTm(),_str,_args);
    }

    public static String callMoveBeanClickPokemonTmId(NaSt _str, long... _args) {
        callMoveBeanClickPokemonTm(_str, _args);
        return getValPkId(_str);
    }

    public static String callMoveBeanClickPokemonHm(NaSt _str, long... _args) {
        return navigateData(new MoveBeanClickPokemonHm(),_str,_args);
    }

    public static String callMoveBeanClickPokemonHmId(NaSt _str, long... _args) {
        callMoveBeanClickPokemonHm(_str, _args);
        return getValPkId(_str);
    }

    public static String callMoveBeanClickPokemonMt(NaSt _str, long... _args) {
        return navigateData(new MoveBeanClickPokemonMt(),_str,_args);
    }

    public static String callMoveBeanClickPokemonMtId(NaSt _str, long... _args) {
        callMoveBeanClickPokemonMt(_str, _args);
        return getValPkId(_str);
    }

    public static String callMoveBeanClickRequiredStatus(NaSt _str, long... _args) {
        return navigateData(new MoveBeanClickRequiredStatus(),_str,_args);
    }

    public static String callMoveBeanClickRequiredStatusId(NaSt _str, long... _args) {
        callMoveBeanClickRequiredStatus(_str, _args);
        return getValStatusId(_str);
    }

    public static String callMoveBeanClickTypesByOwnedItems(NaSt _str, long... _args) {
        return navigateData(new MoveBeanClickTypesByOwnedItems(),_str,_args);
    }

    public static String callMoveBeanClickTypesByOwnedItemsId(NaSt _str, long... _args) {
        callMoveBeanClickTypesByOwnedItems(_str, _args);
        return getValItemId(_str);
    }

    public static String callMoveBeanClickTypesByWeathers(NaSt _str, long... _args) {
        return navigateData(new MoveBeanClickTypesByWeathers(),_str,_args);
    }

    public static String callMoveBeanClickTypesByWeathersId(NaSt _str, long... _args) {
        callMoveBeanClickTypesByWeathers(_str, _args);
        return getValMoveId(_str);
    }
    public static NaSt callMoveBeanConstUserChoiceGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanConstUserChoiceGet(),_str,_args);
    }

    public static NaSt callMoveBeanDeletedStatusGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanDeletedStatusGet(),_str,_args);
    }

    public static NaSt callMoveBeanDisappearBeforeUseGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanDisappearBeforeUseGet(),_str,_args);
    }

    public static NaSt callMoveBeanDisplayNameGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanDisplayNameGet(),_str,_args);
    }

    public static NaSt callMoveBeanEffectsGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanEffectsGet(),_str,_args);
    }

    public static NaSt callMoveBeanGetDeletedStatus(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetDeletedStatus(),_str,_args);
    }

    public static NaSt callMoveBeanGetPage(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetPage(),_str,_args);
    }

    public static NaSt callMoveBeanGetRequiredStatus(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetRequiredStatus(),_str,_args);
    }

    public static NaSt callMoveBeanGetTrAbility(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrAbility(),_str,_args);
    }

    public static NaSt callMoveBeanGetTrAchieveDisappearedPkUsingMove(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrAchieveDisappearedPkUsingMove(),_str,_args);
    }

    public static String callMoveBeanClickAchieveDisappearedPkUsingMove(NaSt _str, long... _args) {
        return navigateData(new MoveBeanClickAchieveDisappearedPkUsingMove(),_str,_args);
    }

    public static String callMoveBeanClickAchieveDisappearedPkUsingMoveId(NaSt _str, long... _args) {
        callMoveBeanClickAchieveDisappearedPkUsingMove(_str, _args);
        return getValMoveId(_str);
    }
    public static NaSt callMoveBeanGetTrItem(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrItem(),_str,_args);
    }

    public static NaSt callMoveBeanGetTrMove(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrMove(),_str,_args);
    }

    public static NaSt callMoveBeanGetTrPokemon(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrPokemon(),_str,_args);
    }

    public static NaSt callMoveBeanGetTrPokemonHm(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrPokemonHm(),_str,_args);
    }

    public static NaSt callMoveBeanGetTrPokemonMt(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrPokemonMt(),_str,_args);
    }

    public static NaSt callMoveBeanGetTrPokemonTm(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrPokemonTm(),_str,_args);
    }

    public static NaSt callMoveBeanGetTrTypesByOwnedItems(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrTypesByOwnedItems(),_str,_args);
    }

    public static NaSt callMoveBeanGetTrTypesByWeathers(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanGetTrTypesByWeathers(),_str,_args);
    }

    public static NaSt callMoveBeanHasDefaultTypesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanHasDefaultTypesGet(),_str,_args);
    }

    public static NaSt callMoveBeanIgnVarAccurUserNegGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIgnVarAccurUserNegGet(),_str,_args);
    }

    public static NaSt callMoveBeanIgnVarEvasTargetPosGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIgnVarEvasTargetPosGet(),_str,_args);
    }

    public static NaSt callMoveBeanIsAdjAdv(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsAdjAdv(),_str,_args);
    }

    public static NaSt callMoveBeanIsAdjMult(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsAdjMult(),_str,_args);
    }

    public static NaSt callMoveBeanIsAdjUniq(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsAdjUniq(),_str,_args);
    }

    public static NaSt callMoveBeanIsAfterPrimaryEffect(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsAfterPrimaryEffect(),_str,_args);
    }

    public static NaSt callMoveBeanIsAllie(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsAllie(),_str,_args);
    }

    public static NaSt callMoveBeanIsAllies(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsAllies(),_str,_args);
    }

    public static NaSt callMoveBeanIsAnyFoe(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsAnyFoe(),_str,_args);
    }

    public static NaSt callMoveBeanIsAutreUniq(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsAutreUniq(),_str,_args);
    }

    public static NaSt callMoveBeanIsBeforePrimaryEffect(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsBeforePrimaryEffect(),_str,_args);
    }

    public static NaSt callMoveBeanIsConstAccuracy(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsConstAccuracy(),_str,_args);
    }

    public static NaSt callMoveBeanIsDamagingDirectMove(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsDamagingDirectMove(),_str,_args);
    }

    public static NaSt callMoveBeanIsDamagingMove(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsDamagingMove(),_str,_args);
    }

    public static NaSt callMoveBeanIsEndRoundEffect(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsEndRoundEffect(),_str,_args);
    }

    public static NaSt callMoveBeanIsGlobale(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsGlobale(),_str,_args);
    }

    public static NaSt callMoveBeanIsItem(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsItem(),_str,_args);
    }

    public static NaSt callMoveBeanIsLanceur(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsLanceur(),_str,_args);
    }

    public static NaSt callMoveBeanIsPrimaryEffect(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsPrimaryEffect(),_str,_args);
    }

    public static NaSt callMoveBeanIsPseudoGlobale(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsPseudoGlobale(),_str,_args);
    }

    public static NaSt callMoveBeanIsRepeatedRound(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsRepeatedRound(),_str,_args);
    }

    public static NaSt callMoveBeanIsTousAdv(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsTousAdv(),_str,_args);
    }

    public static NaSt callMoveBeanIsUniqueImporte(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsUniqueImporte(),_str,_args);
    }

    public static NaSt callMoveBeanIsWeather(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsWeather(),_str,_args);
    }

    public static NaSt callMoveBeanIsZeroPrepaRound(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsZeroPrepaRound(),_str,_args);
    }

    public static NaSt callMoveBeanIsZeroPriority(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanIsZeroPriority(),_str,_args);
    }

    public static NaSt callMoveBeanItemsGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanItemsGet(),_str,_args);
    }

    public static NaSt callMoveBeanMapVarsAccuracyGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanMapVarsAccuracyGet(),_str,_args);
    }

    public static NaSt callMoveBeanMovesHmLearntByPokemonGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanMovesHmLearntByPokemonGet(),_str,_args);
    }

    public static NaSt callMoveBeanMovesLevelLearntByPokemonGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanMovesLevelLearntByPokemonGet(),_str,_args);
    }

    public static NaSt callMoveBeanMovesMtLearntByPokemonGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanMovesMtLearntByPokemonGet(),_str,_args);
    }

    public static NaSt callMoveBeanMovesTmLearntByPokemonGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanMovesTmLearntByPokemonGet(),_str,_args);
    }

    public static NaSt callMoveBeanNameGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanNameGet(),_str,_args);
    }

    public static NaSt callMoveBeanNbPrepaRoundGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanNbPrepaRoundGet(),_str,_args);
    }

    public static NaSt callMoveBeanPpGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanPpGet(),_str,_args);
    }

    public static NaSt callMoveBeanPriorityGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanPriorityGet(),_str,_args);
    }

    public static NaSt callMoveBeanRankIncrementNbRoundGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanRankIncrementNbRoundGet(),_str,_args);
    }

    public static NaSt callMoveBeanRechargeRoundGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanRechargeRoundGet(),_str,_args);
    }

    public static NaSt callMoveBeanRepeatRoundLawGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanRepeatRoundLawGet(),_str,_args);
    }

    public static NaSt callMoveBeanRequiredStatusGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanRequiredStatusGet(),_str,_args);
    }

    public static NaSt callMoveBeanSecEffectIfNoDamageGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanSecEffectIfNoDamageGet(),_str,_args);
    }

    public static NaSt callMoveBeanSecEffectsByItemGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanSecEffectsByItemGet(),_str,_args);
    }

    public static NaSt callMoveBeanSwitchAfterUsingMove(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanSwitchAfterUsingMove(),_str,_args);
    }

    public static NaSt callMoveBeanTranslateItemSecEffect(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanTranslateItemSecEffect(),_str,_args);
    }

    public static NaSt callMoveBeanTypesByOwnedItemsGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanTypesByOwnedItemsGet(),_str,_args);
    }

    public static NaSt callMoveBeanTypesByWeathersGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanTypesByWeathersGet(),_str,_args);
    }

    public static NaSt callMoveBeanTypesDependOnWeatherAndItem(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanTypesDependOnWeatherAndItem(),_str,_args);
    }

    public static NaSt callMoveBeanTypesDependOnlyOnItem(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanTypesDependOnlyOnItem(),_str,_args);
    }

    public static NaSt callMoveBeanTypesDependOnlyOnWeather(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanTypesDependOnlyOnWeather(),_str,_args);
    }

    public static NaSt callMoveBeanTypesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanTypesGet(),_str,_args);
    }

    public static NaSt callMoveBeanEffPrimOrBeforeNotEndRound(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanEffPrimOrBeforeNotEndRound(),_str,_args);
    }

    public static NaSt callMoveBeanSecNotEndRound(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveBeanSecNotEndRound(),_str,_args);
    }
    protected static NaSt dispMove(FacadeGame _fac, int _index) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToMove(pk_);
        StringMap<String> mapping_ = mappingToMove();
        return transitMove(_index, pk_, all_, mapping_);
    }

    public static StringMap<NaSt> beanToMove(PkData _pk) {
        StringMap<NaSt> map_ = beanToMoves(_pk);
        map_.addEntry(AikiBeansMovesStd.BEAN_MOVE,_pk.beanMoveBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToMove() {
        StringMap<String> map_ = mappingToMoves();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,AikiBeansMovesStd.BEAN_MOVE);
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
        f_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
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
        pk_.setHappiness( 1);
        pk_.setHappinessHatch( 1);
        pk_.setExpRate(1);
        pk_.setCatchingRate( 1);
        pk_.setExpEvo(ExpType.E);
        pk_.setGenderRep(GenderRepartition.NO_GENDER);
        pk_.setHatchingSteps(LgInt.one());
        pk_.setHiddenMoves(Ints.newList(_hm));
        pk_.setTechnicalMoves(Ints.newList(_tm));
        pk_.setEggGroups(new StringList("__"));
        pk_.setMoveTutors(new StringList(M_DAM));
        CustList<LevelMove> lv_ = new CustList<LevelMove>();
        lv_.add(new LevelMove( 1,M_WEA));
        lv_.add(new LevelMove( 1,M_DAM_VAR));
        lv_.add(new LevelMove( 3,M_STA));
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
    protected static void trs(FacadeGame _facade) {
        _facade.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedAbilities().getVal(EN).addEntry(A_ABILITY,A_ABILITY_TR);
        _facade.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE1, T_TYPE1_TR);
        _facade.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE2, T_TYPE2_TR);
        _facade.getData().getTranslatedCategories().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedCategories().getVal(EN).addEntry(C_CAT, C_CAT1_TR);
        _facade.getData().getTranslatedCategories().getVal(EN).addEntry(AUTRE,C_CAT2_TR);
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

    protected static void target(MoveData _dam, Effect _ef) {
        _ef.setTargetChoice(_dam.getTargetChoice());
        _dam.getEffects().add(_ef);
    }
}
