package aiki.beans.items;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.enums.TargetChoice;
import code.bean.nat.*;
import code.maths.Rate;
import code.util.StringMap;

public abstract class InitDbHealing extends InitDbItem {

    public static String callHealingItemBeanClickHappiness(NaSt _str, long... _args) {
        return navigateData(new HealingItemBeanClickHappiness(),_str,_args);
    }

    public static String callHealingItemBeanClickHappinessId(NaSt _str, long... _args) {
        callHealingItemBeanClickHappiness(_str,_args);
        return getValItemId(_str);
    }

    public static NaSt callHealingItemBeanGetTrHappiness(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingItemBeanGetTrHappiness(),_str,_args);
    }

    public static NaSt callHealingItemBeanHappinessGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingItemBeanHappinessGet(),_str,_args);
    }

    public static NaSt callHealingItemBeanHealingItemBeanGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingItemBeanHealingItemBeanGet(),_str,_args);
    }

    public static NaSt callHealingItemBeanHealingTeamGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingItemBeanHealingTeamGet(),_str,_args);
    }

    public static NaSt callHealingItemBeanIsBall(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingItemBeanIsBall(),_str,_args);
    }

    public static StringMap<NaSt> beanToHealing(PkData _pk) {
        StringMap<NaSt> map_ = beanToItem(_pk);
        map_.addEntry(AikiBeansItemsStd.BEAN_HEALINGITEM,_pk.beanHealingItemBean(EN));
        return map_;
    }

    protected static NaSt healSimple(boolean _healingMoveFullpp) {
        PkData pk_ = pkDataByFacade(feedDbSimple(_healingMoveFullpp));
        StringMap<NaSt> all_ = beanToHealing(pk_);
        callHealingItemBeanHealingItemBeanGet(all_.getVal(AikiBeansItemsStd.BEAN_HEALINGITEM));
        return dispLine(AikiBeansItemsStd.BEAN_HEALINGITEM, pk_, all_);
    }

    private static FacadeGame feedDbSimple(boolean _healingMoveFullpp) {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_DAM,moveDam(TargetChoice.ANY_FOE));
        facade_.getData().completeMembers(I_BASE,healItem(_healingMoveFullpp));
        facade_.getData().completeMembers(I_BALL,ball());
        facade_.getData().completeMembers(I_BOOST,boost());
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
