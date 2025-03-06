package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class EditPokemonBeanRemainingHpGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (EditPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getRemainingHp().valueRate());
    }
}
