package aiki.beans.moves;

import aiki.beans.*;
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
import code.scripts.pages.aiki.*;
import code.sml.util.*;
import code.util.*;

public abstract class InitDbMove extends InitDbMoves {


    public static NaSt callMoveBeanAbilitiesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getAbilities());
    }

    public static NaSt callMoveBeanAccuracyGet(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getAccuracy());
    }

    public static NaSt callMoveBeanAchieveDisappearedPkUsingMoveGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getAchieveDisappearedPkUsingMove());
    }

    public static NaSt callMoveBeanAffectedByMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getAffectedByMoves());
    }

    public static NaSt callMoveBeanBoostedTypesGet(NaSt _str, int... _args) {
        return PokemonStandards.getValues(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getBoostedTypes());
    }

    public static NaSt callMoveBeanCanBeLearnt(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).canBeLearnt());
    }

    public static NaSt callMoveBeanCannotKoGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getCannotKo());
    }

    public static NaSt callMoveBeanCategoryGet(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getCategory().getTranslation());
    }

    public static String callMoveBeanClickAbility(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).clickAbility(_args[0])).getInstance();
    }

    public static String callMoveBeanClickAbilityId(NaSt _str, int... _args) {
        callMoveBeanClickAbility(_str, _args);
        return getValAbilityId(_str);
    }

    public static String callMoveBeanClickDeletedStatus(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).clickDeletedStatus(_args[0])).getInstance();
    }

    public static String callMoveBeanClickDeletedStatusId(NaSt _str, int... _args) {
        callMoveBeanClickDeletedStatus(_str, _args);
        return getValStatusId(_str);
    }

    public static String callMoveBeanClickItem(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).clickItem(_args[0])).getInstance();
    }

    public static String callMoveBeanClickItemId(NaSt _str, int... _args) {
        callMoveBeanClickItem(_str, _args);
        return getValItemId(_str);
    }

    public static String callMoveBeanClickItemSecEffect(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).clickItemSecEffect(_args[0])).getInstance();
    }

    public static String callMoveBeanClickItemSecEffectId(NaSt _str, int... _args) {
        callMoveBeanClickItemSecEffect(_str, _args);
        return getValItemId(_str);
    }

    public static String callMoveBeanClickMove(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).clickMove(_args[0])).getInstance();
    }

    public static String callMoveBeanClickMoveId(NaSt _str, int... _args) {
        callMoveBeanClickMove(_str, _args);
        return getValMoveId(_str);
    }
    public static String callMoveBeanClickMoves(NaSt _str, long... _args) {
        return navigateData(new MoveBeanClickMoves((MoveBean) ((PokemonBeanStruct)_str).getBean()),_str);
    }

    public static String callMoveBeanClickPokemon(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).clickPokemon(_args[0],_args[1])).getInstance();
    }

    public static String callMoveBeanClickPokemonId(NaSt _str, int... _args) {
        callMoveBeanClickPokemon(_str, _args);
        return getValPkId(_str);
    }

//    public static Struct callMoveBeanClickPokemon(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MoveBeanClickPokemon(),_str,_args);
//    }

    public static String callMoveBeanClickPokemonTm(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).clickPokemonTm(_args[0])).getInstance();
    }

    public static String callMoveBeanClickPokemonTmId(NaSt _str, int... _args) {
        callMoveBeanClickPokemonTm(_str, _args);
        return getValPkId(_str);
    }

    public static String callMoveBeanClickPokemonHm(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).clickPokemonHm(_args[0])).getInstance();
    }

    public static String callMoveBeanClickPokemonHmId(NaSt _str, int... _args) {
        callMoveBeanClickPokemonHm(_str, _args);
        return getValPkId(_str);
    }

    public static String callMoveBeanClickPokemonMt(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).clickPokemonMt(_args[0])).getInstance();
    }

    public static String callMoveBeanClickPokemonMtId(NaSt _str, int... _args) {
        callMoveBeanClickPokemonMt(_str, _args);
        return getValPkId(_str);
    }

    public static String callMoveBeanClickRequiredStatus(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).clickRequiredStatus(_args[0])).getInstance();
    }

    public static String callMoveBeanClickRequiredStatusId(NaSt _str, int... _args) {
        callMoveBeanClickRequiredStatus(_str, _args);
        return getValStatusId(_str);
    }

    public static String callMoveBeanClickTypesByOwnedItems(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).clickTypesByOwnedItems(_args[0])).getInstance();
    }

    public static String callMoveBeanClickTypesByOwnedItemsId(NaSt _str, int... _args) {
        callMoveBeanClickTypesByOwnedItems(_str, _args);
        return getValItemId(_str);
    }

    public static String callMoveBeanClickTypesByWeathers(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).clickTypesByWeathers(_args[0])).getInstance();
    }

    public static String callMoveBeanClickTypesByWeathersId(NaSt _str, int... _args) {
        callMoveBeanClickTypesByWeathers(_str, _args);
        return getValMoveId(_str);
    }
    public static NaSt callMoveBeanConstUserChoiceGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getConstUserChoice());
    }

    public static NaSt callMoveBeanDeletedStatusGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getDeletedStatus());
    }

    public static NaSt callMoveBeanDisappearBeforeUseGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getDisappearBeforeUse());
    }

    public static NaSt callMoveBeanDisplayNameGet(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getDisplayName());
    }

    public static NaSt callMoveBeanEffectsGet(NaSt _str, int... _args) {
        return PokemonStandards.getIntArray(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getEffects());
    }

    public static NaSt callMoveBeanGetDeletedStatus(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getDeletedStatus(_args[0]));
    }

//    public static NaSt callMoveBeanGetPage(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MoveBeanGetPage(),_str,_args);
//    }

    public static NaSt callMoveBeanGetRequiredStatus(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getRequiredStatus(_args[0]));
    }

    public static NaSt callMoveBeanGetTrAbility(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbility(_args[0]));
    }

    public static NaSt callMoveBeanGetTrAchieveDisappearedPkUsingMove(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getTrAchieveDisappearedPkUsingMove(_args[0]));
    }

    public static String callMoveBeanClickAchieveDisappearedPkUsingMove(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).clickAchieveDisappearedPkUsingMove(_args[0])).getInstance();
    }

    public static String callMoveBeanClickAchieveDisappearedPkUsingMoveId(NaSt _str, int... _args) {
        callMoveBeanClickAchieveDisappearedPkUsingMove(_str, _args);
        return getValMoveId(_str);
    }
    public static NaSt callMoveBeanGetTrItem(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getTrItem(_args[0]));
    }

    public static NaSt callMoveBeanGetTrMove(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getTrMove(_args[0]));
    }

    public static NaSt callMoveBeanGetTrPokemon(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getTrPokemon(_args[0],_args[1]));
    }

    public static NaSt callMoveBeanGetTrPokemonHm(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getTrPokemonHm(_args[0]));
    }

    public static NaSt callMoveBeanGetTrPokemonMt(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getTrPokemonMt(_args[0]));
    }

    public static NaSt callMoveBeanGetTrPokemonTm(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getTrPokemonTm(_args[0]));
    }

    public static NaSt callMoveBeanGetTrTypesByOwnedItems(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getTrTypesByOwnedItems(_args[0]));
    }

    public static NaSt callMoveBeanGetTrTypesByWeathers(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getTrTypesByWeathers(_args[0]));
    }

    public static NaSt callMoveBeanHasDefaultTypesGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getHasDefaultTypes());
    }

    public static NaSt callMoveBeanIgnVarAccurUserNegGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getIgnVarAccurUserNeg());
    }

    public static NaSt callMoveBeanIgnVarEvasTargetPosGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getIgnVarEvasTargetPos());
    }

    public static NaSt callMoveBeanIsAdjAdv(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isAdjAdv());
    }

    public static NaSt callMoveBeanIsAdjMult(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isAdjMult());
    }

    public static NaSt callMoveBeanIsAdjUniq(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isAdjUniq());
    }

    public static NaSt callMoveBeanIsAfterPrimaryEffect(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isAfterPrimaryEffect(_args[0]));
    }

    public static NaSt callMoveBeanIsAllie(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isAllie());
    }

    public static NaSt callMoveBeanIsAllies(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isAllies());
    }

    public static NaSt callMoveBeanIsAnyFoe(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isAnyFoe());
    }

    public static NaSt callMoveBeanIsAutreUniq(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isAutreUniq());
    }

    public static NaSt callMoveBeanIsBeforePrimaryEffect(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isBeforePrimaryEffect(_args[0]));
    }

    public static NaSt callMoveBeanIsConstAccuracy(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isConstAccuracy());
    }

    public static NaSt callMoveBeanIsDamagingDirectMove(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isDamagingDirectMove());
    }

    public static NaSt callMoveBeanIsDamagingMove(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isDamagingMove());
    }

    public static NaSt callMoveBeanIsEndRoundEffect(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isEndRoundEffect(_args[0]));
    }

    public static NaSt callMoveBeanIsGlobale(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isGlobale());
    }

    public static NaSt callMoveBeanIsItem(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isItem(_args[0]));
    }

    public static NaSt callMoveBeanIsLanceur(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isLanceur());
    }

    public static NaSt callMoveBeanIsPrimaryEffect(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isPrimaryEffect(_args[0]));
    }

    public static NaSt callMoveBeanIsPseudoGlobale(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isPseudoGlobale());
    }

    public static NaSt callMoveBeanIsRepeatedRound(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isRepeatedRound());
    }

    public static NaSt callMoveBeanIsTousAdv(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isTousAdv());
    }

    public static NaSt callMoveBeanIsUniqueImporte(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isUniqueImporte());
    }

    public static NaSt callMoveBeanIsWeather(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isWeather(_args[0]));
    }

    public static NaSt callMoveBeanIsZeroPrepaRound(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isZeroPrepaRound());
    }

    public static NaSt callMoveBeanIsZeroPriority(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).isZeroPriority());
    }

    public static NaSt callMoveBeanItemsGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getItems());
    }

    public static NaSt callMoveBeanMapVarsAccuracyGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getMapVarsAccuracy());
    }

    public static NaSt callMoveBeanMovesHmLearntByPokemonGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getMovesHmLearntByPokemon());
    }

    public static NaSt callMoveBeanMovesLevelLearntByPokemonGet(NaSt _str, int... _args) {
        return PokemonStandards.getShStrList(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getMovesLevelLearntByPokemon());
    }

    public static NaSt callMoveBeanMovesMtLearntByPokemonGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getMovesMtLearntByPokemon());
    }

    public static NaSt callMoveBeanMovesTmLearntByPokemonGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getMovesTmLearntByPokemon());
    }

    public static NaSt callMoveBeanNameGet(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getName());
    }

    public static NaSt callMoveBeanNbPrepaRoundGet(NaSt _str, int... _args) {
        return new NaNbSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getNbPrepaRound());
    }

    public static NaSt callMoveBeanPpGet(NaSt _str, int... _args) {
        return new NaNbSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getPp());
    }

    public static NaSt callMoveBeanPriorityGet(NaSt _str, int... _args) {
        return new NaNbSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getPriority());
    }

    public static NaSt callMoveBeanRankIncrementNbRoundGet(NaSt _str, int... _args) {
        return new NaNbSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getRankIncrementNbRound());
    }

    public static NaSt callMoveBeanRechargeRoundGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getRechargeRound());
    }

    public static NaSt callMoveBeanRepeatRoundLawGet(NaSt _str, int... _args) {
        return PokemonStandards.getLgIntRate(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getRepeatRoundLaw());
    }

    public static NaSt callMoveBeanRequiredStatusGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getRequiredStatus());
    }

    public static NaSt callMoveBeanSecEffectIfNoDamageGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getSecEffectIfNoDamage());
    }

    public static NaSt callMoveBeanSecEffectsByItemGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrInts(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getSecEffectsByItem());
    }

    public static NaSt callMoveBeanSwitchAfterUsingMove(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).switchAfterUsingMove());
    }

    public static NaSt callMoveBeanTranslateItemSecEffect(NaSt _str, int... _args) {
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).translateItemSecEffect(_args[0]));
    }

    public static NaSt callMoveBeanTypesByOwnedItemsGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getTypesByOwnedItems());
    }

    public static NaSt callMoveBeanTypesByWeathersGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getTypesByWeathers());
    }

    public static NaSt callMoveBeanTypesDependOnWeatherAndItem(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).typesDependOnWeatherAndItem());
    }

    public static NaSt callMoveBeanTypesDependOnlyOnItem(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).typesDependOnlyOnItem());
    }

    public static NaSt callMoveBeanTypesDependOnlyOnWeather(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).typesDependOnlyOnWeather());
    }

    public static NaSt callMoveBeanTypesGet(NaSt _str, int... _args) {
        return PokemonStandards.getValues(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).getTypes());
    }

    public static NaSt callMoveBeanEffPrimOrBeforeNotEndRound(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).effPrimOrBeforeNotEndRound(_args[0]));
    }

    public static NaSt callMoveBeanSecNotEndRound(NaSt _str, int... _args) {
        return NaBoSt.of(( (MoveBean) ((PokemonBeanStruct)_str).getInstance()).effSecNotEndRound(_args[0]));
    }
    protected static NaSt dispMove(FacadeGame _fac, int _index) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToMove();
        return transitMove(_index, pk_, all_);
    }

    public static StringMap<NaSt> beanToMove(PkData _pk) {
        StringMap<NaSt> map_ = beanToMoves(_pk);
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.MV_DATA,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ACCURACY,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ALLY,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_BATONPASS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_CLONE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_COMMONSTATISTICS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_COPYFIGHTER,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_COPYMOVE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_COUNTERATTACK,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_DAMAGE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_DAMAGERATE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_FULLHPRATE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_GLOBAL,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_INVOKE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_MULTSUFFEREDMOVEPOWER,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_MULTUSEDMOVEPOWER,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ORDER,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_PROTECTFROMTYPES,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_PROTECTION,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_REMAINEDHPRATE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_RESTRICTION,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_STATIS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_STATUS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHABILITIES,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHITEMS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHMOVESTYPES,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHPOINTVIEW,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHPOSITION,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHTYPES,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_TEAM,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_TEAMWHILESENDINGFOE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_UNPROTECTFROMTYPES,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_VARPP,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_WINMONEY,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.MV_DATA,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ACCURACY,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ALLY,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_BATONPASS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_CLONE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_COMMONSTATISTICS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_COPYFIGHTER,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_COPYMOVE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_COUNTERATTACK,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_DAMAGE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_DAMAGERATE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_FULLHPRATE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_GLOBAL,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_INVOKE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_MULTSUFFEREDMOVEPOWER,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_MULTUSEDMOVEPOWER,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ORDER,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_PROTECTFROMTYPES,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_PROTECTION,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_REMAINEDHPRATE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_RESTRICTION,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_STATIS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_STATUS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHABILITIES,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHITEMS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHMOVESTYPES,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHPOINTVIEW,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHPOSITION,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHTYPES,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_TEAM,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_TEAMWHILESENDINGFOE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_UNPROTECTFROMTYPES,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_VARPP,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_WINMONEY,new TranslationsFile());
        MoveBean move_ = new MoveBean();
        move_.setBuilder(((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder());
        map_.addEntry(BEAN_MOVE, _pk.bean(move_, EN));
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_MOVES_DATA_HTML,move_);
        return map_;
    }
    //    public static StringMap<String> mappingToMove() {
//        StringMap<String> map_ = mappingToMoves();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,AikiBeansMovesStd.BEAN_MOVE);
//        return map_;
//    }
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
