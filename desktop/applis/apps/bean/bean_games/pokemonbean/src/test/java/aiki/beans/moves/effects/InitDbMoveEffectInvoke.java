package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.instances.Instances;
import aiki.map.levels.enums.EnvironmentType;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.IdMap;
import code.util.StringMap;

public abstract class InitDbMoveEffectInvoke extends InitDbMoveEffect{

    public static final String ROAD_TR = "ROAD_TR";

    public static String callEffectInvokeBeanClickGlobalMoveFctEnv(NaSt _str, long... _args) {
        return navigateData(new EffectInvokeBeanClickGlobalMoveFctEnv(),_str,_args);
    }

    public static String callEffectInvokeBeanClickGlobalMoveFctEnvId(NaSt _str, long... _args) {
        callEffectInvokeBeanClickGlobalMoveFctEnv(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectInvokeBeanClickMoveFctEnv(NaSt _str, long... _args) {
        return navigateData(new EffectInvokeBeanClickMoveFctEnv(),_str,_args);
    }
    public static String callEffectInvokeBeanClickMoveFctEnvId(NaSt _str, long... _args) {
        callEffectInvokeBeanClickMoveFctEnv(_str,_args);
        return getValMoveId(_str);
    }
    public static String callEffectInvokeBeanClickMoveNotInvok(NaSt _str, long... _args) {
        return navigateData(new EffectInvokeBeanClickMoveNotInvok(),_str,_args);
    }

    public static String callEffectInvokeBeanClickMoveNotInvokId(NaSt _str, long... _args) {
        callEffectInvokeBeanClickMoveNotInvok(_str,_args);
        return getValMoveId(_str);
    }
    public static String callEffectInvokeBeanClickMoveUserTypes(NaSt _str, long... _args) {
        return navigateData(new EffectInvokeBeanClickMoveUserTypes(),_str,_args);
    }

    public static String callEffectInvokeBeanClickMoveUserTypesId(NaSt _str, long... _args) {
        callEffectInvokeBeanClickMoveUserTypes(_str, _args);
        return getValMoveId(_str);
    }
    public static NaSt callEffectInvokeBeanGetTrEnv(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanGetTrEnv(),_str,_args);
    }

    public static NaSt callEffectInvokeBeanGetTrGlobalMoveFctEnv(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanGetTrGlobalMoveFctEnv(),_str,_args);
    }

    public static NaSt callEffectInvokeBeanGetTrMoveFctEnv(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanGetTrMoveFctEnv(),_str,_args);
    }

    public static NaSt callEffectInvokeBeanGetTrMoveNotInvok(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanGetTrMoveNotInvok(),_str,_args);
    }

    public static NaSt callEffectInvokeBeanGetTrMoveUserTypes(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanGetTrMoveUserTypes(),_str,_args);
    }

    public static NaSt callEffectInvokeBeanGetTrUserTypes(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanGetTrUserTypes(),_str,_args);
    }

    public static NaSt callEffectInvokeBeanGlobalMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanGlobalMovesGet(),_str,_args);
    }

    public static NaSt callEffectInvokeBeanInvokingAllyMoveGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanInvokingAllyMoveGet(),_str,_args);
    }

    public static NaSt callEffectInvokeBeanInvokingMoveButUserGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanInvokingMoveButUserGet(),_str,_args);
    }

    public static NaSt callEffectInvokeBeanInvokingMoveByUserTypesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanInvokingMoveByUserTypesGet(),_str,_args);
    }

    public static NaSt callEffectInvokeBeanInvokingSufferedMoveGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanInvokingSufferedMoveGet(),_str,_args);
    }

    public static NaSt callEffectInvokeBeanInvokingTargetChosenMoveGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanInvokingTargetChosenMoveGet(),_str,_args);
    }

    public static NaSt callEffectInvokeBeanInvokingTargetSuccesfulMoveGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanInvokingTargetSuccesfulMoveGet(),_str,_args);
    }

    public static NaSt callEffectInvokeBeanInvokingUserMoveWhileSleepGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanInvokingUserMoveWhileSleepGet(),_str,_args);
    }

    public static NaSt callEffectInvokeBeanIsType(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanIsType(),_str,_args);
    }

    public static NaSt callEffectInvokeBeanMoveFctEnvGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanMoveFctEnvGet(),_str,_args);
    }

    public static NaSt callEffectInvokeBeanMovesNotToBeInvokedGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanMovesNotToBeInvokedGet(),_str,_args);
    }

    public static NaSt callEffectInvokeBeanRateInvokationMoveGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectInvokeBeanRateInvokationMoveGet(),_str,_args);
    }
    protected static NaSt dispMoveEffInvoke(boolean _invokingAllyMove, boolean _invokingMoveButUser, boolean _invokingSufferedMove, boolean _invokingTargetChosenMove, boolean _invokingTargetSuccesfulMove, boolean _invokingUserMoveWhileSleep) {
        return dispMoveEffInvoke(feedDbMoveEffDataInvoke(_invokingAllyMove, _invokingMoveButUser, _invokingSufferedMove, _invokingTargetChosenMove, _invokingTargetSuccesfulMove, _invokingUserMoveWhileSleep));
    }
    protected static NaSt dispMoveEffInvoke(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectInvoke(pk_);
        StringMap<String> mapping_ = mappingToEffectInvoke();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectInvoke(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_INVOKE,_pk.beanEffectInvokeBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectInvoke() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFINVOKE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_INVOKE);
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
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
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
