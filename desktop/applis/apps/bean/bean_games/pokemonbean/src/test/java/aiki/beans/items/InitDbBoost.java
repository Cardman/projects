package aiki.beans.items;

import aiki.beans.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.enums.TargetChoice;
import code.bean.nat.*;
import code.maths.Rate;
import code.util.StringMap;

public abstract class InitDbBoost extends InitDbItem {

    public static String callBoostBeanClickHappiness(NaSt _str, int... _args) {
        return new NaStSt(( (BoostBean) ((PokemonBeanStruct)_str).getInstance()).clickHappiness(_args[0])).getInstance();
    }

    public static String callBoostBeanClickHappinessId(NaSt _str, int... _args) {
        callBoostBeanClickHappiness(_str,_args);
        return getValItemId(_str);
    }

    public static NaSt callBoostBeanEvsGet(NaSt _str, int... _args) {
        return PokemonStandards.getStaByte(( (BoostBean) ((PokemonBeanStruct)_str).getInstance()).getEvs());
    }

    public static NaSt callBoostBeanGetTrEv(NaSt _str, int... _args) {
        return new NaStSt(( (BoostBean) ((PokemonBeanStruct)_str).getInstance()).getTrEv(_args[0]));
    }

    public static NaSt callBoostBeanGetTrHappiness(NaSt _str, int... _args) {
        return new NaStSt(( (BoostBean) ((PokemonBeanStruct)_str).getInstance()).getTrHappiness(_args[0]));
    }

    public static NaSt callBoostBeanHappinessGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrLong(( (BoostBean) ((PokemonBeanStruct)_str).getInstance()).getHappiness());
    }

    public static NaSt callBoostBeanIsBall(NaSt _str, int... _args) {
        return NaBoSt.of(( (BoostBean) ((PokemonBeanStruct)_str).getInstance()).isBall(_args[0]));
    }

    public static NaSt callBoostBeanMaxEvGet(NaSt _str, int... _args) {
        return new NaNbSt(( (BoostBean) ((PokemonBeanStruct)_str).getInstance()).getMaxEv());
    }

    public static NaSt callBoostBeanWinPpGet(NaSt _str, int... _args) {
        return new RtSt(( (BoostBean) ((PokemonBeanStruct)_str).getInstance()).getWinPp());
    }
    protected static NaSt boostDb() {
        return dispLineSample(feedDbItem(), InitDbItems.BEAN_BOOST);
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
