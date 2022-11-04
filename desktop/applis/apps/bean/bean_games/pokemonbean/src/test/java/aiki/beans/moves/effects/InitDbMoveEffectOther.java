package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.fight.Fight;
import aiki.instances.Instances;
import aiki.map.levels.enums.EnvironmentType;
import code.expressionlanguage.structs.Struct;
import code.maths.Rate;
import code.util.StringMap;

public abstract class InitDbMoveEffectOther extends InitDbMoveEffect {


    public static Struct callEffectAllyBeanMultAllyDamageGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectAllyBeanMultAllyDamageGet(),_str,_args);
    }

    public static String callEffectBatonPassBeanClickMove(Struct _str, long... _args) {
        return navigateData(new EffectBatonPassBeanClickMove(),_str,_args);
    }

    public static String callEffectBatonPassBeanClickMoveId(Struct _str, long... _args) {
        callEffectBatonPassBeanClickMove(_str, _args);
        return getValMoveId(_str);
    }

    public static Struct callEffectBatonPassBeanGetTrMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBatonPassBeanGetTrMove(),_str,_args);
    }

    public static Struct callEffectBatonPassBeanMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBatonPassBeanMovesGet(),_str,_args);
    }

    public static String callEffectCloneBeanClickMoveBatonPass(Struct _str, long... _args) {
        return navigateData(new EffectCloneBeanClickMoveBatonPass(),_str,_args);
    }

    public static String callEffectCloneBeanClickMoveBatonPassId(Struct _str, long... _args) {
        callEffectCloneBeanClickMoveBatonPass(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectCloneBeanClickMoveEndRound(Struct _str, long... _args) {
        return navigateData(new EffectCloneBeanClickMoveEndRound(),_str,_args);
    }

    public static String callEffectCloneBeanClickMoveEndRoundId(Struct _str, long... _args) {
        callEffectCloneBeanClickMoveEndRound(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectCloneBeanClickMoveSending(Struct _str, long... _args) {
        return navigateData(new EffectCloneBeanClickMoveSending(),_str,_args);
    }

    public static String callEffectCloneBeanClickMoveSendingId(Struct _str, long... _args) {
        callEffectCloneBeanClickMoveSending(_str, _args);
        return getValMoveId(_str);
    }
    public static Struct callEffectCloneBeanGetTrMovesBatonPass(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCloneBeanGetTrMovesBatonPass(),_str,_args);
    }

    public static Struct callEffectCloneBeanGetTrMovesEndRound(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCloneBeanGetTrMovesEndRound(),_str,_args);
    }

    public static Struct callEffectCloneBeanGetTrMovesSending(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCloneBeanGetTrMovesSending(),_str,_args);
    }

    public static Struct callEffectCloneBeanHpRateCloneGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCloneBeanHpRateCloneGet(),_str,_args);
    }

    public static Struct callEffectCloneBeanMovesBatonPassGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCloneBeanMovesBatonPassGet(),_str,_args);
    }

    public static Struct callEffectCloneBeanMovesEndRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCloneBeanMovesEndRoundGet(),_str,_args);
    }

    public static Struct callEffectCloneBeanMovesSendingGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCloneBeanMovesSendingGet(),_str,_args);
    }

    public static Struct callEffectCommonStatisticsBeanCommonValueGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCommonStatisticsBeanCommonValueGet(),_str,_args);
    }

    public static Struct callEffectCommonStatisticsBeanGetTrStatistic(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCommonStatisticsBeanGetTrStatistic(),_str,_args);
    }

    public static Struct callEffectCommonStatisticsBeanMapVarsCommonStatisticsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCommonStatisticsBeanMapVarsCommonStatisticsGet(),_str,_args);
    }


    public static Struct callEffectCounterAttackBeanDroppedStatDirectMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCounterAttackBeanDroppedStatDirectMoveGet(),_str,_args);
    }

    public static Struct callEffectCounterAttackBeanGetMapVarsFailCounter(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCounterAttackBeanGetMapVarsFailCounter(),_str,_args);
    }

    public static Struct callEffectCounterAttackBeanGetTrDroppedStatDirectMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCounterAttackBeanGetTrDroppedStatDirectMove(),_str,_args);
    }

    public static Struct callEffectCounterAttackBeanGetTrSufferingDamageTypes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCounterAttackBeanGetTrSufferingDamageTypes(),_str,_args);
    }

    public static Struct callEffectCounterAttackBeanReasonsCounterGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCounterAttackBeanReasonsCounterGet(),_str,_args);
    }

    public static Struct callEffectCounterAttackBeanReasonsProtectGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCounterAttackBeanReasonsProtectGet(),_str,_args);
    }

    public static Struct callEffectCounterAttackBeanSufferingDamageDirectMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCounterAttackBeanSufferingDamageDirectMoveGet(),_str,_args);
    }

    public static Struct callEffectCounterAttackBeanSufferingDamageTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCounterAttackBeanSufferingDamageTypesGet(),_str,_args);
    }

    public static Struct callEffectFullHpRateBeanClosestFoeDamageRateHpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectFullHpRateBeanClosestFoeDamageRateHpGet(),_str,_args);
    }

    public static Struct callEffectFullHpRateBeanLeftUserHpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectFullHpRateBeanLeftUserHpGet(),_str,_args);
    }

    public static Struct callEffectFullHpRateBeanMapVarsRestoredGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectFullHpRateBeanMapVarsRestoredGet(),_str,_args);
    }

    public static Struct callEffectFullHpRateBeanRestoredHpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectFullHpRateBeanRestoredHpGet(),_str,_args);
    }



    public static Struct callEffectMultSufferedMovePowerBeanGetTrType(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectMultSufferedMovePowerBeanGetTrType(),_str,_args);
    }

    public static Struct callEffectMultSufferedMovePowerBeanMultMovePowerFctTypeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectMultSufferedMovePowerBeanMultMovePowerFctTypeGet(),_str,_args);
    }

    public static Struct callEffectMultUsedMovePowerBeanGetTrType(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectMultUsedMovePowerBeanGetTrType(),_str,_args);
    }

    public static Struct callEffectMultUsedMovePowerBeanMultMovePowerFctTypeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectMultUsedMovePowerBeanMultMovePowerFctTypeGet(),_str,_args);
    }

    public static Struct callEffectOrderBeanTargetAttacksLastGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectOrderBeanTargetAttacksLastGet(),_str,_args);
    }


    public static Struct callEffectRemainedHpRateBeanRateHpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRemainedHpRateBeanRateHpGet(),_str,_args);
    }

    public static Struct callEffectRemainedHpRateBeanWinHpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRemainedHpRateBeanWinHpGet(),_str,_args);
    }



    public static Struct callEffectVarPPBeanDeletePpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectVarPPBeanDeletePpGet(),_str,_args);
    }

    public static Struct callEffectWinMoneyBeanWinningRateBySumTargetUserGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectWinMoneyBeanWinningRateBySumTargetUserGet(),_str,_args);
    }
    protected static Struct dispMoveEffFullHpRate() {
        return dispMoveEffFullHpRate(feedDbMoveEffDataFullHpRate());
    }
    protected static Struct dispMoveEffFullHpRate(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<Struct> all_ = beanToEffectFullHpRate(pk_);
        StringMap<String> mapping_ = mappingToEffectFullHpRate();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<Struct> beanToEffectFullHpRate(PkData _pk) {
        StringMap<Struct> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_FULLHPRATE,_pk.beanEffectFullHpRateBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectFullHpRate() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFFFULLHPRATE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_FULLHPRATE);
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
        facade_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
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
        e_.setRestoredHp(DataBase.VAR_PREFIX+Fight.TEMPS_TOUR);
        e_.setClosestFoeDamageRateHp(Rate.one());
        e_.setLeftUserHp(Rate.one());
        return e_;
    }
    protected static Struct dispMoveEffCounterAttack() {
        return dispMoveEffCounterAttack(feedDbMoveEffDataCounterAttack());
    }
    protected static Struct dispMoveEffCounterAttack(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<Struct> all_ = beanToEffectCounterAttack(pk_);
        StringMap<String> mapping_ = mappingToEffectCounterAttack();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<Struct> beanToEffectCounterAttack(PkData _pk) {
        StringMap<Struct> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_COUNTER_ATTACK,_pk.beanEffectCounterAttackBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectCounterAttack() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFFCOUNTERATTACK_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_COUNTER_ATTACK);
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
        facade_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
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
        e_.setCounterFail(DataBase.VAR_PREFIX+Fight.TEMPS_TOUR);
        e_.setProtectFail(DataBase.VAR_PREFIX+Fight.TEMPS_TOUR);
        e_.setSufferingDamageDirectMove(Rate.one());
        e_.getSufferingDamageTypes().addEntry(T_TYPE1,Rate.one());
        e_.getDroppedStatDirectMove().addEntry(Statistic.SPEED,(byte)1);
        return e_;
    }
    protected static Struct dispMoveEffAlly() {
        return dispMoveEffAlly(feedDbMoveEffDataAlly());
    }
    protected static Struct dispMoveEffAlly(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<Struct> all_ = beanToEffectAlly(pk_);
        StringMap<String> mapping_ = mappingToEffectAlly();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<Struct> beanToEffectAlly(PkData _pk) {
        StringMap<Struct> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_ALLY,_pk.beanEffectAllyBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectAlly() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFFALLY_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_ALLY);
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
        facade_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
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
    protected static Struct dispMoveEffBatonPass() {
        return dispMoveEffBatonPass(feedDbMoveEffDataBatonPass());
    }
    protected static Struct dispMoveEffBatonPass(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<Struct> all_ = beanToEffectBatonPass(pk_);
        StringMap<String> mapping_ = mappingToEffectBatonPass();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<Struct> beanToEffectBatonPass(PkData _pk) {
        StringMap<Struct> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_BATONPASS,_pk.beanEffectBatonPassBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectBatonPass() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFFBATONPASS_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_BATONPASS);
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
        facade_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
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
    protected static Struct dispMoveEffClone() {
        return dispMoveEffClone(feedDbMoveEffDataClone());
    }
    protected static Struct dispMoveEffClone(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<Struct> all_ = beanToEffectClone(pk_);
        StringMap<String> mapping_ = mappingToEffectClone();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<Struct> beanToEffectClone(PkData _pk) {
        StringMap<Struct> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_CLONE,_pk.beanEffectCloneBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectClone() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFFCLONE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_CLONE);
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
        mu_.setDamageRateAgainstFoe(DataBase.VAR_PREFIX+Fight.TEMPS_TOUR);
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
        facade_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
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
        mu_.setDamageRateAgainstFoe(DataBase.VAR_PREFIX+Fight.TEMPS_TOUR);
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
}
