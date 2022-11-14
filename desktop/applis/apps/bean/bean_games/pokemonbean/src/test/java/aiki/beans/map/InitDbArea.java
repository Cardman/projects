package aiki.beans.map;

import aiki.beans.AreaApparitionGetAvgNbSteps;
import aiki.beans.AreaApparitionGetWildPokemon;
import aiki.beans.AreaApparitionGetWildPokemonFishing;
import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.map.elements.*;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbArea extends InitDbLevelMap{

    public static Struct callAreaApparitionGetAvgNbSteps(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaApparitionGetAvgNbSteps(),_str,_args);
    }

    public static Struct callAreaApparitionGetWildPokemon(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaApparitionGetWildPokemon(),_str,_args);
    }

    public static Struct callAreaApparitionGetWildPokemonFishing(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaApparitionGetWildPokemonFishing(),_str,_args);
    }

    public static Struct callAreaBeanAreaGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanAreaGet(),_str,_args);
    }

    public static Struct callAreaBeanClickAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanClickAbility(),_str,_args);
    }

    public static Struct callAreaBeanClickAbilityFishing(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanClickAbilityFishing(),_str,_args);
    }

    public static Struct callAreaBeanClickItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanClickItem(),_str,_args);
    }

    public static Struct callAreaBeanClickItemFishing(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanClickItemFishing(),_str,_args);
    }

    public static Struct callAreaBeanClickMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanClickMove(),_str,_args);
    }

    public static Struct callAreaBeanClickMoveFishing(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanClickMoveFishing(),_str,_args);
    }

    public static Struct callAreaBeanClickName(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanClickName(),_str,_args);
    }

    public static Struct callAreaBeanClickNameFishing(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanClickNameFishing(),_str,_args);
    }

    public static Struct callAreaBeanGetAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetAbility(),_str,_args);
    }

    public static Struct callAreaBeanGetAbilityFishing(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetAbilityFishing(),_str,_args);
    }

    public static Struct callAreaBeanGetGender(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetGender(),_str,_args);
    }

    public static Struct callAreaBeanGetImage(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetImage(),_str,_args);
    }

    public static Struct callAreaBeanGetImageFishing(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetImageFishing(),_str,_args);
    }

    public static Struct callAreaBeanGetItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetItem(),_str,_args);
    }

    public static Struct callAreaBeanGetItemFishing(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetItemFishing(),_str,_args);
    }

    public static Struct callAreaBeanGetMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetMove(),_str,_args);
    }

    public static Struct callAreaBeanGetMoveFishing(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetMoveFishing(),_str,_args);
    }

    public static Struct callAreaBeanGetMovesAtLevel(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetMovesAtLevel(),_str,_args);
    }

    public static Struct callAreaBeanGetMovesAtLevelFishing(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetMovesAtLevelFishing(),_str,_args);
    }

    public static Struct callAreaBeanGetName(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetName(),_str,_args);
    }

    public static Struct callAreaBeanGetNameFishing(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetNameFishing(),_str,_args);
    }

}
