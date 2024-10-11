package aiki.beans.items;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.db.DataBase;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.items.*;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.*;
import code.util.StringMap;

public abstract class InitDbItemOther extends InitDbItem {


    public static NaSt callBallBeanCatchingRateGet() {
        return BeanPokemonCommonTs.callLongs(new BallBeanCatchingRateGet(),ballBean());
    }

    public static NaSt callBallBeanMapVarsGet() {
        return BeanPokemonCommonTs.callLongs(new BallBeanMapVarsGet(),ballBean());
    }

    public static NaSt callRepelBeanStepsGet() {
        return BeanPokemonCommonTs.callLongs(new RepelBeanStepsGet(),repelBean());
    }

    protected static NaSt sellBean() {
        PkData pk_ = pkDataByFacade(feedDbSell());
        StringMap<NaSt> all_ = beanToSell(pk_);
        return callItemBeanPriceGet(dispLine(AikiBeansItemsStd.BEAN_SELLINGITEM, pk_, all_));
    }
    public static StringMap<NaSt> beanToSell(PkData _pk) {
        StringMap<NaSt> map_ = beanToItem(_pk);
        map_.addEntry(AikiBeansItemsStd.BEAN_SELLINGITEM,_pk.beanSellingItemBean(EN));
        return map_;
    }

    protected static FacadeGame feedDbSell() {
        SellingItem s_ = Instances.newSellingItem();
        s_.setPrice(1);
        return feedDbItem(s_);
    }
    protected static NaSt repelBean() {
        PkData pk_ = pkDataByFacade(feedDbRepel());
        StringMap<NaSt> all_ = beanToRepel(pk_);
        return dispLine(AikiBeansItemsStd.BEAN_REPEL, pk_, all_);
    }
    public static StringMap<NaSt> beanToRepel(PkData _pk) {
        StringMap<NaSt> map_ = beanToItem(_pk);
        map_.addEntry(AikiBeansItemsStd.BEAN_REPEL,_pk.beanRepelBean(EN));
        return map_;
    }

    protected static FacadeGame feedDbRepel() {
        Repel r_ = Instances.newRepel();
        r_.setSteps(1);
        return feedDbItem(r_);
    }
    protected static NaSt ballBean() {
        PkData pk_ = pkDataByFacade(feedDbBall());
        StringMap<NaSt> all_ = beanToBall(pk_);
        return dispLine(AikiBeansItemsStd.BEAN_BALL, pk_, all_);
    }
    public static StringMap<NaSt> beanToBall(PkData _pk) {
        StringMap<NaSt> map_ = beanToItem(_pk);
        map_.addEntry(AikiBeansItemsStd.BEAN_BALL,_pk.beanBallBean(EN));
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
