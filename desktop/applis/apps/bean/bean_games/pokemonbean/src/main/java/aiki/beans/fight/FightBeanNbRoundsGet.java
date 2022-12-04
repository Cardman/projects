package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.LgIntStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightBeanNbRoundsGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new LgIntStruct(( (FightBean) ((PokemonBeanStruct)_instance).getInstance()).getNbRounds());
    }
}
