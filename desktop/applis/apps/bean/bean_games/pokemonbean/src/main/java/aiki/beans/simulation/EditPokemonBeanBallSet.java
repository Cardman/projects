package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class EditPokemonBeanBallSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (EditPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getBall().setupValue(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
