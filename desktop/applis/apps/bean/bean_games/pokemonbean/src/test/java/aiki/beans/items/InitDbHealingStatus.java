package aiki.beans.items;

import aiki.beans.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.items.*;
import aiki.fight.moves.enums.TargetChoice;
import aiki.instances.Instances;
import code.maths.Rate;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import code.util.*;

public abstract class InitDbHealingStatus extends InitDbHealing {

    public static String callHealingStatusBeanClickStatus(HealingStatusBean _str, int... _args) {
        return _str.clickStatus(_args[0]);
    }

    public static String callHealingStatusBeanClickStatusId(HealingStatusBean _str, int... _args) {
        callHealingStatusBeanClickStatus(_str,_args);
        return getValStatusId(_str);
    }

    public static String callHealingStatusBeanGetTrStatus(HealingStatusBean _str, int... _args) {
        return _str.getTrStatus(_args[0]);
    }

    public static Rate callHealingStatusBeanHealedHpRateGet(HealingStatusBean _str, int... _args) {
        return _str.getHealedHpRate();
    }

    public static boolean callHealingStatusBeanHealingKoGet(HealingStatusBean _str, int... _args) {
        return _str.getHealingKo();
    }

    public static String callHealingStatusBeanHealingStatusBeanGet(BeanRenderWithAppName _str, int... _args) {
        return HealingStatusBean.HEALING_STATUS_BEAN;
    }

    public static CustList<TranslatedKey> callHealingStatusBeanStatusGet(HealingStatusBean _str, int... _args) {
        return _str.getStatus();
    }

    public static StringMap<BeanRenderWithAppName> beanToHealingStatus(FacadeGame _pk) {
        StringMap<BeanRenderWithAppName> map_ = beanToHealing(_pk);
        HealingStatusBean s_ = new HealingStatusBean();
        initBean(s_,EN,_pk);
        map_.addEntry(InitDbItems.BEAN_HEALINGSTATUS, s_);
        s_.setBuilder(map_.getValue(0).getBuilder());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_HEALINGSTATUS,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_HEALINGSTATUS,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_HEALINGHPSTATUS,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_HEALINGHPSTATUS,new TranslationsFile());
        map_.getValue(0).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_HEALINGSTATUS_HTML,s_);
        map_.getValue(0).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_HEALINGHPSTATUS_HTML,s_);
        return map_;
    }
    protected static HealingStatusBean statusDb(HealingStatus _heal) {
        FacadeGame pk_ = pkDataByFacade(feedDbStatus(_heal));
        StringMap<BeanRenderWithAppName> all_ = beanToHealingStatus(pk_);
        callHealingStatusBeanHealingStatusBeanGet(all_.getVal(InitDbItems.BEAN_HEALINGSTATUS));
        return (HealingStatusBean) dispLineClick(InitDbItems.BEAN_HEALINGSTATUS, pk_, all_);
    }

    private static FacadeGame feedDbStatus(HealingStatus _heal) {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_DAM,moveDam(TargetChoice.ANY_FOE));

        facade_.getData().completeMembers(I_BASE,_heal);
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
    protected static HealingStatus simple() {
        return Instances.newHealingSimpleStatus();
    }
    protected static HealingStatus full(boolean _h) {
        HealingHpStatus h_ = Instances.newHealingHpStatus();
        h_.setHealingKo(_h);
        h_.setHealedHpRate(Rate.one());
        h_.getStatus().add(S_STA_SIM);
        return h_;
    }
}