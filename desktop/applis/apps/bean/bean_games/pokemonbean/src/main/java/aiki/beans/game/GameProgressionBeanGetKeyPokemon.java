package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class GameProgressionBeanGetKeyPokemon implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getKeyPokemon(NaPa.convertToNumber(_args[0]).intStruct(),NaPa.convertToNumber(_args[1]).intStruct()));
    }
}
