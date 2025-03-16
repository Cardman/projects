package aiki.beans.moves.effects;

import aiki.beans.*;
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
import aiki.comparators.*;
import code.maths.*;
import code.util.*;

public abstract class InitDbMoveEffectInvoke extends InitDbMoveEffect{

    public static final String ROAD_TR = "ROAD_TR";

    public static String callEffectInvokeBeanClickGlobalMoveFctEnv(EffectInvokeBean _str, int... _args) {
        return _str.clickGlobalMoveFctEnv(_args[0]);
    }

    public static String callEffectInvokeBeanClickGlobalMoveFctEnvId(EffectInvokeBean _str, int... _args) {
        callEffectInvokeBeanClickGlobalMoveFctEnv(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectInvokeBeanClickMoveFctEnv(EffectInvokeBean _str, int... _args) {
        return _str.clickMoveFctEnv(_args[0]);
    }
    public static String callEffectInvokeBeanClickMoveFctEnvId(EffectInvokeBean _str, int... _args) {
        callEffectInvokeBeanClickMoveFctEnv(_str,_args);
        return getValMoveId(_str);
    }
    public static String callEffectInvokeBeanClickMoveNotInvok(EffectInvokeBean _str, int... _args) {
        return _str.clickMoveNotInvok(_args[0]);
    }

    public static String callEffectInvokeBeanClickMoveNotInvokId(EffectInvokeBean _str, int... _args) {
        callEffectInvokeBeanClickMoveNotInvok(_str,_args);
        return getValMoveId(_str);
    }
    public static String callEffectInvokeBeanClickMoveUserTypes(EffectInvokeBean _str, int... _args) {
        return _str.clickMoveUserTypes(_args[0]);
    }

    public static String callEffectInvokeBeanClickMoveUserTypesId(EffectInvokeBean _str, int... _args) {
        callEffectInvokeBeanClickMoveUserTypes(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectInvokeBeanGetTrEnv(EffectInvokeBean _str, int... _args) {
        return _str.getTrEnv(_args[0]);
    }

    public static String callEffectInvokeBeanGetTrGlobalMoveFctEnv(EffectInvokeBean _str, int... _args) {
        return _str.getTrGlobalMoveFctEnv(_args[0]);
    }

    public static String callEffectInvokeBeanGetTrMoveFctEnv(EffectInvokeBean _str, int... _args) {
        return _str.getTrMoveFctEnv(_args[0]);
    }

    public static String callEffectInvokeBeanGetTrMoveNotInvok(EffectInvokeBean _str, int... _args) {
        return _str.getTrMoveNotInvok(_args[0]);
    }

    public static String callEffectInvokeBeanGetTrMoveUserTypes(EffectInvokeBean _str, int... _args) {
        return _str.getTrMoveUserTypes(_args[0]);
    }

    public static String callEffectInvokeBeanGetTrUserTypes(EffectInvokeBean _str, int... _args) {
        return _str.getTrUserTypes(_args[0]);
    }

    public static CustList<TranslatedKey> callEffectInvokeBeanGlobalMovesGet(EffectInvokeBean _str, int... _args) {
        return _str.getGlobalMoves();
    }

    public static boolean callEffectInvokeBeanInvokingAllyMoveGet(EffectInvokeBean _str, int... _args) {
        return _str.getInvokingAllyMove();
    }

    public static boolean callEffectInvokeBeanInvokingMoveButUserGet(EffectInvokeBean _str, int... _args) {
        return _str.getInvokingMoveButUser();
    }

    public static DictionaryComparator<TranslatedKey,TranslatedKey> callEffectInvokeBeanInvokingMoveByUserTypesGet(EffectInvokeBean _str, int... _args) {
        return _str.getInvokingMoveByUserTypes();
    }

    public static boolean callEffectInvokeBeanInvokingSufferedMoveGet(EffectInvokeBean _str, int... _args) {
        return _str.getInvokingSufferedMove();
    }

    public static boolean callEffectInvokeBeanInvokingTargetChosenMoveGet(EffectInvokeBean _str, int... _args) {
        return _str.getInvokingTargetChosenMove();
    }

    public static boolean callEffectInvokeBeanInvokingTargetSuccesfulMoveGet(EffectInvokeBean _str, int... _args) {
        return _str.getInvokingTargetSuccesfulMove();
    }

    public static boolean callEffectInvokeBeanInvokingUserMoveWhileSleepGet(EffectInvokeBean _str, int... _args) {
        return _str.getInvokingUserMoveWhileSleep();
    }

    public static boolean callEffectInvokeBeanIsType(EffectInvokeBean _str, int... _args) {
        return _str.isType(_args[0]);
    }

    public static DictionaryComparator<TranslatedKey,TranslatedKey> callEffectInvokeBeanMoveFctEnvGet(EffectInvokeBean _str, int... _args) {
        return _str.getMoveFctEnv();
    }

    public static CustList<TranslatedKey> callEffectInvokeBeanMovesNotToBeInvokedGet(EffectInvokeBean _str, int... _args) {
        return _str.getMovesNotToBeInvoked();
    }

    public static Rate callEffectInvokeBeanRateInvokationMoveGet(EffectInvokeBean _str, int... _args) {
        return _str.getRateInvokationMove();
    }
    protected static EffectInvokeBean dispMoveEffInvoke(boolean _invokingAllyMove, boolean _invokingMoveButUser, boolean _invokingSufferedMove, boolean _invokingTargetChosenMove, boolean _invokingTargetSuccesfulMove, boolean _invokingUserMoveWhileSleep) {
        return dispMoveEffInvoke(feedDbMoveEffDataInvoke(_invokingAllyMove, _invokingMoveButUser, _invokingSufferedMove, _invokingTargetChosenMove, _invokingTargetSuccesfulMove, _invokingUserMoveWhileSleep));
    }
    protected static EffectInvokeBean dispMoveEffInvoke(FacadeGame _fac) {
        FacadeGame pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectInvoke();
        return (EffectInvokeBean)transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectInvoke(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_INVOKE,_pk.beanEffectInvokeBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectInvoke() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFINVOKE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_INVOKE);
//        return map_;
//    }
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