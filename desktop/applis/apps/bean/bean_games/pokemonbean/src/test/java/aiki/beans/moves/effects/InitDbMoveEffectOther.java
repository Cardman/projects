package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.instances.Instances;
import aiki.map.levels.enums.EnvironmentType;
import code.maths.Rate;
import aiki.comparators.*;
import code.util.*;

public abstract class InitDbMoveEffectOther extends InitDbMoveEffect {


    public static Rate callEffectAllyBeanMultAllyDamageGet(EffectBean _str, int... _args) {
        return ((EffectAllyBean) _str).getMultAllyDamage();
    }

    public static String callEffectBatonPassBeanClickMove(EffectBean _str, int... _args) {
        return ((EffectBatonPassBean) _str).clickMove(_args[0]);
    }

    public static String callEffectBatonPassBeanClickMoveId(EffectBean _str, int... _args) {
        callEffectBatonPassBeanClickMove(_str, _args);
        return getValMoveId(_str);
    }

    public static String callEffectBatonPassBeanGetTrMove(EffectBean _str, int... _args) {
        return ((EffectBatonPassBean) _str).getTrMove(_args[0]);
    }

    public static CustList<TranslatedKey> callEffectBatonPassBeanMovesGet(EffectBean _str, int... _args) {
        return ((EffectBatonPassBean) _str).getMoves();
    }

    public static String callEffectCloneBeanClickMoveBatonPass(EffectBean _str, int... _args) {
        return ((EffectCloneBean) _str).clickMoveBatonPass(_args[0]);
    }

    public static String callEffectCloneBeanClickMoveBatonPassId(EffectBean _str, int... _args) {
        callEffectCloneBeanClickMoveBatonPass(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectCloneBeanClickMoveEndRound(EffectBean _str, int... _args) {
        return ((EffectCloneBean) _str).clickMoveEndRound(_args[0]);
    }

    public static String callEffectCloneBeanClickMoveEndRoundId(EffectBean _str, int... _args) {
        callEffectCloneBeanClickMoveEndRound(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectCloneBeanClickMoveSending(EffectBean _str, int... _args) {
        return ((EffectCloneBean) _str).clickMoveSending(_args[0]);
    }

    public static String callEffectCloneBeanClickMoveSendingId(EffectBean _str, int... _args) {
        callEffectCloneBeanClickMoveSending(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectCloneBeanGetTrMovesBatonPass(EffectBean _str, int... _args) {
        return ((EffectCloneBean) _str).getTrMovesBatonPass(_args[0]);
    }

    public static String callEffectCloneBeanGetTrMovesEndRound(EffectBean _str, int... _args) {
        return ((EffectCloneBean) _str).getTrMovesEndRound(_args[0]);
    }

    public static String callEffectCloneBeanGetTrMovesSending(EffectBean _str, int... _args) {
        return ((EffectCloneBean) _str).getTrMovesSending(_args[0]);
    }

    public static Rate callEffectCloneBeanHpRateCloneGet(EffectBean _str, int... _args) {
        return ((EffectCloneBean) _str).getHpRateClone();
    }

    public static CustList<TranslatedKey> callEffectCloneBeanMovesBatonPassGet(EffectBean _str, int... _args) {
        return ((EffectCloneBean) _str).getMovesBatonPass();
    }

    public static CustList<TranslatedKey> callEffectCloneBeanMovesEndRoundGet(EffectBean _str, int... _args) {
        return ((EffectCloneBean) _str).getMovesEndRound();
    }

    public static CustList<TranslatedKey> callEffectCloneBeanMovesSendingGet(EffectBean _str, int... _args) {
        return ((EffectCloneBean) _str).getMovesSending();
    }

    public static DictionaryComparator<TranslatedKey,String> callEffectCommonStatisticsBeanCommonValueGet(EffectBean _str, int... _args) {
        return ((EffectCommonStatisticsBean) _str).getCommonValue();
    }

    public static String callEffectCommonStatisticsBeanGetTrStatistic(EffectBean _str, int... _args) {
        return ((EffectCommonStatisticsBean) _str).getTrStatistic(_args[0]);
    }

    public static AbsMap<String,String> callEffectCommonStatisticsBeanMapVarsCommonStatisticsGet(EffectBean _str, int... _args) {
        return ((EffectCommonStatisticsBean) _str).getMapVarsCommonStatistics();
    }


    public static DictionaryComparator<TranslatedKey,Long> callEffectCounterAttackBeanDroppedStatDirectMoveGet(EffectBean _str, int... _args) {
        return ((EffectCounterAttackBean) _str).getDroppedStatDirectMove();
    }

    public static AbsMap<String,String> callEffectCounterAttackBeanGetMapVarsFailCounter(EffectBean _str, int... _args) {
        return ((EffectCounterAttackBean) _str).getMapVarsFailCounter();
    }

    public static String callEffectCounterAttackBeanGetTrDroppedStatDirectMove(EffectBean _str, int... _args) {
        return ((EffectCounterAttackBean) _str).getTrDroppedStatDirectMove(_args[0]);
    }

    public static String callEffectCounterAttackBeanGetTrSufferingDamageTypes(EffectBean _str, int... _args) {
        return ((EffectCounterAttackBean) _str).getTrSufferingDamageTypes(_args[0]);
    }

    public static CustList<String> callEffectCounterAttackBeanReasonsCounterGet(EffectBean _str, int... _args) {
        return ((EffectCounterAttackBean) _str).getReasonsCounter();
    }

    public static CustList<String> callEffectCounterAttackBeanReasonsProtectGet(EffectBean _str, int... _args) {
        return ((EffectCounterAttackBean) _str).getReasonsProtect();
    }

    public static Rate callEffectCounterAttackBeanSufferingDamageDirectMoveGet(EffectBean _str, int... _args) {
        return ((EffectCounterAttackBean) _str).getSufferingDamageDirectMove();
    }

    public static DictionaryComparator<TranslatedKey,Rate> callEffectCounterAttackBeanSufferingDamageTypesGet(EffectBean _str, int... _args) {
        return ((EffectCounterAttackBean) _str).getSufferingDamageTypes();
    }

    public static Rate callEffectFullHpRateBeanLeftUserHpGet(EffectBean _str, int... _args) {
        return ((EffectFullHpRateBean) _str).getLeftUserHp();
    }

    public static AbsMap<String,String> callEffectFullHpRateBeanMapVarsRestoredGet(EffectBean _str, int... _args) {
        return ((EffectFullHpRateBean) _str).getMapVarsRestored();
    }

    public static String callEffectFullHpRateBeanRestoredHpGet(EffectBean _str, int... _args) {
        return ((EffectFullHpRateBean) _str).getRestoredHp();
    }



    public static String callEffectMultSufferedMovePowerBeanGetTrType(EffectBean _str, int... _args) {
        return ((EffectMultMovePowerBean) _str).getTrType(_args[0]);
    }

    public static DictionaryComparator<TranslatedKey,Rate> callEffectMultSufferedMovePowerBeanMultMovePowerFctTypeGet(EffectBean _str, int... _args) {
        return ((EffectMultMovePowerBean) _str).getMultMovePowerFctType();
    }

    public static String callEffectMultUsedMovePowerBeanGetTrType(EffectBean _str, int... _args) {
        return ((EffectMultMovePowerBean) _str).getTrType(_args[0]);
    }

    public static DictionaryComparator<TranslatedKey,Rate> callEffectMultUsedMovePowerBeanMultMovePowerFctTypeGet(EffectBean _str, int... _args) {
        return ((EffectMultMovePowerBean) _str).getMultMovePowerFctType();
    }

    public static boolean callEffectOrderBeanTargetAttacksLastGet(EffectBean _str, int... _args) {
        return ((EffectOrderBean) _str).getTargetAttacksLast();
    }


    public static Rate callEffectRemainedHpRateBeanRateHpGet(EffectBean _str, int... _args) {
        return ((EffectRemainedHpRateBean) _str).getRateHp();
    }

    public static boolean callEffectRemainedHpRateBeanWinHpGet(EffectBean _str, int... _args) {
        return ((EffectRemainedHpRateBean) _str).getWinHp();
    }



    public static long callEffectVarPPBeanDeletePpGet(EffectBean _str, int... _args) {
        return ((EffectVarPPBean) _str).getDeletePp();
    }

    public static Rate callEffectWinMoneyBeanWinningRateBySumTargetUserGet(EffectBean _str, int... _args) {
        return ((EffectWinMoneyBean) _str).getWinningRateBySumTargetUser();
    }
    protected static EffectBean dispMoveEffFullHpRate() {
        return dispMoveEffFullHpRate(feedDbMoveEffDataFullHpRate());
    }
    protected static EffectBean dispMoveEffFullHpRate(FacadeGame _fac) {
        FacadeGame pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectFullHpRate();
        return transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectFullHpRate(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_FULLHPRATE,_pk.beanEffectFullHpRateBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectFullHpRate() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFFULLHPRATE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_FULLHPRATE);
//        return map_;
//    }
    protected static FacadeGame feedDbMoveEffDataFullHpRate() {
        FacadeGame facade_ = facade();
        addEffFullHpRate(facade_);
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
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void addEffFullHpRate(FacadeGame _facade) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, effFullHpRate());
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectFullHpRate effFullHpRate() {
        EffectFullHpRate e_ = Instances.newEffectFullHpRate();
        e_.setRestoredHp(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        e_.setLeftUserHp(Rate.one());
        return e_;
    }
    protected static EffectBean dispMoveEffCounterAttack() {
        return dispMoveEffCounterAttack(feedDbMoveEffDataCounterAttack());
    }
    protected static EffectBean dispMoveEffCounterAttack(FacadeGame _fac) {
        FacadeGame pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectCounterAttack();
        return transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectCounterAttack(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_COUNTER_ATTACK,_pk.beanEffectCounterAttackBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectCounterAttack() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOUNTERATTACK_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_COUNTER_ATTACK);
//        return map_;
//    }
    protected static FacadeGame feedDbMoveEffDataCounterAttack() {
        FacadeGame facade_ = facade();
        addEffCounterAttack(facade_);
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
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void addEffCounterAttack(FacadeGame _facade) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, effCounterAttack());
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectCounterAttack effCounterAttack() {
        EffectCounterAttack e_ = Instances.newEffectCounterAttack();
        e_.setCounterFail(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        e_.setProtectFail(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        e_.setSufferingDamageDirectMove(Rate.one());
        e_.getSufferingDamageTypes().addEntry(T_TYPE1,Rate.one());
        e_.getDroppedStatDirectMove().addEntry(Statistic.SPEED,1L);
        return e_;
    }
    protected static EffectBean dispMoveEffAlly() {
        return dispMoveEffAlly(feedDbMoveEffDataAlly());
    }
    protected static EffectBean dispMoveEffAlly(FacadeGame _fac) {
        FacadeGame pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectAlly();
        return transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectAlly(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_ALLY,_pk.beanEffectAllyBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectAlly() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFALLY_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_ALLY);
//        return map_;
//    }
    protected static FacadeGame feedDbMoveEffDataAlly() {
        FacadeGame facade_ = facade();
        addEffAlly(facade_);
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
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void addEffAlly(FacadeGame _facade) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, effAlly());
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectAlly effAlly() {
        EffectAlly e_ = Instances.newEffectAlly();
        e_.setMultAllyDamage(Rate.one());
        return e_;
    }
    protected static EffectBean dispMoveEffBatonPass() {
        return dispMoveEffBatonPass(feedDbMoveEffDataBatonPass());
    }
    protected static EffectBean dispMoveEffBatonPass(FacadeGame _fac) {
        FacadeGame pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectBatonPass();
        return transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectBatonPass(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_BATONPASS,_pk.beanEffectBatonPassBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectBatonPass() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFBATONPASS_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_BATONPASS);
//        return map_;
//    }
    protected static FacadeGame feedDbMoveEffDataBatonPass() {
        FacadeGame facade_ = facade();
        addEffBatonPass(facade_);
        facade_.getData().completeMembers(M_STA, moveSta(TargetChoice.TOUS_ADV));
        StatusMoveData minv_ = moveSta(TargetChoice.TOUS_ADV);
        minv_.getEffects().add(Instances.newEffectProtectFromTypes());
        facade_.getData().completeMembers(M_WEA, minv_);
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void addEffBatonPass(FacadeGame _facade) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, effBatonPass());
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectBatonPass effBatonPass() {
        return Instances.newEffectBatonPass();
    }
    protected static EffectBean dispMoveEffClone() {
        return dispMoveEffClone(feedDbMoveEffDataClone());
    }
    protected static EffectBean dispMoveEffClone(FacadeGame _fac) {
        FacadeGame pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectClone();
        return transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectClone(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_CLONE,_pk.beanEffectCloneBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectClone() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCLONE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_CLONE);
//        return map_;
//    }
    protected static FacadeGame feedDbMoveEffDataClone() {
        FacadeGame facade_ = facade();
        addEffClone(facade_);
        StatusMoveData ba_ = moveSta(TargetChoice.TOUS_ADV);
        ba_.getEffects().add(Instances.newEffectBatonPass());
        facade_.getData().completeMembers(M_STA, ba_);
        StatusMoveData minv_ = moveSta(TargetChoice.TOUS_ADV);
        EffectTeamWhileSendFoe mu_ = Instances.newEffectTeamWhileSendFoe();
        mu_.setDamageRateAgainstFoe(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        minv_.getEffects().add(mu_);
        facade_.getData().completeMembers(M_WEA, minv_);
        StatusMoveData ant_ = moveSta(TargetChoice.TOUS_ADV);
        ant_.getEffects().add(Instances.newEffectEndRoundPositionTargetRelation());
        facade_.getData().completeMembers(M_DAM_VERY_BAD, ant_);
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveEffDataCloneBatonPass() {
        FacadeGame facade_ = facade();
        addEffClone(facade_);
        facade_.getData().completeMembers(M_STA, moveSta(TargetChoice.TOUS_ADV));
        StatusMoveData minv_ = moveSta(TargetChoice.TOUS_ADV);
        minv_.getEffects().add(Instances.newEffectBatonPass());
        facade_.getData().completeMembers(M_WEA, minv_);
        trs(facade_);
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static FacadeGame feedDbMoveEffDataCloneSending() {
        FacadeGame facade_ = facade();
        addEffClone(facade_);
        StatusMoveData si_ = moveSta(TargetChoice.TOUS_ADV);
        si_.getEffects().add(Instances.newEffectTeamWhileSendFoe());
        facade_.getData().completeMembers(M_STA, si_);
        StatusMoveData minv_ = moveSta(TargetChoice.TOUS_ADV);
        EffectTeamWhileSendFoe mu_ = Instances.newEffectTeamWhileSendFoe();
        mu_.setDamageRateAgainstFoe(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        minv_.getEffects().add(mu_);
        facade_.getData().completeMembers(M_WEA, minv_);
        trs(facade_);
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void addEffClone(FacadeGame _facade) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, effClone());
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectClone effClone() {
        EffectClone cl_ = Instances.newEffectClone();
        cl_.setHpRateClone(Rate.one());
        return cl_;
    }
    protected static EffectBean dispMoveEffCommonStatistics() {
        return dispMoveEffCommonStatistics(feedDbMoveEffDataCommonStatistics());
    }
    protected static EffectBean dispMoveEffCommonStatistics(FacadeGame _fac) {
        FacadeGame pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectCommonStatistics();
        return transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectCommonStatistics(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_COMMONSTATISTICS,_pk.beanEffectCommonStatisticsBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectCommonStatistics() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOMMONSTATISTICS_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_COMMONSTATISTICS);
//        return map_;
//    }
    protected static FacadeGame feedDbMoveEffDataCommonStatistics() {
        FacadeGame facade_ = facade();
        addEffCommonStatistics(facade_);
        facade_.getData().completeMembers(M_STA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_DAM_VERY_BAD, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void addEffCommonStatistics(FacadeGame _facade) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, effCommonStatistics());
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectCommonStatistics effCommonStatistics() {
        EffectCommonStatistics cl_ = Instances.newEffectCommonStatistics();
        cl_.getCommonValue().addEntry(Statistic.SPEED,VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        return cl_;
    }
    protected static EffectBean dispMoveEffOrder(boolean _targetAttacksLast) {
        return dispMoveEffOrder(feedDbMoveEffDataOrder(_targetAttacksLast));
    }
    protected static EffectBean dispMoveEffOrder(FacadeGame _fac) {
        FacadeGame pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectOrder();
        return transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectOrder(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_ORDER,_pk.beanEffectOrderBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectOrder() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFORDER_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_ORDER);
//        return map_;
//    }
    protected static FacadeGame feedDbMoveEffDataOrder(boolean _targetAttacksLast) {
        FacadeGame facade_ = facade();
        addEffOrder(facade_, _targetAttacksLast);
        facade_.getData().completeMembers(M_STA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_DAM_VERY_BAD, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void addEffOrder(FacadeGame _facade, boolean _targetAttacksLast) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, effOrder(_targetAttacksLast));
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectOrder effOrder(boolean _targetAttacksLast) {
        EffectOrder cl_ = Instances.newEffectOrder();
        cl_.setTargetAttacksLast(_targetAttacksLast);
        return cl_;
    }
    protected static EffectBean dispMoveEffRemainedHpRate(Rate _targetAttacksLast) {
        return dispMoveEffRemainedHpRate(feedDbMoveEffDataRemainedHpRate(_targetAttacksLast));
    }
    protected static EffectBean dispMoveEffRemainedHpRate(FacadeGame _fac) {
        FacadeGame pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectRemainedHpRate();
        return transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectRemainedHpRate(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_REMAINEDHPRATE,_pk.beanEffectRemainedHpRateBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectRemainedHpRate() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFREMAINEDHPRATE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_REMAINEDHPRATE);
//        return map_;
//    }
    protected static FacadeGame feedDbMoveEffDataRemainedHpRate(Rate _targetAttacksLast) {
        FacadeGame facade_ = facade();
        addEffRemainedHpRate(facade_, _targetAttacksLast);
        facade_.getData().completeMembers(M_STA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_DAM_VERY_BAD, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void addEffRemainedHpRate(FacadeGame _facade, Rate _targetAttacksLast) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, effRemainedHpRate(_targetAttacksLast));
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectRemainedHpRate effRemainedHpRate(Rate _targetAttacksLast) {
        EffectRemainedHpRate cl_ = Instances.newEffectRemainedHpRate();
        cl_.setRateHp(_targetAttacksLast);
        return cl_;
    }

    protected static EffectBean dispMoveEffMultMovePower(int _indexEff) {
        return dispMoveEffMultMovePower(feedDbMoveEffDataMultMovePower(), _indexEff);
    }
    protected static EffectBean dispMoveEffMultMovePower(FacadeGame _fac, int _indexEff) {
        FacadeGame pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectMultMovePower();
        return transitEffect(0, _indexEff,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectMultMovePower(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_MULTSUFFEREDMOVEPOWER,_pk.beanEffectMultMovePowerBean(EN));
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_MULTUSEDMOVEPOWER,_pk.beanEffectMultMovePowerBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectMultMovePower() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFMULTSUFFEREDMOVEPOWER_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_MULTSUFFEREDMOVEPOWER);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFMULTUSEDMOVEPOWER_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_MULTUSEDMOVEPOWER);
//        return map_;
//    }
    protected static FacadeGame feedDbMoveEffDataMultMovePower() {
        FacadeGame facade_ = facade();
        addEffMultMovePower(facade_);
        facade_.getData().completeMembers(M_STA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_DAM_VERY_BAD, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void addEffMultMovePower(FacadeGame _facade) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, effMultMovePowerInf());
        target(dam_, effMultMovePowerSub());
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectMultMovePower effMultMovePowerInf() {
        EffectMultMovePower cl_ = Instances.newEffectMultUsedMovePower();
        cl_.getMultMovePowerFctType().addEntry(T_TYPE1,Rate.one());
        return cl_;
    }
    protected static EffectMultMovePower effMultMovePowerSub() {
        EffectMultMovePower cl_ = Instances.newEffectMultSufferedMovePower();
        cl_.getMultMovePowerFctType().addEntry(T_TYPE2,Rate.newRate("2"));
        return cl_;
    }
    protected static EffectBean dispMoveEffVarPp() {
        EffectVarPP e_ = Instances.newEffectVarPP();
        e_.setDeletePp( 1);
        return dispMoveEffOther(feedDbMoveEffDataOther(e_));
    }
    protected static EffectBean dispMoveEffWinMoney() {
        EffectWinMoney e_ = Instances.newEffectWinMoney();
        e_.setWinningRateBySumTargetUser(Rate.one());
        return dispMoveEffOther(feedDbMoveEffDataOther(e_));
    }

    protected static EffectBean dispMoveEffOther(FacadeGame _fac) {
        FacadeGame pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectOther();
        return transitEffectQuick(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectOther(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_VARPP,_pk.beanEffectVarPPBean(EN));
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_WINMONEY,_pk.beanEffectWinMoneyBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectOther() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFVARPP_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_VARPP);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFWINMONEY_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_WINMONEY);
//        return map_;
//    }
    protected static FacadeGame feedDbMoveEffDataOther(Effect _eff) {
        FacadeGame facade_ = facade();
        addEffOther(facade_,_eff);
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
    private static void addEffOther(FacadeGame _facade, Effect _eff) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, _eff);
        _facade.getData().completeMembers(M_DAM, dam_);
    }
}