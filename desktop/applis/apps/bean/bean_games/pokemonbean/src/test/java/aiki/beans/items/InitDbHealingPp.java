package aiki.beans.items;

import aiki.beans.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.enums.TargetChoice;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import code.util.StringMap;

public abstract class InitDbHealingPp extends InitDbHealing {

    public static long callHealingPpBeanHealedMovePpGet(NaSt _str, int... _args) {
        return ( (HealingPpBean) ((PokemonBeanStruct)_str).getInstance()).getHealedMovePp();
    }

    public static long callHealingPpBeanHealingAllMovesFullppGet(NaSt _str, int... _args) {
        return ( (HealingPpBean) ((PokemonBeanStruct)_str).getInstance()).getHealingAllMovesFullpp();
    }

    public static boolean callHealingPpBeanHealingAllMovesPpGet(NaSt _str, int... _args) {
        return ( (HealingPpBean) ((PokemonBeanStruct)_str).getInstance()).getHealingAllMovesPp();
    }

    public static boolean callHealingPpBeanHealingMoveFullppGet(NaSt _str, int... _args) {
        return ( (HealingPpBean) ((PokemonBeanStruct)_str).getInstance()).getHealingMoveFullpp();
    }

    public static boolean callHealingPpBeanLimitedPpMove(NaSt _str, int... _args) {
        return ( (HealingPpBean) ((PokemonBeanStruct)_str).getInstance()).limitedPpMove();
    }

    public static boolean callHealingPpBeanLimitedPpMoves(NaSt _str, int... _args) {
        return ( (HealingPpBean) ((PokemonBeanStruct)_str).getInstance()).limitedPpMoves();
    }

    public static StringMap<NaSt> beanToHealingPp(PkData _pk) {
        StringMap<NaSt> map_ = beanToHealing(_pk);
        HealingPpBean s_ = new HealingPpBean();
        map_.addEntry(InitDbItems.BEAN_HEALINGPP, _pk.bean(s_, EN));
        s_.setBuilder(((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_HEALINGPP,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_HEALINGPP,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_HEALINGPP_HTML,s_);
        return map_;
    }
    protected static NaSt ppDb(boolean _healingMoveFullpp, boolean _healingAllMovesPp, int _healingAllMovesFullpp, int _healedMovePp) {
        PkData pk_ = pkDataByFacade(feedDbPp(_healingMoveFullpp, _healingAllMovesPp, _healingAllMovesFullpp, _healedMovePp));
        StringMap<NaSt> all_ = beanToHealingPp(pk_);
        return dispLineClick(InitDbItems.BEAN_HEALINGPP, pk_, all_);
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