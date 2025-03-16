package aiki.beans.items;

import aiki.beans.*;
import aiki.db.DataBase;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.items.*;
import aiki.instances.Instances;
import code.maths.*;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import code.util.*;

public abstract class InitDbItemOther extends InitDbItem {


    public static String callBallBeanCatchingRateGet() {
        return ballBean().getCatchingRate();
    }

    public static AbsMap<String,String> callBallBeanMapVarsGet() {
        return ballBean().getMapVars();
    }

    public static long callRepelBeanStepsGet() {
        return repelBean().getSteps();
    }

    protected static long sellBean() {
        PkData pk_ = pkDataByFacade(feedDbSell());
        StringMap<BeanRenderWithAppName> all_ = beanToSell(pk_);
        return callItemBeanPriceGet(dispLineQuick(InitDbItems.BEAN_SELLINGITEM, pk_, all_));
    }
    public static StringMap<BeanRenderWithAppName> beanToSell(PkData _pk) {
        StringMap<BeanRenderWithAppName> map_ = beanToItem(_pk);
        SellingItemBean b_ = new SellingItemBean();
        initBean(b_,EN,_pk.getDataBase());
        map_.addEntry(InitDbItems.BEAN_SELLINGITEM, b_);
        map_.getValue(0).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_SELLINGITEM_HTML,b_);
        return map_;
    }

    protected static FacadeGame feedDbSell() {
        SellingItem s_ = Instances.newSellingItem();
        s_.setPrice(1);
        return feedDbItem(s_);
    }
    protected static RepelBean repelBean() {
        PkData pk_ = pkDataByFacade(feedDbRepel());
        StringMap<BeanRenderWithAppName> all_ = beanToRepel(pk_);
        return (RepelBean) dispLineQuick(InitDbItems.BEAN_REPEL, pk_, all_);
    }
    public static StringMap<BeanRenderWithAppName> beanToRepel(PkData _pk) {
        StringMap<BeanRenderWithAppName> map_ = beanToItem(_pk);
        RepelBean b_ = new RepelBean();
        initBean(b_,EN,_pk.getDataBase());
        map_.addEntry(InitDbItems.BEAN_REPEL, b_);
        b_.setBuilder(map_.getValue(0).getBuilder());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_REPEL,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_REPEL,new TranslationsFile());
        map_.getValue(0).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_REPEL_HTML,b_);
        return map_;
    }

    protected static FacadeGame feedDbRepel() {
        Repel r_ = Instances.newRepel();
        r_.setSteps(1);
        return feedDbItem(r_);
    }
    protected static BallBean ballBean() {
        PkData pk_ = pkDataByFacade(feedDbBall());
        StringMap<BeanRenderWithAppName> all_ = beanToBall(pk_);
        return (BallBean) dispLineQuick(InitDbItems.BEAN_BALL, pk_, all_);
    }
    public static StringMap<BeanRenderWithAppName> beanToBall(PkData _pk) {
        StringMap<BeanRenderWithAppName> map_ = beanToItem(_pk);
        BallBean b_ = new BallBean();
        initBean(b_,EN,_pk.getDataBase());
        map_.addEntry(InitDbItems.BEAN_BALL, b_);
        b_.setBuilder(map_.getValue(0).getBuilder());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_BALL,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_BALL,new TranslationsFile());
        map_.getValue(0).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_BALL_HTML,b_);
        return map_;
    }

    protected static FacadeGame feedDbBall() {
        return feedDbItem(ball(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR));
    }
    protected static FacadeGame feedDbItem(Item _item) {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(I_BASE, _item);
        otherElts(facade_);
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void otherElts(FacadeGame _facade) {
        trsCore(_facade);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_BASE,I_BASE_TR);
        _facade.getData().getTranslatedClassesDescriptions().addEntry(LANGUAGE,new StringMap<String>());
        _facade.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(_facade.getData().getItem(I_BASE).getItemType(),CI_ITEMBATTLE_TR);
        _facade.getData().getLitterals().addEntry(EN,new StringMap<String>());
        _facade.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        _facade.getData().getMiniItems().addEntry(I_BASE, instance(IMG_MAX_RAI));
        _facade.getData().addConstNumTest(DataBase.VALEUR_DEF_STATIS,Rate.one());
    }
}