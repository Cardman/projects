package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectStatusBeanGetFail implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectStatusBean) ((PokemonBeanStruct)_instance).getInstance()).getFail(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
