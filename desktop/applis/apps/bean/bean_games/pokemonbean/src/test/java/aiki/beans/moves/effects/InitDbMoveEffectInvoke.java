package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.fight.Fight;
import aiki.instances.Instances;
import aiki.map.levels.enums.EnvironmentType;
import code.expressionlanguage.structs.Struct;
import code.maths.Rate;
import code.util.IdMap;
import code.util.StringMap;

public abstract class InitDbMoveEffectInvoke extends InitDbMoveEffect{

    public static final String ROAD_TR = "ROAD_TR";

    public static String callEffectInvokeBeanClickGlobalMoveFctEnv(Struct _str, long... _args) {
        return navigateData(new EffectInvokeBeanClickGlobalMoveFctEnv(),_str,_args);
    }

    public static String callEffectInvokeBeanClickGlobalMoveFctEnvId(Struct _str, long... _args) {
        callEffectInvokeBeanClickGlobalMoveFctEnv(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectInvokeBeanClickMoveFctEnv(Struct _str, long... _args) {
        return navigateData(new EffectInvokeBeanClickMoveFctEnv(),_str,_args);
    }
    public static String callEffectInvokeBeanClickMoveFctEnvId(Struct _str, long... _args) {
        callEffectInvokeBeanClickMoveFctEnv(_str,_args);
        return getValMoveId(_str);
    }
    public static String callEffectInvokeBeanClickMoveNotInvok(Struct _str, long... _args) {
        return navigateData(new EffectInvokeBeanClickMoveNotInvok(),_str,_args);
    }

    public static String callEffectInvokeBeanClickMoveNotInvokId(Struct _str, long... _args) {
        callEffectInvokeBeanClickMoveNotInvok(_str,_args);
        return getValMoveId(_str);
    }
    public static String callEffectInvokeBeanClickMoveUserTypes(Struct _str, long... _args) {
        return navigateData(new EffectInvokeBeanClickMoveUserTypes(),_str,_args);
    }

    public static String callEffectInvokeBeanClickMoveUserTypesId(Struct _str, long... _args) {
        callEffectInvokeBeanClickMoveUserTypes(_str, _args);
        return getValMoveId(_str);
    }
    public static Struct callEffectInvokeBeanGetTrEnv(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanGetTrEnv(),_str,_args);
    }

    public static Struct callEffectInvokeBeanGetTrGlobalMoveFctEnv(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanGetTrGlobalMoveFctEnv(),_str,_args);
    }

    public static Struct callEffectInvokeBeanGetTrMoveFctEnv(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanGetTrMoveFctEnv(),_str,_args);
    }

    public static Struct callEffectInvokeBeanGetTrMoveNotInvok(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanGetTrMoveNotInvok(),_str,_args);
    }

    public static Struct callEffectInvokeBeanGetTrMoveUserTypes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanGetTrMoveUserTypes(),_str,_args);
    }

    public static Struct callEffectInvokeBeanGetTrUserTypes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanGetTrUserTypes(),_str,_args);
    }

    public static Struct callEffectInvokeBeanGlobalMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanGlobalMovesGet(),_str,_args);
    }

    public static Struct callEffectInvokeBeanInvokingAllyMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanInvokingAllyMoveGet(),_str,_args);
    }

    public static Struct callEffectInvokeBeanInvokingMoveButUserGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanInvokingMoveButUserGet(),_str,_args);
    }

    public static Struct callEffectInvokeBeanInvokingMoveByUserTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanInvokingMoveByUserTypesGet(),_str,_args);
    }

    public static Struct callEffectInvokeBeanInvokingSufferedMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanInvokingSufferedMoveGet(),_str,_args);
    }

    public static Struct callEffectInvokeBeanInvokingTargetChosenMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanInvokingTargetChosenMoveGet(),_str,_args);
    }

    public static Struct callEffectInvokeBeanInvokingTargetSuccesfulMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanInvokingTargetSuccesfulMoveGet(),_str,_args);
    }

    public static Struct callEffectInvokeBeanInvokingUserMoveWhileSleepGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanInvokingUserMoveWhileSleepGet(),_str,_args);
    }

    public static Struct callEffectInvokeBeanIsType(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanIsType(),_str,_args);
    }

    public static Struct callEffectInvokeBeanMoveFctEnvGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanMoveFctEnvGet(),_str,_args);
    }

    public static Struct callEffectInvokeBeanMovesNotToBeInvokedGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanMovesNotToBeInvokedGet(),_str,_args);
    }

    public static Struct callEffectInvokeBeanRateInvokationMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanRateInvokationMoveGet(),_str,_args);
    }
    protected static Struct dispMoveEffInvoke(boolean _invokingAllyMove, boolean _invokingMoveButUser, boolean _invokingSufferedMove, boolean _invokingTargetChosenMove, boolean _invokingTargetSuccesfulMove, boolean _invokingUserMoveWhileSleep) {
        return dispMoveEffInvoke(feedDbMoveEffDataInvoke(_invokingAllyMove, _invokingMoveButUser, _invokingSufferedMove, _invokingTargetChosenMove, _invokingTargetSuccesfulMove, _invokingUserMoveWhileSleep));
    }
    protected static Struct dispMoveEffInvoke(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<Struct> all_ = beanToEffectInvoke(pk_);
        StringMap<String> mapping_ = mappingToEffectInvoke();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<Struct> beanToEffectInvoke(PkData _pk) {
        StringMap<Struct> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_INVOKE,_pk.beanEffectInvokeBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectInvoke() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFFINVOKE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_INVOKE);
        return map_;
    }
    protected static FacadeGame feedDbMoveEffDataInvoke(boolean _invokingAllyMove, boolean _invokingMoveButUser, boolean _invokingSufferedMove, boolean _invokingTargetChosenMove, boolean _invokingTargetSuccesfulMove, boolean _invokingUserMoveWhileSleep) {
        FacadeGame facade_ = facade();
        addEffInvoke(facade_, _invokingAllyMove, _invokingMoveButUser, _invokingSufferedMove, _invokingTargetChosenMove, _invokingTargetSuccesfulMove, _invokingUserMoveWhileSleep);
        StatusMoveData chg_ = moveSta(TargetChoice.TOUS_ADV);
        EffectGlobal sw_ = Instances.newEffectGlobal();
        sw_.setInvokedMoveTerrain(M_WEA);
        chg_.getEffects().add(sw_);
        facade_.getData().completeMembers(M_STA, chg_);
        StatusMoveData minv_ = moveSta(TargetChoice.TOUS_ADV);
        minv_.getEffects().add(Instances.newEffectGlobal());
        facade_.getData().completeMembers(M_WEA, minv_);
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        facade_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
        facade_.getData().getTranslatedEnvironment().addEntry(EN,new IdMap<EnvironmentType,String>());
        facade_.getData().getTranslatedEnvironment().getVal(EN).addEntry(EnvironmentType.ROAD, ROAD_TR);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void addEffInvoke(FacadeGame _facade, boolean _invokingAllyMove, boolean _invokingMoveButUser, boolean _invokingSufferedMove, boolean _invokingTargetChosenMove, boolean _invokingTargetSuccesfulMove, boolean _invokingUserMoveWhileSleep) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, effInvoke(_invokingAllyMove, _invokingMoveButUser, _invokingSufferedMove, _invokingTargetChosenMove, _invokingTargetSuccesfulMove, _invokingUserMoveWhileSleep));
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectInvoke effInvoke(boolean _invokingAllyMove, boolean _invokingMoveButUser, boolean _invokingSufferedMove, boolean _invokingTargetChosenMove, boolean _invokingTargetSuccesfulMove, boolean _invokingUserMoveWhileSleep) {
        EffectInvoke e_ = Instances.newEffectInvoke();
        e_.setInvokingAllyMove(_invokingAllyMove);
        e_.setInvokingMoveButUser(_invokingMoveButUser);
        e_.setInvokingSufferedMove(_invokingSufferedMove);
        e_.setInvokingTargetChosenMove(_invokingTargetChosenMove);
        e_.setInvokingTargetSuccesfulMove(_invokingTargetSuccesfulMove);
        e_.setInvokingUserMoveWhileSleep(_invokingUserMoveWhileSleep);
        e_.getMoveFctEnv().addEntry(EnvironmentType.ROAD,M_WEA);
        e_.getInvokingMoveByUserTypes().addEntry(NULL_REF,M_WEA);
        e_.getInvokingMoveByUserTypes().addEntry(T_TYPE1,M_STA);
        e_.getMovesNotToBeInvoked().add(M_STA);
        e_.setRateInvokationMove(Rate.one());
        return e_;
    }
}
