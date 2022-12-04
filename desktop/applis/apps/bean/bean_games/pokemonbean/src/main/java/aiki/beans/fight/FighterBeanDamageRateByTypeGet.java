package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class FighterBeanDamageRateByTypeGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return AikiBeansFightStd.getMultPowStr(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getDamageRateByType());
    }
}
