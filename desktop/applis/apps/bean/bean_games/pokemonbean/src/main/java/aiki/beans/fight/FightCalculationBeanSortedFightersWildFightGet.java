package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class FightCalculationBeanSortedFightersWildFightGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return AikiBeansFightStd.getBigNatMapLs(( (FightCalculationBean) ((PokemonBeanStruct)_instance).getInstance()).getSortedFightersWildFight());
    }
}
