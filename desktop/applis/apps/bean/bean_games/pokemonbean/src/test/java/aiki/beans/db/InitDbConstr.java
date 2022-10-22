package aiki.beans.db;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.InitDbBean;
import aiki.beans.effects.EffectWhileSendingBeanEffectSet;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.scripts.confs.PkScriptPagesInit;

public abstract class InitDbConstr extends InitDbBean {
    public static String navigateData(NatCaller _caller, String _url, String _concat, Struct _str, long... _args) {
        return navigate(_caller,_url, PkScriptPagesInit.initConfData(new Configuration()),_concat,_str,_args);
    }
    public static Struct callEffectWhileSendingBeanEffectSet(Struct _str, Struct _args) {
        return BeanPokemonCommonTs.callStruct(new EffectWhileSendingBeanEffectSet(),_str,_args);
    }
}
