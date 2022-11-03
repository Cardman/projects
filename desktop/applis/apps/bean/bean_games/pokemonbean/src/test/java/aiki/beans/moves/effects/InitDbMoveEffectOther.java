package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
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

    public static Struct callEffectBatonPassBeanClickMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBatonPassBeanClickMove(),_str,_args);
    }

    public static Struct callEffectBatonPassBeanGetTrMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBatonPassBeanGetTrMove(),_str,_args);
    }

    public static Struct callEffectBatonPassBeanMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBatonPassBeanMovesGet(),_str,_args);
    }

    public static Struct callEffectCloneBeanClickMoveBatonPass(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCloneBeanClickMoveBatonPass(),_str,_args);
    }

    public static Struct callEffectCloneBeanClickMoveEndRound(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCloneBeanClickMoveEndRound(),_str,_args);
    }

    public static Struct callEffectCloneBeanClickMoveSending(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCloneBeanClickMoveSending(),_str,_args);
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
}
