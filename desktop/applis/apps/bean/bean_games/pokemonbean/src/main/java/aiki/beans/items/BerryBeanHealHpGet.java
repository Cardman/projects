package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class BerryBeanHealHpGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (BerryBean) ((PokemonBeanStruct)_instance).getInstance()).getHealHp());
    }
}
