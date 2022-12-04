package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonBeanClickEggPk implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).clickEggPk(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
