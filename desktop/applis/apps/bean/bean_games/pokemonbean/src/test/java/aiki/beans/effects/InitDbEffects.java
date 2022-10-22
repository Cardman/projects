package aiki.beans.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.InitDbBean;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbEffects extends InitDbBean {
    public void fwdComboDto(Struct _update, Struct _use) {
        callEffectComboBeanCombosSet(_update,callCombosBeanCombosGet(_use));
    }

    public static Struct callEffectComboBeanCombosSet(Struct _str, Struct _args) {
        return BeanPokemonCommonTs.callStruct(new EffectComboBeanCombosSet(),_str,_args);
    }

    public static Struct callCombosBeanCombosGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new CombosBeanCombosGet(),_str,_args);
    }
}