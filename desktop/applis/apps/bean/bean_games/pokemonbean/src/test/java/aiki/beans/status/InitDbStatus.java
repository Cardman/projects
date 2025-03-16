package aiki.beans.status;

import aiki.beans.*;
import aiki.facade.FacadeGame;
import aiki.fight.status.effects.*;
import code.scripts.pages.aiki.*;
import code.sml.util.*;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;
import code.maths.*;
import code.util.*;

public abstract class InitDbStatus extends InitDbStatusSet {

    public static int[][] callStatusBeanAnimStatusGet(int _str, int... _args) {
        return dispStatusOne(_str).getAnimStatus();
    }

    public static String callStatusBeanAttackGet(int _str, int... _args) {
        return StringUtil.nullToEmpty(dispStatusOne(_str).getAttack());
    }

    public static Rate callStatusBeanCatchingRateGet(int _str, int... _args) {
        return dispStatusOne(_str).getCatchingRate();
    }

    public static String callStatusBeanClickIndex(int _str, long... _args) {
        StatusBean s_ = dispStatusOne(_str);
        return navigateData(new StatusBeanClickIndex(s_), s_);
    }

    public static String callStatusBeanDefenseGet(int _str, int... _args) {
        return StringUtil.nullToEmpty(dispStatusOne(_str).getDefense());
    }

    public static boolean callStatusBeanDisabledEffIfSwitchGet(int _str, int... _args) {
        return dispStatusOne(_str).getDisabledEffIfSwitch();
    }

    public static String callStatusBeanDisplayNameGet(int _str, int... _args) {
        return dispStatusOne(_str).getDisplayName();
    }

    public static CustList<EffectPartnerStatus> callStatusBeanEffectsPartnerGet(int _str, int... _args) {
        return dispStatusOne(_str).getEffectsPartner();
    }

    public static boolean callStatusBeanEndRoundGet(int _str, int... _args) {
        return dispStatusOne(_str).getEndRound();
    }

    public static long callStatusBeanEndRoundRankGet(int _str, int... _args) {
        return dispStatusOne(_str).getEndRoundRank();
    }

    public static EffectPartnerStatus callStatusBeanGetEffectPartner(int _str, int... _args) {
        return dispStatusOne(_str).getEffectPartner();
    }

    public static String callStatusBeanGetTrMultStat(int _str, int... _args) {
        return dispStatusOne(_str).getTrMultStat(_args[0]);
    }

    public static long callStatusBeanIncrementEndRoundGet(int _str, int... _args) {
        return dispStatusOne(_str).getIncrementEndRound();
    }

    public static boolean callStatusBeanIncrementEndRoundInt(int _str, int... _args) {
        return dispStatusOne(_str).incrementEndRoundInt();
    }

    public static boolean callStatusBeanIncrementingDamageByRoundsGet(int _str, int... _args) {
        return dispStatusOne(_str).getIncrementingDamageByRounds();
    }

    public static boolean callStatusBeanIncrementingEndRoundGet(int _str, int... _args) {
        return dispStatusOne(_str).getIncrementingEndRound();
    }

    public static boolean callStatusBeanIsSingle(int _str, int... _args) {
        return dispStatusOne(_str).isSingle();
    }

    public static AbsMap<LgInt,Rate> callStatusBeanLawForUsingAMoveNbRoundGet(int _str, int... _args) {
        return dispStatusOne(_str).getLawForUsingAMoveNbRound();
    }

    public static AbsMap<String,String> callStatusBeanMapVarsFailEndRoundGet(int _str, int... _args) {
        return dispStatusOne(_str).getMapVarsFailEndRound();
    }

    public static AbsMap<String,String> callStatusBeanMapVarsFailGet(int _str, int... _args) {
        return dispStatusOne(_str).getMapVarsFail();
    }

    public static AbsMap<TranslatedKey,Rate> callStatusBeanMultStatGet(int _str, int... _args) {
        return dispStatusOne(_str).getMultStat();
    }

    public static boolean callStatusBeanNotAttackFoeGet(int _str, int... _args) {
        return dispStatusOne(_str).getNotAttackFoe();
    }

    public static boolean callStatusBeanNotAttackGet(int _str, int... _args) {
        return dispStatusOne(_str).getNotAttack();
    }

    public static Rate callStatusBeanPowerGet(int _str, int... _args) {
        return dispStatusOne(_str).getPower();
    }

    public static Rate callStatusBeanRateForFullHealIfMoveGet(int _str, int... _args) {
        return dispStatusOne(_str).getRateForFullHealIfMove();
    }

    public static Rate callStatusBeanRateForUsingAMoveGet(int _str, int... _args) {
        return dispStatusOne(_str).getRateForUsingAMove();
    }

    public static Rate callStatusBeanRateForUsingAMoveIfFoeGet(int _str, int... _args) {
        return dispStatusOne(_str).getRateForUsingAMoveIfFoe();
    }

    public static CustList<String> callStatusBeanReasonsEndRoundGet(int _str, int... _args) {
        return dispStatusOne(_str).getReasonsEndRound();
    }

    public static CustList<String> callStatusBeanReasonsGet(int _str, int... _args) {
        return dispStatusOne(_str).getReasons();
    }

    public static EffectPartnerStatus eltPart(CustList<EffectPartnerStatus> _l, int _i) {
        return _l.get(_i);
    }
    public static boolean callStatusBeanSingleStatusGet(int _str, int... _args) {
        return dispStatusOne(_str).getSingleStatus();
    }
    protected static StatusBean dispStatusOne(int _index) {
        FacadeGame pk_ = pkDataByFacade(feedDb());
        StringMap<BeanRenderWithAppName> all_ = beanToStatus(pk_);
        return transitToAllStatus(pk_, all_, _index);
    }
    public static StringMap<BeanRenderWithAppName> beanToStatus(FacadeGame _pk) {
        StringMap<BeanRenderWithAppName> map_ = beanToStatusSet(_pk);
        StatusBean s_ = new StatusBean();
        initBean(s_,EN,_pk);
        map_.addEntry(InitDbStatusSet.BEAN_STATUS, s_);
        s_.setBuilder(map_.getValue(0).getBuilder());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.STATUS,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.STATUS,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND,new TranslationsFile());
        map_.getValue(0).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_STATUS_DATA_HTML,s_);
        return map_;
    }
}