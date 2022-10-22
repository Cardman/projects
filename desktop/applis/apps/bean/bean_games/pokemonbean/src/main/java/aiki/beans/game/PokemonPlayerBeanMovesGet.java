package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.facade.UsesOfMoveStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class PokemonPlayerBeanMovesGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return UsesOfMoveStruct.getUsesStr(( (PokemonPlayerBean) ((PokemonBeanStruct)_instance).getInstance()).getMoves());
    }
}
