package aiki.beans.items;

import aiki.beans.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.Rate;
import code.scripts.pages.aiki.*;
import code.sml.util.*;
import aiki.comparators.*;
import aiki.fight.util.*;
import code.util.*;

public abstract class InitDbBerry extends InitDbItem {

    public static DictionaryComparator<TranslatedKey,Long> callBerryBeanBoostStatisGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getBoostStatis();
    }

    public static String callBerryBeanCategoryBoostingGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getCategoryBoosting().getTranslation();
    }

    public static String callBerryBeanClickStatus(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return callBerryBeanClickStatus(healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static String callBerryBeanClickStatus(BerryBean _str, int... _args) {
        return _str.clickStatus(_args[0]);
    }

    public static String callBerryBeanClickStatusId(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        BerryBean b_ = healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail);
        callBerryBeanClickStatus(b_, _args);
        return getValStatusId(b_);
    }

    public static DictionaryComparator<TranslatedKey,Rate> callBerryBeanDamageRateRecoilFoeGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getDamageRateRecoilFoe();
    }

    public static String callBerryBeanGetTrBoostStat(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getTrBoostStat(_args[0]);
    }

    public static String callBerryBeanGetTrCategRecoil(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getTrCategRecoil(_args[0]);
    }

    public static String callBerryBeanGetTrMultFoesDamage(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getTrMultFoesDamage(_args[0]);
    }

    public static String callBerryBeanGetTrMultStat(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getTrMultStat(_args[0]);
    }

    public static String callBerryBeanGetTrStatus(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getTrStatus(_args[0]);
    }

    public static Rate callBerryBeanHealHpBySuperEffMoveGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getHealHpBySuperEffMove();
    }

    public static Rate callBerryBeanHealHpGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getHealHp();
    }

    public static Rate callBerryBeanHealHpRateGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getHealHpRate();
    }

    public static long callBerryBeanHealPpGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getHealPp();
    }

    public static CustList<TranslatedKey> callBerryBeanHealStatusGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getHealStatus();
    }

    public static boolean callBerryBeanIsHealingPp(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).isHealingPp();
    }

    public static boolean callBerryBeanLawForAttackFirstGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getLawForAttackFirst();
    }

    public static Rate callBerryBeanMaxHpHealingHpGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getMaxHpHealingHp();
    }

    public static Rate callBerryBeanMaxHpHealingHpRateGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getMaxHpHealingHpRate();
    }

    public static AbsMap<TranslatedKey,EfficiencyRate> callBerryBeanMultFoesDamageGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getMultFoesDamage();
    }

    public static AbsMap<TranslatedKey,BoostHpRate> callBerryBeanMultStatGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getMultStat();
    }

    public static boolean callBerryBeanWithoutFailGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail).getWithoutFail();
    }

    public static StringMap<BeanRenderWithAppName> beanToBerry(FacadeGame _pk) {
        StringMap<BeanRenderWithAppName> map_ = beanToItem(_pk);
        BerryBean b_ = new BerryBean();
        initBean(b_,EN,_pk);
        map_.addEntry(InitDbItems.BEAN_BERRY, b_);
        b_.setBuilder(map_.getValue(0).getBuilder());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_BERRY,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_BERRY,new TranslationsFile());
        map_.getValue(0).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_BERRY_HTML,b_);
        return map_;
    }

    protected static BerryBean healSimple(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail) {
        FacadeGame pk_ = pkDataByFacade(feedDbSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail));
        StringMap<BeanRenderWithAppName> all_ = beanToBerry(pk_);
        return (BerryBean) dispLineClick(InitDbItems.BEAN_BERRY, pk_, all_);
    }

    private static FacadeGame feedDbSimple(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail) {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_DAM,moveDam(TargetChoice.ANY_FOE));
        facade_.getData().completeMembers(I_BASE,berry(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail));
        facade_.getData().completeMembers(I_BALL,ball());
        facade_.getData().completeMembers(I_BOOST,boost());
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        trsCore(facade_);
        facade_.getData().getTranslatedItems().getVal(EN).addEntry(I_BASE,I_BASE_TR);
        facade_.getData().getTranslatedClassesDescriptions().addEntry(LANGUAGE,new StringMap<String>());
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_BASE).getItemType(),CI_BOOST_TR);
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_BALL).getItemType(),CI_BALL_TR);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().addConstNumTest(DataBase.MAX_EV, Rate.newRate("2"));
        facade_.getData().completeVariables();
        facade_.getData().getMiniItems().addEntry(I_BASE, instance(IMG_MAX_RAI));
        facade_.getData().getMiniItems().addEntry(I_BALL, instance(IMG_MAX_RAI2));
        facade_.getData().getMiniItems().addEntry(I_BOOST, instance(IMG_MAX_RAI));
        return facade_;
    }
}
