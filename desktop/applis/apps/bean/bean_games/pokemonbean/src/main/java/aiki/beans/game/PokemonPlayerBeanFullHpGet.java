package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
public class PokemonPlayerBeanFullHpGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RateStruct(( (PokemonPlayerBean) ((PokemonBeanStruct)_instance).getInstance()).getFullHp());
    }
}
