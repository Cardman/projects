package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class FighterBeanTrappingMovesGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getWcMvAm(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getTrappingMoves());
    }
}
