package aiki.beans.items;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.enums.TargetChoice;
import code.expressionlanguage.structs.Struct;
import code.images.BaseSixtyFourUtil;
import code.maths.Rate;
import code.util.StringMap;

public abstract class InitDbHealingPp extends InitDbHealing {

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

    public static StringMap<Struct> beanToHealingPp(PkData _pk) {
        StringMap<Struct> map_ = beanToHealing(_pk);
        map_.addEntry(AikiBeansItemsStd.BEAN_HEALINGPP,_pk.beanHealingPpBean(EN));
        return map_;
    }
    protected static Struct ppDb(boolean _healingMoveFullpp, boolean _healingAllMovesPp, int _healingAllMovesFullpp, int _healedMovePp) {
        PkData pk_ = pkDataByFacade(feedDbPp(_healingMoveFullpp, _healingAllMovesPp, _healingAllMovesFullpp, _healedMovePp));
        StringMap<Struct> all_ = beanToHealingPp(pk_);
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
        facade_.getData().getMiniItems().addEntry(I_BASE, BaseSixtyFourUtil.getImageByString(MAX_RAI));
        facade_.getData().getMiniItems().addEntry(I_BALL, BaseSixtyFourUtil.getImageByString(MAX_RAI2));
        facade_.getData().getMiniItems().addEntry(I_BOOST, BaseSixtyFourUtil.getImageByString(MAX_RAI));
        return facade_;
    }
}
