package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonBeanIsAppearingZero implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).isAppearing(NaPa.convertToNumber(_args[0]).intStruct(),0));
    }
}
