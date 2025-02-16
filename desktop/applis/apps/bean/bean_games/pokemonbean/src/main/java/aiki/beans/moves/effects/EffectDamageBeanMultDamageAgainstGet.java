package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectDamageBeanMultDamageAgainstGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrRateVal(( (EffectDamageBean) ((PokemonBeanStruct)_instance).getInstance()).getMultDamageAgainst());
    }
}
