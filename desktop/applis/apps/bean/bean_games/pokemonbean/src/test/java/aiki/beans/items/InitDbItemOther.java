package aiki.beans.items;

import aiki.beans.BeanPokemonCommonTs;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.enums.TargetChoice;
import code.expressionlanguage.structs.Struct;
import code.images.BaseSixtyFourUtil;
import code.maths.Rate;
import code.util.*;

public abstract class InitDbItemOther extends InitDbItem {


    public static Struct callBallBeanCatchingRateGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BallBeanCatchingRateGet(),_str,_args);
    }

    public static Struct callBallBeanMapVarsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BallBeanMapVarsGet(),_str,_args);
    }

    public static String callBoostBeanClickHappiness(Struct _str, long... _args) {
        return navigateData(new BoostBeanClickHappiness(),_str,_args);
    }

    public static String callBoostBeanClickHappinessId(Struct _str, long... _args) {
        callBoostBeanClickHappiness(_str,_args);
        return getValItemId(_str);
    }

    public static Struct callBoostBeanEvsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BoostBeanEvsGet(),_str,_args);
    }

    public static Struct callBoostBeanGetTrEv(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BoostBeanGetTrEv(),_str,_args);
    }

    public static Struct callBoostBeanGetTrHappiness(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BoostBeanGetTrHappiness(),_str,_args);
    }

    public static Struct callBoostBeanHappinessGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BoostBeanHappinessGet(),_str,_args);
    }

    public static Struct callBoostBeanIsBall(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BoostBeanIsBall(),_str,_args);
    }

    public static Struct callBoostBeanMaxEvGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BoostBeanMaxEvGet(),_str,_args);
    }

    public static Struct callBoostBeanWinPpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BoostBeanWinPpGet(),_str,_args);
    }

    public static Struct callEvolvingItemBeanClickPokemon(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolvingItemBeanClickPokemon(),_str,_args);
    }

    public static Struct callEvolvingItemBeanGetTrPokemon(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolvingItemBeanGetTrPokemon(),_str,_args);
    }

    public static Struct callEvolvingItemBeanPokemonGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolvingItemBeanPokemonGet(),_str,_args);
    }

    public static Struct callEvolvingStoneBeanClickPokemon(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolvingStoneBeanClickPokemon(),_str,_args);
    }

    public static Struct callEvolvingStoneBeanGetTrPokemon(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolvingStoneBeanGetTrPokemon(),_str,_args);
    }

    public static Struct callEvolvingStoneBeanPokemonGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolvingStoneBeanPokemonGet(),_str,_args);
    }

    public static Struct callFossilBeanClickPokemon(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FossilBeanClickPokemon(),_str,_args);
    }

    public static Struct callFossilBeanGetTrPokemon(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FossilBeanGetTrPokemon(),_str,_args);
    }

    public static Struct callFossilBeanLevelGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new FossilBeanLevelGet(),_str,_args);
    }

    public static Struct callHealingHpBeanHpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingHpBeanHpGet(),_str,_args);
    }

    public static Struct callHealingItemBeanClickHappiness(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingItemBeanClickHappiness(),_str,_args);
    }

    public static Struct callHealingItemBeanGetTrHappiness(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingItemBeanGetTrHappiness(),_str,_args);
    }

    public static Struct callHealingItemBeanHappinessGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingItemBeanHappinessGet(),_str,_args);
    }

    public static Struct callHealingItemBeanHealingItemBeanGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingItemBeanHealingItemBeanGet(),_str,_args);
    }

    public static Struct callHealingItemBeanHealingTeamGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingItemBeanHealingTeamGet(),_str,_args);
    }

    public static Struct callHealingItemBeanIsBall(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingItemBeanIsBall(),_str,_args);
    }

    public static Struct callHealingPpBeanHealedMovePpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingPpBeanHealedMovePpGet(),_str,_args);
    }

    public static Struct callHealingPpBeanHealingAllMovesFullppGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingPpBeanHealingAllMovesFullppGet(),_str,_args);
    }

    public static Struct callHealingPpBeanHealingAllMovesPpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingPpBeanHealingAllMovesPpGet(),_str,_args);
    }

    public static Struct callHealingPpBeanHealingMoveFullppGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingPpBeanHealingMoveFullppGet(),_str,_args);
    }

    public static Struct callHealingPpBeanLimitedPpMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingPpBeanLimitedPpMove(),_str,_args);
    }

    public static Struct callHealingPpBeanLimitedPpMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingPpBeanLimitedPpMoves(),_str,_args);
    }

    public static Struct callHealingStatusBeanClickStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingStatusBeanClickStatus(),_str,_args);
    }

    public static Struct callHealingStatusBeanGetTrStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingStatusBeanGetTrStatus(),_str,_args);
    }

    public static Struct callHealingStatusBeanHealedHpRateGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingStatusBeanHealedHpRateGet(),_str,_args);
    }

    public static Struct callHealingStatusBeanHealingKoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingStatusBeanHealingKoGet(),_str,_args);
    }

    public static Struct callHealingStatusBeanHealingStatusBeanGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingStatusBeanHealingStatusBeanGet(),_str,_args);
    }

    public static Struct callHealingStatusBeanStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingStatusBeanStatusGet(),_str,_args);
    }



    public static Struct callRepelBeanStepsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new RepelBeanStepsGet(),_str,_args);
    }
    protected static Struct boostDb() {
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
        facade_.getData().getMiniItems().addEntry(I_BASE, BaseSixtyFourUtil.getImageByString(MAX_RAI));
        facade_.getData().getMiniItems().addEntry(I_BALL, BaseSixtyFourUtil.getImageByString(MAX_RAI2));
        facade_.getData().getMiniItems().addEntry(I_BOOST, BaseSixtyFourUtil.getImageByString(MAX_RAI));
        return facade_;
    }
}
