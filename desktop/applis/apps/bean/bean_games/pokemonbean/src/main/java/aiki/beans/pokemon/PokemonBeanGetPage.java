package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonBeanGetPage implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getPage(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
