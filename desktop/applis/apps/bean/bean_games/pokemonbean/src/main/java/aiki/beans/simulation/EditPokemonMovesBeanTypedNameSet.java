package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EditPokemonMovesBeanTypedNameSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (EditPokemonMovesBean) ((PokemonBeanStruct)_instance).getInstance()).setTypedName(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
