package aiki.beans.status;

import aiki.beans.*;
import code.bean.nat.*;
import code.scripts.pages.aiki.*;
import code.sml.util.*;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class InitDbStatus extends InitDbStatusSet {

    public static NaSt callStatusBeanAnimStatusGet(int _str, int... _args) {
        return new NaImgSt(( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getAnimStatus());
    }

    public static String callStatusBeanAttackGet(int _str, int... _args) {
        return StringUtil.nullToEmpty(( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getAttack());
    }

    public static NaSt callStatusBeanCatchingRateGet(int _str, int... _args) {
        return new RtSt(( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getCatchingRate());
    }

    public static String callStatusBeanClickIndex(int _str, long... _args) {
        NaSt s_ = dispStatusOne(_str);
        return navigateData(new StatusBeanClickIndex((StatusBean) ((PokemonBeanStruct)s_).getBean()), s_);
    }

    public static String callStatusBeanDefenseGet(int _str, int... _args) {
        return StringUtil.nullToEmpty(( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getDefense());
    }

    public static boolean callStatusBeanDisabledEffIfSwitchGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getDisabledEffIfSwitch();
    }

    public static String callStatusBeanDisplayNameGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getDisplayName();
    }

    public static NaSt callStatusBeanEffectsPartnerGet(int _str, int... _args) {
        return PokemonStandards.getEffPartStat(( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getEffectsPartner());
    }

    public static boolean callStatusBeanEndRoundGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getEndRound();
    }

    public static long callStatusBeanEndRoundRankGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getEndRoundRank();
    }

    public static NaSt callStatusBeanGetEffectPartner(int _str, int... _args) {
        return new EffectPartnerStatusStruct(( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getEffectPartner());
    }

    public static String callStatusBeanGetTrMultStat(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getTrMultStat(_args[0]);
    }

    public static long callStatusBeanIncrementEndRoundGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getIncrementEndRound();
    }

    public static boolean callStatusBeanIncrementEndRoundInt(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).incrementEndRoundInt();
    }

    public static boolean callStatusBeanIncrementingDamageByRoundsGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getIncrementingDamageByRounds();
    }

    public static boolean callStatusBeanIncrementingEndRoundGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getIncrementingEndRound();
    }

    public static boolean callStatusBeanIsSingle(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).isSingle();
    }

    public static NaSt callStatusBeanLawForUsingAMoveNbRoundGet(int _str, int... _args) {
        return PokemonStandards.getLgIntRate(( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getLawForUsingAMoveNbRound());
    }

    public static NaSt callStatusBeanMapVarsFailEndRoundGet(int _str, int... _args) {
        return PokemonStandards.getStrStr(( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getMapVarsFailEndRound());
    }

    public static NaSt callStatusBeanMapVarsFailGet(int _str, int... _args) {
        return PokemonStandards.getStrStr(( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getMapVarsFail());
    }

    public static NaSt callStatusBeanMultStatGet(int _str, int... _args) {
        return PokemonStandards.getStaRate(( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getMultStat());
    }

    public static boolean callStatusBeanNotAttackFoeGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getNotAttackFoe();
    }

    public static boolean callStatusBeanNotAttackGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getNotAttack();
    }

    public static NaSt callStatusBeanPowerGet(int _str, int... _args) {
        return new RtSt(( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getPower());
    }

    public static NaSt callStatusBeanRateForFullHealIfMoveGet(int _str, int... _args) {
        return new RtSt(( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getRateForFullHealIfMove());
    }

    public static NaSt callStatusBeanRateForUsingAMoveGet(int _str, int... _args) {
        return new RtSt(( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getRateForUsingAMove());
    }

    public static NaSt callStatusBeanRateForUsingAMoveIfFoeGet(int _str, int... _args) {
        return new RtSt(( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getRateForUsingAMoveIfFoe());
    }

    public static NaSt callStatusBeanReasonsEndRoundGet(int _str, int... _args) {
        return BeanNatCommonLgNames.getStringArray(( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getReasonsEndRound());
    }

    public static NaSt callStatusBeanReasonsGet(int _str, int... _args) {
        return BeanNatCommonLgNames.getStringArray(( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getReasons());
    }

    public static boolean callStatusBeanSingleStatusGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getSingleStatus();
    }
    protected static NaSt dispStatusOne(int _index) {
        PkData pk_ = pkDataByFacade(feedDb());
        StringMap<NaSt> all_ = beanToStatus(pk_);
        return transitToAllStatus(pk_, all_, _index);
    }
    public static StringMap<NaSt> beanToStatus(PkData _pk) {
        StringMap<NaSt> map_ = beanToStatusSet(_pk);
        StatusBean s_ = new StatusBean();
        map_.addEntry(InitDbStatusSet.BEAN_STATUS, _pk.bean(s_, EN));
        s_.setBuilder(((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.STATUS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.STATUS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_STATUS_DATA_HTML,s_);
        return map_;
    }
}