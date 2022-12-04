package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SelectPokemonBeanTypedNameSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SelectPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).setTypedName(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
