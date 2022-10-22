package aiki.beans.items;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.db.InitDbConstr;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbItems extends InitDbConstr {

    public static void fwdEffectWhileSendingWithStatistic(Struct _update, Struct _use) {
        callEffectWhileSendingBeanEffectSet(_update,callItemForBattleBeanGetEffectSending(_use));
    }
    public static Struct callItemForBattleBeanGetEffectSending(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetEffectSending(),_str,_args);
    }
}
