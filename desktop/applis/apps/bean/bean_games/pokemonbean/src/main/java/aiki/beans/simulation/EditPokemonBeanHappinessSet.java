package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class EditPokemonBeanHappinessSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (EditPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getHappiness().valueLong(NaPa.convertToNumber(_args[0]).longStruct());
        return NaNu.NULL_VALUE;
    }
}
