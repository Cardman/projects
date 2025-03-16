package aiki.beans.items;

import aiki.beans.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.Rate;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import aiki.comparators.*;
import code.util.*;

public abstract class InitDbHealing extends InitDbItem {

    public static String callHealingItemBeanClickHappiness(HealingItemBean _str, int... _args) {
        return _str.clickHappiness(_args[0]);
    }

    public static String callHealingItemBeanClickHappinessId(HealingItemBean _str, int... _args) {
        callHealingItemBeanClickHappiness(_str,_args);
        return getValItemId(_str);
    }

    public static String callHealingItemBeanGetTrHappiness(HealingItemBean _str, int... _args) {
        return _str.getTrHappiness(_args[0]);
    }

    public static DictionaryComparator<TranslatedKey,Long> callHealingItemBeanHappinessGet(HealingItemBean _str, int... _args) {
        return _str.getHappiness();
    }

    public static String callHealingItemBeanHealingItemBeanGet(BeanRenderWithAppName _str, int... _args) {
        return HealingItemBean.HEALING_ITEM_BEAN;
    }

    public static boolean callHealingItemBeanHealingTeamGet(HealingItemBean _str, int... _args) {
        return _str.getHealingTeam();
    }

    public static boolean callHealingItemBeanIsBall(HealingItemBean _str, int... _args) {
        return _str.isBall(_args[0]);
    }

    public static StringMap<BeanRenderWithAppName> beanToHealing(FacadeGame _pk) {
        StringMap<BeanRenderWithAppName> map_ = beanToItem(_pk);
        SimpleHealingItemBean s_ = new SimpleHealingItemBean();
        initBean(s_,EN,_pk);
        map_.addEntry(InitDbItems.BEAN_HEALINGITEM, s_);
        s_.setBuilder(map_.getValue(0).getBuilder());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_HEALINGITEM,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_HEALINGITEM,new TranslationsFile());
        map_.getValue(0).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_HEALINGITEM_HTML,s_);
        return map_;
    }

    protected static HealingItemBean healSimple(boolean _healingMoveFullpp) {
        FacadeGame pk_ = pkDataByFacade(feedDbSimple(_healingMoveFullpp));
        StringMap<BeanRenderWithAppName> all_ = beanToHealing(pk_);
        callHealingItemBeanHealingItemBeanGet(all_.getVal(InitDbItems.BEAN_HEALINGITEM));
        return (HealingItemBean) dispLineClick(InitDbItems.BEAN_HEALINGITEM, pk_, all_);
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