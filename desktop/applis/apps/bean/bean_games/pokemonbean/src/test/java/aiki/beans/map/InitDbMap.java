package aiki.beans.map;

import aiki.beans.*;
import aiki.beans.db.InitDbConstr;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbMap extends InitDbConstr {
    public static Struct callAreaApparitionGetAvgNbSteps(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaApparitionGetAvgNbSteps(),_str,_args);
    }

    public static Struct callAreaApparitionGetWildPokemon(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaApparitionGetWildPokemon(),_str,_args);
    }

    public static Struct callAreaApparitionGetWildPokemonFishing(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AreaApparitionGetWildPokemonFishing(),_str,_args);
    }
}
