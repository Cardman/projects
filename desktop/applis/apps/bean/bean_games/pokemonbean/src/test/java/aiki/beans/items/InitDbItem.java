package aiki.beans.items;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.facade.FacadeGame;
import aiki.fight.items.HealingItem;
import aiki.fight.items.HealingPp;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.util.StringList;
import code.util.StringMap;

public abstract class InitDbItem extends InitDbItems{
    public static final String I_BASE_TR = "B_BASE_TR";
    public static final String I_BASE = "B_BASE";

    public static String callItemBeanClickItems(NaSt _str, long... _args) {
        return navigateData(new ItemBeanClickItems(),_str,_args);
    }

    public static NaSt callItemBeanDescriptionGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemBeanDescriptionGet(),_str,_args);
    }

    public static NaSt callItemBeanDisplayNameGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemBeanDisplayNameGet(),_str,_args);
    }

    public static NaSt callItemBeanItemBeanGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemBeanItemBeanGet(),_str,_args);
    }

    public static NaSt callItemBeanItemImageGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemBeanItemImageGet(),_str,_args);
    }

    public static NaSt callItemBeanNameGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemBeanNameGet(),_str,_args);
    }

    public static NaSt callItemBeanPriceGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemBeanPriceGet(),_str,_args);
    }
    public static NaSt callItemBeanNameSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new ItemBeanNameSet(),_str,_args);
    }
    public static StringMap<NaSt> beanToItemSample(PkData _pk) {
        StringMap<NaSt> map_ = beanToItem(_pk);
        map_.addEntry(AikiBeansItemsStd.BEAN_BOOST,_pk.beanBoostBean(EN));
        return map_;
    }
    protected static NaSt itemLineSample() {
        return dispLine(feedDbItem(), AikiBeansItemsStd.BEAN_BOOST);
    }

    protected static NaSt dispLine(FacadeGame _fac, String _key) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToItemSample(pk_);
        return dispLine(_key, pk_, all_);
    }

    protected static NaSt dispLine(String _key, PkData _pk, StringMap<NaSt> _all) {
        NaSt res_ = transitToAllItems(_pk, _all, _key);
        callItemBeanItemBeanGet(res_);
        callItemBeanNameSet(_all.getVal(AikiBeansItemsStd.BEAN_ITEM),toStr(callItemBeanNameGet(res_)));
        return res_;
    }

    private static FacadeGame feedDbItem() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_DAM,moveDam(TargetChoice.ANY_FOE));
        facade_.getData().completeMembers(I_BASE,boost());
        facade_.getData().completeMembers(I_BALL,ball());
        facade_.getData().completeMembers(I_BOOST,boost());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trsCore(facade_);
        facade_.getData().getTranslatedItems().getVal(EN).addEntry(I_BASE,I_BASE_TR);
        facade_.getData().getTranslatedClassesDescriptions().addEntry(LANGUAGE,new StringMap<String>());
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_BASE).getItemType(),CI_BOOST_TR);
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_BALL).getItemType(),CI_BALL_TR);
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_BOOST).getItemType(),CI_BOOST_TR);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        facade_.getData().getMiniItems().addEntry(I_BASE, instance(IMG_MAX_RAI));
        facade_.getData().getMiniItems().addEntry(I_BALL, instance(IMG_MAX_RAI2));
        facade_.getData().getMiniItems().addEntry(I_BOOST, instance(IMG_MAX_RAI));
        return facade_;
    }

    protected static HealingPp ppItem(boolean _healingMoveFullpp, boolean _healingAllMovesPp, int _healingAllMovesFullpp, int _healedMovePp) {
        HealingPp h_ = Instances.newHealingPp();
        h_.setHealingMoveFullpp(_healingMoveFullpp);
        h_.setHealingAllMovesPp(_healingAllMovesPp);
        h_.setHealingAllMovesFullpp(_healingAllMovesFullpp);
        h_.setHealedMovePp(_healedMovePp);
        h_.getHappiness().addEntry(I_BALL,1L);
        h_.getHappiness().addEntry(I_BOOST,2L);
        return h_;
    }

    protected static HealingItem healItem(boolean _healingMoveFullpp) {
        HealingItem h_ = Instances.newHealingSimpleItem();
        h_.setHealingTeam(_healingMoveFullpp);
        return h_;
    }
}
