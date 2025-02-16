package aiki.beans.moves.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectDamageBeanBoostStatisOnceKoFoeGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrLongVal(( (EffectDamageBean) ((PokemonBeanStruct)_instance).getInstance()).getBoostStatisOnceKoFoe());
    }
}
