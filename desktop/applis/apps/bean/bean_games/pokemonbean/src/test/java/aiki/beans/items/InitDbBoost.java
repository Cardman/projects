package aiki.beans.items;

import aiki.beans.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.enums.TargetChoice;
import aiki.comparators.*;
import code.maths.*;
import code.util.*;

public abstract class InitDbBoost extends InitDbItem {

    public static String callBoostBeanClickHappiness(BoostBean _str, int... _args) {
        return _str.clickHappiness(_args[0]);
    }

    public static String callBoostBeanClickHappinessId(BoostBean _str, int... _args) {
        callBoostBeanClickHappiness(_str,_args);
        return getValItemId(_str);
    }

    public static DictionaryComparator<TranslatedKey,Long> callBoostBeanEvsGet(BoostBean _str, int... _args) {
        return _str.getEvs();
    }

    public static String callBoostBeanGetTrEv(BoostBean _str, int... _args) {
        return _str.getTrEv(_args[0]);
    }

    public static String callBoostBeanGetTrHappiness(BoostBean _str, int... _args) {
        return _str.getTrHappiness(_args[0]);
    }

    public static DictionaryComparator<TranslatedKey,Long> callBoostBeanHappinessGet(BoostBean _str, int... _args) {
        return _str.getHappiness();
    }

    public static boolean callBoostBeanIsBall(BoostBean _str, int... _args) {
        return _str.isBall(_args[0]);
    }

    public static long callBoostBeanMaxEvGet(BoostBean _str, int... _args) {
        return _str.getMaxEv();
    }

    public static Rate callBoostBeanWinPpGet(BoostBean _str, int... _args) {
        return _str.getWinPp();
    }
    protected static BoostBean boostDb() {
        return (BoostBean) dispLineSample(feedDbItem(), InitDbItems.BEAN_BOOST);
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
