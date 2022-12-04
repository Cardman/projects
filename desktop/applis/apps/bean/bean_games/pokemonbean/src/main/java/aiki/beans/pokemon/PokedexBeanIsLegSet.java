package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokedexBeanIsLegSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (PokedexBean) ((PokemonBeanStruct)_instance).getInstance()).setIsLeg(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
