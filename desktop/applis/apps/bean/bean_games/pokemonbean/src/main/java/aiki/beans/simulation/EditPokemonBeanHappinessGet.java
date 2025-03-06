package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class EditPokemonBeanHappinessGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (EditPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getHappiness().valueLong());
    }
}
