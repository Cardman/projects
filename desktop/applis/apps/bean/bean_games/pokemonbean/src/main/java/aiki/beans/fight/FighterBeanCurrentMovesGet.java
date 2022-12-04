package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.facade.UsesOfMoveStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class FighterBeanCurrentMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return UsesOfMoveStruct.getUsesStr(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getCurrentMoves());
    }
}
