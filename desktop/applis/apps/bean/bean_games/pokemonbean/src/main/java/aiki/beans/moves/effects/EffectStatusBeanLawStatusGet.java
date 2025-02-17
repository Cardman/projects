package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectStatusBeanLawStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrLongStatKey(( (EffectStatusBean) ((PokemonBeanStruct)_instance).getInstance()).getLawStatus());
    }
}
