package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectCloneBeanClickMoveSending implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectCloneBean) ((PokemonBeanStruct)_instance).getInstance()).clickMoveSending(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
