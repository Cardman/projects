package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokedexBeanTypedTypeSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (PokedexBean) ((PokemonBeanStruct)_instance).getInstance()).setTypedType(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
