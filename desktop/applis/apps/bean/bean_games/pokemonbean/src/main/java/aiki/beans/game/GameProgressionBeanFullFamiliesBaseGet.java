package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class GameProgressionBeanFullFamiliesBaseGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrListStrList(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getFullFamiliesBase());
    }
}
