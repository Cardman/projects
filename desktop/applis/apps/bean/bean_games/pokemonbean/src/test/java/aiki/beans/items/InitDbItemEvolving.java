package aiki.beans.items;

import aiki.beans.*;
import aiki.facade.FacadeGame;
import aiki.fight.items.Fossil;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.pokemon.evolution.EvolutionItem;
import aiki.fight.pokemon.evolution.EvolutionStoneSimple;
import aiki.instances.Instances;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import code.util.*;

public abstract class InitDbItemEvolving extends InitDbItem {

    protected static final String P_POKEMON2="P_POKEMON2";
    protected static final String P_POKEMON2_TR="P_POKEMON2_TR";
    public static String callEvolvingItemBeanClickPokemon() {
        return callEvolvingItemBeanClickPokemon(healEvoItem());
    }

    public static String callEvolvingItemBeanClickPokemon(EvolvingItemBean _str) {
        return _str.clickPokemon(0);
    }

    public static String callEvolvingItemBeanClickPokemonId() {
        EvolvingItemBean it_ = healEvoItem();
        callEvolvingItemBeanClickPokemon(it_);
        return getValPkId(it_);
    }

    public static String callEvolvingItemBeanGetTrPokemon() {
        return healEvoItem().getTrPokemon(0);
    }

    public static CustList<TranslatedKey> callEvolvingItemBeanPokemonGet() {
        return healEvoItem().getPokemon();
    }

    public static String callEvolvingStoneBeanClickPokemon() {
        return callEvolvingStoneBeanClickPokemon(healEvoStone());
    }

    public static String callEvolvingStoneBeanClickPokemon(EvolvingStoneBean _str) {
        return _str.clickPokemon(0);
    }

    public static String callEvolvingStoneBeanClickPokemonId() {
        EvolvingStoneBean it_ = healEvoStone();
        callEvolvingStoneBeanClickPokemon(it_);
        return getValPkId(it_);
    }

    public static String callEvolvingStoneBeanGetTrPokemon() {
        return healEvoStone().getTrPokemon(0);
    }

    public static CustList<TranslatedKey> callEvolvingStoneBeanPokemonGet() {
        return healEvoStone().getPokemon();
    }

    public static String callFossilBeanClickPokemon() {
        return callFossilBeanClickPokemon(healFossil());
    }

    public static String callFossilBeanClickPokemon(FossilBean _str) {
        return _str.clickPokemon();
    }

    public static String callFossilBeanClickPokemonId() {
        FossilBean it_ = healFossil();
        callFossilBeanClickPokemon(it_);
        return getValPkId(it_);
    }

    public static String callFossilBeanGetTrPokemon() {
        return healFossil().getTrPokemon();
    }

    public static long callFossilBeanLevelGet() {
        return healFossil().getLevel();
    }

    public static StringMap<BeanRenderWithAppName> beanToEvoItem(PkData _pk) {
        StringMap<BeanRenderWithAppName> map_ = beanToItem(_pk);
        EvolvingItemBean b_ = new EvolvingItemBean();
        initBean(b_,EN,_pk.getDataBase());
        map_.addEntry(InitDbItems.BEAN_EVO_ITEM, b_);
        b_.setBuilder(map_.getValue(0).getBuilder());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_EVOITEM,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_EVOITEM,new TranslationsFile());
        map_.getValue(0).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_EVO_ITEM_HTML,b_);
        return map_;
    }

    protected static EvolvingItemBean healEvoItem() {
        PkData pk_ = pkDataByFacade(feedDbEvoItem());
        StringMap<BeanRenderWithAppName> all_ = beanToEvoItem(pk_);
        return (EvolvingItemBean) dispLineQuick(InitDbItems.BEAN_EVO_ITEM, pk_, all_);
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

    public static StringMap<BeanRenderWithAppName> beanToEvoStone(PkData _pk) {
        StringMap<BeanRenderWithAppName> map_ = beanToItem(_pk);
        EvolvingStoneBean b_ = new EvolvingStoneBean();
        initBean(b_,EN,_pk.getDataBase());
        map_.addEntry(InitDbItems.BEAN_EVO_STONE, b_);
        b_.setBuilder(map_.getValue(0).getBuilder());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_EVOSTONE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_EVOSTONE,new TranslationsFile());
        map_.getValue(0).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_EVO_STONE_HTML,b_);
        return map_;
    }

    protected static EvolvingStoneBean healEvoStone() {
        PkData pk_ = pkDataByFacade(feedDbEvoStone());
        StringMap<BeanRenderWithAppName> all_ = beanToEvoStone(pk_);
        return (EvolvingStoneBean) dispLineQuick(InitDbItems.BEAN_EVO_STONE, pk_, all_);
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

    public static StringMap<BeanRenderWithAppName> beanToFossil(PkData _pk) {
        StringMap<BeanRenderWithAppName> map_ = beanToItem(_pk);
        FossilBean b_ = new FossilBean();
        initBean(b_,EN,_pk.getDataBase());
        map_.addEntry(InitDbItems.BEAN_FOSSIL, b_);
        b_.setBuilder(map_.getValue(0).getBuilder());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_FOSSIL,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_FOSSIL,new TranslationsFile());
        map_.getValue(0).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_FOSSIL_HTML,b_);
        return map_;
    }

    protected static FossilBean healFossil() {
        PkData pk_ = pkDataByFacade(feedDbFossil());
        StringMap<BeanRenderWithAppName> all_ = beanToFossil(pk_);
        return (FossilBean) dispLineQuick(InitDbItems.BEAN_FOSSIL, pk_, all_);
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