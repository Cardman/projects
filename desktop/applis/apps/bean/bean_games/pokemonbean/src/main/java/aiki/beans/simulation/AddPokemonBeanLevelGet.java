package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class AddPokemonBeanLevelGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(((AddPokemonBean) ((PokemonBeanStruct) _instance).getInstance()).getCommon().getLevel().valueLong());
    }
}
