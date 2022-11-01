package aiki.beans.pokemon;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.db.InitDbConstr;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbPk extends InitDbConstr {

    public static Struct callPokedexBeanBooleansGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanBooleansGet(),_str,_args);
    }

    public static Struct callPokedexBeanClickLink(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanClickLink(),_str,_args);
    }

    public static Struct callPokedexBeanGetMiniImage(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanGetMiniImage(),_str,_args);
    }

    public static Struct callPokedexBeanIsEvoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanIsEvoGet(),_str,_args);
    }

    public static Struct callPokedexBeanIsLegGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanIsLegGet(),_str,_args);
    }

    public static Struct callPokedexBeanPokedexGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanPokedexGet(),_str,_args);
    }

    public static Struct callPokedexBeanSearch(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanSearch(),_str,_args);
    }

    public static Struct callPokedexBeanTypedMaxNbPossEvosGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanTypedMaxNbPossEvosGet(),_str,_args);
    }

    public static Struct callPokedexBeanTypedMinNbPossEvosGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanTypedMinNbPossEvosGet(),_str,_args);
    }

    public static Struct callPokedexBeanTypedNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanTypedNameGet(),_str,_args);
    }

    public static Struct callPokedexBeanTypedTypeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanTypedTypeGet(),_str,_args);
    }

    public static Struct callPokedexBeanWholeWordGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokedexBeanWholeWordGet(),_str,_args);
    }

    public static Struct callPokedexBeanIsEvoSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanIsEvoSet(),_str,_args);
    }

    public static Struct callPokedexBeanIsLegSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanIsLegSet(),_str,_args);
    }

    public static Struct callPokedexBeanTypedMaxNbPossEvosSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanTypedMaxNbPossEvosSet(),_str,_args);
    }

    public static Struct callPokedexBeanTypedMinNbPossEvosSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanTypedMinNbPossEvosSet(),_str,_args);
    }

    public static Struct callPokedexBeanTypedNameSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanTypedNameSet(),_str,_args);
    }

    public static Struct callPokedexBeanTypedTypeSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new PokedexBeanTypedTypeSet(),_str,_args);
    }

    public static Struct callPokedexBeanWholeWordSet(Struct _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new PokedexBeanWholeWordSet(),_str,_args);
    }
}
