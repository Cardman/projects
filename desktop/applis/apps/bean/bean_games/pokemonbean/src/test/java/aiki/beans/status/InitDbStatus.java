package aiki.beans.status;

import aiki.beans.*;
import aiki.fight.status.effects.*;
import code.bean.nat.*;
import code.scripts.pages.aiki.*;
import code.sml.util.*;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;
import code.maths.*;
import code.util.*;

public abstract class InitDbStatus extends InitDbStatusSet {

    public static int[][] callStatusBeanAnimStatusGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getAnimStatus();
    }

    public static String callStatusBeanAttackGet(int _str, int... _args) {
        return StringUtil.nullToEmpty(( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getAttack());
    }

    public static Rate callStatusBeanCatchingRateGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getCatchingRate();
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

    public static CustList<EffectPartnerStatus> callStatusBeanEffectsPartnerGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getEffectsPartner();
    }

    public static boolean callStatusBeanEndRoundGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getEndRound();
    }

    public static long callStatusBeanEndRoundRankGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getEndRoundRank();
    }

    public static EffectPartnerStatus callStatusBeanGetEffectPartner(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getEffectPartner();
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

    public static AbsMap<LgInt,Rate> callStatusBeanLawForUsingAMoveNbRoundGet(int _str, int... _args) {
        return (( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getLawForUsingAMoveNbRound());
    }

    public static AbsMap<String,String> callStatusBeanMapVarsFailEndRoundGet(int _str, int... _args) {
        return (( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getMapVarsFailEndRound());
    }

    public static AbsMap<String,String> callStatusBeanMapVarsFailGet(int _str, int... _args) {
        return (( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getMapVarsFail());
    }

    public static AbsMap<TranslatedKey,Rate> callStatusBeanMultStatGet(int _str, int... _args) {
        return (( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getMultStat());
    }

    public static boolean callStatusBeanNotAttackFoeGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getNotAttackFoe();
    }

    public static boolean callStatusBeanNotAttackGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getNotAttack();
    }

    public static Rate callStatusBeanPowerGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getPower();
    }

    public static Rate callStatusBeanRateForFullHealIfMoveGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getRateForFullHealIfMove();
    }

    public static Rate callStatusBeanRateForUsingAMoveGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getRateForUsingAMove();
    }

    public static Rate callStatusBeanRateForUsingAMoveIfFoeGet(int _str, int... _args) {
        return ( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getRateForUsingAMoveIfFoe();
    }

    public static CustList<String> callStatusBeanReasonsEndRoundGet(int _str, int... _args) {
        return (( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getReasonsEndRound());
    }

    public static CustList<String> callStatusBeanReasonsGet(int _str, int... _args) {
        return (( (StatusBean) ((PokemonBeanStruct)dispStatusOne(_str)).getInstance()).getReasons());
    }

    public static EffectPartnerStatus eltPart(CustList<EffectPartnerStatus> _l, int _i) {
        return _l.get(_i);
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