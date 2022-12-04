package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightCalculationBeanDamageGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return AikiBeansFightStd.getKeyHyp(( (FightCalculationBean) ((PokemonBeanStruct)_instance).getInstance()).getDamage());
    }
}
