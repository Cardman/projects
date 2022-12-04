package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EditTrainerPokemonBeanGenderSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (EditTrainerPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getCommon().setGender(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
