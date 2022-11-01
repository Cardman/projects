package aiki.beans.status;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.db.InitDbConstr;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbStatusSet extends InitDbConstr {

    public static Struct callStatusSetBeanClickStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusSetBeanClickStatus(),_str,_args);
    }

    public static Struct callStatusSetBeanGetTrStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusSetBeanGetTrStatus(),_str,_args);
    }

    public static Struct callStatusSetBeanSearch(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusSetBeanSearch(),_str,_args);
    }

    public static Struct callStatusSetBeanSortedStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusSetBeanSortedStatusGet(),_str,_args);
    }

    public static Struct callStatusSetBeanTypedStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusSetBeanTypedStatusGet(),_str,_args);
    }

    public static Struct callStatusSetBeanTypedStatusSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new StatusSetBeanTypedStatusSet(),_str,_args);
    }
}
