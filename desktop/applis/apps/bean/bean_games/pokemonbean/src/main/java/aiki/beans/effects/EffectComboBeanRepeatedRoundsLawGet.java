package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class EffectComboBeanRepeatedRoundsLawGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getLgIntRate(( (EffectComboBean) ((PokemonBeanStruct)_instance).getInstance()).getRepeatedRoundsLaw());
    }
}
