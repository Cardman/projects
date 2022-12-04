package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonBeanIsAppearingPlace implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).isAppearingPlace(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
