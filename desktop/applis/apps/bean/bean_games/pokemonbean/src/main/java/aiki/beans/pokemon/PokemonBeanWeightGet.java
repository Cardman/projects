package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class PokemonBeanWeightGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getWeight());
    }
}
