package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.facade.UsesOfMoveStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class FighterBeanCurrentMovesGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return UsesOfMoveStruct.getUsesStr(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getCurrentMoves());
    }
}
