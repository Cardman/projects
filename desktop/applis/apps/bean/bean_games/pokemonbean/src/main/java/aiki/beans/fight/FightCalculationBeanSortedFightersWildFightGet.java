package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightCalculationBeanSortedFightersWildFightGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return AikiBeansFightStd.getBigNatMapLs(( (FightCalculationBean) ((PokemonBeanStruct)_instance).getInstance()).getSortedFightersWildFight());
    }
}
