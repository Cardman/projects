package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class FighterBeanTrappingMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return AikiBeansFightStd.getWcMvAm(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getTrappingMoves());
    }
}
