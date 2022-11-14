package aiki.beans.map;

import aiki.beans.*;
import aiki.beans.db.InitDbConstr;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbMap extends InitDbConstr {
    public static Struct callMapBeanClickLevel(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapBeanClickLevel(),_str,_args);
    }

    public static Struct callMapBeanClickLevelZero(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapBeanClickLevelZero(),_str,_args);
    }

    public static Struct callMapBeanIsMultiLayer(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapBeanIsMultiLayer(),_str,_args);
    }

    public static Struct callMapBeanLayers(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapBeanLayers(),_str,_args);
    }

    public static Struct callMapBeanPlacesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapBeanPlacesGet(),_str,_args);
    }

}
