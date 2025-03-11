package aiki.beans.items;

import aiki.beans.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.enums.TargetChoice;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.pages.aiki.*;
import code.sml.util.*;
import code.util.StringMap;

public abstract class InitDbBerry extends InitDbItem {

    public static NaSt callBerryBeanBoostStatisGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return PokemonStandards.getStaByte(( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getBoostStatis());
    }

    public static String callBerryBeanCategoryBoostingGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return ( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getCategoryBoosting().getTranslation();
    }

    public static String callBerryBeanClickStatus(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return callBerryBeanClickStatus(healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static String callBerryBeanClickStatus(NaSt _str, int... _args) {
        return ( (BerryBean) ((PokemonBeanStruct)_str).getInstance()).clickStatus(_args[0]);
    }

    public static String callBerryBeanClickStatusId(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        NaSt b_ = healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail);
        callBerryBeanClickStatus(b_, _args);
        return getValStatusId(b_);
    }

    public static NaSt callBerryBeanDamageRateRecoilFoeGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return PokemonStandards.getStrRate(( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getDamageRateRecoilFoe());
    }

    public static String callBerryBeanGetTrBoostStat(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return ( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getTrBoostStat(_args[0]);
    }

    public static String callBerryBeanGetTrCategRecoil(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return ( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getTrCategRecoil(_args[0]);
    }

    public static String callBerryBeanGetTrMultFoesDamage(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return ( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getTrMultFoesDamage(_args[0]);
    }

    public static String callBerryBeanGetTrMultStat(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return ( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getTrMultStat(_args[0]);
    }

    public static String callBerryBeanGetTrStatus(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return ( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getTrStatus(_args[0]);
    }

    public static Rate callBerryBeanHealHpBySuperEffMoveGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return ( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getHealHpBySuperEffMove();
    }

    public static Rate callBerryBeanHealHpGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return ( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getHealHp();
    }

    public static Rate callBerryBeanHealHpRateGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return ( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getHealHpRate();
    }

    public static long callBerryBeanHealPpGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return ( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getHealPp();
    }

    public static NaSt callBerryBeanHealStatusGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return PokemonStandards.getKeys(( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getHealStatus());
    }

    public static boolean callBerryBeanIsHealingPp(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return ( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).isHealingPp();
    }

    public static boolean callBerryBeanLawForAttackFirstGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return ( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getLawForAttackFirst();
    }

    public static Rate callBerryBeanMaxHpHealingHpGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return ( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getMaxHpHealingHp();
    }

    public static Rate callBerryBeanMaxHpHealingHpRateGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return ( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getMaxHpHealingHpRate();
    }

    public static NaSt callBerryBeanMultFoesDamageGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return PokemonStandards.getEffRateStr(( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getMultFoesDamage());
    }

    public static NaSt callBerryBeanMultStatGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return PokemonStandards.getStaBoost(( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getMultStat());
    }

    public static boolean callBerryBeanWithoutFailGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, int... _args) {
        return ( (BerryBean) ((PokemonBeanStruct)healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail)).getInstance()).getWithoutFail();
    }

    public static StringMap<NaSt> beanToBerry(PkData _pk) {
        StringMap<NaSt> map_ = beanToItem(_pk);
        BerryBean b_ = new BerryBean();
        map_.addEntry(InitDbItems.BEAN_BERRY, _pk.bean(b_, EN));
        b_.setBuilder(((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_BERRY,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_BERRY,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_BERRY_HTML,b_);
        return map_;
    }

    protected static NaSt healSimple(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail) {
        PkData pk_ = pkDataByFacade(feedDbSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail));
        StringMap<NaSt> all_ = beanToBerry(pk_);
        return dispLineClick(InitDbItems.BEAN_BERRY, pk_, all_);
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
