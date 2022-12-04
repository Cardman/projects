package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightBeanEnabledMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return AikiBeansFightStd.getActMove(( (FightBean) ((PokemonBeanStruct)_instance).getInstance()).getEnabledMoves());
    }
}
