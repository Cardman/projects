package aiki.beans.items;

import aiki.beans.BeanPokemonCommonTs;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbItem extends InitDbItems{

    public static Struct callItemBeanClickItems(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemBeanClickItems(),_str,_args);
    }

    public static Struct callItemBeanDescriptionGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemBeanDescriptionGet(),_str,_args);
    }

    public static Struct callItemBeanDisplayNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemBeanDisplayNameGet(),_str,_args);
    }

    public static Struct callItemBeanItemBeanGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemBeanItemBeanGet(),_str,_args);
    }

    public static Struct callItemBeanItemImageGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemBeanItemImageGet(),_str,_args);
    }

    public static Struct callItemBeanNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemBeanNameGet(),_str,_args);
    }

    public static Struct callItemBeanPriceGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemBeanPriceGet(),_str,_args);
    }
    public static Struct callItemBeanNameSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new ItemBeanNameSet(),_str,_args);
    }
}
