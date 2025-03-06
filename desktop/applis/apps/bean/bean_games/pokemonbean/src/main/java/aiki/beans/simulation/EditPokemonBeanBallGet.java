package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class EditPokemonBeanBallGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EditPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getBall().tryRet());
    }
}
