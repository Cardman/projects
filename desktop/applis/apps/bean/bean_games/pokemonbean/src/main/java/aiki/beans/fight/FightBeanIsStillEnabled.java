package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightBeanIsStillEnabled implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (FightBean) ((PokemonBeanStruct)_instance).getInstance()).isStillEnabled(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
