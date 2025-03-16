package aiki.beans.items;

import aiki.beans.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.items.HealingHp;
import aiki.fight.moves.enums.TargetChoice;
import aiki.instances.Instances;
import code.maths.Rate;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import code.util.StringMap;

public abstract class InitDbHealingHp extends InitDbHealing {

    public static Rate callHealingHpBeanHpGet() {
        return ppDb().getHp();
    }
    public static StringMap<BeanRenderWithAppName> beanToHealingHp(PkData _pk) {
        StringMap<BeanRenderWithAppName> map_ = beanToHealing(_pk);
        HealingHpBean s_ = new HealingHpBean();
        initBean(s_,EN,_pk.getDataBase());
        map_.addEntry(InitDbItems.BEAN_HEALINGHP, s_);
        s_.setBuilder(map_.getValue(0).getBuilder());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_HEALINGHP,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_HEALINGHP,new TranslationsFile());
        map_.getValue(0).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_HEALINGHP_HTML,s_);
        return map_;
    }
    protected static HealingHpBean ppDb() {
        PkData pk_ = pkDataByFacade(feedDbHp());
        StringMap<BeanRenderWithAppName> all_ = beanToHealingHp(pk_);
        return (HealingHpBean) dispLineClick(InitDbItems.BEAN_HEALINGHP, pk_, all_);
    }

    private static FacadeGame feedDbHp() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_DAM,moveDam(TargetChoice.ANY_FOE));
        HealingHp h_ = Instances.newHealingHp();
        h_.setHp(Rate.one());
        facade_.getData().completeMembers(I_BASE,h_);
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