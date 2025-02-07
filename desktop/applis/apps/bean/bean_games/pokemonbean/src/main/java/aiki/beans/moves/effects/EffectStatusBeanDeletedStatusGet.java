package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectStatusBeanDeletedStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (EffectStatusBean) ((PokemonBeanStruct)_instance).getInstance()).getDeletedStatus());
    }
}
