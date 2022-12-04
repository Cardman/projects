package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightCalculationBeanGetTargetNameFoeChoice implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (FightCalculationBean) ((PokemonBeanStruct)_instance).getInstance()).getTargetNameFoeChoice(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
