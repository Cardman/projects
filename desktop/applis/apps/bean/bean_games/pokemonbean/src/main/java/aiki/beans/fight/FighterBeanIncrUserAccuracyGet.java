package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class FighterBeanIncrUserAccuracyGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return AikiBeansFightStd.getWcMvTpBool(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getIncrUserAccuracy());
    }
}
