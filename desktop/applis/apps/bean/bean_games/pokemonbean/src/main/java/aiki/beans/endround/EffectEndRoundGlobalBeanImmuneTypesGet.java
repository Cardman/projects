package aiki.beans.endround;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectEndRoundGlobalBeanImmuneTypesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getValues(( (EffectEndRoundGlobalBean) ((PokemonBeanStruct)_instance).getInstance()).getImmuneTypes());
    }
}
