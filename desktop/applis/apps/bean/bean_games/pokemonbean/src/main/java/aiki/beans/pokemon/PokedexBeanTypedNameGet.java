package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokedexBeanTypedNameGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (PokedexBean) ((PokemonBeanStruct)_instance).getInstance()).getTypedName().tryRet());
    }
}
