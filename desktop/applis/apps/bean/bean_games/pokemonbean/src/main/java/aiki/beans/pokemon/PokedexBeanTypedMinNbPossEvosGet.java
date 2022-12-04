package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokedexBeanTypedMinNbPossEvosGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (PokedexBean) ((PokemonBeanStruct)_instance).getInstance()).getTypedMinNbPossEvos());
    }
}
