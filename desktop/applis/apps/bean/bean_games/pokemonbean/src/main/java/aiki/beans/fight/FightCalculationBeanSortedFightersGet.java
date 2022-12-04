package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightCalculationBeanSortedFightersGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return AikiBeansFightStd.getTeamPos(( (FightCalculationBean) ((PokemonBeanStruct)_instance).getInstance()).getSortedFighters());
    }
}
