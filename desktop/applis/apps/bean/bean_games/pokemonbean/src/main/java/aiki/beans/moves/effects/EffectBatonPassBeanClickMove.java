package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectBatonPassBeanClickMove implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectBatonPassBean) ((PokemonBeanStruct)_instance).getInstance()).clickMove(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
