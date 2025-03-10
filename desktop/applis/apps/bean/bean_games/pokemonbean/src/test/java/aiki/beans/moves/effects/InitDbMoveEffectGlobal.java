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
import code.maths.Rate;
import code.util.StringMap;

public abstract class InitDbMoveEffectGlobal extends InitDbMoveEffect {

    public static NaSt callEffectGlobalBeanCancelChgtStatGet(NaSt _str, int... _args) {
        return PokemonStandards.getValues(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getCancelChgtStat());
    }

    public static NaSt callEffectGlobalBeanCancelEffectsGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getCancelEffects());
    }

    public static NaSt callEffectGlobalBeanCancelProtectingAbilitiesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getCancelProtectingAbilities());
    }

    public static NaSt callEffectGlobalBeanCanceledIfUsedGet(NaSt _str, int... _args) {
        return NaBoSt.of(((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getCanceledIfUsed());
    }

    public static NaSt callEffectGlobalBeanChangedTypesTerrainGet(NaSt _str, int... _args) {
        return PokemonStandards.getValues(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getChangedTypesTerrain());
    }

    public static String callEffectGlobalBeanClickCancelledAbility(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickCancelledAbility(_args[0])).getInstance();
    }

    public static String callEffectGlobalBeanClickCancelledAbilityId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickCancelledAbility(_str, _args);
        return getValAbilityId(_str);
    }
    public static String callEffectGlobalBeanClickCancelledEffect(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickCancelledEffect(_args[0])).getInstance();
    }

    public static String callEffectGlobalBeanClickCancelledEffectId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickCancelledEffect(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickInvokedMove(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickInvokedMove()).getInstance();
    }

    public static String callEffectGlobalBeanClickInvokedMoveId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickInvokedMove(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickInvokingMove(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickInvokingMove(_args[0])).getInstance();
    }

    public static String callEffectGlobalBeanClickInvokingMoveId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickInvokingMove(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickInvokingMoveTypes(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickInvokingMoveTypes(_args[0])).getInstance();
    }

    public static String callEffectGlobalBeanClickInvokingMoveTypesId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickInvokingMoveTypes(_str, _args);
        return getValMoveId(_str);
    }

    public static String callEffectGlobalBeanClickMovesTarget(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesTarget(_args[0])).getInstance();
    }

    public static String callEffectGlobalBeanClickMovesTargetId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickMovesTarget(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickMultMovePower(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickMultMovePower(_args[0])).getInstance();
    }

    public static String callEffectGlobalBeanClickMultMovePowerId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickMultMovePower(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickPreventedStatus(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickPreventedStatus(_args[0])).getInstance();
    }

    public static String callEffectGlobalBeanClickPreventedStatusId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickPreventedStatus(_str, _args);
        return getValStatusId(_str);
    }
    public static String callEffectGlobalBeanClickUnusableMove(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).clickUnusableMove(_args[0])).getInstance();
    }

    public static String callEffectGlobalBeanClicUnusableMoveId(NaSt _str, int... _args) {
        callEffectGlobalBeanClickUnusableMove(_str, _args);
        return getValMoveId(_str);
    }
    public static NaSt callEffectGlobalBeanDamageEndRoundGet(NaSt _str, int... _args) {
        return new RtSt(((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getDamageEndRound());
    }

    public static NaSt callEffectGlobalBeanDisableImmuAgainstTypesGet(NaSt _str, int... _args) {
        return PokemonStandards.getValues(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getDisableImmuAgainstTypes());
    }

    public static NaSt callEffectGlobalBeanEfficiencyMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getWeatherTypeRateMap(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getEfficiencyMoves());
    }

    public static NaSt callEffectGlobalBeanGetTrCancelledAbility(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrCancelledAbility(_args[0]));
    }

    public static NaSt callEffectGlobalBeanGetTrCancelledEffect(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrCancelledEffect(_args[0]));
    }

    public static NaSt callEffectGlobalBeanGetTrInvokedMoveTerrain(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrInvokedMoveTerrain());
    }

    public static NaSt callEffectGlobalBeanGetTrInvokingMove(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrInvokingMove(_args[0]));
    }

    public static NaSt callEffectGlobalBeanGetTrInvokingMoveTypes(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrInvokingMoveTypes(_args[0]));
    }

    public static NaSt callEffectGlobalBeanGetTrMovesTarget(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesTarget(_args[0]));
    }

    public static NaSt callEffectGlobalBeanGetTrMultMovePower(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrMultMovePower(_args[0]));
    }

    public static NaSt callEffectGlobalBeanGetTrMultStatIfDamgeTypeFirst(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrMultStatIfDamgeTypeFirst(_args[0]));
    }

    public static NaSt callEffectGlobalBeanGetTrMultStatIfDamgeTypeSecond(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrMultStatIfDamgeTypeSecond(_args[0]));
    }

    public static NaSt callEffectGlobalBeanGetTrPreventedStatus(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrPreventedStatus(_args[0]));
    }

    public static NaSt callEffectGlobalBeanGetTrUnusableMoves(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getTrUnusableMoves(_args[0]));
    }

    public static NaSt callEffectGlobalBeanHealingEndRoundGet(NaSt _str, int... _args) {
        return new RtSt(((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getHealingEndRound());
    }

    public static NaSt callEffectGlobalBeanHealingEndRoundGroundGet(NaSt _str, int... _args) {
        return new RtSt(((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getHealingEndRoundGround());
    }

    public static NaSt callEffectGlobalBeanImmuneTypesGet(NaSt _str, int... _args) {
        return PokemonStandards.getValues(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getImmuneTypes());
    }

    public static NaSt callEffectGlobalBeanInvokedMoveTerrainGet(NaSt _str, int... _args) {
        return new NaStSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getInvokedMoveTerrain().getKey());
    }

    public static NaSt callEffectGlobalBeanInvokingMovesChangingTypesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getInvokingMovesChangingTypes());
    }

    public static NaSt callEffectGlobalBeanInvokingMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getInvokingMoves());
    }

    public static NaSt callEffectGlobalBeanMovesUsedByTargetedFightersGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getMovesUsedByTargetedFighters());
    }

    public static NaSt callEffectGlobalBeanMultAccuracyGet(NaSt _str, int... _args) {
        return new RtSt(((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getMultAccuracy());
    }

    public static NaSt callEffectGlobalBeanMultDamagePrepaRoundGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrRateVal(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getMultDamagePrepaRound());
    }

    public static NaSt callEffectGlobalBeanMultDamageTypesMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrRateVal(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getMultDamageTypesMoves());
    }

    public static NaSt callEffectGlobalBeanMultEffectLovingAllyGet(NaSt _str, int... _args) {
        return new RtSt(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getMultEffectLovingAlly());
    }

    public static NaSt callEffectGlobalBeanMultPowerMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrRate(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getMultPowerMoves());
    }

    public static NaSt callEffectGlobalBeanMultStatIfContainsTypeGet(NaSt _str, int... _args) {
        return PokemonStandards.getWcRateMap(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getMultStatIfContainsType());
    }

    public static NaSt callEffectGlobalBeanPreventStatusGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getPreventStatus());
    }

    public static NaSt callEffectGlobalBeanPuttingKoGet(NaSt _str, int... _args) {
        return NaBoSt.of(((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getPuttingKo());
    }

    public static NaSt callEffectGlobalBeanReverseOrderOfSortBySpeedGet(NaSt _str, int... _args) {
        return NaBoSt.of(((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getReverseOrderOfSortBySpeed());
    }

    public static NaSt callEffectGlobalBeanUnusableItemGet(NaSt _str, int... _args) {
        return NaBoSt.of(((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getUnusableItem());
    }

    public static NaSt callEffectGlobalBeanUnusableMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectGlobalBean) ((PokemonBeanStruct)_str).getInstance()).getUnusableMoves());
    }

    public static NaSt callEffectGlobalBeanWeatherGet(NaSt _str, int... _args) {
        return NaBoSt.of(((EffectGlobalBean) ((PokemonBeanStruct) _str).getInstance()).getEffectGlobalCore().getWeather());
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
