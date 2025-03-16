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
import aiki.beans.abilities.*;
import aiki.comparators.*;
import code.maths.*;
import code.util.*;

public abstract class InitDbMoveEffectGlobal extends InitDbMoveEffect {

    public static CustList<TranslatedKey> callEffectGlobalBeanCancelChgtStatGet(EffectGlobalBean _str, int... _args) {
        return _str.getCancelChgtStat();
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanCancelEffectsGet(EffectGlobalBean _str, int... _args) {
        return _str.getCancelEffects();
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanCancelProtectingAbilitiesGet(EffectGlobalBean _str, int... _args) {
        return _str.getCancelProtectingAbilities();
    }

    public static boolean callEffectGlobalBeanCanceledIfUsedGet(EffectGlobalBean _str, int... _args) {
        return _str.getEffectGlobalCore().getCanceledIfUsed();
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanChangedTypesTerrainGet(EffectGlobalBean _str, int... _args) {
        return _str.getChangedTypesTerrain();
    }

    public static String callEffectGlobalBeanClickCancelledAbility(EffectGlobalBean _str, int... _args) {
        return _str.clickCancelledAbility(_args[0]);
    }

    public static String callEffectGlobalBeanClickCancelledAbilityId(EffectGlobalBean _str, int... _args) {
        callEffectGlobalBeanClickCancelledAbility(_str, _args);
        return getValAbilityId(_str);
    }
    public static String callEffectGlobalBeanClickCancelledEffect(EffectGlobalBean _str, int... _args) {
        return _str.clickCancelledEffect(_args[0]);
    }

    public static String callEffectGlobalBeanClickCancelledEffectId(EffectGlobalBean _str, int... _args) {
        callEffectGlobalBeanClickCancelledEffect(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickInvokedMove(EffectGlobalBean _str, int... _args) {
        return _str.clickInvokedMove();
    }

    public static String callEffectGlobalBeanClickInvokedMoveId(EffectGlobalBean _str, int... _args) {
        callEffectGlobalBeanClickInvokedMove(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickInvokingMove(EffectGlobalBean _str, int... _args) {
        return _str.clickInvokingMove(_args[0]);
    }

    public static String callEffectGlobalBeanClickInvokingMoveId(EffectGlobalBean _str, int... _args) {
        callEffectGlobalBeanClickInvokingMove(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickInvokingMoveTypes(EffectGlobalBean _str, int... _args) {
        return _str.clickInvokingMoveTypes(_args[0]);
    }

    public static String callEffectGlobalBeanClickInvokingMoveTypesId(EffectGlobalBean _str, int... _args) {
        callEffectGlobalBeanClickInvokingMoveTypes(_str, _args);
        return getValMoveId(_str);
    }

    public static String callEffectGlobalBeanClickMovesTarget(EffectGlobalBean _str, int... _args) {
        return _str.clickMovesTarget(_args[0]);
    }

    public static String callEffectGlobalBeanClickMovesTargetId(EffectGlobalBean _str, int... _args) {
        callEffectGlobalBeanClickMovesTarget(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickMultMovePower(EffectGlobalBean _str, int... _args) {
        return _str.clickMultMovePower(_args[0]);
    }

    public static String callEffectGlobalBeanClickMultMovePowerId(EffectGlobalBean _str, int... _args) {
        callEffectGlobalBeanClickMultMovePower(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectGlobalBeanClickPreventedStatus(EffectGlobalBean _str, int... _args) {
        return _str.clickPreventedStatus(_args[0]);
    }

    public static String callEffectGlobalBeanClickPreventedStatusId(EffectGlobalBean _str, int... _args) {
        callEffectGlobalBeanClickPreventedStatus(_str, _args);
        return getValStatusId(_str);
    }
    public static String callEffectGlobalBeanClickUnusableMove(EffectGlobalBean _str, int... _args) {
        return _str.clickUnusableMove(_args[0]);
    }

    public static String callEffectGlobalBeanClicUnusableMoveId(EffectGlobalBean _str, int... _args) {
        callEffectGlobalBeanClickUnusableMove(_str, _args);
        return getValMoveId(_str);
    }
    public static Rate callEffectGlobalBeanDamageEndRoundGet(EffectGlobalBean _str, int... _args) {
        return _str.getEffectGlobalCore().getDamageEndRound();
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanDisableImmuAgainstTypesGet(EffectGlobalBean _str, int... _args) {
        return _str.getDisableImmuAgainstTypes();
    }

    public static AbsMap<TranslatedKeyPair,Rate> callEffectGlobalBeanEfficiencyMovesGet(EffectGlobalBean _str, int... _args) {
        return _str.getEfficiencyMoves();
    }

    public static String callEffectGlobalBeanGetTrCancelledAbility(EffectGlobalBean _str, int... _args) {
        return _str.getTrCancelledAbility(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrCancelledEffect(EffectGlobalBean _str, int... _args) {
        return _str.getTrCancelledEffect(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrInvokedMoveTerrain(EffectGlobalBean _str, int... _args) {
        return _str.getTrInvokedMoveTerrain();
    }

    public static String callEffectGlobalBeanGetTrInvokingMove(EffectGlobalBean _str, int... _args) {
        return _str.getTrInvokingMove(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrInvokingMoveTypes(EffectGlobalBean _str, int... _args) {
        return _str.getTrInvokingMoveTypes(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrMovesTarget(EffectGlobalBean _str, int... _args) {
        return _str.getTrMovesTarget(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrMultMovePower(EffectGlobalBean _str, int... _args) {
        return _str.getTrMultMovePower(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrMultStatIfDamgeTypeFirst(EffectGlobalBean _str, int... _args) {
        return _str.getTrMultStatIfDamgeTypeFirst(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrMultStatIfDamgeTypeSecond(EffectGlobalBean _str, int... _args) {
        return _str.getTrMultStatIfDamgeTypeSecond(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrPreventedStatus(EffectGlobalBean _str, int... _args) {
        return _str.getTrPreventedStatus(_args[0]);
    }

    public static String callEffectGlobalBeanGetTrUnusableMoves(EffectGlobalBean _str, int... _args) {
        return _str.getTrUnusableMoves(_args[0]);
    }

    public static Rate callEffectGlobalBeanHealingEndRoundGet(EffectGlobalBean _str, int... _args) {
        return _str.getEffectGlobalCore().getHealingEndRound();
    }

    public static Rate callEffectGlobalBeanHealingEndRoundGroundGet(EffectGlobalBean _str, int... _args) {
        return _str.getEffectGlobalCore().getHealingEndRoundGround();
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanImmuneTypesGet(EffectGlobalBean _str, int... _args) {
        return _str.getImmuneTypes();
    }

    public static String callEffectGlobalBeanInvokedMoveTerrainGet(EffectGlobalBean _str, int... _args) {
        return _str.getInvokedMoveTerrain().getKey();
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanInvokingMovesChangingTypesGet(EffectGlobalBean _str, int... _args) {
        return _str.getInvokingMovesChangingTypes();
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanInvokingMovesGet(EffectGlobalBean _str, int... _args) {
        return _str.getInvokingMoves();
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanMovesUsedByTargetedFightersGet(EffectGlobalBean _str, int... _args) {
        return _str.getMovesUsedByTargetedFighters();
    }

    public static Rate callEffectGlobalBeanMultAccuracyGet(EffectGlobalBean _str, int... _args) {
        return _str.getEffectGlobalCore().getMultAccuracy();
    }

    public static DictionaryComparator<TranslatedKey,Rate> callEffectGlobalBeanMultDamagePrepaRoundGet(EffectGlobalBean _str, int... _args) {
        return _str.getMultDamagePrepaRound();
    }

    public static DictionaryComparator<TranslatedKey,Rate> callEffectGlobalBeanMultDamageTypesMovesGet(EffectGlobalBean _str, int... _args) {
        return _str.getMultDamageTypesMoves();
    }

    public static Rate callEffectGlobalBeanMultEffectLovingAllyGet(EffectGlobalBean _str, int... _args) {
        return _str.getMultEffectLovingAlly();
    }

    public static DictionaryComparator<TranslatedKey,Rate> callEffectGlobalBeanMultPowerMovesGet(EffectGlobalBean _str, int... _args) {
        return _str.getMultPowerMoves();
    }

    public static AbsMap<TranslatedKeyPair,Rate> callEffectGlobalBeanMultStatIfContainsTypeGet(EffectGlobalBean _str, int... _args) {
        return _str.getMultStatIfContainsType();
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanPreventStatusGet(EffectGlobalBean _str, int... _args) {
        return _str.getPreventStatus();
    }

    public static boolean callEffectGlobalBeanPuttingKoGet(EffectGlobalBean _str, int... _args) {
        return _str.getEffectGlobalCore().getPuttingKo();
    }

    public static boolean callEffectGlobalBeanReverseOrderOfSortBySpeedGet(EffectGlobalBean _str, int... _args) {
        return _str.getEffectGlobalCore().getReverseOrderOfSortBySpeed();
    }

    public static boolean callEffectGlobalBeanUnusableItemGet(EffectGlobalBean _str, int... _args) {
        return _str.getEffectGlobalCore().getUnusableItem();
    }

    public static CustList<TranslatedKey> callEffectGlobalBeanUnusableMovesGet(EffectGlobalBean _str, int... _args) {
        return _str.getUnusableMoves();
    }

    public static boolean callEffectGlobalBeanWeatherGet(EffectGlobalBean _str, int... _args) {
        return _str.getEffectGlobalCore().getWeather();
    }
    protected static EffectGlobalBean dispMoveEffGlobal(FacadeGame _fac, int _index) {
        return dispMoveEffGlobal(_fac, _index,0);
    }
    protected static EffectGlobalBean dispMoveEffGlobal(FacadeGame _fac, int _index, int _indexEff) {
        FacadeGame pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectGlobal();
        return (EffectGlobalBean)transitEffect(_index,_indexEff,pk_,all_);
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
