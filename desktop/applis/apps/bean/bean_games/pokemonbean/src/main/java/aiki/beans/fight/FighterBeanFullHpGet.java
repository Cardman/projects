package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NaSt;
import code.bean.nat.NatCaller;
import code.bean.nat.RtSt;

public class FighterBeanFullHpGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getMaxHp());
    }
}
