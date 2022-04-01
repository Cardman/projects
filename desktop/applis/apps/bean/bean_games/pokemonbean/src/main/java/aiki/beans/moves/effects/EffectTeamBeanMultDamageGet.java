package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class EffectTeamBeanMultDamageGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getCatMultRateMap(( (EffectTeamBean) ((PokemonBeanStruct)_instance).getInstance()).getMultDamage());
    }
}
