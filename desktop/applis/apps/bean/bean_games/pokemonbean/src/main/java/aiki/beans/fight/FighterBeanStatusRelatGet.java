package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class FighterBeanStatusRelatGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return AikiBeansFightStd.getWcMvTpNb(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getStatusRelat());
    }
}
