package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class FighterBeanTrackingMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return AikiBeansFightStd.getWcMvTp(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getTrackingMoves());
    }
}
