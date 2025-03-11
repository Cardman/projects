package aiki.beans.items;

import aiki.beans.*;
import aiki.facade.FacadeGame;
import aiki.fight.items.Fossil;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.pokemon.evolution.EvolutionItem;
import aiki.fight.pokemon.evolution.EvolutionStoneSimple;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import code.util.StringList;
import code.util.StringMap;

public abstract class InitDbItemEvolving extends InitDbItem {

    protected static final String P_POKEMON2="P_POKEMON2";
    protected static final String P_POKEMON2_TR="P_POKEMON2_TR";
    public static String callEvolvingItemBeanClickPokemon() {
        return callEvolvingItemBeanClickPokemon(healEvoItem());
    }

    public static String callEvolvingItemBeanClickPokemon(NaSt _str) {
        return ( (EvolvingItemBean) ((PokemonBeanStruct)_str).getInstance()).clickPokemon(0);
    }

    public static String callEvolvingItemBeanClickPokemonId() {
        NaSt it_ = healEvoItem();
        callEvolvingItemBeanClickPokemon(it_);
        return getValPkId(it_);
    }

    public static String callEvolvingItemBeanGetTrPokemon() {
        return ( (EvolvingItemBean) ((PokemonBeanStruct)healEvoItem()).getInstance()).getTrPokemon(0);
    }

    public static NaSt callEvolvingItemBeanPokemonGet() {
        return PokemonStandards.getKeys(( (EvolvingItemBean) ((PokemonBeanStruct)healEvoItem()).getInstance()).getPokemon());
    }

    public static String callEvolvingStoneBeanClickPokemon() {
        return callEvolvingStoneBeanClickPokemon(healEvoStone());
    }

    public static String callEvolvingStoneBeanClickPokemon(NaSt _str) {
        return ( (EvolvingStoneBean) ((PokemonBeanStruct)_str).getInstance()).clickPokemon(0);
    }

    public static String callEvolvingStoneBeanClickPokemonId() {
        NaSt it_ = healEvoStone();
        callEvolvingStoneBeanClickPokemon(it_);
        return getValPkId(it_);
    }

    public static String callEvolvingStoneBeanGetTrPokemon() {
        return ( (EvolvingStoneBean) ((PokemonBeanStruct)healEvoStone()).getInstance()).getTrPokemon(0);
    }

    public static NaSt callEvolvingStoneBeanPokemonGet() {
        return PokemonStandards.getKeys(( (EvolvingStoneBean) ((PokemonBeanStruct)healEvoStone()).getInstance()).getPokemon());
    }

    public static String callFossilBeanClickPokemon() {
        return callFossilBeanClickPokemon(healFossil());
    }

    public static String callFossilBeanClickPokemon(NaSt _str) {
        return ( (FossilBean) ((PokemonBeanStruct)_str).getInstance()).clickPokemon();
    }

    public static String callFossilBeanClickPokemonId() {
        NaSt it_ = healFossil();
        callFossilBeanClickPokemon(it_);
        return getValPkId(it_);
    }

    public static String callFossilBeanGetTrPokemon() {
        return ( (FossilBean) ((PokemonBeanStruct)healFossil()).getInstance()).getTrPokemon();
    }

    public static long callFossilBeanLevelGet() {
        return ( (FossilBean) ((PokemonBeanStruct)healFossil()).getInstance()).getLevel();
    }

    public static StringMap<NaSt> beanToEvoItem(PkData _pk) {
        StringMap<NaSt> map_ = beanToItem(_pk);
        EvolvingItemBean b_ = new EvolvingItemBean();
        map_.addEntry(InitDbItems.BEAN_EVO_ITEM, _pk.bean(b_, EN));
        b_.setBuilder(((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_EVOITEM,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_EVOITEM,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_EVO_ITEM_HTML,b_);
        return map_;
    }

    protected static NaSt healEvoItem() {
        PkData pk_ = pkDataByFacade(feedDbEvoItem());
        StringMap<NaSt> all_ = beanToEvoItem(pk_);
        return dispLineQuick(InitDbItems.BEAN_EVO_ITEM, pk_, all_);
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

    public static StringMap<NaSt> beanToEvoStone(PkData _pk) {
        StringMap<NaSt> map_ = beanToItem(_pk);
        EvolvingStoneBean b_ = new EvolvingStoneBean();
        map_.addEntry(InitDbItems.BEAN_EVO_STONE, _pk.bean(b_, EN));
        b_.setBuilder(((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_EVOSTONE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_EVOSTONE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_EVO_STONE_HTML,b_);
        return map_;
    }

    protected static NaSt healEvoStone() {
        PkData pk_ = pkDataByFacade(feedDbEvoStone());
        StringMap<NaSt> all_ = beanToEvoStone(pk_);
        return dispLineQuick(InitDbItems.BEAN_EVO_STONE, pk_, all_);
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

    public static StringMap<NaSt> beanToFossil(PkData _pk) {
        StringMap<NaSt> map_ = beanToItem(_pk);
        FossilBean b_ = new FossilBean();
        map_.addEntry(InitDbItems.BEAN_FOSSIL, _pk.bean(b_, EN));
        b_.setBuilder(((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_FOSSIL,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_FOSSIL,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_FOSSIL_HTML,b_);
        return map_;
    }

    protected static NaSt healFossil() {
        PkData pk_ = pkDataByFacade(feedDbFossil());
        StringMap<NaSt> all_ = beanToFossil(pk_);
        return dispLineQuick(InitDbItems.BEAN_FOSSIL, pk_, all_);
    }

    private static FacadeGame feedDbFossil() {
        FacadeGame facade_ = facade();
        Fossil f_ = Instances.newFossil();
        f_.setPokemon(P_POKEMON);
        f_.setLevel( 1);
        facade_.getData().completeMembers(I_BASE, f_);
        facade_.getData().completeMembers(P_POKEMON, pk(new StringList("__"), GenderRepartition.NO_GENDER));
        trsEvos(facade_, CI_FOSSIL_TR);
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
        _facade.getData().getMiniItems().addEntry(I_BASE, instance(IMG_MAX_RAI));
    }
}