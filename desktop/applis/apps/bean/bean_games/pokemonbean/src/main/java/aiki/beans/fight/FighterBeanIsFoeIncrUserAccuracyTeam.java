package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class FighterBeanIsFoeIncrUserAccuracyTeam implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).isFoeIncrUserAccuracyTeam(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
