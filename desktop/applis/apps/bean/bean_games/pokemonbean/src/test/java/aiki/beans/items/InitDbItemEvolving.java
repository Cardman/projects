package aiki.beans.items;

import aiki.beans.*;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbItemEvolving extends InitDbItem {

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

}
