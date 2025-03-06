package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class EditPokemonBeanExperienceSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (EditPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getExperience().valueRate(RtSt.convertToRate(_args[0]));
        return NaNu.NULL_VALUE;
    }
}
