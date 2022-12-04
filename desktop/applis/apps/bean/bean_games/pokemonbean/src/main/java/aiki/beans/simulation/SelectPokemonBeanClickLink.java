package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SelectPokemonBeanClickLink implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SelectPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).clickLink(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
