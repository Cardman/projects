package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectStatusBeanIsStatus implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (EffectStatusBean) ((PokemonBeanStruct)_instance).getInstance()).isStatus(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
