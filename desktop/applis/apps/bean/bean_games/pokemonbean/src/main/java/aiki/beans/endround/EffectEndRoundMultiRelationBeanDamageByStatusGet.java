package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectEndRoundMultiRelationBeanDamageByStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrRate(( (EffectEndRoundMultiRelationBean) ((PokemonBeanStruct)_instance).getInstance()).getDamageByStatus());
    }
}
