package aiki.beans.fight;

import aiki.beans.*;
import code.bean.nat.*;
public class FightCalculationBeanSortedFightersGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (FightCalculationBean) ((PokemonBeanStruct)_instance).getInstance()).getSortedFighters());
    }
}
