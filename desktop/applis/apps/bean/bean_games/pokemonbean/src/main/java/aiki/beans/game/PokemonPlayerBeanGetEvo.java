package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonPlayerBeanGetEvo implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (PokemonPlayerBean) ((PokemonBeanStruct)_instance).getInstance()).getEvo(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
