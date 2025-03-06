package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class EditTrainerPokemonBeanGenderSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (EditTrainerPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getCommon().getGender().setupValue(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
