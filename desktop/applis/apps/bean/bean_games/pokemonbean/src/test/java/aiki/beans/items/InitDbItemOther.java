package aiki.beans.items;

import aiki.beans.BeanPokemonCommonTs;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbItemOther extends InitDbItem {


    public static Struct callBallBeanCatchingRateGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BallBeanCatchingRateGet(),_str,_args);
    }

    public static Struct callBallBeanMapVarsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new BallBeanMapVarsGet(),_str,_args);
    }

    public static Struct callRepelBeanStepsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new RepelBeanStepsGet(),_str,_args);
    }

}
