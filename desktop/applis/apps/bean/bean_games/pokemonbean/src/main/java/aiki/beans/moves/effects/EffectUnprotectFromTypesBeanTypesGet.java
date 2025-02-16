package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectUnprotectFromTypesBeanTypesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getTypesDuo(( (EffectUnprotectFromTypesBean) ((PokemonBeanStruct)_instance).getInstance()).getTypes());
    }
}
