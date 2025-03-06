package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class AddPokemonBeanGenderSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (AddPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getCommon().getGender().setupValue(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
