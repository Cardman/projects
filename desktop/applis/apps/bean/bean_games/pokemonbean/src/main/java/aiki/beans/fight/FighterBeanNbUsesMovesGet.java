package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class FighterBeanNbUsesMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrInteger(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getNbUsesMoves());
    }
}
