package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class EffectProtectionBeanProtSingleAgainstKoGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (EffectProtectionBean) ((PokemonBeanStruct)_instance).getInstance()).getProtSingleAgainstKo());
    }
}
