package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
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
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.StringMap;

public abstract class InitDbMoveEffectOther extends InitDbMoveEffect {


    public static NaSt callEffectAllyBeanMultAllyDamageGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectAllyBeanMultAllyDamageGet(),_str,_args);
    }

    public static String callEffectBatonPassBeanClickMove(NaSt _str, long... _args) {
        return navigateData(new EffectBatonPassBeanClickMove(),_str,_args);
    }

    public static String callEffectBatonPassBeanClickMoveId(NaSt _str, long... _args) {
        callEffectBatonPassBeanClickMove(_str, _args);
        return getValMoveId(_str);
    }

    public static NaSt callEffectBatonPassBeanGetTrMove(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBatonPassBeanGetTrMove(),_str,_args);
    }

    public static NaSt callEffectBatonPassBeanMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBatonPassBeanMovesGet(),_str,_args);
    }

    public static String callEffectCloneBeanClickMoveBatonPass(NaSt _str, long... _args) {
        return navigateData(new EffectCloneBeanClickMoveBatonPass(),_str,_args);
    }

    public static String callEffectCloneBeanClickMoveBatonPassId(NaSt _str, long... _args) {
        callEffectCloneBeanClickMoveBatonPass(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectCloneBeanClickMoveEndRound(NaSt _str, long... _args) {
        return navigateData(new EffectCloneBeanClickMoveEndRound(),_str,_args);
    }

    public static String callEffectCloneBeanClickMoveEndRoundId(NaSt _str, long... _args) {
        callEffectCloneBeanClickMoveEndRound(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectCloneBeanClickMoveSending(NaSt _str, long... _args) {
        return navigateData(new EffectCloneBeanClickMoveSending(),_str,_args);
    }

    public static String callEffectCloneBeanClickMoveSendingId(NaSt _str, long... _args) {
        callEffectCloneBeanClickMoveSending(_str, _args);
        return getValMoveId(_str);
    }
    public static NaSt callEffectCloneBeanGetTrMovesBatonPass(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCloneBeanGetTrMovesBatonPass(),_str,_args);
    }

    public static NaSt callEffectCloneBeanGetTrMovesEndRound(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCloneBeanGetTrMovesEndRound(),_str,_args);
    }

    public static NaSt callEffectCloneBeanGetTrMovesSending(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCloneBeanGetTrMovesSending(),_str,_args);
    }

    public static NaSt callEffectCloneBeanHpRateCloneGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCloneBeanHpRateCloneGet(),_str,_args);
    }

    public static NaSt callEffectCloneBeanMovesBatonPassGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCloneBeanMovesBatonPassGet(),_str,_args);
    }

    public static NaSt callEffectCloneBeanMovesEndRoundGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCloneBeanMovesEndRoundGet(),_str,_args);
    }

    public static NaSt callEffectCloneBeanMovesSendingGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCloneBeanMovesSendingGet(),_str,_args);
    }

    public static NaSt callEffectCommonStatisticsBeanCommonValueGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCommonStatisticsBeanCommonValueGet(),_str,_args);
    }

    public static NaSt callEffectCommonStatisticsBeanGetTrStatistic(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCommonStatisticsBeanGetTrStatistic(),_str,_args);
    }

    public static NaSt callEffectCommonStatisticsBeanMapVarsCommonStatisticsGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCommonStatisticsBeanMapVarsCommonStatisticsGet(),_str,_args);
    }


    public static NaSt callEffectCounterAttackBeanDroppedStatDirectMoveGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCounterAttackBeanDroppedStatDirectMoveGet(),_str,_args);
    }

    public static NaSt callEffectCounterAttackBeanGetMapVarsFailCounter(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCounterAttackBeanGetMapVarsFailCounter(),_str,_args);
    }

    public static NaSt callEffectCounterAttackBeanGetTrDroppedStatDirectMove(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCounterAttackBeanGetTrDroppedStatDirectMove(),_str,_args);
    }

    public static NaSt callEffectCounterAttackBeanGetTrSufferingDamageTypes(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCounterAttackBeanGetTrSufferingDamageTypes(),_str,_args);
    }

    public static NaSt callEffectCounterAttackBeanReasonsCounterGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCounterAttackBeanReasonsCounterGet(),_str,_args);
    }

    public static NaSt callEffectCounterAttackBeanReasonsProtectGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCounterAttackBeanReasonsProtectGet(),_str,_args);
    }

    public static NaSt callEffectCounterAttackBeanSufferingDamageDirectMoveGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCounterAttackBeanSufferingDamageDirectMoveGet(),_str,_args);
    }

    public static NaSt callEffectCounterAttackBeanSufferingDamageTypesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCounterAttackBeanSufferingDamageTypesGet(),_str,_args);
    }

    public static NaSt callEffectFullHpRateBeanClosestFoeDamageRateHpGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectFullHpRateBeanClosestFoeDamageRateHpGet(),_str,_args);
    }

    public static NaSt callEffectFullHpRateBeanLeftUserHpGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectFullHpRateBeanLeftUserHpGet(),_str,_args);
    }

    public static NaSt callEffectFullHpRateBeanMapVarsRestoredGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectFullHpRateBeanMapVarsRestoredGet(),_str,_args);
    }

    public static NaSt callEffectFullHpRateBeanRestoredHpGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectFullHpRateBeanRestoredHpGet(),_str,_args);
    }



    public static NaSt callEffectMultSufferedMovePowerBeanGetTrType(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectMultSufferedMovePowerBeanGetTrType(),_str,_args);
    }

    public static NaSt callEffectMultSufferedMovePowerBeanMultMovePowerFctTypeGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectMultSufferedMovePowerBeanMultMovePowerFctTypeGet(),_str,_args);
    }

    public static NaSt callEffectMultUsedMovePowerBeanGetTrType(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectMultUsedMovePowerBeanGetTrType(),_str,_args);
    }

    public static NaSt callEffectMultUsedMovePowerBeanMultMovePowerFctTypeGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectMultUsedMovePowerBeanMultMovePowerFctTypeGet(),_str,_args);
    }

    public static NaSt callEffectOrderBeanTargetAttacksLastGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectOrderBeanTargetAttacksLastGet(),_str,_args);
    }


    public static NaSt callEffectRemainedHpRateBeanRateHpGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRemainedHpRateBeanRateHpGet(),_str,_args);
    }

    public static NaSt callEffectRemainedHpRateBeanWinHpGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRemainedHpRateBeanWinHpGet(),_str,_args);
    }



    public static NaSt callEffectVarPPBeanDeletePpGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectVarPPBeanDeletePpGet(),_str,_args);
    }

    public static NaSt callEffectWinMoneyBeanWinningRateBySumTargetUserGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectWinMoneyBeanWinningRateBySumTargetUserGet(),_str,_args);
    }
    protected static NaSt dispMoveEffFullHpRate() {
        return dispMoveEffFullHpRate(feedDbMoveEffDataFullHpRate());
    }
    protected static NaSt dispMoveEffFullHpRate(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectFullHpRate(pk_);
        StringMap<String> mapping_ = mappingToEffectFullHpRate();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectFullHpRate(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_FULLHPRATE,_pk.beanEffectFullHpRateBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectFullHpRate() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFFULLHPRATE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_FULLHPRATE);
        return map_;
    }
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
        e_.setClosestFoeDamageRateHp(Rate.one());
        e_.setLeftUserHp(Rate.one());
        return e_;
    }
    protected static NaSt dispMoveEffCounterAttack() {
        return dispMoveEffCounterAttack(feedDbMoveEffDataCounterAttack());
    }
    protected static NaSt dispMoveEffCounterAttack(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectCounterAttack(pk_);
        StringMap<String> mapping_ = mappingToEffectCounterAttack();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectCounterAttack(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_COUNTER_ATTACK,_pk.beanEffectCounterAttackBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectCounterAttack() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOUNTERATTACK_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_COUNTER_ATTACK);
        return map_;
    }
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
    protected static NaSt dispMoveEffAlly() {
        return dispMoveEffAlly(feedDbMoveEffDataAlly());
    }
    protected static NaSt dispMoveEffAlly(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectAlly(pk_);
        StringMap<String> mapping_ = mappingToEffectAlly();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectAlly(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_ALLY,_pk.beanEffectAllyBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectAlly() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFALLY_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_ALLY);
        return map_;
    }
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
    protected static NaSt dispMoveEffBatonPass() {
        return dispMoveEffBatonPass(feedDbMoveEffDataBatonPass());
    }
    protected static NaSt dispMoveEffBatonPass(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectBatonPass(pk_);
        StringMap<String> mapping_ = mappingToEffectBatonPass();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectBatonPass(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_BATONPASS,_pk.beanEffectBatonPassBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectBatonPass() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFBATONPASS_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_BATONPASS);
        return map_;
    }
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
    protected static NaSt dispMoveEffClone() {
        return dispMoveEffClone(feedDbMoveEffDataClone());
    }
    protected static NaSt dispMoveEffClone(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectClone(pk_);
        StringMap<String> mapping_ = mappingToEffectClone();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectClone(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_CLONE,_pk.beanEffectCloneBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectClone() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCLONE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_CLONE);
        return map_;
    }
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
    protected static NaSt dispMoveEffCommonStatistics() {
        return dispMoveEffCommonStatistics(feedDbMoveEffDataCommonStatistics());
    }
    protected static NaSt dispMoveEffCommonStatistics(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectCommonStatistics(pk_);
        StringMap<String> mapping_ = mappingToEffectCommonStatistics();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectCommonStatistics(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_COMMONSTATISTICS,_pk.beanEffectCommonStatisticsBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectCommonStatistics() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOMMONSTATISTICS_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_COMMONSTATISTICS);
        return map_;
    }
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
    protected static NaSt dispMoveEffOrder(boolean _targetAttacksLast) {
        return dispMoveEffOrder(feedDbMoveEffDataOrder(_targetAttacksLast));
    }
    protected static NaSt dispMoveEffOrder(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectOrder(pk_);
        StringMap<String> mapping_ = mappingToEffectOrder();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectOrder(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_ORDER,_pk.beanEffectOrderBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectOrder() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFORDER_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_ORDER);
        return map_;
    }
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
    protected static NaSt dispMoveEffRemainedHpRate(Rate _targetAttacksLast) {
        return dispMoveEffRemainedHpRate(feedDbMoveEffDataRemainedHpRate(_targetAttacksLast));
    }
    protected static NaSt dispMoveEffRemainedHpRate(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectRemainedHpRate(pk_);
        StringMap<String> mapping_ = mappingToEffectRemainedHpRate();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectRemainedHpRate(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_REMAINEDHPRATE,_pk.beanEffectRemainedHpRateBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectRemainedHpRate() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFREMAINEDHPRATE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_REMAINEDHPRATE);
        return map_;
    }
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

    protected static NaSt dispMoveEffMultMovePower(int _indexEff) {
        return dispMoveEffMultMovePower(feedDbMoveEffDataMultMovePower(), _indexEff);
    }
    protected static NaSt dispMoveEffMultMovePower(FacadeGame _fac, int _indexEff) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectMultMovePower(pk_);
        StringMap<String> mapping_ = mappingToEffectMultMovePower();
        return transitEffect(0, _indexEff,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectMultMovePower(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_MULTSUFFEREDMOVEPOWER,_pk.beanEffectMultMovePowerBean(EN));
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_MULTUSEDMOVEPOWER,_pk.beanEffectMultMovePowerBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectMultMovePower() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFMULTSUFFEREDMOVEPOWER_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_MULTSUFFEREDMOVEPOWER);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFMULTUSEDMOVEPOWER_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_MULTUSEDMOVEPOWER);
        return map_;
    }
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
    protected static NaSt dispMoveEffVarPp() {
        EffectVarPP e_ = Instances.newEffectVarPP();
        e_.setDeletePp( 1);
        return dispMoveEffOther(feedDbMoveEffDataOther(e_));
    }
    protected static NaSt dispMoveEffWinMoney() {
        EffectWinMoney e_ = Instances.newEffectWinMoney();
        e_.setWinningRateBySumTargetUser(Rate.one());
        return dispMoveEffOther(feedDbMoveEffDataOther(e_));
    }

    protected static NaSt dispMoveEffOther(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectOther(pk_);
        StringMap<String> mapping_ = mappingToEffectOther();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectOther(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_VARPP,_pk.beanEffectVarPPBean(EN));
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_WINMONEY,_pk.beanEffectWinMoneyBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectOther() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFVARPP_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_VARPP);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFWINMONEY_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_WINMONEY);
        return map_;
    }
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
