package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.LgSt;
import code.bean.nat.*;
import code.bean.nat.*;
public class FighterBeanNbRepeatingSuccessfulMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new LgSt(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getNbRepeatingSuccessfulMoves());
    }
}
