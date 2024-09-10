package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
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
import code.scripts.confs.PkScriptPages;
import code.util.StringMap;

public abstract class InitDbMoveEffectGlobal extends InitDbMoveEffect {

    public static NaSt callEffectGlobalBeanCancelChgtStatGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanCancelChgtStatGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanCancelEffectsGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanCancelEffectsGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanCancelProtectingAbilitiesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanCancelProtectingAbilitiesGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanCanceledIfUsedGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanCanceledIfUsedGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanChangedTypesTerrainGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanChangedTypesTerrainGet(),_str,_args);
    }

    public static String callEffectGlobalBeanClickCancelledAbility(NaSt _str, long... _args) {
        return navigateData(new EffectGlobalBeanClickCancelledAbility(),_str,_args);
    }

    public static String callEffectGlobalBeanClickCancelledAbilityId(NaSt _str, long... _args) {
        callEffectGlobalBeanClickCancelledAbility(_str, _args);
        return getValAbilityId(_str);
    }
    public static String callEffectGlobalBeanClickCancelledEffect(NaSt _str, long... _args) {
        return navigateData(new EffectGlobalBeanClickCancelledEffect(),_str,_args);
    }

    public static String callEffectGlobalBeanClickCancelledEffectId(NaSt _str, long... _args) {
        callEffectGlobalBeanClickCancelledEffect(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickInvokedMove(NaSt _str, long... _args) {
        return navigateData(new EffectGlobalBeanClickInvokedMove(),_str,_args);
    }

    public static String callEffectGlobalBeanClickInvokedMoveId(NaSt _str, long... _args) {
        callEffectGlobalBeanClickInvokedMove(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickInvokingMove(NaSt _str, long... _args) {
        return navigateData(new EffectGlobalBeanClickInvokingMove(),_str,_args);
    }

    public static String callEffectGlobalBeanClickInvokingMoveId(NaSt _str, long... _args) {
        callEffectGlobalBeanClickInvokingMove(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickInvokingMoveTypes(NaSt _str, long... _args) {
        return navigateData(new EffectGlobalBeanClickInvokingMoveTypes(),_str,_args);
    }

    public static String callEffectGlobalBeanClickInvokingMoveTypesId(NaSt _str, long... _args) {
        callEffectGlobalBeanClickInvokingMoveTypes(_str, _args);
        return getValMoveId(_str);
    }

    public static String callEffectGlobalBeanClickMovesTarget(NaSt _str, long... _args) {
        return navigateData(new EffectGlobalBeanClickMovesTarget(),_str,_args);
    }

    public static String callEffectGlobalBeanClickMovesTargetId(NaSt _str, long... _args) {
        callEffectGlobalBeanClickMovesTarget(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickMultMovePower(NaSt _str, long... _args) {
        return navigateData(new EffectGlobalBeanClickMultMovePower(),_str,_args);
    }

    public static String callEffectGlobalBeanClickMultMovePowerId(NaSt _str, long... _args) {
        callEffectGlobalBeanClickMultMovePower(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickPreventedStatus(NaSt _str, long... _args) {
        return navigateData(new EffectGlobalBeanClickPreventedStatus(),_str,_args);
    }

    public static String callEffectGlobalBeanClickPreventedStatusId(NaSt _str, long... _args) {
        callEffectGlobalBeanClickPreventedStatus(_str, _args);
        return getValStatusId(_str);
    }
    public static String callEffectGlobalBeanClickUnusableMove(NaSt _str, long... _args) {
        return navigateData(new EffectGlobalBeanClickUnusableMove(),_str,_args);
    }

    public static String callEffectGlobalBeanClicUnusableMoveId(NaSt _str, long... _args) {
        callEffectGlobalBeanClickUnusableMove(_str, _args);
        return getValMoveId(_str);
    }
    public static NaSt callEffectGlobalBeanDamageEndRoundGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanDamageEndRoundGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanDisableImmuAgainstTypesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanDisableImmuAgainstTypesGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanEfficiencyMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanEfficiencyMovesGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanGetTrCancelledAbility(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrCancelledAbility(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanGetTrCancelledEffect(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrCancelledEffect(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanGetTrInvokedMoveTerrain(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrInvokedMoveTerrain(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanGetTrInvokingMove(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrInvokingMove(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanGetTrInvokingMoveTypes(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrInvokingMoveTypes(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanGetTrMovesTarget(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrMovesTarget(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanGetTrMultMovePower(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrMultMovePower(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanGetTrMultStatIfDamgeTypeFirst(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrMultStatIfDamgeTypeFirst(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanGetTrMultStatIfDamgeTypeSecond(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrMultStatIfDamgeTypeSecond(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanGetTrPreventedStatus(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrPreventedStatus(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanGetTrUnusableMoves(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrUnusableMoves(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanHealingEndRoundGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanHealingEndRoundGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanHealingEndRoundGroundGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanHealingEndRoundGroundGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanImmuneTypesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanImmuneTypesGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanInvokedMoveTerrainGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanInvokedMoveTerrainGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanInvokingMovesChangingTypesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanInvokingMovesChangingTypesGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanInvokingMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanInvokingMovesGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanMovesUsedByTargetedFightersGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanMovesUsedByTargetedFightersGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanMultAccuracyGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanMultAccuracyGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanMultDamagePrepaRoundGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanMultDamagePrepaRoundGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanMultDamageTypesMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanMultDamageTypesMovesGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanMultEffectLovingAllyGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanMultEffectLovingAllyGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanMultPowerMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanMultPowerMovesGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanMultStatIfContainsTypeGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanMultStatIfContainsTypeGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanPreventStatusGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanPreventStatusGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanPuttingKoGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanPuttingKoGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanReverseOrderOfSortBySpeedGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanReverseOrderOfSortBySpeedGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanUnusableItemGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanUnusableItemGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanUnusableMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanUnusableMovesGet(),_str,_args);
    }

    public static NaSt callEffectGlobalBeanWeatherGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanWeatherGet(),_str,_args);
    }
    protected static NaSt dispMoveEffGlobal(FacadeGame _fac, int _index) {
        return dispMoveEffGlobal(_fac, _index,0);
    }
    protected static NaSt dispMoveEffGlobal(FacadeGame _fac, int _index, int _indexEff) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectGlobal(pk_);
        StringMap<String> mapping_ = mappingToEffectGlobal();
        return transitEffect(_index,_indexEff,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectGlobal(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_GLOBAL,_pk.beanEffectGlobalBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectGlobal() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFGLOBAL_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_GLOBAL);
        return map_;
    }
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
