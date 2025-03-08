package aiki.beans.status;

import aiki.beans.*;
import code.bean.nat.*;
import code.scripts.pages.aiki.*;
import code.sml.util.*;
import code.util.StringMap;

public abstract class InitDbStatus extends InitDbStatusSet {

    public static NaSt callStatusBeanAnimStatusGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanAnimStatusGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanAttackGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanAttackGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanCatchingRateGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanCatchingRateGet(),dispStatusOne(_str),_args);
    }

    public static String callStatusBeanClickIndex(int _str, long... _args) {
        NaSt s_ = dispStatusOne(_str);
        return navigateData(new StatusBeanClickIndex((StatusBean) ((PokemonBeanStruct)s_).getBean()), s_);
    }

    public static NaSt callStatusBeanDefenseGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanDefenseGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanDisabledEffIfSwitchGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanDisabledEffIfSwitchGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanDisplayNameGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanDisplayNameGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanEffectsPartnerGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanEffectsPartnerGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanEndRoundGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanEndRoundGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanEndRoundRankGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanEndRoundRankGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanGetEffectPartner(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanGetEffectPartner(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanGetTrMultStat(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanGetTrMultStat(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanIncrementEndRoundGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanIncrementEndRoundGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanIncrementEndRoundInt(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanIncrementEndRoundInt(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanIncrementingDamageByRoundsGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanIncrementingDamageByRoundsGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanIncrementingEndRoundGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanIncrementingEndRoundGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanIsSingle(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanIsSingle(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanLawForUsingAMoveNbRoundGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanLawForUsingAMoveNbRoundGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanMapVarsFailEndRoundGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanMapVarsFailEndRoundGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanMapVarsFailGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanMapVarsFailGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanMultStatGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanMultStatGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanNotAttackFoeGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanNotAttackFoeGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanNotAttackGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanNotAttackGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanPowerGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanPowerGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanRateForFullHealIfMoveGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanRateForFullHealIfMoveGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanRateForUsingAMoveGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanRateForUsingAMoveGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanRateForUsingAMoveIfFoeGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanRateForUsingAMoveIfFoeGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanReasonsEndRoundGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanReasonsEndRoundGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanReasonsGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanReasonsGet(),dispStatusOne(_str),_args);
    }

    public static NaSt callStatusBeanSingleStatusGet(int _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusBeanSingleStatusGet(),dispStatusOne(_str),_args);
    }
    protected static NaSt dispStatusOne(int _index) {
        PkData pk_ = pkDataByFacade(feedDb());
        StringMap<NaSt> all_ = beanToStatus(pk_);
        return transitToAllStatus(pk_, all_, _index);
    }
    public static StringMap<NaSt> beanToStatus(PkData _pk) {
        StringMap<NaSt> map_ = beanToStatusSet(_pk);
        StatusBean s_ = new StatusBean();
        map_.addEntry(AikiBeansStatusStd.BEAN_STATUS, _pk.bean(s_, EN));
        s_.setBuilder(((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.STATUS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.STATUS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_STATUS_DATA_HTML,s_);
        return map_;
    }
}
