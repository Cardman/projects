package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightCalculationBeanIsBackTargetTeam implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (FightCalculationBean) ((PokemonBeanStruct)_instance).getInstance()).isBackTargetTeam(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
