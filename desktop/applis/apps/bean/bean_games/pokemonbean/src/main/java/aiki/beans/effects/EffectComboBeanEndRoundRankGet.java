package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class EffectComboBeanEndRoundRankGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( (EffectComboBean) ((PokemonBeanStruct)_instance).getInstance()).getEndRoundRank());
    }
}
