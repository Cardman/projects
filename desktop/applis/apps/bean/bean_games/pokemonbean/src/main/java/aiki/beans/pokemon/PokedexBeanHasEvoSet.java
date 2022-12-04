package aiki.beans.pokemon;

import aiki.beans.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;

public class PokedexBeanHasEvoSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (WithFilterBean) ((PokemonBeanStruct)_instance).getInstance()).setHasEvo(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
