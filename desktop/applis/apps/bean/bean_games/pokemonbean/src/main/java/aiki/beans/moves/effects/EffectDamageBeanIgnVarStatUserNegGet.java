package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectDamageBeanIgnVarStatUserNegGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getValues(( (EffectDamageBean) ((PokemonBeanStruct)_instance).getInstance()).getIgnVarStatUserNeg());
    }
}
