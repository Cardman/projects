package aiki.beans.endround;

import aiki.beans.*;
import aiki.beans.db.InitDbConstr;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbEndRound extends InitDbConstr {
    public static Struct callEffectEndRoundBeanIndexSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new EffectEndRoundBeanIndexSet(),_str,_args);
    }
}
