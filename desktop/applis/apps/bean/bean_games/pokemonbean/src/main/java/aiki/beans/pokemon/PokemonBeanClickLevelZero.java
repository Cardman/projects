package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonBeanClickLevelZero implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).clickLevel(NaPa.convertToNumber(_args[0]).intStruct(),0));
    }
}
