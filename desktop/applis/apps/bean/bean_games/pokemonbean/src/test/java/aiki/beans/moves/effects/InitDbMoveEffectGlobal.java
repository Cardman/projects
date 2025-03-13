package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.fight.moves.effects.EffectSwitchTypes;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.util.StatisticType;
import aiki.fight.util.TypesDuo;
import aiki.instances.Instances;
import aiki.map.levels.enums.EnvironmentType;
import code.bean.nat.*;
import aiki.beans.abilities.*;
import aiki.comparators.*;
import code.maths.*;
import code.util.*;

public abstract class InitDbMoveEffectGlobal extends InitDbMoveEffect {

    public static CustList<TranslatedKey> callEffectGlobalBeanCancelChgtStatGet(NaSt _str, int... _args) {
        return (( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getCancelChgtStat());
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanCancelEffectsGet(NaSt _str, int... _args) {
        return (( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getCancelEffects());
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanCancelProtectingAbilitiesGet(NaSt _str, int... _args) {
        return (( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getCancelProtectingAbilities());
    }

    public static boolean callEffectGlobalBeanCanceledIfUsedGet(NaSt _str, int... _args) {
        return ((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getCanceledIfUsed();
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanChangedTypesTerrainGet(NaSt _str, int... _args) {
        return (( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getChangedTypesTerrain());
    }

    public static String callEffectGlobalBeanClickCancelledAbility(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickCancelledAbility(_args[0]);
    }

    public static String callEffectGlobalBeanClickCancelledAbilityId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickCancelledAbility(_str, _args);
        return getValAbilityId(_str);
    }
    public static String callEffectGlobalBeanClickCancelledEffect(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickCancelledEffect(_args[0]);
    }

    public static String callEffectGlobalBeanClickCancelledEffectId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickCancelledEffect(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickInvokedMove(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickInvokedMove();
    }

    public static String callEffectGlobalBeanClickInvokedMoveId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickInvokedMove(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickInvokingMove(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickInvokingMove(_args[0]);
    }

    public static String callEffectGlobalBeanClickInvokingMoveId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickInvokingMove(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickInvokingMoveTypes(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickInvokingMoveTypes(_args[0]);
    }

    public static String callEffectGlobalBeanClickInvokingMoveTypesId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickInvokingMoveTypes(_str, _args);
        return getValMoveId(_str);
    }

    public static String callEffectGlobalBeanClickMovesTarget(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesTarget(_args[0]);
    }

    public static String callEffectGlobalBeanClickMovesTargetId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickMovesTarget(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickMultMovePower(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickMultMovePower(_args[0]);
    }

    public static String callEffectGlobalBeanClickMultMovePowerId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickMultMovePower(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickPreventedStatus(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickPreventedStatus(_args[0]);
    }

    public static String callEffectGlobalBeanClickPreventedStatusId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickPreventedStatus(_str, _args);
        return getValStatusId(_str);
    }
    public static String callEffectGlobalBeanClickUnusableMove(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickUnusableMove(_args[0]);
    }

    public static String callEffectGlobalBeanClicUnusableMoveId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickUnusableMove(_str, _args);
        return getValMoveId(_str);
    }
    public static Rate callEffectGlobalBeanDamageEndRoundGet(NaSt _str, int... _args) {
        return ((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getDamageEndRound();
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanDisableImmuAgainstTypesGet(NaSt _str, int... _args) {
        return (( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getDisableImmuAgainstTypes());
    }

    public static AbsMap<TranslatedKeyPair,Rate> callEffectGlobalBeanEfficiencyMovesGet(NaSt _str, int... _args) {
        return (( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getEfficiencyMoves());
    }

    public static String callEffectGlobalBeanGetTrCancelledAbility(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrCancelledAbility(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrCancelledEffect(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrCancelledEffect(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrInvokedMoveTerrain(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrInvokedMoveTerrain();
    }

    public static String callEffectGlobalBeanGetTrInvokingMove(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrInvokingMove(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrInvokingMoveTypes(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrInvokingMoveTypes(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrMovesTarget(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesTarget(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrMultMovePower(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrMultMovePower(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrMultStatIfDamgeTypeFirst(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrMultStatIfDamgeTypeFirst(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrMultStatIfDamgeTypeSecond(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrMultStatIfDamgeTypeSecond(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrPreventedStatus(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrPreventedStatus(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrUnusableMoves(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrUnusableMoves(_args[0]);
    }

    public static Rate callEffectGlobalBeanHealingEndRoundGet(NaSt _str, int... _args) {
        return ((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getHealingEndRound();
    }

    public static Rate callEffectGlobalBeanHealingEndRoundGroundGet(NaSt _str, int... _args) {
        return ((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getHealingEndRoundGround();
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanImmuneTypesGet(NaSt _str, int... _args) {
        return (( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getImmuneTypes());
    }

    public static String callEffectGlobalBeanInvokedMoveTerrainGet(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getInvokedMoveTerrain().getKey();
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanInvokingMovesChangingTypesGet(NaSt _str, int... _args) {
        return (( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getInvokingMovesChangingTypes());
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanInvokingMovesGet(NaSt _str, int... _args) {
        return (( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getInvokingMoves());
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanMovesUsedByTargetedFightersGet(NaSt _str, int... _args) {
        return (( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getMovesUsedByTargetedFighters());
    }

    public static Rate callEffectGlobalBeanMultAccuracyGet(NaSt _str, int... _args) {
        return ((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getMultAccuracy();
    }

    public static DictionaryComparator<TranslatedKey,Rate> callEffectGlobalBeanMultDamagePrepaRoundGet(NaSt _str, int... _args) {
        return (( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getMultDamagePrepaRound());
    }

    public static DictionaryComparator<TranslatedKey,Rate> callEffectGlobalBeanMultDamageTypesMovesGet(NaSt _str, int... _args) {
        return (( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getMultDamageTypesMoves());
    }

    public static Rate callEffectGlobalBeanMultEffectLovingAllyGet(NaSt _str, int... _args) {
        return ( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getMultEffectLovingAlly();
    }

    public static DictionaryComparator<TranslatedKey,Rate> callEffectGlobalBeanMultPowerMovesGet(NaSt _str, int... _args) {
        return (( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getMultPowerMoves());
    }

    public static AbsMap<TranslatedKeyPair,Rate> callEffectGlobalBeanMultStatIfContainsTypeGet(NaSt _str, int... _args) {
        return (( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getMultStatIfContainsType());
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanPreventStatusGet(NaSt _str, int... _args) {
        return (( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getPreventStatus());
    }

    public static boolean callEffectGlobalBeanPuttingKoGet(NaSt _str, int... _args) {
        return ((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getPuttingKo();
    }

    public static boolean callEffectGlobalBeanReverseOrderOfSortBySpeedGet(NaSt _str, int... _args) {
        return ((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getReverseOrderOfSortBySpeed();
    }

    public static boolean callEffectGlobalBeanUnusableItemGet(NaSt _str, int... _args) {
        return ((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getUnusableItem();
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanUnusableMovesGet(NaSt _str, int... _args) {
        return (( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getUnusableMoves());
    }

    public static boolean callEffectGlobalBeanWeatherGet(NaSt _str, int... _args) {
        return ((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getWeather();
    }
    protected static NaSt dispMoveEffGlobal(FacadeGame _fac, int _index) {
        return dispMoveEffGlobal(_fac, _index,0);
    }
    protected static NaSt dispMoveEffGlobal(FacadeGame _fac, int _index, int _indexEff) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectGlobal();
        return transitEffect(_index,_indexEff,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectGlobal(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_GLOBAL,_pk.beanEffectGlobalBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectGlobal() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFGLOBAL_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_GLOBAL);
//        return map_;
//    }
    protected static FacadeGame feedDbMoveEffDataDam(EffectGlobal _eff) {
        FacadeGame facade_ = facade();
        addEff(_eff, facade_);
        StatusMoveData chg_ = moveSta(TargetChoice.TOUS_ADV);
        EffectSwitchTypes sw_ = Instances.newEffectSwitchTypes();
        sw_.getChgtTypeByEnv().addEntry(EnvironmentType.ROAD,T_TYPE1);
        chg_.getEffects().add(sw_);
        facade_.getData().completeMembers(M_STA, chg_);
        StatusMoveData minv_ = moveSta(TargetChoice.TOUS_ADV);
        EffectInvoke inv_ = Instances.newEffectInvoke();
        inv_.getMoveFctEnv().addEntry(EnvironmentType.ROAD,T_TYPE1);
        minv_.getEffects().add(inv_);
        facade_.getData().completeMembers(M_WEA, minv_);
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveEffDataDamMovesChangingTypes(EffectGlobal _eff) {
        FacadeGame facade_ = facade();
        addEff(_eff, facade_);
        StatusMoveData st_ = moveSta(TargetChoice.TOUS_ADV);
        st_.getEffects().add(Instances.newEffectSwitchTypes());
        facade_.getData().completeMembers(M_STA, st_);
        StatusMoveData chg_ = moveSta(TargetChoice.TOUS_ADV);
        EffectSwitchTypes sw_ = Instances.newEffectSwitchTypes();
        sw_.getChgtTypeByEnv().addEntry(EnvironmentType.ROAD,T_TYPE1);
        chg_.getEffects().add(sw_);
        facade_.getData().completeMembers(M_WEA, chg_);
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveEffDataDamMoves(EffectGlobal _eff) {
        FacadeGame facade_ = facade();
        addEff(_eff, facade_);
        StatusMoveData st_ = moveSta(TargetChoice.TOUS_ADV);
        st_.getEffects().add(Instances.newEffectStatistic());
        st_.getEffects().add(Instances.newEffectInvoke());
        facade_.getData().completeMembers(M_STA, st_);
        StatusMoveData minv_ = moveSta(TargetChoice.TOUS_ADV);
        EffectInvoke inv_ = Instances.newEffectInvoke();
        inv_.getMoveFctEnv().addEntry(EnvironmentType.ROAD,T_TYPE1);
        minv_.getEffects().add(inv_);
        facade_.getData().completeMembers(M_WEA, minv_);
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void addEff(EffectGlobal _eff, FacadeGame _facade) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, _eff);
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectGlobal eff(boolean _weather, boolean _puttingKo, boolean _unusableItem, boolean _canceledIfUsed, boolean _reverseOrderOfSortBySpeed) {
        EffectGlobal e_ = Instances.newEffectGlobal();
        e_.setWeather(_weather);
        e_.setPuttingKo(_puttingKo);
        e_.setUnusableItem(_unusableItem);
        e_.setCanceledIfUsed(_canceledIfUsed);
        e_.setReverseOrderOfSortBySpeed(_reverseOrderOfSortBySpeed);
        e_.setMultAccuracy(Rate.one());
        e_.setDamageEndRound(Rate.one());
        e_.setHealingEndRound(Rate.one());
        e_.setHealingEndRoundGround(Rate.one());
        e_.setMultEffectLovingAlly(Rate.one());
        e_.getDisableImmuAgainstTypes().add(T_TYPE1);
        e_.getMultDamageTypesMoves().addEntry(T_TYPE1,Rate.one());
        e_.getMultPowerMoves().addEntry(M_STA,Rate.one());
        e_.getUnusableMoves().add(M_STA);
        e_.getCancelEffects().add(M_STA);
        e_.getCancelProtectingAbilities().add(A_ABILITY);
        e_.getEfficiencyMoves().addEntry(new TypesDuo(T_TYPE1,T_TYPE2),Rate.one());
        e_.getCancelChgtStat().add(Statistic.SPEED);
        e_.setInvokedMoveTerrain(M_STA);
        e_.getChangedTypesTerrain().add(T_TYPE1);
        e_.getImmuneTypes().add(T_TYPE1);
        e_.getPreventStatus().add(S_STA_SIM);
        e_.getMultStatIfContainsType().addEntry(new StatisticType(Statistic.SPEED,T_TYPE1),Rate.one());
        e_.getMultDamagePrepaRound().addEntry(T_TYPE1,Rate.one());
        e_.getMovesUsedByTargetedFighters().add(M_STA);
        return e_;
    }
}
