package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AddPokemonBeanTypedNameGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AddPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getTypedName().tryRet());
    }
}
