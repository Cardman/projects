package aiki.beans.items;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.enums.TargetChoice;
import code.bean.nat.*;
import code.maths.Rate;
import code.util.StringMap;

public abstract class InitDbHealingPp extends InitDbHealing {

    public static NaSt callHealingPpBeanHealedMovePpGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingPpBeanHealedMovePpGet(),_str,_args);
    }

    public static NaSt callHealingPpBeanHealingAllMovesFullppGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingPpBeanHealingAllMovesFullppGet(),_str,_args);
    }

    public static NaSt callHealingPpBeanHealingAllMovesPpGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingPpBeanHealingAllMovesPpGet(),_str,_args);
    }

    public static NaSt callHealingPpBeanHealingMoveFullppGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingPpBeanHealingMoveFullppGet(),_str,_args);
    }

    public static NaSt callHealingPpBeanLimitedPpMove(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingPpBeanLimitedPpMove(),_str,_args);
    }

    public static NaSt callHealingPpBeanLimitedPpMoves(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingPpBeanLimitedPpMoves(),_str,_args);
    }

    public static StringMap<NaSt> beanToHealingPp(PkData _pk) {
        StringMap<NaSt> map_ = beanToHealing(_pk);
        map_.addEntry(AikiBeansItemsStd.BEAN_HEALINGPP,_pk.beanHealingPpBean(EN));
        return map_;
    }
    protected static NaSt ppDb(boolean _healingMoveFullpp, boolean _healingAllMovesPp, int _healingAllMovesFullpp, int _healedMovePp) {
        PkData pk_ = pkDataByFacade(feedDbPp(_healingMoveFullpp, _healingAllMovesPp, _healingAllMovesFullpp, _healedMovePp));
        StringMap<NaSt> all_ = beanToHealingPp(pk_);
        return dispLine(AikiBeansItemsStd.BEAN_HEALINGPP, pk_, all_);
    }

    private static FacadeGame feedDbPp(boolean _healingMoveFullpp, boolean _healingAllMovesPp, int _healingAllMovesFullpp, int _healedMovePp) {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_DAM,moveDam(TargetChoice.ANY_FOE));
        facade_.getData().completeMembers(I_BASE,ppItem(_healingMoveFullpp, _healingAllMovesPp, _healingAllMovesFullpp, _healedMovePp));
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
