package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EffectComboBeanGetTrStatistic implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EffectComboBean) ((PokemonBeanStruct)_instance).getInstance()).getTrStatistic(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
