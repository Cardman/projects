package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class EditPokemonBeanRemainingHpSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (EditPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).setRemainingHp(RateStruct.convertToRate(_args[0]));
        return NaNu.NULL_VALUE;
    }
}
