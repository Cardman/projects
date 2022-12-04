package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EditPokemonMovesBeanTypedTypeSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (EditPokemonMovesBean) ((PokemonBeanStruct)_instance).getInstance()).setTypedType(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
