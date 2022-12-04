package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.facade.UsesOfMoveStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class FighterBeanMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return UsesOfMoveStruct.getUsesStr(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getMoves());
    }
}
