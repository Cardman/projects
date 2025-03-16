package aiki.beans.moves;

import aiki.beans.*;
import aiki.comparators.DictionaryComparator;
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
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.scripts.pages.aiki.*;
import code.sml.util.*;
import code.util.*;

public abstract class InitDbMove extends InitDbMoves {


    public static CustList<TranslatedKey> callMoveBeanAbilitiesGet(MoveBean _str, int... _args) {
        return _str.getAbilities();
    }

    public static String callMoveBeanAccuracyGet(MoveBean _str, int... _args) {
        return _str.getAccuracy();
    }

    public static CustList<TranslatedKey> callMoveBeanAchieveDisappearedPkUsingMoveGet(MoveBean _str, int... _args) {
        return _str.getAchieveDisappearedPkUsingMove();
    }

    public static CustList<TranslatedKey> callMoveBeanAffectedByMovesGet(MoveBean _str, int... _args) {
        return _str.getAffectedByMoves();
    }

    public static CustList<TranslatedKey> callMoveBeanBoostedTypesGet(MoveBean _str, int... _args) {
        return _str.getBoostedTypes();
    }

    public static boolean callMoveBeanCanBeLearnt(MoveBean _str, int... _args) {
        return _str.canBeLearnt();
    }

    public static boolean callMoveBeanCannotKoGet(MoveBean _str, int... _args) {
        return _str.getCannotKo();
    }

    public static String callMoveBeanCategoryGet(MoveBean _str, int... _args) {
        return _str.getCategory().getTranslation();
    }

    public static String callMoveBeanClickAbility(MoveBean _str, int... _args) {
        return _str.clickAbility(_args[0]);
    }

    public static String callMoveBeanClickAbilityId(MoveBean _str, int... _args) {
        callMoveBeanClickAbility(_str, _args);
        return getValAbilityId(_str);
    }

    public static String callMoveBeanClickDeletedStatus(MoveBean _str, int... _args) {
        return _str.clickDeletedStatus(_args[0]);
    }

    public static String callMoveBeanClickDeletedStatusId(MoveBean _str, int... _args) {
        callMoveBeanClickDeletedStatus(_str, _args);
        return getValStatusId(_str);
    }

    public static String callMoveBeanClickItem(MoveBean _str, int... _args) {
        return _str.clickItem(_args[0]);
    }

    public static String callMoveBeanClickItemId(MoveBean _str, int... _args) {
        callMoveBeanClickItem(_str, _args);
        return getValItemId(_str);
    }

    public static String callMoveBeanClickItemSecEffect(MoveBean _str, int... _args) {
        return _str.clickItemSecEffect(_args[0]);
    }

    public static String callMoveBeanClickItemSecEffectId(MoveBean _str, int... _args) {
        callMoveBeanClickItemSecEffect(_str, _args);
        return getValItemId(_str);
    }

    public static String callMoveBeanClickMove(MoveBean _str, int... _args) {
        return _str.clickMove(_args[0]);
    }

    public static String callMoveBeanClickMoveId(MoveBean _str, int... _args) {
        callMoveBeanClickMove(_str, _args);
        return getValMoveId(_str);
    }
    public static String callMoveBeanClickMoves(MoveBean _str, long... _args) {
        return navigateData(new MoveBeanClickMoves(_str),_str);
    }

    public static String callMoveBeanClickPokemon(MoveBean _str, int... _args) {
        return _str.clickPokemon(_args[0],_args[1]);
    }

    public static String callMoveBeanClickPokemonId(MoveBean _str, int... _args) {
        callMoveBeanClickPokemon(_str, _args);
        return getValPkId(_str);
    }

//    public static Struct callMoveBeanClickPokemon(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MoveBeanClickPokemon(),_str,_args);
//    }

    public static String callMoveBeanClickPokemonTm(MoveBean _str, int... _args) {
        return _str.clickPokemonTm(_args[0]);
    }

    public static String callMoveBeanClickPokemonTmId(MoveBean _str, int... _args) {
        callMoveBeanClickPokemonTm(_str, _args);
        return getValPkId(_str);
    }

    public static String callMoveBeanClickPokemonHm(MoveBean _str, int... _args) {
        return _str.clickPokemonHm(_args[0]);
    }

    public static String callMoveBeanClickPokemonHmId(MoveBean _str, int... _args) {
        callMoveBeanClickPokemonHm(_str, _args);
        return getValPkId(_str);
    }

    public static String callMoveBeanClickPokemonMt(MoveBean _str, int... _args) {
        return _str.clickPokemonMt(_args[0]);
    }

    public static String callMoveBeanClickPokemonMtId(MoveBean _str, int... _args) {
        callMoveBeanClickPokemonMt(_str, _args);
        return getValPkId(_str);
    }

    public static String callMoveBeanClickRequiredStatus(MoveBean _str, int... _args) {
        return _str.clickRequiredStatus(_args[0]);
    }

    public static String callMoveBeanClickRequiredStatusId(MoveBean _str, int... _args) {
        callMoveBeanClickRequiredStatus(_str, _args);
        return getValStatusId(_str);
    }

    public static String callMoveBeanClickTypesByOwnedItems(MoveBean _str, int... _args) {
        return _str.clickTypesByOwnedItems(_args[0]);
    }

    public static String callMoveBeanClickTypesByOwnedItemsId(MoveBean _str, int... _args) {
        callMoveBeanClickTypesByOwnedItems(_str, _args);
        return getValItemId(_str);
    }

    public static String callMoveBeanClickTypesByWeathers(MoveBean _str, int... _args) {
        return _str.clickTypesByWeathers(_args[0]);
    }

    public static String callMoveBeanClickTypesByWeathersId(MoveBean _str, int... _args) {
        callMoveBeanClickTypesByWeathers(_str, _args);
        return getValMoveId(_str);
    }
    public static boolean callMoveBeanConstUserChoiceGet(MoveBean _str, int... _args) {
        return _str.getConstUserChoice();
    }

    public static CustList<TranslatedKey> callMoveBeanDeletedStatusGet(MoveBean _str, int... _args) {
        return _str.getDeletedStatus();
    }

    public static boolean callMoveBeanDisappearBeforeUseGet(MoveBean _str, int... _args) {
        return _str.getDisappearBeforeUse();
    }

    public static String callMoveBeanDisplayNameGet(MoveBean _str, int... _args) {
        return _str.getDisplayName();
    }

    public static CustList<Integer> callMoveBeanEffectsGet(MoveBean _str, int... _args) {
        return _str.getEffects();
    }

    public static String callMoveBeanGetDeletedStatus(MoveBean _str, int... _args) {
        return _str.getDeletedStatus(_args[0]);
    }

//    public static NaSt callMoveBeanGetPage(MoveBean _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MoveBeanGetPage(),_str,_args);
//    }

    public static String callMoveBeanGetRequiredStatus(MoveBean _str, int... _args) {
        return _str.getRequiredStatus(_args[0]);
    }

    public static String callMoveBeanGetTrAbility(MoveBean _str, int... _args) {
        return _str.getTrAbility(_args[0]);
    }

    public static String callMoveBeanGetTrAchieveDisappearedPkUsingMove(MoveBean _str, int... _args) {
        return _str.getTrAchieveDisappearedPkUsingMove(_args[0]);
    }

    public static String callMoveBeanClickAchieveDisappearedPkUsingMove(MoveBean _str, int... _args) {
        return _str.clickAchieveDisappearedPkUsingMove(_args[0]);
    }

    public static String callMoveBeanClickAchieveDisappearedPkUsingMoveId(MoveBean _str, int... _args) {
        callMoveBeanClickAchieveDisappearedPkUsingMove(_str, _args);
        return getValMoveId(_str);
    }
    public static String callMoveBeanGetTrItem(MoveBean _str, int... _args) {
        return _str.getTrItem(_args[0]);
    }

    public static String callMoveBeanGetTrMove(MoveBean _str, int... _args) {
        return _str.getTrMove(_args[0]);
    }

    public static String callMoveBeanGetTrPokemon(MoveBean _str, int... _args) {
        return _str.getTrPokemon(_args[0],_args[1]);
    }

    public static String callMoveBeanGetTrPokemonHm(MoveBean _str, int... _args) {
        return _str.getTrPokemonHm(_args[0]);
    }

    public static String callMoveBeanGetTrPokemonMt(MoveBean _str, int... _args) {
        return _str.getTrPokemonMt(_args[0]);
    }

    public static String callMoveBeanGetTrPokemonTm(MoveBean _str, int... _args) {
        return _str.getTrPokemonTm(_args[0]);
    }

    public static String callMoveBeanGetTrTypesByOwnedItems(MoveBean _str, int... _args) {
        return _str.getTrTypesByOwnedItems(_args[0]);
    }

    public static String callMoveBeanGetTrTypesByWeathers(MoveBean _str, int... _args) {
        return _str.getTrTypesByWeathers(_args[0]);
    }

    public static boolean callMoveBeanHasDefaultTypesGet(MoveBean _str, int... _args) {
        return _str.getHasDefaultTypes();
    }

    public static boolean callMoveBeanIgnVarAccurUserNegGet(MoveBean _str, int... _args) {
        return _str.getIgnVarAccurUserNeg();
    }

    public static boolean callMoveBeanIgnVarEvasTargetPosGet(MoveBean _str, int... _args) {
        return _str.getIgnVarEvasTargetPos();
    }

    public static boolean callMoveBeanIsAdjAdv(MoveBean _str, int... _args) {
        return _str.isAdjAdv();
    }

    public static boolean callMoveBeanIsAdjMult(MoveBean _str, int... _args) {
        return _str.isAdjMult();
    }

    public static boolean callMoveBeanIsAdjUniq(MoveBean _str, int... _args) {
        return _str.isAdjUniq();
    }

    public static boolean callMoveBeanIsAfterPrimaryEffect(MoveBean _str, int... _args) {
        return _str.isAfterPrimaryEffect(_args[0]);
    }

    public static boolean callMoveBeanIsAllie(MoveBean _str, int... _args) {
        return _str.isAllie();
    }

    public static boolean callMoveBeanIsAllies(MoveBean _str, int... _args) {
        return _str.isAllies();
    }

    public static boolean callMoveBeanIsAnyFoe(MoveBean _str, int... _args) {
        return _str.isAnyFoe();
    }

    public static boolean callMoveBeanIsAutreUniq(MoveBean _str, int... _args) {
        return _str.isAutreUniq();
    }

    public static boolean callMoveBeanIsBeforePrimaryEffect(MoveBean _str, int... _args) {
        return _str.isBeforePrimaryEffect(_args[0]);
    }

    public static boolean callMoveBeanIsConstAccuracy(MoveBean _str, int... _args) {
        return _str.isConstAccuracy();
    }

    public static boolean callMoveBeanIsDamagingDirectMove(MoveBean _str, int... _args) {
        return _str.isDamagingDirectMove();
    }

    public static boolean callMoveBeanIsDamagingMove(MoveBean _str, int... _args) {
        return _str.isDamagingMove();
    }

    public static boolean callMoveBeanIsEndRoundEffect(MoveBean _str, int... _args) {
        return _str.isEndRoundEffect(_args[0]);
    }

    public static boolean callMoveBeanIsGlobale(MoveBean _str, int... _args) {
        return _str.isGlobale();
    }

    public static boolean callMoveBeanIsItem(MoveBean _str, int... _args) {
        return _str.isItem(_args[0]);
    }

    public static boolean callMoveBeanIsLanceur(MoveBean _str, int... _args) {
        return _str.isLanceur();
    }

    public static boolean callMoveBeanIsPrimaryEffect(MoveBean _str, int... _args) {
        return _str.isPrimaryEffect(_args[0]);
    }

    public static boolean callMoveBeanIsPseudoGlobale(MoveBean _str, int... _args) {
        return _str.isPseudoGlobale();
    }

    public static boolean callMoveBeanIsRepeatedRound(MoveBean _str, int... _args) {
        return _str.isRepeatedRound();
    }

    public static boolean callMoveBeanIsTousAdv(MoveBean _str, int... _args) {
        return _str.isTousAdv();
    }

    public static boolean callMoveBeanIsUniqueImporte(MoveBean _str, int... _args) {
        return _str.isUniqueImporte();
    }

    public static boolean callMoveBeanIsWeather(MoveBean _str, int... _args) {
        return _str.isWeather(_args[0]);
    }

    public static boolean callMoveBeanIsZeroPrepaRound(MoveBean _str, int... _args) {
        return _str.isZeroPrepaRound();
    }

    public static boolean callMoveBeanIsZeroPriority(MoveBean _str, int... _args) {
        return _str.isZeroPriority();
    }

    public static CustList<TranslatedKey> callMoveBeanItemsGet(MoveBean _str, int... _args) {
        return _str.getItems();
    }

    public static AbsMap<String,String> callMoveBeanMapVarsAccuracyGet(MoveBean _str, int... _args) {
        return _str.getMapVarsAccuracy();
    }

    public static CustList<TranslatedKey> callMoveBeanMovesHmLearntByPokemonGet(MoveBean _str, int... _args) {
        return _str.getMovesHmLearntByPokemon();
    }

    public static AbsMap<Long,CustList<TranslatedKey>> callMoveBeanMovesLevelLearntByPokemonGet(MoveBean _str, int... _args) {
        return _str.getMovesLevelLearntByPokemon();
    }

    public static CustList<TranslatedKey> callMoveBeanMovesMtLearntByPokemonGet(MoveBean _str, int... _args) {
        return _str.getMovesMtLearntByPokemon();
    }

    public static CustList<TranslatedKey> callMoveBeanMovesTmLearntByPokemonGet(MoveBean _str, int... _args) {
        return _str.getMovesTmLearntByPokemon();
    }

    public static String callMoveBeanNameGet(MoveBean _str, int... _args) {
        return _str.getName();
    }

    public static long callMoveBeanNbPrepaRoundGet(MoveBean _str, int... _args) {
        return _str.getNbPrepaRound();
    }

    public static long callMoveBeanPpGet(MoveBean _str, int... _args) {
        return _str.getPp();
    }

    public static long callMoveBeanPriorityGet(MoveBean _str, int... _args) {
        return _str.getPriority();
    }

    public static long callMoveBeanRankIncrementNbRoundGet(MoveBean _str, int... _args) {
        return _str.getRankIncrementNbRound();
    }

    public static boolean callMoveBeanRechargeRoundGet(MoveBean _str, int... _args) {
        return _str.getRechargeRound();
    }

    public static AbsMap<LgInt,Rate> callMoveBeanRepeatRoundLawGet(MoveBean _str, int... _args) {
        return _str.getRepeatRoundLaw();
    }

    public static CustList<TranslatedKey> callMoveBeanRequiredStatusGet(MoveBean _str, int... _args) {
        return _str.getRequiredStatus();
    }

    public static boolean callMoveBeanSecEffectIfNoDamageGet(MoveBean _str, int... _args) {
        return _str.getSecEffectIfNoDamage();
    }

    public static AbsMap<TranslatedKey,Ints> callMoveBeanSecEffectsByItemGet(MoveBean _str, int... _args) {
        return _str.getSecEffectsByItem();
    }

    public static boolean callMoveBeanSwitchAfterUsingMove(MoveBean _str, int... _args) {
        return _str.switchAfterUsingMove();
    }

    public static String callMoveBeanTranslateItemSecEffect(MoveBean _str, int... _args) {
        return _str.translateItemSecEffect(_args[0]);
    }

    public static DictionaryComparator<TranslatedKey, TranslatedKey> callMoveBeanTypesByOwnedItemsGet(MoveBean _str, int... _args) {
        return _str.getTypesByOwnedItems();
    }

    public static DictionaryComparator<TranslatedKey, TranslatedKey> callMoveBeanTypesByWeathersGet(MoveBean _str, int... _args) {
        return _str.getTypesByWeathers();
    }

    public static boolean callMoveBeanTypesDependOnWeatherAndItem(MoveBean _str, int... _args) {
        return _str.typesDependOnWeatherAndItem();
    }

    public static boolean callMoveBeanTypesDependOnlyOnItem(MoveBean _str, int... _args) {
        return _str.typesDependOnlyOnItem();
    }

    public static boolean callMoveBeanTypesDependOnlyOnWeather(MoveBean _str, int... _args) {
        return _str.typesDependOnlyOnWeather();
    }

    public static CustList<TranslatedKey> callMoveBeanTypesGet(MoveBean _str, int... _args) {
        return _str.getTypes();
    }

    public static boolean callMoveBeanEffPrimOrBeforeNotEndRound(MoveBean _str, int... _args) {
        return _str.effPrimOrBeforeNotEndRound(_args[0]);
    }

    public static boolean callMoveBeanSecNotEndRound(MoveBean _str, int... _args) {
        return _str.effSecNotEndRound(_args[0]);
    }
    protected static MoveBean dispMove(FacadeGame _fac, int _index) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToMove();
        return transitMove(_index, pk_, all_);
    }

    public static StringMap<BeanRenderWithAppName> beanToMove(PkData _pk) {
        StringMap<BeanRenderWithAppName> map_ = beanToMoves(_pk);
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.MV_DATA,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ACCURACY,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ALLY,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_BATONPASS,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_CLONE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_COMMONSTATISTICS,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_COPYFIGHTER,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_COPYMOVE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_COUNTERATTACK,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_DAMAGE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_DAMAGERATE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_FULLHPRATE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_GLOBAL,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_INVOKE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_MULTSUFFEREDMOVEPOWER,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_MULTUSEDMOVEPOWER,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ORDER,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_PROTECTFROMTYPES,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_PROTECTION,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_REMAINEDHPRATE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_RESTRICTION,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_STATIS,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_STATUS,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHABILITIES,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHITEMS,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHMOVESTYPES,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHPOINTVIEW,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHPOSITION,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHTYPES,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_TEAM,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_TEAMWHILESENDINGFOE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_UNPROTECTFROMTYPES,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_VARPP,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_WINMONEY,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.MV_DATA,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ACCURACY,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ALLY,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_BATONPASS,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_CLONE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_COMMONSTATISTICS,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_COPYFIGHTER,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_COPYMOVE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_COUNTERATTACK,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_DAMAGE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_DAMAGERATE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_FULLHPRATE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_GLOBAL,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_INVOKE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_MULTSUFFEREDMOVEPOWER,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_MULTUSEDMOVEPOWER,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ORDER,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_PROTECTFROMTYPES,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_PROTECTION,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_REMAINEDHPRATE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_RESTRICTION,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_STATIS,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_STATUS,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHABILITIES,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHITEMS,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHMOVESTYPES,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHPOINTVIEW,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHPOSITION,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_SWITCHTYPES,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_TEAM,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_TEAMWHILESENDINGFOE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_UNPROTECTFROMTYPES,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_VARPP,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_WINMONEY,new TranslationsFile());
        MoveBean move_ = new MoveBean();
        move_.setBuilder(map_.getValue(0).getBuilder());
        initBean(move_,EN,_pk.getDataBase());
        map_.addEntry(BEAN_MOVE, move_);
        map_.getValue(0).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_MOVES_DATA_HTML,move_);
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
