package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class PokemonBeanTechnicalMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getShortStr(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getTechnicalMoves());
    }
}
