package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.facade.UsesOfMoveStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonPlayerBeanMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return UsesOfMoveStruct.getUsesStr(( (PokemonPlayerBean) ((PokemonBeanStruct)_instance).getInstance()).getMoves());
    }
}
