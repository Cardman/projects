package aiki.beans.items;

import aiki.beans.*;
import aiki.facade.FacadeGame;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.pokemon.evolution.EvolutionItem;
import aiki.fight.pokemon.evolution.EvolutionStoneSimple;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.images.BaseSixtyFourUtil;
import code.util.StringList;
import code.util.StringMap;

public abstract class InitDbItemEvolving extends InitDbItem {

    protected static final String P_POKEMON2="P_POKEMON2";
    protected static final String P_POKEMON2_TR="P_POKEMON2_TR";
    public static String callEvolvingItemBeanClickPokemon() {
        return callEvolvingItemBeanClickPokemon(healEvoItem());
    }

    public static String callEvolvingItemBeanClickPokemon(Struct _str) {
        return navigateData(new EvolvingItemBeanClickPokemon(),_str,0);
    }

    public static String callEvolvingItemBeanClickPokemonId() {
        Struct it_ = healEvoItem();
        callEvolvingItemBeanClickPokemon(it_);
        return getValPkId(it_);
    }

    public static Struct callEvolvingItemBeanGetTrPokemon() {
        return BeanPokemonCommonTs.callLongs(new EvolvingItemBeanGetTrPokemon(),healEvoItem(),0);
    }

    public static Struct callEvolvingItemBeanPokemonGet() {
        return BeanPokemonCommonTs.callLongs(new EvolvingItemBeanPokemonGet(),healEvoItem());
    }

    public static String callEvolvingStoneBeanClickPokemon() {
        return callEvolvingStoneBeanClickPokemon(healEvoStone());
    }

    public static String callEvolvingStoneBeanClickPokemon(Struct _str) {
        return navigateData(new EvolvingStoneBeanClickPokemon(),_str,0);
    }

    public static String callEvolvingStoneBeanClickPokemonId() {
        Struct it_ = healEvoStone();
        callEvolvingStoneBeanClickPokemon(it_);
        return getValPkId(it_);
    }

    public static Struct callEvolvingStoneBeanGetTrPokemon() {
        return BeanPokemonCommonTs.callLongs(new EvolvingStoneBeanGetTrPokemon(),healEvoStone(),0);
    }

    public static Struct callEvolvingStoneBeanPokemonGet() {
        return BeanPokemonCommonTs.callLongs(new EvolvingStoneBeanPokemonGet(),healEvoStone());
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

    public static StringMap<Struct> beanToEvoItem(PkData _pk) {
        StringMap<Struct> map_ = beanToItem(_pk);
        map_.addEntry(AikiBeansItemsStd.BEAN_EVO_ITEM,_pk.beanEvolvingItemBean(EN));
        return map_;
    }

    protected static Struct healEvoItem() {
        PkData pk_ = pkDataByFacade(feedDbEvoItem());
        StringMap<Struct> all_ = beanToEvoItem(pk_);
        return dispLine(AikiBeansItemsStd.BEAN_EVO_ITEM, pk_, all_);
    }

    private static FacadeGame feedDbEvoItem() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(I_BASE,Instances.newEvolvingItem());
        PokemonData pk_ = pk(new StringList("__"), GenderRepartition.NO_GENDER);
        EvolutionItem evo_ = Instances.newEvolutionItem();
        evo_.setItem(I_BASE);
        pk_.getEvolutions().addEntry(P_POKEMON, evo_);
        facade_.getData().completeMembers(P_POKEMON, pk_);
        PokemonData other_ = pk(new StringList("__"), GenderRepartition.NO_GENDER);
        other_.getEvolutions().addEntry(P_POKEMON,Instances.newEvolutionStoneSimple());
        other_.getEvolutions().addEntry(P_POKEMON2,Instances.newEvolutionItem());
        facade_.getData().completeMembers(P_POKEMON2, other_);
        trsEvos(facade_, CI_EVO_ITEM_TR);
        return facade_;
    }

    public static StringMap<Struct> beanToEvoStone(PkData _pk) {
        StringMap<Struct> map_ = beanToItem(_pk);
        map_.addEntry(AikiBeansItemsStd.BEAN_EVO_STONE,_pk.beanEvolvingStoneBean(EN));
        return map_;
    }

    protected static Struct healEvoStone() {
        PkData pk_ = pkDataByFacade(feedDbEvoStone());
        StringMap<Struct> all_ = beanToEvoStone(pk_);
        return dispLine(AikiBeansItemsStd.BEAN_EVO_STONE, pk_, all_);
    }

    private static FacadeGame feedDbEvoStone() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(I_BASE,Instances.newEvolvingStone());
        PokemonData pk_ = pk(new StringList("__"), GenderRepartition.NO_GENDER);
        EvolutionStoneSimple evo_ = Instances.newEvolutionStoneSimple();
        evo_.setStone(I_BASE);
        pk_.getEvolutions().addEntry(P_POKEMON, evo_);
        facade_.getData().completeMembers(P_POKEMON, pk_);
        PokemonData other_ = pk(new StringList("__"), GenderRepartition.NO_GENDER);
        other_.getEvolutions().addEntry(P_POKEMON,Instances.newEvolutionStoneSimple());
        other_.getEvolutions().addEntry(P_POKEMON2,Instances.newEvolutionItem());
        facade_.getData().completeMembers(P_POKEMON2, other_);
        trsEvos(facade_, CI_EVO_STONE_TR);
        return facade_;
    }
    private static void trsEvos(FacadeGame _facade, String _tr) {
        trsCore(_facade);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POKEMON2,P_POKEMON2_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_BASE,I_BASE_TR);
        _facade.getData().getTranslatedClassesDescriptions().addEntry(LANGUAGE,new StringMap<String>());
        _facade.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(_facade.getData().getItem(I_BASE).getItemType(), _tr);
        feedTm(_facade.getData().getTm(), _facade.getData().getTmPrice());
        feedHm(_facade.getData().getHm());
        _facade.getData().completeVariables();
        _facade.getData().getMiniItems().addEntry(I_BASE, BaseSixtyFourUtil.getImageByString(MAX_RAI));
    }
}
