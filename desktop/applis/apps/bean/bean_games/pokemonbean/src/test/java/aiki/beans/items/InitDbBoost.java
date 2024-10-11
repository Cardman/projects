package aiki.beans.items;

import aiki.beans.BeanPokemonCommonTs;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.enums.TargetChoice;
import code.bean.nat.*;
import code.maths.Rate;
import code.util.StringMap;

public abstract class InitDbBoost extends InitDbItem {

    public static String callBoostBeanClickHappiness(NaSt _str, long... _args) {
        return navigateData(new BoostBeanClickHappiness(),_str,_args);
    }

    public static String callBoostBeanClickHappinessId(NaSt _str, long... _args) {
        callBoostBeanClickHappiness(_str,_args);
        return getValItemId(_str);
    }

    public static NaSt callBoostBeanEvsGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BoostBeanEvsGet(),_str,_args);
    }

    public static NaSt callBoostBeanGetTrEv(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BoostBeanGetTrEv(),_str,_args);
    }

    public static NaSt callBoostBeanGetTrHappiness(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BoostBeanGetTrHappiness(),_str,_args);
    }

    public static NaSt callBoostBeanHappinessGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BoostBeanHappinessGet(),_str,_args);
    }

    public static NaSt callBoostBeanIsBall(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BoostBeanIsBall(),_str,_args);
    }

    public static NaSt callBoostBeanMaxEvGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BoostBeanMaxEvGet(),_str,_args);
    }

    public static NaSt callBoostBeanWinPpGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BoostBeanWinPpGet(),_str,_args);
    }
    protected static NaSt boostDb() {
        return dispLine(feedDbItem(), AikiBeansItemsStd.BEAN_BOOST);
    }

    private static FacadeGame feedDbItem() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_DAM,moveDam(TargetChoice.ANY_FOE));
        facade_.getData().completeMembers(I_BASE,boost());
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
