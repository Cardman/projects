package aiki.beans.items;

import aiki.beans.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.items.*;
import aiki.fight.moves.enums.TargetChoice;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import code.util.StringMap;

public abstract class InitDbHealingStatus extends InitDbHealing {

    public static String callHealingStatusBeanClickStatus(NaSt _str, int... _args) {
        return new NaStSt(( (HealingStatusBean) ((PokemonBeanStruct)_str).getInstance()).clickStatus(_args[0])).getInstance();
    }

    public static String callHealingStatusBeanClickStatusId(NaSt _str, int... _args) {
        callHealingStatusBeanClickStatus(_str,_args);
        return getValStatusId(_str);
    }

    public static NaSt callHealingStatusBeanGetTrStatus(NaSt _str, int... _args) {
        return new NaStSt(( (HealingStatusBean) ((PokemonBeanStruct)_str).getInstance()).getTrStatus(_args[0]));
    }

    public static NaSt callHealingStatusBeanHealedHpRateGet(NaSt _str, int... _args) {
        return new RtSt(( (HealingStatusBean) ((PokemonBeanStruct)_str).getInstance()).getHealedHpRate());
    }

    public static NaSt callHealingStatusBeanHealingKoGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (HealingStatusBean) ((PokemonBeanStruct)_str).getInstance()).getHealingKo());
    }

    public static NaSt callHealingStatusBeanHealingStatusBeanGet(NaSt _str, int... _args) {
        return new NaStSt(HealingStatusBean.HEALING_STATUS_BEAN);
    }

    public static NaSt callHealingStatusBeanStatusGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (HealingStatusBean) ((PokemonBeanStruct)_str).getInstance()).getStatus());
    }

    public static StringMap<NaSt> beanToHealingStatus(PkData _pk) {
        StringMap<NaSt> map_ = beanToHealing(_pk);
        HealingStatusBean s_ = new HealingStatusBean();
        map_.addEntry(InitDbItems.BEAN_HEALINGSTATUS, _pk.bean(s_, EN));
        s_.setBuilder(((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_HEALINGSTATUS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_HEALINGSTATUS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_HEALINGHPSTATUS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_HEALINGHPSTATUS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_HEALINGSTATUS_HTML,s_);
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_HEALINGHPSTATUS_HTML,s_);
        return map_;
    }
    protected static NaSt statusDb(HealingStatus _heal) {
        PkData pk_ = pkDataByFacade(feedDbStatus(_heal));
        StringMap<NaSt> all_ = beanToHealingStatus(pk_);
        callHealingStatusBeanHealingStatusBeanGet(all_.getVal(InitDbItems.BEAN_HEALINGSTATUS));
        return dispLineClick(InitDbItems.BEAN_HEALINGSTATUS, pk_, all_);
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