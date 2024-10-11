package aiki.beans.items;

import aiki.beans.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.enums.TargetChoice;
import code.bean.nat.*;
import code.maths.Rate;
import code.util.StringMap;

public abstract class InitDbBerry extends InitDbItem {

    public static NaSt callBerryBeanBoostStatisGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanBoostStatisGet(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanCategoryBoostingGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanCategoryBoostingGet(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static String callBerryBeanClickStatus(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return callBerryBeanClickStatus(healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static String callBerryBeanClickStatus(NaSt _str, long... _args) {
        return navigateData(new BerryBeanClickStatus(),_str,_args);
    }

    public static String callBerryBeanClickStatusId(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        NaSt b_ = healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail);
        callBerryBeanClickStatus(b_, _args);
        return getValStatusId(b_);
    }

    public static NaSt callBerryBeanDamageRateRecoilFoeGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanDamageRateRecoilFoeGet(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanGetTrBoostStat(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanGetTrBoostStat(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanGetTrCategRecoil(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanGetTrCategRecoil(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanGetTrMultFoesDamage(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanGetTrMultFoesDamage(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanGetTrMultStat(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanGetTrMultStat(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanGetTrStatus(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanGetTrStatus(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanHealHpBySuperEffMoveGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanHealHpBySuperEffMoveGet(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanHealHpGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanHealHpGet(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanHealHpRateGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanHealHpRateGet(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanHealPpGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanHealPpGet(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanHealStatusGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanHealStatusGet(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanIsHealingPp(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanIsHealingPp(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanLawForAttackFirstGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanLawForAttackFirstGet(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanMaxHpHealingHpGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanMaxHpHealingHpGet(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanMaxHpHealingHpRateGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanMaxHpHealingHpRateGet(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanMultFoesDamageGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanMultFoesDamageGet(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanMultStatGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanMultStatGet(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static NaSt callBerryBeanWithoutFailGet(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BerryBeanWithoutFailGet(),healSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail),_args);
    }

    public static StringMap<NaSt> beanToBerry(PkData _pk) {
        StringMap<NaSt> map_ = beanToItem(_pk);
        map_.addEntry(AikiBeansItemsStd.BEAN_BERRY,_pk.beanBerryBean(EN));
        return map_;
    }

    protected static NaSt healSimple(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail) {
        PkData pk_ = pkDataByFacade(feedDbSimple(_r, _pp, _hp, _eff, _cat, _lawForAttackFirst, _withoutFail));
        StringMap<NaSt> all_ = beanToBerry(pk_);
        return dispLine(AikiBeansItemsStd.BEAN_BERRY, pk_, all_);
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
