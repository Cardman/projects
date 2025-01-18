package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
public class PokemonBeanHiddenMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getIntStr(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getHiddenMoves());
    }
}
