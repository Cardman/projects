package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.LgIntStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class FighterBeanNbRoundsGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new LgIntStruct(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getNbRounds());
    }
}
