package aiki.beans.items;

import aiki.beans.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.enums.TargetChoice;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import aiki.comparators.*;
import code.util.*;

public abstract class InitDbHealing extends InitDbItem {

    public static String callHealingItemBeanClickHappiness(NaSt _str, int... _args) {
        return ( (HealingItemBean) ((PokemonBeanStruct)_str).getInstance()).clickHappiness(_args[0]);
    }

    public static String callHealingItemBeanClickHappinessId(NaSt _str, int... _args) {
        callHealingItemBeanClickHappiness(_str,_args);
        return getValItemId(_str);
    }

    public static String callHealingItemBeanGetTrHappiness(NaSt _str, int... _args) {
        return ( (HealingItemBean) ((PokemonBeanStruct)_str).getInstance()).getTrHappiness(_args[0]);
    }

    public static DictionaryComparator<TranslatedKey,Long> callHealingItemBeanHappinessGet(NaSt _str, int... _args) {
        return (( (HealingItemBean) ((PokemonBeanStruct)_str).getInstance()).getHappiness());
    }

    public static String callHealingItemBeanHealingItemBeanGet(NaSt _str, int... _args) {
        return HealingItemBean.HEALING_ITEM_BEAN;
    }

    public static boolean callHealingItemBeanHealingTeamGet(NaSt _str, int... _args) {
        return ( (HealingItemBean) ((PokemonBeanStruct)_str).getInstance()).getHealingTeam();
    }

    public static boolean callHealingItemBeanIsBall(NaSt _str, int... _args) {
        return ( (HealingItemBean) ((PokemonBeanStruct)_str).getInstance()).isBall(_args[0]);
    }

    public static StringMap<NaSt> beanToHealing(PkData _pk) {
        StringMap<NaSt> map_ = beanToItem(_pk);
        SimpleHealingItemBean s_ = new SimpleHealingItemBean();
        map_.addEntry(InitDbItems.BEAN_HEALINGITEM, _pk.bean(s_, EN));
        s_.setBuilder(((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_HEALINGITEM,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_HEALINGITEM,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_HEALINGITEM_HTML,s_);
        return map_;
    }

    protected static NaSt healSimple(boolean _healingMoveFullpp) {
        PkData pk_ = pkDataByFacade(feedDbSimple(_healingMoveFullpp));
        StringMap<NaSt> all_ = beanToHealing(pk_);
        callHealingItemBeanHealingItemBeanGet(all_.getVal(InitDbItems.BEAN_HEALINGITEM));
        return dispLineClick(InitDbItems.BEAN_HEALINGITEM, pk_, all_);
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