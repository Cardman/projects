package aiki.beans.items;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.items.*;
import aiki.game.fight.Fight;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.images.BaseSixtyFourUtil;
import code.maths.*;
import code.util.StringMap;

public abstract class InitDbItemOther extends InitDbItem {


    public static Struct callBallBeanCatchingRateGet() {
        return BeanPokemonCommonTs.callLongs(new BallBeanCatchingRateGet(),ballBean());
    }

    public static Struct callBallBeanMapVarsGet() {
        return BeanPokemonCommonTs.callLongs(new BallBeanMapVarsGet(),ballBean());
    }

    public static Struct callRepelBeanStepsGet() {
        return BeanPokemonCommonTs.callLongs(new RepelBeanStepsGet(),repelBean());
    }

    protected static Struct sellBean() {
        PkData pk_ = pkDataByFacade(feedDbSell());
        StringMap<Struct> all_ = beanToSell(pk_);
        return callItemBeanPriceGet(dispLine(AikiBeansItemsStd.BEAN_SELLINGITEM, pk_, all_));
    }
    public static StringMap<Struct> beanToSell(PkData _pk) {
        StringMap<Struct> map_ = beanToItem(_pk);
        map_.addEntry(AikiBeansItemsStd.BEAN_SELLINGITEM,_pk.beanSellingItemBean(EN));
        return map_;
    }

    protected static FacadeGame feedDbSell() {
        SellingItem s_ = Instances.newSellingItem();
        s_.setPrice(1);
        return feedDbItem(s_);
    }
    protected static Struct repelBean() {
        PkData pk_ = pkDataByFacade(feedDbRepel());
        StringMap<Struct> all_ = beanToRepel(pk_);
        return dispLine(AikiBeansItemsStd.BEAN_REPEL, pk_, all_);
    }
    public static StringMap<Struct> beanToRepel(PkData _pk) {
        StringMap<Struct> map_ = beanToItem(_pk);
        map_.addEntry(AikiBeansItemsStd.BEAN_REPEL,_pk.beanRepelBean(EN));
        return map_;
    }

    protected static FacadeGame feedDbRepel() {
        Repel r_ = Instances.newRepel();
        r_.setSteps(1);
        return feedDbItem(r_);
    }
    protected static Struct ballBean() {
        PkData pk_ = pkDataByFacade(feedDbBall());
        StringMap<Struct> all_ = beanToBall(pk_);
        return dispLine(AikiBeansItemsStd.BEAN_BALL, pk_, all_);
    }
    public static StringMap<Struct> beanToBall(PkData _pk) {
        StringMap<Struct> map_ = beanToItem(_pk);
        map_.addEntry(AikiBeansItemsStd.BEAN_BALL,_pk.beanBallBean(EN));
        return map_;
    }

    protected static FacadeGame feedDbBall() {
        return feedDbItem(ball(DataBase.VAR_PREFIX+Fight.TEMPS_TOUR));
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
        _facade.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
        _facade.getData().getMiniItems().addEntry(I_BASE, BaseSixtyFourUtil.getImageByString(MAX_RAI));
        _facade.getData().addConstNumTest(DataBase.VALEUR_DEF_STATIS,Rate.one());
    }
}
