package aiki.beans.items;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.db.InitDbConstr;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbItems extends InitDbConstr {

    public static Struct callItemsBeanClickLink(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemsBeanClickLink(),_str,_args);
    }

    public static Struct callItemsBeanGetMiniImage(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemsBeanGetMiniImage(),_str,_args);
    }

    public static Struct callItemsBeanItemsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemsBeanItemsGet(),_str,_args);
    }

    public static Struct callItemsBeanSearch(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemsBeanSearch(),_str,_args);
    }

    public static Struct callItemsBeanTypedClassGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemsBeanTypedClassGet(),_str,_args);
    }

    public static Struct callItemsBeanTypedNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemsBeanTypedNameGet(),_str,_args);
    }

    public static Struct callItemsBeanTypedPriceGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemsBeanTypedPriceGet(),_str,_args);
    }
    public static Struct callItemsBeanTypedClassSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new ItemsBeanTypedClassSet(),_str,_args);
    }

    public static Struct callItemsBeanTypedNameSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new ItemsBeanTypedNameSet(),_str,_args);
    }

    public static Struct callItemsBeanTypedPriceSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new ItemsBeanTypedPriceSet(),_str,_args);
    }
    public static void fwdEffectWhileSendingWithStatistic(Struct _update, Struct _use) {
        callEffectWhileSendingBeanEffectSet(_update,callItemForBattleBeanGetEffectSending(_use));
    }
    public static Struct callItemForBattleBeanGetEffectSending(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetEffectSending(),_str,_args);
    }
}
