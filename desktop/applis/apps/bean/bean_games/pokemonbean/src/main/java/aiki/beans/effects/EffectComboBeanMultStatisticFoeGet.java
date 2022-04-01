package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class EffectComboBeanMultStatisticFoeGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getStaRate(( (EffectComboBean) ((PokemonBeanStruct)_instance).getInstance()).getMultStatisticFoe());
    }
}
