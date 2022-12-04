package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
public class FighterBeanNecessaryPointsNextLevelGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RateStruct(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getNecessaryPointsNextLevel());
    }
}
