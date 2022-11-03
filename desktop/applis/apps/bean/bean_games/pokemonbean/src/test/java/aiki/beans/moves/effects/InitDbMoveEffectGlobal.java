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
import code.expressionlanguage.structs.Struct;
import code.maths.Rate;
import code.util.StringMap;

public abstract class InitDbMoveEffectGlobal extends InitDbMoveEffect {

    public static Struct callEffectGlobalBeanCancelChgtStatGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanCancelChgtStatGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanCancelEffectsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanCancelEffectsGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanCancelProtectingAbilitiesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanCancelProtectingAbilitiesGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanCanceledIfUsedGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanCanceledIfUsedGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanChangedTypesTerrainGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanChangedTypesTerrainGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanClickCancelledAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanClickCancelledAbility(),_str,_args);
    }

    public static Struct callEffectGlobalBeanClickCancelledEffect(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanClickCancelledEffect(),_str,_args);
    }

    public static Struct callEffectGlobalBeanClickInvokedMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanClickInvokedMove(),_str,_args);
    }

    public static Struct callEffectGlobalBeanClickInvokingMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanClickInvokingMove(),_str,_args);
    }

    public static Struct callEffectGlobalBeanClickInvokingMoveTypes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanClickInvokingMoveTypes(),_str,_args);
    }

    public static Struct callEffectGlobalBeanClickMovesTarget(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanClickMovesTarget(),_str,_args);
    }

    public static Struct callEffectGlobalBeanClickMultMovePower(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanClickMultMovePower(),_str,_args);
    }

    public static Struct callEffectGlobalBeanClickPreventedStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanClickPreventedStatus(),_str,_args);
    }

    public static Struct callEffectGlobalBeanClickUnusableMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanClickUnusableMove(),_str,_args);
    }

    public static Struct callEffectGlobalBeanDamageEndRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanDamageEndRoundGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanDisableImmuAgainstTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanDisableImmuAgainstTypesGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanEfficiencyMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanEfficiencyMovesGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanGetTrCancelledAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrCancelledAbility(),_str,_args);
    }

    public static Struct callEffectGlobalBeanGetTrCancelledEffect(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrCancelledEffect(),_str,_args);
    }

    public static Struct callEffectGlobalBeanGetTrInvokedMoveTerrain(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrInvokedMoveTerrain(),_str,_args);
    }

    public static Struct callEffectGlobalBeanGetTrInvokingMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrInvokingMove(),_str,_args);
    }

    public static Struct callEffectGlobalBeanGetTrInvokingMoveTypes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrInvokingMoveTypes(),_str,_args);
    }

    public static Struct callEffectGlobalBeanGetTrMovesTarget(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrMovesTarget(),_str,_args);
    }

    public static Struct callEffectGlobalBeanGetTrMultMovePower(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrMultMovePower(),_str,_args);
    }

    public static Struct callEffectGlobalBeanGetTrMultStatIfDamgeTypeFirst(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrMultStatIfDamgeTypeFirst(),_str,_args);
    }

    public static Struct callEffectGlobalBeanGetTrMultStatIfDamgeTypeSecond(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrMultStatIfDamgeTypeSecond(),_str,_args);
    }

    public static Struct callEffectGlobalBeanGetTrPreventedStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrPreventedStatus(),_str,_args);
    }

    public static Struct callEffectGlobalBeanGetTrUnusableMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanGetTrUnusableMoves(),_str,_args);
    }

    public static Struct callEffectGlobalBeanHealingEndRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanHealingEndRoundGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanHealingEndRoundGroundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanHealingEndRoundGroundGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanImmuneTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanImmuneTypesGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanInvokedMoveTerrainGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanInvokedMoveTerrainGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanInvokingMovesChangingTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanInvokingMovesChangingTypesGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanInvokingMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanInvokingMovesGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanMovesUsedByTargetedFightersGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanMovesUsedByTargetedFightersGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanMultAccuracyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanMultAccuracyGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanMultDamagePrepaRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanMultDamagePrepaRoundGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanMultDamageTypesMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanMultDamageTypesMovesGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanMultEffectLovingAllyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanMultEffectLovingAllyGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanMultPowerMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanMultPowerMovesGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanMultStatIfContainsTypeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanMultStatIfContainsTypeGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanPreventStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanPreventStatusGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanPuttingKoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanPuttingKoGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanReverseOrderOfSortBySpeedGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanReverseOrderOfSortBySpeedGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanUnusableItemGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanUnusableItemGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanUnusableMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanUnusableMovesGet(),_str,_args);
    }

    public static Struct callEffectGlobalBeanWeatherGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectGlobalBeanWeatherGet(),_str,_args);
    }
    protected static Struct dispMoveEffGlobal(FacadeGame _fac, int _index) {
        return dispMoveEffGlobal(_fac, _index,0);
    }
    protected static Struct dispMoveEffGlobal(FacadeGame _fac, int _index, int _indexEff) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<Struct> all_ = beanToEffectGlobal(pk_);
        StringMap<String> mapping_ = mappingToEffectGlobal();
        return transitEffect(_index,_indexEff,pk_,all_,mapping_);
    }
    public static StringMap<Struct> beanToEffectGlobal(PkData _pk) {
        StringMap<Struct> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_GLOBAL,_pk.beanEffectGlobalBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectGlobal() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFFGLOBAL_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_GLOBAL);
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
