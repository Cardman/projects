package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class FighterBeanGetIncrTrackingMovesTeam implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getIncrTrackingMovesTeam(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
