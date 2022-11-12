package aiki.beans.status;

import aiki.beans.*;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public abstract class InitDbStatus extends InitDbStatusSet {

    public static Struct callStatusBeanAnimStatusGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanAnimStatusGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanAttackGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanAttackGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanCatchingRateGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanCatchingRateGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanClickIndex(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanClickIndex(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanDefenseGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanDefenseGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanDisabledEffIfSwitchGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanDisabledEffIfSwitchGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanDisplayNameGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanDisplayNameGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanEffectsPartnerGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanEffectsPartnerGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanEndRoundGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanEndRoundGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanEndRoundRankGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanEndRoundRankGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanGetEffectPartner(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanGetEffectPartner(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanGetTrMultStat(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanGetTrMultStat(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanIncrementEndRoundGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanIncrementEndRoundGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanIncrementEndRoundInt(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanIncrementEndRoundInt(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanIncrementingDamageByRoundsGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanIncrementingDamageByRoundsGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanIncrementingEndRoundGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanIncrementingEndRoundGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanIsSingle(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanIsSingle(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanLawForUsingAMoveNbRoundGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanLawForUsingAMoveNbRoundGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanMapVarsFailEndRoundGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanMapVarsFailEndRoundGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanMapVarsFailGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanMapVarsFailGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanMultStatGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanMultStatGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanNotAttackFoeGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanNotAttackFoeGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanNotAttackGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanNotAttackGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanPowerGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanPowerGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanRateForFullHealIfMoveGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanRateForFullHealIfMoveGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanRateForUsingAMoveGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanRateForUsingAMoveGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanRateForUsingAMoveIfFoeGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanRateForUsingAMoveIfFoeGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanReasonsEndRoundGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanReasonsEndRoundGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanReasonsGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanReasonsGet(),dispStatusOne(_str),_args);
    }

    public static Struct callStatusBeanSingleStatusGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanSingleStatusGet(),dispStatusOne(_str),_args);
    }
    protected static Struct dispStatusOne(int _index) {
        PkData pk_ = pkDataByFacade(feedDb());
        StringMap<Struct> all_ = beanToStatus(pk_);
        return transitToAllStatus(pk_, all_, _index);
    }
    public static StringMap<Struct> beanToStatus(PkData _pk) {
        StringMap<Struct> map_ = beanToStatusSet(_pk);
        map_.addEntry(AikiBeansStatusStd.BEAN_STATUS,_pk.beanStatusBean(EN));
        return map_;
    }
}
