package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class HealingPpBeanHealedMovePpGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (HealingPpBean) ((PokemonBeanStruct)_instance).getInstance()).getHealedMovePp());
    }
}
