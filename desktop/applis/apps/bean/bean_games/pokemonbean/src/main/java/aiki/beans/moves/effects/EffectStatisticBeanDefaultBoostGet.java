package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class EffectStatisticBeanDefaultBoostGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( (EffectStatisticBean) ((PokemonBeanStruct)_instance).getInstance()).getDefaultBoost());
    }
}
